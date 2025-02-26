--EJEMPLOS DE MODIFICACIÓN Y BORRADO DE tablas.

CREATE DATABASE pruebas_alter_table;
GO
USE pruebas_alter_table;

/* Con ALTER TABLE podemos:
1. AÑADIR, BORRAR Y MODIFICAR COLUMNAS con las cláusulas:
   ADD, DROP COLUMN, ALTER COLUMN.
2. AÑADIR Y BORRAR RESTRICCIONES con las cláusulas:
   ADD CONSTRAINT, DROP CONSTRAINT.
*/ 

/*1. AÑADIR, BORRAR Y MODIFICAR COLUMNAS con las cláusulas:
     ADD, DROP COLUMN, ALTER COLUMN.*/

--AGREGAR UNA NUEVA COLUMNA: ADD.
/*
 Si la tabla está vacía, podemos:
 -Añadir un campo con restricción NULL, NOT NULL, DEFAULT (null o not null),
  IDENTITY (si no existe otro campo con esta propiedad), 
  CHECK (null o not null), UNIQUE (null o not null).
 -Añadir un campo con restricción FK (null o not null, y si existe la
 tabla objetivo).
 -Añadir un campo con restricción PK (si no existe otra PK).
*/
--EJEMPLOS: 
CREATE TABLE tabla1
(c1 INT PRIMARY KEY);

SELECT * FROM tabla1;

ALTER TABLE tabla1 ADD 
c2 VARCHAR(40) NOT NULL DEFAULT 'Málaga';

/*Si la tabla no está vacía, podemos:
-Añadir un campo con restricción NULL, DEFAULT (null o not null), 
 IDENTITY (si no existe otro campo con esta propiedad).
-Añadir un campo con restricción CHECK (debe permitir null).
-Si queremos añadir un campo con restricción UNIQUE:
 Una posible solución es :
	    o Añadiendo campo IDENTITY y UNIQUE.
 Otra solución podría ser:
		o Añadir campo NULL.
		o Actualizar tabla con valores no repetidos en el nuevo campo.
		o Añadir restricción UNIQUE.
-Añadir un campo con restricción FK (debe permitir null y existir la tabla objetivo).
-Si queremos añadir un campo con restricción PRIMARY (si no existe otra PK). 
 Una posible solución es :
		o Añadir campo IDENTITY y PRIMARY KEY.
 Otra solución podría ser:
		o Añadir campo NULL.
		o Actualizar tabla con valores no repetidos en el nuevo campo.
		o Añadir restricción NOT NULL (sino no permite la siguiente sentencia).
		o Añadir restricción de  PRIMARY KEY.
*/

--EJEMPLOS
--Añadir un campo con restricción NULL.
CREATE TABLE tabla2
(c1 INT PRIMARY KEY);

INSERT INTO tabla2
VALUES (1),(2);

SELECT * FROM tabla2;

ALTER TABLE tabla2 ADD c2 VARCHAR(2) NULL;

SELECT * FROM tabla2;

INSERT INTO tabla2 
VALUES (3,'aa'),(4,'bb');

SELECT * FROM tabla2;

/*Añadir un campo con restricción DEFAULT (NULL). La nueva columna 
contendrá valores nulos, y a partir de ahora, cuando no se introduzca
valor, se pondrá automáticamente el valor por defecto.
*/
CREATE TABLE tabla3
(c1 INT PRIMARY KEY);

INSERT INTO tabla3
VALUES (1),(2);

SELECT * FROM tabla3;

ALTER TABLE tabla3 ADD  c2 VARCHAR(20)  DEFAULT 'MÁLAGA';

SELECT * FROM tabla3;

INSERT INTO tabla3 (c1)
VALUES (3),(4);

SELECT * FROM tabla3;

/*Añadir un campo con restricción DEFAULT (NOT NULL). la nueva columna contendrá 
automáticamente el valor por defecto.*/
CREATE TABLE tabla4
(c1 INT PRIMARY KEY);

