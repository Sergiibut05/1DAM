USE ejemploempresa;

/*Ejemplo de predicado básico donde los dos elementos de comparación son expresiones. 
numde es operando (nombre de columna) y 2 es operando (constante numérica)*/

SELECT nomem
FROM empleado
WHERE numde > 2;

/*Ejemplo de predicado básico donde el segundo elemento de comparación 
es un SELECT: Queremos saber el nombre de los empleados que están en 
un departamento con un código mayor que el código del 
departamento de Dep2*/

SELECT * FROM empleado;

SELECT numde FROM departamento WHERE nomde='Dep2';

--SOLUCIÓN:
SELECT nomem
FROM empleado
WHERE numde > (SELECT numde FROM departamento WHERE nomde='Dep2');

--También se podría haber hecho con producto cartesiano.
SELECT *
FROM empleado e,departamento d;

--SOLUCIÓN:
SELECT nomem
FROM empleado e,departamento d
WHERE d.nomde='Dep2' AND e.numde>d.numde;  

--También se podría haber hecho con concatenación o JOIN.
SELECT *
FROM empleado e JOIN departamento d ON (e.numde>d.numde);

--SOLUCIÓN:
SELECT nomem
FROM empleado e JOIN departamento d ON (e.numde>d.numde)
WHERE d.nomde='Dep2';



