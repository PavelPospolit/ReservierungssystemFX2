package com.trainee.ReservierungssystemFX.actions;

import com.trainee.ReservierungssystemFX.resources.Constants;
import javafx.scene.control.Alert;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*create a date format
 * safe today's Date and Time into date1 variable
 * safe the Date untill booking ends into date2 variable
 * compare each others
 * if date1>=date2 (ending date of the reservation has been reeched or surpassed):
 * return Room by deleting the reservation and setting availability to true
 * check next reservation
 * wait 1 second
 * repeat until true (always) */

public class Zeit_Vergleich extends Thread {
    String sNow;
    Random_Number_Generator rand = new Random_Number_Generator();

    public void run() {
        while (true) {
            try (Connection con = DriverManager.getConnection(Constants.sql_url); Statement stmt = con.createStatement();) {
                String SQL = "SELECT * FROM dbo.Reservations";
                ResultSet rs = stmt.executeQuery(SQL);
                while (rs.next()) {
                    String sdatumBISRES = rs.getDate("Ending_Date") + ";" + rs.getTime("Ending_Time");
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd;HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    sNow = dtf.format(now);
                    if ((Constants.df.parse(sNow).compareTo(Constants.df.parse(sdatumBISRES)) >= 0)) {
                        int iHistID = rand.HistRandomNumber();
                        String insert = "insert into ReservationsHistory values(?,?,?,?,?,?,?,?)";
                        PreparedStatement insertstmt = con.prepareStatement(insert);
                        insertstmt.setInt(1, iHistID);
                        insertstmt.setInt(2, rs.getInt("ReservationID"));
                        insertstmt.setInt(3, rs.getInt("EmployeeID"));
                        insertstmt.setInt(4, rs.getInt("Roomnumber"));
                        insertstmt.setDate(5, rs.getDate("Starting_Date"));
                        insertstmt.setTime(6, rs.getTime("Starting_Time"));
                        insertstmt.setDate(7, rs.getDate("Ending_Date"));
                        insertstmt.setTime(8, rs.getTime("Ending_Time"));
                        insertstmt.execute();
                        PreparedStatement delstmt = con.prepareStatement("delete from Reservations where ReservationID like '" + rs.getInt("ReservationID") + "'");
                        delstmt.executeUpdate();
                    }
                }
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Aktualisierungsfehler");
                    alert.setContentText("Es ist ein Fehler beim Zeitvergleich aufgetreten, bitte Support kontaktieren!");

                    alert.showAndWait();
                }

            } catch (SQLException | ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
