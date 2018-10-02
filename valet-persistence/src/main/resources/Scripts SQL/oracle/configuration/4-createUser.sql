-- **********************************************************************
-- ************* Creación del Usuario de Configuración ******************
-- **********************************************************************

CREATE USER "VALET_CONF"  PROFILE "DEFAULT" 
    IDENTIFIED BY "PASSWORD" DEFAULT TABLESPACE "VALET_CONFIGURACION_TABLESPACE" 
        ACCOUNT UNLOCK;

GRANT INSERT ON "VALET_CONFIGOWNER"."USER_VALET" TO "VALET_CONF";
GRANT SELECT ON "VALET_CONFIGOWNER"."USER_VALET" TO "VALET_CONF";
GRANT UPDATE ON "VALET_CONFIGOWNER"."USER_VALET" TO "VALET_CONF";
GRANT DELETE ON "VALET_CONFIGOWNER"."USER_VALET" TO "VALET_CONF";
GRANT SELECT,ALTER ON "VALET_CONFIGOWNER"."SQ_USER_VALET" TO "VALET_CONF";

GRANT INSERT ON "VALET_CONFIGOWNER"."TSL_VALET" TO "VALET_CONF";
GRANT SELECT ON "VALET_CONFIGOWNER"."TSL_VALET" TO "VALET_CONF";
GRANT UPDATE ON "VALET_CONFIGOWNER"."TSL_VALET" TO "VALET_CONF";
GRANT DELETE ON "VALET_CONFIGOWNER"."TSL_VALET" TO "VALET_CONF";
GRANT SELECT,ALTER ON "VALET_CONFIGOWNER"."SQ_TSL_VALET" TO "VALET_CONF";

GRANT INSERT ON "VALET_CONFIGOWNER"."TSL_COUNTRY_REGION" TO "VALET_CONF";
GRANT SELECT ON "VALET_CONFIGOWNER"."TSL_COUNTRY_REGION" TO "VALET_CONF";
GRANT UPDATE ON "VALET_CONFIGOWNER"."TSL_COUNTRY_REGION" TO "VALET_CONF";
GRANT DELETE ON "VALET_CONFIGOWNER"."TSL_COUNTRY_REGION" TO "VALET_CONF";
GRANT SELECT,ALTER ON "VALET_CONFIGOWNER"."SQ_TSL_COUNTRY_REGION" TO "VALET_CONF";

GRANT INSERT ON "VALET_CONFIGOWNER"."TSL_COUNTRY_REGION_MAPPING" TO "VALET_CONF";  
GRANT SELECT ON "VALET_CONFIGOWNER"."TSL_COUNTRY_REGION_MAPPING" TO "VALET_CONF";  
GRANT UPDATE ON "VALET_CONFIGOWNER"."TSL_COUNTRY_REGION_MAPPING" TO "VALET_CONF";  
GRANT DELETE ON "VALET_CONFIGOWNER"."TSL_COUNTRY_REGION_MAPPING" TO "VALET_CONF";  
GRANT SELECT,ALTER ON "VALET_CONFIGOWNER"."SQ_TSL_COUNTRY_REGION_MAPPING" TO "VALET_CONF";

GRANT INSERT ON "VALET_CONFIGOWNER"."TSL_COUNTRY_REGION_MAPPING" TO "VALET_CONF";  
GRANT SELECT ON "VALET_CONFIGOWNER"."TSL_COUNTRY_REGION_MAPPING" TO "VALET_CONF";  
GRANT UPDATE ON "VALET_CONFIGOWNER"."TSL_COUNTRY_REGION_MAPPING" TO "VALET_CONF";  
GRANT DELETE ON "VALET_CONFIGOWNER"."TSL_COUNTRY_REGION_MAPPING" TO "VALET_CONF";  
GRANT SELECT,ALTER ON "VALET_CONFIGOWNER"."SQ_TSL_COUNTRY_REGION_MAPPING" TO "VALET_CONF";

GRANT INSERT ON "VALET_CONFIGOWNER"."KEYSTORE" TO "VALET_CONF";
GRANT SELECT ON "VALET_CONFIGOWNER"."KEYSTORE" TO "VALET_CONF";
GRANT UPDATE ON "VALET_CONFIGOWNER"."KEYSTORE" TO "VALET_CONF";
GRANT DELETE ON "VALET_CONFIGOWNER"."KEYSTORE" TO "VALET_CONF";

GRANT INSERT ON "VALET_CONFIGOWNER"."SYSTEM_CERTIFICATE" TO "VALET_CONF";
GRANT SELECT ON "VALET_CONFIGOWNER"."SYSTEM_CERTIFICATE" TO "VALET_CONF";
GRANT UPDATE ON "VALET_CONFIGOWNER"."SYSTEM_CERTIFICATE" TO "VALET_CONF";
GRANT DELETE ON "VALET_CONFIGOWNER"."SYSTEM_CERTIFICATE" TO "VALET_CONF";
GRANT SELECT,ALTER ON "VALET_CONFIGOWNER"."SQ_SYSTEM_CERTIFICATE" TO "VALET_CONF";

GRANT SELECT ON "VALET_CONFIGOWNER"."C_STATUS_CERTIFICATES" TO "VALET_CONF";



GRANT CREATE SYNONYM TO "VALET_CONF";
GRANT "CONNECT" TO "VALET_CONF";

COMMIT;