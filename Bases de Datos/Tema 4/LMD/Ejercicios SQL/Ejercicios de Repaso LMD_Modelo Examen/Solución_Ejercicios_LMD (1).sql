
USE ejercicios_LMD

/*1. Obtener para cada película el título, el director
y el nombre de los personajes (no protagonista) que participan
en la misma. Si una película aún no tiene personajes que participan 
almacenados, el nombre de la película debe aparecer igualmente. 
Utiliza alias para el nombre de los personajes y en el resultado 
que salga el valor NULL, debes poner "Aún no se han añadido personajes." 
*/
SELECT * FROM pelicula;
SELECT * FROM participa_pel;
SELECT * FROM personaje;

SELECT * 
FROM (pelicula Pel LEFT JOIN participa_pel Par ON (Pel.CodPel=Par.CodPel)) 
      LEFT JOIN personaje Per ON (Par.CodPer=Per.CodPer);

--SOLUCIÓN
SELECT Titulo,Director,ISNULL(NomPer, 'Aún no se han añadido personajes') AS 'Nombre del Personaje'
FROM (pelicula Pel LEFT JOIN participa_pel Par ON (Pel.CodPel=Par.CodPel)) 
      LEFT JOIN personaje Per ON (Par.CodPer=Per.CodPer);
	  
/*NOTA: Para facilitar la consulta en este ejercicio, se pretendía 
que se ignorara el personaje que es protagonista en cada película. De hecho,
no aparecen en la tabla participa_pel para la película que protagonizan.
Pero si queremos sacar el título, el director y los personajes no 
protagonistas en cada película asegurandome que no están en participa_pel, 
la consulta sería así: */

/*Vemos que no deberían de salir las filas que cumplieran que 
Pel.CodPerProtagonista fuera igual a Par.CodPer*/
SELECT *
FROM (pelicula Pel LEFT JOIN participa_pel Par ON (Pel.CodPel=Par.CodPel)) 
      LEFT JOIN personaje Per ON (Par.CodPer=Per.CodPer)
WHERE Pel.CodPerProtagonista<>Par.CodPer OR Par.CodPel IS NULL;

--SOLUCIÓN
SELECT Titulo,Director,ISNULL(NomPer, 'Aún no se han añadido personajes') AS 'Nombre del Personaje'
FROM (pelicula Pel LEFT JOIN participa_pel Par ON (Pel.CodPel=Par.CodPel)) 
      LEFT JOIN personaje Per ON (Par.CodPer=Per.CodPer)
WHERE Pel.CodPerProtagonista<>Par.CodPer OR Par.CodPel IS NULL;

--La condición también se podría poner así:
SELECT Titulo,Director,ISNULL(NomPer, 'Aún no se han añadido personajes') AS 'Nombre del Personaje'
FROM (pelicula Pel LEFT JOIN participa_pel Par ON (Pel.CodPel=Par.CodPel)) 
      LEFT JOIN personaje Per ON (Par.CodPer=Per.CodPer)
WHERE Pel.CodPerProtagonista<>ISNULL(Par.CodPel,0)

/*Esta solución es también correcta. Tener en cuenta
que not in(pel.CodPerProtagonista) es lo mismo que decir 
<>Pel.CodPerProtagonista*/


SELECT pel.Titulo,pel.Director,ISNULL(per.NomPer,'Aún no se han añadido personajes') as"Personaje"
from pelicula pel LEFT JOIN participa_pel par on(pel.CodPel=par.CodPel) LEFT JOIN personaje per on(par.CodPer=per.CodPer)
where isnull(per.CodPer,0) NOT IN(pel.CodPerProtagonista)

/*2. Obtener el nombre de cada personaje y en cuántas películas 
ha participado, pero solo deben salir los personajes que hayan 
participado en más de dos películas. Utiliza alias para las columnas.
Ordena por nombre de personaje descendentemente.
*/
-- Veo en cuántas películas ha participado cada personaje
SELECT *
FROM personaje Per JOIN participa_pel Par ON (Per.CodPer = Par.CodPer)
ORDER BY Per.CodPer,CodPel;

