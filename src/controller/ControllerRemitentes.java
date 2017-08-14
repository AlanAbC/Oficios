package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.Main;
import model.Departamento;
import model.Remitente;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerRemitentes implements Initializable {

    //Declaracion de objetos
    //Arraylist Remitentes
    private ArrayList<Departamento> arrayDepartamentos;
    private ArrayList<Remitente> arrayRemitentes;
    private Remitente remitenteEditar;

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
        assert listRemitentes != null : "fx:id=listRemitentes";
        assert nombreNuevo != null : "fx:id=nombreNuevo";
        assert encargadoNuevo != null : "fx:id=encargadoNuevo";
        assert nombreEditar != null : "fx:id=nombreEditar";
        assert encargadoEditar != null : "fx:id=encargadoEditar";
        assert agregar != null : "fx:id=agregar";
        assert eliminar != null : "fx:id=eliminar";
        assert editar != null : "fx:id=editar";
        remitenteEditar = new Remitente();
        desactivarEditarEliminar();
        llenarList();
        listenersBotones();
        listenerListView();
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
                alert.setContentText("Falta ingresar el nombre del remitente");
                alert.showAndWait();
            }else if(encargadoNuevo.getText().equals("")){
                alert.setContentText("Falta ingresar un responsable del remitente");
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
                    correcto.setContentText("Se guardo correctamente el remitente");
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

            // Comprobaciones de campos vacios
            if(nombreEditar.getText().equals("")){
                alert.setContentText("Falta ingresar el nuevo nombre del remitente");
                alert.showAndWait();
            }else if(encargadoEditar.getText().equals("")){
                alert.setContentText("Falta ingresar un nuevo responsable del remitente");
                alert.showAndWait();
            }else{
                //Creacion de alerta para confirmacion de accion
                Alert alertc = new Alert(Alert.AlertType.CONFIRMATION);
                alertc.setTitle("Confirmacion");
                alertc.setHeaderText("Confirmación de actualización");
                alertc.setContentText("¿Seguro que deseas modificar el remiente " + remitenteEditar.getNombre() + "?");

                Optional<ButtonType> result =alertc.showAndWait();

                if(result.get() == ButtonType.OK) {

                    // Creacion del nuevo objeto de departamento
                    Remitente editarRem = new Remitente();
                    editarRem.setId(remitenteEditar.getId());
                    editarRem.setNombre(nombreEditar.getText());
                    editarRem.setResponsable(encargadoEditar.getText());

                    // Insercion del nuevo departamento
                    String respuesta = Main.db.updateRemitentes(editarRem);

                    // Comprobar registro correcto
                    if (respuesta.equals("1")) {

                        //Mensaje de insersion correcta
                        Alert correcto = new Alert(Alert.AlertType.INFORMATION);
                        correcto.setTitle("Correcto");
                        correcto.setHeaderText("Operacion correcta");
                        correcto.setContentText("Se actualizó correctamente el remitente");
                        correcto.showAndWait();

                        // Limpiar campos del formulario
                        nombreEditar.setText("");
                        encargadoEditar.setText("");
                        editar.setDisable(true);
                        eliminar.setDisable(true);
                        nombreEditar.setDisable(true);
                        encargadoEditar.setDisable(true);

                        // Rellenar listview
                        llenarList();
                    } else {
                        // Mensaje de error
                        Alert error = new Alert(Alert.AlertType.ERROR);
                        error.setTitle("Error");
                        error.setHeaderText("No se pudo actualizar el remitente");
                        error.setContentText("Error: " + respuesta);
                        error.showAndWait();
                    }
                }
            }
        });
        eliminar.setOnAction(event -> {
            //Creacion de alerta para confirmacion de accion
            Alert alertc = new Alert(Alert.AlertType.CONFIRMATION);
            alertc.setTitle("Confirmacion");
            alertc.setHeaderText("Confirmación de actualización");
            alertc.setContentText("¿Seguro que deseas eliminar el remitente " + remitenteEditar.getNombre() + "?");

            Optional<ButtonType> result =alertc.showAndWait();

            if(result.get() == ButtonType.OK){
                String respuesta = Main.db.deleteRemitente(remitenteEditar.getId());

                // Comprobar registro correcto
                if(respuesta.equals("1")){

                    //Mensaje de insersion correcta
                    Alert correcto = new Alert(Alert.AlertType.INFORMATION);
                    correcto.setTitle("Correcto");
                    correcto.setHeaderText("Operacion correcta");
                    correcto.setContentText("Se Eliminó correctamente el remitente");
                    correcto.showAndWait();

                    // Limpiar campos del formulario
                    nombreEditar.setText("");
                    encargadoEditar.setText("");
                    editar.setDisable(true);
                    eliminar.setDisable(true);
                    nombreEditar.setDisable(true);
                    encargadoEditar.setDisable(true);

                    // Rellenar listview
                    llenarList();
                }else{
                    // Mensaje de error
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText("No se pudo eliminar el remitente");
                    error.setContentText("Error: " + respuesta);
                    error.showAndWait();
                }

            }
        });
    }

    private void listenerListView(){
        listRemitentes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue != null) {
                    int index = listRemitentes.getSelectionModel().getSelectedIndex();
                    if (index >= 0) {
                        System.out.println("");
                        if (arrayRemitentes.size() > 0) {
                            remitenteEditar = arrayRemitentes.get(index);
                            editarELiminar(remitenteEditar);
                        }
                    }
                }
            }
        });
    }

    private void editarELiminar(Remitente rem){
        activarEditarEliminar();
        nombreEditar.setText(rem.getNombre());
        encargadoEditar.setText(rem.getResponsable());
    }

    private void llenarList() {
        arrayRemitentes = Main.db.getRemitentes();
        listRemitentes.getItems().clear();
        for(Remitente r : arrayRemitentes) {
            listRemitentes.getItems().add("Nombre: " + r.getNombre() + "\nResponsable: " + r.getResponsable());
        }
    }

    private void desactivarEditarEliminar(){
        editar.setDisable(true);
        eliminar.setDisable(true);
        nombreEditar.setDisable(true);
        encargadoEditar.setDisable(true);
    }

    private void activarEditarEliminar(){
        editar.setDisable(false);
        eliminar.setDisable(false);
        nombreEditar.setDisable(false);
        encargadoEditar.setDisable(false);
    }

}
