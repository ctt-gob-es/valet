-- ********************************************************
-- ************** Creación de Secuencias ******************
-- ********************************************************

-- Secuencia necesaria para la clave de la tabla USER_VALET.
CREATE SEQUENCE SQ_USER_VALET
  START WITH 2
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  ORDER; 
  
-- Secuencia necesaria para la clave de la tabla TSL_DATA.
CREATE SEQUENCE SQ_TSL_DATA
  START WITH 2
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  ORDER; 
  
-- Secuencia necesaria para la clave de la tabla TSL_COUNTRY_REGION.
CREATE SEQUENCE SQ_TSL_COUNTRY_REGION
  START WITH 1
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  ORDER;
  
-- Secuencia necesaria para la clave de la tabla TSL_COUNTRY_REGION_MAPPING.
CREATE SEQUENCE SQ_TSL_COUNTRY_REGION_MAPPING
  START WITH 1
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  ORDER;
  
-- Secuencia necesaria para la clave de la tabla SYSTEM_CERTIFICATE.
CREATE SEQUENCE SQ_SYSTEM_CERTIFICATE
  START WITH 1
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  ORDER;
  
-- Secuencia necesaria para la clave de la tabla MAIL.
CREATE SEQUENCE SQ_MAIL
  START WITH 1
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  ORDER; 
  
-- Secuencia necesaria para la clave de la tabla CONF_SERVER_MAIL.
CREATE SEQUENCE SQ_CONF_SERVER_MAIL
  START WITH 1
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  ORDER;

-- Secuencia necesaria para la clave de la tabla TASK.
CREATE SEQUENCE SQ_TASK
  START WITH 2
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  ORDER;

-- Secuencia necesaria para la clave de la tabla TASK.
CREATE SEQUENCE SQ_PLANNER
  START WITH 2
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  ORDER;

-- Secuencia necesaria para la clave de la tabla APPLICATION.
CREATE SEQUENCE SQ_APPLICATION
  START WITH 1
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  ORDER;
  
-- Secuencia necesaria para la clave de la tabla TSL_SERVICE.
CREATE SEQUENCE SQ_TSL_SERVICE
  START WITH 1
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  ORDER;
  
-- Secuencia necesaria para la clave de la tabla LOGICAL_FIELD.
CREATE SEQUENCE SQ_LOGICAL_FIELD
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

COMMIT;
