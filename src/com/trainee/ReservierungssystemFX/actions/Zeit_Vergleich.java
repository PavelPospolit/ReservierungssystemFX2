package com.trainee.ReservierungssystemFX.actions;

import com.trainee.ReservierungssystemFX.resources.Constants;
import javafx.scene.control.Alert;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
    public void run() {
        while (true) {
            String date = Constants.df.format(new Date());
            try (Connection con = DriverManager.getConnection(Constants.sql_url); Statement stmt = con.createStatement();) {
                String SQL = "SELECT * FROM dbo.Reservations";
                ResultSet rs = stmt.executeQuery(SQL);
                while (rs.next()) {
                    String sdatumVONRES = rs.getDate("Starting_Date") + ";" + rs.getTime("Starting_Time");
                    String sdatumBISRES = rs.getDate("Ending_Date") + ";" + rs.getTime("Ending_Time");


                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd;HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    sNow = dtf.format(now);
                    if ((Constants.df.parse(sNow).compareTo(Constants.df.parse(sdatumBISRES)) >= 0)) {
                        PreparedStatement stat = con.prepareStatement("delete from Reservations where ReservationID like '"+rs.getInt("ReservationID")+"'");
                        stat.executeUpdate();
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
