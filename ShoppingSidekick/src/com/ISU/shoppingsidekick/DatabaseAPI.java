package com.ISU.shoppingsidekick;

import java.sql.*;	
import java.util.ArrayList;
import java.util.List;

/**
 * Database API for the ShopppingSidekick application.
 * @author Sean Cavanaugh
 *
 */
public class DatabaseAPI {

	
	//Class variables
	
	/**
	 * Connection to the database being used
	 */
	private Connection connection;
	
	/**
	 * User ID from the database being used
	 */
	private String UserID = "adm309";
	
	/**
	 * Password for the User ID
	 */
	private String Password = "EXbDqudt4";
	
	/**
	 * URL location of the database
	 */
	private String Url = "jdbc:mysql://mysql.cs.iastate.edu:3306/db30916";
	
	//Constructors
	
	/**
	 * Create a new DatabaseAPI Object. This will automatically connect to the database. Remember to call the disconnect method when done with this object!
	 */
	public DatabaseAPI()
	{
		connectToDatabase();
	}
	
	//Container Classes
	
	/**
	 * Container class for the Account table
	 * @author Sean Cavanaugh
	 *
	 */
	public class Account
	{
		/**
		 * ID for the row
		 */
		public int ID;
		/**
		 * UserID for the row
		 */
		public String UserID;
		/**
		 * Password for the row
		 */
		public String Password;
		/**
		 * Email for the row
		 */
		public String Email;
		/**
		 * The name of the user's table in the database
		 */
		public String UserTable;
	}
	
	/**
	 * Container class for the Food table
	 * @author Sean Cavanaugh
	 *
	 */
	public class Food
	{
		/**
		 * ID for the food
		 */
		public String ID;
		/**
		 * Name of the food
		 */
		public String Name;
		/**
		 * Brand of the food
		 */
		public String Brand;
		/**
		 * FoodGroup the food belongs to
		 */
		public String FoodGroup;
		/**
		 * Expiration information of the food. This is a saved as an Expiration class object
		 */
		public Expiration ExpirationInformation;
		/**
		 * Price information of the food. This is a saved as an Price class object
		 */
		public Price PriceInformation;
		/**
		 * Review information of the food. This is a saved as a list of Review class objects
		 */
		public List<Review> ReviewInformation;
	}
	
	/**
	 * Container class for the Expiration table
	 * @author Sean Cavanaugh
	 *
	 */
	public class Expiration
	{
		/**
		 * ID for the food
		 */
		public String FoodID;
		/**
		 * The largest amount of hours inputed so far
		 */
		public int longHours;
		/**
		 * The smallest amount of hours inputed so far
		 */
		public int shortHours;
		/**
		 * The average expiration time
		 */
		public double avgHours;
		/**
		 * The number of datapoints this item has
		 */
		public int numPoints;
	}
	
	/**
	 * Container class for the Price table
	 * @author Sean Cavanaugh
	 *
	 */
	public class Price
	{
		/**
		 * ID of the food
		 */
		public String FoodID;
		/**
		 * The largest price inputed so far
		 */
		public double biggestPrice;
		/**
		 * The average price inputed so far
		 */
		public double avgPrice;
		/**
		 * The smallest price inputed so far
		 */
		public double smallestPrice;
		/**
		 * The number of datapoints this item has
		 */
		public int numPricePoints;
		
	}

	/**
	 * Container class for the Review table
	 * @author Sean Cavanaugh
	 *
	 */
	public class Review
	{
		/**
		 * ID of the food
		 */
		public String FoodID;
		/**
		 * Rating (1-5) of the food
		 */
		public int Rating;
		/**
		 * Review of the food
		 */
		public String Review;
	}
	
	/**
	 * Container class for Calendar items
	 * @author Sean Cavanaugh
	 *
	 */
	public class CalendarItem
	{
		/**
		 * Represents a food object
		 */
		public Food Food;
		/**
		 * Represents the date the food was entered
		 */
		public Date DateStarted;
		/**
		 * Represents the date the food will expire
		 */
		public Date DateExpired;
	}
	
	//Public get methods
	
