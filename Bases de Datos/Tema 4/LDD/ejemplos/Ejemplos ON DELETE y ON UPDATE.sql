/*EJEMPLOS DE RESTRICCIÓN FOREIGN KEY ESPECIFICANDO LAS REGLAS
DE INTEGRIDAD PARA LA MODIFICACIÓN Y LA ELIMINACIÓN*/  

CREATE DATABASE prueba3;
GO
USE prueba3;

/*A continuación, vemos diferentes ejemplos en los que ON DELETE y
ON UPDATE toman el mismo valor. Pero, estos valores pueden ser
diferentes para ajustarse a lo que pida la especificación.*/

/*Ejemplos con: 
ON DELETE NO ACTION
ON UPDATE NO ACTION
*/

/*ON DELETE NO ACTION
Cuando hay un intento de borrar una fila de la TABLA
departamento y el valor de PK existe como FK en la tabla 
empleado: NO SE PERMITE EL borrado. Así funciona por defecto,
si no se pone la cláusula ON DELETE.*/

/*ON UPDATE NO ACTION
Cuando hay un intento de modificar el valor de la PK
de la TABLA departamento y el valor de PK existe como FK 
en la tabla empleado: NO SE PERMITE la modificación.
Así funciona por defecto si no se pone la cláusula ON UPDATE.*/

CREATE TABLE departamento
(
  CodDep INT NOT NULL,
  NomDep VARCHAR(20) NOT NULL,
  PRIMARY KEY (CodDep)
);

CREATE TABLE empleado
(
  CodEmp INT NOT NULL,
  NomEmp VARCHAR(60) NOT NULL,
  CodDep INT ,
  PRIMARY KEY (CodEmp),
  FOREIGN KEY (CodDep) REFERENCES departamento(CodDep)
						ON DELETE NO ACTION
						ON UPDATE NO ACTION
);

INSERT INTO departamento
VALUES (1,'DEP1'),(2,'DEP2'),(3,'DEP3');

INSERT INTO empleado
VALUES  (1,'EMP1',1),
        (2,'EMP2',1),
		(3,'EMP3',1),
		(4,'EMP4',2),
		(5,'EMP5',2),
		(6,'EMP6',NULL);

SELECT * FROM departamento;
SELECT * FROM empleado;


DELETE FROM departamento
WHERE CodDep=1;

BEGIN TRANSACTION;

DELETE FROM departamento
WHERE CodDep=3;

ROLLBACK TRANSACTION;

UPDATE departamento
SET CodDep=CodDep*100
WHERE CodDep=1;

BEGIN TRANSACTION;

UPDATE departamento
SET CodDep=CodDep*100
WHERE CodDep=3;

ROLLBACK TRANSACTION;

DROP TABLE empleado, departamento;


/*Ejemplos con: 
ON DELETE CASCADE
ON UPDATE CASCADE
*/

/*ON DELETE CASCADE
Cuando hay un intento de borrar una fila de la TABLA
departamento y el valor de PK existe como FK en la tabla 
empleado: La fila SE BORRA de la TABLA departamento y
SE BORRAN todas las filas de la TABLA empleado que tengan
el mismo valor.*/

/*ON UPDATE CASCADE
Cuando hay un intento de modificar el valor de la PK
de la TABLA departamento y el valor de PK existe como FK 
en la tabla empleado: La PK se modifica en la
TABLA departamento y se modifica el valor de la FK en
todas las filas de la TABLA empleado que tengan
el mismo valor.*/

CREATE TABLE departamento
(
  CodDep INT NOT NULL,
  NomDep VARCHAR(20) NOT NULL,
  PRIMARY KEY (CodDep)
);

CREATE TABLE empleado
(
  CodEmp INT NOT NULL,
  NomEmp VARCHAR(60) NOT NULL,
  CodDep INT ,
  PRIMARY KEY (CodEmp),
  FOREIGN KEY (CodDep) REFERENCES departamento(CodDep)
						ON DELETE CASCADE
						ON UPDATE CASCADE
);


INSERT INTO departamento
VALUES (1,'DEP1'),(2,'DEP2'),(3,'DEP3')

INSERT INTO empleado
VALUES  (1,'EMP1',1),
		(2,'EMP2',1),
		(3,'EMP3',1),
		(4,'EMP4',2),
		(5,'EMP5',2),
		(6,'EMP6',NULL);

