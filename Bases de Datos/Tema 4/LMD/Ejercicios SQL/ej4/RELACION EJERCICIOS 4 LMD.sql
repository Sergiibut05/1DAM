/*Consultas con los predicado IN y ANY, cláusulas GROUP BY y HAVING, producto
cartesiano, JOIN, RIGTH JOIN, LEFT JOIN y FULL JOIN, funciones escalares y de
agregado.*/

USE EMPRESA

/*1. Para cada extensión telefónica, hallar cuantos empleados la usan y salario medio de
estos.*/
SELECT *
FROM temple;

SELECT EXTEL, COUNT(*) AS 'NUMERO PERSONAS POR EXTENSION', AVG(SALAR) AS 'SALARIO MEDIO'
FROM temple
GROUP BY EXTEL;

/*1. Para cada extensión telefónica, hallar cuantos empleados la usan y salario medio de
estos.*/
SELECT EXTEL, COUNT(*), AVG(SALAR)
FROM temple
GROUP BY EXTEL


/*2. Agrupando por departamento y número de hijos, hallar cuantos empleados hay en cada
grupo.*/
SELECT *
FROM temple
ORDER BY NUMDE;

SELECT NUMDE, NUMHI, COUNT(*) AS 'NUMERO EMPLEADOS POR DEPARTAMENTO CON MISMO NUMERO DE HIJOS'
FROM temple
GROUP BY NUMDE, NUMHI

/*2. Agrupando por departamento y número de hijos, hallar cuantos empleados hay en cada
grupo.*/
SELECT d.NOMDE, e.NUMHI, COUNT(*)
FROM temple e JOIN tdepto d ON d.NUMDE = e.NUMDE
GROUP BY d.NOMDE, e.NUMHI

/*3. Hallar por departamentos la edad en años cumplidos del empleado más mayor, así
como la edad media del mismo (el empleado debe tener comisión). Ordenar el
resultado por edades.*/
SELECT NUMDE, MAX(DATEDIFF(YEAR, e.FECNA, GETDATE())), AVG(DATEDIFF(YEAR, e.FECNA, GETDATE()))
FROM temple e
WHERE COMIS IS NULL OR COMIS = 0
GROUP BY NUMDE
ORDER BY MAX(DATEDIFF(YEAR, e.FECNA, GETDATE()));

/*4. Para los departamentos cuyo salario medio supera al de la empresa, hallar cuántas
extensiones telefónicas tienen. Se debe mostrar el número de departamento (numde) y
el número de extensiones telefónicas distintas que tiene cada uno de ellos.*/
SELECT e.NUMDE, COUNT(DISTINCT e.EXTEL)
FROM temple e
GROUP BY e.NUMDE
HAVING AVG(e.SALAR) > (SELECT AVG(SALAR) FROM temple)

/*5. Hallar el máximo valor de la suma de los salarios de los departamentos. Queremos
obtener número de departamento (numde) y la suma de sus salarios, pero del
departamento cuya suma de salarios es la mayor de todas.*/
SELECT NUMDE, SUM(SALAR) AS TOTAL_SALARIOS
FROM temple
GROUP BY NUMDE
HAVING SUM(SALAR) = (SELECT MAX(TOTAL)
					 FROM (SELECT NUMDE, SUM(SALAR) AS TOTAL
						   FROM temple
						   GROUP BY NUMDE) AS SUBQUERY);

/*5. Hallar el máximo valor de la suma de los salarios de los departamentos. Queremos
obtener número de departamento (numde) y la suma de sus salarios, pero del
departamento cuya suma de salarios es la mayor de todas.*/
SELECT NUMDE, SUM(SALAR)
FROM temple
GROUP BY NUMDE
HAVING SUM(SALAR) >= ALL (SELECT SUM(SALAR)
					 FROM temple
					 GROUP BY NUMDE);


/*6. Para cada departamento con presupuesto inferior a 10000 euros obtener el nombre, el
nombre del centro donde está ubicado y el máximo salario de sus empleados, si éste
excede de 1500 euros. Clasificar alfabéticamente por nombre de departamento. Hacer
el ejercicio de dos maneras: con producto cartesiano y con JOIN.*/
SELECT NOMDE, NOMCE, SALAR
FROM tdepto D JOIN tcentr C ON (D.NUMCE = C.NUMCE)
JOIN temple E ON (D.NUMDE = E.NUMDE)
WHERE D.PRESU < 10000
GROUP BY D.NOMDE, C.NOMCE, E.SALAR
HAVING MAX(E.SALAR) > 1500
ORDER BY D.NOMDE

/*6. Para cada departamento con presupuesto inferior a 10000 euros obtener el nombre, el
nombre del centro donde está ubicado y el máximo salario de sus empleados, si éste
excede de 1500 euros. Clasificar alfabéticamente por nombre de departamento. Hacer
el ejercicio de dos maneras: con producto cartesiano y con JOIN.*/
SELECT d.NOMDE, c.NOMCE, e.SALAR
FROM tdepto d JOIN tcentr c ON c.NUMCE = d.NUMCE
JOIN temple e ON e.NUMDE = d.NUMDE
WHERE d.PRESU < 10000
GROUP BY d.NOMDE, c.NOMCE, e.SALAR
HAVING MAX(e.SALAR) > 1500
ORDER BY d.NOMDE

