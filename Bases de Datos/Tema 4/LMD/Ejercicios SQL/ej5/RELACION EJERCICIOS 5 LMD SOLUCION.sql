USE empresa;

/*1. Para la sentencia INSERT, en qu� caso es
obligatorio poner el nombre de los campos.*/
/*Es obligatorio cuando no se introducen valores en
todos los campos. O dicho de otra manera, no es
obligatorio poner el nombre de los campos si
introducimos los valores de todos los campos de la fila
y adem�s en el mismo orden en el que est�n
especificadas las columnas*/

/*2. A�adir un nuevo centro. A�adir dos nuevos
departamentos
pertenecientes al centro anterior.*/
INSERT INTO tcentr (numce,nomce,se�as)
VALUES (100,'CENTRO NUEVO','C/NUEVA');
INSERT INTO tdepto
(numde,numce,direc,tidir,presu,depde,nomde)
VALUES(1000,100,110,'F',3000,121,'NUEVO_DEP1'),
(1001,100,110,'F',3000,121,'NUEVO_DEP2');
/*Siempre que hagas inserciones comprueba con SELECT que
se han realizado correctamente.*/
SELECT * FROM tcentr;
SELECT * FROM tdepto;

/*3.A�adir dos empleados a cada uno de los departamentos
creados anteriormente. Estos empleados nuevos no tienen
comisi�n y la fecha de ingreso es la fecha actual. Para
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
comisi�n. La instrucci�n debe estar dentro de una
transacci�n y cuando compruebes que la operaci�n se ha
realizado correctamente, debes deshacerla.*/
BEGIN TRANSACTION;
UPDATE temple
SET salar = salar - (salar * 0.05)
WHERE comis IS NOT NULL;
SELECT * FROM temple WHERE comis IS NOT NULL;
ROLLBACK TRANSACTION;

/* 5. Actualiza los presupuestos de todos los
departamentos multiplic�ndolos por 10. La instrucci�n
debe estar dentro de una transacci�n y cuando compruebes
que la operaci�n se ha realizado correctamente, debes
deshacerla.*/
BEGIN TRANSACTION;
UPDATE tdepto
SET presu = presu * 10;
SELECT * FROM tdepto;
ROLLBACK TRANSACTION;

/*6. Borrar a uno de los empleados nuevos (solo
conocemos su nombre). La instrucci�n debe estar dentro
de una transacci�n y cuando compruebes que la operaci�n
se ha realizado correctamente, debes deshacerla.*/
BEGIN TRANSACTION;
DELETE FROM temple
WHERE nomem LIKE 'ZAMORA,CARLA';
SELECT * FROM temple;
ROLLBACK TRANSACTION;

/* 7. Borrar los empleados pertenecientes a los
departamentos
ubicados en el centro que a�adiste en el ejercicio 2
(solo conocemos el nombre del centro). La instrucci�n
debe estar dentro de una transacci�n y cuando compruebes
que la operaci�n se ha realizado correctamente, debes
deshacerla. Realiza el ejercicio de diferentes
maneras.*/
/*Vemos que la informaci�n que nos interesa, concluyendo
en este caso que los empleados que debemos borrar son
los del departamento 1000 o 1001*/
SELECT * FROM tcentr WHERE nomce LIKE 'CENTRO NUEVO';
SELECT * FROM tdepto WHERE numce=100;
SELECT * FROM temple WHERE numde=1000 OR numde =1001;

--SOLUCIONES:
BEGIN TRANSACTION
/*Observa que podemos concatenar indicando una condici�n
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
direcci�n del centro). La instrucci�n debe estar dentro
de una transacci�n y cuando compruebes que la operaci�n
se ha realizado correctamente, debes confirmarla.
Realiza el ejercicio dos veces, primero usando
un subselect, y despu�s usando un JOIN.*/
BEGIN TRANSACTION
UPDATE tdepto
SET presu = presu - presu * 0.1
WHERE numce IN (SELECT numce
FROM tcentr
WHERE se�as LIKE 'C/NUEVA');
-- O bien,
UPDATE tdepto
SET presu = presu - presu * 0.1
FROM tdepto d JOIN tcentr c ON (d.numce=c.numce)
WHERE se�as LIKE 'C/NUEVA';
SELECT * FROM tdepto;
COMMIT WORK;

