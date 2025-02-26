/*Consultas con expresiones aritm�ticas y predicados b�sicos. Consultas b�sicas de
producto cartesiano y join.

Relaci�n de Ejercicios 1.*/

USE EMPRESA

/*1. Hallar por orden alfab�tico los nombres de los departamentos cuyo director est� en funciones.*/
SELECT NOMDE 
FROM tdepto 
WHERE TIDIR = 'F' 
ORDER BY NOMDE;

/*1. Hallar por orden alfab�tico los nombres de los departamentos cuyo director est� en funciones.*/
SELECT d.NOMDE AS departamento
FROM tdepto d
WHERE TIDIR='F'
ORDER BY NOMDE ASC

/*2. Obtener un list�n telef�nico de los empleados del departamento 121 incluyendo nombre del empleado,
n�mero del empleado y extensi�n telef�nica. Por orden alfab�tico descendente.*/
SELECT NOMEM, NUMEM, EXTEL
FROM temple
WHERE NUMDE = 121
ORDER BY NOMEM DESC;

/*2. Obtener un list�n telef�nico de los empleados del departamento 121 incluyendo nombre del empleado,
n�mero del empleado y extensi�n telef�nica. Por orden alfab�tico descendente.*/
SELECT d.NOMEM, d.NUMDE, d.EXTEL
FROM temple d
WHERE d.NUMDE = 121
ORDER BY NOMEM DESC

/*3. Obtener por orden creciente una relaci�n de todos los n�meros de extensiones telef�nicas de los
empleados (elimina las repeticiones).*/
SELECT DISTINCT EXTEL 
FROM temple
ORDER BY EXTEL ASC;

/*3. Obtener por orden creciente una relaci�n de todos los n�meros de extensiones telef�nicas de los
empleados (elimina las repeticiones).*/
SELECT DISTINCT e.EXTEL
from temple e
ORDER BY EXTEL ASC

/*4. Hallar el nombre, salario y la comisi�n de los empleados con m�s de un hijo, clasificados por comisi�n, y
dentro de la comisi�n por orden alfab�tico. El listado debe incluir tambi�n los empleados con m�s de un hijo
aunque no tengan comisi�n. Utilizar la funci�n ISNULL para que cuando la comisi�n NULL muestre un 0.
Utiliza alias para los nombres de las columnas.*/
SELECT NOMEM AS Nombre, SALAR AS Salario, ISNULL(COMIS, 0) AS Comision
FROM temple
WHERE NUMHI > 1
ORDER BY Comision, NOMEM;

/*4. Hallar el nombre, salario y la comisi�n de los empleados con m�s de un hijo, clasificados por comisi�n, y
dentro de la comisi�n por orden alfab�tico. El listado debe incluir tambi�n los empleados con m�s de un hijo
aunque no tengan comisi�n. Utilizar la funci�n ISNULL para que cuando la comisi�n NULL muestre un 0.
Utiliza alias para los nombres de las columnas.*/
SELECT e.NOMEM AS Nombre, e.SALAR AS Salario, ISNULL(e.COMIS, 0) AS Comision
FROM temple e
WHERE NUMHI > 1
ORDER BY Comision, Nombre

/*5. Obtener salario y nombre de los empleados con dos hijos por orden decreciente de salario y por orden
alfab�tico dentro del salario.*/
SELECT SALAR, NOMEM
FROM temple
WHERE NUMHI = 2
ORDER BY SALAR DESC, NOMEM;

/*5. Obtener salario y nombre de los empleados con dos hijos por orden decreciente de salario y por orden
alfab�tico dentro del salario.*/
SELECT e.SALAR, e.NOMEM
FROM temple e
WHERE NUMHI = 2
ORDER BY SALAR DESC, NOMEM ASC

/*6. Obtener el nombre de los empleados cuya comisi�n es superior o igual al 50% de su salario, por orden
alfab�tico.*/
SELECT NOMEM 
FROM temple
WHERE COMIS >= SALAR * 0.50
ORDER BY NOMEM;

/*7. A. En una campa�a de ayuda familiar se ha decidido dar a los empleados una paga extra de 30 euros por
hijo, a partir del tercero inclusive. Obtener por orden alfab�tico para estos empleados: nombre y salario total
que van a cobrar incluyendo esta paga extra.*/
SELECT NOMEM, (SALAR + (NUMHI - 2) * 30)
FROM temple
WHERE NUMHI >= 3
ORDER BY NOMEM;

/*8. Igual que el ejercicio anterior, pero mostrar tambi�n el nombre y el salario que ganan el resto de los
empleados (los que tienen 0, 1 o 2 hijos). Resuelve el ejercicio de dos formas diferentes: con el operador
UNION y con una expresi�n CASE. Consulta en el Manual SQL w3schools �SQL Union� y �SQL Case�.*/
SELECT NOMEM AS Nombre, (SALAR + (NUMHI - 2) * 30) AS SalarioTotal
FROM temple
WHERE NUMHI >= 3
UNION
SELECT NOMEM AS Nombre, SALAR AS SalarioTotal
FROM temple
WHERE NUMHI < 3
ORDER BY Nombre;

