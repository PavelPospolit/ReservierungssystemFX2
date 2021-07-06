package com.trainee.ReservierungssystemFX.actions;

/*Creates a random number for 2 cases:
 *employee (Mitarbeiter) and booking (Reservierung)
 */

import com.trainee.ReservierungssystemFX.resources.Constants;

import java.sql.*;

public class Random_Number_Generator {
    public int MaRandomNumber() {
        int iRand = 0;
        try (Connection con = DriverManager.getConnection(Constants.sql_url); Statement stmt = con.createStatement();) {
            while (true) {
                iRand = (int) (Math.random() * 99999999) + 10000000;
                String SQL = "SELECT EmployeeID FROM dbo.Employees WHERE EmployeeID like'" + iRand + "'";
                ResultSet rs = stmt.executeQuery(SQL);
                if (!rs.next()) {
                    break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return iRand;
    }

    public int ResRandomNumber() {
        int iRand = 0;
        try (Connection con = DriverManager.getConnection(Constants.sql_url); Statement stmt = con.createStatement();) {
            while (true) {
                iRand = (int) (Math.random() * 99999999) + 10000000;
                String SQL = "SELECT ReservationID FROM dbo.Reservations WHERE ReservationID like'" + iRand + "'";
                ResultSet rs = stmt.executeQuery(SQL);
                if (!rs.next()) {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return iRand;
    }
    public int HistRandomNumber() {
        int iRand = 0;
        try (Connection con = DriverManager.getConnection(Constants.sql_url); Statement stmt = con.createStatement();) {
            while (true) {
                iRand = (int) (Math.random() * 99999999) + 10000000;
                String SQL = "SELECT HistoryID FROM dbo.ReservationsHistory WHERE HistoryID like'" + iRand + "'";
                ResultSet rs = stmt.executeQuery(SQL);
                if (!rs.next()) {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return iRand;
    }
}
