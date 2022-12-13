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

-- ######################################################## 4. CREACIÓN DE SINONIMOS PARA TABLAS Y SEQUENCIAS ########################################################
ALTER SESSION SET CURRENT_SCHEMA="VALET_CONF";

-- TABLAS
CREATE SYNONYM "TSL_SERVICE" FOR "VALET_CONFIGOWNER"."TSL_SERVICE";
CREATE SYNONYM "TSL_MAPPING" FOR "VALET_CONFIGOWNER"."TSL_MAPPING";

-- SECUENCIAS
CREATE OR REPLACE SYNONYM "SQ_TSL_SERVICE" FOR "VALET_CONFIGOWNER"."SQ_TSL_SERVICE";
CREATE OR REPLACE SYNONYM "SQ_LOGICAL_FIELD" FOR "VALET_CONFIGOWNER"."SQ_LOGICAL_FIELD";