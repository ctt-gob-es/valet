-- ************************************************************
-- *** Creación de los valores en las tablas de constantes  ***
-- ************************************************************
   
-- TABLA USER_VALET 
   
Insert into USER_VALET
   (ID_USER_VALET, LOGIN, PASSWORD, NAME, SURNAMES, 
    EMAIL, IS_BLOCKED, ATTEMPTS_NUMBER)
 Values
   (1, 'admin', '$2a$04$THxVTEMMIVxHUxwjm9gKjetbxvPX/H9r54HtukxBTMcAXSrgDzCGK', 'admin', 'admin', 'admin@admin.com', 'N', 0);
   
-- TABLA KEYSTORE
   
Insert into KEYSTORE
   (ID_KEYSTORE, NAME, TOKEN_NAME, KEYSTORE, PASSWORD, IS_HARDWARE, KEYSTORE_TYPE, VERSION, HAS_BACKUP)
 Values (1,'AlmacenConfianzaSSL', 'KEYSTORE01', 'CECECECE00000002000000002F03597B85569C665FE07C832801E57EFF4A872C', 'UH2rpWNDX+RLltMuyFPirQ==', 'N', 'JCEKS', 0, 'N');
   
Insert into KEYSTORE
   (ID_KEYSTORE, NAME, TOKEN_NAME, KEYSTORE, PASSWORD, IS_HARDWARE, KEYSTORE_TYPE, VERSION, HAS_BACKUP)
 Values (2,'TruststoreTSL', 'KEYSTORE02', 'CECECECE00000002000000002F03597B85569C665FE07C832801E57EFF4A872C', 'UH2rpWNDX+RLltMuyFPirQ==', 'N', 'JCEKS', 0, 'N');

 -- TABLA ALARM_DEFECT
 
Insert into ALARM (ID_ALARM, DESCRIPTION)
  Values ('ALM001', 'Alarma 1');
  
Insert into ALARM (ID_ALARM, DESCRIPTION)
  Values ('ALM002', 'Alarma 2');
  
Insert into ALARM (ID_ALARM, DESCRIPTION)
  Values ('ALM003', 'Alarma 3');
  
Insert into ALARM (ID_ALARM, DESCRIPTION)
  Values ('ALM004', 'Alarma 4');