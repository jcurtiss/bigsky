package com.ISU.shoppingsidekick;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class FoodFinderActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_finder);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.food_finder, menu);
		return true;
	}

}
