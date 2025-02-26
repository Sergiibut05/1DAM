USE EMPRESA

/*Obtener el número de los empleados (su identificador único) que ganen más que
alguno de los empleados del departamento 110*/

--Vemos todos los empleados.
SELECT * FROM temple;

--Vemos los datos que nos interesa de los empleados del departamento 110.
SELECT numem, salar, numde
FROM temple
WHERE numde=110;

--Estos son los empleados cuyo salario es mayor que 1500 o mayor que 1800. 
SELECT *
FROM temple
WHERE salar > ANY(SELECT salar
		          FROM temple
   		          WHERE numde=110);

/*Si no queremos que salgan los empleados del departamento 110 vamos 
a poner un predicado compuesto utilizando el operador lógico AND*/
SELECT *
FROM temple
WHERE (salar > ANY(SELECT salar
		    FROM temple
   		    WHERE numde=110) ) AND numde <> 110;

--SOLUCIÓN:
SELECT numem
FROM temple
WHERE (salar > ANY(SELECT salar
		    FROM temple
   		    WHERE numde=110) ) AND numde <> 110;

--OTRAS FORMAS DE HACER EL EJEMPLO:

/*Si el salario es mayor que el más pequeño, entonces lo será para los
dos*/
SELECT numem 
FROM temple
WHERE (salar > (SELECT MIN (salar) FROM temple WHERE numde=110) ) AND numde <> 110;

/*SELF JOIN: Se llama SELF JOIN cuando hacemos un producto cartesiano
o una concatenación de una tabla consigo misma.*/

--Con producto cartesiano.
SELECT E1.numem ,E1.numde,E1.salar,E2.numem,E2.numde,E2.salar
FROM temple E1, temple E2
WHERE (E1.salar>E2.salar) AND E2.numde=110 AND E1.numde<>110;

/*Tenemos que poner DISTINCT para que no salgan dos veces los empleados
como el 150 cuyo salario es 2200 y cumple que es mayor que
1500 y mayor que 1800*/
SELECT DISTINCT E1.numem 
FROM temple E1, temple E2
WHERE (E1.salar>E2.salar) AND E2.numde=110 AND E1.numde<>110;

--Con JOIN
SELECT DISTINCT E1.numem 
FROM temple E1 JOIN temple E2 ON (E1.salar>E2.salar)
WHERE  E2.numde=110 AND E1.numde<>110;