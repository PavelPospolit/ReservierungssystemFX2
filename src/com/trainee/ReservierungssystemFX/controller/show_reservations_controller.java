package com.trainee.ReservierungssystemFX.controller;

import com.trainee.ReservierungssystemFX.resources.CreateData;
import com.trainee.ReservierungssystemFX.resources.FrequentlyUsedButtons;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    public void ZeigeListe(){

        for(String key: getAllReservations().keySet()) {
            BuchungsNummer.setText(BuchungsNummer.getText() + CreateData.getAllReservations().get(key).getSmaName().split("@")[0]+"\n");
            RaumNummer.setText(RaumNummer.getText()+ CreateData.getAllReservations().get(key).getsRaumNummer()+"\n");
            BuchungVon.setText(BuchungVon.getText()+ CreateData.getAllReservations().get(key).getsAbwann()+"\n");
            BuchungBis.setText(BuchungBis.getText()+ CreateData.getAllReservations().get(key).getsBisWann()+"\n");

        }
    }

    public void updateButtonClick(MouseEvent mouseEvent) throws IOException {
        FrequentlyUsedButtons.showRes(mouseEvent);
    }
}