INSERT INTO tabla4
VALUES (1),(2);

SELECT * FROM tabla4;

ALTER TABLE tabla4 ADD  c2 VARCHAR(20) NOT NULL DEFAULT 'MÁLAGA'; 

SELECT * FROM tabla4;

INSERT INTO tabla4 (c1)
VALUES (3),(4);

SELECT * FROM tabla4;

INSERT INTO tabla4
VALUES (5,DEFAULT),(6,'SEVILLA');

SELECT * FROM tabla4;

--Añadir un campo IDENTITY.
CREATE TABLE tabla5
(c1 INT PRIMARY KEY);

INSERT INTO tabla5
VALUES (1),(2);

SELECT * FROM tabla5;

ALTER TABLE tabla5 ADD  c2 INT IDENTITY(100,10);  

SELECT * FROM tabla5;

INSERT INTO tabla5 (c1)
VALUES (3),(4);

SELECT * FROM tabla5;

/*Añadir un campo con restricción CHECK. Debe admitir NULL
Rellena las columnas con NULL, y cuando se introduzcan valores 
deben cumplir la restricción.*/

CREATE TABLE tabla6
(c1 INT PRIMARY KEY);

INSERT INTO tabla6
VALUES (1),(2);

SELECT * FROM tabla6;

ALTER TABLE tabla6 ADD c2 INT CHECK (c2>=10);  

SELECT * FROM tabla6;

INSERT INTO tabla6
VALUES (3,10),(4,12),(5,NULL);

--Daría error, pues no cumple la restricción
INSERT INTO tabla6
VALUES (6,9);

/* Para que la nueva columna pudiera no admitir nulos,
Podríamos, por ejemplo, poner un DEFAULT que cumpliera el CHECK.*/
ALTER TABLE tabla6 ADD c3 INT NOT NULL DEFAULT 10 CHECK (c3>=10);  

SELECT * FROM tabla6;

/*Añadir un campo con restricción UNIQUE: Añadiendo campo IDENTITY 
  y UNIQUE*/
CREATE TABLE tabla7
(c1 INT PRIMARY KEY);

INSERT INTO tabla7
VALUES (1),(2);

SELECT * FROM tabla7;

ALTER TABLE tabla7 ADD c2 INT IDENTITY (100,10) UNIQUE; 

SELECT * FROM tabla7;

/*Añadir un campo con restricción FK. Debe admitir NULL y existir la
tabla objetivo.*/
CREATE TABLE tabla_objetivo
(c1 INT PRIMARY KEY,
 c2 VARCHAR(20)
 );

INSERT INTO tabla_objetivo
VALUES (1,'valor_objetivo1'),(2,'valor_objetivo2');

CREATE TABLE tabla_referencial
(c1 INT PRIMARY KEY,
 c2 VARCHAR(20)
 );

 INSERT INTO tabla_referencial
 VALUES (1,'valor_referencial1'),(2,'valor_referencial2');

SELECT * FROM tabla_objetivo;
SELECT * FROM tabla_referencial;
  
ALTER TABLE tabla_referencial 
ADD c3 INT NULL FOREIGN KEY REFERENCES tabla_objetivo(c1) 
				ON DELETE SET NULL
				ON UPDATE NO ACTION;

INSERT INTO tabla_referencial
VALUES (3,'valor_referencial3',1),
       (4,'valor_referencial4',1);

SELECT * FROM tabla_objetivo;
SELECT * FROM tabla_referencial;

/*Añadir un campo con restricción PRIMARY 
 (si no existe otra PK): Añadiendo campo IDENTITY y PRIMARY KEY.*/
CREATE TABLE tabla8
(c1 INT);

INSERT INTO tabla8
VALUES (1),(2);

SELECT * FROM tabla8;

ALTER TABLE tabla8 ADD  c2 INT IDENTITY(100,10) PRIMARY KEY;

