package com.example.sistemadulceria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Verificar que el archivo login.fxml existe
        URL fxmlLocation = getClass().getResource("/fxml/login.fxml");
        System.out.println("Cargando FXML desde: " + fxmlLocation);

        if (fxmlLocation == null) {
            throw new RuntimeException("No se encontró login.fxml en /resources/fxml/");
        }

        Parent root = FXMLLoader.load(fxmlLocation);
        Scene scene = new Scene(root, 430, 932);

        System.out.println("CSS: " + getClass().getResource("/css/style.css"));

        //Cargar CSS global
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        primaryStage.setTitle("Sistema Dulcería");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}