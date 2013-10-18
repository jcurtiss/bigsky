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
		public int ID;
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
		public int FoodID;
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
		public int FoodID;
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
		public int FoodID;
		/**
		 * Rating (1-5) of the food
		 */
		public int Rating;
		/**
		 * Review of the food
		 */
		public String Review;
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
	public Food getFoodItemByID(int foodID)
	{
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Food WHERE ID = ?");
			ps.setInt(1, foodID);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				Food food = new Food();
				food.Name = rs.getString("Name");
				food.Brand = rs.getString("Brand");
				food.ID = rs.getInt("ID");
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
	public Expiration getExpirationItemByID(int foodID)
	{
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Expiration WHERE FoodID = ?");
			ps.setInt(1, foodID);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				Expiration expiration = new Expiration();
				expiration.FoodID = rs.getInt("FoodID");
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
	public Price getPriceItemByID(int foodID)
	{
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Price WHERE FoodID = ?");
			ps.setInt(1, foodID);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				Price price = new Price();
				price.FoodID = rs.getInt("FoodID");
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
	public ArrayList<Review> getReviewsByID(int foodID)
	{
		ArrayList<Review> reviews = new ArrayList<Review>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Reviews WHERE FoodID = ?");
			ps.setInt(1, foodID);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Review review = new Review();
				review.FoodID = rs.getInt("FoodID");
				review.Rating = rs.getInt("Rating");
				review.Review = rs.getString("Review");
				reviews.add(review);
			} 
		} catch (SQLException e) {
			System.out.println("There was an error in the getReviewsByID method. Error message: " + e.getMessage());
		}
		return reviews;	
	}
	
	public List<Food> getFoodByFuzzyNameMatch(String name)
	{
		// TODO
		return null;
	}
	
	public List<Food> getFoodByFuzzyFoodGroupMatch(String foodGroup)
	{
		// TODO
		return null;
	}
	
	public void getUsersItems(String userID)
	{
		// TODO
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
	public boolean createFoodItem(int id, String name, String brand, String foodGroup)
	{
		try {
			//Create record in the Food table
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Food(ID, Name, Brand, FoodGroup) VALUES (?, ?, ?, ?)");
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, brand);
			ps.setString(4, foodGroup);
			ps.executeUpdate();
			//Create record in the Expiration table
			ps = connection.prepareStatement("INSERT INTO Expiration(FoodID, avgHours, longHours, shortHours, numPoints) VALUES (?, ?, ?, ?, ?)");
			ps.setInt(1, id);
			ps.setDouble(2, 0.0);
			ps.setInt(3, 0);
			ps.setInt(4, 0);
			ps.setInt(5, 0);
			ps.executeUpdate();
			//Create record in the price table
			ps = connection.prepareStatement("INSERT INTO Price(FoodID, avgPrice, biggestPrice, smallestPrice, numPricePoints) VALUES (?, ?, ?, ?, ?)");
			ps.setInt(1, id);
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
	
	public boolean createPricePoint(int foodID, double price)
	{
		if(price <= 0)
		{
			throw new IllegalArgumentException();
		}
		// TODO
		return true;
	}
	
	public boolean createExpirationPoint(int foodID, int hours)
	{
		if(hours <= 0)
		{
			throw new IllegalArgumentException();
		}
		// TODO
		return true;
	}

	public boolean createReview(int FoodID, int rating, String review)
	{
		if(rating <= 0 || rating > 6)
		{
			throw new IllegalArgumentException();
		}
		// TODO
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
		connection.createStatement().executeUpdate("CREATE TABLE Food(ID int NOT NULL, Name varchar(255) NOT NULL, Brand varchar(255), FoodGroup varchar(255), PRIMARY KEY(ID))");
		connection.createStatement().executeUpdate("CREATE TABLE Expiration(FoodID int, avgHours double, longHours int, shortHours int, numPoints int, FOREIGN KEY(FoodID) REFERENCES Food(ID))");
		connection.createStatement().executeUpdate("CREATE TABLE Price(FoodID int, avgPrice double, biggestPrice double, smallestPrice double, numPricePoints int, FOREIGN KEY(FoodID) REFERENCES Food(ID))");
		connection.createStatement().executeUpdate("CREATE TABLE Reviews(FoodID int, Rating int, Review varchar(255), CHECK(Rating > 0 AND Rating < 6))");
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