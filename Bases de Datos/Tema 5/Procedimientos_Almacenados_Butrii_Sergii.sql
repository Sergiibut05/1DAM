CREATE DATABASE cursos
GO
USE cursos

CREATE TABLE curso
(CodCurso INT PRIMARY KEY,
 NomCurso VARCHAR(50) NOT NULL,
 Horas INT,
 Fecha DATE
 );
 
 CREATE TABLE trabajador
(CodTrab int PRIMARY KEY,
 NomTrab VARCHAR(50) NOT NULL,
 APeTrab VARCHAR(50) NOT NULL,
 FechNac DATE NOT NULL,
 Salario MONEY NOT NULL
 );
 	
CREATE TABLE cursado
(CodCursado INT PRIMARY KEY,
 CodCurso INT NOT NULL FOREIGN KEY REFERENCES curso(CodCurso) ON DELETE NO ACTION ON UPDATE CASCADE,
 CodTrab INT NOT NULL FOREIGN KEY REFERENCES trabajador(CodTrab) ON DELETE NO ACTION ON UPDATE CASCADE,
 Apto CHAR(1) NULL
 );
 

 /*Ejemplos de filas para las tablas*/
 -- Insertando en la tabla 'curso'
INSERT INTO curso (CodCurso, NomCurso, Horas, Fecha) 
VALUES 
(101, 'Curso de SQL Básico', 40, '2025-04-01'),
(102, 'Curso de Programación en Python', 60, '2025-05-10');

-- Insertando en la tabla 'trabajador'
INSERT INTO trabajador (CodTrab, NomTrab, APeTrab, FechNac, Salario)
VALUES 
(1, 'Juan', 'Pérez', '1990-07-15', 2500.00),
(2, 'Ana', 'Gómez', '1985-11-23', 3200.00);

INSERT INTO trabajador (CodTrab, NomTrab, APeTrab, FechNac, Salario)
VALUES 
(4, 'Antonio', 'Salces', '1965-11-23', 3200.00);
-- Insertando en la tabla 'cursado'
INSERT INTO cursado (CodCursado, CodCurso, CodTrab, Apto)
VALUES 
(3, 101, 1, NULL),
(4, 102, 2, NULL);

INSERT INTO cursado (CodCursado, CodCurso, CodTrab, Apto)
VALUES
(2, 101, 2, 'N'),
(1, 102, 1, 'S');


 /*Ejercicio 1*/
CREATE PROCEDURE proc1
AS
BEGIN
SELECT NomTrab,ApeTrab,Salario
FROM trabajador
WHERE YEAR(FechNac)<1970
END;

EXEC proc1;

/*Ejercicio 2*/
CREATE OR ALTER PROCEDURE proc2
AS
BEGIN
EXEC proc1;
PRINT('Finalizado');
END;

EXEC proc2;

/*Ejercicio 3*/
CREATE OR ALTER PROCEDURE proc3
AS
BEGIN
SELECT NomCurso, NomTrab, APeTrab, ISNULL(Apto, '-') AS Apto
FROM trabajador a JOIN cursado b ON (a.CodTrab=b.CodCursado) JOIN curso c ON (c.CodCurso=b.CodCurso)
END;

EXEC proc3;

/*Ejercicio 4*/
UPDATE curso SET Fecha='2024-05-01' WHERE CodCurso=102;

CREATE OR ALTER PROCEDURE proc4 @NOMBRECURSO VARCHAR(50), @NUMERO INT OUTPUT
AS
BEGIN
SELECT @NUMERO = COUNT(*)
FROM cursado a JOIN curso b ON (a.CodCurso=b.CodCurso)
WHERE NomCurso LIKE @NOMBRECURSO AND Fecha<CAST(GETDATE() AS DATE);
PRINT 'El curso '+@NOMBRECURSO+' ha sido realizado por '+CAST(@NUMERO AS VARCHAR(10))+' trabajadores';
END;

DECLARE @NUM INT;
EXEC proc4 'Curso de Programación en Python', @NUM OUTPUT;

