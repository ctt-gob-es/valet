-- ************************************************************
-- *** Creación de los valores en las tablas de constantes  ***
-- ************************************************************
   
-- TABLA USER_VALET 
   
Insert into USER_VALET
   (ID_USER_VALET, LOGIN, PASSWORD, NAME, SURNAMES, 
    EMAIL, IS_BLOCKED, ATTEMPTS_NUMBER)
 Values
   (1, 'admin', 'jLIjfQZ5yojbZGTqxg2pY0VROWQ=', 'admin', 'admin', 'admin@admin.com', 'N', 0);