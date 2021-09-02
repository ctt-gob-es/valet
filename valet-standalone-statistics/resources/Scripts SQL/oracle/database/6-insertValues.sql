-- Valores iniciales del modelo --

-- Tipos de Servicios -- 
INSERT INTO C_SERVICETYPES VALUES (1, 'getTslInformation');
INSERT INTO C_SERVICETYPES VALUES (2, 'detectCertInTslInfoAndValidation');
INSERT INTO C_SERVICETYPES VALUES (3, 'getTslInfoVersions');

-- Código Resultados -- 
INSERT INTO C_CODRESULTS Values (0,'OK');
INSERT INTO C_CODRESULTS Values (1,'ERROR');
INSERT INTO C_CODRESULTS Values (2,'UNDEFINED');