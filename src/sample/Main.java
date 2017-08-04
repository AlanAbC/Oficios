package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    // Variables de Ecenas

    // Variables de Menu
    private Label inicio;
    private Label oficios;
    private Label departamentos;
    private Label remitentes;
    private Label estadisticas;

    // Variables de Inicio
    private TextField folioInicio;
    private DatePicker fechaInicio;
    private ComboBox departamentoInicio;
    private ComboBox remitenteInicio;
    private TextArea descripcionInicio;
    private TextArea observacionesInicio;
    private Button archivoInicio;
    private Button guardarInicio;

    // Variables de Oficios

    // Variables de Departamentos

    // Variables de Remitentes

    // Variables de Estadisticas

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
