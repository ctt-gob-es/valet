-- ######################################################## 1. CREACIÓN DE TABLAS ########################################################
ALTER SESSION SET CURRENT_SCHEMA="VALET_CONFIGOWNER";

-- Table TSL_SERVICE
CREATE TABLE "TSL_SERVICE"(
	"ID_TSL_SERVICE" Number(19, 0) NOT NULL,
	"COUNTRY" Varchar2(2) NOT NULL,
	"TSL_VERSION" Number(8,0) NOT NULL,
	"TSP_NAME" Varchar2(256) NOT NULL,
	"TSP_SERVICE_NAME" Varchar2(256) NOT NULL,
	"DIGITAL_IDENTITY_ID" Varchar2(44) NOT NULL,
	"DIGITAL_IDENTITY_CAD" Timestamp NOT NULL,
	"CERTIFICATE" Blob
) INITRANS 1 MAXTRANS 255 NOCACHE;
ALTER TABLE "TSL_SERVICE" ADD CONSTRAINT "PK_ID_TSL_SERVICE" PRIMARY KEY ("ID_TSL_SERVICE");
COMMENT ON TABLE "TSL_SERVICE" IS 'Tabla que almacena toda la información relativa a servicios TSL.';
COMMENT ON COLUMN "TSL_SERVICE"."ID_TSL_SERVICE" IS 'Clave primaria generada por secuencia.';
COMMENT ON COLUMN "TSL_SERVICE"."COUNTRY" IS 'Código del país.';
COMMENT ON COLUMN "TSL_SERVICE"."TSL_VERSION" IS 'Versión de la TSL.';
COMMENT ON COLUMN "TSL_SERVICE"."TSP_NAME" IS 'Nombre de TSP.';
COMMENT ON COLUMN "TSL_SERVICE"."TSP_SERVICE_NAME" IS 'Nombre del TSP Service.';
COMMENT ON COLUMN "TSL_SERVICE"."DIGITAL_IDENTITY_ID" IS 'Hash (SHA 256) en Base 64 identidad digital.';
COMMENT ON COLUMN "TSL_SERVICE"."DIGITAL_IDENTITY_CAD" IS 'Fecha de caducidad del certificado.';
COMMENT ON COLUMN "TSL_SERVICE"."CERTIFICATE" IS 'Certificado de ejemplo. NO DEBE OBTENERSE EN LA LÓGICA DE SERVICIO';

-- Table TSL_MAPPING
CREATE TABLE "TSL_MAPPING"(
	"ID_TSL_MAPPING" Number(19, 0) NOT NULL,
	"ID_TSL_SERVICE" Number(19, 0) NOT NULL,
	"ID_ASSOCIATION_TYPE" Number(19,0) NOT NULL,
	"LOGICAL_FIELD_ID" Varchar2(256) NOT NULL,
	"LOGICAL_FIELD_VALUE" Varchar2(256) NOT NULL
) INITRANS 1 MAXTRANS 255 NOCACHE;
ALTER TABLE "TSL_MAPPING" ADD CONSTRAINT "PK_ID_MAPPING" PRIMARY KEY ("ID_TSL_MAPPING");
ALTER TABLE "TSL_MAPPING" ADD CONSTRAINT "FK_ID_TSL_SERVICE" FOREIGN KEY ("ID_TSL_SERVICE") REFERENCES "TSL_SERVICE" ("ID_TSL_SERVICE");
ALTER TABLE "TSL_MAPPING" ADD CONSTRAINT "FK_ID_ASSOCIATION_TYPE" FOREIGN KEY ("ID_ASSOCIATION_TYPE") REFERENCES "C_ASSOCIATION_TYPE" ("ID_ASSOCIATION_TYPE");
COMMENT ON TABLE "TSL_MAPPING" IS 'Tabla que almacena toda la información relativa a servicios TSL y campos lógicos.';
COMMENT ON COLUMN "TSL_MAPPING"."ID_TSL_SERVICE" IS 'Clave foranea a la tabla TSL_SERVICE';
COMMENT ON COLUMN "TSL_MAPPING"."ID_ASSOCIATION_TYPE" IS 'Clave foranea a la tabla C_ASSOCIATION_TYPE';
COMMENT ON COLUMN "TSL_MAPPING"."LOGICAL_FIELD_ID" IS 'Nombre del campo lógico. ej: nombreResponsable.';
COMMENT ON COLUMN "TSL_MAPPING"."LOGICAL_FIELD_VALUE" IS 'Valor del campo lógico.';

