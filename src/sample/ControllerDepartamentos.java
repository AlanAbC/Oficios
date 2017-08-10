package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerDepartamentos implements Initializable{

    // Declaracion de objetos y listas de onjetos
    //private DB db;
    private ArrayList<Departamento> departamentos;

    @FXML
    private ListView<String> listDepartamentos;

    @FXML
    private TextField nombreNuevo;

    @FXML
    private TextField encargadoNuevo;

    @FXML
    private TextField nombreEditar;

    @FXML
    private TextField encargadoEditar;

    @FXML
    private Button agregar;

    @FXML
    private Button eliminar;

    @FXML
    private Button editar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //db = new DB();
        assert listDepartamentos != null : "fx:id=listDepartamentos";
        assert nombreNuevo != null : "fx:id=nombreNuevo";
        assert encargadoNuevo != null : "fx:id=encargadoNuevo";
        assert nombreEditar != null : "fx:id=nombreEditar";
        assert encargadoEditar != null : "fx:id=encargadoEditar";
        assert agregar != null : "fx:id=agregar";
        assert eliminar != null : "fx:id=eliminar";
        assert editar != null : "fx:id=editar";
        llenarList();
    }

    private void llenarList() {
        departamentos = Main.db.getDepartamentos();
        listDepartamentos.getItems().clear();
        for(Departamento d : departamentos) {
            listDepartamentos.getItems().add(d.getNombre() + " - " + d.getResponsable());
        }
    }

}
