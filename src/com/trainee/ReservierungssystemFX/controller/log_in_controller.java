package com.trainee.ReservierungssystemFX.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class log_in_controller {

    /*Scene switch method*/


    public void onMouseClick(MouseEvent mouseEvent) throws IOException {
        Parent parentRegestrierung = FXMLLoader.load(getClass().getClassLoader().getResource("com/trainee/ReservierungssystemFX/resources/sign_in.fxml"));
        Scene sceneRegestrierung = new Scene(parentRegestrierung);

        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(sceneRegestrierung);
        window.setTitle("Regestrierung");
        window.show();

    }
}
