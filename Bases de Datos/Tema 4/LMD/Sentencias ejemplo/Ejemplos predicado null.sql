USE empresa;

SELECT * FROM temple;

/*Obtiene los nombres de los empleados cuya comisión es nula. 
El campo comis tiene el valor NULL.  
*/
SELECT nomem
FROM temple
WHERE comis IS NULL;

/*Obtiene los nombres de los empleados cuya comisión no es nula. 
El campo comis tiene un valor distinto de NULL.  
*/
SELECT nomem
FROM temple
WHERE comis IS NOT NULL;
