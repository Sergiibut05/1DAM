/*INSERT INTO, DELETE FROM y UPDATE*/

/*Relación de Ejercicios 5*/

USE EMPRESA;

/*1. Para la sentencia INSERT, en qué caso es obligatorio poner el nombre de los campos.*/


/*2. Añadir un nuevo centro. Añadir dos nuevos departamentos pertenecientes al centro anterior.*/
SELECT *
FROM tcentr;
INSERT INTO tcentr
VALUES(50, 'Betis', 'AVDA. DE LA PALMERA, 20, Sevilla');
SELECT *
FROM tdepto;
INSERT INTO tdepto
VALUES(17, 50, NULL, 'F', 1, NULL, 'JOAQUÍN'), (22, 50, NULL, 'P', 12.3, 110, 'ISCO')

/*3. Añadir dos empleados a cada uno de los departamentos creados anteriormente. Estos
empleados nuevos no tienen comisión y la fecha de ingreso es la fecha actual. Para realizar
este ejercicio utilizar solo dos sentencias INSERT.*/
INSERT INTO temple
VALUES(400, 17, 170, 1965-11-20, GETDATE(), 10, NULL, 0, 'LOS SANTOS, ANTHONY'), (410, 17, 170, 1965-11-20, GETDATE(), 20, NULL, 0, 'HERNANDEZ, CUCHO');

INSERT INTO temple
VALUES(500, 22, 170, 1965-11-20, GETDATE(), 10, NULL, 0, 'RODRIGUEZ, JESÚS'), (510, 22, 170, 1965-11-20, GETDATE(), 20, NULL, 0, 'ORTÍZ, ÁNGEL');

SELECT *
FROM temple;

/*4. Bajar los salarios un 5% a todos los empleados con comisión. La instrucción debe estar
dentro de una transacción y cuando compruebes que la operación se ha realizado
correctamente, debes deshacerla.*/
BEGIN TRANSACTION

UPDATE temple
SET SALAR = SALAR * 0.95
WHERE COMIS IS NOT NULL;

ROLLBACK TRANSACTION

/*5. Actualiza los presupuestos de todos los departamentos multiplicándolos por 10. La
instrucción debe estar dentro de una transacción y cuando compruebes que la operación se
ha realizado correctamente, debes deshacerla.*/
BEGIN TRANSACTION

UPDATE tdepto
SET PRESU = PRESU * 10

ROLLBACK TRANSACTION

/*6. Borrar a uno de los empleados nuevos (solo conocemos su nombre). La instrucción debe
estar dentro de una transacción y cuando compruebes que la operación se ha realizado
correctamente, debes deshacerla.*/
BEGIN TRANSACTION

DELETE FROM temple
WHERE NOMEM = 'HERNANDEZ, CUCHO'; 

ROLLBACK TRANSACTION

/*7. Borrar los empleados pertenecientes a los departamentos ubicados en el centro que
añadiste en el ejercicio 2 (solo conocemos el nombre del centro). La instrucción debe estar
dentro de una transacción y cuando compruebes que la operación se ha realizado
correctamente, debes deshacerla. Realiza el ejercicio de diferentes maneras.*/
BEGIN TRANSACTION

DELETE temple FROM temple
JOIN tdepto ON tdepto.NUMDE = temple.NUMDE
JOIN tcentr ON tcentr.NUMCE = tdepto.NUMCE
WHERE tcentr.NOMCE = 'BETIS'

DELETE FROM temple
WHERE NUMDE IN (
	SELECT NUMDE FROM tdepto
	WHERE NUMCE IN (
		SELECT NUMCE FROM tcentr WHERE NOMCE = 'BETIS'
	)
);

DELETE FROM temple
WHERE NUMDE = ANY (
	SELECT NUMDE FROM tdepto
	WHERE NUMCE = ANY (
		SELECT NUMCE FROM tcentr WHERE NOMCE = 'BETIS'
	)
);

DELETE FROM temple
WHERE EXISTS (
	SELECT 1 FROM tdepto
	WHERE tdepto.NUMDE = temple.NUMDE
	AND EXISTS (
		SELECT 1 FROM tcentr
		WHERE tcentr.NUMCE = tdepto.NUMCE
		AND tcentr.NOMCE = 'BETIS'
	)
);

