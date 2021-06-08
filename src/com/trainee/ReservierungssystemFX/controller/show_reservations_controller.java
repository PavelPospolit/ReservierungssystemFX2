package com.trainee.ReservierungssystemFX.controller;

import com.trainee.ReservierungssystemFX.resources.Constants;
import com.trainee.ReservierungssystemFX.resources.CreateData;
import com.trainee.ReservierungssystemFX.resources.FrequentlyUsedButtons;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.split;
import static com.trainee.ReservierungssystemFX.resources.CreateData.getAllReservations;

public class show_reservations_controller implements Initializable {
    public Button returnRoomButton;
    public Button backToReservationButton;
    public Button safeAndBack;
    public TextArea BuchungsNummer;
    public TextArea RaumNummer;
    public TextArea BuchungVon;
    public TextArea BuchungBis;
    public Button updateButton;


    public void onMouseClickCancelRes(MouseEvent mouseEvent) throws IOException {
        FrequentlyUsedButtons.cancelReservatrionButton(mouseEvent);

    }

    public void onMouseClickBackToRes(MouseEvent mouseEvent) throws IOException {
        FrequentlyUsedButtons.goToReservation(mouseEvent);
    }

    public void SafeAndBackClick(MouseEvent mouseEvent) throws IOException {
        FrequentlyUsedButtons.safeAndBack(mouseEvent);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ZeigeListe();
    }

    public void ZeigeListe() {
        try (Connection con = DriverManager.getConnection(Constants.sql_url); Statement stmt = con.createStatement();) {
            String SQL = "SELECT * FROM Employees E INNER JOIN Reservations RE ON E.EmployeeID=RE.EmployeeID";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                String startingDate = rs.getDate("Starting_Date") + "; " + rs.getTime("Starting_Time");
                String endingDate = rs.getDate("Ending_Date") + "; " + rs.getTime("Ending_Time");
                BuchungsNummer.setText(BuchungsNummer.getText() + rs.getString("Emailaddress").split("@")[0] + "\n");
                RaumNummer.setText(RaumNummer.getText() + rs.getInt("Roomnumber") + "\n");
                BuchungVon.setText(BuchungVon.getText() + startingDate + "\n");
                BuchungBis.setText(BuchungBis.getText() + endingDate + "\n");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateButtonClick(MouseEvent mouseEvent) throws IOException {
        FrequentlyUsedButtons.showRes(mouseEvent);
    }
}
