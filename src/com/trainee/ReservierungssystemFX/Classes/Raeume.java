package com.trainee.ReservierungssystemFX.Classes;/*Create a com.trainee.ReservierungssystemFX.Class.Raeume Constructor*/

public class Raeume {
    private final String sBezeichnung;
    private final int iKapazitaet;
    private String sEigenschaften;
    private final String sRaumNr;

    public String getRaumNr() {
        return sRaumNr;
    }
    public int getKapazitaet() {
        return iKapazitaet;
    }
    public String getBezeichnung() {
        return sBezeichnung;
    }
    public String getEigenschaften() {
        return sEigenschaften;
    }
    public void setEigenschaften(String eigenschaften) {
        sEigenschaften = eigenschaften;
    }

    public Raeume(String bezeichnung, String raumnummer, String eigenschaften, int kapazitaet) {
        sBezeichnung = bezeichnung;
        sEigenschaften = eigenschaften;
        iKapazitaet = kapazitaet;
        sRaumNr = raumnummer;
    }
}
