--Ejemplos de FOREIGN KEY compuesta.

/*En el Diagrama Entidad - Relación la entidad modelo es débil.

A continuación, vamos a crear la tablas e introducir datos para: 
1. Relación con dependencia en existencia.
2. Relación con dependencia en identificación. En este caso,
   tendremos una FK compuesta.*/

CREATE DATABASE coches;
GO
USE coches;

--1. Ejemplo de entidad débil con dependencia en EXISTENCIA

CREATE TABLE marca
(
  CodMar INT NOT NULL,
  NomMar VARCHAR(50) NOT NULL,
  PRIMARY KEY (CodMar)
);

CREATE TABLE modelo 
( 
  CodMod INT NOT NULL,
  CodMar INT NOT NULL,
  NomMod VARCHAR(100) NOT NULL,
  PRIMARY KEY (CodMod),
  FOREIGN KEY (CodMar) REFERENCES marca(CodMar)
					   ON DELETE CASCADE --Por ser entidad débil.
					   ON UPDATE NO ACTION
);

CREATE TABLE especificacion
(
  CodEsp INT NOT NULL,
  Caballos INT NOT NULL,
  CodMod INT NOT NULL,
  PRIMARY KEY (CodEsp),
  FOREIGN KEY (CodMod) REFERENCES modelo(CodMod)
							   ON DELETE CASCADE
							   ON UPDATE NO ACTION
);

/*Siguiendo las normas de un entidad débil con dependencia
en EXISTENCIA insertamos datos en las tablas marca,
modelo y especificación*/

INSERT INTO marca (CodMar,NomMar)
VALUES (1, 'FORD'),
       (2, 'NISSAN');

INSERT INTO modelo (CodMod,CodMar, NomMod)
VALUES (1, 1, 'FOCUS'),
       (2, 1, 'PUMA'),
       (3, 2, 'QASHQAI'),
       (4, 2, 'JUKE');

INSERT INTO especificacion(CodEsp,Caballos,CodMod) 
VALUES (1, 2000, 2),
       (2, 3500, 2),
       (3, 2250, 4);

BEGIN TRANSACTION;

DELETE FROM marca
WHERE NomMar LIKE 'FORD';

SELECT * FROM marca;
SELECT * FROM modelo;
SELECT * FROM especificacion;

ROLLBACK TRANSACTION;

DROP TABLE especificacion, modelo, marca;

--Ejemplos de entidad débil con dependencia en IDENTIFICACIÓN.

CREATE TABLE marca
(
  CodMar INT NOT NULL,
  NomMar VARCHAR(50) NOT NULL,
  PRIMARY KEY (CodMar)
);

CREATE TABLE modelo 
( 
  CodMar INT NOT NULL,
  CodMod INT NOT NULL,
  NomMod VARCHAR(100) NOT NULL,
  PRIMARY KEY (CodMar, CodMod), --Por ser con dependencia en identificación.
  FOREIGN KEY (CodMar) REFERENCES marca(CodMar)
					   ON DELETE CASCADE --Por ser entidad débil.
					   ON UPDATE NO ACTION
);

CREATE TABLE especificacion
(
  CodEsp INT NOT NULL,
  Caballos INT NOT NULL,
  CodMar INT NOT NULL,
  CodMod INT NOT NULL,
  PRIMARY KEY (CodEsp),
  FOREIGN KEY (CodMar, CodMod) REFERENCES modelo(CodMar, CodMod)
							   ON DELETE CASCADE
							   ON UPDATE NO ACTION
);

/*Siguiendo las normas de un entidad débil con dependencia
en IDENTIFICACIÓN insertamos datos en las tablas marca,
modelo y especificacón*/

INSERT INTO marca (CodMar, NomMar)
VALUES (1, 'FORD'),
       (2, 'NISSAN');

INSERT INTO modelo (CodMar, CodMod, NomMod)
VALUES (1, 1, 'FOCUS'),
       (1, 2, 'PUMA'),
       (2, 1, 'QASHQAI'),
       (2, 2, 'JUKE');

INSERT INTO especificacion (CodEsp, Caballos,CodMar,CodMod)
VALUES (1, 2000, 1, 2),
       (2, 3500, 1, 2),
       (3, 2250, 2, 2);

BEGIN TRANSACTION;

DELETE FROM marca
WHERE NomMar LIKE 'FORD';

SELECT * FROM marca;
SELECT * FROM modelo;
SELECT * FROM especificacion;

ROLLBACK TRANSACTION;