/*Ejercicio 5*/
CREATE OR ALTER PROCEDURE proc5 @CODTRABAJADOR INT, @NUMERO INT OUTPUT
AS
BEGIN
SELECT @NUMERO = COUNT(*)
FROM cursado
WHERE CodTrab = @CODTRABAJADOR AND Apto LIKE 'S';
END;


DECLARE @NUM INT;
EXEC proc5 1, @NUM OUTPUT;
PRINT @NUM;

/*Ejercicio 6*/
CREATE OR ALTER PROCEDURE proc6 
AS
BEGIN
IF (SELECT COUNT(*) FROM curso WHERE Fecha IS NULL) > 0
BEGIN
	RETURN 1
END
ELSE
BEGIN 
	RETURN 2
END
END;

DECLARE @VALOR INT;
EXEC @VALOR=proc6;
PRINT @VALOR;


INSERT INTO curso 
VALUES 
(103,'Curso JavaScript',30, NULL);

/*Ejercicio 7*/
CREATE OR ALTER PROCEDURE proc7v1 @NOMBRECURSO VARCHAR(50)
AS
BEGIN
IF (SELECT COUNT(*) FROM curso WHERE NomCurso LIKE @NOMBRECURSO) > 0
BEGIN 
	PRINT ('Existe El curso'+@NOMBRECURSO)
END
ELSE
BEGIN
	PRINT ('No existe el curso')
END
END;

EXEC proc7v1 'Curso de SQL Básico'

CREATE OR ALTER PROCEDURE proc7v2 @NOMBRECURSO VARCHAR(50)
AS
BEGIN
IF (SELECT COUNT(*) FROM curso WHERE NomCurso LIKE @NOMBRECURSO) > 0
BEGIN 
	RETURN 1
END
ELSE
BEGIN
	RETURN 2
END
END;

DECLARE @VALOR INT;
EXEC @VALOR=proc7v2 'Curso de SQL Básico'
PRINT @VALOR

CREATE OR ALTER PROCEDURE proc7v3 @NOMBRECURSO VARCHAR(50), @VALORSAL VARCHAR(50) OUTPUT
AS
BEGIN
IF (SELECT COUNT(*) FROM curso WHERE NomCurso LIKE @NOMBRECURSO) > 0
BEGIN 
	SET @VALORSAL='Existe El '+@NOMBRECURSO;
END
ELSE
BEGIN
	SET @VALORSAL='No existe El '+@NOMBRECURSO;
END
END;

DECLARE @VALOR VARCHAR(50);
EXEC proc7v3 'Curso de SQL Básico', @VALOR OUTPUT
PRINT @VALOR

/*Ejercicio 11*/
/*A*/
CREATE TABLE Personas (
    DNI VARCHAR(9) CHECK (DNI LIKE '[0-9]%' AND LEN(DNI) = 9),  -- El DNI tendrá 8 dígitos seguidos de una letra
    Nombre VARCHAR(100),
    Apellidos VARCHAR(100)
);

-- Insertar algunos datos de prueba con REPLICATE para generar los 8 dígitos
INSERT INTO Personas (DNI, Nombre, Apellidos)
VALUES 
    (CONCAT(REPLICATE('1', 8), 'A'), 'Juan', 'Pérez'), 
    (CONCAT(REPLICATE('2', 8), 'B'), 'María', 'González'),
    (CONCAT(REPLICATE('3', 8), 'C'), 'Carlos', 'López');


/*B*/
ALTER TABLE Personas
ADD NuevoCampo INT;

-- Procedimiento almacenado para añadir valores únicos sin ROW_NUMBER
CREATE PROCEDURE AñadirValorUnicoSimple
AS
BEGIN
    DECLARE @Valor INT = 1; 

    UPDATE Personas
    SET NuevoCampo = @Valor, 
        @Valor = @Valor + 1 
    WHERE NuevoCampo IS NULL;  
END;


SELECT * FROM Personas;
EXEC AñadirValorUnicoSimple;
SELECT * FROM Personas;

/*C*/
ALTER TABLE Personas
ADD CONSTRAINT PK_Personas_NuevoCampo PRIMARY KEY (NuevoCampo);


SELECT * FROM cursado;
SELECT * FROM trabajador;
SELECT * FROM curso;