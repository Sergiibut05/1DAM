/*Supongamos que queremos evitar que se eliminen tablas 
en la base de datos:*/

USE usuarios;

CREATE TRIGGER trg_PreventDropTable
ON DATABASE
FOR DROP_TABLE
AS
BEGIN
    PRINT '�No est� permitido eliminar tablas en esta base de datos!';
    ROLLBACK TRANSACTION;
END;

--Comprobaci�n:
DROP TABLE usuario;

SELECT * FROM usuario;

/*Supongamos que queremos registrar en una tabla todas las
 creaciones, modificaciones de estructura o borrados de tablas.*/

CREATE TABLE AuditoriaDDL
  ( ID INT IDENTITY(1,1) PRIMARY KEY,
    Evento XML,
    Fecha DATETIME DEFAULT GETDATE()
  );

CREATE TRIGGER trg_AuditDDL
ON DATABASE
FOR CREATE_TABLE, ALTER_TABLE, DROP_TABLE
AS
BEGIN
    INSERT INTO AuditoriaDDL (Evento)
    VALUES (EVENTDATA());
END;

--Comprobaci�n:
ALTER TABLE usuario
ADD Edad DATE NULL;

SELECT * FROM AuditoriaDDL;


