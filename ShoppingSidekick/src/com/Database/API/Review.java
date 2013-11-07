package com.Database.API;
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
		private String FoodID;
		
		/**
		 * Rating (1-5) of the food
		 */
		private int Rating;
		
		/**
		 * Review of the food
		 */
		private String Review;
		
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
		 * @return the rating
		 */
		public int getRating() {
			return Rating;
		}
		
		/**
		 * @param rating the rating to set
		 */
		public void setRating(int rating) {
			Rating = rating;
		}
		
		/**
		 * @return the review
		 */
		public String getReview() {
			return Review;
		}
		
		/**
		 * @param review the review to set
		 */
		public void setReview(String review) {
			Review = review;
		}
	}