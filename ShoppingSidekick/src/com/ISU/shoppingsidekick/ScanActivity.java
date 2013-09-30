package com.ISU.shoppingsidekick;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class ScanActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);
		
		//home button     
        Button goToHomeBtn = (Button) findViewById(R.id.scanToHome);
        goToHomeBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ScanActivity.this, HomeActivity.class);
				startActivity(i);
			}
		});
        
        //Food results button
        Button goToFoodResultsBtn = (Button) findViewById(R.id.scanToResults);
        goToFoodResultsBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ScanActivity.this, FoodResultsActivity.class);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scan, menu);
		return true;
	}

}
