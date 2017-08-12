package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.Main;
import model.Remitente;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerRemitentes implements Initializable {

    //Declaracion de objetos
    //Arraylist Remitentes
    private ArrayList<Remitente> remitentes;

    @FXML
    private ListView<String> listRemitentes;

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
        assert listRemitentes != null : "fx:id=listDepartamentos";
        assert nombreNuevo != null : "fx:id=nombreNuevo";
        assert encargadoNuevo != null : "fx:id=encargadoNuevo";
        assert nombreEditar != null : "fx:id=nombreEditar";
        assert encargadoEditar != null : "fx:id=encargadoEditar";
        assert agregar != null : "fx:id=agregar";
        assert eliminar != null : "fx:id=eliminar";
        assert editar != null : "fx:id=editar";

        //Funcion para llenar listview
        llenarList();

        //Funcion para asignar todos los listener a los botones
        listenersBotones();
    }

    //Funcion para asignar todos los listener a los botones
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
                Remitente nuevoRem = new Remitente();
                nuevoRem.setNombre(nombreNuevo.getText());
                nuevoRem.setResponsable(encargadoNuevo.getText());

                // Insercion del nuevo departamento
                String respuesta = Main.db.setRemitentes(nuevoRem);

                // Comprobar registro correcto
                if(respuesta.equals("1")){
                    //Mensaje de insersion correcta
                    Alert correcto = new Alert(Alert.AlertType.CONFIRMATION);
                    correcto.setTitle("Correcto");
                    correcto.setHeaderText("Operacion correcta");
                    correcto.setContentText("Se guardo correctamente el remitentes");
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
                    error.setHeaderText("No se pudo guardar el remitente");
                    error.setContentText("Error: " + respuesta);
                    error.showAndWait();
                }
            }
        });

        editar.setOnAction(event -> {

            // Creacion de alerta para campos faltantes
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Datos faltantes");

            //Creacion de alerta para confirmacion de accion
            Alert alertc = new Alert(Alert.AlertType.CONFIRMATION);
            alertc.setTitle("Confirmacion");
            alertc.setHeaderText("Confirmación de actualización");
            alertc.setContentText("¿Seguro que deseas modificar este elemento?");

            // Comprobaciones de campos vacios
            if(nombreEditar.getText().equals("") && encargadoEditar.getText().equals("")){
                alert.setContentText("Falta ingresar algun dato");
                alert.showAndWait();
            }else{
                // Creacion del nuevo objeto de remitente
                Remitente nuevoRem = new Remitente();
                nuevoRem.setNombre(nombreEditar.getText());
                nuevoRem.setResponsable(encargadoEditar.getText());

                //Despliegue de alerta de confirmacion
                Optional<ButtonType> result =alertc.showAndWait();
                if(result.get() == ButtonType.OK){
                    // Insercion del update de remitente
                    String respuesta = Main.db.updateRemitentes(nuevoRem);

                    // Comprobar registro correcto
                    if(respuesta.equals("1")){
                        //Mensaje de insersion correcta
                        Alert correcto = new Alert(Alert.AlertType.CONFIRMATION);
                        correcto.setTitle("Correcto");
                        correcto.setHeaderText("Operacion correcta");
                        correcto.setContentText("Se guardo correctamente el remitentes");
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
                        error.setHeaderText("No se pudo guardar el remitente");
                        error.setContentText("Error: " + respuesta);
                        error.showAndWait();
                    }
                }else{
                    //Aqui se renderizara la pagina como se muestra en un inicio
                    nombreNuevo.setText("");
                    encargadoNuevo.setText("");

                }

            }
        });
    }

    //Funcion para llenar listview
    private void llenarList() {
        remitentes = Main.db.getRemitentes();
        listRemitentes.getItems().clear();
        for(Remitente r : remitentes) {
            listRemitentes.getItems().add(r.getNombre() + " - " + r.getResponsable());
        }
    }
}
