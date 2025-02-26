--EJEMPLOS DE TIPOS DE EXPRESIONES QUE NOS PODEMOS ENCONTRAR:

USE empresa;

--SELECT expresi�n. Donde expresi�n es un operando (funci�n).
SELECT GETDATE();

/*SELECT expresi�n. Donde expresi�n es un operando (funci�n), que tiene como par�metro 
otra funci�n */
SELECT FORMAT(GETDATE(),'d');

--SELECT * y usando la cl�usula FROM.
SELECT * 
FROM temple;

/*SELECT expresi�n1, expresi�n2 con cl�usula FROM y cl�usula ORDER BY.
Donde expresi�n1 y expresi�n2 son operandos (nombres de columnas)*/
SELECT  nomem, salar
FROM temple
ORDER BY nomem ASC;

--Igual si no ponemos ASC (valor por defecto).
SELECT  nomem, salar
FROM temple
ORDER BY nomem;

--Igual, pero ordenando descendentemente.
SELECT  nomem, salar
FROM temple
ORDER BY nomem DESC;

/*SELECT expresi�n1, expresi�n2 con cl�usula FROM y cl�usula TOP para
limitar el n�mero de filas mostradas*/
--Del resultado de la consulta muestra las cinco primeras filas.
SELECT TOP(5) nomem, salar 
FROM temple;

/*Dos ejemplos de SELECT con TOP y ORDER BY*/
--Del resultado de la consulta muestra las cinco primeras filas.
--ORDER BY 1: 1 indica el lugar que ocupa la expresi�n del SELECT por la que ordeno.
SELECT TOP(5) nomem, salar 
FROM temple
ORDER BY 1;

--Del resultado de la consulta muestra el 50%.
SELECT TOP(50) PERCENT nomem, salar 
FROM temple
ORDER BY 1;

--SELECT con la cl�usula DISTINCT y ORDER BY
--As� mostramos los salarios con las comisiones de los 14 empleados.
--ALL es el valor por defecto. Podemos no ponerlo.
SELECT ALL salar, comis 
FROM temple
ORDER BY 1;
--Si solo queremos conocer los distintos salarios con sus comisiones, me interesa que no se repitan las filas.
SELECT DISTINCT salar,comis 
FROM temple
ORDER BY 1;

/*SELECT expresi�n1, expresi�n2, expresi�n3 con cl�usula FROM. 
Donde expresi�n1 y expresi�n2 son operandos (nombres de columnas) y
expresi�n3 est� formada por operando (nombre de columna), operador aritm�tico
(producto) y operando (constante num�rica 2).*/
-- Tambi�n usamos un alias de columna para darle nombre a una columna.
SELECT nomem, salar, (salar * 2) AS 'DOBLE DEL SALARIO'
FROM temple;

/*SELECT expresi�n1, expresi�n2, expresi�n3 con cl�usula FROM. 
Donde expresi�n1 y expresi�n2 son operandos (nombres de columnas) y
expresi�n3 est� formada por operando (nombre de columna), operador aritm�tico
(producto) y otra expresi�n (10/100). A su vez, esta �ltima expresi�n est� formada 
por operando (constante num�rica 10), operador aritm�tico (divisi�n) y operando 
(constante num�rica 100).*/
-- Tambi�n usamos un alias de columna para darle nombre a una columna.
SELECT nomem, salar, (salar * 10/100) AS '10% DEL SALARIO'
FROM temple;

--FUNCIONES ESCALARES

--SELECT expresi�n. Donde expresi�n es un operando (funci�n escalar).
--Tambi�n usamos un alias de columna para darle nombre a una columna.
SELECT nomem, LEN(nomem) AS 'LONGITUD' 
FROM temple;


SELECT fecna, YEAR(GETDATE()) - YEAR(fecna) AS 'A�OS'
FROM temple;

--FUNCIONES COLECTIVAS O DE AGREGADO

--SELECT expresi�n. Donde expresi�n es un operando (funci�n de agregado).
--Tambi�n usamos un alias de columna para darle nombre a una columna.
SELECT AVG(salar) AS 'SALARIO MEDIO'
FROM temple;

--FUNCI�N DE AGREGADO COUNT
/*
COUNT(nombre_columna) cuenta el n�mero de veces que
el atributo tiene valor distinto de null en la tabla

COUNT(*) cuenta el n�mero de filas de la tabla
*/

SELECT COUNT(extel)
FROM temple;

SELECT COUNT(DISTINCT extel)
FROM temple;

SELECT COUNT(*)
FROM temple;

SELECT COUNT(comis)
FROM temple;

SELECT COUNT(DISTINCT comis)
FROM temple;

SELECT COUNT(*)
FROM temple;


