--Sergii Butrii
CREATE DATABASE ejercicio_alter;
GO
USE ejercicio_alter;

CREATE TABLE LibroOriginal (
ISBN CHAR(10),
NumeroCopias INT,
NombreEditorial VARCHAR(40),
TituloLibro VARCHAR(60),
CONSTRAINT PK_ISBN PRIMARY KEY (ISBN)
);

INSERT INTO LibroOriginal (ISBN, NumeroCopias, NombreEditorial, TituloLibro)
VALUES 
('1234567890', 5, 'Editorial ABC', 'El Gran Viaje'),
('0987654321', 10, 'Editorial XYZ', 'La Aventura Perdida'),
('1122334455', 8, 'Editorial Alfa', 'El Último Susurro'),
('2233445566', 12, 'Editorial Beta', 'Los Ecos del Futuro'),
('3344556677', 3, 'Editorial Gamma', 'La Ciudad Olvidada');

CREATE PROCEDURE procnorm 
AS
BEGIN

CREATE TABLE Editorial (
CodEdi INT IDENTITY(1,1)
PRIMARY KEY (CodEdi)
);

ALTER TABLE Editorial
ADD NombreEditorial VARCHAR(40);

INSERT INTO Editorial
SELECT NombreEditorial
FROM LibroOriginal;

ALTER TABLE LibroOriginal
ADD CodEdi INT;


UPDATE LibroOriginal
SET CodEdi = b.CodEdi
FROM LibroOriginal a JOIN Editorial b ON a.NombreEditorial = b.NombreEditorial;

CREATE TABLE Titulo (
CodTit INT IDENTITY(1,1),
TituloLibro VARCHAR(60),
PRIMARY KEY (CodTit)
);

INSERT INTO Titulo
SELECT TituloLibro
FROM LibroOriginal;

ALTER TABLE LibroOriginal
ADD CodTit INT;

UPDATE LibroOriginal
SET CodTit = b.CodTit
FROM LibroOriginal a JOIN Titulo b ON a.TituloLibro = b.TituloLibro;

ALTER TABLE LibroOriginal
DROP COLUMN NombreEditorial, TituloLibro;

ALTER TABLE LibroOriginal
ADD CONSTRAINT FKCodTiti FOREIGN KEY (CodTit) REFERENCES Titulo(CodTit);

ALTER TABLE LibroOriginal
ADD CONSTRAINT FKCodEdi FOREIGN KEY (CodEdi) REFERENCES Editorial(CodEdi);

ALTER TABLE LibroOriginal
ADD CodLib INT IDENTITY NOT NULL;

ALTER TABLE LibroOriginal
DROP PK_ISBN

ALTER TABLE LibroOriginal
ADD CONSTRAINT PK_CodLib PRIMARY KEY (CodLib);

ALTER TABLE LibroOriginal
ALTER COLUMN ISBN CHAR(13) NOT NULL;

ALTER TABLE LibroOriginal
ADD CONSTRAINT Unique_ISBN UNIQUE (ISBN);

EXEC sp_rename 'LibroOriginal', 'Libro';

PRINT 'La base de datos ha sido normalizada con éxito';
END;
EXEC procnorm;

SELECT * FROM Libro;
SELECT * FROM Editorial;
SELECT * FROM Titulo;

/*Ejercicio 8*/
CREATE OR ALTER PROCEDURE proc8 @NOMBREEDITORIAL VARCHAR(60)
AS
BEGIN
IF (SELECT COUNT(*) FROM Editorial WHERE NombreEditorial LIKE @NOMBREEDITORIAL) > 0
BEGIN 
	PRINT('La Editorial '+@NOMBREEDITORIAL+' Existe')
END
ELSE
BEGIN
	PRINT('La Editorial '+@NOMBREEDITORIAL+' no Existe')
END
END;

EXEC proc8 'Editorial XYZ'

/*Ejercicio 9*/

CREATE OR ALTER PROCEDURE proc9 @NOMBREEDITORIAL VARCHAR(60), @VALOR VARCHAR(90) OUTPUT
AS
BEGIN
IF (SELECT COUNT(*) FROM Editorial WHERE NombreEditorial LIKE @NOMBREEDITORIAL) > 0
BEGIN 
	SET @VALOR = ('La Editorial '+@NOMBREEDITORIAL+' Existe')
END
ELSE
BEGIN
	SET @VALOR = ('La Editorial '+@NOMBREEDITORIAL+' no Existe')
END
END;

DECLARE @VALORr VARCHAR(90);
EXEC proc9 'Editorial XYZ', @VALORr OUTPUT
PRINT @VALORr

/*Ejercicio 10*/
CREATE OR ALTER PROCEDURE proc10 @NOMBREEDITORIAL VARCHAR(60)
AS
BEGIN
IF (SELECT COUNT(*) FROM Editorial WHERE NombreEditorial LIKE @NOMBREEDITORIAL) > 0
BEGIN 
	RETURN 1
END
ELSE
BEGIN
	RETURN 2
END
END;

DECLARE @VALORr INT;
EXEC @VALORr= proc10 'Editorial XYZ'
PRINT @VALORr