package com.trainee.ReservierungssystemFX.controller;

import com.trainee.ReservierungssystemFX.resources.CreateData;
import com.trainee.ReservierungssystemFX.resources.FrequentlyUsedButtons;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class log_in_controller {
    @FXML
    private TextField textFieldEmail;
    @FXML
    private PasswordField textFieldPassword;
    @FXML
    private Label errorWindowEmail;
    @FXML
    private Label errorWindowPassword;
    @FXML
    static Button log_in_button;
    static String sName = "";


    public TextField getTextFieldEmail() {
        return textFieldEmail;
    }

    public TextField getTextFieldPassword() {
        return textFieldPassword;
    }


    public void goToRegistration(MouseEvent mouseEvent) throws IOException {
        Parent parentRegestrierung = FXMLLoader.load(getClass().getClassLoader().getResource("com/trainee/ReservierungssystemFX/FXML/sign_in.fxml"));
        Scene sceneRegestrierung = new Scene(parentRegestrierung);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(sceneRegestrierung);
        window.setTitle("Regestrierung");
        window.show();
    }

    public void logInOnMouseClicked(MouseEvent mouseEvent_logIn) throws IOException {
        boolean bEmail = true, bPasswort = true;
        sName = getTextFieldEmail().getText();
        for (String key : CreateData.getAllEmployees().keySet()) {
            if (CreateData.getEmployee(key).getsMaName().equals(sName) &&
                    (CreateData.getEmployee(key).getsMaName().contains("@") &&
                            CreateData.getEmployee(key).getsMaName().contains("."))) {
                errorWindowEmail.setText("");
                bEmail = true;
                break;
            } else {
                bEmail = false;
                errorWindowEmail.setText("ungültige Emailadresse");
            }
        }
        String sEinloggpasswort = getTextFieldPassword().getText();
        for (String key : CreateData.getAllEmployees().keySet()) {
            if (bEmail && CreateData.getEmployee(key).getsPasswort().equals(sEinloggpasswort)) {
                bPasswort = true;
                errorWindowPassword.setText("");
                break;
            } else {
                bPasswort = false;
                errorWindowPassword.setText("Ungültiges Passwort!");
            }
        }

        if (bPasswort) {
            FrequentlyUsedButtons.goToReservation(mouseEvent_logIn);
        }
    }
}
