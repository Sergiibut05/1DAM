--EJEMPLOS DE TIPOS DE EXPRESIONES QUE NOS PODEMOS ENCONTRAR:

USE empresa;

--SELECT expresión. Donde expresión es un operando (función).
SELECT GETDATE();

/*SELECT expresión. Donde expresión es un operando (función), que tiene como parámetro 
otra función */
SELECT FORMAT(GETDATE(),'d');

--SELECT * y usando la cláusula FROM.
SELECT * 
FROM temple;

/*SELECT expresión1, expresión2 con cláusula FROM y cláusula ORDER BY.
Donde expresión1 y expresión2 son operandos (nombres de columnas)*/
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

/*SELECT expresión1, expresión2 con cláusula FROM y cláusula TOP para
limitar el número de filas mostradas*/
--Del resultado de la consulta muestra las cinco primeras filas.
SELECT TOP(5) nomem, salar 
FROM temple;

/*Dos ejemplos de SELECT con TOP y ORDER BY*/
--Del resultado de la consulta muestra las cinco primeras filas.
--ORDER BY 1: 1 indica el lugar que ocupa la expresión del SELECT por la que ordeno.
SELECT TOP(5) nomem, salar 
FROM temple
ORDER BY 1;

--Del resultado de la consulta muestra el 50%.
SELECT TOP(50) PERCENT nomem, salar 
FROM temple
ORDER BY 1;

--SELECT con la cláusula DISTINCT y ORDER BY
--Así mostramos los salarios con las comisiones de los 14 empleados.
--ALL es el valor por defecto. Podemos no ponerlo.
SELECT ALL salar, comis 
FROM temple
ORDER BY 1;
--Si solo queremos conocer los distintos salarios con sus comisiones, me interesa que no se repitan las filas.
SELECT DISTINCT salar,comis 
FROM temple
ORDER BY 1;

/*SELECT expresión1, expresión2, expresión3 con cláusula FROM. 
Donde expresión1 y expresión2 son operandos (nombres de columnas) y
expresión3 está formada por operando (nombre de columna), operador aritmético
(producto) y operando (constante numérica 2).*/
-- También usamos un alias de columna para darle nombre a una columna.
SELECT nomem, salar, (salar * 2) AS 'DOBLE DEL SALARIO'
FROM temple;

/*SELECT expresión1, expresión2, expresión3 con cláusula FROM. 
Donde expresión1 y expresión2 son operandos (nombres de columnas) y
expresión3 está formada por operando (nombre de columna), operador aritmético
(producto) y otra expresión (10/100). A su vez, esta última expresión está formada 
por operando (constante numérica 10), operador aritmético (división) y operando 
(constante numérica 100).*/
-- También usamos un alias de columna para darle nombre a una columna.
SELECT nomem, salar, (salar * 10/100) AS '10% DEL SALARIO'
FROM temple;

--FUNCIONES ESCALARES

--SELECT expresión. Donde expresión es un operando (función escalar).
--También usamos un alias de columna para darle nombre a una columna.
SELECT nomem, LEN(nomem) AS 'LONGITUD' 
FROM temple;


SELECT fecna, YEAR(GETDATE()) - YEAR(fecna) AS 'AÑOS'
FROM temple;

--FUNCIONES COLECTIVAS O DE AGREGADO

--SELECT expresión. Donde expresión es un operando (función de agregado).
--También usamos un alias de columna para darle nombre a una columna.
SELECT AVG(salar) AS 'SALARIO MEDIO'
FROM temple;

--FUNCIÓN DE AGREGADO COUNT
/*
COUNT(nombre_columna) cuenta el número de veces que
el atributo tiene valor distinto de null en la tabla

COUNT(*) cuenta el número de filas de la tabla
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


