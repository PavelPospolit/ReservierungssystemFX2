package com.trainee.ReservierungssystemFX.resources;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import com.trainee.ReservierungssystemFX.actions.Schreiben;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class FrequentlyUsedButtons {
    public static void showRes(MouseEvent mouseEvent) throws IOException {
        Parent parentShowReservation = FXMLLoader.load(FrequentlyUsedButtons.class.getClassLoader().getResource("com/trainee/ReservierungssystemFX/FXML/show_reservations.fxml"));
        Scene sceneShowReservation = new Scene(parentShowReservation, 600, 400);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(sceneShowReservation);
        window.setTitle("Alle Reservierungen");
        window.show();
    }
    public static void cancelReservatrionButton(MouseEvent mouseEvent) throws IOException{
        Parent parentCancelReservation = FXMLLoader.load(FrequentlyUsedButtons.class.getClassLoader().getResource("com/trainee/ReservierungssystemFX/FXML/cancel_reservation.fxml"));
        Scene sceneCancelReservation = new Scene(parentCancelReservation, 600, 400);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(sceneCancelReservation);
        window.setTitle("Buchung Zurückziehen");
        window.show();
    }
    public static void safeAndBack(MouseEvent mouseEvent) throws    IOException{
        Parent parentRegestrierung = FXMLLoader.load(FrequentlyUsedButtons.class.getClassLoader().getResource("com/trainee/ReservierungssystemFX/FXML/log_in.fxml"));
        Scene sceneRegestrierung = new Scene(parentRegestrierung, 600, 400);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(sceneRegestrierung);
        window.setTitle("Anmeldung");
        window.show();
    }
    public static void goToReservation(MouseEvent mouseEvent)throws IOException{
        Parent parentRegestrierung = FXMLLoader.load(FrequentlyUsedButtons.class.getClassLoader().getResource("com/trainee/ReservierungssystemFX/FXML/reservation.fxml"));
        Scene sceneRegestrierung = new Scene(parentRegestrierung, 600, 400);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(sceneRegestrierung);
        window.setTitle("Reservierung");
        window.show();
    }
    public static void goToReservationKey(KeyEvent keyEvent)throws IOException{
        Parent parentRegestrierung = FXMLLoader.load(FrequentlyUsedButtons.class.getClassLoader().getResource("com/trainee/ReservierungssystemFX/FXML/reservation.fxml"));
        Scene sceneRegestrierung = new Scene(parentRegestrierung, 600, 400);
        Stage window = (Stage) ((Node) keyEvent.getSource()).getScene().getWindow();
        window.setScene(sceneRegestrierung);
        window.setTitle("Reservierung");
        window.show();
    }
    public static void goToSignIn(MouseEvent mouseEvent) throws IOException{
        Parent parentRegestrierung = FXMLLoader.load(FrequentlyUsedButtons.class.getClassLoader().getResource("com/trainee/ReservierungssystemFX/FXML/sign_in.fxml"));
        Scene sceneRegestrierung = new Scene(parentRegestrierung, 600, 400);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(sceneRegestrierung);
        window.setTitle("Regestrierung");
        window.show();
    }
    public static void goToSignInKey(KeyEvent keyEvent) throws IOException{
        Parent parentRegestrierung = FXMLLoader.load(FrequentlyUsedButtons.class.getClassLoader().getResource("com/trainee/ReservierungssystemFX/FXML/sign_in.fxml"));
        Scene sceneRegestrierung = new Scene(parentRegestrierung, 600, 400);
        Stage window = (Stage) ((Node) keyEvent.getSource()).getScene().getWindow();
        window.setScene(sceneRegestrierung);
        window.setTitle("Regestrierung");
        window.show();
    }

    public static void closeAndExit() throws IOException {
        Schreiben s = new Schreiben();
        System.exit(0);
    }
}
