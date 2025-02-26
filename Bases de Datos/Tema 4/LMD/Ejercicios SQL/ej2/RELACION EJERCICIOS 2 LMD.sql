/*Consultas con alias, funciones y predicado NULL.

Relación de Ejercicios 2.*/

USE EMPRESA;

/*1. Hallar el nombre de los empleados que no tienen comisión, clasificados
   de manera que aparezca primero aquellos nombres que son más cortos.*/
SELECT NOMEM
FROM temple
WHERE COMIS IS NULL
ORDER BY LEN(NOMEM);

/*1. Hallar el nombre de los empleados que no tienen comisión, clasificados
   de manera que aparezca primero aquellos nombres que son más cortos.*/
SELECT NOMEM
FROM temple
WHERE COMIS IS NULL
ORDER BY LEN(NOMEM);

/*2. Hallar, por orden alfabético, los nombres de los empleados suprimiendo
   las dos últimas letras.*/
SELECT LEFT(NOMEM, LEN(NOMEM) - 2) AS NombreModificado
FROM temple
ORDER BY NOMEM;

/*2. Hallar, por orden alfabético, los nombres de los empleados suprimiendo
   las dos últimas letras.*/
SELECT LEFT(NOMEM, LEN(NOMEM) - 2)
FROM temple
ORDER BY NOMEM ASC

/*3. Hallar cuántos departamentos hay y el presupuesto anual medio de
   ellos.*/
SELECT COUNT(*) AS TotalDepartamentos, AVG(PRESU) AS PresupuestoMedio
FROM tdepto;

/*3. Hallar cuántos departamentos hay y el presupuesto anual medio de
   ellos.*/
SELECT COUNT(*), AVG(PRESU)
FROM tdepto

/*4. Hallar la masa salarial anual (salario más comisión) de la empresa (se
   suponen 14 pagas anuales).*/
SELECT SUM((SALAR + COALESCE(COMIS, 0)) * 14) AS MasaSalarialAnual
FROM temple;

/*5. Hallar la masa salarial anual (salario más comisión) de cada empleado
   (se suponen 14 pagas anuales).*/
SELECT NOMEM, (SALAR + COALESCE(COMIS, 0)) * 14 AS MasaSalarialAnual
FROM temple;

/*6. Hallar cuántos empleados han ingresado en el año actual.*/
SELECT COUNT(*) AS EmpleadosIngresados
FROM temple
WHERE YEAR(FECIN) = YEAR(GETDATE());

/*6. Hallar cuántos empleados han ingresado en el año actual.*/
SELECT COUNT(*)
FROM temple
WHERE YEAR(FECIN) = YEAR(GETDATE())

/*7. Hallar la diferencia entre el salario más alto y el salario más bajo.*/
SELECT MAX(SALAR) - MIN(SALAR) AS DiferenciaSalarial
FROM temple;

/*7. Hallar la diferencia entre el salario más alto y el salario más bajo.*/
SELECT MAX(SALAR) - MIN(SALAR)
FROM temple