ROLLBACK TRANSACTION

/*8. Disminuir en un 10% el presupuesto de los departamentos del nuevo centro (solo
conocemos la dirección del centro). La instrucción debe estar dentro de una transacción y
cuando compruebes que la operación se ha realizado correctamente, debes confirmarla.
Realiza el ejercicio dos veces, primero usando un subselect, y después usando un JOIN.*/
BEGIN TRANSACTION

UPDATE tdepto
SET tdepto.PRESU = tdepto.PRESU * 0.9
FROM tdepto
INNER JOIN tcentr ON tdepto.NUMCE = tcentr.NUMCE
WHERE tcentr.SEÑAS = 'AVDA. DE LA PALMERA, 20, Sevilla'

ROLLBACK TRANSACTION

/*9. Asignar a todos los empleados de los departamentos cuyos nombres comienzan por la
palabra SECTOR y que no tengan comisión, la comisión más alta del departamento de
NOMINAS. La instrucción debe estar dentro de una transacción y cuando compruebes que la
operación se ha realizado correctamente, debes deshacerla.*/
BEGIN TRANSACTION

UPDATE temple
SET COMIS = (
	SELECT MAX(COMIS)
	FROM temple t
	WHERE t.NUMDE = (SELECT NUMDE FROM tdepto WHERE tdepto.NOMDE = 'NOMINAS')
)
FROM temple 
JOIN tdepto ON temple.NUMDE = tdepto.NUMDE
WHERE tdepto.NOMDE LIKE 'SECTOR %' AND temple.COMIS IS NULL;

ROLLBACK TRANSACTION

/*10. Asignar a los empleados con salarios comprendidos entre 1000 y 1500 euros el salario
medio del departamento de PERSONAL. La instrucción debe estar dentro de una
transacción y cuando compruebes que la operación se ha realizado correctamente, debes
deshacerla. Utiliza un predicado BETWEEN. El salario asignado debe tener solo dos
decimales.*/
BEGIN TRANSACTION

UPDATE e1
SET e1.SALAR = (
	SELECT ROUND(AVG(e.SALAR), 2)
	FROM temple e JOIN tdepto d ON d.NUMDE = e.NUMDE
	WHERE d.NOMDE = 'PERSONAL'
)
FROM temple e1
WHERE e1.SALAR BETWEEN 1000 AND 1500

ROLLBACK TRANSACTION

/*11. Borrar al empleado 260 ¿Por qué crees que te da error al intentarlo?*/
DELETE FROM temple
WHERE NUMEM = 260

/*12. Borrar al empleado 180. La instrucción debe estar dentro de una transacción y cuando
compruebes que la operación se ha realizado correctamente, debes deshacerla. Antes de
borrar al empleado 180 comprueba que no es jefe de ningún departamento, es decir, que el
empleado 180 no está como FK en la tabla tdepto.*/
SELECT * FROM tdepto

BEGIN TRANSACTION

DELETE FROM temple
WHERE NUMEM = 180

ROLLBACK TRANSACTION


/*13. Obtener una tabla con los nombres, extensiones telefónicas y salarios, únicamente de los
empleados de temple dados de alta en la empresa en el año actual. Realiza de dos formas
diferentes:
a. Utilizando solo la sentencia SELECT INTO*/
SELECT EXTEL, SALAR
INTO newTable
FROM temple
WHERE YEAR(FECIN) = YEAR(GETDATE());

SELECT * FROM temple

SELECT *
FROM newTable

/*b. Utilizando La sentencia INSERT INTO SELECT. Crea primeramente la tabla de la
forma más rápida posible.*/
SELECT EXTEL, SALAR
INTO newTable3
FROM temple
WHERE 1 = 0;

INSERT INTO newTable3
SELECT EXTEL, SALAR
FROM temple
WHERE YEAR(FECIN) = YEAR(GETDATE());

SELECT * FROM newTable3

/*14. Borrar todas las filas de las tablas creadas en el ejercicio anterior. Realizar el ejercicio
con la sentencia DELETE para la primera tabla y la sentencia TRUNCATE para la segunda
¿Cuál es la diferencia?*/
DELETE FROM newTable
WHERE 1 = 1;
SELECT * FROM newTable

TRUNCATE TABLE newTable3
SELECT * FROM newTable