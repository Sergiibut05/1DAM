CREATE DATABASE ejercicios_LMD
GO
USE ejercicios_LMD
-- Creación de la tabla 'actor'
CREATE TABLE actor (
    CodAct INT PRIMARY KEY,
    NomAct VARCHAR(45) NOT NULL,
    ApeAct VARCHAR(90) NOT NULL,
    Edad DATE NOT NULL
);
-- Creación de la tabla 'personaje'
CREATE TABLE personaje (
    CodPer INT PRIMARY KEY,
    NomPer VARCHAR(45) NOT NULL,
    CodAct INT FOREIGN KEY REFERENCES actor(CodAct)
);
-- Creación de la tabla 'pelicula'
CREATE TABLE pelicula (
    CodPel INT PRIMARY KEY,
    Titulo VARCHAR(100) NOT NULL,
    Director VARCHAR(100) NOT NULL,
    Lanzamiento SMALLINT NOT NULL,
	CodPerProtagonista INT FOREIGN KEY REFERENCES personaje (CodPer)
);
-- Creación de la tabla 'participa_pel'
CREATE TABLE participa_pel (
    CodPel INT FOREIGN KEY REFERENCES pelicula(CodPel),
    CodPer INT FOREIGN KEY REFERENCES personaje(CodPer),
    PRIMARY KEY (CodPel, CodPer)
);

INSERT INTO actor (CodAct, NomAct, ApeAct, Edad) VALUES
(1, 'Juan', 'Gómez', '1980-01-01'),
(2, 'María', 'López', '1982-02-02'),
(3, 'Carlos', 'Fernández', '1984-03-03'),
(4, 'Sofía', 'Rodríguez', '1986-04-04'),
(5, 'Pedro', 'Martínez', '1988-05-05'),
(6, 'Ana', 'García', '1990-06-06'),
(7, 'Luis', 'Hernández', '1992-07-07'),
(8, 'Laura', 'Pérez', '2006-01-07'),
(9, 'Javier', 'Díaz', '2008-01-09'),
(10, 'Isabel', 'Sánchez', '2009-10-10');

INSERT INTO personaje (CodPer, NomPer, CodAct) VALUES
(1, 'Elara de Eldor', 1),
(2, 'Tobin el Firme', 2),
(3, 'Mira de Miradel', 3),
(4, 'Corvus el Valiente', 4),
(5, 'Elysa de Lyr', 5),
(6, 'Griffin el Audaz', 6),
(7, 'Soraya la Silenciosa', 7),
(8, 'Dax de Dunlow', 8),
(9, 'Evelyn la de Ojo de Águila', 9),
(10, 'Kael el Errante', 10);

INSERT INTO pelicula (CodPel, Titulo, Director, Lanzamiento, CodPerProtagonista) VALUES
(1, 'Aventuras en el Bosque Encantado', 'Alejandro Sánchez', 2019, 1),
(2, 'El Misterio del Castillo de la Luna', 'Isabella Martínez', 2018, 1),
(3, 'La Búsqueda del Tesoro Perdido', 'Carlos Rodríguez', 2020, 2),
(4, 'Secretos en la Isla de Coral', 'Lucía Fernández', 2023, 2),
(5, 'Secretos en la Isla de Coral II', 'Lucía Fernández', 2024, 2);


INSERT INTO participa_pel (CodPel, CodPer) VALUES
(1, 2),(1, 3),(1, 4),(1, 5),
(2, 2),(2, 4),(2, 6),(2, 8),
(3, 1),(3, 3),(3, 4),(3, 5),(3, 6),
(4, 1),(4, 3),(4, 4),(4, 5),(4, 6),(4,7),(4,8);



