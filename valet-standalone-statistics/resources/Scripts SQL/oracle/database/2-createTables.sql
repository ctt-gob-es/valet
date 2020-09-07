-- TABLE C_SERVICETYPES
CREATE TABLE "C_SERVICETYPES"(
  "SERVICETYPEPK" Number(8,0) NOT NULL,
  "DESCRIPTION" VARCHAR2(255) NOT NULL
)
TABLESPACE VALET_STATISTICS_DW_TS INITRANS 1 MAXTRANS 255 NOCACHE;

ALTER TABLE "C_SERVICETYPES" ADD CONSTRAINT "SERVICETYPEPK" PRIMARY KEY ("SERVICETYPEPK") USING INDEX TABLESPACE VALET_STATISTICS_DW_INDEX_TS;

COMMENT ON TABLE "C_SERVICETYPES" IS 'Tabla que almacena las constantes para los tipos de servicio.';
COMMENT ON COLUMN "C_SERVICETYPES"."SERVICETYPEPK" IS 'Identificador de la tabla.';
COMMENT ON COLUMN "C_SERVICETYPES"."DESCRIPTION" IS 'Valor que indica el nombre del servicio.';

-- TABLA: C_CODRESULTS

CREATE TABLE "C_CODRESULTS"(
  "CODRESULTPK" NUMBER(1) NOT NULL,
  "DESCRIPTION" VARCHAR2(255) NOT NULL
)
TABLESPACE VALET_STATISTICS_DW_TS INITRANS 1 MAXTRANS 255 NOCACHE;

ALTER TABLE "C_CODRESULTS" ADD CONSTRAINT "CRESULT_PK" PRIMARY KEY ("CODRESULTPK") USING INDEX TABLESPACE VALET_STATISTICS_DW_TS;

COMMENT ON TABLE "C_CODRESULTS" IS 'Tabla que almacena los distintos resultado de un servicio.';
COMMENT ON COLUMN "C_CODRESULTS"."CODRESULTPK" IS 'Identificador del tipo de resultado.';
COMMENT ON COLUMN "C_CODRESULTS"."DESCRIPTION" IS 'Valor del código del error.';

--TABLA DIM_DATES

CREATE TABLE "DIM_DATES"(
  "DATEPK" NUMBER(19,0) NOT NULL,
  "NYEAR" NUMBER(4) NOT NULL,
  "NMONTH" NUMBER(2) NOT NULL,
  "NDAY" NUMBER(2) NOT NULL,
  CONSTRAINT "DIM_DATES_PK" PRIMARY KEY ("DATEPK") USING INDEX TABLESPACE VALET_STATISTICS_DW_INDEX_TS
)
TABLESPACE VALET_STATISTICS_DW_TS INITRANS 1 MAXTRANS 255 NOCACHE;
COMMENT ON TABLE "DIM_DATES" IS 'Tabla que almacena los distintos dias en los que se realizarón el precalculo de estadísticas.';
COMMENT ON COLUMN "DIM_DATES"."DATEPK" IS 'Identificador del día de cálculo. En formato YYYYMMDD';
COMMENT ON COLUMN "DIM_DATES"."NYEAR" IS 'Año.';
COMMENT ON COLUMN "DIM_DATES"."NMONTH" IS 'Mes.';
COMMENT ON COLUMN "DIM_DATES"."NDAY" IS 'Día del Mes.';

-- TABLA DIM_APPLICATIONS
CREATE TABLE "DIM_APPLICATIONS"(
  "APPLICATIONPK" NUMBER(19,0) NOT NULL,
  "IDENTIFICATOR" VARCHAR2(2000 ) NOT NULL,
  "REGISTRATION_DATE" NUMBER(19,0) NOT NULL,
  CONSTRAINT "APP_U_IDENT" UNIQUE ("IDENTIFICATOR"),
    -- FK: DIM_APPLICATIONS - DIM_DATES
  CONSTRAINT FK_APP_DIM_DATES FOREIGN KEY (REGISTRATION_DATE) REFERENCES DIM_DATES(DATEPK) ON DELETE CASCADE
)
TABLESPACE VALET_STATISTICS_DW_TS INITRANS 1 MAXTRANS 255 NOCACHE;

ALTER TABLE "DIM_APPLICATIONS" ADD CONSTRAINT "APP_PK" PRIMARY KEY ("APPLICATIONPK") USING INDEX TABLESPACE VALET_STATISTICS_DW_INDEX_TS;


COMMENT ON TABLE "DIM_APPLICATIONS" IS 'Tabla que almacena las aplicaciones que han generado estadísticas.';
COMMENT ON COLUMN "DIM_APPLICATIONS"."APPLICATIONPK" IS 'Clave primaria de la tabla.';
COMMENT ON COLUMN "DIM_APPLICATIONS"."IDENTIFICATOR" IS 'Valor que representa el identificador de aplicación.';
COMMENT ON COLUMN "DIM_APPLICATIONS"."REGISTRATION_DATE" IS 'Fecha de inserción del registro.';

