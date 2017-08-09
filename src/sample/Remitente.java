package sample;

public class Remitente {
    private int id;
    private String nombre;
    private String responsable;

    public Remitente(int id, String nombre, String responsable) {
        this.id = id;
        this.nombre = nombre;
        this.responsable = responsable;
    }
    public Remitente(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
}
