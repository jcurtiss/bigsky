package com.Database.API;
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
		private String FoodID;
		/**
		 * The largest price inputed so far
		 */
		private double biggestPrice;
		/**
		 * The average price inputed so far
		 */
		private double avgPrice;
		/**
		 * The smallest price inputed so far
		 */
		private double smallestPrice;
		/**
		 * The number of datapoints this item has
		 */
		private int numPricePoints;
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
		 * @return the biggestPrice
		 */
		public double getBiggestPrice() {
			return biggestPrice;
		}
		/**
		 * @param biggestPrice the biggestPrice to set
		 */
		public void setBiggestPrice(double biggestPrice) {
			this.biggestPrice = biggestPrice;
		}
		/**
		 * @return the avgPrice
		 */
		public double getAvgPrice() {
			return avgPrice;
		}
		/**
		 * @param avgPrice the avgPrice to set
		 */
		public void setAvgPrice(double avgPrice) {
			this.avgPrice = avgPrice;
		}
		/**
		 * @return the smallestPrice
		 */
		public double getSmallestPrice() {
			return smallestPrice;
		}
		/**
		 * @param smallestPrice the smallestPrice to set
		 */
		public void setSmallestPrice(double smallestPrice) {
			this.smallestPrice = smallestPrice;
		}
		/**
		 * @return the numPricePoints
		 */
		public int getNumPricePoints() {
			return numPricePoints;
		}
		/**
		 * @param numPricePoints the numPricePoints to set
		 */
		public void setNumPricePoints(int numPricePoints) {
			this.numPricePoints = numPricePoints;
		}
		
	}