SELECT * FROM departamento;
SELECT * FROM empleado;

BEGIN TRANSACTION;

DELETE FROM departamento
WHERE CodDep=1;

ROLLBACK TRANSACTION;

BEGIN TRANSACTION;

DELETE FROM departamento
WHERE CodDep=3;

ROLLBACK TRANSACTION;

BEGIN TRANSACTION;

UPDATE departamento
SET CodDep=CodDep*100
WHERE CodDep=1;

ROLLBACK TRANSACTION;

DROP TABLE empleado, departamento;


/*Ejemplos con: 
ON DELETE SET NULL
ON UPDATE SET NULL
IMPORTANTE: El campo de la FK debe permitir nulos.
*/

/*ON DELETE SET NULL
Cuando hay un intento de borrar una fila de la TABLA
departamento y el valor de PK existe como FK en la tabla 
empleado: La fila SE BORRA de la TABLA departamento y 
en la TABLA empleado SE PONEN A NULL los valores de la
FK que coincidan con la PK de la fila que hemos borrado*/

/*ON UPDATE SET NULL
Cuando hay un intento de modificar el valor de la PK
de la TABLA departamento y el valor de PK existe como FK
en la tabla empleado: La PK se modifica en la
TABLA departamento y SE PONEN A NULL los valores de la
FK que coincidan con la PK de la fila que hemos borrado
os valores de la FK */

CREATE TABLE departamento
(
  CodDep INT NOT NULL,
  NomDep VARCHAR(20) NOT NULL,
  PRIMARY KEY (CodDep)
);

CREATE TABLE empleado
(
  CodEmp INT NOT NULL,
  NomEmp VARCHAR(60) NOT NULL,
  CodDep INT,
  PRIMARY KEY (CodEmp),
  FOREIGN KEY (CodDep) REFERENCES departamento(CodDep)
						ON DELETE SET NULL
						ON UPDATE SET NULL
);

INSERT INTO departamento
VALUES (1,'DEP1'),(2,'DEP2'),(3,'DEP3')

INSERT INTO empleado
VALUES  (1,'EMP1',1),
		(2,'EMP2',1),
		(3,'EMP3',1),
		(4,'EMP4',2),
		(5,'EMP5',2),
		(6,'EMP6',NULL);

SELECT * FROM departamento;
SELECT * FROM empleado;

BEGIN TRANSACTION;

DELETE FROM departamento
WHERE CodDep=1;

ROLLBACK TRANSACTION;

BEGIN TRANSACTION;

UPDATE departamento
SET CodDep=CodDep*100
WHERE CodDep=1;

ROLLBACK TRANSACTION;

DROP TABLE empleado, departamento;



/*Ejemplos con:
  ON DELETE SET DEFAULT
  ON UPDATE SET DEFAULT
*/

/*Igual que SET NULL, pero la FK toma el valor indicado
por defecto en la creación de la TABLA REFERENCIAL, en vez de
tomar el valor NULL.*/


CREATE TABLE departamento
(
  CodDep INT NOT NULL,
  NomDep VARCHAR(20) NOT NULL,
  PRIMARY KEY (CodDep)
);

CREATE TABLE empleado
(
  CodEmp INT NOT NULL,
  NomEmp VARCHAR(60) NOT NULL,
  CodDep INT  DEFAULT 0,
  PRIMARY KEY (CodEmp),
  FOREIGN KEY (CodDep) REFERENCES departamento(CodDep)
						ON DELETE SET DEFAULT
						ON UPDATE SET DEFAULT
);


INSERT INTO departamento
VALUES (0,'Sin departamento'),(1,'DEP1'),(2,'DEP2'),(3,'DEP3')

INSERT INTO empleado
VALUES (1,'EMP1',1),
(2,'EMP2',1),
(3,'EMP3',1),
(4,'EMP4',2),
(5,'EMP5',2),
(6,'EMP6',2),
(7,'EMP7', DEFAULT);

SELECT * FROM departamento;
SELECT * FROM empleado;

BEGIN TRANSACTION;

DELETE FROM departamento
WHERE CodDep=1;

ROLLBACK TRANSACTION;

BEGIN TRANSACTION;

UPDATE departamento
SET CodDep=CodDep*100
WHERE CodDep=1;

ROLLBACK TRANSACTION;
