-- ************************************************************
-- *** Creación de los valores en las tablas de constantes  ***
-- ************************************************************

-- TABLA C_ASSOCIATION_TYPE 
Insert into C_ASSOCIATION_TYPE
   (ID_ASSOCIATION_TYPE, TOKEN_NAME)
 Values
   (0, 'ASSOCIATION_TYPE01');
Insert into C_ASSOCIATION_TYPE
   (ID_ASSOCIATION_TYPE, TOKEN_NAME)
 Values
   (4, 'ASSOCIATION_TYPE02');


-- TABLA C_OPERATION_MODE
Insert into C_OPERATION_MODE
   (ID_OPERATION_MODE, OPERATION_MODE)
 Values
   (1, 'NONE');
Insert into C_OPERATION_MODE
   (ID_OPERATION_MODE, OPERATION_MODE)
 Values
   (2, 'NONE_AUTHENTICATION');
Insert into C_OPERATION_MODE
   (ID_OPERATION_MODE, OPERATION_MODE)
 Values
   (3, 'BASIC_AUTHENTICATION');
Insert into C_OPERATION_MODE
   (ID_OPERATION_MODE, OPERATION_MODE)
 Values
   (4, 'NTLM_AUTHENTICATION');


-- TABLA C_PLANNER_TYPE 
Insert into C_PLANNER_TYPE
   (ID_PLANNER_TYPE, TOKEN_NAME)
 Values
   (0, 'PLANNER_TYPE00');
Insert into C_PLANNER_TYPE
   (ID_PLANNER_TYPE, TOKEN_NAME)
 Values
   (1, 'PLANNER_TYPE01');
Insert into C_PLANNER_TYPE
   (ID_PLANNER_TYPE, TOKEN_NAME)
 Values
   (2, 'PLANNER_TYPE02');


--  TABLA C_STATUS_CERTIFICATES 
Insert into C_STATUS_CERTIFICATES
   (ID_STATUS_CERTIFICATE, TOKEN_NAME)
 Values
   (0, 'STATUS_CERTIFICATE00');
Insert into C_STATUS_CERTIFICATES
   (ID_STATUS_CERTIFICATE, TOKEN_NAME)
 Values
   (1, 'STATUS_CERTIFICATE01');
Insert into C_STATUS_CERTIFICATES
   (ID_STATUS_CERTIFICATE, TOKEN_NAME)
 Values
   (2, 'STATUS_CERTIFICATE02');
Insert into C_STATUS_CERTIFICATES
   (ID_STATUS_CERTIFICATE, TOKEN_NAME)
 Values
   (3, 'STATUS_CERTIFICATE03');
Insert into C_STATUS_CERTIFICATES
   (ID_STATUS_CERTIFICATE, TOKEN_NAME)
 Values
   (4, 'STATUS_CERTIFICATE04');
Insert into C_STATUS_CERTIFICATES
   (ID_STATUS_CERTIFICATE, TOKEN_NAME)
 Values
   (5, 'STATUS_CERTIFICATE05');


-- TABLA C_TSL_IMPL
Insert into C_TSL_IMPL
   (ID_TSL_IMPL, SPECIFICATION, VERSION, NAMESPACE)
 Values
   (3, '119612', '2.1.1', 'http://uri.etsi.org/02231/v2#');


-- TABLA USER_VALET 
Insert into USER_VALET
   (ID_USER_VALET, LOGIN, PASSWORD, NAME, SURNAMES, 
    EMAIL, IS_BLOCKED, ATTEMPTS_NUMBER, NIF)
 Values
   (1, 'admin', '$2a$04$THxVTEMMIVxHUxwjm9gKjetbxvPX/H9r54HtukxBTMcAXSrgDzCGK', 'admin', 'admin', 'admin@admin.com', 'N', 0, '12345678Z');

-- TABLA KEYSTORE
Insert into KEYSTORE
   (ID_KEYSTORE, NAME, TOKEN_NAME, KEYSTORE, PASSWORD, IS_HARDWARE, KEYSTORE_TYPE, VERSION, HAS_BACKUP)
 Values (17,'TrustStoreTSL', 'KEYSTORE17', 'CECECECE00000002000000002F03597B85569C665FE07C832801E57EFF4A872C', 'e7bwCEbPPU0D4UHC83ZKCVeJb6zc', 'N', 'JCEKS', 0, 'N');
