package com.trainee.ReservierungssystemFX.actions;

/*Creates a random number for 3 cases:
 *employee (Mitarbeiter), booking (Reservierung) and History
 */

import com.trainee.ReservierungssystemFX.resources.Constants;

import java.sql.*;

public class Random_Number_Generator {
    public int MaRandomNumber() {
        int iRand = 0;
        try (Connection con = DriverManager.getConnection(Constants.sql_url); Statement stmt = con.createStatement();) {
            while (true) {
                iRand = (int) (Math.random() * 99999999) + 1;
                String SQLHist = "SELECT HistoryID FROM dbo.ReservationsHistory WHERE HistoryID like'" + iRand + "'";
                ResultSet rsHist = stmt.executeQuery(SQLHist);
                if (!rsHist.next()) {
                    break;
                }
                String SQLRes = "SELECT ReservationID FROM dbo.Reservations WHERE ReservationID like'" + iRand + "'";
                ResultSet rsRes = stmt.executeQuery(SQLRes);
                if (!rsRes.next()) {
                    break;
                }
                String SQLEmp = "SELECT EmployeeID FROM dbo.Employees WHERE EmployeeID like'" + iRand + "'";
                ResultSet rsEmp = stmt.executeQuery(SQLEmp);
                if (!rsEmp.next()) {
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
                iRand = (int) (Math.random() * 99999999) + 1;
                String SQLHist = "SELECT HistoryID FROM dbo.ReservationsHistory WHERE HistoryID like'" + iRand + "'";
                ResultSet rsHist = stmt.executeQuery(SQLHist);
                if (!rsHist.next()) {
                    break;
                }
                String SQLRes = "SELECT ReservationID FROM dbo.Reservations WHERE ReservationID like'" + iRand + "'";
                ResultSet rsRes = stmt.executeQuery(SQLRes);
                if (!rsRes.next()) {
                    break;
                }
                String SQLEmp = "SELECT EmployeeID FROM dbo.Employees WHERE EmployeeID like'" + iRand + "'";
                ResultSet rsEmp = stmt.executeQuery(SQLEmp);
                if (!rsEmp.next()) {
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
                iRand = (int) (Math.random() * 99999999) + 1;
                String SQLHist = "SELECT HistoryID FROM dbo.ReservationsHistory WHERE HistoryID like'" + iRand + "'";
                ResultSet rsHist = stmt.executeQuery(SQLHist);
                if (!rsHist.next()) {
                    break;
                }
                String SQLRes = "SELECT ReservationID FROM dbo.Reservations WHERE ReservationID like'" + iRand + "'";
                ResultSet rsRes = stmt.executeQuery(SQLRes);
                if (!rsRes.next()) {
                    break;
                }
                String SQLEmp = "SELECT EmployeeID FROM dbo.Employees WHERE EmployeeID like'" + iRand + "'";
                ResultSet rsEmp = stmt.executeQuery(SQLEmp);
                if (!rsEmp.next()) {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return iRand;
    }
}
