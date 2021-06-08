package com.trainee.ReservierungssystemFX;
import com.trainee.ReservierungssystemFX.actions.Zeit_Vergleich;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent parentAnmeldung = FXMLLoader.load(getClass().getResource("FXML/log_in.fxml"));
        primaryStage.setTitle("Anmeldung");
        primaryStage.setScene(new Scene(parentAnmeldung,600,400));
        primaryStage.setHeight(440);
        primaryStage.setWidth(630);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });
    }
    public static void main(String[] args) {
        Zeit_Vergleich timeCheck = new Zeit_Vergleich();
        new Thread(timeCheck).start();
        launch(args);

    }

}

