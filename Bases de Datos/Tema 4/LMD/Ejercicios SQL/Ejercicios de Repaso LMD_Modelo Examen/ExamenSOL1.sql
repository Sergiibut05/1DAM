USE ejercicios_LMD


/* EJERCICIO 1
 Obtener para cada pel�cula el t�tulo, el director y el nombre de los personajes (no protagonista)
 que participan en la misma. Si una pel�cula a�n no tiene personajes que participan
 almacenados, el nombre de la pel�cula debe aparecer igualmente. Utiliza alias para el nombre de
 los personajes y en el resultado que salga el valor NULL, debes poner "A�n no se han a�adido
 personajes.*/
 SELECT Titulo, Director, ISNULL(NomPer,'A�n no se han a�adido personajes') AS 'NomPer'
 FROM (dbo.pelicula a LEFT JOIN dbo.participa_pel b ON (b.CodPel=a.CodPel)) LEFT JOIN dbo.personaje c ON (b.CodPer=c.CodPer)
 
 /* EJERCICIO 2
 Obtener el nombre de cada personaje y en cu�ntas pel�culas ha participado, pero solo deben
 salir los personajes que hayan participado en m�s de dos pel�culas. Utiliza alias para las
 columnas. Ordena por nombre de personaje descendentemente*/
 SELECT NomPer AS 'Personaje', COUNT(CodPel) AS 'Participaci�n en Pel�culas'
 FROM dbo.personaje a, dbo.participa_pel b
 WHERE a.CodPer= b.CodPer
 GROUP BY NomPer
 HAVING COUNT(CodPel)>2
 ORDER BY NomPer DESC;

 /* EJERCICIO 3
 Obtener el nombre y el apellido del actor m�s joven almacenado en la base de datos a d�a de
 hoy, junto con el nombre del personaje que interpreta, as� como la edad que tiene el actor*/
 SELECT TOP(1) a.NomAct, a.ApeAct, ISNULL(b.NomPer,'No tiene personaje Vinculado') AS 'NomPer', DATEDIFF(YEAR, a.Edad, GETDATE()) AS 'Edad'
 FROM dbo.actor a LEFT JOIN dbo.personaje b ON (a.CodAct=b.CodAct)
 ORDER BY 4;

 /* EJERCICIO 4 
 Utilizando la sentencia adecuada del LMD de SQL, modifica la pel�cula que contiene la palabra
 "Bosque" (solo conoces esta palabra del t�tulo de la pel�cula), para que el a�o de lanzamiento
 sea igual al a�o de lanzamiento de la pel�cula m�s antigua almacenada en la base de datos. La
 sentencia debe estar dentro de una transacci�n y cuando hayas comprobado que has realizado
 el ejercicio correctamente, debes deshacerla*/
 BEGIN TRANSACTION;
 UPDATE dbo.pelicula SET Lanzamiento = (SELECT MIN(Lanzamiento) FROM dbo.pelicula)
 WHERE Titulo LIKE '%Bosque%';
 ROLLBACK TRANSACTION;
 
 /*EJERCICIO 5
 Borra las pel�culas en las que a�n no existe ning�n personaje que participe. No tienes m�s
 informaci�n que la que se te indica en el enunciado. La sentencia debe estar dentro de una
 transacci�n y cuando hayas comprobado que has realizado el ejercicio correctamente, debes
 deshacerla. Realizar el ejercicio de tres formas diferentes: utilizando un predicado EXISTS, un
 predicado cuantificado y un predicado IN*/
 BEGIN TRANSACTION;
 DELETE FROM dbo.pelicula
 WHERE CodPel IN(SELECT a.CodPel FROM dbo.pelicula a LEFT JOIN dbo.participa_pel b ON (a.CodPel=b.CodPel) GROUP BY a.CodPel HAVING COUNT(CodPer)=0)
 ROLLBACK TRANSACTION;

 BEGIN TRANSACTION;
 DELETE FROM dbo.pelicula
 WHERE CodPel <> ALL(SELECT CodPel FROM dbo.participa_pel)
 ROLLBACK TRANSACTION;

 BEGIN TRANSACTION;
 DELETE FROM dbo.pelicula 
 WHERE NOT EXISTS(SELECT b.CodPel FROM dbo.participa_pel b WHERE CodPel=pelicula.CodPel)
 ROLLBACK TRANSACTION;

 /* EJERCICIO 6
 Para todos los actores mayores de edad a d�a de hoy, obtener el nombre y el apellido, la fecha de
 nacimiento en formato espa�ol, la correspondiente edad a d�a de hoy, junto con el nombre del
 personaje que interpreta. Utiliza alias para las columnas. Ordena por nombre de personaje
 descendentemente*/
 SELECT NomAct AS 'Nombre Actor', ApeAct AS 'Apellido Actor', FORMAT(Edad,'dd-MMMM-yyyy') AS 'Fecha Nacimiento', DATEDIFF(YEAR,Edad,GETDATE()) AS 'Edad', ISNULL(NomPer,'No tiene Personaje') AS 'Nombre Personaje'
 FROM dbo.actor a LEFT JOIN dbo.personaje b ON (a.CodAct=b.CodAct)
 WHERE DATEDIFF(YEAR,Edad,GETDATE())>17
 ORDER BY 1 DESC;

 /* EJERCICIO 7
 Obtener el nombre y el apellido de cada actor almacenado en la base de datos, junto con el
 nombre del personaje que interpreta y el t�tulo de las pel�culas de las que es protagonista, si no
 es protagonista a�n de ninguna pel�cula, en lugar de salir el valor NULL, debe salir el mensaje
 "No es protagonista�. Utiliza alias para el t�tulo de la pel�cula que protagonizan*/
 SELECT NomAct AS 'Nombre Actor', ApeAct AS 'Apellido Actor', NomPer AS 'Nombre Personaje', ISNULL(Titulo, 'No es protagonista') AS 'Pel�culas de las que es protagonista'
 FROM (dbo.actor a LEFT JOIN dbo.personaje b ON (a.CodAct=b.CodAct)) LEFT JOIN dbo.pelicula c ON (b.CodPer=c.CodPerProtagonista)

 /* EJERCICIO 8
 Por cada pel�cula mostrar el t�tulo y la edad media de los actores que interpretan los personajes
 que participan en la misma (no tener en cuenta al personaje protagonista). La edad media debe
 salir con uno o dos decimales. Ordenar ascendentemente por nombre de pel�cula. Utiliza alias
 para la columna edad media*/
 SELECT Titulo, ROUND(AVG(CONVERT(FLOAT,DATEDIFF(YEAR, Edad, GETDATE()))),2)  AS 'Edad Media'
 FROM ((dbo.participa_pel a JOIN dbo.pelicula b ON (a.CodPel=b.CodPel)) JOIN dbo.personaje c ON (c.CodPer = a.CodPer)) JOIN dbo.actor d ON (d.CodAct = c.CodAct)
 GROUP BY Titulo
 ORDER BY Titulo ASC;

 /* EJERCICIO 9
 Obtener con una sola sentencia el t�tulo de todas las pel�culas, el a�o de lanzamiento y nombre
 del personaje protagonista, para las pel�culas con un a�o de lanzamiento anterior a 2020. Y para
 las pel�culas de 2020 o posteriores, obtener el t�tulo de la pel�cula, el a�o de lanzamiento y el
 nombre del director. Ordenar ascendentemente por a�o de lanzamiento. Utiliza como alias de
 columna para el nombre del personaje protagonista o del director el texto: 'Personaje o Director'.*/
 SELECT Titulo, Lanzamiento, CASE WHEN Lanzamiento<2020 THEN NomPer ELSE Director END AS 'Personaje o Director'
 FROM dbo.pelicula a LEFT JOIN dbo.personaje b ON (a.CodPerProtagonista=b.CodPer)

 /* EJERCICIO 10
 Utilizando la sentencia adecuada del LMD de SQL, borra los personajes que cumplen las
 siguientes tres condiciones: que no sean protagonistas, que no participan en ninguna pel�cula y
 su nombre empiece por la letra E. La sentencia debe estar dentro de una transacci�n y cuando
 hayas comprobado que has realizado el ejercicio correctamente, debes deshacerla*/
 BEGIN TRANSACTION
 DELETE FROM dbo.personaje 
 WHERE NOT EXISTS(SELECT b.CodPerProtagonista 
					FROM dbo.pelicula b 
					WHERE personaje.CodPer=b.CodPerProtagonista) AND NOT EXISTS(SELECT 2 
																				FROM dbo.participa_pel c 
																				WHERE personaje.CodPer=c.CodPer) AND NomPer LIKE 'E%';
 ROLLBACK TRANSACTION;
 
 /* EJERCICIO 11
 Ha habido un error cuando se ha almacenado en la base de datos la fecha de nacimiento de
 Isabel S�nchez. Precisamente, Isabel S�nchez naci� el mismo d�a que Javier D�az. Utilizando la
 sentencia adecuada del LMD de SQL, modifica la fecha de nacimiento de Isabel S�nchez para
 que sea la misma que la de Javier D�az. Solo conoces los nombres de los actores. La sentencia
 debe estar dentro de una transacci�n y cuando hayas comprobado que has realizado el
 ejercicio correctamente, debes deshacerla*/
 BEGIN TRANSACTION
 UPDATE dbo.actor 
 SET Edad=(SELECT Edad FROM dbo.actor WHERE NomAct LIKE 'Javier')
 WHERE NomAct LIKE 'Isabel';
 ROLLBACK TRANSACTION;



SELECT *
FROM dbo.actor
SELECT *
FROM dbo.participa_pel
SELECT *
FROM dbo.pelicula
SELECT *
FROM dbo.personaje