package com.ISU.shoppingsidekick;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class FoodResultsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_results);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.food_results, menu);
		return true;
	}

}
