USE EMPRESA

/*Obtener los empleados cuyo salario estén comprendido entre
1000 y 1600 ambos inclusive.*/
--Lo que nos pide es que: salario >= 1000 AND salario <= 1600.

--Vemos todos los empleados.
SELECT * FROM temple;

SELECT *
FROM temple
WHERE salar BETWEEN 1000 AND 1600;

--Si no usamos el predicado BETWEEN, se podría hacer así.
SELECT *
FROM temple
WHERE salar >= 1000 AND salar<=1600;

/*Obtener los empleados cuyo salario sea menor que 1000 o bien mayor que 1600. */
--Lo que nos pide es que: salario < 1000 OR salario > 1600.
--Al ser lo contrario de BETWEEN, podemos usar el predicado NOT BETWEEN.

SELECT *
FROM temple
WHERE salar NOT BETWEEN 1000 AND 1600;

--Si no usamos el predicado BETWEEN, se podría hacer así.
SELECT *
FROM temple 
WHERE salar < 1000 OR salar> 1600;
