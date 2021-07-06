SELECT Emailaddress, ReservationID, Roomnumber, Starting_Date, Starting_Time, Ending_Date, Ending_Time
FROM ReservationsHistory RH
INNER JOIN Employees E
ON RH.EmployeeID = E.EmployeeID
