/*En estos ejemplos vemos que en la cláusula FROM puede aparecer:
- Una sola tabla.
- Varias tablas separadas por "coma". Entonces, tendremos el producto cartesiano de
  dichas tablas.
- Varias tablas usando la cláusula JOIN. Entonces, tendremos la concatenación 
  de dichas tablas mediante el campo indicado.
*/
USE ejemploempresa;

SELECT * FROM departamento;
SELECT * FROM empleado;

--PRODUCTO CARTESIANO.
SELECT * 
FROM departamento, empleado; 
SELECT * 
FROM empleado, departamento;

--PRODUCTO CARTESIANO Y SELECCIÓN DE FILAS.
--Uso de alias de tablas.
SELECT * 
FROM departamento d, empleado e 
WHERE d.numde=e.numde;

SELECT e.nomem,d.nomde
FROM departamento d, empleado e 
WHERE d.numde=e.numde;

SELECT *
FROM empleado e, departamento d
WHERE e.numde=d.numde;

SELECT e.nomem,d.nomde
FROM empleado e, departamento d
WHERE e.numde=d.numde;

--CONCATENACIÓN: USO DE LA CLÁUSULA JOIN.
SELECT *
FROM departamento d JOIN empleado e ON (d.numde=e.numde);

SELECT *
FROM empleado e JOIN departamento d ON (e.numde=d.numde);

SELECT e.nomem,d.nomde
FROM departamento d JOIN empleado e ON (d.numde=e.numde);

SELECT e.nomem,d.nomde
FROM empleado e JOIN departamento d ON (e.numde=d.numde);