/* 9. Asignar a todos los empleados de los
departamentos cuyos nombres comienzan por la palabra
SECTOR y que no tengan comisi�n, la comisi�n m�s alta
del departamento de NOMINAS. La instrucci�n debe estar
dentro de una transacci�n y cuando compruebes que la
operaci�n se ha realizado correctamente, debes
deshacerla.*/
/*Vemos cu�les son los empleados de los departamentos
que empiezan por la palabra SECTOR y que no tienen
comisi�n*/
SELECT *
FROM temple
WHERE numde = ANY (SELECT numde
FROM tdepto WHERE nomde
LIKE 'SECTOR%')
AND comis IS NULL;
--Vemos la comisi�n m�s alta del departamento de NOMINAS
SELECT MAX(comis)
FROM temple
WHERE numde=(SELECT numde
FROM tdepto
WHERE nomde LIKE 'NOMINAS');
--SOLUCI�N:
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
--Otra SOLUCI�N:
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
departamento de PERSONAL. La instrucci�n debe estar
dentro de una transacci�n y cuando compruebes que la
operaci�n se ha realizado correctamente, debes
deshacerla. Utiliza un predicado BETWEEN. El salario
asignado debe tener solo dos decimales.*/
/*Vemos cu�les son los empleados a los que hay que
cambiarle el salario.*/
SELECT *
FROM temple
WHERE salar BETWEEN 1000 AND 1500;
/*Vemos cu�l es el salario medio del departamento de
PERSONAL.*/
SELECT AVG(salar)
FROM temple e JOIN tdepto d ON (e.numde=d.numde)
WHERE nomde LIKE 'PERSONAL';
--SOLUCI�N:
BEGIN TRANSACTION;
UPDATE temple
SET salar = (SELECT ROUND(AVG(SALAR),2,1)
FROM temple e JOIN tdepto d ON
(e.numde=d.numde)
WHERE nomde LIKE 'PERSONAL'
)
WHERE SALAR BETWEEN 1000 AND 1500;
ROLLBACK TRANSACTION;

/*11.Borrar al empleado 260 �Por qu� crees que te da
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
departamento 100 tendr�a un empleado como director que
no existe.
El empleado 260 es FK en tdepto. El empleado 260
pertenece al departamento 100 y adem�s es su director.*/

/*12. Borrar al empleado 180. La instrucci�n debe estar
dentro de una transacci�n y cuando compruebes que la
operaci�n se ha realizado correctamente, debes
deshacerla. Antes de borrar al empleado 180 comprueba
que no es jefe de ning�n departamento, es decir, que el
empleado 180 no est� como FK en la tabla tdepto.*/
BEGIN TRANSACTION;
SELECT *
FROM tdepto
WHERE direc=180;
DELETE FROM temple WHERE numem=180;
SELECT * FROM temple WHERE numem=180;
ROLLBACK TRANSACTION;

/*13. Obtener una tabla con los nombres, extensiones
telef�nicas y salarios, �nicamente de los empleados de
temple dados de alta en la empresa en el a�o actual.
Realiza de dos formas diferentes:
a. Utilizando solo la sentencia SELECT INTO
b. Utilizando La sentencia INSERT INTO SELECT. Crea
primeramente la tabla de la forma m�s r�pida posible.
*/
--Soluci�n con SELECT INTO:
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
--Soluci�n con INSERT INTO SELECT:
/*En primer lugar creo la tabla con la estructura que
necesitamos*/
SELECT nomem AS 'nombre',
extel AS 'tfno',
salar AS 'salario'
INTO emp_nuevos1
FROM temple
WHERE 0=1;
--Comprobamos la tabla y observamos que est� vac�a.
SELECT * FROM emp_nuevos1;

--A�adimos los datos:
INSERT INTO emp_nuevos1
SELECT nomem, extel, salar
FROM temple
WHERE YEAR(fecin)=YEAR(GETDATE());
--Comprobamos la tabla con los datos correctos.
SELECT * FROM emp_nuevos1;

/*14. Borrar todas las filas de las tablas creadas en el
ejercicio anterior. Realizar el ejercicio con la
sentencia DELETE para la primera tabla y la sentencia
TRUNCATE para la segunda �Cu�l es la diferencia?*/
DELETE FROM emp_nuevos;
TRUNCATE TABLE emp_nuevos1;
--Comprobamos que las tablas est�n ahora vac�as:
SELECT * FROM emp_nuevos;
SELECT * FROM emp_nuevos1;
/*La diferencia es que TRUNCATE quita todas las filas
de una tabla sin registrar las eliminaciones
individuales de filas.*/