--SOLUCIÓN
SELECT NomPer AS 'Personaje', COUNT(*) AS 'Nº de Películas'
FROM personaje Per JOIN participa_pel Par ON (Per.CodPer = Par.CodPer)
GROUP BY NomPer
HAVING COUNT(*) > 2
ORDER BY NomPer DESC;


/*3. Obtener el nombre y el apellido del actor más joven almacenado en la
base de datos a día de hoy, junto con el nombre del personaje
que interpreta, así como la edad que tiene el actor.
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

--Veo cuál es el más joven
SELECT MIN(DATEDIFF(DAY,Edad,GETDATE())/365)
FROM actor;

--SOLUCIÓN
SELECT NomAct, ApeAct,NomPer, DATEDIFF(DAY,Edad,GETDATE())/365 AS 'Edad'
FROM actor a JOIN personaje p ON (a.CodAct=p.CodPer)
WHERE DATEDIFF(DAY,Edad,GETDATE())/365 = (SELECT MIN(DATEDIFF(DAY,Edad,GETDATE())/365)
										  FROM actor);

/*4. Utilizando la sentencia adecuada del LMD de SQL, modifica 
la película que contiene la palabra "Bosque" (solo conoces esta
palabra del título de la película), para que el año de
lanzamiento sea igual al año de lanzamiento de la película
más antigua almacenada en la base de datos. La sentencia debe
estar dentro de una transacción y cuando hayas comprobado que 
has realizado el ejercicio correctamente, debes deshacerla.
*/
--Veo cuál es la Película que debo modificar
SELECT * FROM pelicula WHERE Titulo LIKE '%Bosque%';

--Veo cuál es el año de lanzamiento que le debo poner
SELECT MIN(Lanzamiento) FROM pelicula;

--SOLUCIÓN
BEGIN TRANSACTION

UPDATE pelicula
SET Lanzamiento = (SELECT MIN(Lanzamiento) FROM pelicula)
WHERE Titulo LIKE '%Bosque%';

ROLLBACK TRANSACTION

/*5. Borra las películas en las que aún no existe ningún 
personaje que participe. No tienes más información que
la que se te indica en el enunciado. La sentencia debe 
estar dentro de una transacción y cuando hayas comprobado
que has realizado el ejercicio correctamente, debes
deshacerla. Realizar el ejercicio de tres maneras diferentes: 
utilizando un predicado EXISTS, un predicado cuantificado y
un predicado IN*/

--Veo cuáles son las películas que debo borrar
SELECT * FROM pelicula;
SELECT * FROM participa_pel;

--Predicado NOT EXIST es verdadero si devuelve 0 filas
SELECT * 
FROM pelicula
WHERE NOT EXISTS (SELECT * 
				  FROM Participa_Pel Par 
				  WHERE Par.CodPel=pelicula.CodPel)
--SOLUCIÓN 1
BEGIN TRANSACTION

DELETE FROM pelicula
WHERE NOT EXISTS (SELECT * 
				  FROM Participa_Pel Par 
				  WHERE Par.CodPel=pelicula.CodPel)

ROLLBACK TRANSACTION

--SOLUCIÓN 2
BEGIN TRANSACTION

DELETE FROM pelicula
WHERE CodPel <> ALL (SELECT CodPel 
				     FROM Participa_Pel)
ROLLBACK TRANSACTION

--SOLUCIÓN 3
BEGIN TRANSACTION

DELETE FROM pelicula
WHERE CodPel NOT IN (SELECT DISTINCT CodPel FROM participa_pel);

ROLLBACK TRANSACTION

/*6. Para todos los actores mayores de edad a día de hoy, obtener
el nombre y el apellido, la fecha de nacimiento en formato español, 
la correspondiente edad a día de hoy, junto con el nombre del 
personaje que interpreta. Utiliza alias para las columnas.
Ordena por nombre de personaje descendentemente.*/
SELECT * FROM actor;
SELECT * FROM personaje;

--Veo cuáles son los actores mayores de edad
SELECT CodAct, NomAct, ApeAct, Edad, DATEDIFF(DAY,EDAD,GETDATE())/365
FROM actor;

