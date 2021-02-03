package com.trainee.ReservierungssystemFX.controller;

import com.trainee.ReservierungssystemFX.Classes.Reservierungen;
import com.trainee.ReservierungssystemFX.actions.Random_Number_Generator;
import com.trainee.ReservierungssystemFX.actions.Schreiben;
import com.trainee.ReservierungssystemFX.resources.DatenErzeugnung;
import com.trainee.ReservierungssystemFX.resources.FrequentlyUsedButtons;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static com.trainee.ReservierungssystemFX.resources.DatenErzeugnung.*;

public class reservation_controller implements Initializable {
    @FXML
    public Button showReservationsButton;
    public Button returnRoomButton;
    @FXML
    Button safeAndClose;
    @FXML
    DatePicker startDate;
    @FXML
    DatePicker endDate;
    @FXML
    TextField startTime;
    @FXML
    TextField endTime;
    @FXML
    Button bookingButton;
    @FXML
    ChoiceBox<String> selectRoom;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }

    private void loadData() {
        String[] hilfsString = DatenErzeugnung.getHmapRooms().keySet().toArray(new String[0]);
        ArrayList<String> hilfsArray = new ArrayList<>();
        for (int i = 0; i < hilfsString.length; i++) {
            if (DatenErzeugnung.getHmapRooms().get(hilfsString[i]).getVerfuegbarkeit()) {
                hilfsArray.add(
                        DatenErzeugnung.getHmapRooms().get(hilfsString[i]).getBezeichnung() +
                                ", " +
                                DatenErzeugnung.getHmapRooms().get(hilfsString[i]).getRaumNr() +
                                ", " +
                                DatenErzeugnung.getHmapRooms().get(hilfsString[i]).getEigenschaften() +
                                ", " +
                                DatenErzeugnung.getHmapRooms().get(hilfsString[i]).getKapazitaet());
            }
        }
        selectRoom.getItems().addAll(hilfsArray);
    }

    public void bookingClick(MouseEvent mouseEvent) throws IOException {
        Random_Number_Generator rand = new Random_Number_Generator();

        String sBis = (endDate.getValue().toString() + ";" + endTime.getText());

        String sVon = (startDate.getValue().toString() + ";" + startTime.getText());

        String roomnumber = selectRoom.getValue().split(", ")[1];

        int iRand = rand.ResRandomNumber();
        String Buchungsnummer = "BU" + iRand;
        
        Reservierungen hilfsRes = new Reservierungen(
                Buchungsnummer,
                log_in_controller.sNameue,
                roomnumber,
                sVon,
                sBis);
        addReservierungen(Buchungsnummer, hilfsRes);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERFOLG!");
        alert.setContentText("Raum erfolgreich gebucht");
        alert.showAndWait();

        for (String key : getAllReservations().keySet()) {

            Reservierungen res = getReservation(key);
            if (res.getsRaumNummer().equals(roomnumber)) {

                DatenErzeugnung.getHmapRooms().get(roomnumber).setVerfuegbarkeit(false);

            }
        }

        FrequentlyUsedButtons.goToReservation(mouseEvent);
    }

    public void safeAndCloseClick(MouseEvent mouseEvent) throws IOException {
        Schreiben s = new Schreiben();
        System.exit(0);
    }

    public void onMouseClickshowRes(MouseEvent mouseEvent) throws IOException {
        FrequentlyUsedButtons.showRes(mouseEvent);
    }

    public void onMouseClickCancelRes(MouseEvent mouseEvent) throws IOException {
        FrequentlyUsedButtons.cancelReservatrionButton(mouseEvent);

    }

    public void SafeAndBackClick(MouseEvent mouseEvent) throws IOException {
        FrequentlyUsedButtons.safeAndBack(mouseEvent);
    }
}
