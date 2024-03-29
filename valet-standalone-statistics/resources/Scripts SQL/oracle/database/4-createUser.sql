-- Usuario de acceso de la aplicacion
DROP USER "VALET_STATISTICSDW" CASCADE;

CREATE USER "VALET_STATISTICSDW"  PROFILE "DEFAULT" 
    IDENTIFIED BY "XXXXX" DEFAULT TABLESPACE "VALET_STATISTICS_DW_TS" 
        ACCOUNT UNLOCK;
        
                
        
-- Asignacion de permisos para usuario VALET_STATISTICSDW
GRANT "CONNECT" TO "VALET_STATISTICSDW";

GRANT CREATE SYNONYM TO "VALET_STATISTICSDW";

GRANT SELECT ON "VALET_STATISTICSDWOWNER"."FCT_TRANSACTIONS" TO "VALET_STATISTICSDW";
GRANT INSERT ON "VALET_STATISTICSDWOWNER"."FCT_TRANSACTIONS" TO "VALET_STATISTICSDW";
GRANT UPDATE ON "VALET_STATISTICSDWOWNER"."FCT_TRANSACTIONS" TO "VALET_STATISTICSDW";
GRANT DELETE ON "VALET_STATISTICSDWOWNER"."FCT_TRANSACTIONS" TO "VALET_STATISTICSDW";

GRANT SELECT ON "VALET_STATISTICSDWOWNER"."DIM_DATES" TO "VALET_STATISTICSDW";
GRANT INSERT ON "VALET_STATISTICSDWOWNER"."DIM_DATES" TO "VALET_STATISTICSDW";
GRANT UPDATE ON "VALET_STATISTICSDWOWNER"."DIM_DATES" TO "VALET_STATISTICSDW";
GRANT DELETE ON "VALET_STATISTICSDWOWNER"."DIM_DATES" TO "VALET_STATISTICSDW";

GRANT SELECT ON "VALET_STATISTICSDWOWNER"."C_SERVICETYPES" TO "VALET_STATISTICSDW";
GRANT INSERT ON "VALET_STATISTICSDWOWNER"."C_SERVICETYPES" TO "VALET_STATISTICSDW";
GRANT UPDATE ON "VALET_STATISTICSDWOWNER"."C_SERVICETYPES" TO "VALET_STATISTICSDW";
GRANT DELETE ON "VALET_STATISTICSDWOWNER"."C_SERVICETYPES" TO "VALET_STATISTICSDW";

GRANT SELECT ON "VALET_STATISTICSDWOWNER"."DIM_APPLICATIONS" TO "VALET_STATISTICSDW";
GRANT INSERT ON "VALET_STATISTICSDWOWNER"."DIM_APPLICATIONS" TO "VALET_STATISTICSDW";
GRANT UPDATE ON "VALET_STATISTICSDWOWNER"."DIM_APPLICATIONS" TO "VALET_STATISTICSDW";
GRANT DELETE ON "VALET_STATISTICSDWOWNER"."DIM_APPLICATIONS" TO "VALET_STATISTICSDW";

GRANT SELECT ON "VALET_STATISTICSDWOWNER"."C_CODRESULTS" TO "VALET_STATISTICSDW";
GRANT INSERT ON "VALET_STATISTICSDWOWNER"."C_CODRESULTS" TO "VALET_STATISTICSDW";
GRANT UPDATE ON "VALET_STATISTICSDWOWNER"."C_CODRESULTS" TO "VALET_STATISTICSDW";
GRANT DELETE ON "VALET_STATISTICSDWOWNER"."C_CODRESULTS" TO "VALET_STATISTICSDW";

GRANT SELECT ON "VALET_STATISTICSDWOWNER"."DIM_NODES" TO "VALET_STATISTICSDW";
GRANT INSERT ON "VALET_STATISTICSDWOWNER"."DIM_NODES" TO "VALET_STATISTICSDW";
GRANT UPDATE ON "VALET_STATISTICSDWOWNER"."DIM_NODES" TO "VALET_STATISTICSDW";
GRANT DELETE ON "VALET_STATISTICSDWOWNER"."DIM_NODES" TO "VALET_STATISTICSDW";


GRANT SELECT ON "VALET_STATISTICSDWOWNER"."FCT_VALIDATIONS" TO "VALET_STATISTICSDW";
GRANT INSERT ON "VALET_STATISTICSDWOWNER"."FCT_VALIDATIONS" TO "VALET_STATISTICSDW";
GRANT UPDATE ON "VALET_STATISTICSDWOWNER"."FCT_VALIDATIONS" TO "VALET_STATISTICSDW";
GRANT DELETE ON "VALET_STATISTICSDWOWNER"."FCT_VALIDATIONS" TO "VALET_STATISTICSDW";

GRANT SELECT,ALTER ON  "VALET_STATISTICSDWOWNER"."TRANSACTIONPK_SEQ" TO "VALET_STATISTICSDW";
GRANT SELECT,ALTER ON  "VALET_STATISTICSDWOWNER"."APPLICATIONPK_SEQ" TO "VALET_STATISTICSDW";
GRANT SELECT,ALTER ON  "VALET_STATISTICSDWOWNER"."DATEPK_SEQ" TO "VALET_STATISTICSDW";
GRANT SELECT,ALTER ON  "VALET_STATISTICSDWOWNER"."NODEPK_SEQ" TO "VALET_STATISTICSDW";
GRANT SELECT,ALTER ON  "VALET_STATISTICSDWOWNER"."VALIDATIONPK_SEQ" TO "VALET_STATISTICSDW";


COMMIT;

--EXIT;