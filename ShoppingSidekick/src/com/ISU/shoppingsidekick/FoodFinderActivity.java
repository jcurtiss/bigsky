package com.ISU.shoppingsidekick;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.Database.API.DatabaseAPI;
import com.Database.API.Food;

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
	ArrayList<Food> searchResults;
	ArrayAdapter<String> adapter;
	ArrayList<String> listItems;
	String st;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_finder);
		listItems = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
		resultsList = (ListView) findViewById(R.id.searchResultsList);
		resultsList.setAdapter(adapter);
		
		//Search button and search field
        searchBtn = (Button) findViewById(R.id.searchButton);
        searchBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				st = getSearchFieldText();
				ExecutorService pool = Executors.newFixedThreadPool(3);
				final String st = getSearchFieldText();
		        Callable task = new Callable(){
					@Override
					public Object call() throws Exception {
						return getSearchResults(st);
					}
					
					private ArrayList<Food> getSearchResults(String searchText){
						DatabaseAPI d = new DatabaseAPI();
						ArrayList<Food> setFromName = d.getFoodByFuzzyNameMatch(searchText);
						ArrayList<Food> setFromGroup = d.getFoodByFuzzyFoodGroupMatch(searchText);
						
						return removeDuplicates(setFromName, setFromGroup);
					}
					
					private ArrayList<Food> removeDuplicates(ArrayList<Food> arr1, ArrayList<Food> arr2){
						arr1.addAll(arr2);
						HashSet hs = new HashSet();
						hs.addAll(arr1);
						arr1.clear();
						arr1.addAll(hs);
						return arr1;
					}
		        };
		        Future<ArrayList<Food>> future = pool.submit(task);
		        try {
					searchResults = future.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
		        if(searchResults.size() > 0)
		        	populateResultsList(searchResults);
		        else{
		        	listItems.clear();
		        	listItems.add("No search results found");	
		        }
		        adapter.notifyDataSetChanged();
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
		st = "";
		searchField = (EditText)findViewById(R.id.searchField);
		if(searchField.length() > 0)
			return searchField.getText().toString();
		else
			st = "";
		return st;
	}

	public void populateResultsList(ArrayList<Food> results){
		listItems.clear();
		for(int i = 0; i < results.size(); i++){
			listItems.add(results.get(i).getName());
		}
	}
}
