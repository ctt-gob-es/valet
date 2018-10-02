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
  
  -- Secuencia necesaria para la clave de la tabla TS_VALET.
CREATE SEQUENCE SQ_TSL_VALET
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
