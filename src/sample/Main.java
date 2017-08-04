package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    // Variables de los Pane
    private Pane contenedor;
    private Pane InicioPane;
    private Pane oficioPane;
    private Pane departamentoPane;
    private Pane remitentePane;
    private Pane estadisticaPane;

    // Variables de los elementos del Pane Menu
    private Label inicio;
    private Label oficios;
    private Label departamentos;
    private Label remitentes;
    private Label estadisticas;

    // Variables de los elementos del Pane Inicio
    private TextField folioInicio;
    private DatePicker fechaInicio;
    private ComboBox departamentoInicio;
    private ComboBox remitenteInicio;
    private TextArea descripcionInicio;
    private TextArea observacionesInicio;
    private Button archivoInicio;
    private Button guardarInicio;

    // Variables de los elementos del Pane Oficio
    private ListView oficiosOficio;
    private TextField folioOficio;
    private DatePicker fechaOficio;
    private ComboBox departamentoOficio;
    private ComboBox remitenteOficio;
    private TextArea descripcionOficio;
    private TextArea observacionesOficio;
    private Button editarOficio;
    private Button eliminarOficio;

    // Variables de los elementos del Pane Departamento
    private ListView departamentosDepartamento;
    private TextField agregarNombreDepartamento;
    private TextField agregarEncargadoDepartamento;
    private Button agregarDepartamento;
    private TextField editarNombreDepartamento;
    private TextField editarEncargadoDepartamento;
    private Button editarDepartamento;
    private Button eliminarDepartamento;

    // Variables de los elementos del Pane Remitentes
    private ListView remitentesRemitente;
    private TextField agregarNombreRemitente;
    private TextField agregarEncargadoRemitente;
    private Button agregarRemitente;
    private TextField editarNombreRemitente;
    private TextField editarEncargadoRemitente;
    private Button editarRemitente;
    private Button eliminarRemitente;

    // Variables de los elementos del Pane Estadisticas
    private DatePicker filtroEstadistica;
    private ListView departamentosEstadistica;
    private ListView remitentesEstadistica;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Obtencion del fxml principal
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Oficios");

        // Creacion de la ecena principal
        Scene scene = new Scene(root, 800, 600);

        // Muestra de la Ventana inicial
        primaryStage.setScene(scene);
        primaryStage.show();

        //
    }


    public static void main(String[] args) {
        launch(args);
    }
}
