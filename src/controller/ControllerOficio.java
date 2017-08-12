package controller;

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

    private void loadComboRem() {
    }

    private void loadComboDep() {
    }

}
