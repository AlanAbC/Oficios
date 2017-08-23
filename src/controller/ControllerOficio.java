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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerOficio implements Initializable{

    // Declaracion de objetos
    private ArrayList<Oficio> oficios;
    private ArrayList<Departamento> arrayDepartamentos;
    private ArrayList<Remitente> arrayRemitentes;
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
        if(oficios.size() > 0) {
            for (Oficio o : oficios) {
                listaOficios.getItems().add("Folio: " + o.getFolio() + "\n" +
                        "Fecha: " + o.getFechaOficio().toString() + "\n" +
                        "Departamento: " + o.getDepartamento().getNombre());
            }
        }
    }

    private void listenersBotones() {
        eliminar.setOnAction(event -> {
            //Creacion de alerta para confirmacion de accion
            Alert alertc = new Alert(Alert.AlertType.CONFIRMATION);
            alertc.setTitle("Confirmacion");
            alertc.setHeaderText("Confirmación de actualización");
            alertc.setContentText("¿Seguro que deseas eliminar el oficio " + oficioEditar.getFolio() + "?");

            Optional<ButtonType> result = alertc.showAndWait();

            if(result.get() == ButtonType.OK){
                String respuesta = Main.db.deleteOficio(oficioEditar.getFolio());

                // Comprobar registro correcto
                if(respuesta.equals("1")){

                    //Mensaje de insersion correcta
                    Alert correcto = new Alert(Alert.AlertType.INFORMATION);
                    correcto.setTitle("Correcto");
                    correcto.setHeaderText("Operacion correcta");
                    correcto.setContentText("Se Eliminó correctamente el oficio");
                    correcto.showAndWait();

                    // Limpiar campos del formulario
                    limpiar();
                    desactivar();

                    // Rellenar listview
                    llenarListView();
                }else{
                    // Mensaje de error
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText("No se pudo eliminar el oficio");
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
            if(folioOficio.getText().equals("")){
                alert.setContentText("Falta ingresar un folio para el oficio");
                alert.showAndWait();
            }else if(fechaOficio.getValue() == null){
                alert.setContentText("Falta ingresar la fecha del oficio");
                alert.showAndWait();
            }else if(comboDep.getSelectionModel().getSelectedIndex() == -1){
                alert.setContentText("Falta seleccionar un departamento");
                alert.showAndWait();
            }else if(comboRem.getSelectionModel().getSelectedIndex() == -1){
                alert.setContentText("Falta seleccionar el remitente");
                alert.showAndWait();
            }else{
                //Creacion de alerta para confirmacion de accion
                Alert alertc = new Alert(Alert.AlertType.CONFIRMATION);
                alertc.setTitle("Confirmacion");
                alertc.setHeaderText("Confirmación de actualización");
                alertc.setContentText("¿Seguro que deseas modificar el oficio " + oficioEditar.getFolio() + "?");

                Optional<ButtonType> result =alertc.showAndWait();

                if(result.get() == ButtonType.OK) {

                    //Obtencion de valores
                    Departamento dep = arrayDepartamentos.get(comboDep.getSelectionModel().getSelectedIndex());
                    Remitente rem = arrayRemitentes.get(comboRem.getSelectionModel().getSelectedIndex());
                    String folio = folioOficio.getText();
                    LocalDate fecha = fechaOficio.getValue();
                    String des = desOficio.getText();
                    String obs = obsOficio.getText();

                    // Creacion de nuevo objeto de oficio
                    Oficio nuevoOficio = new Oficio();

                    // Asignacion de valores al nuevo objeto de oficio
                    String folioN = folio;
                    nuevoOficio.setFolio(oficioEditar.getFolio());
                    nuevoOficio.setFechaOficio(fecha);
                    nuevoOficio.setDepartamento(dep);
                    nuevoOficio.setRemitente(rem);
                    nuevoOficio.setDescripcion(des);
                    nuevoOficio.setObservaciones(obs);

                    // Insercion del nuevo departamento
                    String respuesta = Main.db.updateOficio(nuevoOficio, folioN);

                    // Comprobar registro correcto
                    if (respuesta.equals("1")) {

                        //Mensaje de insersion correcta
                        Alert correcto = new Alert(Alert.AlertType.CONFIRMATION);
                        correcto.setTitle("Correcto");
                        correcto.setHeaderText("Operacion correcta");
                        correcto.setContentText("Se actualizó correctamente el oficio");
                        correcto.showAndWait();

                        // Limpiar campos del formulario
                        limpiar();
                        desactivar();

                        // Rellenar listview
                        llenarListView();
                    } else {
                        // Mensaje de error
                        Alert error = new Alert(Alert.AlertType.ERROR);
                        error.setTitle("Error");
                        error.setHeaderText("No se pudo actualizar el oficio");
                        error.setContentText("Error: " + respuesta);
                        error.showAndWait();
                    }
                }
            }
        });
    }

    private void listenerListView(){
        listaOficios.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int index = listaOficios.getSelectionModel().getSelectedIndex();
                System.out.println(index);
                if(index >= 0) {
                    if (oficios.size() > 0) {
                        oficioEditar = oficios.get(index);
                        editarEliminar();
                    }
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

    private void limpiar(){
        comboDep.getSelectionModel().clearSelection();
        comboRem.getSelectionModel().clearSelection();
        fechaOficio.setValue(null);
        folioOficio.setText("");
        desOficio.clear();
        obsOficio.clear();
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
        arrayRemitentes = Main.db.getRemitentes();
        comboRem.getItems().clear();
        for(Remitente r : arrayRemitentes) {
            comboRem.getItems().add(r.getNombre());
        }
    }

    private void loadComboDep() {
        arrayDepartamentos = Main.db.getDepartamentos();
        comboDep.getItems().clear();
        for(Departamento d : arrayDepartamentos) {
            comboDep.getItems().add(d.getNombre());
        }
    }

}
