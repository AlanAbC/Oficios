package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerAgregarOficio implements Initializable {

    //Declaracion objetos
    private DB db;

    @FXML
    private ComboBox<String> comboDep;

    @FXML
    private ComboBox<String> comboRem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        db = new DB();
        assert comboDep != null : "fx:id=comboDep";
        assert comboRem != null : "fx:id=comboRem";
        loadComboDep();
        loadComboRem();
    }

    private void loadComboDep(){
        ArrayList<Departamento> departamentos = db.getDepartamentos();
        comboDep.getItems().clear();
        for(Departamento d : departamentos) {
            comboDep.getItems().add(d.getNombre());
        }
    }

    private void loadComboRem(){
        ArrayList<Remitente> remitentes = db.getRemitentes();
        comboRem.getItems().clear();
        for(Remitente r : remitentes) {
            comboRem.getItems().add(r.getNombre());
        }
    }
}
