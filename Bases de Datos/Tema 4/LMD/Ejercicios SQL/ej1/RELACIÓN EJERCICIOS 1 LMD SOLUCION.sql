USE EMPRESA;
/*1. Hallar por orden alfabético los nombres de los
departamentos cuyo está en
funciones.*/
SELECT * FROM tdepto;
--SOLUCIÓN:
SELECT nomde
FROM tdepto
WHERE tidir='F'
ORDER BY 1;

/*2. Obtener un listín telefónico de los empleados
del departamento 121 incluyendo nombre del empleado,
número del empleado y extensión telefónica. Por orden
alfabético descendente.*/
SELECT nomem,numem,extel
FROM temple
WHERE numde=121
ORDER BY 1 DESC;

/*3. Obtener por orden creciente una relación de todos
los números de extensiones telefónicas
de los empleados (elimina las repeticiones).*/
SELECT DISTINCT extel
FROM temple
ORDER BY 1;

/*4. Hallar el nombre, salario y la comisión de los
empleados con más de un hijo,
clasificados por comisión, y dentro de la comisión por
orden alfabético.
El listado debe incluir también los empleados con más
de un hijo aunque no
tengan comisión. Utilizar la función ISNULL para que
cuando la comisión NULL
muestre un 0. Utiliza alias para los nombres de las
columnas.*/
SELECT nomem AS 'Nombre', salar AS 'Salario',
ISNULL(comis,0) AS 'Comisión'
FROM temple
WHERE numhi>1
ORDER BY 3,1;

/*5. Obtener salario y nombre de los empleados con dos
hijos por orden decreciente
de salario y por orden alfabético dentro del
salario.*/
SELECT salar, nomem
FROM temple
WHERE numhi=2
ORDER BY 1 DESC,2;

/*6. Obtener el nombre de los empleados cuya comisión
es superior o igual
al 50% de su salario, por orden alfabético.*/
SELECT nomem
FROM temple
WHERE comis>=salar*0.5
ORDER BY 1;

/*7.En una campaña de ayuda familiar se ha decidido
dar a los empleados
una paga extra de 30 euros por hijo, a partir del
tercero inclusive.
Obtener por orden alfabético para estos empleados:
nombre y salario total
que van a cobrar incluyendo esta paga extra.*/
SELECT nomem, salar + 30*(numhi-2)
FROM temple
WHERE numhi>2
ORDER BY 1;

/*8. Igual que el ejercicio anterior, pero mostrar
también el nombre y
el salario que ganan el resto de los empleados (los
que tienen
0, 1 o 2 hijos). Resuelve el ejercicio de dos formas
diferentes:
con el operador UNION y con una expresión CASE.
Consulta en el
Manual SQL w3schools "SQL Union" y "SQL Case".*/

--Con el operador UNION:
/*Ten en cuenta que para poder utilizar el operador
UNION, debes ponerlo entre
dos SELECT, los SELECT deben devolver el mismo número
de columna definidas
sobre los mismos tipos de datos. Además la cláusula
ORDER BY debe ir al final.
El operador UNION elimina las filas repetidas del
resultado de la consulta.
El operador UNION ALL deja la filas repetidas en el
resultado de la consulta.
*/
--Vemos que la información que nos va a dar la consulta es correcta.
SELECT nomem, numhi, salar, salar + 30*(numhi-2) AS
'PAGA'
FROM temple
WHERE numhi>=3
UNION
SELECT nomem, numhi, salar, salar
FROM temple
WHERE numhi<3
ORDER BY 1;
--SOLUCIÓN:
SELECT nomem, salar + 30*(numhi-2) AS 'PAGA'
FROM temple
WHERE numhi>=3
UNION
SELECT nomem, salar
FROM temple
WHERE numhi<3
ORDER BY 1;

--Con la expresión CASE:
--Vemos que la información que nos va a dar la consulta es correcta.
SELECT nomem, numhi, salar, CASE WHEN numhi>=3 THEN
salar + 30*(numhi-2)
WHEN numhi<3 THEN salar
ELSE 'No es posible'
END AS 'PAGA'
FROM temple;
--SOLUCIÓN:
SELECT nomem, CASE WHEN numhi>=3 THEN salar +
30*(numhi-2)
ELSE salar
END AS 'PAGA'
FROM temple
ORDER BY 1;

/*9. Hallar por orden alfabético los nombres de los
empleados, tales que si
se les da una gratificación de 60 euros por hijo, esta
gratificación no supera a la décima parte de
su salario.*/
SELECT nomem
FROM temple
WHERE 60*numhi<=salar/10
ORDER BY 1;

/*10. Obtener el nombre de cada centro, junto con el
nombre de los departamentos que tiene.
Ordena ascendentemente por nombre de centro y a igual
nombre de centro ordena
por nombre de departamento.*/
SELECT nomce, nomde
FROM tcentr C JOIN tdepto D ON (C.numce=D.numce)
ORDER BY nomce, nomde;

/*11. Obtener ordenadamente el nombre de cada
departamento junto con el nombre de
cada empleado que tiene.*/
SELECT nomde, nomem
FROM tdepto D JOIN temple E ON (D.numde=E.numde)
ORDER BY 1, 2;

/*12. Obtener ordenadamente el nombre de cada centro,
junto con el nombre de los departamentos que tiene
y el nombre de los empleados que pertenecen a cada
departamento.*/
SELECT nomce, nomde, nomem
FROM (tcentr C JOIN tdepto D ON (C.numce=D.numce))
JOIN temple E ON (D.numde=E.numde)
ORDER BY nomce, nomde, nomem;

/*13. Obtener para los departamentos con un
presupuesto superior
a 5000 euros, su nombre junto con el nombre del centro
donde está ubicado.
Hacer el ejercicio de dos formas: utilizando un
producto cartesiano y
con la cláusula JOIN. */
SELECT nomde,nomce
FROM tcentr c, tdepto d
WHERE (c.numce=d.numce) AND (presu>5000);
SELECT nomde,nomce
FROM tcentr c JOIN tdepto d ON (c.numce=d.numce)
WHERE presu>5000;

/*14. Para cada empleado obtener el nombre, salario,
número de hijos y el
nombre del departamento en el que está.*/
SELECT nomem, salar, numhi, nomde
FROM temple e JOIN tdepto d ON (e.numde=d.numde);

/*15. Para los empleados del departamento de Nóminas
obtener el nombre, salario y número de hijos. Ordena
ascendentemente por nombre de empleado
y utiliza alias para las columnas.*/
SELECT nomem AS 'Nombre', salar AS 'Salario', numhi AS
'Número de hijos'
FROM temple e JOIN tdepto d ON (e.numde=d.numde)
WHERE nomde='Nominas'
ORDER BY 1;

/*16. Obtener el nombre de los empleados que están en
el centro Sede Central.*/
SELECT nomem
FROM (tcentr C JOIN tdepto D ON (C.numce=D.numce))
JOIN temple E ON (D.numde=E.numde)
WHERE nomce = 'Sede Central'
ORDER BY nomem;