-- **********************************************************************
-- ************* Creación del Usuario de Configuración ******************
-- **********************************************************************

CREATE USER "VALET_CONF"  PROFILE "DEFAULT" 
    IDENTIFIED BY "PASSWORD" DEFAULT TABLESPACE "VALET_CONFIGURACION_TABLESPACE" 
        ACCOUNT UNLOCK;

GRANT SELECT ON "VALET_CONFIGOWNER"."C_ASSOCIATION_TYPE" TO "VALET_CONF";
GRANT SELECT ON "VALET_CONFIGOWNER"."C_OPERATION_MODE" TO "VALET_CONF";
GRANT SELECT ON "VALET_CONFIGOWNER"."C_PLANNER_TYPE" TO "VALET_CONF";
GRANT SELECT ON "VALET_CONFIGOWNER"."C_STATUS_CERTIFICATES" TO "VALET_CONF";
GRANT SELECT ON "VALET_CONFIGOWNER"."C_TSL_IMPL" TO "VALET_CONF";
        
GRANT INSERT ON "VALET_CONFIGOWNER"."USER_VALET" TO "VALET_CONF";
GRANT SELECT ON "VALET_CONFIGOWNER"."USER_VALET" TO "VALET_CONF";
GRANT UPDATE ON "VALET_CONFIGOWNER"."USER_VALET" TO "VALET_CONF";
GRANT DELETE ON "VALET_CONFIGOWNER"."USER_VALET" TO "VALET_CONF";
GRANT SELECT,ALTER ON "VALET_CONFIGOWNER"."SQ_USER_VALET" TO "VALET_CONF";

GRANT INSERT ON "VALET_CONFIGOWNER"."TSL_DATA" TO "VALET_CONF";
GRANT SELECT ON "VALET_CONFIGOWNER"."TSL_DATA" TO "VALET_CONF";
GRANT UPDATE ON "VALET_CONFIGOWNER"."TSL_DATA" TO "VALET_CONF";
GRANT DELETE ON "VALET_CONFIGOWNER"."TSL_DATA" TO "VALET_CONF";
GRANT SELECT,ALTER ON "VALET_CONFIGOWNER"."SQ_TSL_DATA" TO "VALET_CONF";

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

GRANT INSERT ON "VALET_CONFIGOWNER"."KEYSTORE" TO "VALET_CONF";
GRANT SELECT ON "VALET_CONFIGOWNER"."KEYSTORE" TO "VALET_CONF";
GRANT UPDATE ON "VALET_CONFIGOWNER"."KEYSTORE" TO "VALET_CONF";
GRANT DELETE ON "VALET_CONFIGOWNER"."KEYSTORE" TO "VALET_CONF";

GRANT INSERT ON "VALET_CONFIGOWNER"."SYSTEM_CERTIFICATE" TO "VALET_CONF";
GRANT SELECT ON "VALET_CONFIGOWNER"."SYSTEM_CERTIFICATE" TO "VALET_CONF";
GRANT UPDATE ON "VALET_CONFIGOWNER"."SYSTEM_CERTIFICATE" TO "VALET_CONF";
GRANT DELETE ON "VALET_CONFIGOWNER"."SYSTEM_CERTIFICATE" TO "VALET_CONF";
GRANT SELECT,ALTER ON "VALET_CONFIGOWNER"."SQ_SYSTEM_CERTIFICATE" TO "VALET_CONF";

GRANT INSERT ON "VALET_CONFIGOWNER"."MAIL" TO "VALET_CONF";
GRANT SELECT ON "VALET_CONFIGOWNER"."MAIL" TO "VALET_CONF";
GRANT UPDATE ON "VALET_CONFIGOWNER"."MAIL" TO "VALET_CONF";
GRANT DELETE ON "VALET_CONFIGOWNER"."MAIL" TO "VALET_CONF";
GRANT SELECT,ALTER ON "VALET_CONFIGOWNER"."SQ_MAIL" TO "VALET_CONF";

