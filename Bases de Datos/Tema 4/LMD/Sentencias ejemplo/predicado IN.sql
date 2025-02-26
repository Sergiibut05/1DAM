USE EMPRESA

/*Obtener los datos de los empleados que ganen igual que 
alguno de los empleados del departamento 111*/

--Vemos todos los empleados.
SELECT * FROM temple;

--Vemos los datos que nos interesa de los empleados del departamento 111.
SELECT numem,numde,salar 
FROM temple 
WHERE numde=111
ORDER BY 2;

--SOLUCIÓN:
--Si conocemos la información de la lista, la podemos poner.
SELECT *
FROM temple
WHERE salar IN (2200,1800) AND numde <> 111;

--SOLUCIÓN:
/*Si no conocemos lo que ganan los empleados del departamento 111, 
tendremos que usar IN con un subselect*/
SELECT *
FROM temple
WHERE (salar IN (SELECT salar
		         FROM temple
   		         WHERE numde=111) ) AND numde <> 111;

/*El resultado es equivalente a utilizar un predicado ANY 
con el operador relacional = .*/  
SELECT *
FROM temple
WHERE (salar = ANY(SELECT salar
		           FROM temple
   		           WHERE numde=111) ) AND numde <> 111;

--OTRAS FORMAS DE HACER EL EJEMPLO:
   		    
/*SELF JOIN:*/
--Producto cartesiano.   
SELECT E1.*
FROM temple E1, temple E2
WHERE E2.numde=111 AND E1.numde<>111 AND E2.salar=E1.salar;

--JOIN.
SELECT E1.*
FROM temple E1 JOIN temple E2 ON (E2.salar=E1.salar)
WHERE E2.numde=111 AND E1.numde<>111;
