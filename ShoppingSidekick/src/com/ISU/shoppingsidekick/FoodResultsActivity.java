package com.ISU.shoppingsidekick;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.Database.API.DatabaseAPI;
import com.Database.API.Food;

public class FoodResultsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_results);
		
		//String scanValue = null;
		
		Intent scannerValue = getIntent();
		Bundle scanVal = scannerValue.getExtras();
		TextView productName = (TextView) findViewById(R.id.productName);
		TextView productBrand = (TextView) findViewById(R.id.productBrand);
		TextView productID = (TextView) findViewById(R.id.productID);
		TextView expInformation = (TextView) findViewById(R.id.expInformation);
		TextView priceInformation = (TextView) findViewById(R.id.priceInformation);
		TextView reviewInformation = (TextView) findViewById(R.id.reviewInformation);
		String name = "";
		String brand= "";
		String id ="";
		Food scannedFood = new Food();
		if(scanVal != null){		
			//final String scanValue = scanVal.getString("scanID");
			ExecutorService pool = Executors.newFixedThreadPool(3);
			final String scanValue = "085239311189";
			Callable task = new Callable(){
				@Override
				public Object call() throws Exception{
					DatabaseAPI database = new DatabaseAPI();
					return database.getFoodItemByID(scanValue);
				}
			};
			Future<Food> future = pool.submit(task);
			try {
				scannedFood = future.get();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			Expiration expirationInfo = scannedFood.getExpirationInformation();
//			Price priceInfo = scannedFood.getPriceInformation();
//			List<Review> reviewInfo = scannedFood.getReviewInformation();
			
			name = scannedFood.getName();
			brand = scannedFood.getBrand();
			id = scannedFood.getID();
			productName.setText(name);
			
			productBrand.setText(brand);
			
			productID.setText(id);
			
//			expInformation.setText(expirationInfo.toString());
//			
//			priceInformation.setText(priceInfo.toString());
//			
//			reviewInformation.setText(reviewInfo.toString());
		}
		
		else{
			productName.setText("Item not found");
			productBrand.setVisibility(View.INVISIBLE);
			productID.setVisibility(View.INVISIBLE);
			expInformation.setVisibility(View.INVISIBLE);
			priceInformation.setVisibility(View.INVISIBLE);
			reviewInformation.setVisibility(View.INVISIBLE);
		}	
			
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
