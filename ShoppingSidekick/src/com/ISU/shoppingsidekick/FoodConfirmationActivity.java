package com.ISU.shoppingsidekick;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class FoodConfirmationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_confirmation);
		
		//food finder button     
        Button goToFinderBtn = (Button) findViewById(R.id.confirmToFinder);
        goToFinderBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(FoodConfirmationActivity.this, FoodResultsActivity.class);
				startActivity(i);
			}
		});
        
        Button goToHomeBtn = (Button) findViewById(R.id.confirmToHome);
        goToHomeBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(FoodConfirmationActivity.this, FoodResultsActivity.class);
				startActivity(i);
			}
		});
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.food_confirmation, menu);
		return true;
	}

}
