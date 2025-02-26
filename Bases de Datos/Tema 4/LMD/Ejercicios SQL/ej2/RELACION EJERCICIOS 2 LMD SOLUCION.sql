USE EMPRESA;
/*1. Hallar el nombre de los empleados que no tienen
comisión, clasificados de manera
que aparezca primero aquellos nombres que son más
cortos.*/
SELECT nomem
FROM temple
WHERE comis IS NULL
ORDER BY LEN(nomem);

/*2. Hallar, por orden alfabético, los nombres de los
empleados suprimiendo las dos
últimas letras. Mirar en la ayuda el funcionamiento
de las funciones escalares de manejo
de cadena: substring y len.*/
SELECT SUBSTRING(nomem,1,LEN(nomem)-2) AS 'NOMBRE'
FROM temple
ORDER BY 1;

/*3. Hallar cuántos departamentos hay y el
presupuesto anual medio de ellos.*/
SELECT COUNT(numde) as 'Número de departamentos'
,AVG(presu)
FROM tdepto;
--Otra solución:
SELECT COUNT(*) as 'Número de departamentos'
,SUM(presu)/COUNT(*)
FROM tdepto;
/*4. Hallar la masa salarial anual (salario más
comisión) de la empresa
(se suponen 14 pagas anuales).*/
SELECT SUM(SALAR*14)+ SUM(comis*14)
FROM temple;
--Otra solución:
SELECT (SUM(SALAR)+ SUM(comis) )*14
FROM temple;

/*Observa que de estas dos maneras el resultado es
inferior e incorrecto.
El motivo es que al hacer una operación aritmética
con un valor
nulo el resultado es nulo.*/
SELECT SUM( (salar*14) + (comis*14) )
FROM temple;
SELECT SUM((salar + comis))*14
FROM temple;
/*Para hacer el ejercicio de esta manera y que el
resultado sea
correcto debemos utilizar la función ISNULL*/
SELECT SUM( (salar*14) + ISNULL((comis*14),0))
FROM temple;

/*5.Hallar la masa salarial anual (salario más
comisión) de cada
empleado (se suponen 14 pagas anuales).*/
/*Observamos que de la siguiente manera no sale un
valor correcto porque cuando la comisión es NULL, el
salario anual nos sale NULL.
El motivo se ha explicado en el ejercicio anterior.*/
SELECT nomem, (salar+comis)*14 AS 'SALARIO ANUAL'
FROM temple
ORDER BY 1;
/*Posibles soluciones*/
SELECT nomem,( salar + ISNULL(comis,0) ) * 14 AS
'SALARIO ANUAL'
FROM temple
ORDER BY 1;
SELECT nomem,(salar+comis)*14 AS 'SALARIO ANUAL'
FROM temple
WHERE comis IS NOT NULL
UNION
SELECT nomem,salar*14 AS 'SALARIO ANUAL'
FROM temple
WHERE comis IS NULL
ORDER BY 1;
SELECT nomem,( salar + IIF(comis IS NULL,0,comis) ) *
14 AS 'SALARIO ANUAL'
FROM temple
ORDER BY 1;
SELECT nomem, CASE WHEN comis IS NULL THEN salar * 14
ELSE (salar+comis)*14
END
AS 'SALARIO ANUAL'
FROM temple
ORDER BY 1;

/*6. Hallar cuántos empleados han ingresado
en el año actual. Utiliza la función year.*/
SELECT COUNT(*) AS 'Ingreso en el presente año'
FROM temple
WHERE YEAR(fecin)=YEAR(GETDATE());
--Otras soluciones:
SELECT COUNT(*) AS 'Ingreso en el presente año'
FROM temple
WHERE YEAR(GETDATE())-YEAR(fecin)=0;
SELECT COUNT(*) as 'Ingreso en el presente año'
FROM temple
WHERE YEAR(CURRENT_TIMESTAMP)-YEAR(fecin)=0;

/*7. Hallar la diferencia entre el salario más alto y
el salario más bajo.*/
SELECT MAX(salar)-MIN(salar)
FROM temple;