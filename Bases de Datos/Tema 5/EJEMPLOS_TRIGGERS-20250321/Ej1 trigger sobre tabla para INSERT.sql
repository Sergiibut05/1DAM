/*Queremos controlar que el nombre de un curso no esté duplicado en la
  tabla curso. La tabla curso no tiene la restricción UNIQUE en el campo
  NomCurso. Además, no tenemos permiso para modificar la estructura de 
  las tablas para poder añadirle esta restricción.*/

/*Trigger que controla que no se den de alta dos cursos con el mismo nombre.*/

USE formacion;

SELECT * FROM curso;

CREATE OR ALTER TRIGGER trig1
ON curso
FOR INSERT
AS
BEGIN

DECLARE @CODCURSO INT

SELECT @CODCURSO=CodCurso FROM inserted

/*PRINT @valor
SELECT * FROM inserted*/

IF (SELECT COUNT(*)
    FROM curso c, inserted i 
    WHERE c.NomCurso=i.NomCurso)>1
	BEGIN
		DELETE curso WHERE CodCurso=@CODCURSO
		PRINT 'No se puede insertar el curso porque el nombre ya existe'
	END
END;

--Comprobaciones:
INSERT INTO curso
VALUES (100,'Curso1',30,'20/04/2024');

INSERT INTO curso
VALUES (101,'Curso2',30,'20/04/2024');

INSERT INTO curso
VALUES (102,'Curso1',30,'20/04/2024');

SELECT * FROM curso;

--Vamos a hacer el mismo trigger, pero de otra manera.

--Borrar un Trigger:
DROP TRIGGER trig1;

CREATE OR ALTER TRIGGER trig1
ON curso
FOR INSERT
AS
BEGIN

DECLARE @CODCURSO INT, @NOMCURSO VARCHAR(50)

SELECT @CODCURSO=CodCurso, @NOMCURSO=NomCurso FROM inserted

IF (SELECT COUNT(*)
    FROM curso 
    WHERE NomCurso=@NOMCURSO)>1
	BEGIN
		DELETE curso WHERE CodCurso=@CODCURSO
		PRINT 'No se puede insertar el curso porque el nombre ya existe'
	END
END;

--Vaciamos la tabla:
SELECT * FROM curso;

DELETE FROM curso;

--Comprobaciones:
INSERT INTO curso
VALUES (100,'Curso1',30,'20/04/2024');

INSERT INTO curso
VALUES (101,'Curso2',30,'20/04/2024');

INSERT INTO curso
VALUES (102,'Curso1',30,'20/04/2024');

SELECT * FROM curso;
