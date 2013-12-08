package com.ISU.shoppingsidekick;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends Activity {

	private Intent i;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        
      //Scan button     
        Button goToScanBtn = (Button) findViewById(R.id.scan_button);
        goToScanBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//i = new Intent();
				notOnClick();
				//startActivity(i);
			}
		});
        
        //Food Finder button
        Button goToFoodFinderBtn = (Button) findViewById(R.id.goToFoodFinder);
        goToFoodFinderBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(HomeActivity.this, FoodFinderActivity.class);
				startActivity(i);
			}
		});
        
        //Recent Activity
        Button goToRecentActivityBtn = (Button) findViewById(R.id.goToRecentActivity);
        goToRecentActivityBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(HomeActivity.this, RecentActionsActivity.class);
				startActivity(i);
			}
		});
        
        //Recipes button
        Button goToRecipesBtn = (Button) findViewById(R.id.goToRecipes);
        goToRecipesBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(HomeActivity.this, RecipesActivity.class);
				startActivity(i);
			}
		});
        
        //Calendar button
        Button goToCalendarBtn = (Button) findViewById(R.id.goToCalendar);
        goToCalendarBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(HomeActivity.this, CalendarActivity.class);
				startActivity(i);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    
    public void notOnClick() {
    	IntentIntegrator scanIntegrator = new IntentIntegrator(this);
		scanIntegrator.initiateScan();
	}
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
			if (scanningResult != null) {
					String scanContent = scanningResult.getContents();
					//String scanFormat = scanningResult.getFormatName();
										
					Intent i = new Intent(HomeActivity.this, FoodResultsActivity.class);
					i.putExtra("scanID",scanContent);
					startActivity(i);
					}
			else{
				 	Toast toast = Toast.makeText(getApplicationContext(),
				        "No scan data received!", Toast.LENGTH_SHORT);
				    toast.show();
				}
	}
    
}
