package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerDepartamentos implements Initializable{

    // Declaracion de objetos y listas de onjetos
    private DB db;
    private ArrayList<Departamento> departamentos;

    @FXML
    private ListView<String> listDepartamentos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        db = new DB();
        assert listDepartamentos != null : "fx:id=listDepartamentos";
        llenarList();
    }

    private void llenarList() {
        departamentos = db.getDepartamentos();
        listDepartamentos.getItems().clear();
        for(Departamento d : departamentos) {
            listDepartamentos.getItems().add(d.getNombre() + " - " + d.getResponsable());
        }
    }

}
