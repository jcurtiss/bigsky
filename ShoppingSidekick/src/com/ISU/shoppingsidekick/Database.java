package com.ISU.shoppingsidekick;

import java.sql.*;

public class Database {

	private static Connection connection;
	private static String UserID = "adm309";
	private static String Password = "EXbDqudt4";
	private static String Url = "jdbc:mysql://mysql.cs.iastate.edu:3306/db30916";
	
	public static void main (String [] args)
	{
		connectToDatabase();
		dropTables();
		createTables();
		createFoodItem("Pasta", "Barilla");
		ResultSet rs = getFoodItemByID(1);
		try {
			rs.next();
			System.out.println(rs.getString("Brand"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
	}
	
	public static void connectToDatabase()
	{
		try {   
	      Class.forName ("com.mysql.jdbc.Driver");
	      } 
	   catch (Exception E) {
	         System.err.println ("Unable to load driver.");
	         E.printStackTrace ();
	   } 	
	   try { 
	      connection = DriverManager.getConnection (Url, UserID, Password);
	      System.out.println ("*** Connected to the database ***"); 

	   }
	      catch (SQLException E) {
	         System.out.println ("SQLException: " + E.getMessage());
	         System.out.println ("SQLState: " + E.getSQLState());
	         System.out.println ("VendorError: " + E.getErrorCode());

	      } // End of catch
	}
	
	public static void disconnect()
	{
		try {
			connection.close();
			System.out.println ("*** Disconnected to the database ***"); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet getAccountInfoByID(int id)
	{
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Account WHERE AccountID = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResultSet getAccountInfoByUserID(String userID)
	{
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Account WHERE UserID = ?");
			ps.setString(1, userID);
			ResultSet rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean createAccount(String userID, String password, String email)
	{
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Account(UserID, Password, Email) VALUES (?, ?, ?)");
			ps.setString(1, userID);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public static boolean createFoodItem(String Name, String Brand)
	{
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Food(Name, Brand) VALUES (?, ?)");
			ps.setString(1, Name);
			ps.setString(2, Brand);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static ResultSet getFoodItemByID(int id)
	{
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Food WHERE ID = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}

	public static void dropTables()
	{
		try {
			connection.createStatement().executeUpdate("DROP TABLE Account");
	    	connection.createStatement().executeUpdate("DROP TABLE Food");
	    	connection.createStatement().executeUpdate("DROP TABLE Expiration");
	    	connection.createStatement().executeUpdate("DROP TABLE Price");
	    	connection.createStatement().executeUpdate("DROP TABLE Review");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createTables()
	{
      try
      {
		connection.createStatement().executeUpdate("CREATE TABLE Account(AccountID int NOT NULL AUTO_INCREMENT, UserID varchar(255) NOT NULL, Password varchar(255) NOT NULL, Email varchar(255) NOT NULL, PRIMARY KEY(AccountID))");
		connection.createStatement().executeUpdate("CREATE TABLE Food(ID int NOT NULL AUTO_INCREMENT, Name varchar(255) NOT NULL, Brand varchar(255), PRIMARY KEY(ID))");
		connection.createStatement().executeUpdate("CREATE TABLE Expiration(FoodID int, avgHours double, longHours int, shortHours int, numPricePoints int, FOREIGN KEY(FoodID) REFERENCES Food(ID))");
		connection.createStatement().executeUpdate("CREATE TABLE Price(FoodID int, avgPrice double, biggestPrice double, smallestPrice double, numPricePoints int, FOREIGN KEY(FoodID) REFERENCES Food(ID))");
		connection.createStatement().executeUpdate("CREATE TABLE Review(FoodID int, Rating int, Review varchar(255), CHECK(Rating > 0 AND Rating < 6))");
      }
      catch (SQLException e)
      {
    	  e.printStackTrace();
      }
	}
}