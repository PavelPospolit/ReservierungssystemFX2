package com.trainee.ReservierungssystemFX.controller;

import com.trainee.ReservierungssystemFX.actions.Random_Number_Generator;
import com.trainee.ReservierungssystemFX.resources.FrequentlyUsedButtons;
import com.trainee.ReservierungssystemFX.resources.Constants;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class reservation_controller implements Initializable {
    @FXML
    public Button showReservationsButton;
    public Button returnRoomButton;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }

    private void loadData() {
        ArrayList<String> hilfsArray = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(Constants.sql_url); Statement stmt = con.createStatement();) {
            String SQL = "SELECT * FROM dbo.Rooms";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                hilfsArray.add(
                        rs.getInt("Roomnumber") +
                                ", " +
                                rs.getString("Roomdescritpion") +
                                ", " +
                                rs.getString("Roomspecials") +
                                ", " +
                                rs.getInt("Roomcapacity"));
            }
            selectRoom.getItems().addAll(hilfsArray);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void bookingClick(MouseEvent mouseEvent) throws IOException {
        Random_Number_Generator rand = new Random_Number_Generator();
        String sBisDate = (endDate.getValue().toString());
        String sBisTime = (endTime.getText());
        String sVonDate = (startDate.getValue().toString());
        String sVonTime = (startTime.getText());
        boolean bTimeFormat = true;
        try {
            Constants.tf.parse(sVonTime);
        } catch (ParseException e) {
            bTimeFormat = false;
        }
        try {
            Constants.tf.parse(sBisTime);
            Constants.tf.parse(sVonTime);
        } catch (ParseException e) {
            bTimeFormat = false;
        }
        Time timeCheck = Time.valueOf("24:00:01");

        if (bTimeFormat && (Time.valueOf(sBisTime + ":00").compareTo(timeCheck) < 0) &&
                (Time.valueOf(sVonTime + ":00").compareTo(timeCheck) < 0)) {
            String roomnumber = selectRoom.getValue().split(", ")[0];

            Date datumBIS = null;
            try {
                datumBIS = Constants.df.parse(sBisDate + ";" + sBisTime);
            } catch (ParseException e) {
            }
            Date datumVON = null;
            try {
                datumVON = Constants.df.parse(sVonDate + ";" + sVonTime);
            } catch (ParseException e) {
            }

            try (Connection con = DriverManager.getConnection(Constants.sql_url); Statement stmt = con.createStatement();) {
                String SQLres = "SELECT * FROM dbo.Reservations";
                ResultSet rsres = stmt.executeQuery(SQLres);
                while (rsres.next()) {
                    String sdatumVONRES = rsres.getDate("Starting_Date") + ";" + rsres.getTime("Starting_Time");
                    String sdatumBISRES = rsres.getDate("Ending_Date") + ";" + rsres.getTime("Ending_Time");


                    Date datumVONRES = null;
                    try {
                        datumVONRES = Constants.df.parse(sdatumVONRES);
                    } catch (ParseException e) {
                        System.out.println("Falsches Zeitformat");
                    }
                    Date datumBISRES = null;
                    try {
                        datumBISRES = Constants.df.parse(sdatumBISRES);
                    } catch (ParseException e) {
                        System.out.println("Falsches Zeitformat");
                    }
                    if (rsres.getInt("Roomnumber") == Integer.parseInt(roomnumber)) {
                        if ((datumVON.compareTo(datumVONRES) >= 0) && (datumVON.compareTo(datumBISRES) <= 0)) {
                            zuFrüh = false;
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("FALSCHE ZEIT");
                            alert.setContentText("Raum " + rsres.getInt("Roomnumber") + " ist " +
                                    "am " + sVonDate + " ;" + sVonTime + " noch nicht frei!");
                            alert.showAndWait();
                            FrequentlyUsedButtons.goToReservation(mouseEvent);
                            break;
                        }
                        if ((datumBIS.compareTo(datumBISRES) <= 0) && (datumBIS.compareTo(datumVONRES) >= 0)) {
                            zuSpät = false;
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("FALSCHE ZEIT");
                            alert.setContentText("Raum " + rsres.getInt("Roomnumber") + " ist " +
                                    "am " + sBisDate + " ;" + sBisTime + " noch nicht frei!");
                            alert.showAndWait();
                            FrequentlyUsedButtons.goToReservation(mouseEvent);
                            break;
                        }
                    }
                }
                String SQLemp = "SELECT * FROM Employees WHERE Emailaddress like'" + log_in_controller.sName + "'";
                ResultSet rsemp = stmt.executeQuery(SQLemp);
                String sNow;
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd;HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                sNow = dtf.format(now);
                boolean bDate = true;
                while (rsemp.next()) {
                    if ((zuSpät && zuFrüh) && (Constants.df.parse(sVonDate + ";" + sVonTime).compareTo(Constants.df.parse(sBisDate + ";" + sBisTime)) <= 0)&&
                            (Constants.df.parse(sNow).compareTo(Constants.df.parse(sVonDate+";"+sVonTime)) <= 0)) {
                        bDate = false;
                        int iRand = rand.ResRandomNumber();
                        String insert = "insert into Reservations values(?,?,?,?,?,?,?)";
                        PreparedStatement insertstmt = con.prepareStatement(insert);
                        insertstmt.setInt(1, iRand);
                        insertstmt.setInt(2, rsemp.getInt("EmployeeID"));
                        insertstmt.setInt(3, Integer.parseInt(roomnumber));
                        insertstmt.setDate(4, java.sql.Date.valueOf(sVonDate));
                        insertstmt.setTime(5, Time.valueOf(sVonTime + ":00"));
                        insertstmt.setDate(6, java.sql.Date.valueOf(sBisDate));
                        insertstmt.setTime(7, Time.valueOf(sBisTime + ":00"));
                        insertstmt.execute();

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("ERFOLG!");
                        alert.setContentText("Raum erfolgreich gebucht \nBuchungsnummer: " + iRand);
                        alert.showAndWait();

                        FrequentlyUsedButtons.goToReservation(mouseEvent);
                    }
                    else if((Constants.df.parse(sNow).compareTo(Constants.df.parse(sVonDate+";"+sVonTime)) >= 0)){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Zeitfehler");
                        alert.setContentText("Das Startdatum liegt in der Vergangenheit!");

                        alert.showAndWait();
                    }
                    else if ((Constants.df.parse(sVonDate + ";" + sVonTime).compareTo(Constants.df.parse(sBisDate + ";" + sBisTime)) >= 0)){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Zeitfehler");
                        alert.setContentText("Das Enddatum darf nicht vor dem Startdatum liegen!");

                        alert.showAndWait();
                    }
                }

            } catch (SQLException | ParseException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FEHLER!");
            alert.setContentText("Falsches Zeitformat");
            alert.showAndWait();
        }

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
