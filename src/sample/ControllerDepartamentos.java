package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
        listenersBotones();
    }

    private void listenersBotones() {
        agregar.setOnAction(event -> {

            // Creacion de alerta para campos faltantes
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Datos faltantes");

            // Comprobaciones de campos vacios
            if(nombreNuevo.getText().equals("")){
                alert.setContentText("Falta ingresar el nombre del departamento");
                alert.showAndWait();
            }else if(encargadoNuevo.getText().equals("")){
                alert.setContentText("Falta ingresar un responsable del departamento");
                alert.showAndWait();
            }else{
                // Creacion del nuevo objeto de departamento
                Departamento nuevoDep = new Departamento();
                nuevoDep.setNombre(nombreNuevo.getText());
                nuevoDep.setResponsable(encargadoNuevo.getText());

                // Insercion del nuevo departamento
                String respuesta = Main.db.setDepartamento(nuevoDep);

                // Comprobar registro correcto
                if(respuesta.equals("1")){
                    //Mensaje de insersion correcta
                    Alert correcto = new Alert(Alert.AlertType.CONFIRMATION);
                    correcto.setTitle("Correcto");
                    correcto.setHeaderText("Operacion correcta");
                    correcto.setContentText("Se guardo correctamente el departamento");
                    correcto.showAndWait();

                    // Limpiar campos del formulario
                    nombreNuevo.setText("");
                    encargadoNuevo.setText("");

                    // Rellenar listview
                    llenarList();
                }else{
                    // Mensaje de error
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText("No se pudo guardar el departamento");
                    error.setContentText("Error: " + respuesta);
                    error.showAndWait();
                }
            }
        });
    }

    private void llenarList() {
        departamentos = Main.db.getDepartamentos();
        listDepartamentos.getItems().clear();
        for(Departamento d : departamentos) {
            listDepartamentos.getItems().add(d.getNombre() + " - " + d.getResponsable());
        }
    }

}