SELECT * FROM tabla8;

--Con una misma sentencia DROP podemos borrar más de una tabla.
/*Cuando vayamos a borrar una tabla objetivo y una referencial,
es importante recordar que debemos borrar primero la referencial.*/
DROP TABLE tabla1,tabla2,tabla3,tabla4,tabla5,tabla6,tabla7,tabla8;

DROP TABLE tabla_referencial,tabla_objetivo;

--BORRAR UNA COLUMNA: DROP COLUMN.
/* Para eliminar una columna, la única restricción que puede tener es NOT NULL.
   Si la columna tiene otra restricción debemos eliminar primero la restricción,
   y después podremos eliminar la columna.*/
CREATE TABLE tabla1
(c1 INT PRIMARY KEY,
 c2 VARCHAR(20) NOT NULL,
 c3 INT CONSTRAINT res1 CHECK (c3>0)
 ); 
 
SELECT * FROM tabla1;

--c2 podemos eliminarla directamente.
ALTER TABLE tabla1 DROP COLUMN c2;

SELECT * FROM tabla1;

--Para eliminar c3 debemos borrar primero la restricción.
ALTER TABLE tabla1 DROP CONSTRAINT res1;
ALTER TABLE tabla1 DROP COLUMN c3;

--MODIFICAR UNA COLUMNA: ALTER COLUMN.
/*
  Se usa para CAMBIAR EL TIPO DE DATOS DE UNA COLUMNA.
 -Podemos indicar NULL o NOT NULL y ninguna otra restricción. 
 -Si la tabla no está vacía y admite nulos en el campo a modificar
  y ya contiene un NULL, no se podrá modificar a NOT NULL.
 -Deben ser tipos compatibles (aunque la tabla esté vacía. Por ejemplo,
 de entero no podemos cambiar a tipo date.
*/
CREATE TABLE tabla2
 (c1 INT  PRIMARY KEY,
  c2 INT);

INSERT INTO tabla2
VALUES (1,10);

ALTER TABLE tabla2 ALTER COLUMN c2 VARCHAR(20) NOT NULL;

INSERT INTO tabla2  VALUES (2,'b');

SELECT * FROM tabla2;

--Ya no lo podremos modificar a INT pues tiene datos incompatibles.
ALTER TABLE tabla2 ALTER COLUMN c2 INT NOT NULL;

--Tampoco podremos cambiarlo a tipo fecha.
ALTER TABLE tabla2 ALTER COLUMN c2 DATE NOT NULL;

/*La sentencia siguiente no es válida pues estamos añadiendo
una restricción.*/
ALTER TABLE tabla2 ALTER COLUMN c2 VARCHAR(20) UNIQUE;

DROP TABLE tabla1,tabla2;


/*2.AÑADIR Y BORRAR RESTRICCIONES con las cláusulas:
  ADD CONSTRAINT, DROP CONSTRAINT*/
 
 -- ADD CONSTRAINT:

-- AGREGAR UNA RESTRICCIÓN CHECK NO COMPROBADA A UNA COLUMNA EXISTENTE
/*
 En el ejemplo siguiente se agrega una restricción a una columna 
 existente de la tabla. La columna tiene un valor que infringe la restricción. 
 Por lo tanto, WITH NOCHECK se utiliza para evitar que la restricción 
 se valide en las filas existentes, y para poder agregar la restricción.
*/
CREATE TABLE tabla1 
(c1 INT IDENTITY PRIMARY KEY,
 c2 INT
 );

INSERT INTO tabla1 (c2) VALUES (-3); 

SELECT * FROM tabla1;

/*En la siguiente sentencia debemos poner la cláusula WITH NOCHECK, pues
hay un valor que no cumple la restricción*/
ALTER TABLE tabla1 WITH NOCHECK
ADD CONSTRAINT nombre1 CHECK (c2 > 1); 

