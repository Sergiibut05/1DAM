USE EMPRESA

/* 
   EXISTS     = TRUE  si el subselect devuelve 1 o más filas
   EXISTS     = FALSE si el subselect devuelve 0 filas

   NOT EXISTS = TRUE  si el subselect devuelve 0 filas
   NOT EXISTS = FALSE si el subselect devuelve 1 o más filas
*/

--Si existe algún departamento, entonces decir cuántos empleados tiene la empresa.
SELECT COUNT(*)
FROM temple
WHERE EXISTS (SELECT * FROM tdepto);

/*Si todos los directores son en propiedad, entonces decir cuántos empleados 
tiene la empresa, o lo que es lo mismo, si no existe ningún director en funciones, 
entonces decir cuántos empleados tiene la empresa*/
SELECT COUNT(*)
FROM temple
WHERE  NOT EXISTS (SELECT * FROM tdepto WHERE tidir='F');

--OTRA FORMA DE HACER EL EJEMPLO:
--Usamos la cláusula CASE. (Ver SQL Case en el enlace Manual SQL w3schools)

SELECT CASE WHEN (SELECT COUNT(*) FROM tdepto) = 
				 (SELECT COUNT(*) FROM tdepto WHERE tidir='P') THEN COUNT(*)
			ELSE 0
			END
FROM temple;

/*Obtener el nombre de los centros que tienen al menos un departamentos
 con presupuestos inferior a 3000 euros*/

--Vemos todos los departamentos y centros.
SELECT * FROM tdepto;
SELECT * FROM tcentr;

SELECT nomce
FROM tcentr C
WHERE (EXISTS (SELECT * FROM tdepto  D WHERE D.numce=C.numce AND presu<3000));

--OTRAS FORMAS DE HACER EL EJEMPLO:

--Con predicado ANY.
SELECT nomce
FROM tcentr C
WHERE numce = ANY (SELECT numce FROM tdepto  WHERE presu<3000);

--Con predicado IN.
SELECT nomce
FROM tcentr C
WHERE numce IN (SELECT numce FROM tdepto  WHERE presu<3000);

--Con JOIN.
/*En este caso hay que poner la cláusula DISTINCT, porque en el caso de que
hubiera más de un departamento en el mismo centro que cumpliera el WHERE,
el nombre del centro se repetiría. Prueba con presu<=3000
*/
SELECT DISTINCT nomce
FROM tcentr c JOIN tdepto D ON (C.numce=d.numce) 
WHERE presu<3000;

 
/* Obtener el nombre de los departamentos que no tienen empleados con
sueldos inferiores a 1500 euros*/

--Vemos todos los departamentos y empleados.
SELECT * FROM tdepto;
SELECT * FROM temple ORDER BY numde;

SELECT nomde
FROM tdepto D
WHERE (NOT EXISTS (SELECT * FROM temple E WHERE D.numde=E.numde AND salar<1500));

--OTRAS FORMAS DE HACER EL EJEMPLO:

--Con predicado ALL
SELECT nomde
FROM tdepto 
WHERE numde <> ALL(SELECT numde FROM temple  WHERE salar<1500);

--Con Predicado IN
SELECT nomde
FROM tdepto 
WHERE numde NOT IN (SELECT numde FROM temple  WHERE salar<1500);

