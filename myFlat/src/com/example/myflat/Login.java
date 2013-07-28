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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	
	LoginDataBaseAdapter loginDataBaseAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		final SharedPreferences prefs = this.getSharedPreferences("com.example.myflat",Context.MODE_PRIVATE);
		
		loginDataBaseAdapter = new LoginDataBaseAdapter(this);
		loginDataBaseAdapter = loginDataBaseAdapter.open();
		
		LinearLayout ll = (LinearLayout) findViewById(R.id.login_register);
		ll.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), Register.class);
        		startActivity(intent);
			}
			
		});
		
		Button loginButton = (Button) findViewById(R.id.login_action);
		loginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String mail = ((TextView) findViewById(R.id.login_mail)).getText().toString();
				String password = ((TextView) findViewById(R.id.login_pass)).getText().toString();
				
				if (mail.length() != 0 && password.length() != 0 && loginDataBaseAdapter.checkPassword(mail, password)) {
					prefs.edit().putString("MAIL", mail).commit();
					Intent intent = new Intent(getApplicationContext(), Dashboard.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
	        		startActivity(intent);
				}
				else
					Toast.makeText(getApplicationContext(), R.string.login_error, Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.context_login:
			Intent i = new Intent(getApplicationContext(), Register.class);
			startActivity(i);
			break;
		default:
			break;
		}
		
		return true;
	}
}
