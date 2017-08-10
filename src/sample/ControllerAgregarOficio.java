package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerAgregarOficio implements Initializable {

    //Declaracion objetos
    private DB db;

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
    private Button aceptar;

    // Array de objetos
    private ArrayList<Departamento> departamentos;
    private ArrayList<Remitente> remitentes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        db = new DB();
        assert comboDep != null : "fx:id=comboDep";
        assert comboRem != null : "fx:id=comboRem";
        assert folioOficio != null : "fx:id=folioOficio";
        assert fechaOficio != null : "fx:id=fechaOficio";
        assert desOficio != null : "fx:id=desOficio";
        assert obsOficio != null : "fx:id=obsOficio";
        assert aceptar != null : "fx:id=aceptar";
        loadComboDep();
        loadComboRem();
        listenersBotones();
    }

    private void listenersBotones() {
        aceptar.setOnAction(event -> {

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
                //Obtencion de valores
                Departamento dep = departamentos.get(comboDep.getSelectionModel().getSelectedIndex());
                Remitente rem = remitentes.get(comboRem.getSelectionModel().getSelectedIndex());
                String folio = folioOficio.getText();
                Date fecha = Date.valueOf(fechaOficio.getValue());
                String des = desOficio.getText();
                String obs = obsOficio.getText();

                // Creacion de nuevo objeto de oficio
                Oficio nuevoOficio = new Oficio();

                // Asignacion de valores al nuevo objeto de oficio
                nuevoOficio.setFolio(folio);
                nuevoOficio.setFechaOficio(fecha);
                nuevoOficio.setDepartamento(dep);
                nuevoOficio.setRemitente(rem);
                nuevoOficio.setDescripcion(des);
                nuevoOficio.setObservaciones(obs);
                nuevoOficio.setFechaRegistro(new java.util.Date());

                // Ingresar registro base de datos

                // Comprobar registro correcto

                // Limpiar campos del formulario
                comboDep.getSelectionModel().clearSelection();
                comboRem.getSelectionModel().clearSelection();
                fechaOficio.setValue(null);
                folioOficio.setText("");
                desOficio.clear();
                obsOficio.clear();
            }
        });
    }

    private void loadComboDep(){
        departamentos = db.getDepartamentos();
        comboDep.getItems().clear();
        for(Departamento d : departamentos) {
            comboDep.getItems().add(d.getNombre());
        }
    }

    private void loadComboRem(){
        remitentes = db.getRemitentes();
        comboRem.getItems().clear();
        for(Remitente r : remitentes) {
            comboRem.getItems().add(r.getNombre());
        }
    }
}
