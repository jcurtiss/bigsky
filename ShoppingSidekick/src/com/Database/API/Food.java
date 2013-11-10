package com.Database.API;
import java.util.List;

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
		private String ID;
		
		/**
		 * Name of the food
		 */
		private String Name;
		
		/**
		 * Brand of the food
		 */
		private String Brand;
		
		/**
		 * FoodGroup the food belongs to
		 */
		private String FoodGroup;
		
		/**
		 * Expiration information of the food. This is a saved as an Expiration class object
		 */
		private Expiration ExpirationInformation;
		
		/**
		 * Price information of the food. This is a saved as an Price class object
		 */
		private Price PriceInformation;
		
		/**
		 * Review information of the food. This is a saved as a list of Review class objects
		 */
		private List<Review> ReviewInformation;

		@Override
		public boolean equals(Object obj){
			boolean result = false;
			if(obj instanceof Food){
				Food f = (Food) obj;
				if(f.getID().equals(this.getID()))
					result = true;
			}
			return result;
		}

		
		public int hashCode(){
			return getID().hashCode();
		}
		/**
		 * @return the iD
		 */
		public String getID() {
			return ID;
		}

		/**
		 * @param iD the iD to set
		 */
		public void setID(String iD) {
			ID = iD;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return Name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			Name = name;
		}

		/**
		 * @return the brand
		 */
		public String getBrand() {
			return Brand;
		}

		/**
		 * @param brand the brand to set
		 */
		public void setBrand(String brand) {
			Brand = brand;
		}

		/**
		 * @return the foodGroup
		 */
		public String getFoodGroup() {
			return FoodGroup;
		}

		/**
		 * @param foodGroup the foodGroup to set
		 */
		public void setFoodGroup(String foodGroup) {
			FoodGroup = foodGroup;
		}

		/**
		 * @return the expirationInformation
		 */
		public Expiration getExpirationInformation() {
			return ExpirationInformation;
		}

		/**
		 * @param expirationInformation the expirationInformation to set
		 */
		public void setExpirationInformation(Expiration expirationInformation) {
			ExpirationInformation = expirationInformation;
		}

		/**
		 * @return the priceInformation
		 */
		public Price getPriceInformation() {
			return PriceInformation;
		}

		/**
		 * @param priceInformation the priceInformation to set
		 */
		public void setPriceInformation(Price priceInformation) {
			PriceInformation = priceInformation;
		}

		/**
		 * @return the reviewInformation
		 */
		public List<Review> getReviewInformation() {
			return ReviewInformation;
		}

		/**
		 * @param reviewInformation the reviewInformation to set
		 */
		public void setReviewInformation(List<Review> reviewInformation) {
			ReviewInformation = reviewInformation;
		}
	}
