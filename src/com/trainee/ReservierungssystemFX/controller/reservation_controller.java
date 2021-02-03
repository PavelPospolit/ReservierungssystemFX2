package com.trainee.ReservierungssystemFX.controller;

import com.trainee.ReservierungssystemFX.Classes.Reservierungen;
import com.trainee.ReservierungssystemFX.actions.Random_Number_Generator;
import com.trainee.ReservierungssystemFX.actions.Schreiben;
import com.trainee.ReservierungssystemFX.resources.DatenErzeugnung;
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

        for(String key: getAllReservations().keySet()) {
            System.out.println(key);
            System.out.println(DatenErzeugnung.getAllReservations().get(key).getsReservierungsnummer());
            System.out.println(DatenErzeugnung.getAllReservations().get(key).getsRaumNummer());
            System.out.println(DatenErzeugnung.getAllReservations().get(key).getsAbwann());
            System.out.println(DatenErzeugnung.getAllReservations().get(key).getsBisWann());

        }






        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERFOLG!");
        alert.setContentText("Raum erfolgreich gebucht");
        alert.showAndWait();



        for(String key: getAllReservations().keySet()){

            Reservierungen res = getReservation(key);
            if(res.getsRaumNummer().equals(roomnumber)){

                DatenErzeugnung.getHmapRooms().get(roomnumber).setVerfuegbarkeit(false);

            }
        }


        /*String[] hilfsStringKeyset = getAllReservations().keySet().toArray(new String[0]);
        for (int j = 0; j < hilfsStringKeyset.length; j++) {
            String raumnummer = getAllReservations().get(hilfsStringKeyset[j]).getsRaumNummer();
            if (DatenErzeugnung.getHmapRooms().get(raumnummer).getRaumNr().equals(roomnumber)) {
                DatenErzeugnung.getHmapRooms().get(roomnumber).setVerfuegbarkeit(false);
            }
        }*/



        Parent parentRegestrierung = FXMLLoader.load(getClass().getClassLoader().getResource("com/trainee/ReservierungssystemFX/FXML/reservation.fxml"));
        Scene sceneRegestrierung = new Scene(parentRegestrierung);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(sceneRegestrierung);
        window.setTitle("Reservierung");
        window.show();
    }

    public void safeAndCloseClick(MouseEvent mouseEvent) throws IOException {
        Schreiben s = new Schreiben();
        System.exit(0);
    }

    public void onMouseClickshowRes(MouseEvent mouseEvent) throws IOException {
        Parent parentShowReservation = FXMLLoader.load(getClass().getClassLoader().getResource("com/trainee/ReservierungssystemFX/FXML/show_reservations.fxml"));
        Scene sceneShowReservation = new Scene(parentShowReservation);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(sceneShowReservation);
        window.setTitle("Alle Reservierungen");
        window.show();
    }

    public void onMouseClickCancelRes(MouseEvent mouseEvent) throws IOException {
        Parent parentCancelReservation = FXMLLoader.load(getClass().getClassLoader().getResource("com/trainee/ReservierungssystemFX/FXML/cancel_reservation.fxml"));
        Scene sceneCancelReservation = new Scene(parentCancelReservation);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(sceneCancelReservation);
        window.setTitle("Buchung Zurückziehen");
        window.show();

    }

    public void SafeAndBackClick(MouseEvent mouseEvent) throws IOException {
        Parent parentRegestrierung = FXMLLoader.load(getClass().getClassLoader().getResource("com/trainee/ReservierungssystemFX/FXML/log_in.fxml"));
        Scene sceneRegestrierung = new Scene(parentRegestrierung);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(sceneRegestrierung);
        window.setTitle("Anmeldung");
        window.show();
    }
}