--Estos son los actores que deben salir
SELECT *
FROM actor 
WHERE DATEDIFF(DAY,EDAD,GETDATE())/365>=18;

--Uno las tablas para sacar también el personaje
SELECT *
FROM actor a JOIN personaje p ON (a.CodAct=p.CodAct)
WHERE DATEDIFF(DAY,EDAD,GETDATE())/365>=18;

--SOLUCIÓN
SELECT NomAct AS 'Nombre', ApeAct AS 'Apellido',
       FORMAT(Edad,'d') AS 'Fecha Nacimiento',
	   DATEDIFF(DAY,Edad,GETDATE())/365 AS 'Edad',
	   NomPer AS 'Personaje'
FROM actor a JOIN personaje p ON (a.CodAct=p.CodAct)
WHERE DATEDIFF(DAY,EDAD,GETDATE())/365>=18
ORDER BY 5 DESC;

/*7. Obtener el nombre y el apellido de cada actor almacenado en la
base de datos, junto con el nombre del personaje
que interpreta y el título de las películas de las que 
es protagonista, si no es protagonista aún de ninguna película, 
en lugar de salir el valor NULL, debe salir el mensaje
"No es protagonista." Utiliza alias para el título de la película
que protagonizan.*/
SELECT * FROM actor;
SELECT * FROM personaje;
SELECT * FROM pelicula;

--Uno las tablas y veo los personajes que son protagonistas
SELECT *
FROM (actor a JOIN personaje p ON (a.CodAct=p.CodAct))
     LEFT JOIN pelicula pe ON (pe.CodPerProtagonista=p.CodPer);

--SOLUCIÓN
SELECT NomAct, ApeAct, NomPer,ISNULL(Titulo,'No es protagonista')
       AS 'Título película protagonista'
FROM (actor a JOIN personaje p ON (a.CodAct=p.CodAct))
     LEFT JOIN pelicula pe ON (pe.CodPerProtagonista=p.CodPer);

/*8. Por cada película mostrar el título y la edad media de 
los actores que interpretan los personajes que participan en la misma
(no tener en cuenta al personaje protagonista). La edad media debe
salir con uno o dos decimales. Ordenar ascendentemente
por nombre de película. Utiliza alias para la columna edad media.*/

--Uno las tablas
SELECT *
FROM ((actor a JOIN personaje p ON (a.CodAct=p.CodAct))
     JOIN participa_pel pp ON (pp.CodPer=p.CodPer))
	 JOIN pelicula pe ON (pe.CodPel=pp.CodPel)
ORDER BY Titulo;

--Visulizo solo la información que me interesa
SELECT Titulo, NomAct, NomPer, DATEDIFF(DAY,EDAD,GETDATE())/365
FROM ((actor a JOIN personaje p ON (a.CodAct=p.CodAct))
     JOIN participa_pel pp ON (pp.CodPer=p.CodPer))
	 JOIN pelicula pe ON (pe.CodPel=pp.CodPel)
ORDER BY Titulo;

--Hago grupos por títulos de películas y obtengo la media en cada grupo
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

/* Con la función ROUND conseguimos truncar para que nos queden dos decimales.
El tercer parámetro de ROUND es opcional:
If 0, it rounds the result to the number of decimal (saldría 33,86).
If another value than 0, it truncates the result to the number of decimals
(saldría 33,85).
Default value is 0*/

--SOLUCIÓN
SELECT Titulo, 
       ROUND(AVG (CONVERT (FLOAT,DATEDIFF(DAY,EDAD,GETDATE())/365)),2,1)
	   AS 'Edad media de los personajes'
FROM ((actor a JOIN personaje p ON (a.CodAct=p.CodAct))
     JOIN participa_pel pp ON (pp.CodPer=p.CodPer))
	 JOIN pelicula pe ON (pe.CodPel=pp.CodPel)
GROUP BY Titulo
ORDER BY Titulo;


/*9. Obtener con una sola sentencia el título de todas las películas,
el año de lanzamiento y nombre del personaje protagonista, para las películas
con un año de lanzamiento anterior a 2020. Y para las películas
de 2020 o posteriores, obtener el título de la película, el año de lanzamiento junto 
y el nombre del director. Ordenar ascendentemente por año de lanzamiento. 
Utiliza como alias de columna para el nombre del personaje protagonista o del 
director el texto:'Personaje o Director'*/

