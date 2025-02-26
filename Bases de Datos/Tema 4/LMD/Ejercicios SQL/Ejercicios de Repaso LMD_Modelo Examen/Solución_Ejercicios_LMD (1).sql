
USE ejercicios_LMD

/*1. Obtener para cada pel�cula el t�tulo, el director
y el nombre de los personajes (no protagonista) que participan
en la misma. Si una pel�cula a�n no tiene personajes que participan 
almacenados, el nombre de la pel�cula debe aparecer igualmente. 
Utiliza alias para el nombre de los personajes y en el resultado 
que salga el valor NULL, debes poner "A�n no se han a�adido personajes." 
*/
SELECT * FROM pelicula;
SELECT * FROM participa_pel;
SELECT * FROM personaje;

SELECT * 
FROM (pelicula Pel LEFT JOIN participa_pel Par ON (Pel.CodPel=Par.CodPel)) 
      LEFT JOIN personaje Per ON (Par.CodPer=Per.CodPer);

--SOLUCI�N
SELECT Titulo,Director,ISNULL(NomPer, 'A�n no se han a�adido personajes') AS 'Nombre del Personaje'
FROM (pelicula Pel LEFT JOIN participa_pel Par ON (Pel.CodPel=Par.CodPel)) 
      LEFT JOIN personaje Per ON (Par.CodPer=Per.CodPer);
	  
/*NOTA: Para facilitar la consulta en este ejercicio, se pretend�a 
que se ignorara el personaje que es protagonista en cada pel�cula. De hecho,
no aparecen en la tabla participa_pel para la pel�cula que protagonizan.
Pero si queremos sacar el t�tulo, el director y los personajes no 
protagonistas en cada pel�cula asegurandome que no est�n en participa_pel, 
la consulta ser�a as�: */

/*Vemos que no deber�an de salir las filas que cumplieran que 
Pel.CodPerProtagonista fuera igual a Par.CodPer*/
SELECT *
FROM (pelicula Pel LEFT JOIN participa_pel Par ON (Pel.CodPel=Par.CodPel)) 
      LEFT JOIN personaje Per ON (Par.CodPer=Per.CodPer)
WHERE Pel.CodPerProtagonista<>Par.CodPer OR Par.CodPel IS NULL;

--SOLUCI�N
SELECT Titulo,Director,ISNULL(NomPer, 'A�n no se han a�adido personajes') AS 'Nombre del Personaje'
FROM (pelicula Pel LEFT JOIN participa_pel Par ON (Pel.CodPel=Par.CodPel)) 
      LEFT JOIN personaje Per ON (Par.CodPer=Per.CodPer)
WHERE Pel.CodPerProtagonista<>Par.CodPer OR Par.CodPel IS NULL;

--La condici�n tambi�n se podr�a poner as�:
SELECT Titulo,Director,ISNULL(NomPer, 'A�n no se han a�adido personajes') AS 'Nombre del Personaje'
FROM (pelicula Pel LEFT JOIN participa_pel Par ON (Pel.CodPel=Par.CodPel)) 
      LEFT JOIN personaje Per ON (Par.CodPer=Per.CodPer)
WHERE Pel.CodPerProtagonista<>ISNULL(Par.CodPel,0)

/*Esta soluci�n es tambi�n correcta. Tener en cuenta
que not in(pel.CodPerProtagonista) es lo mismo que decir 
<>Pel.CodPerProtagonista*/


SELECT pel.Titulo,pel.Director,ISNULL(per.NomPer,'A�n no se han a�adido personajes') as"Personaje"
from pelicula pel LEFT JOIN participa_pel par on(pel.CodPel=par.CodPel) LEFT JOIN personaje per on(par.CodPer=per.CodPer)
where isnull(per.CodPer,0) NOT IN(pel.CodPerProtagonista)

