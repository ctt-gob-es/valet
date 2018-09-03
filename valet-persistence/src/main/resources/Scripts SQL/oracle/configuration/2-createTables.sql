-- ********************************************************
-- **************** Creación de Tablas ********************
-- ********************************************************

-- ****************** TABLAS DE CONFIGURACIÓN *****************

-- Table USER_VALET		
 CREATE TABLE "USER_VALET"(
  "ID_USER_VALET" Number(19,0) NOT NULL,
  "LOGIN" Varchar2(100) NOT NULL,
  "PASSWORD" Varchar2(150) NOT NULL,
  "NAME" Varchar2(100) NOT NULL,
  "SURNAMES" Varchar2(150) NOT NULL,
  "EMAIL" Varchar2(150) NOT NULL,
  "IS_BLOCKED" Char(1) NOT NULL,
  "ATTEMPTS_NUMBER" Integer NOT NULL,
  "LAST_ACCESS" Timestamp(6),
  "IP_LAST_ACCESS" Varchar2(15)
)
TABLESPACE VALET_CONFIGURACION_TABLESPACE INITRANS 1 MAXTRANS 255 NOCACHE;
ALTER TABLE "USER_VALET" ADD CONSTRAINT "ID_USER_VALET" PRIMARY KEY ("ID_USER_VALET");
ALTER TABLE "USER_VALET" ADD CONSTRAINT "USER_UNIQUE_LOGIN" UNIQUE ("LOGIN");
COMMENT ON TABLE "USER_VALET" IS 'Tabla que almacena toda la información relativa a usuarios del sistema.';
COMMENT ON COLUMN "USER_VALET"."ID_USER_VALET" IS 'Identificador de la tabla.';
COMMENT ON COLUMN "USER_VALET"."LOGIN" IS 'Valor que representa el nombre de acceso a la plataforma.';
COMMENT ON COLUMN "USER_VALET"."PASSWORD" IS 'Valor que representa el hash de la contraseña de acceso a la plataforma.';
COMMENT ON COLUMN "USER_VALET"."NAME" IS 'Valor que representa el nombre del usuario.';
COMMENT ON COLUMN "USER_VALET"."SURNAMES" IS 'Valor que representa los apellidos del usuario.';
COMMENT ON COLUMN "USER_VALET"."EMAIL" IS 'Valor que representa el e-mail del usuario.';
COMMENT ON COLUMN "USER_VALET"."IS_BLOCKED" IS 'Valor que indica si el usuario está bloqueado (Y) o no (N).';
COMMENT ON COLUMN "USER_VALET"."ATTEMPTS_NUMBER" IS 'Valor que representa el número de intentos fallidos de acceso a la plataforma que lleva acumulados el usuario desde la última vez que accedió correctamente.';
COMMENT ON COLUMN "USER_VALET"."LAST_ACCESS" IS 'Valor que representa la fecha del último acceso del usuario a la plataforma.';
COMMENT ON COLUMN "USER_VALET"."IP_LAST_ACCESS" IS 'Valor que representa la dirección IP desde la que accedió el usuario por última vez.';

