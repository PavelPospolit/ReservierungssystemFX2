package com.trainee.ReservierungssystemFX;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent parentAnmeldung = FXMLLoader.load(getClass().getResource("resources/log_in.fxml"));
        primaryStage.setTitle("Anmeldung");
        primaryStage.setScene(new Scene(parentAnmeldung));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

