package com.trainee.ReservierungssystemFX.resources;

import com.trainee.ReservierungssystemFX.Classes.Mitarbeiter;
import com.trainee.ReservierungssystemFX.Classes.Raeume;
import com.trainee.ReservierungssystemFX.Classes.Reservierungen;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

/*Creates 3 Objects com.trainee.ReservierungssystemFX.Class.Mitarbeiter (ma), com.trainee.ReservierungssystemFX.Class.Raeume (room), com.trainee.ReservierungssystemFX.Class.Reservierungen (res)
 * creates 3 Hashmaps (hmapMitarbeiter, hmapRaeume, hmapReservierungen)
 * puts the Data from Onjects imto Hashmaps*/

public class CreateData {
    static String sZeile;
    static final String UTF8_BOM = "\uFEFF";
    static String[] alhilfe;
    private static HashMap<String, Mitarbeiter> hmapMitarbeiter = new HashMap<>();
    private static HashMap<String, Raeume> hmapRaeume = new HashMap<>();
    private static HashMap<String, Reservierungen> hmapReservierungen = new HashMap<>();


    public static HashMap<String, Reservierungen> getAllReservations() {
        return hmapReservierungen;
    }

    public static Reservierungen getReservation(String key) {
        return hmapReservierungen.get(key);
    }

    public static void setHmapRooms(HashMap<String, Raeume> hmapRooms1) {
        hmapRaeume = hmapRooms1;
    }

    public static void addReservierungen(String stringKey, Reservierungen resClass) {
        hmapReservierungen.put(stringKey, resClass);
    }

    public static void addEmployee(String stringKey, Mitarbeiter eployeeClass) {
        hmapMitarbeiter.put(stringKey, eployeeClass);
    }

    public static Mitarbeiter getEmployee(String key) {
        return hmapMitarbeiter.get(key);
    }

    public static HashMap<String, Mitarbeiter> getAllEmployees() {
        return hmapMitarbeiter;
    }

    public static void addRoom(String stringKey, Raeume roomClass) {
        hmapRaeume.put(stringKey, roomClass);
    }

    public static Raeume getRoom(String key) {
        return hmapRaeume.get(key);
    }

    public static HashMap<String, Raeume> getAllRooms() {
        return hmapRaeume;
    }

    public static void leseAlle() throws IOException {
        verarbeiteZeilen(Constants.mEinlesen);
        verarbeiteZeilen(Constants.rEinlesen);
        verarbeiteZeilen(Constants.resEinlesen);
    }

    public static void verarbeiteZeilen(BufferedReader welcher) throws IOException {
        for (int i = 0; (CreateData.sZeile = welcher.readLine()) != null; i++) {
            if (CreateData.sZeile.startsWith(UTF8_BOM)) {
                CreateData.sZeile = CreateData.sZeile.substring(1);
            }
            alhilfe = CreateData.sZeile.split(", ");
            if (welcher == Constants.mEinlesen) {
                for (int j = 0; j <= alhilfe.length - 2; j += 3) {
                    Mitarbeiter ma = new Mitarbeiter(
                            alhilfe[j],
                            alhilfe[j + 1],
                            alhilfe[j + 2]);
                    hmapMitarbeiter.put(ma.getsMitarbeiterID(), ma);
                }
            }
            if (welcher == Constants.rEinlesen) {
                for (int j = 0; j <= alhilfe.length - 4; j += 5) {
                    Raeume room = new Raeume(
                            alhilfe[j],
                            alhilfe[j + 1],
                            alhilfe[j + 2],
                            Integer.parseInt(alhilfe[j + 3]));
                    hmapRaeume.put(room.getRaumNr(), room);
                }
            }
            if (welcher == Constants.resEinlesen) {
                for (int j = 0; j <= alhilfe.length - 4; j += 4) {
                    Reservierungen res = new Reservierungen(
                            alhilfe[j],
                            alhilfe[j + 1],
                            alhilfe[j + 2],
                            alhilfe[j + 3],
                            alhilfe[j + 4]);
                    addReservierungen(res.getsReservierungsnummer(), res);
                }
            }
        }
    }
}
