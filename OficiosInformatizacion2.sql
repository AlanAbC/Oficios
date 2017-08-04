CREATE TABLE Oficios (
of_fechaOficio datetime NULL,
of_fechaRegistro datetime NULL,
of_departamento varchar(100) NULL,
of_descripcion varchar(500) NULL,
of_folio varchar(255) NOT NULL,
of_observaciones varchar(500) NULL,
of_pdf varchar(255) NULL,
dep_id int(11) NULL,
res_id int(11) NULL,
PRIMARY KEY (of_folio) 
);

CREATE TABLE Departamentos (
dep_id int(11) NOT NULL AUTO_INCREMENT,
dep_nombre varchar(50) NULL,
dep_responsable varchar(100) NULL,
PRIMARY KEY (dep_id) 
);

CREATE TABLE Remitentes (
res_id int(11) NOT NULL AUTO_INCREMENT,
res_despartamento varchar(255) NULL,
res_responsable varchar(255) NULL,
PRIMARY KEY (res_id) 
);


ALTER TABLE Oficios ADD CONSTRAINT fk_Oficios_Departamentos_1 FOREIGN KEY (dep_id) REFERENCES Departamentos (dep_id);
ALTER TABLE Oficios ADD CONSTRAINT fk_Oficios_Remitentes_1 FOREIGN KEY (res_id) REFERENCES Remitentes (res_id);