-- Table EXTERNAL_ACCESS
CREATE TABLE "EXTERNAL_ACCESS"(
	"ID_URL" NUMBER(19,0) NOT NULL,
	"URL" Varchar2(4000) NOT NULL,
	"ORIGIN_URL" Varchar2(4000) NOT NULL,
	"STATE_CONN" CHAR(1 BYTE) NOT NULL,
	"LAST_CONN" TIMESTAMP(6) NOT NULL
) INITRANS 1 MAXTRANS 255 NOCACHE;
ALTER TABLE "EXTERNAL_ACCESS" ADD CONSTRAINT "ID_EXTERNAL_ACCESS" PRIMARY KEY ("ID_URL");
ALTER TABLE "EXTERNAL_ACCESS" ADD CONSTRAINT "SC_UNIQUE_EXTERNAL_ACCESS" UNIQUE ("URL");
COMMENT ON TABLE "EXTERNAL_ACCESS" IS 'Tabla que almacena todas las urls';
COMMENT ON COLUMN "EXTERNAL_ACCESS"."ID_URL" IS 'Identificador de la url de conexión.';
COMMENT ON COLUMN "EXTERNAL_ACCESS"."URL" IS 'URL de conexión.';
COMMENT ON COLUMN "EXTERNAL_ACCESS"."ORIGIN_URL" IS 'Valor que representa el origen de recuperación de las URLs.';
COMMENT ON COLUMN "EXTERNAL_ACCESS"."STATE_CONN" IS 'Valor que indica si la conexión es correcta (Y) o no (N).';
COMMENT ON COLUMN "EXTERNAL_ACCESS"."LAST_CONN" IS 'Valor que indica la fecha de la última conexión.';

-- Table CONF_SERVER_MAIL
ALTER TABLE "CONF_SERVER_MAIL" ADD "CONNECTION_TIMEOUT"  Number(10,0);
ALTER TABLE "CONF_SERVER_MAIL" ADD "READING_TIMEOUT"  Number(10,0);
ALTER TABLE "CONF_SERVER_MAIL" ADD "TLS_ENABLED" Char(1) DEFAULT 'N' NOT NULL;

-- Table SYSTEM_CERTIFICATE
ALTER TABLE "SYSTEM_CERTIFICATE" ADD "COUNTRY" VARCHAR2(20);
COMMENT ON COLUMN "SYSTEM_CERTIFICATE"."COUNTRY" IS 'País del certificado.';

ALTER TABLE "SYSTEM_CERTIFICATE" ADD "COUNTRY" VARCHAR2(20);
COMMENT ON COLUMN "SYSTEM_CERTIFICATE"."COUNTRY" IS 'País del certificado.';

ALTER TABLE EXTERNAL_ACCESS ADD ID_COUNTRY_REGION NUMBER(19,0) NOT NULL;
COMMENT ON COLUMN "EXTERNAL_ACCESS"."ID_COUNTRY_REGION" IS 'Clave foranea a la tabla de regiones de países.';
ALTER TABLE "EXTERNAL_ACCESS" ADD CONSTRAINT "EA_ID_COUNTRY_REGION" FOREIGN KEY ("ID_COUNTRY_REGION") REFERENCES "TSL_COUNTRY_REGION" ("ID_COUNTRY_REGION");


-- Table SYSTEM_CERTIFICATE
ALTER TABLE "SYSTEM_CERTIFICATE" ADD "VALIDATION_CERT" Char(1) DEFAULT 'N' NOT NULL;
COMMENT ON COLUMN "SYSTEM_CERTIFICATE"."VALIDATION_CERT" IS 'Valor que indica si el certificado es válido (Y) o no (N).';

