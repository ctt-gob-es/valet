-- Secuencia para las claves primarias de las tablas del Modelo Datawarehouse de ValET.

--DROP SEQUENCE TRANSACTIONPK_SEQ;
--DROP SEQUENCE APPLICATIONPK_SEQ;
--DROP SEQUENCE DATEPK_SEQ;
--DROP SEQUENCE NODEPK_SEQ;
--DROP SEQUENCE VALIDATIONPK_SEQ;

-- Secuencia para la clave primaria de la tabla FCT_TRANSACTIONS
CREATE SEQUENCE TRANSACTIONPK_SEQ
  START WITH 1
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  ORDER;
  
-- Secuencia para la clave primaria de la tabla DIM_APPLICATIONS
CREATE SEQUENCE APPLICATIONPK_SEQ
  START WITH 1
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  ORDER;
  
  -- Secuencia para la clave primaria de la tabla DIM_DATES
CREATE SEQUENCE DATEPK_SEQ
  START WITH 1
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  ORDER;
  
  
-- Secuencia para la clave primaria de la tabla DIM_NODES
CREATE SEQUENCE NODEPK_SEQ
  START WITH 1
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  ORDER;

  -- Secuencia para la clave primaria de la tabla FCT_VALIDATIONS
CREATE SEQUENCE VALIDATIONPK_SEQ
  START WITH 1
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  ORDER;
  
COMMIT;

--EXIT;
