package com.Database.API;
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
		private String FoodID;
		
		/**
		 * The largest amount of hours inputed so far
		 */
		private int longHours;
		
		/**
		 * The smallest amount of hours inputed so far
		 */
		private int shortHours;
		
		/**
		 * The average expiration time
		 */
		private double avgHours;
		
		/**
		 * The number of datapoints this item has
		 */
		private int numPoints;
		
		/**
		 * @return the foodID
		 */
		public String getFoodID() {
			return FoodID;
		}
		
		/**
		 * @param foodID the foodID to set
		 */
		public void setFoodID(String foodID) {
			FoodID = foodID;
		}
		
		/**
		 * @return the longHours
		 */
		public int getLongHours() {
			return longHours;
		}
		
		/**
		 * @param longHours the longHours to set
		 */
		public void setLongHours(int longHours) {
			this.longHours = longHours;
		}
		
		/**
		 * @return the shortHours
		 */
		public int getShortHours() {
			return shortHours;
		}
		
		/**
		 * @param shortHours the shortHours to set
		 */
		public void setShortHours(int shortHours) {
			this.shortHours = shortHours;
		}
		
		/**
		 * @return the avgHours
		 */
		public double getAvgHours() {
			return avgHours;
		}
		
		/**
		 * @param avgHours the avgHours to set
		 */
		public void setAvgHours(double avgHours) {
			this.avgHours = avgHours;
		}
		
		/**
		 * @return the numPoints
		 */
		public int getNumPoints() {
			return numPoints;
		}
		
		/**
		 * @param numPoints the numPoints to set
		 */
		public void setNumPoints(int numPoints) {
			this.numPoints = numPoints;
		}
	}