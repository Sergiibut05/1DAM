USE ejercicios_LMD


/* EJERCICIO 1
 Obtener para cada película el título, el director y el nombre de los personajes (no protagonista)
 que participan en la misma. Si una película aún no tiene personajes que participan
 almacenados, el nombre de la película debe aparecer igualmente. Utiliza alias para el nombre de
 los personajes y en el resultado que salga el valor NULL, debes poner "Aún no se han añadido
 personajes.*/
 SELECT Titulo, Director, ISNULL(NomPer,'Aún no se han añadido personajes') AS 'NomPer'
 FROM (dbo.pelicula a LEFT JOIN dbo.participa_pel b ON (b.CodPel=a.CodPel)) LEFT JOIN dbo.personaje c ON (b.CodPer=c.CodPer)
 
 /* EJERCICIO 2
 Obtener el nombre de cada personaje y en cuántas películas ha participado, pero solo deben
 salir los personajes que hayan participado en más de dos películas. Utiliza alias para las
 columnas. Ordena por nombre de personaje descendentemente*/
 SELECT NomPer AS 'Personaje', COUNT(CodPel) AS 'Participación en Películas'
 FROM dbo.personaje a, dbo.participa_pel b
 WHERE a.CodPer= b.CodPer
 GROUP BY NomPer
 HAVING COUNT(CodPel)>2
 ORDER BY NomPer DESC;

 /* EJERCICIO 3
 Obtener el nombre y el apellido del actor más joven almacenado en la base de datos a día de
 hoy, junto con el nombre del personaje que interpreta, así como la edad que tiene el actor*/
 SELECT TOP(1) a.NomAct, a.ApeAct, ISNULL(b.NomPer,'No tiene personaje Vinculado') AS 'NomPer', DATEDIFF(YEAR, a.Edad, GETDATE()) AS 'Edad'
 FROM dbo.actor a LEFT JOIN dbo.personaje b ON (a.CodAct=b.CodAct)
 ORDER BY 4;

 /* EJERCICIO 4 
 Utilizando la sentencia adecuada del LMD de SQL, modifica la película que contiene la palabra
 "Bosque" (solo conoces esta palabra del título de la película), para que el año de lanzamiento
 sea igual al año de lanzamiento de la película más antigua almacenada en la base de datos. La
 sentencia debe estar dentro de una transacción y cuando hayas comprobado que has realizado
 el ejercicio correctamente, debes deshacerla*/
 BEGIN TRANSACTION;
 UPDATE dbo.pelicula SET Lanzamiento = (SELECT MIN(Lanzamiento) FROM dbo.pelicula)
 WHERE Titulo LIKE '%Bosque%';
 ROLLBACK TRANSACTION;
 
 /*EJERCICIO 5
 Borra las películas en las que aún no existe ningún personaje que participe. No tienes más
 información que la que se te indica en el enunciado. La sentencia debe estar dentro de una
 transacción y cuando hayas comprobado que has realizado el ejercicio correctamente, debes
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
 Para todos los actores mayores de edad a día de hoy, obtener el nombre y el apellido, la fecha de
 nacimiento en formato español, la correspondiente edad a día de hoy, junto con el nombre del
 personaje que interpreta. Utiliza alias para las columnas. Ordena por nombre de personaje
 descendentemente*/
 SELECT NomAct AS 'Nombre Actor', ApeAct AS 'Apellido Actor', FORMAT(Edad,'dd-MMMM-yyyy') AS 'Fecha Nacimiento', DATEDIFF(YEAR,Edad,GETDATE()) AS 'Edad', ISNULL(NomPer,'No tiene Personaje') AS 'Nombre Personaje'
 FROM dbo.actor a LEFT JOIN dbo.personaje b ON (a.CodAct=b.CodAct)
 WHERE DATEDIFF(YEAR,Edad,GETDATE())>17
 ORDER BY 1 DESC;

 /* EJERCICIO 7
 Obtener el nombre y el apellido de cada actor almacenado en la base de datos, junto con el
 nombre del personaje que interpreta y el título de las películas de las que es protagonista, si no
 es protagonista aún de ninguna película, en lugar de salir el valor NULL, debe salir el mensaje
 "No es protagonista”. Utiliza alias para el título de la película que protagonizan*/
 SELECT NomAct AS 'Nombre Actor', ApeAct AS 'Apellido Actor', NomPer AS 'Nombre Personaje', ISNULL(Titulo, 'No es protagonista') AS 'Películas de las que es protagonista'
 FROM (dbo.actor a LEFT JOIN dbo.personaje b ON (a.CodAct=b.CodAct)) LEFT JOIN dbo.pelicula c ON (b.CodPer=c.CodPerProtagonista)

 /* EJERCICIO 8
 Por cada película mostrar el título y la edad media de los actores que interpretan los personajes
 que participan en la misma (no tener en cuenta al personaje protagonista). La edad media debe
 salir con uno o dos decimales. Ordenar ascendentemente por nombre de película. Utiliza alias
 para la columna edad media*/
 SELECT Titulo, ROUND(AVG(CONVERT(FLOAT,DATEDIFF(YEAR, Edad, GETDATE()))),2)  AS 'Edad Media'
 FROM ((dbo.participa_pel a JOIN dbo.pelicula b ON (a.CodPel=b.CodPel)) JOIN dbo.personaje c ON (c.CodPer = a.CodPer)) JOIN dbo.actor d ON (d.CodAct = c.CodAct)
 GROUP BY Titulo
 ORDER BY Titulo ASC;

 /* EJERCICIO 9
 Obtener con una sola sentencia el título de todas las películas, el año de lanzamiento y nombre
 del personaje protagonista, para las películas con un año de lanzamiento anterior a 2020. Y para
 las películas de 2020 o posteriores, obtener el título de la película, el año de lanzamiento y el
 nombre del director. Ordenar ascendentemente por año de lanzamiento. Utiliza como alias de
 columna para el nombre del personaje protagonista o del director el texto: 'Personaje o Director'.*/
 SELECT Titulo, Lanzamiento, CASE WHEN Lanzamiento<2020 THEN NomPer ELSE Director END AS 'Personaje o Director'
 FROM dbo.pelicula a LEFT JOIN dbo.personaje b ON (a.CodPerProtagonista=b.CodPer)

 /* EJERCICIO 10
 Utilizando la sentencia adecuada del LMD de SQL, borra los personajes que cumplen las
 siguientes tres condiciones: que no sean protagonistas, que no participan en ninguna película y
 su nombre empiece por la letra E. La sentencia debe estar dentro de una transacción y cuando
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
 Isabel Sánchez. Precisamente, Isabel Sánchez nació el mismo día que Javier Díaz. Utilizando la
 sentencia adecuada del LMD de SQL, modifica la fecha de nacimiento de Isabel Sánchez para
 que sea la misma que la de Javier Díaz. Solo conoces los nombres de los actores. La sentencia
 debe estar dentro de una transacción y cuando hayas comprobado que has realizado el
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