USE EMPRESA

/*Obtener el número de los empleados (su identificador único) que ganen más que
cualquiera de los empleados del departamento 110*/

--Vemos todos los empleados.
SELECT * FROM temple;

--Vemos los datos que nos interesa de los empleados del departamento 110.
SELECT numem, salar, numde
FROM temple
WHERE numde=110;

--Estos son los empleados cuyo salario es mayor que 1500 y mayor que 1800. 
SELECT *
FROM temple
WHERE salar > ALL (SELECT salar
				    FROM temple
   		            WHERE numde=110);

/*Si no queremos que salgan los empleados del departamento 110 vamos 
a poner un predicado compuesto utilizando el operador lógico AND*/
SELECT *
FROM temple
WHERE (salar > ALL (SELECT salar
				    FROM temple
   		            WHERE numde=110) ) AND numde <> 110;

--SOLUCIÓN:
SELECT numem
FROM temple
WHERE (salar > ALL (SELECT salar
				    FROM temple
   		            WHERE numde=110) ) AND numde <> 110;

--OTRAS FORMAS DE HACER EL EJEMPLO:

/*Si es mayor que el más grande, entonces lo será para todos*/
SELECT numem
FROM temple
WHERE salar > (SELECT MAX(salar) FROM temple WHERE numde=110) AND numde <> 110;

/*SELF JOIN:*/
--Producto cartesiano.
SELECT E1.numem,E1.salar,E2.numem,E2.salar, E2.numde
FROM temple E1, temple E2
WHERE  E2.numde=110 AND E1.numde<>110 AND (E1.salar> (SELECT MAX(salar) FROM temple WHERE numde=110) );

/*Eliminamos las repeticiones*/
SELECT DISTINCT E1.numem
FROM temple E1, temple E2
WHERE  E2.numde=110 AND E1.numde<>110 AND (E1.salar> (SELECT MAX(salar) FROM temple WHERE numde=110) );

