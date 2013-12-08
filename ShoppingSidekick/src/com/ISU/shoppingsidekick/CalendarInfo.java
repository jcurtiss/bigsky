package com.ISU.shoppingsidekick;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.Database.API.Account;
import com.Database.API.CalendarItem;
import com.Database.API.DatabaseAPI;
import com.Database.API.Expiration;
import com.Database.API.Food;
import com.Database.API.Price;
import com.Database.API.Review;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.TextView;

public class CalendarInfo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar_info);
		
		TextView foodName = (TextView) findViewById(R.id.foodName);
		TextView dateStarted = (TextView) findViewById(R.id.dateAdded);
		TextView dateExpired = (TextView) findViewById(R.id.dateExpires);
		final ArrayList<CalendarItem> itemsList;
		final ArrayList<Food> foodItems = null;
		
		String name = "";
		String dStart= "";
		String dExp ="";		
		ExecutorService pool = Executors.newFixedThreadPool(3);
		Callable task = new Callable(){
			@Override
			public Object call() throws Exception{
				DatabaseAPI db = new DatabaseAPI();
				Account account = db.getAccountInfoByUserID("Daotoo");
				itemsList = (ArrayList<CalendarItem>) db.getUsersItems(account.getUserID());
				for(int i = 0; i < itemsList.size(); i++){
					foodItems.add(itemsList.get(i).getFood());
				}
			}
		};
		Future<Food> future = pool.submit(task);
		Food food = foodItems.get(1);
		try {
			food = future.get();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(food.getID() != null){
			Expiration expirationInfo = food.getExpirationInformation();
			
			name = food.getName();
			//dStart = expirationInfo.getAvgHours();
			dExp = food.getID();
			
			foodName.setText(name);
			
			dateStarted.setText("Expiration Date" + " " + expirationInfo.getShortHours());
			
			dateExpired.setText("Expiration Date" + " " + expirationInfo.getAvgHours());
			
		}
	
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calendar_info, menu);
		return true;
	}
	
	//Override the onKeyDown method  
    @Override  
    public boolean onKeyDown(int keyCode, KeyEvent event)  
    {  
        //replaces the default 'Back' button action  
        if(keyCode==KeyEvent.KEYCODE_BACK)  
        {  
            //Go back to calendar activity when back button is pressed  
            this.startActivity(new Intent(CalendarInfo.this, CalendarActivity.class));  
        }  
        return true;  
    }  

}
