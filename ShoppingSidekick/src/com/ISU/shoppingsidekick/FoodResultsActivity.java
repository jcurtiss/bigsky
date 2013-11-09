package com.ISU.shoppingsidekick;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ISU.shoppingsidekick.DatabaseAPI.Food;

public class FoodResultsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_results);
		
		
		String scanValue = getIntent().getExtras().getString("scanValue");
		Food scannedFood = new DatabaseAPI().getFoodItemByID(scanValue);
		String name = scannedFood.Name;
		String brand = scannedFood.Brand;
		String id = scannedFood.ID;
		DatabaseAPI.Expiration expirationInfo = scannedFood.ExpirationInformation;
		DatabaseAPI.Price priceInfo = scannedFood.PriceInformation;
		List<DatabaseAPI.Review> reviewInfo = scannedFood.ReviewInformation;
		
		TextView productName = (TextView) findViewById(R.id.productName);
		productName.setText(name);
		
		TextView productBrand = (TextView) findViewById(R.id.productBrand);
		productBrand.setText(brand);
		
		TextView productID = (TextView) findViewById(R.id.productID);
		productBrand.setText(id);
		
		TextView expInformation = (TextView) findViewById(R.id.expInformation);
		productBrand.setText(expirationInfo.avgHours);
		
		TextView priceInformation = (TextView) findViewById(R.id.priceInformation);
		productBrand.setText(priceInfo);
		
		TextView reviewInformation = (TextView) findViewById(R.id.reviewInformation);
		productBrand.setText(reviewInfo);
		
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
