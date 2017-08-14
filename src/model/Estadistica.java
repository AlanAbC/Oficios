package model;

/**
 * Created by mansi on 13/08/2017.
 */
public class Estadistica {

    private String departamento;
    private int no_folios;

    public Estadistica(){

    }

    public Estadistica(String departamento, int no_folios) {
        this.departamento = departamento;
        this.no_folios = no_folios;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getNo_folios() {
        return no_folios;
    }

    public void setNo_folios(int no_folios) {
        this.no_folios = no_folios;
    }

}
