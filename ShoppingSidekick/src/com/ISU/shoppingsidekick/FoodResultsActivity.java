package com.ISU.shoppingsidekick;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class FoodResultsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_results);
		
		//confirmation button     
        Button goToScanBtn = (Button) findViewById(R.id.resultsToConfirmation);
        goToScanBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(FoodResultsActivity.this, FoodConfirmationActivity.class);
				startActivity(i);
			}
		});
        
        //home button
        Button goToFoodFinderBtn = (Button) findViewById(R.id.resultsToHome);
        goToFoodFinderBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(FoodResultsActivity.this, HomeActivity.class);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.food_results, menu);
		return true;
	}

}
