package com.trainee.ReservierungssystemFX.actions;

import com.trainee.ReservierungssystemFX.resources.Constants;
import javafx.scene.control.Alert;

import java.sql.*;
import java.text.ParseException;
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
    public void run() {
        while (true) {
            String date = Constants.df.format(new Date());
            try (Connection con = DriverManager.getConnection(Constants.sql_url); Statement stmt = con.createStatement();) {
                String SQL = "SELECT * FROM dbo.Reservations";
                ResultSet rs = stmt.executeQuery(SQL);
                while (rs.next()) {
                    String sdatumVONRES = rs.getDate("Starting_Date") + ";" + rs.getTime("Starting_Time");
                    String sdatumBISRES = rs.getDate("Ending_Date") + ";" + rs.getTime("Ending_Time");
                    Date aktuellesDatum = null;
                    try {
                        aktuellesDatum = Constants.df.parse(date);
                    } catch (ParseException e) {
                        System.out.println("Falsches Zeitformat");
                    }
                    Date bisDatum = null;
                    try {
                        bisDatum = Constants.df.parse(sdatumBISRES);
                    } catch (ParseException e) {
                        System.out.println("Falsches Zeitformat");
                    }
                    Date abDatum = null;
                    try {
                        abDatum = Constants.df.parse(sdatumVONRES);
                    } catch (ParseException e) {
                        System.out.println("Falsches Zeitformat");
                    }
                    if ((aktuellesDatum.compareTo(bisDatum) >= 0)) {
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

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
