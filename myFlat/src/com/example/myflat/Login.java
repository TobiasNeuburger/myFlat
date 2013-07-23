package com.example.myflat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		LinearLayout ll = (LinearLayout) findViewById(R.id.login_register);
		ll.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), Register.class);
        		startActivity(i);
			}
			
		});
		
		Button loginButton = (Button) findViewById(R.id.login_action);
		loginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String mail = ((TextView) findViewById(R.id.login_mail)).getText().toString();
				String pass = ((TextView) findViewById(R.id.login_pass)).getText().toString();
				
				if ((mail.length() != 0) && (pass.length() != 0)) {
					Intent i = new Intent(getApplicationContext(), Dashboard.class);
	        		startActivity(i);
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

}
