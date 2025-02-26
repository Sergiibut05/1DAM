--EJEMPLOS DE CREACI�N Y BORRADO DE �NDICES

/*
-Los �ndices se crean sobre los campos utilizados para realizar 
 b�squedas frecuentemente, ya que el objetivo de los �ndices es
 acelerar las b�squedas.
-Solo creamos los �ndices necesarios, puesto que la  estructura
 de los ficheros indexados es compleja y si  creamos demasiados
 �ndices podemos provocar el efecto contrario.
-El sistema crea autom�ticamente �ndices sobre las PK y sobre los campos 
 con la restricci�n UNIQUE. 
*/

USE EMPRESA;

--Creaci�n de un �ndice.
CREATE INDEX indice1
ON temple (nomem);

--Borrado de un �ndice.
DROP INDEX indice1 ON temple;

--Borrado de varios �ndices de una misma tabla.
CREATE INDEX indice1
ON temple (nomem);

CREATE INDEX indice2
ON temple (salar);

DROP INDEX indice1 ON temple, indice2 ON temple;

--Borrado de varios �ndices de diferentes tablas.
CREATE INDEX indice1
ON temple (nomem);

CREATE INDEX indice2
ON tdepto (nomde);

DROP INDEX indice1 ON temple, indice2 ON tdepto;

