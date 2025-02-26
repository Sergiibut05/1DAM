USE empresa;

SELECT * FROM temple;

/*Obtiene los nombres de los empleados cuya comisi�n es nula. 
El campo comis tiene el valor NULL.  
*/
SELECT nomem
FROM temple
WHERE comis IS NULL;

/*Obtiene los nombres de los empleados cuya comisi�n no es nula. 
El campo comis tiene un valor distinto de NULL.  
*/
SELECT nomem
FROM temple
WHERE comis IS NOT NULL;
