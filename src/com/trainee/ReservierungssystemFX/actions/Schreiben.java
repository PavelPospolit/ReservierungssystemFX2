package com.trainee.ReservierungssystemFX.actions;

import com.trainee.ReservierungssystemFX.resources.CreateData;
import com.trainee.ReservierungssystemFX.resources.Constants;
import javafx.scene.control.Alert;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static com.trainee.ReservierungssystemFX.resources.CreateData.getAllReservations;

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
            mWriter = new BufferedWriter(new FileWriter(Constants.mFile, true));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Schreibfehler");
            alert.setContentText("Mitarbeiterdatei konnte nicht gespeichert werden, bitte Support kontaktieren");

            alert.showAndWait();
        }
    }

    static {
        try {
            rWriter = new BufferedWriter(new FileWriter(Constants.rFile, true));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Schreibfehler");
            alert.setContentText("Raumdatei konnte nicht gespeichert werden, bitte Support kontaktieren");

            alert.showAndWait();
        }
    }

    static {
        try {
            resWriter = new BufferedWriter(new FileWriter(Constants.resFile, true));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Schreibfehler");
            alert.setContentText("Reservierungsdatei konnte nicht gespeichert werden, bitte Support kontaktieren");

            alert.showAndWait();
        }
    }

    public Schreiben() throws IOException {
        try {
            mLeeren = new BufferedWriter(new FileWriter(Constants.mFile));
        } catch (IOException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Rechte fehlen");
            alert.setContentText("Datei konnte nicht gespeichert werden. es Fehlen Rechte. Bitte Support kontaktieren!");

            alert.showAndWait();
        }
        mLeeren.write("");
        mLeeren.close();
        for (String key : CreateData.getAllEmployees().keySet()) {
            mWriter.write(
                    CreateData.getEmployee(key).getsMaName() +
                            ", " +
                            CreateData.getEmployee(key).getsMitarbeiterID() +
                            ", " +
                            CreateData.getEmployee(key).getsPasswort());
            mWriter.newLine();
        }
        mWriter.close();

        try {
            rLeeren = new BufferedWriter(new FileWriter(Constants.rFile));
        } catch (IOException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Rechte fehlen");
            alert.setContentText("Datei konnte nicht gespeichert werden. es Fehlen Rechte. Bitte Support kontaktieren!");

            alert.showAndWait();
        }
        rLeeren.write("");
        rLeeren.close();
        for (String key: CreateData.getAllRooms().keySet()) {
            rWriter.write(
                    CreateData.getRoom(key).getBezeichnung() +
                            ", " +
                            CreateData.getRoom(key).getRaumNr() +
                            ", " +
                            CreateData.getRoom(key).getEigenschaften() +
                            ", " +
                            CreateData.getRoom(key).getKapazitaet());
            rWriter.newLine();
        }
        rWriter.close();

        try {
            resLeeren = new BufferedWriter(new FileWriter(Constants.resFile));
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
                    CreateData.getReservation(key).getsReservierungsnummer() +
                            ", " +
                            CreateData.getReservation(key).getSmaName() +
                            ", " +
                            CreateData.getReservation(key).getsRaumNummer() +
                            ", " +
                            CreateData.getReservation(key).getsAbwann()+
                            ", "+
                            CreateData.getReservation(key).getsBisWann());
            resWriter.newLine();
        }
        resWriter.close();
    }
}
