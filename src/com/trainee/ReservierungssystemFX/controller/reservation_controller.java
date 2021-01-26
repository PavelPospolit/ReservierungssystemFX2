package com.trainee.ReservierungssystemFX.controller;

import com.trainee.ReservierungssystemFX.Classes.Reservierungen;
import com.trainee.ReservierungssystemFX.actions.Random_Number_Generator;
import com.trainee.ReservierungssystemFX.actions.Schreiben;
import com.trainee.ReservierungssystemFX.resources.DatenErzeugnung;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class reservation_controller implements Initializable {
    @FXML
    Button safeAndClose;
    @FXML
    Label emailLabel;
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

    public Label getEmailLabel() {
        return emailLabel;
    }

    public void setEmailLabel(Label emailLabel) {
        this.emailLabel = emailLabel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }

    private void loadData() {
        emailLabel.setText(log_in_controller.sNameue);
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
                                DatenErzeugnung.getHmapRooms().get(hilfsString[i]).getKapazitaet() +
                                ", " +
                                DatenErzeugnung.getHmapRooms().get(hilfsString[i]).getVerfuegbarkeit());
            }
        }
        selectRoom.getItems().addAll(hilfsArray);
    }

    public void bookingClick(javafx.scene.input.MouseEvent mouseEvent) throws ParseException {
        Random_Number_Generator rand = new Random_Number_Generator();
        String sBis = (endDate.getValue().toString() +";"+endTime.getText());
        String sVon = (startDate.getValue().toString()+";"+startTime.getText());
        String roomNumber = selectRoom.getValue();
        String[] roombnumbersplitter = roomNumber.split(",");
        int iRand = rand.ResRandomNumber();
        String Buchungsnummer = "BU" + iRand;
        HashMap<String, Reservierungen> hilfsmap = new HashMap<>();
        Reservierungen hilfsRes = new Reservierungen(
                Buchungsnummer,
                log_in_controller.sNameue,
                roombnumbersplitter[0],
                sVon,
                sBis);
        hilfsmap.put(Buchungsnummer, hilfsRes);
        String[] hilfsString = DatenErzeugnung.getHmapReservierungen().keySet().toArray(new String[0]);
        for (int i = 0; i < hilfsString.length; i++) {
            hilfsmap.put(hilfsString[i], DatenErzeugnung.getHmapReservierungen().get(hilfsString[i]));
        }
        DatenErzeugnung.setHmapReservierungen(hilfsmap);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ERFOLG!");
        alert.setContentText("Raum erfolgreich gebucht");

        alert.showAndWait();
        if (DatenErzeugnung.getHmapRooms().containsKey(roomNumber)) {
            DatenErzeugnung.getHmapRooms().get(roomNumber).setVerfuegbarkeit(false);
        }
    }

    public void safeAndCloseClick(MouseEvent mouseEvent) throws IOException {
        Schreiben s = new Schreiben();
        System.exit(0);
    }
}
