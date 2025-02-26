USE EMPRESA

/*Obtener el n�mero del departamento (su identificador �nico) Sector Servicios*/
--Vemos todos los empleados.
SELECT * FROM tdepto;

--SOLUCI�N: Usando un predicado b�sico.
SELECT numde
FROM tdepto
WHERE nomde = 'Sector Servicios';

/*Obtener el n�mero de los departamentos (su identificador �nico) cuyo
nombre de departamento empiece por la palabra Sector*/
SELECT numde
FROM tdepto
WHERE nomde LIKE 'Sector %';


