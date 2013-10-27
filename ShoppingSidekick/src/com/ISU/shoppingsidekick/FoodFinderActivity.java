package com.ISU.shoppingsidekick;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ISU.shoppingsidekick.DatabaseAPI.Food;

/**
 * Food Finder page that allows the user to search for a particular food item
 * or food group in our database
 * @author Jeremy Curtiss
 *
 */

public class FoodFinderActivity extends Activity {

	EditText searchField;
	ListView resultsList;
	Button searchBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_finder);
		
		resultsList = (ListView) findViewById(R.id.searchResultsList);
		
		//Search button and search field
        searchBtn = (Button) findViewById(R.id.searchButton);
        searchBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				populateResultsList(getSearchResults(getSearchFieldText()));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.food_finder, menu);
		return true;
	}

	/**
	 * Gets the text entered in the searchField
	 * @return String of text from searchField
	 */
	private String getSearchFieldText(){
		searchField = (EditText)findViewById(R.id.searchField);
		return searchField.getText().toString();
	}
	
	/**
	 * Searches database for food entries matching the searchText
	 * @param searchText the text input from the searchField
	 * @return list of food items from database based off of our search text
	 */
	private List<Food> getSearchResults(String searchText){
		DatabaseAPI d = new DatabaseAPI();
		List<Food> setFromName = d.getFoodByFuzzyNameMatch(searchText);
		List<Food> setFromGroup = d.getFoodByFuzzyFoodGroupMatch(searchText);
		List<Food> toReturn = setFromName;
		for(int i = 0; i < setFromGroup.size(); i++){
			if(!toReturn.contains(setFromGroup.get(i)))
				toReturn.add((Food) setFromGroup.get(i));
		}
		return toReturn;
	}
	
	private void populateResultsList(List<Food> results){
		// TODO
	}
}
