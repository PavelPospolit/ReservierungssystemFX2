package com.trainee.ReservierungssystemFX.Classes;/*Create a Reservierungen Constructor*/

public class Reservierungen {
    private String sReservierungsnummer;
    private String smaName;
    private String sBisWann;
    private String sRaumNummer;
    private String sAbwann;

    public String getsReservierungsnummer() {
        return sReservierungsnummer;
    }

    public String getSmaName() {
        return smaName;
    }

    public String getsBisWann() {
        return sBisWann;
    }

    public String getsRaumNummer() {
        return sRaumNummer;
    }

    public String getsAbwann() {
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
