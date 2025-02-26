USE ejemploempresa;

/*Ejemplo de predicado b�sico donde los dos elementos de comparaci�n son expresiones. 
numde es operando (nombre de columna) y 2 es operando (constante num�rica)*/

SELECT nomem
FROM empleado
WHERE numde > 2;

/*Ejemplo de predicado b�sico donde el segundo elemento de comparaci�n 
es un SELECT: Queremos saber el nombre de los empleados que est�n en 
un departamento con un c�digo mayor que el c�digo del 
departamento de Dep2*/

SELECT * FROM empleado;

SELECT numde FROM departamento WHERE nomde='Dep2';

--SOLUCI�N:
SELECT nomem
FROM empleado
WHERE numde > (SELECT numde FROM departamento WHERE nomde='Dep2');

--Tambi�n se podr�a haber hecho con producto cartesiano.
SELECT *
FROM empleado e,departamento d;

--SOLUCI�N:
SELECT nomem
FROM empleado e,departamento d
WHERE d.nomde='Dep2' AND e.numde>d.numde;  

--Tambi�n se podr�a haber hecho con concatenaci�n o JOIN.
SELECT *
FROM empleado e JOIN departamento d ON (e.numde>d.numde);

--SOLUCI�N:
SELECT nomem
FROM empleado e JOIN departamento d ON (e.numde>d.numde)
WHERE d.nomde='Dep2';



