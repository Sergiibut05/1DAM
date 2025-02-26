--EJEMPLOS DE CREACIÓN DE TABLAS. 
--RESTRICCIÓN PRIMARY KEY y uso de IDENTITY.

CREATE DATABASE pruebas1;
GO
USE pruebas1;

--Tabla sin PRIMARY KEY. No se suele hacer.
CREATE TABLE tabla1
(c1 INT,
 c2 CHAR(2)
 );

--Tabla con creación de la PRIMARY KEY en la definición del propio campo.
CREATE TABLE tabla2
(c1 INT PRIMARY KEY,
 c2 CHAR(2)
 );

--Tabla con IDENTITY.
CREATE TABLE tabla3
(c1 INT PRIMARY KEY,
 c2 INT IDENTITY(100,1)
 );
 
/*Añadir datos a un campo IDENTITY:
  No se pone ni el nombre de la columna, ni el valor.
*/
INSERT INTO tabla3 (c1)
VALUES (1),(2);

/*Si introduces los valores en todos los campos  (excepto 
en el campo identity), puedes no poner nombre a las columnas*/ 
INSERT INTO tabla3
VALUES (3),(4);

--Esto da error.
INSERT INTO tabla3 (c1,c2)
VALUES (5,100),(6,101);
 
SELECT * FROM tabla3;
 
----Tabla con IDENTITY en la PRIMARY KEY.
CREATE TABLE tabla4
(c1 INT IDENTITY PRIMARY KEY,
 c2 CHAR(1) NOT NULL
 );
 
INSERT INTO tabla4 (c2)
VALUES ('a'),('b');
 
INSERT INTO tabla4
VALUES ('c'),('d');
 
SELECT * FROM tabla4;

/*El inconveniente de una columna IDENTITY es que si borramos
la fila, por ejemplo las filas 2 y 3, los valores del campo
IDENTITY no se reasignan.  
*/
DELETE FROM tabla4
WHERE c1>1 AND c1<4;

SELECT * FROM tabla4;

INSERT INTO tabla4
VALUES ('e'),('f');

SELECT * FROM tabla4;

/*ACTIVAR Y DESACTIVAR IDENTITY para solucionar el problema
anterior.*/

--DESACTIVAR IDENTITY para poder insertar valores en dicho campo.
SET IDENTITY_INSERT tabla4 ON; 

SELECT * FROM tabla4;

INSERT INTO tabla4 (c1,c2)
VALUES (2,'b'),(3,'c');

--ACTIVAR IDENTITY para que se autoincremente.
SET IDENTITY_INSERT tabla4 OFF;

/*Tabla con creación de la PRIMARY KEY después de declarar
todos los campos.*/
CREATE TABLE tabla5
(c1 INT,
 c2 INT,
 c3 VARCHAR(10),
 PRIMARY KEY (c1)
 );
 
INSERT INTO tabla5
VALUES (1,10,'aa'),(2,20,'bb');

SELECT * FROM tabla5;

/*Cuando la PRIMARY KEY es compuesta, ES OBLIGATORIO
  hacer la declaración de esta después de declarar
  todas las columnas.*/
 CREATE TABLE tabla6
 (c1 INT,
  c2 INT,
  c3 VARCHAR(10),
  PRIMARY KEY (c1,c2)
  );

INSERT INTO tabla6
VALUES (1,1,'aa'), (1,2,'bb');

SELECT * FROM tabla6;
  
--RESTRICCIÓN PRIMARY KEY eligiendo el nombre.
--Los nombres para las restricciones SON ÚNICOS en la base de datos.
 
CREATE TABLE tabla7
(c1 INT CONSTRAINT res1 PRIMARY KEY,
 c2 INT,
 c3 VARCHAR(10)
 );

CREATE TABLE tabla8
(c1 INT,
 c2 INT,
 c3 VARCHAR(10),
 CONSTRAINT res2 PRIMARY KEY (c1)
 );

CREATE TABLE tabla9
(c1 INT,
 c2 INT,
 c3 VARCHAR(10),
 CONSTRAINT res3 PRIMARY KEY (c1,c2)
 );
  