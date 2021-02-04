package com.trainee.ReservierungssystemFX.actions;

/*Creates a random number for 2 cases:
*employee (Mitarbeiter) and booking (Reservierung)
*/

import com.trainee.ReservierungssystemFX.resources.DatenErzeugnung;

public class Random_Number_Generator {
    public int MaRandomNumber(){
        int iRand;
        do {
            iRand = (int) (Math.random() * 99999999) + 10000000;
        } while (DatenErzeugnung.getAllEmployees().containsKey(("MA"+iRand)));
        return iRand;
    }
    public  int ResRandomNumber(){
        int iRand;
        do {
            iRand = (int) (Math.random() * 99999999) + 10000000;
        } while (DatenErzeugnung.getAllReservations().containsKey(("BU"+iRand)));
        return iRand;
    }
}
