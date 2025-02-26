CREATE DATABASE ejercicios_LMD
GO
USE ejercicios_LMD
-- Creaci�n de la tabla 'actor'
CREATE TABLE actor (
    CodAct INT PRIMARY KEY,
    NomAct VARCHAR(45) NOT NULL,
    ApeAct VARCHAR(90) NOT NULL,
    Edad DATE NOT NULL
);
-- Creaci�n de la tabla 'personaje'
CREATE TABLE personaje (
    CodPer INT PRIMARY KEY,
    NomPer VARCHAR(45) NOT NULL,
    CodAct INT FOREIGN KEY REFERENCES actor(CodAct)
);
-- Creaci�n de la tabla 'pelicula'
CREATE TABLE pelicula (
    CodPel INT PRIMARY KEY,
    Titulo VARCHAR(100) NOT NULL,
    Director VARCHAR(100) NOT NULL,
    Lanzamiento SMALLINT NOT NULL,
	CodPerProtagonista INT FOREIGN KEY REFERENCES personaje (CodPer)
);
-- Creaci�n de la tabla 'participa_pel'
CREATE TABLE participa_pel (
    CodPel INT FOREIGN KEY REFERENCES pelicula(CodPel),
    CodPer INT FOREIGN KEY REFERENCES personaje(CodPer),
    PRIMARY KEY (CodPel, CodPer)
);

INSERT INTO actor (CodAct, NomAct, ApeAct, Edad) VALUES
(1, 'Juan', 'G�mez', '1980-01-01'),
(2, 'Mar�a', 'L�pez', '1982-02-02'),
(3, 'Carlos', 'Fern�ndez', '1984-03-03'),
(4, 'Sof�a', 'Rodr�guez', '1986-04-04'),
(5, 'Pedro', 'Mart�nez', '1988-05-05'),
(6, 'Ana', 'Garc�a', '1990-06-06'),
(7, 'Luis', 'Hern�ndez', '1992-07-07'),
(8, 'Laura', 'P�rez', '2006-01-07'),
(9, 'Javier', 'D�az', '2008-01-09'),
(10, 'Isabel', 'S�nchez', '2009-10-10');

INSERT INTO personaje (CodPer, NomPer, CodAct) VALUES
(1, 'Elara de Eldor', 1),
(2, 'Tobin el Firme', 2),
(3, 'Mira de Miradel', 3),
(4, 'Corvus el Valiente', 4),
(5, 'Elysa de Lyr', 5),
(6, 'Griffin el Audaz', 6),
(7, 'Soraya la Silenciosa', 7),
(8, 'Dax de Dunlow', 8),
(9, 'Evelyn la de Ojo de �guila', 9),
(10, 'Kael el Errante', 10);

INSERT INTO pelicula (CodPel, Titulo, Director, Lanzamiento, CodPerProtagonista) VALUES
(1, 'Aventuras en el Bosque Encantado', 'Alejandro S�nchez', 2019, 1),
(2, 'El Misterio del Castillo de la Luna', 'Isabella Mart�nez', 2018, 1),
(3, 'La B�squeda del Tesoro Perdido', 'Carlos Rodr�guez', 2020, 2),
(4, 'Secretos en la Isla de Coral', 'Luc�a Fern�ndez', 2023, 2),
(5, 'Secretos en la Isla de Coral II', 'Luc�a Fern�ndez', 2024, 2);


INSERT INTO participa_pel (CodPel, CodPer) VALUES
(1, 2),(1, 3),(1, 4),(1, 5),
(2, 2),(2, 4),(2, 6),(2, 8),
(3, 1),(3, 3),(3, 4),(3, 5),(3, 6),
(4, 1),(4, 3),(4, 4),(4, 5),(4, 6),(4,7),(4,8);