/*9. Hallar por orden alfab�tico los nombres de los empleados, tales que si se les da una gratificaci�n de 60
euros por hijo, esta gratificaci�n no supera a la d�cima parte de su salario.*/
SELECT NOMEM AS Nombre, NUMHI AS NumeroHijos, SALAR AS Salario
FROM temple
WHERE NUMHI*60 < SALAR/10

/*10. Obtener el nombre de cada centro, junto con el nombre de los departamentos que tiene. Ordena
ascendentemente por nombre de centro y a igual nombre de centro ordena por nombre de departamento.*/
SELECT tcentr.NOMCE AS NombreCentro, tdepto.NOMDE AS NombreDepartamento
FROM tcentr
JOIN tdepto ON tcentr.NUMCE = tdepto.NUMCE
ORDER BY tcentr.NOMCE ASC, tdepto.NOMDE ASC;

/*10. Obtener el nombre de cada centro, junto con el nombre de los departamentos que tiene. Ordena
ascendentemente por nombre de centro y a igual nombre de centro ordena por nombre de departamento.*/
SELECT c.NOMCE, NOMDE
FROM tcentr c
JOIN tdepto ON c.NUMCE = tdepto.NUMCE
ORDER BY NOMCE ASC, NOMDE ASC

/*11. Obtener ordenadamente el nombre de cada departamento junto con el nombre de cada empleado que tiene.*/
SELECT tdepto.NOMDE AS NombreDepartamento, temple.NOMEM AS NombreEmpleadp
FROM temple
JOIN tdepto ON temple.NUMDE = tdepto.NUMDE

/*12. Obtener ordenadamente el nombre de cada centro, junto con el nombre de los departamentos que tiene y el
nombre de los empleados que pertenecen a cada departamento.*/
SELECT tcentr.NOMCE, tdepto.NOMDE, temple.NOMEM
FROM tcentr
JOIN tdepto ON tdepto.NUMCE = tcentr.NUMCE
JOIN temple ON temple.NUMDE = tdepto.NUMDE
ORDER BY tcentr.NOMCE ASC, tdepto.NOMDE ASC, temple.NOMEM ASC;

/*13. Obtener para los departamentos con un presupuesto superior a 5000 euros, su nombre junto con el nombre
del centro donde est� ubicado. Hacer el ejercicio de dos formas: utilizando un producto cartesiano y con la
cl�usula JOIN.*/
SELECT nomde, nomce
FROM tcentr c JOIN tdepto d ON c.NUMCE=d.NUMCE
WHERE presu>5000;

/*13. Obtener para los departamentos con un presupuesto superior a 5000 euros, su nombre junto con el nombre
del centro donde est� ubicado. Hacer el ejercicio de dos formas: utilizando un producto cartesiano y con la
cl�usula JOIN.*/
SELECT tdepto.NOMDE, tcentr.NOMCE
FROM tcentr
JOIN tdepto ON tdepto.NUMCE = tcentr.NUMCE
WHERE PRESU > 5000


/*14. Para cada empleado obtener el nombre, salario, n�mero de hijos y el nombre del departamento en el que
est�.*/
SELECT temple.NOMEM, temple.SALAR, temple.NUMHI, tdepto.NOMDE
FROM temple
JOIN tdepto ON tdepto.NUMDE = temple.NUMDE

/*14. Para cada empleado obtener el nombre, salario, n�mero de hijos y el nombre del departamento en el que
est�.*/
SELECT temple.NOMEM, temple.SALAR, temple.NUMHI, tdepto.NOMDE
FROM temple
JOIN tdepto ON tdepto.NUMDE = temple.NUMDE

/*15. Para los empleados del departamento de Nominas obtener el nombre, salario y n�mero de hijos. Ordena
ascendentemente por nombre de empleado y utiliza alias para las columnas.*/
SELECT temple.NOMEM AS NombreEmpleado, temple.SALAR AS SalarioEmpleado, temple.NUMHI AS HijosEmpleado
FROM temple
WHERE temple.NUMDE = 110
ORDER BY 1 ASC

/*15. Para los empleados del departamento de Nominas obtener el nombre, salario y n�mero de hijos. Ordena
ascendentemente por nombre de empleado y utiliza alias para las columnas.*/
SELECT NOMEM, SALAR, NUMHI
FROM temple
JOIN tdepto ON temple.NUMDE = tdepto.NUMDE
WHERE tdepto.NOMDE = 'Nominas'

/*16. Obtener el nombre de los empleados que est�n en el centro Sede Central.*/
SELECT NOMEM
FROM tcentr C JOIN tdepto D ON C.NUMCE=D.NUMCE JOIN temple E ON D.NUMDE=E.NUMDE
WHERE NOMCE = 'Sede Central'

/*16. Obtener el nombre de los empleados que est�n en el centro Sede Central.*/
SELECT NOMEM
FROM temple
JOIN tdepto ON temple.NUMDE = tdepto.NUMDE
JOIN tcentr ON tdepto.NUMCE = tcentr.NUMCE
WHERE NOMCE = 'Sede Central'