/*7. Hallar por orden alfabético los nombres de los departamentos que dependen de los que
tienen un presupuesto inferior a 10000 euros. Mostrar el nombre del departamento y el
nombre del departamento del que dependen. Realizar la consulta de cuatro formas
distintas: con predicado IN, con predicado ANY, con producto cartesiano y con JOIN.*/
SELECT d1.NOMDE AS dep
FROM tdepto d1
WHERE d1.DEPDE IN (SELECT d2.NUMDE
				   FROM tdepto d2
				   WHERE d2.PRESU < 10000)

SELECT d1.NOMDE AS dep
FROM tdepto d1, tdepto d2
WHERE d1.DEPDE = ANY (SELECT d2.NUMDE
				   FROM tdepto d2
				   WHERE d2.PRESU < 10000)

SELECT d1.NOMDE AS dep, d2.NOMDE AS depPadre
FROM tdepto d1
JOIN tdepto d2 ON d1.DEPDE = d2.NUMDE
WHERE d2.PRESU < 10000

/*7. Hallar por orden alfabético los nombres de los departamentos que dependen de los que
tienen un presupuesto inferior a 10000 euros. Mostrar el nombre del departamento y el
nombre del departamento del que dependen. Realizar la consulta de cuatro formas
distintas: con predicado IN, con predicado ANY, con producto cartesiano y con JOIN.*/
SELECT d1.NOMDE
FROM tdepto d1 JOIN tdepto d2 ON d1.DEPDE = d2.NUMDE
WHERE d2.PRESU < 10000;

SELECT d1.NOMDE
FROM tdepto d1
WHERE d1.DEPDE IN (SELECT d2.NUMDE
				   FROM tdepto D2
				   WHERE d2.PRESU < 10000);

SELECT d1.NOMDE
FROM tdepto d1
WHERE d1.DEPDE = ANY (SELECT d2.NUMDE
				   FROM tdepto D2
				   WHERE d2.PRESU < 10000);

SELECT d1.NOMDE, d2.NOMDE
FROM tdepto d1, tdepto d2
WHERE d1.DEPDE = d2.NUMDE AND d2.PRESU < 10000;

/*8. Obtener por orden alfabético los nombres de los departamentos cuyo presupuesto es
inferior al 10 % de la suma de los salarios anuales de sus empleados (sin tener en
cuenta la comisión y son 14 pagas). Hacer el ejercicio con predicado básico y con
agrupamiento.*/
SELECT d.NOMDE AS departamente
FROM tdepto d
JOIN temple e ON d.numde = e.NUMDE
GROUP BY d.NOMDE, d.PRESU
HAVING d.PRESU < SUM(e.salar * 14) / 10
ORDER BY d.NOMDE

/*8. Obtener por orden alfabético los nombres de los departamentos cuyo presupuesto es
inferior al 10 % de la suma de los salarios anuales de sus empleados (sin tener en
cuenta la comisión y son 14 pagas). Hacer el ejercicio con predicado básico y con
agrupamiento.*/
SELECT d.NOMDE
FROM tdepto d JOIN temple e ON e.NUMDE = d.NUMDE
GROUP BY d.NOMDE, d.PRESU
HAVING d.PRESU < SUM(e.SALAR * 14) / 10

/*9. Ejecutar las siguientes sentencias:
 --Añadir los siguientes centros:
INSERT INTO tcentr (numce,nomce,señas)
VALUES (30,'PRODUCCIÓN','C. DEL ARTE, 13, MADRID'),
(40,'INNOVACIÓN','AVDA. ANDALUCÍA, 20, MÁLAGA');
--Añadir los siguientes departamentos:
INSERT INTO tdepto(numde,numce,direc,tidir,presu,depde,nomde)
VALUES (122,NULL,NULL,'F',10000,120,'MARKETING Y PUBLICIDAD'),
(123,20, NULL,'F',10000,120,'COMPRAS Y LOGÍSTICA');
--Añadir los siguientes empleados:
INSERT INTO temple(numem,numde,extel,fecna,fecin,salar,comis,numhi,nomem)
VALUES (381,122, 350,'12/03/2000','8/1/2025',1800,100,0,'ROMERO, MÍRIAM'),
 (382,122, 350,'13/04/1998','8/1/2025',1800,100,1,'SÁNCHEZ, LUCÍA'),
 (383,NULL,350,'14/05/1997','8/1/2025',1800,100,1,'LÓPEZ, LAURA');
--Asignar el empleado 381 como director del departamento 122.
Una vez ejecutadas estas sentencias, consultar las tablas tcentr, tdepto y temple por
separado para comprobar que tenemos:
• Los centros 30 y 40 que aún no tienen departamentos ubicados en los mismos.*
• El departamento 123 que aún no tiene empleados.
• El empleado 383 que aún no se le ha asignado departamento.
• El departamento 122 que aún no se le ha asignado centro. *
• El departamento 120 (ya existía) y el 123 que aún no tienen directores.
• El departamento 100 (ya existía) que no depende de ningún otro.*/