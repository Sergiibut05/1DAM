USE ejemploempresa;

--Veamos las tablas de departamento y empleados. 
SELECT * FROM departamento;
SELECT * FROM empleado;

/*Preparamos las tablas poniendo los departamentos
de los empleados 4 y 5 a NULL. Así esos dos empleados
no tienen departamento y a su vez el departamento 2 no tiene 
empleados*/

/*Ejecutemos las tres siguientes consultas, observando que me 
dan el mismo resultado.*/ 
SELECT *
FROM departamento D, empleado E
WHERE D.numde=E.numde;

/*JOIN Y INNER JOIN SON EQUIVALENTES*/
SELECT *
FROM departamento D JOIN empleado E ON (D.numde=E.numde);

SELECT *
FROM departamento D INNER JOIN empleado E ON (D.numde=E.numde);

/*Observemos que al juntar las dos tablas, ya sea con producto cartesiano o
con JOIN, el departamento Dep2 no aparece, porque su numde no aparece nunca 
en la tabla de empleado. A su vez, los empleados 4 y 5 tampoco aparecen 
porque su numde están a NULL. Por tanto:

-Si queremos obtener TODOS los departamentos con sus empleados, pero queremos
que aparezcan también los departamentos que no tienen empleados, debemos 
utilizar LEFT JOIN o RIGHT JOIN.

-Si queremos obtener TODOS los empleados con el departamento al que pertenecen, pero queremos
que aparezcan también los empleados que de momento no tienen asignado departamento,
debemos utilizar LEFT JOIN o RIGHT JOIN.

-Si queremos obtener TODOS los departamentos con sus empleados, pero queremos
que aparezcan también los departamentos que no tienen empleados, y además queremos
obtener TODOS los empleados con el departamento al que pertenecen, pero queremos
que aparezcan también los empleados que de momento no tienen asignado departamento, 
debemos utilizar FULL JOIN.
*/
--Veamos ejemplos:

/*Queremos sacar el nombre de los departamentos, junto con el nombre
 de los empleados que tienen. Si el departamento aún no tiene empleados,
 este debe salir igualmente.
 LEFT JOIN Y LEFT OUTER JOIN SON EQUIVALENTES
 */
SELECT nomde,nomem
FROM departamento D LEFT OUTER JOIN  empleado E ON (D.numde=E.numde);

SELECT nomde,nomem
FROM departamento D LEFT OUTER JOIN  empleado E ON (D.numde=E.numde);

/*PARA OBTENER ESTE MISMO RESULTADO, TAMBIÉN PODEMOS 
 INTERCAMBIAR LAS TABLAS Y PONER RIGHT EN VEZ DE LEFT*/
SELECT nomde,nomem
FROM  empleado E RIGHT OUTER JOIN  departamento D ON (D.numde=E.numde);

/* Queremos sacar el nombre de los empleados, junto con el nombre
 del departamento en el que está. Si el empleado aún no tiene asignado
 departamento, este debe salir igualmente. 
 RIGHT JOIN Y RIGHT OUTER JOIN SON EQUIVALENTES*/
SELECT nomem,nomde
FROM departamento D RIGHT JOIN  empleado E ON (D.numde=E.numde);

SELECT nomem,nomde
FROM departamento D RIGHT OUTER JOIN  empleado E ON (D.numde=E.numde);

/*PARA OBTENER ESTE MISMO RESULTADO, TAMBIÉN PODEMOS 
 INTERCAMBIAR LAS TABLAS Y PONER LEFT EN VEZ DE RIGTH*/
SELECT nomem,nomde
FROM empleado E LEFT OUTER JOIN  departamento D ON (D.numde=E.numde);

/* Queremos sacar todos los departamentos aunque no tengan empleados
y además queremos sacar a todos los empleados aunque no tengan 
asignado departamento.

FULL JOIN Y FULL OUTER JOIN SON EQUIVALENTES
*/
SELECT nomde,nomem
FROM departamento D FULL JOIN  empleado E ON (D.numde=E.numde);

SELECT nomde,nomem
FROM departamento D FULL OUTER JOIN  empleado E ON (D.numde=E.numde);




