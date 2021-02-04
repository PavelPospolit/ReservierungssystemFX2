package com.trainee.ReservierungssystemFX.actions;

import com.trainee.ReservierungssystemFX.resources.DatenErzeugnung;
import com.trainee.ReservierungssystemFX.resources.Konstanten;
import javafx.scene.control.Alert;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static com.trainee.ReservierungssystemFX.resources.DatenErzeugnung.getAllReservations;

/*Empties existent .txt files (to avoid data being doubled)
 * gets the Keys of the Hashmaps and puts them into hilsString variable
 * writes Data from Hashmaps into .txt file*/

public class Schreiben {
    static BufferedWriter mWriter;
    static BufferedWriter rWriter;
    static BufferedWriter resWriter;
    static BufferedWriter mLeeren;
    static BufferedWriter rLeeren;
    static BufferedWriter resLeeren;

    static {
        try {
            mWriter = new BufferedWriter(new FileWriter(Konstanten.mFile, true));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Schreibfehler");
            alert.setContentText("Mitarbeiterdatei konnte nicht gespeichert werden, bitte Support kontaktieren");

            alert.showAndWait();
        }
    }

    static {
        try {
            rWriter = new BufferedWriter(new FileWriter(Konstanten.rFile, true));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Schreibfehler");
            alert.setContentText("Raumdatei konnte nicht gespeichert werden, bitte Support kontaktieren");

            alert.showAndWait();
        }
    }

    static {
        try {
            resWriter = new BufferedWriter(new FileWriter(Konstanten.resFile, true));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Schreibfehler");
            alert.setContentText("Reservierungsdatei konnte nicht gespeichert werden, bitte Support kontaktieren");

            alert.showAndWait();
        }
    }

    public Schreiben() throws IOException {
        try {
            mLeeren = new BufferedWriter(new FileWriter(Konstanten.mFile));
        } catch (IOException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Rechte fehlen");
            alert.setContentText("Datei konnte nicht gespeichert werden. es Fehlen Rechte. Bitte Support kontaktieren!");

            alert.showAndWait();
        }
        mLeeren.write("");
        mLeeren.close();
        for (String key : DatenErzeugnung.getAllEmployees().keySet()) {
            mWriter.write(
                    DatenErzeugnung.getEmployee(key).getsMaName() +
                            ", " +
                            DatenErzeugnung.getEmployee(key).getsMitarbeiterID() +
                            ", " +
                            DatenErzeugnung.getEmployee(key).getsPasswort());
            mWriter.newLine();
        }
        mWriter.close();

        try {
            rLeeren = new BufferedWriter(new FileWriter(Konstanten.rFile));
        } catch (IOException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Rechte fehlen");
            alert.setContentText("Datei konnte nicht gespeichert werden. es Fehlen Rechte. Bitte Support kontaktieren!");

            alert.showAndWait();
        }
        rLeeren.write("");
        rLeeren.close();
        for (String key: DatenErzeugnung.getAllRooms().keySet()) {
            rWriter.write(
                    DatenErzeugnung.getRoom(key).getBezeichnung() +
                            ", " +
                            DatenErzeugnung.getRoom(key).getRaumNr() +
                            ", " +
                            DatenErzeugnung.getRoom(key).getEigenschaften() +
                            ", " +
                            DatenErzeugnung.getRoom(key).getKapazitaet());
            rWriter.newLine();
        }
        rWriter.close();

        try {
            resLeeren = new BufferedWriter(new FileWriter(Konstanten.resFile));
        } catch (IOException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Rechte fehlen");
            alert.setContentText("Datei konnte nicht gespeichert werden. es Fehlen Rechte. Bitte Support kontaktieren!");

            alert.showAndWait();
        }
        resLeeren.write("");
        resLeeren.close();
        for(String key: getAllReservations().keySet()) {
            resWriter.write(
                    DatenErzeugnung.getReservation(key).getsReservierungsnummer() +
                            ", " +
                            DatenErzeugnung.getReservation(key).getSmaName() +
                            ", " +
                            DatenErzeugnung.getReservation(key).getsRaumNummer() +
                            ", " +
                            DatenErzeugnung.getReservation(key).getsAbwann()+
                            ", "+
                            DatenErzeugnung.getReservation(key).getsBisWann());
            resWriter.newLine();
        }
        resWriter.close();
    }
}
