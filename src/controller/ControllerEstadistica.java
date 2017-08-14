package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.Main;
import model.Departamento;
import model.Estadistica;
import model.Remitente;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by mansi on 13/08/2017.
 */
public class ControllerEstadistica implements Initializable{

    //Declaración de objetos
    private ArrayList<Departamento> arrayDepartamentos;
    private ArrayList<Remitente> arrayRemitentes;
    private ArrayList<Estadistica> estadisticas;

    @FXML
    private Label estTotal;

    @FXML
    private ListView<String> listaEstDep;

    @FXML
    private ListView<String> listaEstRem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert estTotal != null : "fx:id=estTotal";
        assert listaEstDep != null : "fx:id=listaEstDep";
        assert listaEstRem != null : "fx:id=listaEstRem";
        //estTotal = Main.db.est_total();
        llenarListViewDep();
        llenarListViewRem();

    }

    private void llenarListViewDep() {
        estadisticas = Main.db.estadisticasDep();
        listaEstDep.getItems().clear();
        if(estadisticas.size() > 0) {
            for (Estadistica e : estadisticas){
                listaEstDep.getItems().add("Departamento:  " +  e.getDepartamento() + "             "
                        + "#Oficios:   " + e.getNo_folios() + "\n" );
            }
        }
    }

    private void llenarListViewRem() {
        estadisticas = Main.db.estadisticasRem();
        listaEstRem.getItems().clear();
        if(estadisticas.size() > 0) {
            for (Estadistica e : estadisticas){
                listaEstRem.getItems().add("Departamento:  " +  e.getDepartamento() + "             "
                        + "#Oficios:   " + e.getNo_folios() + "\n" );
            }
        }
    }

}
