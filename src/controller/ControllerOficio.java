package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.Main;
import model.Departamento;
import model.Oficio;
import model.Remitente;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerOficio implements Initializable{

    // Declaracion de objetos
    private ArrayList<Oficio> oficios;
    private ArrayList<Departamento> departamentos;
    private ArrayList<Remitente> remitentes;
    private Oficio oficioEditar;

    @FXML
    private ListView<String> listaOficios;

    @FXML
    private ComboBox<String> comboDep;

    @FXML
    private ComboBox<String> comboRem;

    @FXML
    private TextField folioOficio;

    @FXML
    private DatePicker fechaOficio;

    @FXML
    private TextArea desOficio;

    @FXML
    private TextArea obsOficio;

    @FXML
    private Button editar;

    @FXML
    private Button eliminar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert listaOficios != null : "fx:id=listaOficios";
        assert comboDep != null : "fx:id=comboDep";
        assert comboRem != null : "fx:id=comboRem";
        assert folioOficio != null : "fx:id=folioOficio";
        assert fechaOficio != null : "fx:id=fechaOficio";
        assert desOficio != null : "fx:id=desOficio";
        assert obsOficio != null : "fx:id=obsOficio";
        assert editar != null : "fx:id=aceptar";
        assert eliminar != null : "fx:id=eliminar";
        llenarListView();
        loadComboDep();
        loadComboRem();
        listenersBotones();
        listenerListView();
        desactivar();
    }

    private void llenarListView() {
        oficios = Main.db.getOficios();
        listaOficios.getItems().clear();
        for(Oficio o : oficios) {
            listaOficios.getItems().add("Folio: " + o.getFolio() + "\n" +
                    "Fecha: " + o.getFechaOficio().toString() + "\n" +
                    "Departamento: " + o.getDepartamento().getNombre());
        }
    }

    private void listenersBotones() {

    }

    private void listenerListView(){
        listaOficios.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int index = listaOficios.getSelectionModel().getSelectedIndex();
                System.out.println(index);
                if(index <= 0){
                    oficioEditar = oficios.get(index);
                    editarEliminar();
                }
            }
        });
    }

    private void editarEliminar() {
        comboDep.getSelectionModel().select(oficioEditar.getDepartamento().getNombre());
        comboRem.getSelectionModel().select(oficioEditar.getRemitente().getNombre());
        folioOficio.setText(oficioEditar.getFolio());
        fechaOficio.setValue(oficioEditar.getFechaOficio());
        desOficio.setText(oficioEditar.getDescripcion());
        obsOficio.setText(oficioEditar.getObservaciones());
        activar();
    }

    private void activar() {
        comboDep.setDisable(false);
        comboRem.setDisable(false);
        folioOficio.setDisable(false);
        fechaOficio.setDisable(false);
        desOficio.setDisable(false);
        obsOficio.setDisable(false);
        editar.setDisable(false);
        eliminar.setDisable(false);
    }

    private void desactivar(){
        comboDep.setDisable(true);
        comboRem.setDisable(true);
        folioOficio.setDisable(true);
        fechaOficio.setDisable(true);
        desOficio.setDisable(true);
        obsOficio.setDisable(true);
        editar.setDisable(true);
        eliminar.setDisable(true);
    }

    private void loadComboRem() {
        remitentes = Main.db.getRemitentes();
        comboRem.getItems().clear();
        for(Remitente r : remitentes) {
            comboRem.getItems().add(r.getNombre());
        }
    }

    private void loadComboDep() {
        departamentos = Main.db.getDepartamentos();
        comboDep.getItems().clear();
        for(Departamento d : departamentos) {
            comboDep.getItems().add(d.getNombre());
        }
    }

}
