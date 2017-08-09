package sample;

import java.util.Date;

public class Oficio {
    private String folio;
    private Date fechaOficio;
    private Date fechaRegistro;
    private String descripcion;
    private String observaciones;
    private int idDepartamento;
    private int idRemitente;

    public Oficio(String folio, Date fechaOficio, Date fechaRegistro, String descripcion, String observaciones, int idDepartamento, int idRemitente) {
        this.folio = folio;
        this.fechaOficio = fechaOficio;
        this.fechaRegistro = fechaRegistro;
        this.descripcion = descripcion;
        this.observaciones = observaciones;
        this.idDepartamento = idDepartamento;
        this.idRemitente = idRemitente;
    }
    public Oficio(){

    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Date getFechaOficio() {
        return fechaOficio;
    }

    public void setFechaOficio(Date fechaOficio) {
        this.fechaOficio = fechaOficio;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
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

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getIdRemitente() {
        return idRemitente;
    }

    public void setIdRemitente(int idRemitente) {
        this.idRemitente = idRemitente;
    }
}
