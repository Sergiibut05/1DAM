USE EMPRESA

/*Obtener el número del departamento (su identificador único) Sector Servicios*/
--Vemos todos los empleados.
SELECT * FROM tdepto;

--SOLUCIÓN: Usando un predicado básico.
SELECT numde
FROM tdepto
WHERE nomde = 'Sector Servicios';

/*Obtener el número de los departamentos (su identificador único) cuyo
nombre de departamento empiece por la palabra Sector*/
SELECT numde
FROM tdepto
WHERE nomde LIKE 'Sector %';


