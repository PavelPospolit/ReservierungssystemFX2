package com.trainee.ReservierungssystemFX.controller;

import com.trainee.ReservierungssystemFX.actions.Random_Number_Generator;
import com.trainee.ReservierungssystemFX.resources.Constants;
import com.trainee.ReservierungssystemFX.resources.FrequentlyUsedButtons;
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

import java.io.IOException;
import java.sql.*;

public class sign_in_controller {

    @FXML
    public Label errorWindowPassword;
    @FXML
    public Label errorWindowDoubleEmail;
    @FXML
    public PasswordField textFieldPassword2;
    @FXML
    public Button sign_in_button;
    @FXML
    public TextField textFieldEmail;
    @FXML
    public PasswordField textFieldPassword;
    @FXML
    public Label errorWindowPassword2;
    String sEmail;
    String sPassword;
    boolean bEmail;
    boolean bPassword;

    public void onMouseClick(MouseEvent mouseEvent) throws IOException {
        Parent parentRegestrierung = FXMLLoader.load(getClass().getClassLoader().getResource("com/trainee/ReservierungssystemFX/FXML/log_in.fxml"));
        Scene sceneRegestrierung = new Scene(parentRegestrierung, 600, 400);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(sceneRegestrierung);
        window.setTitle("Anmeldung");
        window.show();

    }

    public void logInOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        bEmail = false;
        bPassword = true;
        sEmail = textFieldEmail.getText();
        sPassword = textFieldPassword.getText();

        try (Connection con = DriverManager.getConnection(Constants.sql_url); Statement stmt = con.createStatement();) {
            String SQL = "SELECT * FROM dbo.Employees WHERE Emailaddress like '" + sEmail + "'";
            ResultSet rs = stmt.executeQuery(SQL);
            if (!rs.next()) {
                bEmail = true;
            } else {
                errorWindowDoubleEmail.setText("Mitarbeiter " + sEmail + " bereits vorhanden!\nBitte Einloggen!");
                bEmail = false;
            }
            if (textFieldEmail.getText().isEmpty()) {
                errorWindowDoubleEmail.setText("Bitte Emailadresse eingeben!");
            }
            if (textFieldPassword.getText().isEmpty()) {
                errorWindowPassword.setText("Bitte ein Password eingeben!");
                bPassword = false;
            } else if (textFieldPassword2.getText().isEmpty()) {
                errorWindowPassword2.setText("Bitte ein Password eingeben!");
                bPassword = false;
            } else if ((textFieldPassword.getText().length() < 8)) {
                errorWindowPassword.setText("Passwort muss mindestens 8 Zeichen lang sein!");
                bPassword = false;
            } else if (!sPassword.equals(textFieldPassword2.getText())) {
                bPassword = false;
                errorWindowPassword.setText("Passwörter stimmen nicht überein!");
                errorWindowPassword2.setText("Passwörter stimmen nicht überein!");
            } else if ((!sEmail.contains("@")) && (!sEmail.contains("."))) {
                bEmail = false;
                errorWindowDoubleEmail.setText("Bitte gültige Emailadresse benutzen!");
            }
            if (bEmail && bPassword) {
                errorWindowPassword.setText("");
                errorWindowPassword2.setText("");
                errorWindowDoubleEmail.setText("");
                Random_Number_Generator rand = new Random_Number_Generator();
                int iRand = rand.MaRandomNumber();
                String insert = "insert into Employees values(?,?,?)";
                PreparedStatement insertstmt = con.prepareStatement(insert);
                insertstmt.setInt(1, iRand);
                insertstmt.setString(2, sEmail);
                insertstmt.setString(3, sPassword);
                insertstmt.execute();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERFOLG!");
                alert.setContentText("Mitarbeiter erfolgreich hinzugefügt!");
                alert.showAndWait();
                FrequentlyUsedButtons.goToSignIn(mouseEvent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void signUpEnter(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            bEmail = false;
            bPassword = true;
            sEmail = textFieldEmail.getText();
            sPassword = textFieldPassword.getText();

            try (Connection con = DriverManager.getConnection(Constants.sql_url); Statement stmt = con.createStatement();) {
                String SQL = "SELECT * FROM dbo.Employees WHERE Emailaddress like '" + sEmail + "'";
                ResultSet rs = stmt.executeQuery(SQL);
                if (!rs.next()) {
                    bEmail = true;
                } else {
                    errorWindowDoubleEmail.setText("Mitarbeiter " + sEmail + " bereits vorhanden!\nBitte Einloggen!");
                    bEmail = false;
                }
                if (textFieldEmail.getText().isEmpty()) {
                    errorWindowDoubleEmail.setText("Bitte Emailadresse eingeben!");
                    bEmail = false;
                }
                if (textFieldPassword.getText().isEmpty()) {
                    errorWindowPassword.setText("Bitte ein Password eingeben!");
                    bPassword = false;
                } else if (textFieldPassword2.getText().isEmpty()) {
                    errorWindowPassword2.setText("Bitte ein Password eingeben!");
                    bPassword = false;
                } else if ((textFieldPassword.getText().length() < 8)) {
                    errorWindowPassword.setText("Passwort muss mindestens 8 Zeichen lang sein!");
                    bPassword = false;
                } else if (!sPassword.equals(textFieldPassword2.getText())) {
                    bPassword = false;
                    errorWindowPassword.setText("Passwörter stimmen nicht überein!");
                    errorWindowPassword2.setText("Passwörter stimmen nicht überein!");
                } else if ((!sEmail.contains("@")) && (!sEmail.contains("."))) {
                    bEmail = false;
                    errorWindowDoubleEmail.setText("Bitte gültige Emailadresse benutzen!");
                }
                if (bEmail && bPassword) {
                    errorWindowPassword.setText("");
                    errorWindowPassword2.setText("");
                    errorWindowDoubleEmail.setText("");
                    Random_Number_Generator rand = new Random_Number_Generator();
                    int iRand = rand.MaRandomNumber();
                    String insert = "insert into Employees values(?,?,?)";
                    PreparedStatement insertstmt = con.prepareStatement(insert);
                    insertstmt.setInt(1, iRand);
                    insertstmt.setString(2, sEmail);
                    insertstmt.setString(3, sPassword);
                    insertstmt.execute();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("ERFOLG!");
                    alert.setContentText("Mitarbeiter erfolgreich hinzugefügt!");
                    alert.showAndWait();
                    FrequentlyUsedButtons.goToSignInKey(keyEvent);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}

