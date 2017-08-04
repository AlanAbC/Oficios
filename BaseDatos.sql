CREATE TABLE Oficios (
of_fechaOficio datetime NULL,
of_fechaRegistro datetime NULL,
of_departamento varchar(100) NULL,
of_descripcion varchar(500) NULL,
of_folio varchar(255) NOT NULL PRIMARY KEY,
of_observaciones varchar(500) NULL,
of_pdf varchar(255) NULL,
dep_id int(11) NULL,
res_id int(11) NULL
);

CREATE TABLE Departamentos (
dep_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
dep_nombre varchar(50) NULL,
dep_responsable varchar(100) NULL
);

CREATE TABLE Remitentes (
res_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
res_despartamento varchar(255) NULL,
res_responsable varchar(255) NULL
);


ALTER TABLE Departamentos ADD CONSTRAINT fk_Departamentos_Oficios_1 FOREIGN KEY (dep_id) REFERENCES Oficios (dep_id);
ALTER TABLE Remitentes ADD CONSTRAINT fk_Remitentes_Oficios_1 FOREIGN KEY (res_id) REFERENCES Oficios (res_id);

