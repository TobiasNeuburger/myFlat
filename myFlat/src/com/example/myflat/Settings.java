package com.example.myflat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		final SharedPreferences prefs = this.getSharedPreferences("com.example.myflat",Context.MODE_PRIVATE);
		
		String firstName = prefs.getString("firstName", "John");
		String lastName = prefs.getString("lastName", "Doe");
		String mail = prefs.getString("mail", "john.doe@student.fhws.de");
		String pass = prefs.getString("pass", "JohnDoe123");
		
		((TextView) findViewById(R.id.account_firstname)).setText(firstName);
		((TextView) findViewById(R.id.account_lastname)).setText(lastName);
		((TextView) findViewById(R.id.account_mail)).setText(mail);
		((TextView) findViewById(R.id.account_pass)).setText(pass);
				
		Button updateButton = (Button) findViewById(R.id.account_action);
		updateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String firstName = ((TextView) findViewById(R.id.account_firstname)).getText().toString();
				String lastName = ((TextView) findViewById(R.id.account_lastname)).getText().toString();
				String mail = ((TextView) findViewById(R.id.account_mail)).getText().toString();
				String pass = ((TextView) findViewById(R.id.account_pass)).getText().toString();
				
				if ((firstName.length() != 0) && (lastName.length() != 0) && (mail.length() != 0) && (pass.length() != 0)) {
					
					prefs.edit().putString("firstName", firstName).putString("lastName", lastName).putString("mail", mail).putString("pass", pass).commit();
					
					Toast.makeText(getApplicationContext(), R.string.account_update_confirm, Toast.LENGTH_SHORT).show();					
					Intent intent = new Intent (Settings.this, Dashboard.class);
					startActivity (intent);
				}
				else
					Toast.makeText(getApplicationContext(), R.string.account_update_error, Toast.LENGTH_SHORT).show();

			}
		});
		
		Button backButton = (Button) findViewById(R.id.account_back);
		backButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),Dashboard.class);
				startActivity (intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.context_account:
			Intent i = new Intent(getApplicationContext(), Dashboard.class);
			startActivity(i);
			break;
		default:
			break;
		}
		
		return true;
	}

}
