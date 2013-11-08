package com.ISU.shoppingsidekick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        
      //Scan button     
        Button goToScanBtn = (Button) findViewById(R.id.goToScan);
        goToScanBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(HomeActivity.this, ScanActivity.class);
				startActivity(i);
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
        
        //Settings
        Button goToSettingsBtn = (Button) findViewById(R.id.goToSettings);
        goToSettingsBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(HomeActivity.this, SettingsActivity.class);
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
    
}
