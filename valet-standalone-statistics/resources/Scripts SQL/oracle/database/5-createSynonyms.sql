CREATE OR REPLACE SYNONYM "FCT_TRANSACTIONS" FOR "VALET_STATISTICSDWOWNER"."FCT_TRANSACTIONS";
CREATE OR REPLACE SYNONYM "DIM_DATES" FOR "VALET_STATISTICSDWOWNER"."DIM_DATES";
CREATE OR REPLACE SYNONYM "C_SERVICETYPES" FOR "VALET_STATISTICSDWOWNER"."C_SERVICETYPES";
CREATE OR REPLACE SYNONYM "DIM_APPLICATIONS" FOR "VALET_STATISTICSDWOWNER"."DIM_APPLICATIONS";
CREATE OR REPLACE SYNONYM "C_CODRESULTS" FOR "VALET_STATISTICSDWOWNER"."C_CODRESULTS";
CREATE OR REPLACE SYNONYM "DIM_NODES" FOR "VALET_STATISTICSDWOWNER"."DIM_NODES";
CREATE OR REPLACE SYNONYM "FCT_VALIDATIONS" FOR "VALET_STATISTICSDWOWNER"."FCT_VALIDATIONS";

--secuencias--
CREATE OR REPLACE SYNONYM "TRANSACTIONPK_SEQ" FOR "VALET_STATISTICSDWOWNER"."TRANSACTIONPK_SEQ";
CREATE OR REPLACE SYNONYM "APPLICATIONPK_SEQ" FOR "VALET_STATISTICSDWOWNER"."APPLICATIONPK_SEQ";
CREATE OR REPLACE SYNONYM "DATEPK_SEQ" FOR "VALET_STATISTICSDWOWNER"."DATEPK_SEQ";
CREATE OR REPLACE SYNONYM "NODEPK_SEQ" FOR "VALET_STATISTICSDWOWNER"."NODEPK_SEQ";
CREATE OR REPLACE SYNONYM "VALIDATIONPK_SEQ" FOR "VALET_STATISTICSDWOWNER"."VALIDATIONPK_SEQ";

COMMIT;
