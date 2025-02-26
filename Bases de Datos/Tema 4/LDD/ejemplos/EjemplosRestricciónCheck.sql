--EJEMPLOS DE CREACI�N DE TABLAS. 
--RESTRICCI�N CHECK

/*Observa que la forma que tiene la restricci�n CHECK es:
-CHECK(PREDICADO).
-Donde PREDICADO ser� cualquiera de los estudiados en 
 la sentencia SELECT del LMD.
-Los �nicos tipos de predicados que no se permiten son los 
 que incluyen un subselect.*/

USE prueba4;

/*
-Una vez creada la tabla ver el apartado Constraints.
-Observa que en el apartado de Constraints de solo 
 aparecen las restricciones de DEFAULT y CHECK.
*/
CREATE TABLE tabla10
(c1 INT PRIMARY KEY,
 c2 INT CHECK (c2 >= 16)
 );

--Esta inserci�n incumple la restricci�n CHECK y dar� error 
INSERT INTO tabla10
VALUES (1,10);

CREATE TABLE tabla11
(c1 INT PRIMARY KEY,
 c2 INT CHECK (c2 >=16 AND c2<65) 
 );

CREATE TABLE tabla12
(c1 INT PRIMARY KEY,
 c2 INT CHECK (c2 IN ('1389', '0736', '0877', '1622', '1756')
               OR c2 LIKE '99[0-9][0-9]')
);

CREATE TABLE tabla13
(c1 INT PRIMARY KEY,
 c2 INT CHECK (c2 BETWEEN 0 AND 1000)
);
 
CREATE TABLE tabla14
(c1 INT PRIMARY KEY,
 c2 CHAR(9) CHECK (c2 LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][A-Z]')
 );
 
CREATE TABLE tabla15
(c1 INT PRIMARY KEY,
 c2 CHAR(9) NOT NULL UNIQUE CHECK (c2 LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][A-Z]')
);

--RESTRICCI�N CHECK eligiendo el nombre.
--Los nombres para las restricciones SON �NICOS en la base de datos. 
CREATE TABLE tabla16
(c1 INT PRIMARY KEY,
 c2 INT CONSTRAINT res4 CHECK (c2 >=16)
);
 
CREATE TABLE tabla17
(c1 INT PRIMARY KEY,
 c2 INT,
 CONSTRAINT res5 CHECK (c2 >=16)
 );