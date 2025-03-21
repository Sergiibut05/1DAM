/*Queremos controlar la Regla de Integridad Referencial, 
  de manera que no pueda existir un trabajador en un departamento 
  que no exista. El trabajador puede tener el campo CodDepto a NULL,
  pero si tiene un valor en dicho campo, este valor debe existir en
  la tabla departamento. De esta manera, no existir� un valor
  de clave for�nea sin concordancia. Adem�s, no tenemos permiso
  para modificar la estructura de las tablas para poder a�adirle
  esta restricci�n.*/
    
/*Trigger que controla que cuando se introduzca un c�digo de departamento
  en la tabla trabajador (bien por a�adir una fila, o bien por modificar el
  valor de CodDepto), este c�digo exista en la tabla departamento o bien
  este sea nulo.*/

USE formacion;

--Deshaciendo a transacci�n
CREATE OR ALTER TRIGGER trig4
ON trabajador
FOR INSERT, UPDATE
AS
BEGIN

DECLARE @CODDEPTO INT

SELECT @CODDEPTO=CodDepto FROM inserted

IF @CODDEPTO IS NOT NULL 
	BEGIN
		IF (SELECT COUNT(*)
			FROM departamento
			WHERE CodDepto = @CODDEPTO) = 0 
			BEGIN
				ROLLBACK TRANSACTION 
				PRINT 'El c�digo que intent� introducir no se corresponde con ning�n departamento'
			END
	END
END;    

--Comprobaciones con UPDATE:
SELECT * FROM departamento;
SELECT * FROM trabajador;

--Departamento que existe.
UPDATE trabajador
SET CodDepto=3
WHERE CodTrab=2;

--Quitamos el departamento al trabjador 2.
UPDATE trabajador
SET CodDepto=NULL
WHERE CodTrab=2;

--Departamento que no existe.
UPDATE trabajador
SET CodDepto=1000
WHERE CodTrab=3;

--Comprobaciones con INSERT:
SELECT * FROM departamento;
SELECT * FROM trabajador;

--Departamento que existe.
INSERT INTO trabajador
VALUES (4,'ROCIO','G�LVEZ ROMERO','12/09/1990',2000,100,3);

--Trabajador sin departamento.
INSERT INTO trabajador
VALUES (5, 'ROBERTO','GONZ�LEZ MORALES','19/10/1999',2000,100,NULL);

--Departamento que no existe.
INSERT INTO trabajador
VALUES (6, 'DAVID','L�PEZ ROMERO','18/03/1998',2000,100,1000);


/* Vamos a hacer el mismo trigger, pero sin deshacer la transacci�n.
  -Si el valor incorrecto en la tabla trabajador lo provoc� un UPDATE,
   hacemos de nuevo un UPDATE para dejar la tabla como estaba antes de 
   ejecutar la instrucci�n.
  -Si el valor incorrecto en la tabla trabajador lo provoc� un INSERT,
   hacemos un DELETE para dejar la tabla como estaba antes de 
   ejecutar la instrucci�n.
*/

--Borramos el trigger:
DROP TRIGGER trig4;

CREATE TRIGGER trig4
ON trabajador
FOR INSERT, UPDATE
AS
BEGIN

DECLARE @CODDEPTO_NUEVO INT , @CODDEPTO_ANTIGUO INT, @CODTRAB INT, @FILAS INT

SELECT @CODDEPTO_NUEVO=CodDepto, @CODTRAB=CodTrab FROM inserted

SELECT @CODDEPTO_ANTIGUO=CodDepto FROM deleted

IF @CODDEPTO_NUEVO IS NOT NULL 
 BEGIN
	IF (SELECT COUNT(*)
        FROM departamento
        WHERE CodDepto = @CODDEPTO_NUEVO) = 0 
	    BEGIN
			PRINT 'El c�digo que intent� introducir no se corresponde con ning�n departamento'
			SELECT @FILAS = COUNT(*) FROM deleted
			--PRINT @FILAS
			IF (@FILAS)=0 --Ha sido un INSERT 
			  BEGIN
				DELETE FROM trabajador WHERE CodTrab=@CODTRAB
			  END    
			ELSE --Ha sido un UPDATE
			  BEGIN
				--PRINT @CODDEPTO_ANTIGUO
				UPDATE trabajador
				SET CodDepto=@CODDEPTO_ANTIGUO
                WHERE CodTrab=@CODTRAB    	      
			  END
	    END
END
END;   

--Comprobaciones con UPDATE:
SELECT * FROM departamento;
SELECT * FROM trabajador;

--Departamento que existe.
UPDATE trabajador
SET CodDepto=3
WHERE CodTrab=2;

--Quitamos el departamento al trabjador 2.
UPDATE trabajador
SET CodDepto=NULL
WHERE CodTrab=2;

--Departamento que no existe.
UPDATE trabajador
SET CodDepto=1000
WHERE CodTrab=3;

--Comprobaciones con INSERT:
SELECT * FROM departamento;
SELECT * FROM trabajador;

--Departamento que existe.
INSERT INTO trabajador
VALUES (6,'ALBA','G�LVEZ ROMERO','12/09/1990',2000,100,3);

--Trabajador sin departamento.
INSERT INTO trabajador
VALUES (7, 'DARIO','GONZ�LEZ MORALES','19/10/1999',2000,100,NULL);

--Departamento que no existe.
INSERT INTO trabajador
VALUES (8, 'ROSA','L�PEZ ROMERO','18/03/1998',2000,100,1000);