-- TABLA DIM_NODES

CREATE TABLE "DIM_NODES"(
  "NODEPK" NUMBER(19,0) NOT NULL,
  "FILENAME" VARCHAR2(255) NOT NULL,
  "START_DATE" DATE NOT NULL,
  "END_DATE" DATE NOT NULL,
  CONSTRAINT "FILENAME_UNIQUE" UNIQUE ("FILENAME")
)
TABLESPACE VALET_STATISTICS_DW_TS INITRANS 1 MAXTRANS 255 NOCACHE;

ALTER TABLE "DIM_NODES" ADD CONSTRAINT "NODE_PK" PRIMARY KEY ("NODEPK") USING INDEX TABLESPACE VALET_STATISTICS_DW_INDEX_TS;

COMMENT ON TABLE "DIM_NODES" IS 'Tabla que almacena el path de las unidades organizativas que han generado estadísticas.';
COMMENT ON COLUMN "DIM_NODES"."NODEPK" IS 'Identificador de la tabla.';
COMMENT ON COLUMN "DIM_NODES"."FILENAME" IS 'Valor que representa el nombre del fichero de log procesado.';
COMMENT ON COLUMN "DIM_NODES"."START_DATE" IS 'Valor que representa la fecha de inicio del procesado del fichero de log.';
COMMENT ON COLUMN "DIM_NODES"."END_DATE" IS 'Valor que representa la fecha de fin del procesado del fichero de log.';


-- TABLA FCT_TRANSACTIONS

CREATE TABLE "FCT_TRANSACTIONS"(
	"TRANSACTIONPK" NUMBER(19,0) NOT NULL,
	"MATCHES" NUMBER (7) NOT NULL,
	"ID_DATE" NUMBER (19,0) NOT NULL,
	"ID_SERVICE_TYPE" NUMBER(19,0) NOT NULL,
	"ID_APPLICATION" NUMBER(19,0),
	"ID_DELEGATED_APPLICATION" NUMBER(19,0),
	"ID_CODRESULT" NUMBER(1),
	"ID_NODE" NUMBER(19,0) NOT NULL,
	CONSTRAINT "GROUP_PK" PRIMARY KEY ("TRANSACTIONPK") USING INDEX TABLESPACE VALET_STATISTICS_DW_INDEX_TS,
	-- FK: FCT_TRANSACTIONS - DIM_DATES
	CONSTRAINT FK_GROUP_DIM_DATES FOREIGN KEY (ID_DATE) REFERENCES DIM_DATES(DATEPK) ON DELETE CASCADE,
	-- FK: FCT_TRANSACTIONS - C_SERVICETYPES
	CONSTRAINT FK_GROUP_SV FOREIGN KEY (ID_SERVICE_TYPE) REFERENCES C_SERVICETYPES(SERVICETYPEPK) ON DELETE CASCADE,
	-- FK: FCT_TRANSACTIONS - DIM_APPLICATIONS
	CONSTRAINT FK_GROUP_APP FOREIGN KEY (ID_APPLICATION) REFERENCES DIM_APPLICATIONS(APPLICATIONPK) ON DELETE CASCADE,
	-- FK: FCT_TRANSACTIONS - DIM_DELEGATED_APPLICATIONS
	CONSTRAINT FK_GROUP_DELAPP FOREIGN KEY (ID_DELEGATED_APPLICATION) REFERENCES DIM_APPLICATIONS(APPLICATIONPK) ON DELETE CASCADE,	
	-- FK: FCT_TRANSACTIONS - C_CODRESULTS
	CONSTRAINT FK_GROUP_RES FOREIGN KEY (ID_CODRESULT) REFERENCES C_CODRESULTS(CODRESULTPK) ON DELETE CASCADE,
	-- FK: FCT_TRANSACTIONS - DIM_NODES
	CONSTRAINT FK_GROUP_NODE FOREIGN KEY (ID_NODE) REFERENCES DIM_NODES(NODEPK) ON DELETE CASCADE
)
TABLESPACE VALET_STATISTICS_DW_TS INITRANS 1 MAXTRANS 255 NOCACHE;