-- Table CONF_SERVER_MAIL
ALTER TABLE "CONF_SERVER_MAIL" ADD "CERTIFICATE_FILE" BLOB NULL;
COMMENT ON COLUMN "CONF_SERVER_MAIL"."CERTIFICATE_FILE" IS 'Columna que almacenará el certificado que se usará para enviar correos con cifrado TLS.';
ALTER TABLE "CONF_SERVER_MAIL" ADD "ORIGINAL_NAME_FILE" VARCHAR2(100) NULL;
COMMENT ON COLUMN "CONF_SERVER_MAIL"."ORIGINAL_NAME_FILE" IS 'Columna que almacenará el nombre original del fichero que contiene el certificado.';

-- ######################################################## 2. CREACIÓN DE SEQUENCIAS ########################################################
ALTER SESSION SET CURRENT_SCHEMA="VALET_CONFIGOWNER";

-- Secuencia necesaria para la clave de la tabla TSL_SERVICE.
CREATE SEQUENCE SQ_TSL_SERVICE
  START WITH 1
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  ORDER;
  
 -- Secuencia necesaria para la clave de la tabla SQ_TSL_MAPPING.
CREATE SEQUENCE SQ_TSL_MAPPING
  START WITH 1
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  ORDER;
  
-- Secuencia necesaria para la clave de la tabla EXTERNAL_ACCESS.
CREATE SEQUENCE SQ_EXTERNAL_ACCESS
  START WITH 1
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  ORDER;
  
-- ######################################################## 3. CREACIÓN DE PERMISOS DE USUARIO ########################################################
 ALTER SESSION SET CURRENT_SCHEMA="SYS";

GRANT INSERT ON "VALET_CONFIGOWNER"."TSL_SERVICE" TO "VALET_CONF";
GRANT SELECT ON "VALET_CONFIGOWNER"."TSL_SERVICE" TO "VALET_CONF";
GRANT UPDATE ON "VALET_CONFIGOWNER"."TSL_SERVICE" TO "VALET_CONF";
GRANT DELETE ON "VALET_CONFIGOWNER"."TSL_SERVICE" TO "VALET_CONF";
GRANT SELECT,ALTER ON "VALET_CONFIGOWNER"."TSL_SERVICE" TO "VALET_CONF";

GRANT INSERT ON "VALET_CONFIGOWNER"."TSL_MAPPING" TO "VALET_CONF";
GRANT SELECT ON "VALET_CONFIGOWNER"."TSL_MAPPING" TO "VALET_CONF";
GRANT UPDATE ON "VALET_CONFIGOWNER"."TSL_MAPPING" TO "VALET_CONF";
GRANT DELETE ON "VALET_CONFIGOWNER"."TSL_MAPPING" TO "VALET_CONF";
GRANT SELECT,ALTER ON "VALET_CONFIGOWNER"."TSL_MAPPING" TO "VALET_CONF";


GRANT INSERT ON "VALET_CONFIGOWNER"."EXTERNAL_ACCESS" TO "VALET_CONF";
GRANT SELECT ON "VALET_CONFIGOWNER"."EXTERNAL_ACCESS" TO "VALET_CONF";
GRANT UPDATE ON "VALET_CONFIGOWNER"."EXTERNAL_ACCESS" TO "VALET_CONF";
GRANT DELETE ON "VALET_CONFIGOWNER"."EXTERNAL_ACCESS" TO "VALET_CONF";
GRANT SELECT,ALTER ON "VALET_CONFIGOWNER"."EXTERNAL_ACCESS" TO "VALET_CONF";

-- ######################################################## 4. CREACIÓN DE SINONIMOS PARA TABLAS Y SEQUENCIAS ########################################################
ALTER SESSION SET CURRENT_SCHEMA="VALET_CONF";

