/*En Microsoft SQL Server, los triggers se pueden definir para 
 que se ejecuten despu�s de la instrucciones INSERT, DELETE o
 o UPDATE (estos son los triggers AFTER, que es como funcionan
 por defecto). Pero, tambi�n se pueden definir para que act�en
 en lugar de estas instrucciones (se conocen como triggers INSTEAD OF). 
 Sin embargo, SQL Server no soporta directamente los triggers 
 que se ejecutan antes de estos eventos, como s� lo hacen otros
 sistemas de gesti�n de bases de datos como PostgreSQL con sus 
 triggers BEFORE.*/

/*Un trigger AFTER se ejecuta justo despu�s de que se ha 
 completado la acci�n que lo activa, permitiendo revisar
 o cambiar los datos afectados por la acci�n original. 
 Por otro lado, un trigger INSTEAD OF se ejecuta en lugar 
 de la acci�n que lo activa, permitiendo personalizar 
 completamente el comportamiento de la acci�n, lo cual puede 
 ser �til para validar o modificar los datos antes de que se 
 realice la operaci�n original.*/

/*Si se necesita una funcionalidad similar a un trigger BEFORE,
 en SQL Server podr�as utilizar un trigger INSTEAD OF para 
 realizar validaciones o modificaciones antes de proceder 
 con la inserci�n, eliminaci�n o actualizaci�n manualmente 
 dentro del cuerpo del trigger. Esto implica que, dentro del 
 trigger INSTEAD OF, deber�s escribir expl�citamente la sentencia 
 para insertar, actualizar o eliminar los datos, ya que el trigger
 reemplaza la operaci�n original.*/

/*Queremos evitar la inserci�n de un email si ya existe,
  es decir, evitar que se repitan como hicimos con los 
  nombres de los cursos en el primer ejemplo.*/

CREATE DATABASE usuarios;
GO
USE usuarios;

CREATE TABLE usuario
  ( UsuarioID INT PRIMARY KEY,
    Nombre VARCHAR(50),
    Apellido VARCHAR(50),
    Email VARCHAR(100)
  );


CREATE OR ALTER TRIGGER trgEmpleadoInsert
ON usuario
INSTEAD OF INSERT
AS
BEGIN
    -- Evitar la inserci�n si el email ya existe
    IF NOT EXISTS (SELECT 1 FROM usuario u JOIN inserted i ON u.Email = i.Email)
    BEGIN
        -- Insertar el registro si el email no existe
        INSERT INTO usuario (UsuarioID, Nombre, Apellido, Email)
        SELECT UsuarioID, Nombre, Apellido, Email
        FROM inserted;
    END
    ELSE
    BEGIN
        -- Opcional: lanzar un error o registrar el intento de inserci�n fallido
        RAISERROR ('El email ya existe en la base de datos.', 16, 1);
	END
END;

--Comprobaciones:
INSERT INTO usuario (UsuarioID, Nombre, Apellido, Email)
VALUES (1, 'Juan', 'P�rez', 'juan.perez@example.com'),
       (2, 'Ana', 'Garc�a', 'ana.garcia@example.com');

SELECT * FROM usuario;

--Introducimos de nuevo el mismo email
INSERT INTO usuario (UsuarioID, Nombre, Apellido, Email)
VALUES (3, 'Antonio', 'Jim�mez', 'juan.perez@example.com');

INSERT INTO usuario (UsuarioID, Nombre, Apellido, Email)
VALUES (3, 'Antonio', 'Jim�mez', 'antonio.jimenez@example.com');
	
SELECT * FROM usuario;