COMMENT ON TABLE "FCT_TRANSACTIONS" IS 'Tabla que recoge las distintas agrupaciones de resultados.';
COMMENT ON COLUMN "FCT_TRANSACTIONS"."TRANSACTIONPK" IS 'Clave primaria de la tabla.';
COMMENT ON COLUMN "FCT_TRANSACTIONS"."MATCHES" IS 'Número de coincidancias.';
COMMENT ON COLUMN "FCT_TRANSACTIONS"."ID_DATE" IS 'Referencia a la fecha de ejecución de la transación.';
COMMENT ON COLUMN "FCT_TRANSACTIONS"."ID_SERVICE_TYPE" IS 'Referencia al tipo de servicio.';
COMMENT ON COLUMN "FCT_TRANSACTIONS"."ID_APPLICATION" IS 'Referencia a la aplicación.';
COMMENT ON COLUMN "FCT_TRANSACTIONS"."ID_DELEGATED_APPLICATION" IS 'Referencia al identificador de la aplicación delegada.';
COMMENT ON COLUMN "FCT_TRANSACTIONS"."ID_CODRESULT" IS 'Referencia al resultado de la operación.';
COMMENT ON COLUMN "FCT_TRANSACTIONS"."ID_NODE" IS 'Referencia al identificador del nodo de control.';
 

-- TABLA FCT_VALIDATIONS
CREATE TABLE "FCT_VALIDATIONS"(
	"VALIDATIONPK" NUMBER(19,0) NOT NULL,
	"MATCHES" NUMBER (7) NOT NULL,
	"ID_DATE" NUMBER (19,0) NOT NULL,
	"ID_APPLICATION" NUMBER(19,0),
	"ID_DELEGATED_APPLICATION" NUMBER(19,0),
	"COUNTRY" VARCHAR(2),
	"TSP_NAME" VARCHAR(255),
	"TSP_SERVICE" VARCHAR(255),
	"TSP_SERVICE_HIST" VARCHAR(255),
	"ID_NODE" NUMBER(19,0) NOT NULL,
	CONSTRAINT "VALIDATIONPK" PRIMARY KEY ("VALIDATIONPK") USING INDEX TABLESPACE VALET_STATISTICS_DW_INDEX_TS,
	-- FK: FCT_VALIDATIONS - DIM_DATES
	CONSTRAINT FK_FCTVALIDATION_DIM_DATES FOREIGN KEY (ID_DATE) REFERENCES DIM_DATES(DATEPK) ON DELETE CASCADE,
	-- FK: FCT_VALIDATIONS - DIM_APPLICATIONS
	CONSTRAINT FK_FCTVALIDATION_APP FOREIGN KEY (ID_APPLICATION) REFERENCES DIM_APPLICATIONS(APPLICATIONPK) ON DELETE CASCADE,
	-- FK: FCT_VALIDATIONS - DIM_DELEGATED_APPLICATIONS
	CONSTRAINT FK_FCTVALIDATION_DELAPP FOREIGN KEY (ID_DELEGATED_APPLICATION) REFERENCES DIM_APPLICATIONS(APPLICATIONPK) ON DELETE CASCADE,	
	-- FK: FCT_VALIDATIONS - DIM_NODES
	CONSTRAINT FK_FCTVALIDATION_NODE FOREIGN KEY (ID_NODE) REFERENCES DIM_NODES(NODEPK) ON DELETE CASCADE
)
TABLESPACE VALET_STATISTICS_DW_TS INITRANS 1 MAXTRANS 255 NOCACHE;

COMMENT ON TABLE "FCT_VALIDATIONS" IS 'Tabla que recoge las distintas agrupaciones de resultados.';
COMMENT ON COLUMN "FCT_VALIDATIONS"."VALIDATIONPK" IS 'Clave primaria de la tabla.';
COMMENT ON COLUMN "FCT_VALIDATIONS"."MATCHES" IS 'Número de coincidancias.';
COMMENT ON COLUMN "FCT_VALIDATIONS"."ID_DATE" IS 'Referencia a la fecha de ejecución de la transación.';
COMMENT ON COLUMN "FCT_VALIDATIONS"."ID_APPLICATION" IS 'Referencia a la aplicación.';
COMMENT ON COLUMN "FCT_VALIDATIONS"."ID_DELEGATED_APPLICATION" IS 'Referencia al identificador de la aplicación delegada.';
COMMENT ON COLUMN "FCT_VALIDATIONS"."COUNTRY" IS 'País que emite la TSL.';
COMMENT ON COLUMN "FCT_VALIDATIONS"."TSP_NAME" IS 'Trust Service Provider en el que se ha detectado el certificado.';
COMMENT ON COLUMN "FCT_VALIDATIONS"."TSP_SERVICE" IS 'Trust Service Provider Service en el que se ha detectado el certificado.';
COMMENT ON COLUMN "FCT_VALIDATIONS"."TSP_SERVICE_HIST" IS 'Trust Service Provider Service Historic en el que se ha detectado el certificado.';
COMMENT ON COLUMN "FCT_VALIDATIONS"."ID_NODE" IS 'Referencia al identificador del nodo de control.';



COMMIT;
