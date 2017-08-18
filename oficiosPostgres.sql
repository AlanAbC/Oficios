ALTER TABLE "Oficios" DROP CONSTRAINT "fk_Oficios_Departamentos_1";
ALTER TABLE "Oficios" DROP CONSTRAINT "fk_Oficios_Remitentes_1";

DROP TABLE "Oficios";
DROP TABLE "Departamentos";
DROP TABLE "Remitentes";

CREATE TABLE "Oficios" (
"of_fechaOficio" timestamp(255),
"of_fechaRegistro" timestamp(255),
"of_descripcion" varchar(500),
"of_folio" varchar(255) NOT NULL,
"of_observaciones" varchar(500),
"dep_id" int4,
"res_id" int4,
PRIMARY KEY ("of_folio")
);

CREATE TABLE "Departamentos" (
"dep_id" int4 NOT NULL,
"dep_nombre" varchar(50),
"dep_responsable" varchar(100),
PRIMARY KEY ("dep_id")
);

CREATE TABLE "Remitentes" (
"res_id" int4 NOT NULL,
"res_despartamento" varchar(255),
"res_responsable" varchar(255),
PRIMARY KEY ("res_id")
);


ALTER TABLE "Oficios" ADD CONSTRAINT "fk_Oficios_Departamentos_1" FOREIGN KEY ("dep_id") REFERENCES "Departamentos" ("dep_id");
ALTER TABLE "Oficios" ADD CONSTRAINT "fk_Oficios_Remitentes_1" FOREIGN KEY ("res_id") REFERENCES "Remitentes" ("res_id");
