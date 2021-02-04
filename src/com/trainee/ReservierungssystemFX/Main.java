package com.trainee.ReservierungssystemFX;


import com.trainee.ReservierungssystemFX.actions.Zeit_Vergleich;
import com.trainee.ReservierungssystemFX.resources.CreateData;
import com.trainee.ReservierungssystemFX.resources.FrequentlyUsedButtons;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        CreateData create_data = new CreateData();
        create_data.leseAlle();
        Parent parentAnmeldung = FXMLLoader.load(getClass().getResource("FXML/log_in.fxml"));
        primaryStage.setTitle("Anmeldung");
        primaryStage.setScene(new Scene(parentAnmeldung));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            try {
                FrequentlyUsedButtons.closeAndExit();
            } catch (IOException ioException) {
                System.out.println("Schreibfehler, bitte Support kontaktieren!");
            }
        });
    }
    public static void main(String[] args) {
        Zeit_Vergleich timeCheck = new Zeit_Vergleich();
        new Thread(timeCheck).start();
        launch(args);

    }

}

