--EJEMPLOS DE CREACI�N DE TABLAS. 
--RESTRICCI�N DEFAULT

CREATE DATABASE prueba4;
GO
USE prueba4;

--CREACI�N DE UN TIPO DE DATOS.
/*Utilizamos el procedimiento almacenado sp_addtype. Una vez creado lo
podemos ver en Programmability/Types*/
sp_addtype nombres, 'VARCHAR(30)','NOT NULL';

--Una vez creada la tabla ver el apartado Constraints.
CREATE TABLE tabla1
(c1 INT PRIMARY KEY,
 c2 nombres DEFAULT 'M�laga'
 ); 

--A�adimos datos utilizando la palabra reservada DEFAULT.
 INSERT INTO tabla1 (c1,c2)
 VALUES (1, DEFAULT);

 INSERT INTO tabla1
 VALUES (2, DEFAULT);
 
 INSERT INTO tabla1 (c1)
 VALUES (3);

SELECT * FROM tabla1;

--El campo c2 toma por defecto el valor 0. 
CREATE TABLE tabla2
(c1 INT PRIMARY KEY,
 c2 INT DEFAULT 0
 ); 

/*
 -Observa que al no introducir valor en c2, toma el valor por defecto.
  Esto ocurre tanto si el campo admite nulos, como si no.
 -Si el campo admite nulos y queremos almacenar NULL, se tiene que escribir
  expl�citamente.*/
 INSERT INTO tabla2 (c1)
 VALUES (1);

 INSERT INTO tabla2 (c1,c2)
 VALUES (2,DEFAULT),(3,NULL);

 SELECT * FROM tabla2;

--El campo c2 toma por defecto la fecha-hora actual.
CREATE TABLE tabla3
(c1 INT PRIMARY KEY,
 c2 DATETIME DEFAULT CURRENT_TIMESTAMP
 );

INSERT INTO tabla3
VALUES(1,DEFAULT);
 
SELECT * FROM tabla3;
 
--El campo c2 toma por defecto la fecha-hora actual.
CREATE TABLE tabla4
(c1 INT IDENTITY PRIMARY KEY,
 c2 DATETIME DEFAULT GETDATE()
 );
 
INSERT INTO tabla4
VALUES(DEFAULT);
 
SELECT * FROM tabla4;

/*
 -RESTRICCI�N DEFAULT eligiendo el nombre.
 -Los nombres para las restricciones SON �NICOS en la base de datos.
 -Las restricciones de PRIMARY KEY, FOREIGN KEY y UNIQUE se pueden 
  definirse como restricciones de tabla despu�s de declarar todas
  las columnas, pero las restricciones DEFAULT s�lo pueden declararse 
  en la definici�n de la columna.
 -El valor por defecto tambi�n puede ir entre par�ntesis.
*/
CREATE TABLE tabla5
(c1 INT PRIMARY KEY,
 c2 INT CONSTRAINT res1 DEFAULT (0)
 );


