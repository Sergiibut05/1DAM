/*Consultas con JOIN, predicados compuestos, subselects y predicados: IN,
ANY, ALL, BETWEEN, LIKE y EXISTS.*/

/*Relación de Ejercicios 3*/

USE EMPRESA;

/*1. Obtener por orden alfabético los nombres de los empleados cuyos salarios superen la mitad del
salario del empleado 180.*/
SELECT NOMEM
FROM temple
WHERE salar > (SELECT salar
			  FROM temple
			  WHERE NUMEM = 180)/2
ORDER BY 1 ASC

/*1. Obtener por orden alfabético los nombres de los empleados cuyos salarios superen la mitad del
salario del empleado 180.*/
SELECT NOMEM
FROM temple
WHERE SALAR > (SELECT SALAR
			   FROM temple
			   WHERE NUMEM = 180) / 2

/*2. Obtener por orden alfabético los nombres de los empleados cuyos salarios superen dos veces al
mínimo salario de los empleados del departamento 121.*/
SELECT NOMEM
FROM temple 
WHERE salar > (SELECT MIN(salar)
			   FROM temple
			   WHERE NUMDE = 121) * 2
ORDER BY 1 ASC;

/*2. Obtener por orden alfabético los nombres de los empleados cuyos salarios superen dos veces al
mínimo salario de los empleados del departamento 121.*/
SELECT NOMEM
FROM temple
WHERE SALAR > (SELECT MIN(SALAR)
			   FROM temple
			   WHERE NUMDE = 121) * 2
ORDER BY NOMEM ASC

/*3. Obtener por orden alfabético los nombres y los salarios de los empleados cuyo salario es inferior
a tres veces la comisión más baja existente distinta de NULL.*/
SELECT NOMEM, SALAR
FROM temple
WHERE SALAR < (SELECT MIN(COMIS) * 3
			   FROM temple
			   WHERE COMIS IS NOT NULL)
ORDER BY NOMEM ASC, SALAR ASC;

/*3. Obtener por orden alfabético los nombres y los salarios de los empleados cuyo salario es inferior
a tres veces la comisión más baja existente distinta de NULL.*/
SELECT NOMEM, SALAR
FROM temple
WHERE SALAR < (SELECT MIN(COMIS)
			   FROM temple
			   WHERE COMIS IS NOT NULL) * 3
ORDER BY NOMEM ASC, SALAR ASC;

/*4. Obtener, utilizando el predicado BETWEEN, por orden alfabético los números (identificador
único), los nombres y los salarios de los empleados con hijos cuyo salario dividido por su número
de hijos cumpla una, o ambas, de las dos condiciones siguientes:
• Que sea inferior a 1200 euros.
• Que sea superior al doble de su comisión.*/
SELECT NUMEM, NOMEM, SALAR
FROM temple
WHERE SALAR/NULLIF(NUMHI,0)NOT BETWEEN 1200 AND COMIS * 2
ORDER BY NOMEM ASC

/*4. Obtener, utilizando el predicado BETWEEN, por orden alfabético los números (identificador
único), los nombres y los salarios de los empleados con hijos cuyo salario dividido por su número
de hijos cumpla una, o ambas, de las dos condiciones siguientes:
• Que sea inferior a 1200 euros.
• Que sea superior al doble de su comisión.*/
SELECT NUMEM, NOMEM, SALAR
FROM temple
WHERE SALAR / NULLIF(NUMHI, 0) NOT BETWEEN 1200 AND COMIS * 2

/*5. Obtener por orden alfabético los nombres de los empleados cuyo primer apellido es Mora o
empieza por Mora.*/
SELECT NOMEM
FROM temple
WHERE NOMEM LIKE 'Mora%'

/*5. Obtener por orden alfabético los nombres de los empleados cuyo primer apellido es Mora o
empieza por Mora.*/
SELECT NOMEM
FROM temple
WHERE NOMEM LIKE 'Mora%'
ORDER BY NOMEM ASC

/*6. Obtener por orden alfabético los nombres de los empleados cuyo primer apellido termina en EZ y
su nombre de pila termina en O y tiene al menos tres letras.*/
SELECT NOMEM
FROM temple
WHERE NOMEM LIKE '%EZ, ___%o'
ORDER BY NOMEM;

/*6. Obtener por orden alfabético los nombres de los empleados cuyo primer apellido termina en EZ y
su nombre de pila termina en O y tiene al menos tres letras.*/
SELECT NOMEM
FROM temple
WHERE NOMEM LIKE '%ez, ___%o'

/*7. Obtener, utilizando el predicado IN, por orden alfabético los nombres de los empleados del
departamento 111 cuyo salario es igual a alguno de los salarios del departamento 112.
¿Cómo lo obtendrías con el predicado ANY?*/
SELECT nomem
FROM temple
WHERE numde = 111
  AND salar IN (SELECT salar FROM temple WHERE numde = 112)
ORDER BY nomem ASC;


SELECT nomem
FROM temple
WHERE numde = 111
  AND salar = ANY(SELECT salar FROM temple WHERE numde = 112)
ORDER BY nomem ASC;