/* -- Table TSL_VALET*/
CREATE TABLE "TSL_VALET"(
"ID_TSL_VALET" Number(19,0) NOT NULL,
"SEQUENCE_NUMBER" Integer NOT NULL,
"RESPONSIBLE" Varchar2(128),
"ISSUE_DATE" Timestamp(6) NOT NULL, 
"EXPIRATION_DATE" Timestamp(6) NOT NULL, 
"URI_TSL_LOCATION" Varchar2(512) NOT NULL,
"XML_DOCUMENT" Blob NOT NULL,
"LEGIBLE_DOCUMENT" Blob,
"ID_COUNTRY_REGION" Number(19,0)NULL,
"ID_TSL_IMPL" Number(19,0) NOT NULL,
"NEW_TSL_AVALIABLE" Varchar2(1) NOT NULL,
"LAST_TSLA_FIND" Timestamp(6) ,
"ALIAS" Varchar2(128) NOT NULL
)
TABLESPACE VALET_CONFIGURACION_TABLESPACE INITRANS 1 MAXTRANS 255 NOCACHE;
ALTER TABLE "TSL_VALET" ADD CONSTRAINT "ID_TSL_VALET" PRIMARY KEY ("ID_TSL_VALET");
ALTER TABLE "TSL_VALET" ADD CONSTRAINT "TV_IDCR_SN" UNIQUE ("ID_COUNTRY_REGION","SEQUENCE_NUMBER");
COMMENT ON TABLE "TSL_VALET" IS 'Tabla que almacena todos los datos relacionados con una TSL concreta, asignada a un país/región e implementación.';
COMMENT ON COLUMN "TSL_VALET"."ID_TSL_VALET" IS 'Identificador de la tabla.';
COMMENT ON COLUMN "TSL_VALET"."ID_COUNTRY_REGION" IS 'Identificador del código de país/región según ISO 3166 asignado a esta TSL.';
COMMENT ON COLUMN "TSL_VALET"."URI_TSL_LOCATION" IS 'URI con la localización del lugar de donde se puede obtener esta TSL de forma pública.';
COMMENT ON COLUMN "TSL_VALET"."XML_DOCUMENT" IS 'Representación en bytes del documento XML que define a la TSL.';
COMMENT ON COLUMN "TSL_VALET"."LEGIBLE_DOCUMENT" IS 'Representación en bytes del documento legible de la TSL.';
COMMENT ON COLUMN "TSL_VALET"."ISSUE_DATE" IS 'Fecha de emisión de la TSL.';
COMMENT ON COLUMN "TSL_VALET"."EXPIRATION_DATE" IS 'Fecha de caducidad o próxima emisión de la TSL.';
COMMENT ON COLUMN "TSL_VALET"."SEQUENCE_NUMBER" IS 'Número de secuencia de la TSL.';
COMMENT ON COLUMN "TSL_VALET"."ID_TSL_IMPL" IS 'Identificador de la especificación ETSI TS que sigue la implementación de esta TSL.';
COMMENT ON COLUMN "TSL_VALET"."NEW_TSL_AVALIABLE" IS 'Indica si hay una nueva TSL disponible valores: Y (hay disponible nueva TSL), N (no hay disponible nueva TSL), P (posiblemente hay disponible una nueva TSL).';
COMMENT ON COLUMN "TSL_VALET"."LAST_TSLA_FIND" IS 'Fecha en la que se encontro (avisó) una nueva TSL disponible.';
COMMENT ON COLUMN "TSL_VALET"."ALIAS" IS 'Alias interno asignado a la TSL.';



-- Table TSL_COUNTRY_REGION
CREATE TABLE "TSL_COUNTRY_REGION"(
  "ID_COUNTRY_REGION" Number(19,0) NOT NULL,
  "COUNTRY_REGION_CODE" Varchar2(16) NOT NULL,
  "COUNTRY_REGION_NAME" Varchar2(128) NOT NULL
)TABLESPACE VALET_CONFIGURACION_TABLESPACE INITRANS 1 MAXTRANS 255 NOCACHE;
ALTER TABLE "TSL_COUNTRY_REGION" ADD CONSTRAINT "ID_COUNTRY_REGION" PRIMARY KEY ("ID_COUNTRY_REGION");
ALTER TABLE "TSL_COUNTRY_REGION" ADD CONSTRAINT "TCR_UNIQUE_COUNTRYREGIONCODE" UNIQUE ("COUNTRY_REGION_CODE");
COMMENT ON TABLE "TSL_COUNTRY_REGION" IS 'Tabla que almacena las distintas regiones y/o paises registrados en la plataforma con TSL asociadas.';
COMMENT ON COLUMN "TSL_COUNTRY_REGION"."ID_COUNTRY_REGION" IS 'Identificador de la tabla.';
COMMENT ON COLUMN "TSL_COUNTRY_REGION"."COUNTRY_REGION_CODE" IS 'Código de país/región según ISO 3166.';
COMMENT ON COLUMN "TSL_COUNTRY_REGION"."COUNTRY_REGION_NAME" IS 'Nombre del país/región.';