/*2. Obtener el nombre de cada personaje y en cu�ntas pel�culas 
ha participado, pero solo deben salir los personajes que hayan 
participado en m�s de dos pel�culas. Utiliza alias para las columnas.
Ordena por nombre de personaje descendentemente.
*/
-- Veo en cu�ntas pel�culas ha participado cada personaje
SELECT *
FROM personaje Per JOIN participa_pel Par ON (Per.CodPer = Par.CodPer)
ORDER BY Per.CodPer,CodPel;

--SOLUCI�N
SELECT NomPer AS 'Personaje', COUNT(*) AS 'N� de Pel�culas'
FROM personaje Per JOIN participa_pel Par ON (Per.CodPer = Par.CodPer)
GROUP BY NomPer
HAVING COUNT(*) > 2
ORDER BY NomPer DESC;


/*3. Obtener el nombre y el apellido del actor m�s joven almacenado en la
base de datos a d�a de hoy, junto con el nombre del personaje
que interpreta, as� como la edad que tiene el actor.
*/
SELECT * FROM actor;
SELECT * FROM personaje;

--Uno las tablas
SELECT * 
FROM actor a JOIN personaje p ON (a.CodAct=p.CodPer)
ORDER BY Edad;

--Veo las edades de los actores
SELECT a.CodAct, NomAct, ApeAct, CodPer, NomPer, DATEDIFF(DAY,Edad,GETDATE())/365 As'Edad'
FROM actor a JOIN personaje p ON (a.CodAct=p.CodPer)
ORDER BY Edad;

--Veo cu�l es el m�s joven
SELECT MIN(DATEDIFF(DAY,Edad,GETDATE())/365)
FROM actor;

--SOLUCI�N
SELECT NomAct, ApeAct,NomPer, DATEDIFF(DAY,Edad,GETDATE())/365 AS 'Edad'
FROM actor a JOIN personaje p ON (a.CodAct=p.CodPer)
WHERE DATEDIFF(DAY,Edad,GETDATE())/365 = (SELECT MIN(DATEDIFF(DAY,Edad,GETDATE())/365)
										  FROM actor);

/*4. Utilizando la sentencia adecuada del LMD de SQL, modifica 
la pel�cula que contiene la palabra "Bosque" (solo conoces esta
palabra del t�tulo de la pel�cula), para que el a�o de
lanzamiento sea igual al a�o de lanzamiento de la pel�cula
m�s antigua almacenada en la base de datos. La sentencia debe
estar dentro de una transacci�n y cuando hayas comprobado que 
has realizado el ejercicio correctamente, debes deshacerla.
*/
--Veo cu�l es la Pel�cula que debo modificar
SELECT * FROM pelicula WHERE Titulo LIKE '%Bosque%';

--Veo cu�l es el a�o de lanzamiento que le debo poner
SELECT MIN(Lanzamiento) FROM pelicula;

--SOLUCI�N
BEGIN TRANSACTION

UPDATE pelicula
SET Lanzamiento = (SELECT MIN(Lanzamiento) FROM pelicula)
WHERE Titulo LIKE '%Bosque%';

ROLLBACK TRANSACTION

/*5. Borra las pel�culas en las que a�n no existe ning�n 
personaje que participe. No tienes m�s informaci�n que
la que se te indica en el enunciado. La sentencia debe 
estar dentro de una transacci�n y cuando hayas comprobado
que has realizado el ejercicio correctamente, debes
deshacerla. Realizar el ejercicio de tres maneras diferentes: 
utilizando un predicado EXISTS, un predicado cuantificado y
un predicado IN*/

--Veo cu�les son las pel�culas que debo borrar
SELECT * FROM pelicula;
SELECT * FROM participa_pel;

--Predicado NOT EXIST es verdadero si devuelve 0 filas
SELECT * 
FROM pelicula
WHERE NOT EXISTS (SELECT * 
				  FROM Participa_Pel Par 
				  WHERE Par.CodPel=pelicula.CodPel)
--SOLUCI�N 1
BEGIN TRANSACTION

