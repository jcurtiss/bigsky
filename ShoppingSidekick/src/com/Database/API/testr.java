package com.Database.API;
import java.util.ArrayList;
import java.util.List;


public class testr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		//085239311189
		DatabaseAPI api = new DatabaseAPI();
		Account account = api.getAccountInfoByUserID("SeanC");
		Food food = api.getFoodItemByID("085239311189");
		api.addFoodItemToUserTable(account.getUserID(), food);
		List<CalendarItem> items = api.getUsersItems(account.getUserID());
		System.out.println(items.get(0).getFood().getName());
//		api.dropTables();
//		api.createTables();
//		api.createFoodItem("085239311189", "Bean with bacon soup", "Hy-vee", "Can");
//		api.createFoodItem("085239311188", "Bean soup", "Hy-vee", "Can");
//		Food food = api.getFoodItemByID("085239311189");
//		System.out.println(food.getName());
//		List<Food> foods = api.getFoodByFuzzyNameMatch("Bean");
//		System.out.println(foods.get(0).getName());
//		System.out.println(foods.get(1).getName());
//		List<Food> foods2 = api.getFoodByFuzzyFoodGroupMatch("Can");
//		System.out.println(foods2.get(0).getName());
//		System.out.println(foods2.get(1).getName());
//		System.out.println();
//		
//		ArrayList<String> arraylist = new ArrayList<String>();
//		arraylist.add("085239311189");
//		arraylist.add("085239311188");
//		Boolean bool = api.createRecipe("Cheese and Crackers", arraylist, "www.cheeseandcrackers.com");
//		System.out.println(bool);
//		Recipe recipe = api.getRecipeByName("Cheese and Crackers");
//		System.out.println(recipe.getItemsID());
//		System.out.println(recipe.getName());
//		ArrayList<Recipe> recipes = api.getRecipesByFoodID("085239311189");
//		System.out.println(recipes.get(0).getItemsID());
//		System.out.println(recipes.get(0).getName());
		api.disconnect();		
	}
}
