--EJEMPLOS DE CREACI�N DE TABLAS. 
--RESTRICCI�N UNIQUE

/* 
-Crea un �ndice sobre el campo.
-Se suelen crear sobre las claves alternativas, puesto
 que normalmente se har�n b�squedas con frecuencia sobre ellas.
-Un campo con restricci�n UNIQUE puede admitir valores nulos,
 pero no valores duplicados.
*/

USE prueba4;

--Una vez creada la tabla ver los apartados Keys e Indexes.
CREATE TABLE tabla6
(c1 INT IDENTITY PRIMARY KEY,
 c2 nombres,
 c3 INT UNIQUE
 );
   
INSERT INTO tabla6 (c2,c3)
VALUES ('Juan',10),('Lola',21),('Ana',NULL);

SELECT * FROM tabla6;
  
  
--RESTRICCI�N UNIQUE eligiendo el nombre.
--Los nombres para las restricciones SON �NICOS en la base de datos.
CREATE TABLE tabla7
(c1 int PRIMARY KEY,
 c2 nombres,
 c3 INT CONSTRAINT res2 UNIQUE
 );

CREATE TABLE tabla8
(c1 INT PRIMARY KEY,
 c2 nombres,
 c3 INT,
 CONSTRAINT res3 UNIQUE(c3)
 );
 
/*
Observa como se ordenan las filas de la siguiente tabla
por el valor de la PK en orden ascendente. 
Esto es debido a que cuando se ha creado la tabla, se han 
creado dos �ndices autom�ticamente: uno para la PK de tipo
CLUSTERED y otro para el campo de la restricci�n UNIQUE de
tipo NONCLUSTERED
*/
INSERT INTO tabla8
VALUES (1,'Pepe',10),(3,'Ana',20);

INSERT INTO tabla8
VALUES (2,'Rosa',15);

SELECT * FROM tabla8;

/*Creaci�n de una tabla con la restricci�n UNIQUE especificando
que es CLUSTERED. 
En este caso, el �ndice de la PK de crea NONCLUSTERED, y por tanto,
las filas de la tabla se ordenan por el campo que tiene la restricci�n
UNIQUE.
*/
CREATE TABLE tabla9
(c1 INT PRIMARY KEY,
 c2 nombres,
 DNI CHAR(9) UNIQUE CLUSTERED
 );

INSERT INTO tabla9
VALUES (1,'Pepe','12345678B'),
       (2,'Ana','12345678A');

SELECT * FROM tabla9;


