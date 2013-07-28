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
	
	LoginDataBaseAdapter loginDataBaseAdapter;
	String mail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		final SharedPreferences prefs = this.getSharedPreferences("com.example.myflat",Context.MODE_PRIVATE);
		
		loginDataBaseAdapter = new LoginDataBaseAdapter(this);
		loginDataBaseAdapter = loginDataBaseAdapter.open();
		
		mail = prefs.getString("MAIL", "nan");
				
		((TextView) findViewById(R.id.account_firstname)).setText(loginDataBaseAdapter.getFirstname(mail));
		((TextView) findViewById(R.id.account_lastname)).setText(loginDataBaseAdapter.getLastname(mail));
		((TextView) findViewById(R.id.account_mail)).setText(mail);
		((TextView) findViewById(R.id.account_pass)).setText(loginDataBaseAdapter.getPassword(mail));
				
		Button updateButton = (Button) findViewById(R.id.account_action);
		updateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String firstname = ((TextView) findViewById(R.id.account_firstname)).getText().toString();
				String lastname = ((TextView) findViewById(R.id.account_lastname)).getText().toString();
				String newMail = ((TextView) findViewById(R.id.account_mail)).getText().toString();
				String password = ((TextView) findViewById(R.id.account_pass)).getText().toString();
				
				if ((firstname.length() != 0) && (lastname.length() != 0) && (newMail.length() != 0) && (password.length() != 0)) {
					loginDataBaseAdapter.updateEntry(mail, newMail, firstname, lastname, password);
					prefs.edit().putString("MAIL", mail).commit();
					
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
		case R.id.context_account_logout:
			Intent i = new Intent(getApplicationContext(), Login.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(i);
			break;
		case R.id.context_account_dashboard:
			Intent j = new Intent(getApplicationContext(), Dashboard.class);
			startActivity(j);
			break;
		default:
			break;
		}
		
		return true;
	}

}
