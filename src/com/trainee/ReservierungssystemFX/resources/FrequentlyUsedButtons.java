package com.trainee.ReservierungssystemFX.resources;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class FrequentlyUsedButtons {
    public static void showRes(MouseEvent mouseEvent) throws IOException {
        Parent parentShowReservation = FXMLLoader.load(FrequentlyUsedButtons.class.getClassLoader().getResource("com/trainee/ReservierungssystemFX/FXML/show_reservations.fxml"));
        Scene sceneShowReservation = new Scene(parentShowReservation);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(sceneShowReservation);
        window.setTitle("Alle Reservierungen");
        window.show();
    }
    public static void cancelReservatrionButton(MouseEvent mouseEvent) throws IOException{
        Parent parentCancelReservation = FXMLLoader.load(FrequentlyUsedButtons.class.getClassLoader().getResource("com/trainee/ReservierungssystemFX/FXML/cancel_reservation.fxml"));
        Scene sceneCancelReservation = new Scene(parentCancelReservation);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(sceneCancelReservation);
        window.setTitle("Buchung Zurückziehen");
        window.show();
    }
    public static void safeAndBack(MouseEvent mouseEvent) throws    IOException{
        Parent parentRegestrierung = FXMLLoader.load(FrequentlyUsedButtons.class.getClassLoader().getResource("com/trainee/ReservierungssystemFX/FXML/log_in.fxml"));
        Scene sceneRegestrierung = new Scene(parentRegestrierung);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(sceneRegestrierung);
        window.setTitle("Anmeldung");
        window.show();
    }
    public static void goToReservation(MouseEvent mouseEvent)throws IOException{
        Parent parentRegestrierung = FXMLLoader.load(FrequentlyUsedButtons.class.getClassLoader().getResource("com/trainee/ReservierungssystemFX/FXML/reservation.fxml"));
        Scene sceneRegestrierung = new Scene(parentRegestrierung);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(sceneRegestrierung);
        window.setTitle("Reservierung");
        window.show();
    }
}