-- TABLAS
CREATE SYNONYM "TSL_SERVICE" FOR "VALET_CONFIGOWNER"."TSL_SERVICE";
CREATE SYNONYM "TSL_MAPPING" FOR "VALET_CONFIGOWNER"."TSL_MAPPING";

-- SECUENCIAS
CREATE OR REPLACE SYNONYM "SQ_TSL_SERVICE" FOR "VALET_CONFIGOWNER"."SQ_TSL_SERVICE";
CREATE OR REPLACE SYNONYM "SQ_LOGICAL_FIELD" FOR "VALET_CONFIGOWNER"."SQ_LOGICAL_FIELD";

-- External_Access
CREATE SYNONYM "EXTERNAL_ACCESS" FOR "VALET_CONFIGOWNER"."EXTERNAL_ACCESS";
CREATE OR REPLACE SYNONYM "SQ_EXTERNAL_ACCESS" FOR "VALET_CONFIGOWNER"."SQ_EXTERNAL_ACCESS"; 

-- ######################################################## 5. INSERTS EN VALET_CONFIGOWNER ########################################################
ALTER SESSION SET CURRENT_SCHEMA="VALET_CONFIGOWNER";

INSERT INTO ALARM (ID_ALARM, DESCRIPTION, TIME_BLOCK, ACTIVE) VALUES('ALM006', 'ALARMDESC006', NULL, 'Y');
INSERT INTO ALARM (ID_ALARM, DESCRIPTION, TIME_BLOCK, ACTIVE) VALUES('ALM007', 'ALARMDESC007', NULL, 'Y');
INSERT INTO ALARM (ID_ALARM, DESCRIPTION, TIME_BLOCK, ACTIVE) VALUES('ALM008', 'ALARMDESC008', NULL, 'Y');
INSERT INTO ALARM (ID_ALARM, DESCRIPTION, TIME_BLOCK, ACTIVE) VALUES('ALM009', 'ALARMDESC009', NULL, 'Y');

-- Se elimina Keystore SSL
DELETE FROM SYSTEM_CERTIFICATE WHERE ID_KEYSTORE = 5;
DELETE FROM KEYSTORE WHERE ID_KEYSTORE = 5;

-- Se añade nuevo planificador 
INSERT INTO PLANNER(ID_PLANNER, HOUR_PERIOD, MINUTE_PERIOD, SECOND_PERIOD, INIT_DAY,ID_PLANNER_TYPE) VALUES (2, 23, 0, 0, TO_TIMESTAMP('01/01/2012 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1);

-- Se añade nueva tarea.
INSERT INTO TASK (ID_TASK, TOKEN_NAME, IMPLEMENTATION_CLASS, IS_ENABLED) VALUES(SQ_TASK.NEXTVAL, 'TASK02', 'es.gob.valet.tasks.ExternalAccessConnectionTestTask', 'N');
INSERT INTO X_TASK_PLANNER (ID_TASK, ID_PLANNER) Values (2, 2);


-- Se añaden nuevas alarmas
INSERT into ALARM (ID_ALARM, DESCRIPTION, TIME_BLOCK, ACTIVE) VALUES ('ALM010', 'ALARMDESC010', null, 'Y');
INSERT into ALARM (ID_ALARM, DESCRIPTION, TIME_BLOCK, ACTIVE) VALUES ('ALM011', 'ALARMDESC011', null, 'Y');

--Se añade un nuevo keystore
Insert into KEYSTORE
(ID_KEYSTORE, NAME, TOKEN_NAME, KEYSTORE, PASSWORD, IS_HARDWARE, KEYSTORE_TYPE, VERSION, HAS_BACKUP)
Values (19,'TrustStoreOCSP', 'KEYSTORE19', 'CECECECE00000002000000002F03597B85569C665FE07C832801E57EFF4A872C', 'e7bwCEbPPU0D4UHC83ZKCVeJb6zc', 'N', 'JCEKS', 0, 'N');

--Se añade el campo NIF a la tabla USER_VALET
ALTER TABLE USER_VALET
ADD NIF VARCHAR2(150) DEFAULT 'DEFAULT_VALUE' NOT NULL;