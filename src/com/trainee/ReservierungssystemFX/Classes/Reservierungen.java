package com.trainee.ReservierungssystemFX.Classes;/*Create a com.trainee.ReservierungssystemFX.Class.Reservierungen Constructor*/

public class Reservierungen {
    private static String sReservierungsnummer;
    private static String smaName;
    private static String sBisWann;
    private static String sRaumNummer;
    private static String sAbwann;

    public static String getsReservierungsnummer() {
        return sReservierungsnummer;
    }
    public static String getSmaName() {
        return smaName;
    }
    public static String getsBisWann() {
        return sBisWann;
    }
    public static String getsRaumNummer() {
        return sRaumNummer;
    }
    public static void setiReservierungsnummer(int iReservierungsnummer) {
        Reservierungen.sReservierungsnummer = sReservierungsnummer;
    }
    public static void setSmaName(String smaName) {
        Reservierungen.smaName = smaName;
    }
    public static void setsBisWann(String sBisWann) {
        Reservierungen.sBisWann = sBisWann;
    }
    public static void setsRaumNummer(String sRaumNummer) {
        Reservierungen.sRaumNummer = sRaumNummer;
    }
    public static String getsAbwann() {
        return sAbwann;
    }
    public static void setsAbwann(String sAbwann) {
        Reservierungen.sAbwann = sAbwann;
    }
    public Reservierungen(String resNummer, String maName, String raumnummer, String abwann, String biswann) {
        sReservierungsnummer = resNummer;
        smaName = maName;
        sBisWann = biswann;
        sRaumNummer = raumnummer;
        sAbwann = abwann;
    }
}
