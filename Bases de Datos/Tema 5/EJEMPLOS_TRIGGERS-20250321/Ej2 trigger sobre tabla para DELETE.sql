/*Queremos controlar la Regla de Integridad Referencial para
  cuando borramos un departamento, de manera que se ponga a NULL
  el campo CodDepto en la tabla trabajador para todos los
  trabajadores que pertenec�an al departamento que acabamos de
  borrar. Adem�s, no tenemos permiso para modificar la estructura de 
  las tablas para poder a�adirle esta restricci�n.*/

/*Trigger que controla que cuando borremos un departamento, para 
  todos los empleados pertenecientes al mismo, tengan NULL en su 
  campo CodDepto hasta que se les asigne a otro departamento*/ 

USE formacion;

CREATE TRIGGER trig2
ON departamento
FOR DELETE
AS
BEGIN

DECLARE @CODDEPTO INT

SELECT @CODDEPTO=CodDepto FROM deleted

IF (SELECT COUNT(*)
    FROM trabajador
    WHERE CodDepto=@CODDEPTO) > 0
	BEGIN
		UPDATE  trabajador
		SET CodDepto=NULL 
		WHERE CodDepto = @CODDEPTO
	END
END;

--Comprobaciones:
INSERT INTO departamento
VALUES(1,'DEPARTAMENTO1'), (2,'DEPARTAMENTO2'),(3,'DEPARTAMENTO3');

SELECT * FROM departamento;

INSERT INTO trabajador (CodTrab,NomTrab,ApeTrab,Salario,CodDepto)
VALUES (1,'JOS�','P�REZ P�REZ',1500,1),
       (2,'JUAN','P�REZ L�PEZ',1500,1),
	   (3,'ANA','G�MEZ L�PEZ',1500,2);

SELECT * FROM trabajador;

DELETE FROM departamento
WHERE CodDepto=1;

