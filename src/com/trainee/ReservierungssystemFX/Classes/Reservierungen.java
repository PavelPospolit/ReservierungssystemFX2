package com.trainee.ReservierungssystemFX.Classes;/*Create a Reservierungen Constructor*/

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
    public static String getsAbwann() {
        return sAbwann;
    }
    public Reservierungen(String resNummer, String maName, String raumnummer, String abwann, String biswann) {
        sReservierungsnummer = resNummer;
        smaName = maName;
        sBisWann = biswann;
        sRaumNummer = raumnummer;
        sAbwann = abwann;
    }
}
