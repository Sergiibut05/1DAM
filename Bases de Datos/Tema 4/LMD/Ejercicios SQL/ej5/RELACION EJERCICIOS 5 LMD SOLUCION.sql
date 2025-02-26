USE empresa;

/*1. Para la sentencia INSERT, en qué caso es
obligatorio poner el nombre de los campos.*/
/*Es obligatorio cuando no se introducen valores en
todos los campos. O dicho de otra manera, no es
obligatorio poner el nombre de los campos si
introducimos los valores de todos los campos de la fila
y además en el mismo orden en el que están
especificadas las columnas*/

/*2. Añadir un nuevo centro. Añadir dos nuevos
departamentos
pertenecientes al centro anterior.*/
INSERT INTO tcentr (numce,nomce,señas)
VALUES (100,'CENTRO NUEVO','C/NUEVA');
INSERT INTO tdepto
(numde,numce,direc,tidir,presu,depde,nomde)
VALUES(1000,100,110,'F',3000,121,'NUEVO_DEP1'),
(1001,100,110,'F',3000,121,'NUEVO_DEP2');
/*Siempre que hagas inserciones comprueba con SELECT que
se han realizado correctamente.*/
SELECT * FROM tcentr;
SELECT * FROM tdepto;

/*3.Añadir dos empleados a cada uno de los departamentos
creados anteriormente. Estos empleados nuevos no tienen
comisión y la fecha de ingreso es la fecha actual. Para
realizar este ejercicio utilizar solo dos sentencias
INSERT.
*/
INSERT INTO temple
(numem,numde,extel,fecna,fecin,salar,comis,numhi,nomem)
VALUES
(410,1000,500,'10/04/1997',GETDATE(),1500,NULL,1,'ZAMBRA
NO,CARLOS'),
(420,1000,500,'11/05/1998',GETDATE(),1500,NULL,2,'ZAMORA
,CARLA');
INSERT INTO temple
(numem,numde,extel,fecna,fecin,salar,comis,numhi,nomem)
VALUES(430,1001,600,'12/06/1999',GETDATE(),1800,NULL,1,'
SOLER,JUAN'),
(440,1001,600,'13/07/2000',GETDATE(),1800,NULL,0,'SOLERA
,JUANA');
SELECT * FROM temple;

/*4. Bajar los salarios un 5% a todos los empleados con
comisión. La instrucción debe estar dentro de una
transacción y cuando compruebes que la operación se ha
realizado correctamente, debes deshacerla.*/
BEGIN TRANSACTION;
UPDATE temple
SET salar = salar - (salar * 0.05)
WHERE comis IS NOT NULL;
SELECT * FROM temple WHERE comis IS NOT NULL;
ROLLBACK TRANSACTION;

/* 5. Actualiza los presupuestos de todos los
departamentos multiplicándolos por 10. La instrucción
debe estar dentro de una transacción y cuando compruebes
que la operación se ha realizado correctamente, debes
deshacerla.*/
BEGIN TRANSACTION;
UPDATE tdepto
SET presu = presu * 10;
SELECT * FROM tdepto;
ROLLBACK TRANSACTION;

/*6. Borrar a uno de los empleados nuevos (solo
conocemos su nombre). La instrucción debe estar dentro
de una transacción y cuando compruebes que la operación
se ha realizado correctamente, debes deshacerla.*/
BEGIN TRANSACTION;
DELETE FROM temple
WHERE nomem LIKE 'ZAMORA,CARLA';
SELECT * FROM temple;
ROLLBACK TRANSACTION;

/* 7. Borrar los empleados pertenecientes a los
departamentos
ubicados en el centro que añadiste en el ejercicio 2
(solo conocemos el nombre del centro). La instrucción
debe estar dentro de una transacción y cuando compruebes
que la operación se ha realizado correctamente, debes
deshacerla. Realiza el ejercicio de diferentes
maneras.*/
/*Vemos que la información que nos interesa, concluyendo
en este caso que los empleados que debemos borrar son
los del departamento 1000 o 1001*/
SELECT * FROM tcentr WHERE nomce LIKE 'CENTRO NUEVO';
SELECT * FROM tdepto WHERE numce=100;
SELECT * FROM temple WHERE numde=1000 OR numde =1001;

--SOLUCIONES:
BEGIN TRANSACTION
/*Observa que podemos concatenar indicando una condición
compuesta*/
DELETE FROM temple
WHERE numde IN (SELECT d.numde
FROM tdepto d JOIN tcentr c
ON (d.numce=c.numce AND c.nomce LIKE
'CENTRO NUEVO')
);
-- O bien,
DELETE FROM temple
WHERE numde = ANY (SELECT numde
FROM tdepto d JOIN tcentr c
ON (d.numce=c.numce)
WHERE C.nomce LIKE 'CENTRO NUEVO'
);
-- O bien,
DELETE FROM temple
WHERE numde = ANY (SELECT numde
FROM tdepto
WHERE numce=(SELECT numce
FROM tcentr
WHERE nomce
LIKE 'CENTRO NUEVO'
)
);
-- O bien,
DELETE FROM temple
FROM ( temple e JOIN tdepto d ON (e.numde=d.numde) )
JOIN tcentr c ON (c.numce=d.numce)
WHERE nomce LIKE 'CENTRO NUEVO';
ROLLBACK TRANSACTION;

/* 8. Disminuir en un 10% el presupuesto de los
departamentos del nuevo centro (solo conocemos la
dirección del centro). La instrucción debe estar dentro
de una transacción y cuando compruebes que la operación
se ha realizado correctamente, debes confirmarla.
Realiza el ejercicio dos veces, primero usando
un subselect, y después usando un JOIN.*/
BEGIN TRANSACTION
UPDATE tdepto
SET presu = presu - presu * 0.1
WHERE numce IN (SELECT numce
FROM tcentr
WHERE señas LIKE 'C/NUEVA');
-- O bien,
UPDATE tdepto
SET presu = presu - presu * 0.1
FROM tdepto d JOIN tcentr c ON (d.numce=c.numce)
WHERE señas LIKE 'C/NUEVA';
SELECT * FROM tdepto;
COMMIT WORK;

/* 9. Asignar a todos los empleados de los
departamentos cuyos nombres comienzan por la palabra
SECTOR y que no tengan comisión, la comisión más alta
del departamento de NOMINAS. La instrucción debe estar
dentro de una transacción y cuando compruebes que la
operación se ha realizado correctamente, debes
deshacerla.*/
/*Vemos cuáles son los empleados de los departamentos
que empiezan por la palabra SECTOR y que no tienen
comisión*/
SELECT *
FROM temple
WHERE numde = ANY (SELECT numde
FROM tdepto WHERE nomde
LIKE 'SECTOR%')
AND comis IS NULL;
--Vemos la comisión más alta del departamento de NOMINAS
SELECT MAX(comis)
FROM temple
WHERE numde=(SELECT numde
FROM tdepto
WHERE nomde LIKE 'NOMINAS');
--SOLUCIÓN:
BEGIN TRANSACTION;
UPDATE temple
SET comis = (SELECT MAX(comis)
FROM temple
WHERE numde=(SELECT numde
FROM tdepto
WHERE nomde LIKE 'NOMINAS')
)
WHERE numde = ANY (SELECT numde FROM tdepto WHERE nomde
LIKE 'SECTOR%') AND comis IS NULL;
ROLLBACK TRANSACTION;
--Para evitar la advertencia debemos poner
--MAX(ISNULL(COMIS,0))
--Otra SOLUCIÓN:
BEGIN TRANSACTION;
UPDATE temple
SET comis = (SELECT MAX(comis)
FROM temple e JOIN tdepto d ON
(e.numde=d.numde)
WHERE nomde LIKE 'NOMINAS'
)
FROM temple e JOIN tdepto d ON (e.numde=d.numde)
WHERE nomde LIKE 'SECTOR%' AND comis IS NULL;
ROLLBACK TRANSACTION;

/* 10. Asignar a los empleados con salarios comprendidos
entre 1000 y 1500 euros el salario medio del
departamento de PERSONAL. La instrucción debe estar
dentro de una transacción y cuando compruebes que la
operación se ha realizado correctamente, debes
deshacerla. Utiliza un predicado BETWEEN. El salario
asignado debe tener solo dos decimales.*/
/*Vemos cuáles son los empleados a los que hay que
cambiarle el salario.*/
SELECT *
FROM temple
WHERE salar BETWEEN 1000 AND 1500;
/*Vemos cuál es el salario medio del departamento de
PERSONAL.*/
SELECT AVG(salar)
FROM temple e JOIN tdepto d ON (e.numde=d.numde)
WHERE nomde LIKE 'PERSONAL';
--SOLUCIÓN:
BEGIN TRANSACTION;
UPDATE temple
SET salar = (SELECT ROUND(AVG(SALAR),2,1)
FROM temple e JOIN tdepto d ON
(e.numde=d.numde)
WHERE nomde LIKE 'PERSONAL'
)
WHERE SALAR BETWEEN 1000 AND 1500;
ROLLBACK TRANSACTION;

/*11.Borrar al empleado 260 ¿Por qué crees que te da
error al intentarlo?*/
/*Comprobamos que al intentar borrar al empleado 260 nos
da error.*/
DELETE FROM temple WHERE numem=260;
/*Vemos que el empleado 260 pertenece al departamento
100*/
SELECT * FROM temple ORDER BY numde;
/*Vemos en la tabla de departamento que el jefe del
departamento 100
es el empleado 260*/
SELECT * FROM tdepto;

/*Por tanto, si me dejara borrar al empleado 260, el
departamento 100 tendría un empleado como director que
no existe.
El empleado 260 es FK en tdepto. El empleado 260
pertenece al departamento 100 y además es su director.*/

/*12. Borrar al empleado 180. La instrucción debe estar
dentro de una transacción y cuando compruebes que la
operación se ha realizado correctamente, debes
deshacerla. Antes de borrar al empleado 180 comprueba
que no es jefe de ningún departamento, es decir, que el
empleado 180 no está como FK en la tabla tdepto.*/
BEGIN TRANSACTION;
SELECT *
FROM tdepto
WHERE direc=180;
DELETE FROM temple WHERE numem=180;
SELECT * FROM temple WHERE numem=180;
ROLLBACK TRANSACTION;

/*13. Obtener una tabla con los nombres, extensiones
telefónicas y salarios, únicamente de los empleados de
temple dados de alta en la empresa en el año actual.
Realiza de dos formas diferentes:
a. Utilizando solo la sentencia SELECT INTO
b. Utilizando La sentencia INSERT INTO SELECT. Crea
primeramente la tabla de la forma más rápida posible.
*/
--Solución con SELECT INTO:
SELECT nomem AS 'nombre',
extel AS 'tfno',
salar AS 'salario'
INTO emp_nuevos
FROM temple
WHERE YEAR(fecin)=YEAR(GETDATE());
/*Comprobamos la tabla con los datos correctos
y observamos que hemos puesto nuevos nombres a los
campos:*/
SELECT * FROM emp_nuevos;
--Solución con INSERT INTO SELECT:
/*En primer lugar creo la tabla con la estructura que
necesitamos*/
SELECT nomem AS 'nombre',
extel AS 'tfno',
salar AS 'salario'
INTO emp_nuevos1
FROM temple
WHERE 0=1;
--Comprobamos la tabla y observamos que está vacía.
SELECT * FROM emp_nuevos1;

--Añadimos los datos:
INSERT INTO emp_nuevos1
SELECT nomem, extel, salar
FROM temple
WHERE YEAR(fecin)=YEAR(GETDATE());
--Comprobamos la tabla con los datos correctos.
SELECT * FROM emp_nuevos1;

/*14. Borrar todas las filas de las tablas creadas en el
ejercicio anterior. Realizar el ejercicio con la
sentencia DELETE para la primera tabla y la sentencia
TRUNCATE para la segunda ¿Cuál es la diferencia?*/
DELETE FROM emp_nuevos;
TRUNCATE TABLE emp_nuevos1;
--Comprobamos que las tablas están ahora vacías:
SELECT * FROM emp_nuevos;
SELECT * FROM emp_nuevos1;
/*La diferencia es que TRUNCATE quita todas las filas
de una tabla sin registrar las eliminaciones
individuales de filas.*/