GRANT SELECT ON "VALET_CONFIGOWNER"."ALARM" TO "VALET_CONF";
GRANT UPDATE ON "VALET_CONFIGOWNER"."ALARM" TO "VALET_CONF";

GRANT INSERT ON "VALET_CONFIGOWNER"."R_ALARM_MAIL" TO "VALET_CONF";
GRANT SELECT ON "VALET_CONFIGOWNER"."R_ALARM_MAIL" TO "VALET_CONF";
GRANT UPDATE ON "VALET_CONFIGOWNER"."R_ALARM_MAIL" TO "VALET_CONF";
GRANT DELETE ON "VALET_CONFIGOWNER"."R_ALARM_MAIL" TO "VALET_CONF";

GRANT INSERT ON "VALET_CONFIGOWNER"."CONF_SERVER_MAIL" TO "VALET_CONF";
GRANT SELECT ON "VALET_CONFIGOWNER"."CONF_SERVER_MAIL" TO "VALET_CONF";
GRANT UPDATE ON "VALET_CONFIGOWNER"."CONF_SERVER_MAIL" TO "VALET_CONF";
GRANT DELETE ON "VALET_CONFIGOWNER"."CONF_SERVER_MAIL" TO "VALET_CONF";
GRANT SELECT,ALTER ON "VALET_CONFIGOWNER"."SQ_CONF_SERVER_MAIL" TO "VALET_CONF";

GRANT INSERT ON "VALET_CONFIGOWNER"."TASK" TO "VALET_CONF";
GRANT SELECT ON "VALET_CONFIGOWNER"."TASK" TO "VALET_CONF";
GRANT UPDATE ON "VALET_CONFIGOWNER"."TASK" TO "VALET_CONF";
GRANT DELETE ON "VALET_CONFIGOWNER"."TASK" TO "VALET_CONF";
GRANT SELECT,ALTER ON "VALET_CONFIGOWNER"."SQ_TASK" TO "VALET_CONF";

GRANT INSERT ON "VALET_CONFIGOWNER"."PLANNER" TO "VALET_CONF";
GRANT SELECT ON "VALET_CONFIGOWNER"."PLANNER" TO "VALET_CONF";
GRANT UPDATE ON "VALET_CONFIGOWNER"."PLANNER" TO "VALET_CONF";
GRANT DELETE ON "VALET_CONFIGOWNER"."PLANNER" TO "VALET_CONF";
GRANT SELECT,ALTER ON "VALET_CONFIGOWNER"."SQ_PLANNER" TO "VALET_CONF";

GRANT INSERT ON "VALET_CONFIGOWNER"."X_TASK_PLANNER" TO "VALET_CONF";
GRANT SELECT ON "VALET_CONFIGOWNER"."X_TASK_PLANNER" TO "VALET_CONF";
GRANT UPDATE ON "VALET_CONFIGOWNER"."X_TASK_PLANNER" TO "VALET_CONF";
GRANT DELETE ON "VALET_CONFIGOWNER"."X_TASK_PLANNER" TO "VALET_CONF";

GRANT SELECT ON "VALET_CONFIGOWNER"."PROXY" TO "VALET_CONF";
GRANT UPDATE ON "VALET_CONFIGOWNER"."PROXY" TO "VALET_CONF";

GRANT INSERT ON "VALET_CONFIGOWNER"."APPLICATION" TO "VALET_CONF";
GRANT SELECT ON "VALET_CONFIGOWNER"."APPLICATION" TO "VALET_CONF";
GRANT UPDATE ON "VALET_CONFIGOWNER"."APPLICATION" TO "VALET_CONF";
GRANT DELETE ON "VALET_CONFIGOWNER"."APPLICATION" TO "VALET_CONF";
GRANT SELECT,ALTER ON "VALET_CONFIGOWNER"."SQ_APPLICATION" TO "VALET_CONF";

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

GRANT CREATE SYNONYM TO "VALET_CONF";
GRANT "CONNECT" TO "VALET_CONF";

COMMIT;