/*Veo cuáles son las películas junto el personaje protagonista
para las péliculas con un año de lanzamiento anterior a 2020*/
SELECT * 
FROM personaje p JOIN pelicula pe ON (pe.CodPerProtagonista=p.CodPer)
WHERE Lanzamiento < 2020;

/*Igual, pero solo obtenemos el título de la película, el año de
lanzamieto y el nombre del personaje protagonista*/
SELECT Titulo, Lanzamiento, NomPer
FROM personaje p JOIN pelicula pe ON (pe.CodPerProtagonista=p.CodPer)
WHERE Lanzamiento < 2020;

--Veo cuáles son las películas de 2020 o posteriores
SELECT * 
FROM pelicula 
WHERE Lanzamiento >= 2020;

/*Igual, pero solo obtenemos el título de la película. el año de lanzamiento
y el nombre del director*/
SELECT Titulo, Lanzamiento, Director
FROM pelicula 
WHERE Lanzamiento >= 2020;

--SOLUCIÓN CON UNION
SELECT Titulo, Lanzamiento, NomPer AS 'Personaje o Director'
FROM personaje p JOIN pelicula pe ON (pe.CodPerProtagonista=p.CodPer)
WHERE Lanzamiento < 2020
UNION
SELECT Titulo,Lanzamiento,Director
FROM pelicula 
WHERE Lanzamiento >= 2020
ORDER BY 2;

--SOLUCIÓN CON CASE
SELECT Titulo, Lanzamiento, 
       CASE WHEN Lanzamiento < 2020 THEN NomPer
	   ELSE Director 
	   END  AS 'Personaje o Director'
FROM personaje p JOIN pelicula pe ON (pe.CodPerProtagonista=p.CodPer)
ORDER BY 2;

--SOLUCIÓN CON IIF
SELECT Titulo, Lanzamiento,IIF( Lanzamiento < 2020, NomPer,Director) AS 'Personaje o Director'
FROM personaje p JOIN pelicula pe ON (pe.CodPerProtagonista=p.CodPer)
ORDER BY 2;

 /*10. Utilizando la sentencia adecuada del LMD de SQL,
 borra los personajes que cumplen las siguientes tres condiciones:
 que no sean protagonistas, que no participan en ninguna película y 
 su  nombre empiece por la letra E. La sentencia debe
 estar dentro de una transacción y cuando hayas comprobado que 
 has realizado el ejercicio correctamente, debes deshacerla.
*/
/*Veamos cuáles son los personajes que se deben borrar.
Se podrían sustituir los predicados IN por un
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

--SOLUCIÓN
BEGIN TRANSACTION

DELETE FROM personaje
WHERE (CodPer NOT IN (SELECT CodPerProtagonista FROM pelicula))
       AND (CodPer NOT IN (SELECT CodPer FROM participa_pel))
	   AND (NomPer LIKE 'E%');

SELECT * FROM personaje;

ROLLBACK TRANSACTION

/*11. Ha habido un error cuando se ha almacenado en la base
de datos la fecha de nacimiento de Isabel Sánchez. Precisamente,
Isabel Sánchez nació el mismo día que Javier Díaz. Utilizando la 
sentencia adecuada del LMD de SQL, modifica la fecha de nacimiento
de Isabel Sánchez para que sea la misma que la de Javier Díaz.
Solo conoces los nombres de los actores.
La sentencia debe  estar dentro de una transacción y 
cuando hayas comprobado que  has realizado el ejercicio 
correctamente, debes deshacerla.*/

--Veamos la información que hay almacenada en actor
SELECT * FROM actor;

--SOLUCIÓN 
BEGIN TRANSACTION

UPDATE actor
SET Edad= (SELECT Edad 
           FROM actor 
		   WHERE NomAct LIKE 'Javier' AND ApeAct LIKE 'Díaz')
WHERE NomAct LIKE 'Isabel' AND ApeAct LIKE 'Sánchez';

SELECT * FROM actor;

ROLLBACK TRANSACTION