DELETE FROM pelicula
WHERE NOT EXISTS (SELECT * 
				  FROM Participa_Pel Par 
				  WHERE Par.CodPel=pelicula.CodPel)

ROLLBACK TRANSACTION

--SOLUCI�N 2
BEGIN TRANSACTION

DELETE FROM pelicula
WHERE CodPel <> ALL (SELECT CodPel 
				     FROM Participa_Pel)
ROLLBACK TRANSACTION

--SOLUCI�N 3
BEGIN TRANSACTION

DELETE FROM pelicula
WHERE CodPel NOT IN (SELECT DISTINCT CodPel FROM participa_pel);

ROLLBACK TRANSACTION

/*6. Para todos los actores mayores de edad a d�a de hoy, obtener
el nombre y el apellido, la fecha de nacimiento en formato espa�ol, 
la correspondiente edad a d�a de hoy, junto con el nombre del 
personaje que interpreta. Utiliza alias para las columnas.
Ordena por nombre de personaje descendentemente.*/
SELECT * FROM actor;
SELECT * FROM personaje;

--Veo cu�les son los actores mayores de edad
SELECT CodAct, NomAct, ApeAct, Edad, DATEDIFF(DAY,EDAD,GETDATE())/365
FROM actor;

--Estos son los actores que deben salir
SELECT *
FROM actor 
WHERE DATEDIFF(DAY,EDAD,GETDATE())/365>=18;

--Uno las tablas para sacar tambi�n el personaje
SELECT *
FROM actor a JOIN personaje p ON (a.CodAct=p.CodAct)
WHERE DATEDIFF(DAY,EDAD,GETDATE())/365>=18;

--SOLUCI�N
SELECT NomAct AS 'Nombre', ApeAct AS 'Apellido',
       FORMAT(Edad,'d') AS 'Fecha Nacimiento',
	   DATEDIFF(DAY,Edad,GETDATE())/365 AS 'Edad',
	   NomPer AS 'Personaje'
FROM actor a JOIN personaje p ON (a.CodAct=p.CodAct)
WHERE DATEDIFF(DAY,EDAD,GETDATE())/365>=18
ORDER BY 5 DESC;

/*7. Obtener el nombre y el apellido de cada actor almacenado en la
base de datos, junto con el nombre del personaje
que interpreta y el t�tulo de las pel�culas de las que 
es protagonista, si no es protagonista a�n de ninguna pel�cula, 
en lugar de salir el valor NULL, debe salir el mensaje
"No es protagonista." Utiliza alias para el t�tulo de la pel�cula
que protagonizan.*/
SELECT * FROM actor;
SELECT * FROM personaje;
SELECT * FROM pelicula;

--Uno las tablas y veo los personajes que son protagonistas
SELECT *
FROM (actor a JOIN personaje p ON (a.CodAct=p.CodAct))
     LEFT JOIN pelicula pe ON (pe.CodPerProtagonista=p.CodPer);

--SOLUCI�N
SELECT NomAct, ApeAct, NomPer,ISNULL(Titulo,'No es protagonista')
       AS 'T�tulo pel�cula protagonista'
FROM (actor a JOIN personaje p ON (a.CodAct=p.CodAct))
     LEFT JOIN pelicula pe ON (pe.CodPerProtagonista=p.CodPer);

/*8. Por cada pel�cula mostrar el t�tulo y la edad media de 
los actores que interpretan los personajes que participan en la misma
(no tener en cuenta al personaje protagonista). La edad media debe
salir con uno o dos decimales. Ordenar ascendentemente
por nombre de pel�cula. Utiliza alias para la columna edad media.*/

--Uno las tablas
SELECT *
FROM ((actor a JOIN personaje p ON (a.CodAct=p.CodAct))
     JOIN participa_pel pp ON (pp.CodPer=p.CodPer))
	 JOIN pelicula pe ON (pe.CodPel=pp.CodPel)
