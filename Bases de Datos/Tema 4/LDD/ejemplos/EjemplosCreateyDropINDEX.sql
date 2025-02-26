--EJEMPLOS DE CREACIÓN Y BORRADO DE ÍNDICES

/*
-Los índices se crean sobre los campos utilizados para realizar 
 búsquedas frecuentemente, ya que el objetivo de los índices es
 acelerar las búsquedas.
-Solo creamos los índices necesarios, puesto que la  estructura
 de los ficheros indexados es compleja y si  creamos demasiados
 índices podemos provocar el efecto contrario.
-El sistema crea automáticamente índices sobre las PK y sobre los campos 
 con la restricción UNIQUE. 
*/

USE EMPRESA;

--Creación de un índice.
CREATE INDEX indice1
ON temple (nomem);

--Borrado de un índice.
DROP INDEX indice1 ON temple;

--Borrado de varios índices de una misma tabla.
CREATE INDEX indice1
ON temple (nomem);

CREATE INDEX indice2
ON temple (salar);

DROP INDEX indice1 ON temple, indice2 ON temple;

--Borrado de varios índices de diferentes tablas.
CREATE INDEX indice1
ON temple (nomem);

CREATE INDEX indice2
ON tdepto (nomde);

DROP INDEX indice1 ON temple, indice2 ON tdepto;