SELECT * FROM tabla1;

--Así no permite la inserción.
INSERT INTO tabla1 (c2) VALUES (-5); 

--Así sí permite la inserción.
INSERT INTO tabla1 (c2) VALUES (5);

SELECT * FROM tabla1;

--AÑADIR UNA RESTRICCIÓN PRIMARY KEY.
CREATE TABLE tabla2
(c1 INT NOT NULL,
 c2 VARCHAR(20)
 ); 
 
INSERT INTO tabla2 
VALUES (1,'valor1'),(2,'valor2');

SELECT * FROM tabla2;

ALTER TABLE tabla2
ADD CONSTRAINT res1 PRIMARY KEY (c1);

--Ejemplo para modificar la tabla para que la PK esté formada por c1 y c3.
ALTER TABLE tabla2
DROP CONSTRAINT res1;
  
ALTER TABLE tabla2 ADD c3 INT; 

SELECT * FROM tabla2;

DECLARE @VALOR INT = 0;
UPDATE tabla2
SET @VALOR= c3= @VALOR + 1;

ALTER TABLE tabla2 ALTER COLUMN c3 INT NOT NULL;

ALTER TABLE tabla2
ADD CONSTRAINT res1 PRIMARY KEY (c1,c3);

--AÑADIR UNA RESTRICCIÓN FOREIGN KEY.
CREATE TABLE tabla_objetivo
(c1 INT PRIMARY KEY,
 c2 INT
 );

CREATE TABLE tabla_referencial
(c1 INT PRIMARY KEY,
 c2 INT
 );
  

ALTER TABLE tabla_referencial
ADD CONSTRAINT res2 FOREIGN KEY (c2) 
    REFERENCES tabla_objetivo(c1) 
	ON UPDATE NO ACTION
	ON DELETE SET NULL;

--AÑADIR UNA RESTRICCIÓN DEFAULT.
CREATE TABLE tabla3
(c1 INT PRIMARY KEY,
 c2 INT
 );
 
ALTER TABLE tabla3
ADD CONSTRAINT res3 DEFAULT 0 FOR c2;
 
--AÑADIR UNA RESTRICCIÓN UNIQUE.
CREATE TABLE tabla4
(c1 INT PRIMARY KEY,
 c2 CHAR(1)
 );

 /*La tabla debe estar vacía o no contener valores repetidos
  en la columna donde vamos a imponerle la condición. Aquí no funciona
  "with nocheck", pues al crear una clave alternativa, no se puede 
  repetir el valor en la tabla.*/
 ALTER TABLE tabla4
 ADD CONSTRAINT res4 UNIQUE(c2);

-- DROP CONSTRAINT:
ALTER TABLE tabla4 
DROP CONSTRAINT res4;  

--BORRAR RESTRICCIÓN Y COLUMNA A LA VEZ.
 /* De la siguiente manera, se puede borrar un restricción y una 
 columna a la vez*/

CREATE TABLE tabla5
(c1 INT PRIMARY KEY,
 c2  CHAR(1) NOT NULL CONSTRAINT res5 CHECK (c2 LIKE '[FM]')
 );

--Si intentamos borrar la columna, no se permite, puesto que tiene una restricción.
ALTER TABLE tabla5 
DROP COLUMN c2;

/*O bien, utilizamos dos sentencias, una para borrar la restricción y otra para
borrar la columna, o bien, utilizamos la siguiente sentencia:*/
BEGIN TRANSACTION;

ALTER TABLE tabla5 DROP CONSTRAINT res5;

ALTER TABLE tabla5 DROP COLUMN c2;

SELECT * FROM tabla5;

ROLLBACK TRANSACTION;

ALTER TABLE tabla5 DROP CONSTRAINT res5, COLUMN c2;


DROP TABLE tabla1,tabla2,tabla3,tabla4,tabla5;
DROP TABLE tabla_referencial, tabla_objetivo;