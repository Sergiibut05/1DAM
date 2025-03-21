/*Vamos a comprobar que Los triggers pueden ser ejecutados
 en respuesta a una acción que está dentro de una transacción.
 Entonces, si uno de esos triggers contiene una sentencia 
 ROLLBACK TRANSACTION, la transacción completa en la que dicho
 trigger se ejecuta será descartada.*/

 /*Queremos crear un procedimiento almacenado para ir bajando
  los salarios de 50 en 50 euros e ir subiendo las comisiones
  de 100 en 100 euros. Podemos hacer esta operación
  mientras la suma de las comisiones no supere 2000 euros.*/

/*Vamos a definir un trigger sobre la tabla trabajador para 
  cuando se realice UPDATE de modo, que permita la bajada de
  los salarios y la subida de las comisiones mientras no se 
  superen los 2000 euros. Cuando esto ocurra, se realiza un 
  ROLLBACK que deshará la transacción donde estaban los 
  UPDATE para bajar salarios y subir comisiones*/

USE formacion;

CREATE OR ALTER PROCEDURE  proc1 AS
BEGIN

BEGIN TRANSACTION

--Bajamos los salarios		
UPDATE trabajador
SET salario = salario - 50

--Subimos las comisiones
UPDATE trabajador
SET comis =ISNULL(comis,0) + 100

COMMIT 

END;

CREATE TRIGGER trig5 ON trabajador
FOR UPDATE AS
BEGIN
/*Si nos salimos del presupuesto asignado para 
  comisiones lo dejamos todo como estaba*/
		IF (SELECT SUM(comis) 
		    FROM trabajador) > 2000
		  BEGIN
			ROLLBACK TRANSACTION
		  END
END;


SELECT * FROM trabajador;
SELECT SUM(comis) from trabajador;

EXEC proc1;

