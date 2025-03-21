/*Queremos controlar la Regla de Integridad Referencial para
  cuando modificamos un código de departamento en la tabla 
  departamento, de manera que se modifique en CASCADA en el
  campo CodDepto en la tabla trabajador. Además, no 
  tenemos permiso para modificar la estructura de las tablas
  para poder añadirle esta restricción.*/

/*Trigger que controla que cuando modifiquemos el código de 
  departamento en la tabla departamento, se modifique en 
  CASCADA en la tabla de trabajador.*/
USE formacion;

CREATE TRIGGER trig3
ON departamento
FOR UPDATE
AS
BEGIN
DECLARE @VANTIGUO INT
DECLARE @VNUEVO INT

SELECT @VANTIGUO=CodDepto FROM deleted
--PRINT @VANTIGUO

SELECT @VNUEVO=CodDepto FROM inserted
--PRINT @VNUEVO

IF (SELECT COUNT(*)
    FROM trabajador
    WHERE CodDepto = @VANTIGUO) > 0
	BEGIN
		UPDATE  trabajador
		SET CodDepto = @VNUEVO 
		WHERE CodDepto = @VANTIGUO
	END
END;

--Comprobaciones:
SELECT * FROM departamento;
SELECT * FROM trabajador;


UPDATE departamento
SET CodDepto=200
WHERE CodDepto=2;