	/**
	 * Gets an entire Account object/row that matches with the ID given. 
	 * @param userID The userID of the user requested
	 * @return An Account object or null if the ID does not exist
	 */
	public Account getAccountInfoByID(int id)
	{
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Account WHERE AccountID = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				Account account = new Account();
				account.Email = rs.getString("Email");
				account.Password = rs.getString("Password");
				account.UserID = rs.getString("UserID");
				account.ID = rs.getInt("AccountID");
				account.UserTable = rs.getString("UserTable");
				return account;
			}
		} catch (SQLException e) {
			System.out.println("There was an error in the getAccountInfoByID method. Error message: " + e.getMessage());
		}
		return null;
	}
	
	/**
	 * Gets an entire Account object/row that matches with the UserID given. 
	 * @param userID The userID of the user requested
	 * @return An Account object or null if the UserID does not exist
	 */
	public Account getAccountInfoByUserID(String userID)
	{
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Account WHERE UserID = ?");
			ps.setString(1, userID);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				Account account = new Account();
				account.Email = rs.getString("Email");
				account.Password = rs.getString("Password");
				account.UserID = rs.getString("UserID");
				account.ID = rs.getInt("AccountID");
				account.UserTable = rs.getString("UserTable");
				return account;
			}
		} catch (SQLException e) {
			System.out.println("There was an error in the getAccountInfoByUserID method and the creation was unsuccessful. Error message: " + e.getMessage());
		}
		return null;
	}
	
	/**
	 * Gets a Food object/row that matches with the ID given.
	 * @param id The ID given
	 * @return A Food Object or null if the ID does not exist in the table
	 */
	public Food getFoodItemByID(String foodID)
	{
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Food WHERE ID = ?");
			ps.setString(1, foodID);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				Food food = new Food();
				food.FoodGroup = rs.getString("FoodGroup");
				food.Name = rs.getString("Name");
				food.Brand = rs.getString("Brand");
				food.ID = rs.getString("ID");
				food.ExpirationInformation = getExpirationItemByID(foodID);
				food.PriceInformation = getPriceItemByID(foodID);
				food.ReviewInformation = getReviewsByID(foodID);
				return food;
			}
		} catch (SQLException e) {
			System.out.println("There was an error in the getFoodItemByID method. Error message: " + e.getMessage());
		}
		return null;	
	}
	
	/**
	 * Gets a Expiration object/row that matches with the ID given.
	 * @param id The ID given
	 * @return A Expiration Object or null if the ID does not exist in the table
	 */
	public Expiration getExpirationItemByID(String foodID)
	{
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Expiration WHERE FoodID = ?");
			ps.setString(1, foodID);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				Expiration expiration = new Expiration();
				expiration.FoodID = rs.getString("FoodID");
				expiration.avgHours = rs.getDouble("avgHours");
				expiration.longHours = rs.getInt("longHours");
				expiration.numPoints = rs.getInt("numPoints");
				expiration.shortHours = rs.getInt("shortHours");
				return expiration;
			}
		} catch (SQLException e) {
			System.out.println("There was an error in the getExpirationItemByID method. Error message: " + e.getMessage());
		}
		return null;	
	}

	/**
	 * Gets a Price object/row that matches with the ID given.
	 * @param id The ID given
	 * @return A Price Object or null if the ID does not exist in the table
	 */
	public Price getPriceItemByID(String foodID)
	{
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Price WHERE FoodID = ?");
			ps.setString(1, foodID);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				Price price = new Price();
				price.FoodID = rs.getString("FoodID");
				price.avgPrice = rs.getDouble("avgPrice");
				price.biggestPrice = rs.getDouble("biggestPrice");
				price.smallestPrice = rs.getDouble("smallestPrice");
				price.numPricePoints = rs.getInt("numPricePoints");
				return price;
			}
		} catch (SQLException e) {
			System.out.println("There was an error in the getPriceItemByID method. Error message: " + e.getMessage());
		}
		return null;	
	}
	
	/**
	 * Get getReviewsByID objects/rows that match with the ID given.
	 * @param id The ID given
	 * @return A list of Review Objects or null if the ID does not exist in the table
	 */
	public ArrayList<Review> getReviewsByID(String foodID)
	{
		ArrayList<Review> reviews = new ArrayList<Review>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Reviews WHERE FoodID = ?");
			ps.setString(1, foodID);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Review review = new Review();
				review.FoodID = rs.getString("FoodID");
				review.Rating = rs.getInt("Rating");
				review.Review = rs.getString("Review");
				reviews.add(review);
			} 
		} catch (SQLException e) {
			System.out.println("There was an error in the getReviewsByID method. Error message: " + e.getMessage());
		}
		return reviews;	
	}
		
	/**
	 * Gets a list of Food objects where the objects name field contains the given name parameter.
	 * @param name The name to search for
	 * @return A list of Food objects that match the name given
	 */
	public ArrayList<Food> getFoodByFuzzyNameMatch(String name)
	{
		ArrayList<Food> foods = new ArrayList<Food>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Food WHERE Name LIKE ?");
			ps.setString(1, "%"+name+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Food food = new Food();
				food.Name = rs.getString("Name");
				food.Brand = rs.getString("Brand");
				food.FoodGroup = rs.getString("FoodGroup");
				food.ID = rs.getString("ID");
				food.ExpirationInformation = getExpirationItemByID(food.ID);
				food.PriceInformation = getPriceItemByID(food.ID);
				food.ReviewInformation = getReviewsByID(food.ID);
				foods.add(food);
			}
		} catch (SQLException e) {
			System.out.println("There was an error in the getFoodByFuzzyNameMatch method. Error message: " + e.getMessage());
		}
		return foods;
	}
	
	/**
	 * Gets a list of Food objects where the objects name field contains the given FoodGroup parameter.
	 * @param foodGroup The FoodGroup to search for
	 * @return A list of Food objects that match the name given
	 */
	public ArrayList<Food> getFoodByFuzzyFoodGroupMatch(String foodGroup)
	{
		ArrayList<Food> foods = new ArrayList<Food>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Food WHERE FoodGroup LIKE ?");
			ps.setString(1, "%"+foodGroup+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Food food = new Food();
				food.Name = rs.getString("Name");
				food.Brand = rs.getString("Brand");
				food.FoodGroup = rs.getString("FoodGroup");
				food.ID = rs.getString("ID");
				food.ExpirationInformation = getExpirationItemByID(food.ID);
				food.PriceInformation = getPriceItemByID(food.ID);
				food.ReviewInformation = getReviewsByID(food.ID);
				foods.add(food);
			}
		} catch (SQLException e) {
			System.out.println("There was an error in the getFoodByFuzzyFoodGroupMatch method. Error message: " + e.getMessage());
		}
		return foods;
	}
	 
	/**
	 * Gets a list of items the user currently has in their database
	 * @param userID The user to get the information for
	 * @return A list of all the items the user has
	 */
	public List<CalendarItem> getUsersItems(String userID)
	{
		ArrayList<CalendarItem> calendarItems = new ArrayList<CalendarItem>();
		try {
			ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM " + userID + "TABLE");
			while(rs.next())
			{
				CalendarItem calendarItem = new CalendarItem();
				calendarItem.DateExpired = rs.getDate("DateExpired");
				calendarItem.DateExpired = rs.getDate("DateExpired");
				calendarItem.Food = getFoodItemByID(rs.getString("FoodID"));
				calendarItems.add(calendarItem);
			}
		} catch (SQLException e) {
			System.out.println("There was an error in the getUsersItems method. Error message: " + e.getMessage());
		}
		return calendarItems;
	}	
		
	//Public create methods
	
	/**
	 * Creates a new row in the Account table with the given parameters as data. ID is auto incremented and doesn't need to be set.
	 * @param userID a NOT NULL user id for the row
	 * @param password a NOT NULL password for the row
	 * @param email a NOT NULL email for the row
	 * @return whether or not the creation was successful
	 */
	public boolean createAccount(String userID, String password, String email)
	{
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Account(UserID, Password, Email, UserTable) VALUES (?, ?, ?, ?)");
			ps.setString(1, userID);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, userID+"Table");
			ps.executeUpdate();
			String createTableString = "CREATE TABLE " + userID+"Table(FoodID varchar(255), DateEntered DateTime, DateExpired DateTime)";
			connection.createStatement().executeUpdate(createTableString);
		} catch (SQLException e) {
			System.out.println("There was an error in the createAccount method and the creation was unsuccessful. Error message: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * Creates a new row in the Food table with the given parameters as data. ID is auto incremented and doesn't need to be set.
	 * @param Name a Name for the new food item
	 * @param Brand a Brand for the new food item
	 * @return Whether or not the creation was successful or not
	 */
	public boolean createFoodItem(String id, String name, String brand, String foodGroup)
	{
		try {
			//Create record in the Food table
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Food(ID, Name, Brand, FoodGroup) VALUES (?, ?, ?, ?)");
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, brand);
			ps.setString(4, foodGroup);
			ps.executeUpdate();
			//Create record in the Expiration table
			ps = connection.prepareStatement("INSERT INTO Expiration(FoodID, avgHours, longHours, shortHours, numPoints) VALUES (?, ?, ?, ?, ?)");
			ps.setString(1, id);
			ps.setDouble(2, 0.0);
			ps.setInt(3, 0);
			ps.setInt(4, 0);
			ps.setInt(5, 0);
			ps.executeUpdate();
			//Create record in the price table
			ps = connection.prepareStatement("INSERT INTO Price(FoodID, avgPrice, biggestPrice, smallestPrice, numPricePoints) VALUES (?, ?, ?, ?, ?)");
			ps.setString(1, id);
			ps.setDouble(2, 0.0);
			ps.setDouble(3, 0.0);
			ps.setDouble(4, 0.0);
			ps.setInt(5, 0);
			ps.executeUpdate();
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("There was an error in the createFoodItem method and the creation was unsuccessful. Error message: " + e.getMessage());
			return false;
		}
		return true;
	}
		
	/**
	 * Updates the row with the given information
	 * @param foodID The ID of the food
	 * @param price The amount of time it takes the food to expire
	 * @return Whether or not the food information was updated successfully
	 */
	public boolean createPricePoint(String foodID, double price)
	{
		if(price <= 0)
		{
			throw new IllegalArgumentException();
		}
		try {
			//Find food price information
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Price WHERE FoodID = ?");
			ps.setString(1, foodID);
			ResultSet rs = ps.executeQuery();
			//Get price information out of the record
			double avgPrice = rs.getDouble("avgPrice");
			double biggestPrice = rs.getDouble("biggestPrice");
			double smallestPrice = rs.getDouble("avgPrice");
			int numPricePoints = rs.getInt("numPricePoints");
			//Find new average
			avgPrice = ((avgPrice*numPricePoints)+price)/++numPricePoints;
			biggestPrice = biggestPrice > price ? biggestPrice : price;
			smallestPrice = smallestPrice < price ? smallestPrice : price;
			//Put new information back into the database
			ps = connection.prepareStatement("UPDATE Price SET avgPrice=?, biggestPrice=?, smallestPrice=?, numPricePoints=?  WHERE FoodID = ?");
			ps.setDouble(1, avgPrice);
			ps.setDouble(2, biggestPrice);
			ps.setDouble(3, smallestPrice);
			ps.setInt(4, numPricePoints);
			ps.setString(5, foodID);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("There was an error in the createPricePoint method and the creation was unsuccessful. Error message: " + e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * Updates the row with the given information
	 * @param foodID The ID of the food
	 * @param hours The amount of time it takes the food to expire
	 * @return Whether or not the food information was updated successfully
	 */
	public boolean createExpirationPoint(String foodID, int hours)
	{
		if(hours <= 0)
		{
			throw new IllegalArgumentException();
		}
		try {
			//Find expiration information
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Expiration WHERE FoodID = ?");
			ps.setString(1, foodID);
			ResultSet rs = ps.executeQuery();
			//Get expiration information out of the record
			double avgHours = rs.getDouble("avgHours");
			int longHours = rs.getInt("longHours");
			int shortHours = rs.getInt("shortHours");
			int numPoints = rs.getInt("numPoints");
			//Find new average
			avgHours = ((avgHours*numPoints)+hours)/++numPoints;
			longHours = longHours > hours ? longHours : hours;
			shortHours = shortHours < hours ? shortHours : hours;
			//Put new information back into the database
			ps = connection.prepareStatement("UPDATE Expiration SET avgHours=?, longHours=?, shortHours=?, numPoints=?  WHERE FoodID = ?");
			ps.setDouble(1, avgHours);
			ps.setDouble(2, longHours);
			ps.setDouble(3, shortHours);
			ps.setInt(4, numPoints);
			ps.setString(5, foodID);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("There was an error in the createExpirationPoint method and the creation was unsuccessful. Error message: " + e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * Adds a review for the food item
	 * @param FoodID The ID for the food
	 * @param rating The rating of the food
	 * @param review The review of the food
	 * @return Whether or not the update was successful
	 */
	public boolean createReview(String FoodID, int rating, String review)
	{
		if(rating <= 0 || rating >= 6)
		{
			throw new IllegalArgumentException();
		}
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Rating(FoodID, Rating, Review) VALUES (?, ?, ?)");
			ps.setString(1, FoodID);
			ps.setInt(2, rating);
			ps.setString(3, review);
		} catch (SQLException e) {
			System.out.println("There was an error in the createReview method and the creation was unsuccessful. Error message: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	//Other public methods
	
	/**
	 * Allows the user to run a custom query against the database. This method should be used in extreme cases.
	 * @param queryString The query to perform
	 * @return The result set from the query
	 */
	public ResultSet customQuery(String queryString)
	{
		try {
			return connection.createStatement().executeQuery(queryString);
		} catch (SQLException e) {
			System.out.println("There was an error in the customQuery method. Error message: " + e.getMessage());
		}
		return null;
	}
	
	/**
	 * Allows the user to run a custom update against the database. This method should be used in extreme cases.
	 * @param updateString The update to run
	 * @return Whether or not the update ran successfully
	 */
	public boolean customUpdate(String updateString)
	{
		try {
		connection.createStatement().executeUpdate(updateString);
		} catch (SQLException e) {
			System.out.println("There was an error in the customUpdate method. Error message: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * Disconnections to the database being used for the ShoppingSidekick application.
	 */
 	public void disconnect()
	{
		try {
			connection.close();
			System.out.println ("*** Disconnected to the database ***"); 
		} catch (SQLException e) {
			System.out.println("There was an error in the disconnect method and the creation was unsuccessful. Error message: " + e.getMessage());
		}
	}
	
	/**
	 * Drops all tables for the ShopppingSidekick application. WARNING: This method should NEVER be called.
	 */
	public void dropTables()
	{
		try {
			connection.createStatement().executeUpdate("DROP TABLE Account");
	    	connection.createStatement().executeUpdate("DROP TABLE Food");
	    	connection.createStatement().executeUpdate("DROP TABLE Expiration");
	    	connection.createStatement().executeUpdate("DROP TABLE Price");
	    	connection.createStatement().executeUpdate("DROP TABLE Reviews");
		} catch (SQLException e) {
	    	  System.out.println("There was an error in the dropTables method. Error message: " + e.getMessage());
		}
	}
	
	/**
	 * Creates all the tables for the ShoppingSidekick application. These tables include Account, Food, Expiration, Price, Review. This method should not need to be called in normal situations.
	 */
	public void createTables()
	{
      try
      {
		connection.createStatement().executeUpdate("CREATE TABLE Account(AccountID int NOT NULL AUTO_INCREMENT, UserID varchar(255) NOT NULL, Password varchar(255) NOT NULL, UserTable varchar(255), Email varchar(255) NOT NULL, PRIMARY KEY(AccountID))");
		connection.createStatement().executeUpdate("CREATE TABLE Food(ID varchar(255) NOT NULL, Name varchar(255) NOT NULL, Brand varchar(255), FoodGroup varchar(255), PRIMARY KEY(ID))");
		connection.createStatement().executeUpdate("CREATE TABLE Expiration(FoodID varchar(255), avgHours double, longHours int, shortHours int, numPoints int, FOREIGN KEY(FoodID) REFERENCES Food(ID))");
		connection.createStatement().executeUpdate("CREATE TABLE Price(FoodID varchar(255), avgPrice double, biggestPrice double, smallestPrice double, numPricePoints int, FOREIGN KEY(FoodID) REFERENCES Food(ID))");
		connection.createStatement().executeUpdate("CREATE TABLE Reviews(FoodID varchar(255), Rating int, Review varchar(255), CHECK(Rating > 0 AND Rating < 6))");
      }
      catch (SQLException e)
      {
    	  System.out.println("There was an error in the createTables method. Error message: " + e.getMessage());
      }
	}
	
	//Private methods
	
	/**
	 * Connections to the database being used for the ShoppingSidekick application.
	 */
	private void connectToDatabase()
	{
		try {   
	      Class.forName ("com.mysql.jdbc.Driver");
	      } 
	   catch (Exception e) {
	         System.err.println ("Unable to load the JDBC driver. Error message: " + e.getMessage());
	   } 	
	   try { 
	      connection = DriverManager.getConnection (Url, UserID, Password);
	      System.out.println ("*** Connected to the database ***"); 
	   }
	      catch (SQLException e) {
	         System.out.println ("SQLException: " + e.getMessage());
	         System.out.println ("SQLState: " + e.getSQLState());
	         System.out.println ("VendorError: " + e.getErrorCode());
	      }
	}
}