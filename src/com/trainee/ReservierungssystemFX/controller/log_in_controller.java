package com.trainee.ReservierungssystemFX.controller;

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
        Scene sceneRegestrierung = new Scene(parentRegestrierung, 600, 400);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(sceneRegestrierung);
        window.setTitle("Regestrierung");
        window.show();
    }

    public void logInOnMouseClicked(MouseEvent mouseEvent_logIn) throws IOException {
        boolean bPasswort = false;
        String sEinloggpasswort = getTextFieldPassword().getText();
        sName = getTextFieldEmail().getText();
        try (Connection con = DriverManager.getConnection(Constants.sql_url); Statement stmt = con.createStatement();) {
            String SQL = "SELECT * FROM dbo.Employees WHERE Emailaddress like'" + sName + "' AND Employee_Password like'" + sEinloggpasswort + "'";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                bPasswort = true;
            }
            if (!rs.next()) {
                errorWindowEmail.setText("ungültige Einloggdaten");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        if (bPasswort) {
            FrequentlyUsedButtons.goToReservation(mouseEvent_logIn);
        }
    }

    public void logInEnter(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            boolean bPasswort = false;
            String sEinloggpasswort = getTextFieldPassword().getText();
            sName = getTextFieldEmail().getText();
            try (Connection con = DriverManager.getConnection(Constants.sql_url); Statement stmt = con.createStatement();) {
                String SQL = "SELECT * FROM dbo.Employees WHERE Emailaddress like'" + sName + "' AND Employee_Password like'" + sEinloggpasswort + "'";
                ResultSet rs = stmt.executeQuery(SQL);
                while (rs.next()) {
                    bPasswort = true;
                }
                if (!rs.next()) {
                    errorWindowEmail.setText("ungültige Einloggdaten");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

            if (bPasswort) {
                FrequentlyUsedButtons.goToReservationKey(keyEvent);
            }
        }
    }
}