Insert into KEYSTORE
(ID_KEYSTORE, NAME, TOKEN_NAME, KEYSTORE, PASSWORD, IS_HARDWARE, KEYSTORE_TYPE, VERSION, HAS_BACKUP)
Values (18,'TrustStoreCA', 'KEYSTORE18', 'CECECECE00000002000000002F03597B85569C665FE07C832801E57EFF4A872C', 'e7bwCEbPPU0D4UHC83ZKCVeJb6zc', 'N', 'JCEKS', 0, 'N');
Insert into KEYSTORE
(ID_KEYSTORE, NAME, TOKEN_NAME, KEYSTORE, PASSWORD, IS_HARDWARE, KEYSTORE_TYPE, VERSION, HAS_BACKUP)
Values (19,'TrustStoreOCSP', 'KEYSTORE19', 'CECECECE00000002000000002F03597B85569C665FE07C832801E57EFF4A872C', 'e7bwCEbPPU0D4UHC83ZKCVeJb6zc', 'N', 'JCEKS', 0, 'N');

-- TABLA ALARM

Insert into ALARM (ID_ALARM, DESCRIPTION, TIME_BLOCK, ACTIVE)
  Values ('ALM001', 'ALARMDESC001', null, 'Y');
Insert into ALARM (ID_ALARM, DESCRIPTION, TIME_BLOCK, ACTIVE)
  Values ('ALM002', 'ALARMDESC002', null, 'Y');
Insert into ALARM (ID_ALARM, DESCRIPTION, TIME_BLOCK, ACTIVE)
  Values ('ALM003', 'ALARMDESC003', null, 'Y'); 
Insert into ALARM (ID_ALARM, DESCRIPTION, TIME_BLOCK, ACTIVE)
  Values ('ALM004', 'ALARMDESC004', null, 'Y');
Insert into ALARM (ID_ALARM, DESCRIPTION, TIME_BLOCK, ACTIVE)
  Values ('ALM005', 'ALARMDESC005', null, 'Y');
  Insert into ALARM (ID_ALARM, DESCRIPTION, TIME_BLOCK, ACTIVE)
  Values ('ALM006', 'ALARMDESC006', null, 'Y');
  Insert into ALARM (ID_ALARM, DESCRIPTION, TIME_BLOCK, ACTIVE)
  Values ('ALM007', 'ALARMDESC007', null, 'Y');
  Insert into ALARM (ID_ALARM, DESCRIPTION, TIME_BLOCK, ACTIVE)
  Values ('ALM008', 'ALARMDESC008', null, 'Y');
  Insert into ALARM (ID_ALARM, DESCRIPTION, TIME_BLOCK, ACTIVE)
  Values ('ALM009', 'ALARMDESC009', null, 'Y');
  Insert into ALARM (ID_ALARM, DESCRIPTION, TIME_BLOCK, ACTIVE)
  Values ('ALM010', 'ALARMDESC010', null, 'Y');
  Insert into ALARM (ID_ALARM, DESCRIPTION, TIME_BLOCK, ACTIVE)
  Values ('ALM011', 'ALARMDESC011', null, 'Y');
  
-- TABLA TASK 
INSERT INTO TASK (ID_TASK, TOKEN_NAME, IMPLEMENTATION_CLASS, IS_ENABLED) VALUES (1, 'TASK01', 'es.gob.valet.tasks.FindNewTSLRevisionsTask', 'N');
INSERT INTO TASK (ID_TASK, TOKEN_NAME, IMPLEMENTATION_CLASS, IS_ENABLED) VALUES (2, 'TASK02', 'es.gob.valet.tasks.ExternalAccessConnectionTestTask', 'N');

-- TABLA PLANNER
  Insert into PLANNER
   (ID_PLANNER, HOUR_PERIOD, MINUTE_PERIOD, SECOND_PERIOD, INIT_DAY, 
    ID_PLANNER_TYPE)
 Values
   (1, 23, 0, 0, TO_TIMESTAMP('01/01/2012 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1);
  Insert into PLANNER
   (ID_PLANNER, HOUR_PERIOD, MINUTE_PERIOD, SECOND_PERIOD, INIT_DAY, 
    ID_PLANNER_TYPE)
 Values
   (2, 23, 0, 0, TO_TIMESTAMP('01/01/2012 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1);


 -- TABLA X_TASK_PLANNER 
INSERT INTO X_TASK_PLANNER (ID_TASK, ID_PLANNER) Values (1, 1);
INSERT INTO X_TASK_PLANNER (ID_TASK, ID_PLANNER) Values (2, 2);


-- TABLA PROXY
Insert into PROXY
   (ID_PROXY, ID_OPERATION_MODE, HOST_PROXY, PORT_PROXY, USER_PROXY, PASSWORD_PROXY, USER_DOMAIN, ADDRESS_LIST, IS_LOCAL_ADDRESS)
 Values
   (1, 1, '', NULL, '', '', '', '', 'N');

COMMIT;