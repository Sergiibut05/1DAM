--EJEMPLOS DE CREACI�N DE TABLAS. 
--RESTRICCI�N FOREIGN KEY.

CREATE DATABASE pruebas2;
GO
USE pruebas2;

--Creaci�n de la TABLA OBJETIVO.
CREATE TABLE tabla_objetivo
(c1 INT PRIMARY KEY,
 c2 INT
 );

--Creaci�n de varias TABLAS REFERENCIALES

--RESTRICCI�N FOREIGN KEY en la declaraci�n del propio campo.
CREATE TABLE tabla_referencial1
(c1 INT PRIMARY KEY,
 c2 INT REFERENCES tabla_objetivo(c1)
 );
 
 CREATE TABLE tabla_referencial2
(c1 int PRIMARY KEY,
 c2 int FOREIGN KEY REFERENCES tabla_objetivo(c1) 
 ); 

--RESTRICCI�N FOREIGN KEY despu�s de declarar todos los campos.
CREATE TABLE tabla_referencial3
(c1 INT,
 c2 INT,
 c3 INT,
 c4 CHAR(1),
 PRIMARY KEY (c1),
 FOREIGN KEY (c2) REFERENCES tabla_objetivo(c1)
 );

--RESTRICCI�N FOREIGN KEY eligiendo el nombre.
--Los nombres para las restricciones SON �NICOS en la base de datos.

CREATE TABLE tabla_referencial4
(c1 INT PRIMARY KEY,
 c2 INT CONSTRAINT res1 REFERENCES tabla_objetivo(c1)
 ); 
 
CREATE TABLE tabla_referencial5
(c1 INT PRIMARY KEY,
 c2 INT CONSTRAINT res2 FOREIGN KEY REFERENCES tabla_objetivo(c1) /*MySQL no permite la declaraci�n as�*/
 );
 
 CREATE TABLE tabla_referencial6
(c1 INT,
 c2 INT,
 CONSTRAINT res4 PRIMARY KEY (c1),
 CONSTRAINT res5 FOREIGN KEY (c2) REFERENCES tabla_objetivo(c1)
 );

/*Ver en el diagrama de la base de datos la FOREIGN KEY:
  nombre y reglas por defecto (NO ACTION). */

/*En las reglas de eliminaci�n (ON DELETE) y actualizaci�n (ON UPDATE)
podemos poner: 
CASCADE | NO ACTION | SET NULL |SET DEFAULT

-SET DEFAULT es igual SET NULL si la clave foranea admite nulos y 
no tiene establecido un valor predeterminado. En caso de tener un 
valor por defecto se pone este.  
*/

