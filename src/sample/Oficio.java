package sample;

import java.time.LocalDate;
import java.util.Date;

public class Oficio {
    private String folio;
    private LocalDate fechaOficio;
    private LocalDate fechaRegistro;
    private String descripcion;
    private String observaciones;
    private Departamento departamento;
    private Remitente remitente;

    public Oficio(){

    }

    public Oficio(String folio, LocalDate fechaOficio, LocalDate fechaRegistro, String descripcion, String observaciones, Departamento departamento, Remitente remitente) {
        this.folio = folio;
        this.fechaOficio = fechaOficio;
        this.fechaRegistro = fechaRegistro;
        this.descripcion = descripcion;
        this.observaciones = observaciones;
        this.departamento = departamento;
        this.remitente = remitente;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public LocalDate getFechaOficio() {
        return fechaOficio;
    }

    public void setFechaOficio(LocalDate fechaOficio) {
        this.fechaOficio = fechaOficio;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Remitente getRemitente() {
        return remitente;
    }

    public void setRemitente(Remitente remitente) {
        this.remitente = remitente;
    }
}
