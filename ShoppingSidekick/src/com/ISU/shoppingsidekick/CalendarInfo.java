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
		
		final ArrayList<CalendarItem> itemsList;
		final ArrayList<Food> foodItems = new ArrayList<Food>();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar_info);
		
		Intent calDate = getIntent();
		Bundle date = null;
		date = calDate.getExtras();
		
		TextView foodName = (TextView) findViewById(R.id.foodName);
		TextView dateStarted = (TextView) findViewById(R.id.dateAdded);
		TextView dateExpired = (TextView) findViewById(R.id.dateExpires);
		String name = "";
		double dStart= 0;
		String dExp ="";	
		final String dateClicked = date.getString("date");
		ExecutorService pool = Executors.newFixedThreadPool(3);
		Callable task = new Callable(){
			@Override
			public Object call() throws Exception{
				DatabaseAPI db = new DatabaseAPI();
				Account account = db.getAccountInfoByUserID("Daotoo");
				ArrayList<CalendarItem> calItems;
				calItems = (ArrayList<CalendarItem>) db.getUsersItems(account.getUserID());
				for(int i = 0; i < calItems.size(); i++){
					if(calItems.get(i).getDateExpired().toString() == dateClicked)
					foodItems.add(calItems.get(i).getFood());
				}
				return foodItems;
			}
		};
		Future<ArrayList<Food>> future = pool.submit(task);
		ArrayList<Food> fI = foodItems;
		
		try {
			fI = future.get();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < fI.size(); i++){
		if(fI.get(i).getID() != null){
			Expiration expirationInfo = fI.get(i).getExpirationInformation();
			
			name = fI.get(i).getName();
			
			foodName.setText(name);
			
			dateStarted.setText("Expiration Date" + " " + expirationInfo.getShortHours());
			
			dateExpired.setText("Expiration Date" + " " + expirationInfo.getAvgHours());
			
		}
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