/*7. Obtener, utilizando el predicado IN, por orden alfabético los nombres de los empleados del
departamento 111 cuyo salario es igual a alguno de los salarios del departamento 112.
¿Cómo lo obtendrías con el predicado ANY?*/
SELECT NOMEM
FROM temple
WHERE NUMDE = 111 
AND SALAR IN (SELECT SALAR
			  FROM temple
			  WHERE NUMDE = 112);

SELECT NOMEM
FROM temple
WHERE NUMDE = 111 
AND SALAR = ANY (SELECT SALAR
				 FROM temple
				 WHERE NUMDE = 112);

/*8. Obtener por orden alfabético los nombres y comisiones de los empleados del
departamento 110 si hay en él algún empleado que tenga comisión.*/
SELECT NOMEM, COMIS
FROM temple
WHERE NUMDE = 110 AND COMIS IS NOT NULL
ORDER BY NOMEM ASC

/*8. Obtener por orden alfabético los nombres y comisiones de los empleados del
departamento 110 si hay en él algún empleado que tenga comisión.*/
SELECT NOMEM, COMIS
FROM temple
WHERE NUMDE = 110 AND COMIS IS NOT NULL
ORDER BY NOMEM ASC;

	/*9. Obtener por orden alfabético los nombres de los departamentos que tienen algún empleado
	sin comisión. Hacer el ejercicio de cuatro formas diferentes:
	❑ Con predicado EXISTS.
	❑ Con predicado ANY
	❑ Con predicado IN
	❑ Usando JOIN.*/
SELECT DISTINCT d.NOMDE
FROM tdepto d
WHERE EXISTS (
    SELECT 1 
    FROM temple e 
    WHERE e.NUMDE = d.NUMDE 
    AND (e.COMIS IS NULL OR e.COMIS = 0)
)
ORDER BY d.NOMDE;

SELECT DISTINCT d.NOMDE
FROM tdepto d
WHERE d.NUMDE = ANY (
    SELECT e.NUMDE
    FROM temple e
    WHERE e.COMIS IS NULL OR e.COMIS = 0
)
ORDER BY d.NOMDE;

SELECT DISTINCT d.NOMDE
FROM tdepto d
WHERE d.NUMDE IN (
    SELECT e.NUMDE
    FROM temple e
    WHERE e.COMIS IS NULL OR e.COMIS = 0
)
ORDER BY d.NOMDE;

SELECT DISTINCT d.NOMDE
FROM tdepto d
JOIN temple e ON d.NUMDE = e.NUMDE
WHERE e.COMIS IS NULL OR e.COMIS = 0
ORDER BY d.NOMDE;

/*9. Obtener por orden alfabético los nombres de los departamentos que tienen algún empleado
	sin comisión. Hacer el ejercicio de cuatro formas diferentes:
	❑ Con predicado EXISTS.
	❑ Con predicado ANY
	❑ Con predicado IN
	❑ Usando JOIN.*/
SELECT DISTINCT d.NOMDE
FROM tdepto d
WHERE EXISTS (SELECT 1
			  FROM temple e
			  WHERE e.NUMDE = d.NUMDE
			  AND (e.COMIS IS NULL OR e.COMIS = 0))

SELECT d.NOMDE
FROM tdepto d
WHERE d.NUMDE IN (SELECT NUMDE
				  FROM temple
				  WHERE COMIS IS NULL OR COMIS = 0)

SELECT d.NOMDE
FROM tdepto d
WHERE d.NUMDE = ANY (SELECT NUMDE
				  FROM temple
				  WHERE COMIS IS NULL OR COMIS = 0)

SELECT DISTINCT NOMDE
FROM tdepto
JOIN temple ON tdepto.NUMDE = temple.NUMDE
WHERE COMIS IS NULL OR COMIS = 0;

/*10. Para los departamentos cuyo nombre empieza por las letras O o P, mostrar el nombre del
departamento y el nombre del departamento del que depende.*/
SELECT d1.NOMDE, d2.NOMDE
FROM tdepto d1
JOIN tdepto d2 ON d1.DEPDE = d2.NUMDE
WHERE d1.NOMDE LIKE 'O%' OR d1.NOMDE LIKE 'P%'

/*11. Para los departamentos del centro 20 obtener el nombre del departamento y el nombre del
director.*/
SELECT d.NOMDE, e.NOMEM
FROM tdepto d
JOIN temple e ON e.NUMEM = d.DIREC
JOIN tcentr c ON d.NUMCE = c.NUMCE
WHERE c.NUMCE = 20;

/*12. Obtener el nombre de los departamentos que no tienen empleados con menos de dos hijos.
Realiza la consulta primero con un predicado ALL y después con un predicado EXISTS.*/
SELECT d.NOMDE
FROM tdepto d
WHERE d.NUMDE = ALL (
    SELECT e.NUMDE
    FROM temple e
    WHERE e.NUMHI >= 2
);

SELECT d.NOMDE
FROM tdepto d
WHERE NOT EXISTS (SELECT 1
				  FROM temple e
				  WHERE NUMHI < 2 AND e.NUMDE = d.NUMDE
);