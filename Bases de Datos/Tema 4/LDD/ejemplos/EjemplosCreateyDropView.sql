--EJEMPLOS DE CREACIÓN, MODIFICACIÓN Y BORRADO DE VISTAS.

USE EMPRESA;

--Creación de vistas.
--Creación de una vista sencilla.
CREATE VIEW vista1
AS
SELECT * FROM temple
WHERE salar>1500;
   
SELECT * FROM vista1 
ORDER BY 2;
  
--Uso de funciones en una vista.
/*En el siguiente ejemplo se muestra una definición de vista que incluye
una función de agregado. Al utilizar funciones, es necesario especificar un 
nombre de columna para la columna derivada.*/
CREATE VIEW vista2
AS
SELECT numde,AVG(salar) 'Salario Medio por Departamento'
FROM temple
GROUP BY numde;

SELECT * FROM vista2;

CREATE VIEW vista3 (Departamento,Salario)
AS
SELECT numde,AVG(salar)
FROM temple
GROUP BY numde;

SELECT * FROM vista3;

--Creación de una vista con JOIN.
 CREATE VIEW vista4
 AS
 SELECT numem,nomem,salar,nomde
 FROM   tdepto D RIGHT JOIN temple E ON D.numde=E.numde;
  
 SELECT * FROM vista4;
  
/*
Operaciones sobre vistas:
-En una VISTA que implica una sola tabla es posible la consulta, inserción,
 borrado y modificación.
-En un VISTA que implica más de una tabla es posible la consulta y la modificación.
*/
--INSERTAR, BORRAR Y MODIFICAR datos en una vista de una sola tabla.
SELECT * FROM vista1;

INSERT INTO vista1 (numem,nomem,salar)
VALUES(450,'PÉREZ,ROSAE',2000);

INSERT INTO vista1(numem,nomem,salar)
VALUES(451,'PÉREZ,ROSAE',1000);

--Observa cómo se han insertado las dos filas en la tabla.
SELECT * FROM temple;

--Observa cómo solo sale el empleado 450 en la consulta de la vista.
SELECT * FROM vista1;

/*Observa cómo se modifica en la tabla y aparece la modificación 
si consultamos la vista*/
UPDATE vista1
SET salar=2200
WHERE numem=450;

SELECT * FROM temple;
SELECT * FROM vista1;

/*Observa que si hacemos la modificación en la vista de un
empleado que no está en la vista. La modificación no se realiza.*/
UPDATE vista1
SET salar=2200
WHERE numem=451;

SELECT * FROM temple;
SELECT * FROM vista1;

/*Observa que si modifico un empleado de la vista y después no 
cumple el SELECT de la vista, este ya no sale en la vista, pero si en
la tabla.*/
UPDATE vista1
SET salar=1000
WHERE numem=450;

SELECT * FROM temple;
SELECT * FROM vista1;

/*Volvemos a dejar al empleado 450 con salarIo igual 2200 para 
que salga en la vista.*/
UPDATE temple
SET salar=2200
WHERE numem=450;

/*Observa que si hacemos el borrado en la vista de un
empleado que no está en la vista. el borrado no se realiza.*/
DELETE FROM vista1
WHERE numem=451;

/*Observa cómo se borra de la tabla y no aparace en la consulta
de la vista.*/
DELETE FROM vista1
WHERE numem=370;

SELECT * FROM temple;
SELECT * FROM vista1;

--MODIFICAR datos en una vista que implica más de una tabla.
SELECT * FROM temple;
SELECT * FROM vista4;

UPDATE vista4
SET salar=2200
WHERE numem=451;

--No podremos BORRAR ni INSERTAR en vista4.
DELETE FROM vista4
WHERE numem=451;

INSERT INTO vista4 (numem,nomem,salar,nomde)
VALUES(452,'PÉREZ,ROSAE',1000,'NUEVO');

--Modificación de la estructura de una vista.
/* 
-Si la lógica de la consulta cambia, puedes modificar la estructura de la vista.
-Si la vista tiene otorgados determinados permisos y la borramos
 para crearla con la nueva lógica, estos permisos se perderán. 
 */
ALTER VIEW vista1
AS
SELECT * FROM temple
WHERE salar>2000;

SELECT * FROM temple;
SELECT * FROM vista1;

--Borrar vistas.
DROP VIEW vista1;
DROP VIEW vista2,vista3,vista4;