ORDER BY Titulo;

--Visulizo solo la informaci�n que me interesa
SELECT Titulo, NomAct, NomPer, DATEDIFF(DAY,EDAD,GETDATE())/365
FROM ((actor a JOIN personaje p ON (a.CodAct=p.CodAct))
     JOIN participa_pel pp ON (pp.CodPer=p.CodPer))
	 JOIN pelicula pe ON (pe.CodPel=pp.CodPel)
ORDER BY Titulo;

--Hago grupos por t�tulos de pel�culas y obtengo la media en cada grupo
SELECT Titulo, AVG (DATEDIFF(DAY,EDAD,GETDATE())/365)
FROM ((actor a JOIN personaje p ON (a.CodAct=p.CodAct))
     JOIN participa_pel pp ON (pp.CodPer=p.CodPer))
	 JOIN pelicula pe ON (pe.CodPel=pp.CodPel)
GROUP BY Titulo
ORDER BY Titulo;

/*Puesto que la media de un entero es un entero, debemos 
convertir las edades en flotantes (por ejemplo)*/
SELECT Titulo, AVG (CONVERT (FLOAT,DATEDIFF(DAY,EDAD,GETDATE())/365))
FROM ((actor a JOIN personaje p ON (a.CodAct=p.CodAct))
     JOIN participa_pel pp ON (pp.CodPer=p.CodPer))
	 JOIN pelicula pe ON (pe.CodPel=pp.CodPel)
GROUP BY Titulo
ORDER BY Titulo;

/* Con la funci�n ROUND conseguimos truncar para que nos queden dos decimales.
El tercer par�metro de ROUND es opcional:
If 0, it rounds the result to the number of decimal (saldr�a 33,86).
If another value than 0, it truncates the result to the number of decimals
(saldr�a 33,85).
Default value is 0*/

--SOLUCI�N
SELECT Titulo, 
       ROUND(AVG (CONVERT (FLOAT,DATEDIFF(DAY,EDAD,GETDATE())/365)),2,1)
	   AS 'Edad media de los personajes'
FROM ((actor a JOIN personaje p ON (a.CodAct=p.CodAct))
     JOIN participa_pel pp ON (pp.CodPer=p.CodPer))
	 JOIN pelicula pe ON (pe.CodPel=pp.CodPel)
GROUP BY Titulo
ORDER BY Titulo;


/*9. Obtener con una sola sentencia el t�tulo de todas las pel�culas,
el a�o de lanzamiento y nombre del personaje protagonista, para las pel�culas
con un a�o de lanzamiento anterior a 2020. Y para las pel�culas
de 2020 o posteriores, obtener el t�tulo de la pel�cula, el a�o de lanzamiento junto 
y el nombre del director. Ordenar ascendentemente por a�o de lanzamiento. 
Utiliza como alias de columna para el nombre del personaje protagonista o del 
director el texto:'Personaje o Director'*/

/*Veo cu�les son las pel�culas junto el personaje protagonista
para las p�liculas con un a�o de lanzamiento anterior a 2020*/
SELECT * 
FROM personaje p JOIN pelicula pe ON (pe.CodPerProtagonista=p.CodPer)
WHERE Lanzamiento < 2020;

/*Igual, pero solo obtenemos el t�tulo de la pel�cula, el a�o de
lanzamieto y el nombre del personaje protagonista*/
SELECT Titulo, Lanzamiento, NomPer
FROM personaje p JOIN pelicula pe ON (pe.CodPerProtagonista=p.CodPer)
WHERE Lanzamiento < 2020;

--Veo cu�les son las pel�culas de 2020 o posteriores
SELECT * 
FROM pelicula 
WHERE Lanzamiento >= 2020;

/*Igual, pero solo obtenemos el t�tulo de la pel�cula. el a�o de lanzamiento
y el nombre del director*/
SELECT Titulo, Lanzamiento, Director
FROM pelicula 
WHERE Lanzamiento >= 2020;

