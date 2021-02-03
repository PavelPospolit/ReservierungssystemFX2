package com.trainee.ReservierungssystemFX.actions;

import com.trainee.ReservierungssystemFX.resources.DatenErzeugnung;
import com.trainee.ReservierungssystemFX.resources.Konstanten;
import javafx.scene.control.Alert;

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
            String date = Konstanten.df.format(new Date());
            String[] hilfsString = DatenErzeugnung.getAllReservations().keySet().toArray(new String[0]);
            for (int i = 0; i < hilfsString.length; i++) {
                Date aktuellesDatum = null;
                try {
                    aktuellesDatum = Konstanten.df.parse(date);
                } catch (ParseException e) {
                    System.out.println("Falsches Zeitformat");
                }
                Date bisDatum = null;
                try {
                    bisDatum = Konstanten.df.parse(DatenErzeugnung.getAllReservations().get(hilfsString[i]).getsBisWann());
                } catch (ParseException e) {
                    System.out.println("Falsches Zeitformat");
                }
                Date abDatum = null;
                try {
                    abDatum = Konstanten.df.parse(DatenErzeugnung.getAllReservations().get(hilfsString[i]).getsAbwann());
                } catch (ParseException e) {
                    System.out.println("Falsches Zeitformat");
                }
                if ((aktuellesDatum.compareTo(bisDatum) >= 0)) {
                    DatenErzeugnung.getHmapRooms().get(DatenErzeugnung.getAllReservations().get(hilfsString[i]).getsRaumNummer()).setVerfuegbarkeit(true);
                    DatenErzeugnung.getAllReservations().remove(hilfsString[i]);
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
        }
    }

}