-- Table C_TSL_IMPL
CREATE TABLE "C_TSL_IMPL"(
  "ID_TSL_IMPL" Number(19,0) NOT NULL,
  "SPECIFICATION" Varchar2(6) NOT NULL,
  "VERSION" Varchar2(5) NOT NULL,
  "NAMESPACE" Varchar2(256) NOT NULL
)TABLESPACE VALET_CONFIGURACION_TABLESPACE INITRANS 1 MAXTRANS 255 NOCACHE;
ALTER TABLE "C_TSL_IMPL" ADD CONSTRAINT "ID_TSL_IMPL" PRIMARY KEY ("ID_TSL_IMPL");
ALTER TABLE "C_TSL_IMPL" ADD CONSTRAINT "CTI_UNIQUE_SPEC_VERS" UNIQUE ("SPECIFICATION","VERSION");
COMMENT ON TABLE "C_TSL_IMPL" IS 'Tabla que almacena constantes relacionadas con los diferentes estándares ETSI TS admitidos para la representación de TSL.';
COMMENT ON COLUMN "C_TSL_IMPL"."ID_TSL_IMPL" IS 'Identificador de la tabla.';
COMMENT ON COLUMN "C_TSL_IMPL"."SPECIFICATION" IS 'Identificador del documento ETSI TS.';
COMMENT ON COLUMN "C_TSL_IMPL"."VERSION" IS 'Versión del documento ETSI TS.';
COMMENT ON COLUMN "C_TSL_IMPL"."NAMESPACE" IS 'URI que representa el namespace asociado a la ETSI TS que se define en esta tupla.';


-- Table TSL_COUNTRY_REGION_MAPPING
CREATE TABLE "TSL_COUNTRY_REGION_MAPPING"(
  "ID_TSL_COUNTRY_REGION_MAPPING" Number(19,0) NOT NULL,
  "ID_COUNTRY_REGION" Number(19,0) NOT NULL,
  "MAPPING_IDENTIFICATOR" Varchar2(30) NOT NULL,
  "MAPPING_DESCRIPTION" Varchar2(255),
  "MAPPING_VALUE" Varchar2(500) NOT NULL
)TABLESPACE VALET_CONFIGURACION_TABLESPACE INITRANS 1 MAXTRANS 255 NOCACHE;
ALTER TABLE "TSL_COUNTRY_REGION_MAPPING" ADD CONSTRAINT "ID_TSL_COUNTRY_REGION_MAPPING" PRIMARY KEY ("ID_TSL_COUNTRY_REGION_MAPPING");
ALTER TABLE "TSL_COUNTRY_REGION_MAPPING" ADD CONSTRAINT "TCRM_IDCR_MI" UNIQUE ("ID_COUNTRY_REGION","MAPPING_IDENTIFICATOR");
COMMENT ON TABLE "TSL_COUNTRY_REGION_MAPPING" IS 'Tabla que almacena los mapeos (lógicos) que se asignarán a los certificados validados con una TSL de un determinado país o región.';
COMMENT ON COLUMN "TSL_COUNTRY_REGION_MAPPING"."ID_TSL_COUNTRY_REGION_MAPPING" IS 'Identificador de la tabla.';
COMMENT ON COLUMN "TSL_COUNTRY_REGION_MAPPING"."ID_COUNTRY_REGION" IS 'Identificador del código de país/región según ISO 3166 asignado a esta TSL.';
COMMENT ON COLUMN "TSL_COUNTRY_REGION_MAPPING"."MAPPING_IDENTIFICATOR" IS 'Identificador para el campo lógico que definirá este mapeo.';
COMMENT ON COLUMN "TSL_COUNTRY_REGION_MAPPING"."MAPPING_DESCRIPTION" IS 'Descripción del campo lógico representado en esta tupla.';
COMMENT ON COLUMN "TSL_COUNTRY_REGION_MAPPING"."MAPPING_VALUE" IS 'Valor asignado al mapeo.';


ALTER TABLE "TSL_VALET" ADD CONSTRAINT "R_TD_ICR" FOREIGN KEY ("ID_COUNTRY_REGION") REFERENCES "TSL_COUNTRY_REGION" ("ID_COUNTRY_REGION") ON DELETE CASCADE;
ALTER TABLE "TSL_VALET" ADD CONSTRAINT "R_TD_ITI" FOREIGN KEY ("ID_TSL_IMPL") REFERENCES "C_TSL_IMPL" ("ID_TSL_IMPL");
ALTER TABLE "TSL_COUNTRY_REGION_MAPPING" ADD CONSTRAINT "R_TCRM_ICR" FOREIGN KEY ("ID_COUNTRY_REGION") REFERENCES "TSL_COUNTRY_REGION" ("ID_COUNTRY_REGION") ON DELETE CASCADE;