package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Departamento;
import main.Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerDepartamentos implements Initializable{

    // Declaracion de objetos y listas de onjetos
    //private DB db;
    private ArrayList<Departamento> departamentos;
    private Departamento departamentoEditar;

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
        departamentoEditar = new Departamento();
        desactivarEditarEliminar();
        llenarList();
        listenersBotones();
        listenerListView();
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
        editar.setOnAction(event -> {
            // Creacion de alerta para campos faltantes
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Datos faltantes");

            // Comprobaciones de campos vacios
            if(nombreEditar.getText().equals("")){
                alert.setContentText("Falta ingresar el nuevo nombre del departamento");
                alert.showAndWait();
            }else if(encargadoEditar.getText().equals("")){
                alert.setContentText("Falta ingresar un nuevo responsable del departamento");
                alert.showAndWait();
            }else{
                //Creacion de alerta para confirmacion de accion
                Alert alertc = new Alert(Alert.AlertType.CONFIRMATION);
                alertc.setTitle("Confirmacion");
                alertc.setHeaderText("Confirmación de actualización");
                alertc.setContentText("¿Seguro que deseas modificar el departamento " + departamentoEditar.getNombre() + "?");

                Optional<ButtonType> result =alertc.showAndWait();

                if(result.get() == ButtonType.OK) {

                    // Creacion del nuevo objeto de departamento
                    Departamento editarDep = new Departamento();
                    editarDep.setId(departamentoEditar.getId());
                    editarDep.setNombre(nombreEditar.getText());
                    editarDep.setResponsable(encargadoEditar.getText());

                    // Insercion del nuevo departamento
                    String respuesta = Main.db.updateDepartamento(editarDep);

                    // Comprobar registro correcto
                    if (respuesta.equals("1")) {

                        //Mensaje de insersion correcta
                        Alert correcto = new Alert(Alert.AlertType.INFORMATION);
                        correcto.setTitle("Correcto");
                        correcto.setHeaderText("Operacion correcta");
                        correcto.setContentText("Se actualizó correctamente el departamento");
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
                        error.setHeaderText("No se pudo actualizar el departamento");
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
            alertc.setContentText("¿Seguro que deseas eliminar el departamento " + departamentoEditar.getNombre() + "?");

            Optional<ButtonType> result =alertc.showAndWait();

            if(result.get() == ButtonType.OK){
                String respuesta = Main.db.deleteDepartamento(departamentoEditar.getId());

                // Comprobar registro correcto
                if(respuesta.equals("1")){

                    //Mensaje de insersion correcta
                    Alert correcto = new Alert(Alert.AlertType.INFORMATION);
                    correcto.setTitle("Correcto");
                    correcto.setHeaderText("Operacion correcta");
                    correcto.setContentText("Se Eliminó correctamente el departamento");
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
                    error.setHeaderText("No se pudo eliminar el departamento");
                    error.setContentText("Error: " + respuesta);
                    error.showAndWait();
                }

            }
        });
    }

    private void listenerListView(){
        listDepartamentos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue != null) {
                    int index = listDepartamentos.getSelectionModel().getSelectedIndex();
                    if (index >= 0) {
                        if (departamentos.size() > 0) {
                            departamentoEditar = departamentos.get(index);
                            editarELiminar(departamentoEditar);
                        }
                    }
                }
            }
        });
    }

    private void editarELiminar(Departamento dep){
        activarEditarEliminar();
        nombreEditar.setText(dep.getNombre());
        encargadoEditar.setText(dep.getResponsable());
    }

    private void llenarList() {
        departamentos = Main.db.getDepartamentos();
        listDepartamentos.getItems().clear();
        for(Departamento d : departamentos) {
            listDepartamentos.getItems().add("Nombre: " + d.getNombre() + "\nResponsable: " + d.getResponsable());
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
