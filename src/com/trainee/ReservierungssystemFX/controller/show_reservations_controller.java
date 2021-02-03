package com.trainee.ReservierungssystemFX.controller;

import com.trainee.ReservierungssystemFX.Classes.Reservierungen;
import com.trainee.ReservierungssystemFX.actions.Schreiben;
import com.trainee.ReservierungssystemFX.resources.DatenErzeugnung;
import com.trainee.ReservierungssystemFX.resources.FrequentlyUsedButtons;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.trainee.ReservierungssystemFX.resources.DatenErzeugnung.getAllReservations;
import static com.trainee.ReservierungssystemFX.resources.DatenErzeugnung.getReservation;

public class show_reservations_controller implements Initializable {
    public Button returnRoomButton;
    public Button backToReservationButton;
    public Button safeAndClose;
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

    public void safeAndCloseClick(MouseEvent mouseEvent) throws IOException {
        Schreiben s = new Schreiben();
        System.exit(0);
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
            BuchungsNummer.setText(BuchungsNummer.getText() + DatenErzeugnung.getAllReservations().get(key).getsReservierungsnummer()+"\n");
            RaumNummer.setText(RaumNummer.getText()+ DatenErzeugnung.getAllReservations().get(key).getsRaumNummer()+"\n");
            BuchungVon.setText(BuchungVon.getText()+ DatenErzeugnung.getAllReservations().get(key).getsAbwann()+"\n");
            BuchungBis.setText(BuchungBis.getText()+ DatenErzeugnung.getAllReservations().get(key).getsBisWann()+"\n");

        }
    }

    public void updateButtonClick(MouseEvent mouseEvent) throws IOException {
        FrequentlyUsedButtons.showRes(mouseEvent);
    }
}
