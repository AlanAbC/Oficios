package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.Main;
import model.Departamento;
import model.Estadistica;
import model.Remitente;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by mansi on 13/08/2017.
 */
public class ControllerEstadistica implements Initializable{

    //Declaración de objetos
    private ArrayList<Departamento> arrayDepartamentos;
    private ArrayList<Remitente> arrayRemitentes;

    @FXML
    private Label estTotal;

    @FXML
    private ListView<String> listaEstDep;

    @FXML
    private ListView<String> listaEstRem;

    @FXML
    private DatePicker fechaInicio;

    @FXML
    private DatePicker fechaFin;

    @FXML
    private Button filtrar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert estTotal != null : "fx:id=estTotal";
        assert listaEstDep != null : "fx:id=listaEstDep";
        assert listaEstRem != null : "fx:id=listaEstRem";
        assert fechaInicio != null : "fx:id=fechaInicio";
        assert fechaFin != null : "fx:id=fechaFin";
        assert filtrar != null : "fx:id=filtrar";
        llenadoInicial();
        listener();
    }

    private void listener() {
        filtrar.setOnAction(event -> {

            // Creacion de alerta para campos faltantes
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Datos faltantes");

            if(fechaInicio.getValue() == null){
                alert.setContentText("Falta ingresar la fecha del inicio para el filtro");
                alert.showAndWait();
            }else if(fechaFin.getValue() == null){
                alert.setContentText("Falta ingresar la fecha del fin para el filtro");
                alert.showAndWait();
            }else{
                LocalDate inicio = fechaInicio.getValue();
                LocalDate fin = fechaFin.getValue();

                ArrayList<Estadistica> depFiltro = Main.db.estadisticasDepFiltro(inicio, fin);
                ArrayList<Estadistica> remFiltro = Main.db.estadisticasRemFiltro(inicio, fin);
                int totalFiltro = Main.db.est_totalFiltro(inicio, fin);

                llenarListDep(depFiltro);
                llenarListRem(remFiltro);
                llenarTotal(totalFiltro);
            }
        });
    }

    private void llenadoInicial() {
        ArrayList<Estadistica> depIniciales = Main.db.estadisticasDepInicial();
        ArrayList<Estadistica> remIniciales = Main.db.estadisticasRemInicial();
        int totalInicial = Main.db.est_totalInicial();
        llenarListDep(depIniciales);
        llenarListRem(remIniciales);
        llenarTotal(totalInicial);
    }


    private void llenarListDep(ArrayList<Estadistica> departamentos) {
        listaEstDep.getItems().clear();
        if(departamentos.size() > 0) {
            for (Estadistica e : departamentos){
                listaEstDep.getItems().add("Departamento:  " +  e.getDepartamento() + "\n"
                        + "Número de oficios:   " + e.getNo_folios());
            }
        }
    }

    private void llenarListRem(ArrayList<Estadistica> remitentes) {
        listaEstRem.getItems().clear();
        if(remitentes.size() > 0) {
            for (Estadistica e : remitentes){
                listaEstRem.getItems().add("Remitente:  " +  e.getDepartamento() + "\n"
                        + "Número de oficios:   " + e.getNo_folios());
            }
        }
    }

    private void llenarTotal(int total){
        estTotal.setText("Total de Folios: " + total);
    }
}
