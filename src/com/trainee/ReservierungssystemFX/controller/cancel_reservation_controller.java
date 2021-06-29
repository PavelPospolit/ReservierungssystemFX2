package com.trainee.ReservierungssystemFX.controller;

import com.trainee.ReservierungssystemFX.resources.Constants;
import com.trainee.ReservierungssystemFX.resources.FrequentlyUsedButtons;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class cancel_reservation_controller implements Initializable {
    public Button ReservationsButton;
    @FXML
    ChoiceBox<String> selectCancelRoom;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }

    private void loadData() {
        ArrayList<String> hilfsArray = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(Constants.sql_url); Statement stmt = con.createStatement();) {
            String SQL = "SELECT * FROM Employees E INNER JOIN Reservations RE ON E.EmployeeID=RE.EmployeeID";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                String rsRoomnumber = "Raumnummer: "+String.valueOf(rs.getInt("Roomnumber"));
                String startingDate = "VON: " + rs.getDate("Starting_Date") + "; " + rs.getTime("Starting_Time");
                String endingDate = "BIS: " + rs.getDate("Ending_Date") + "; " + rs.getTime("Ending_Time");
                if (rs.getString("Emailaddress").equals(log_in_controller.sName)) {
                    hilfsArray.add(rsRoomnumber +
                            ", " +
                            startingDate +
                            ", " +
                            endingDate);
                }
            }
            selectCancelRoom.getItems().addAll(hilfsArray);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void CancelClick(MouseEvent mouseEvent) throws IOException {
        String sZurueckNr = selectCancelRoom.getValue().split(", ")[0];

        try (Connection con = DriverManager.getConnection(Constants.sql_url); Statement stmt = con.createStatement();) {
            String SQL = "SELECT * FROM dbo.Reservations";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                if(rs.getInt("ReservationID")==Integer.parseInt(sZurueckNr)){
                    PreparedStatement stat = con.prepareStatement("delete from Reservations where ReservationID like '"+Integer.parseInt(sZurueckNr)+"'");
                    stat.executeUpdate();
                    FrequentlyUsedButtons.cancelReservatrionButton(mouseEvent);
                    break;
                }
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void onMouseClickshowRes(MouseEvent mouseEvent) throws IOException {
        FrequentlyUsedButtons.showRes(mouseEvent);
    }

    public void onMouseClicksGoToRes(MouseEvent mouseEvent) throws IOException {
        FrequentlyUsedButtons.goToReservation(mouseEvent);
    }


    public void SafeAndBackClick(MouseEvent mouseEvent) throws IOException {
        FrequentlyUsedButtons.safeAndBack(mouseEvent);
    }

}