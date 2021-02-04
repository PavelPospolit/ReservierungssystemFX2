package com.trainee.ReservierungssystemFX.resources;


import javafx.scene.control.Alert;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;


/*
 *List of constants
 *font, colors, Border, paths,...;
 */

public class Constants {

    public static  final Font Listen_Font = new Font("TeleNeo Office", Font.PLAIN, 20);
    public static  final Border Listen_Border = new LineBorder(new java.awt.Color(170, 20, 150));
    public static  final File rFile = new File("src\\com\\trainee\\ReservierungssystemFX\\resources\\Raumliste.txt");
    public static  final File mFile = new File("src\\com\\trainee\\ReservierungssystemFX\\resources\\Mitarbeiter.txt");
    public static  final File resFile = new File("src\\com\\trainee\\ReservierungssystemFX\\resources\\Reservierungen.txt");
    public static  SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd;HH:mm");


    public static  BufferedReader mEinlesen;
    static  {
        try {
            mEinlesen = new BufferedReader(new FileReader("src\\com\\trainee\\ReservierungssystemFX\\resources\\Mitarbeiter.txt"));
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Datei Fehlt");
            alert.setContentText("Mitarbeiterliste nicht gefunden, bitte Support kontaktieren");

            alert.showAndWait();
        }
    }

    public static  BufferedReader rEinlesen;
    static  {
        try {
            rEinlesen = new BufferedReader(new FileReader("src\\com\\trainee\\ReservierungssystemFX\\resources\\Raumliste.txt"));
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Datei Fehlt");
            alert.setContentText("Raumliste nicht gefunden, bitte Support kontaktieren");

            alert.showAndWait();
        }
    }

    public static  BufferedReader resEinlesen;
    static  {
        try {
            resEinlesen = new BufferedReader(new FileReader("src\\com\\trainee\\ReservierungssystemFX\\resources\\Reservierungen.txt"));
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Datei Fehlt");
            alert.setContentText("Reservierungsliste nicht gefunden, bitte Support kontaktieren");

            alert.showAndWait();

        }
    }
}
