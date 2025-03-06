/*Sergii Butrii*/
CREATE DATABASE SERGII
GO
USE SERGII
GO
/* SOLUCIÓN EJERCICIO 2*/
CREATE TABLE piloto
(
CodPil INT NOT NULL,
Edad DATE NOT NULL,
NumDorsal INT NOT NULL CHECK (NumDorsal > 0),
NomPil VARCHAR(20) NOT NULL,
ApePil VARCHAR(40) NOT NULL,
Mentor INT,
PRIMARY KEY (CodPil),
FOREIGN KEY (Mentor) REFERENCES piloto(CodPil) ON DELETE NO ACTION
											   ON UPDATE NO ACTION
		
);

CREATE TABLE carrera
(
CodCar INT IDENTITY(10,10) NOT NULL,
NomCar VARCHAR(40) UNIQUE NOT NULL,
Fecha DATE NOT NULL DEFAULT (GETDATE()),
Ganador INT,
PRIMARY KEY (CodCar),
FOREIGN KEY (Ganador) REFERENCES piloto(CodPil) ON DELETE NO ACTION 
												ON UPDATE NO ACTION

);

CREATE TABLE participa 
(
CodPil INT NOT NULL,
CodCar INT NOT NULL,
PRIMARY KEY(CodPil, CodCar),
FOREIGN KEY (CodPil) REFERENCES piloto(CodPil) ON DELETE NO ACTION
											   ON UPDATE NO ACTION,
FOREIGN KEY (CodCar) REFERENCES carrera(CodCar) ON DELETE CASCADE 
											    ON UPDATE NO ACTION
);

/* SOLUCIÓN EJERCICIO 3*/

INSERT INTO piloto (CodPil, Edad, NumDorsal, NomPil, ApePil, Mentor)
VALUES (1,'2/1/2004', 1, 'nombre1', 'apellido1', NULL),
	   (2,'1/1/2005', 2, 'nombre2', 'apellido2', NULL),
	   (3,'2/1/2004', 3, 'nombre3', 'apellido3', NULL),
		(4,'13/1/2004', 4, 'Ana', 'Pérez Pérez', 1),
		(5,'1/1/2005', 5, 'nombre5', 'apellido5', 1),
		(6,'7/1/2005', 6, 'nombre6', 'apellido6', 2),
		(7,'27/1/2004', 7, 'nombre7', 'apellido7', 2),
		(8,'13/1/2004', 8, 'nombre8', 'apellido8', 2),
		(9,'6/1/2004', 9, 'nombre9', 'apellido9', 2),
		(10,'6/1/2005', 10, 'nombre10', 'apellido10', 2),
		(11,'13/1/2005', 11, 'nombre11', 'apellido11', 2),
		(12,'1/1/2005', 12, 'nombre12', 'apellido12', 2)

INSERT INTO carrera (NomCar, Fecha, Ganador)
VALUES ('carrera1', '1/5/2024', 1),
		('carrera2', '4/6/2024', 5),
		('carrera3', '1/11/2025', NULL),
		('carrera4', '1/12/2025', NULL),
		('carrera5', '2/12/2025', NULL)

INSERT INTO participa (CodPil, CodCar)
VALUES (1,10),
		(2,10),
		(3,10),
		(4,10),
		(1,20),
		(2,20),
		(3,20),
		(4,20),
		(5,20),
		(6,20),
		(7,20),
		(8,20),
		(9,30),
		(10,30)

/*SOLUCIÓN EJERCICIO 4*/
BEGIN TRANSACTION
UPDATE piloto 
SET Mentor = (SELECT Mentor FROM piloto WHERE NomPil = 'Ana' AND ApePil = 'Pérez Pérez')
WHERE CodPil BETWEEN 8 AND 12
ROLLBACK TRANSACTION

/*SOLUCIÓN EJERCICIO 5*/
BEGIN TRANSACTION 
DELETE FROM carrera
FROM carrera a LEFT JOIN participa b ON (a.CodCar=b.CodCar)
WHERE MONTH(Fecha)=12 AND YEAR(Fecha)=2025
ROLLBACK TRANSACTION
SELECT *
FROM carrera

/*SOLUCIÓN EJERCICIO 6*/
SELECT NomCar AS 'Nombre Carrera', FORMAT(Fecha,'dd-MMMM-yyyy') AS 'Fecha carrera', NomPil AS 'Nombre Piloto Ganador', ApePil AS 'Apellido Piloto Ganador'
FROM carrera a JOIN piloto b ON (a.Ganador=b.CodPil)
WHERE YEAR(Fecha) = '2024'
ORDER BY NomCar ASC;

/*SOLUCIÓN EJERCICIO 7*/
SELECT CodPil AS 'Ganador o Participante', NomPil AS 'Nombre Piloto', ApePil AS 'Apellido Piloto' 
FROM piloto a JOIN carrera b ON (a.CodPil=b.Ganador)
UNION
SELECT c.CodPil, c.NomPil, c.ApePil
FROM piloto c JOIN participa d ON (c.CodPil=d.CodPil)
WHERE NOT EXISTS(SELECT f.CodPil FROM carrera e JOIN piloto f ON (e.Ganador=f.CodPil) WHERE f.CodPil=c.CodPil)

/*SOLUCIÓN EJERCICIO 8*/
SELECT CONCAT(NomPil,' ',ApePil) AS 'Piloto', CASE WHEN GETDATE()< Fecha THEN 'No realizada aún' ELSE ISNULL(NomCar, 'No ha participado') END AS 'Carrera Participada'
FROM (piloto a LEFT JOIN participa b ON (a.CodPil=b.CodPil)) LEFT JOIN carrera c ON (c.CodCar=b.CodCar)

/*SOLUCIÓN EJERCICIO 9*/
CREATE VIEW vista1 (NomPil, ApePil, Numero_Carreras) 
AS 
SELECT NomPil, ApePil, ISNULL(COUNT(b.CodPil), '0')
FROM piloto a LEFT JOIN participa b ON (a.CodPil=b.CodPil)
GROUP BY NomPil,ApePil

SELECT * FROM vista1

/*SOLUCIÓN EJERCICIO 10*/
CREATE INDEX INDICE1
ON carrera (Fecha)
/*Tiene 3 indices, el que hemos creado. la PK y el UNIQUE. Sirven para acelerar las búsquedas*/