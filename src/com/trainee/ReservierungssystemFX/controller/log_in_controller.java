package com.trainee.ReservierungssystemFX.controller;

import com.trainee.ReservierungssystemFX.resources.DatenErzeugnung;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.event.KeyListener;
import java.io.IOException;

import com.trainee.ReservierungssystemFX.resources.Konstanten;

import javax.xml.crypto.dsig.keyinfo.KeyValue;


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
    static String sNameue="";


    public TextField getTextFieldEmail() {
        return textFieldEmail;
    }

    public TextField getTextFieldPassword() {
        return textFieldPassword;
    }


    public void onMouseClick(MouseEvent mouseEvent) throws IOException {
        Parent parentRegestrierung = FXMLLoader.load(getClass().getClassLoader().getResource("com/trainee/ReservierungssystemFX/FXML/sign_in.fxml"));
        Scene sceneRegestrierung = new Scene(parentRegestrierung);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(sceneRegestrierung);
        window.setTitle("Regestrierung");
        window.show();
    }

    public void logInOnMouseClicked(MouseEvent mouseEvent_logIn) throws IOException {
        boolean bEmail = true, bPasswort = true;
        String sName = getTextFieldEmail().getText();
        String[] hilfsStringEmail = DatenErzeugnung.getHmapMitarbeiter().keySet().toArray(new String[0]);
        for (int i = 0; i < hilfsStringEmail.length; i++) {
            if ((hilfsStringEmail[i].equals(sName) ||
                    DatenErzeugnung.getHmapMitarbeiter().get(hilfsStringEmail[i]).getsMaName().equals(sName))&&
                    ( DatenErzeugnung.getHmapMitarbeiter().get(hilfsStringEmail[i]).getsMaName().contains("@") &&
                            DatenErzeugnung.getHmapMitarbeiter().get(hilfsStringEmail[i]).getsMaName().contains(".")))  {
                errorWindowEmail.setText("");
                bEmail = true;
                break;
            } else {
                bEmail = false;
                errorWindowEmail.setText("ungültige Emailadresse");
            }
        }
        String sEinloggpasswort = getTextFieldPassword().getText();
        String[] hilfsStringPassword = DatenErzeugnung.getHmapMitarbeiter().keySet().toArray(new String[0]);
        for (int i = 0; i < hilfsStringPassword.length; i++) {
            if (bEmail && DatenErzeugnung.getHmapMitarbeiter().get(hilfsStringPassword[i]).getsPasswort().equals(sEinloggpasswort)) {
                bPasswort = true;
                errorWindowPassword.setText("");
                break;
            } else {
                bPasswort = false;
                errorWindowPassword.setText("Ungültiges Passwort!");
            }
        }

        if (bPasswort) {
            Parent parentRegestrierung = FXMLLoader.load(getClass().getClassLoader().getResource("com/trainee/ReservierungssystemFX/FXML/reservation.fxml"));
            Scene sceneRegestrierung = new Scene(parentRegestrierung);
            Stage window = (Stage) ((Node) mouseEvent_logIn.getSource()).getScene().getWindow();
            window.setScene(sceneRegestrierung);
            window.setTitle("Reservierung");
            window.show();
            sNameue=sName;
        }
    }
}