--SOLUCI�N CON UNION
SELECT Titulo, Lanzamiento, NomPer AS 'Personaje o Director'
FROM personaje p JOIN pelicula pe ON (pe.CodPerProtagonista=p.CodPer)
WHERE Lanzamiento < 2020
UNION
SELECT Titulo,Lanzamiento,Director
FROM pelicula 
WHERE Lanzamiento >= 2020
ORDER BY 2;

--SOLUCI�N CON CASE
SELECT Titulo, Lanzamiento, 
       CASE WHEN Lanzamiento < 2020 THEN NomPer
	   ELSE Director 
	   END  AS 'Personaje o Director'
FROM personaje p JOIN pelicula pe ON (pe.CodPerProtagonista=p.CodPer)
ORDER BY 2;

--SOLUCI�N CON IIF
SELECT Titulo, Lanzamiento,IIF( Lanzamiento < 2020, NomPer,Director) AS 'Personaje o Director'
FROM personaje p JOIN pelicula pe ON (pe.CodPerProtagonista=p.CodPer)
ORDER BY 2;

 /*10. Utilizando la sentencia adecuada del LMD de SQL,
 borra los personajes que cumplen las siguientes tres condiciones:
 que no sean protagonistas, que no participan en ninguna pel�cula y 
 su  nombre empiece por la letra E. La sentencia debe
 estar dentro de una transacci�n y cuando hayas comprobado que 
 has realizado el ejercicio correctamente, debes deshacerla.
*/
/*Veamos cu�les son los personajes que se deben borrar.
Se podr�an sustituir los predicados IN por un
predicado cuantificado (<> ALL), o bien por predicados EXISTS*/

SELECT *
FROM personaje
WHERE (CodPer NOT IN (SELECT CodPerProtagonista FROM pelicula))
       AND (CodPer NOT IN (SELECT CodPer FROM participa_pel))
	   AND (NomPer LIKE 'E%');

SELECT *
FROM personaje
WHERE (CodPer <> ALL (SELECT CodPerProtagonista FROM pelicula))
       AND (CodPer NOT IN (SELECT CodPer FROM participa_pel))
	   AND (NomPer LIKE 'E%');

SELECT *
FROM personaje
WHERE (CodPer <> ALL (SELECT CodPerProtagonista FROM pelicula))
       AND (NOT EXISTS (SELECT * FROM participa_pel WHERE CodPer=personaje.CodPer))
	   AND (NomPer LIKE 'E%');

--SOLUCI�N
BEGIN TRANSACTION

DELETE FROM personaje
WHERE (CodPer NOT IN (SELECT CodPerProtagonista FROM pelicula))
       AND (CodPer NOT IN (SELECT CodPer FROM participa_pel))
	   AND (NomPer LIKE 'E%');

SELECT * FROM personaje;

ROLLBACK TRANSACTION

/*11. Ha habido un error cuando se ha almacenado en la base
de datos la fecha de nacimiento de Isabel S�nchez. Precisamente,
Isabel S�nchez naci� el mismo d�a que Javier D�az. Utilizando la 
sentencia adecuada del LMD de SQL, modifica la fecha de nacimiento
de Isabel S�nchez para que sea la misma que la de Javier D�az.
Solo conoces los nombres de los actores.
La sentencia debe  estar dentro de una transacci�n y 
cuando hayas comprobado que  has realizado el ejercicio 
correctamente, debes deshacerla.*/

--Veamos la informaci�n que hay almacenada en actor
SELECT * FROM actor;

--SOLUCI�N 
BEGIN TRANSACTION

UPDATE actor
SET Edad= (SELECT Edad 
           FROM actor 
		   WHERE NomAct LIKE 'Javier' AND ApeAct LIKE 'D�az')
WHERE NomAct LIKE 'Isabel' AND ApeAct LIKE 'S�nchez';

SELECT * FROM actor;

ROLLBACK TRANSACTION






