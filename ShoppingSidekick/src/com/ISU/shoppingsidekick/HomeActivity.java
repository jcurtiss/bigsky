package com.ISU.shoppingsidekick;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.Database.API.Account;
import com.Database.API.CalendarItem;
import com.Database.API.DatabaseAPI;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
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
        
    	final Account a = (Account) getIntent().getExtras().get("account");
                
	     // Gets an instance of the NotificationManager service
	     final NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
     
     Thread thread = new Thread(){
         @Override
         public void run(){
				DatabaseAPI db = new DatabaseAPI();
				List<CalendarItem> items = db.getUsersItems(a.getUserID());
				for(int i = 0; i < items.size(); i++)
				{
					String content = "";
					CalendarItem item = items.get(i);
					Date dateStarted = item.getDateStarted();
					Date dateExpired = item.getDateExpired();
					Date currentDate = new Date();
					double dateExpiredTime = dateExpired.getTime();
					double dateStartedTime = dateStarted.getTime();
					double currentDateTime = currentDate.getTime();
					double time2 = dateExpiredTime - dateStartedTime;
					double time3 = dateExpiredTime - currentDateTime;
					double time4 = (double) (time3 / time2);
					double time5 = (time4) * ((double) 100.0);
					if(dateExpired.before(currentDate))
					{
						content = item.getFood().getName() + " has expired.";
						db.removeCalendarItem(a.getUserID(), item.getID());
					}
					else if(time5 <= 10.0)
					{
						content = item.getFood().getName() + " will expire soon."; 
					}
					else
					{
						continue;
					}
			        NotificationCompat.Builder mBuilder =
			        	    new NotificationCompat.Builder(HomeActivity.this)
			        	    .setSmallIcon(R.drawable.icon)
			        	    .setContentTitle("Shopping Sidekick Expiration")
			        	    .setContentText(content);
			        mNotifyMgr.notify(i, mBuilder.build());
				}
         }
     };
     thread.start();
     
        
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
				i.putExtra("account", a);
				startActivity(i);
			}
		});
        
        //Recent Activity
        Button goToRecentActivityBtn = (Button) findViewById(R.id.goToRecentActivity);
        goToRecentActivityBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(HomeActivity.this, RecentActionsActivity.class);
				i.putExtra("account", a);
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
				i.putExtra("account", a);
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
