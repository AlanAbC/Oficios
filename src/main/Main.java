package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.DB;
import model.Departamento;
import model.Remitente;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    // Variable de base de datos
    public static DB db;

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

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Obtencion del fxml principal
        Parent root = FXMLLoader.load(getClass().getResource("../view/layout/sample.fxml"));
        primaryStage.setTitle("Oficios");

        // Creacion de la ecena principal
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("../view/style/listStyle.css").toExternalForm());

        // Muestra de la Ventana inicial
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();


        //Asignacion de variables
        contenedor = (Pane)scene.lookup("#contenedor");

        //variables menu
        inicio = (Label)scene.lookup("#inicio");
        oficios = (Label)scene.lookup("#oficios");
        departamentos = (Label)scene.lookup("#departamentos");
        remitentes = (Label)scene.lookup("#remitentes");
        estadisticas = (Label)scene.lookup("#estadisticas");

        //Llamado de paneles
        InicioPane = FXMLLoader.load(getClass().getResource("../view/layout/AgregarOficio.fxml"));
        //oficioPane = FXMLLoader.load(getClass().getResource("../view/layout/Oficios.fxml"));
        //departamentoPane = FXMLLoader.load(getClass().getResource("../view/layout/Departamentos.fxml"));
        //remitentePane = FXMLLoader.load(getClass().getResource("../view/layout/Remitentes.fxml"));
        //estadisticaPane = FXMLLoader.load(getClass().getResource("../view/layout/Estadisticas.fxml"));


        //Asignacion de panel principal
        contenedor.getChildren().add(InicioPane);

        //Asignacion de variables menu

        //Llamada funciones para cambio de paneles
        accionMenu();

        //Llamado de funcion over menu
        overMenu();
    }

    public void accionMenu() {
        inicio.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    InicioPane = FXMLLoader.load(getClass().getResource("../view/layout/AgregarOficio.fxml"));
                    contenedor.getChildren().remove(0);
                    contenedor.getChildren().add(InicioPane);
                }catch (IOException e){
                    System.out.println("Error: " + e.toString());
                }
            }
        });
        oficios.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    oficioPane = FXMLLoader.load(getClass().getResource("../view/layout/Oficios.fxml"));
                    contenedor.getChildren().remove(0);
                    contenedor.getChildren().add(oficioPane);
                }catch(IOException e){
                    System.out.println("Error: " + e.toString());
                }
            }
        });
        departamentos.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    departamentoPane = FXMLLoader.load(getClass().getResource("../view/layout/Departamentos.fxml"));
                    contenedor.getChildren().remove(0);
                    contenedor.getChildren().add(departamentoPane);
                }catch(IOException e){
                    System.out.println("Error: " + e.toString());
                }
            }
        });
        estadisticas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    estadisticaPane = FXMLLoader.load(getClass().getResource("../view/layout/Estadisticas.fxml"));
                    contenedor.getChildren().remove(0);
                    contenedor.getChildren().add(estadisticaPane);
                }catch(IOException e){
                    System.out.println("Error: " + e.toString());
                }
            }
        });
        remitentes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    remitentePane = FXMLLoader.load(getClass().getResource("../view/layout/Remitentes.fxml"));
                    contenedor.getChildren().remove(0);
                    contenedor.getChildren().add(remitentePane);
                }catch(IOException e){
                    System.out.println("Error: " + e.toString());
                }
            }
        });
    }

    //Funcion mouseOver menu
    public void overMenu(){
        inicio.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                inicio.setCursor(Cursor.HAND);
                inicio.setTextFill(Color.valueOf("#818181"));
            }
        });
        inicio.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                inicio.setTextFill(Color.WHITE);
            }
        });
        estadisticas.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                estadisticas.setCursor(Cursor.HAND);
                estadisticas.setTextFill(Color.valueOf("#818181"));
            }
        });
        estadisticas.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                estadisticas.setTextFill(Color.WHITE);
            }
        });
        oficios.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                oficios.setCursor(Cursor.HAND);
                oficios.setTextFill(Color.valueOf("#818181"));
            }
        });
        oficios.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                oficios.setTextFill(Color.WHITE);
            }
        });
        departamentos.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                departamentos.setCursor(Cursor.HAND);
                departamentos.setTextFill(Color.valueOf("#818181"));
            }
        });
        departamentos.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                departamentos.setTextFill(Color.WHITE);
            }
        });
        remitentes.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                remitentes.setCursor(Cursor.HAND);
                remitentes.setTextFill(Color.valueOf("#818181"));
            }
        });
        remitentes.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                remitentes.setTextFill(Color.WHITE);
            }
        });
    }

    //Funccion over botones
    public void overBotones(){

    }

    public static void main(String[] args) {
        db = new DB();
        launch(args);
    }
}
