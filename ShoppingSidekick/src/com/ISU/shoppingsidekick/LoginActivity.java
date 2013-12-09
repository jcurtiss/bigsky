package com.ISU.shoppingsidekick;

import com.Database.API.DatabaseAPI;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		//Scan button     
        Button goToHomeBtn = (Button) findViewById(R.id.LoginButton);
        goToHomeBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final String username = ((EditText) findViewById(R.id.LoginTextUsername)).getText().toString();
				final String password = ((EditText) findViewById(R.id.LoginTextPassword)).getText().toString();
		        Thread thread=new Thread(){
		            @Override
		            public void run(){
		            	hideMessage();
						if(new DatabaseAPI().checkUserLogin(username, password))
						{
							Intent i = new Intent(LoginActivity.this, HomeActivity.class);
							startActivity(i);
						}
						else
						{
							showMessage();
						}
		            }
		        };
		        thread.start();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void showMessage()
	{
		Handler handler = new Handler(Looper.getMainLooper());
		handler.post(new Runnable(){

			@Override
			public void run() {
				final TextView errorMessage = ((TextView) findViewById(R.id.loginErrorMessage));
				errorMessage.setVisibility(View.VISIBLE);
			}
		});
	}
	
	public void hideMessage()
	{
		Handler handler = new Handler(Looper.getMainLooper());
		handler.post(new Runnable(){

			@Override
			public void run() {
				final TextView errorMessage = ((TextView) findViewById(R.id.loginErrorMessage));
				errorMessage.setVisibility(View.GONE);
			}
		});
	}

}
