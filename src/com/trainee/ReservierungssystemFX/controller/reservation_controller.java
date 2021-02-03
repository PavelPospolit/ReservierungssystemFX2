package com.trainee.ReservierungssystemFX.controller;

import com.trainee.ReservierungssystemFX.Classes.Reservierungen;
import com.trainee.ReservierungssystemFX.actions.Random_Number_Generator;
import com.trainee.ReservierungssystemFX.actions.Schreiben;
import com.trainee.ReservierungssystemFX.resources.DatenErzeugnung;
import com.trainee.ReservierungssystemFX.resources.FrequentlyUsedButtons;
import com.trainee.ReservierungssystemFX.resources.Konstanten;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.*;

import static com.trainee.ReservierungssystemFX.resources.DatenErzeugnung.*;

public class reservation_controller implements Initializable {
    String date = Konstanten.df.format(new Date());
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
    boolean zuFrüh = true;
    boolean zuSpät = true;
    boolean rightRoom;

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

        Date datumBIS = null;
        try {
            datumBIS = Konstanten.df.parse(sBis);
        } catch (ParseException e) {
            System.out.println("Falsches Zeitformat");
        }
        Date datumVON = null;
        try {
            datumVON = Konstanten.df.parse(sVon);
        } catch (ParseException e) {
            System.out.println("Falsches Zeitformat");
        }


        for (String key : getAllReservations().keySet()) {

            Date datumVONRES = null;
            try {
                datumVONRES = Konstanten.df.parse(DatenErzeugnung.getReservation(key).getsAbwann());
            } catch (ParseException e) {
                System.out.println("Falsches Zeitformat");
            }
            Date datumBISRES = null;
            try {
                datumBISRES = Konstanten.df.parse(DatenErzeugnung.getReservation(key).getsBisWann());
            } catch (ParseException e) {
                System.out.println("Falsches Zeitformat");
            }

            if (DatenErzeugnung.getReservation(key).getsRaumNummer().equals(roomnumber)) {

                if ((datumVON.compareTo(datumVONRES) >= 0) && (datumVON.compareTo(datumBISRES) <= 0)) {
                    zuFrüh = false;
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("FALSCHE ZEIT");
                    alert.setContentText("Raum " + DatenErzeugnung.getReservation(key).getsRaumNummer() + " ist " +
                            "am " + sVon + " noch nicht frei!");
                    alert.showAndWait();
                    FrequentlyUsedButtons.goToReservation(mouseEvent);
                    break;
                }
                if ((datumBIS.compareTo(datumBISRES) <= 0) && (datumBIS.compareTo(datumVONRES) >= 0)) {
                    zuSpät = false;
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("FALSCHE ZEIT");
                    alert.setContentText("Raum " + DatenErzeugnung.getReservation(key).getsRaumNummer() + " ist " +
                            "am " + sBis + " noch nicht frei!");
                    alert.showAndWait();
                    FrequentlyUsedButtons.goToReservation(mouseEvent);
                    break;
                }
            }
        }
        if (zuSpät && zuFrüh) {
            int iRand = rand.ResRandomNumber();
            String Buchungsnummer = "BU" + iRand;

            Reservierungen hilfsRes = new Reservierungen(
                    Buchungsnummer,
                    log_in_controller.sName,
                    roomnumber,
                    sVon,
                    sBis);
            addReservierungen(Buchungsnummer, hilfsRes);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERFOLG!");
            alert.setContentText("Raum erfolgreich gebucht");
            alert.showAndWait();

            FrequentlyUsedButtons.goToReservation(mouseEvent);
        }


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
