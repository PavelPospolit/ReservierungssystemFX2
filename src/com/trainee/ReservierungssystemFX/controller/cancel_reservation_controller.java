package com.trainee.ReservierungssystemFX.controller;

import com.trainee.ReservierungssystemFX.actions.Schreiben;
import com.trainee.ReservierungssystemFX.resources.DatenErzeugnung;
import com.trainee.ReservierungssystemFX.resources.FrequentlyUsedButtons;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.trainee.ReservierungssystemFX.resources.DatenErzeugnung.getAllReservations;

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
        for (String key : getAllReservations().keySet()) {
            if (DatenErzeugnung.getReservation(key).getSmaName().equals( log_in_controller.sName)) {
                hilfsArray.add(
                        DatenErzeugnung.getHmapRooms().get(DatenErzeugnung.getAllReservations().get(key).getsRaumNummer()).getBezeichnung() +
                                ", " +
                                DatenErzeugnung.getHmapRooms().get(DatenErzeugnung.getAllReservations().get(key).getsRaumNummer()).getRaumNr() +
                                ", " +
                                DatenErzeugnung.getHmapRooms().get(DatenErzeugnung.getAllReservations().get(key).getsRaumNummer()).getEigenschaften() +
                                ", " +
                                DatenErzeugnung.getHmapRooms().get(DatenErzeugnung.getAllReservations().get(key).getsRaumNummer()).getKapazitaet());
            }
        }
        selectCancelRoom.getItems().addAll(hilfsArray);
    }

    public void CancelClick(MouseEvent mouseEvent) throws IOException {
        String sZurueckNr = selectCancelRoom.getValue().split(", ")[1];

        for (String key : getAllReservations().keySet()) {

            if (DatenErzeugnung.getReservation(key).getsRaumNummer().equals(sZurueckNr)) {

                DatenErzeugnung.getAllReservations().remove(key);

            }

        }

        if (DatenErzeugnung.getHmapRooms().containsKey(sZurueckNr)) {
            DatenErzeugnung.getHmapRooms().get(sZurueckNr).setVerfuegbarkeit(true);

        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERFOLG!");
        alert.setContentText("Raum erfolgreich zurückgegeben");
        alert.showAndWait();
        FrequentlyUsedButtons.cancelReservatrionButton(mouseEvent);
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

    public void safeAndCloseClick(MouseEvent mouseEvent) throws IOException {
        Schreiben s = new Schreiben();
        System.exit(0);
    }
}
