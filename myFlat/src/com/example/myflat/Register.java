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

public class Register extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		LinearLayout ll = (LinearLayout) findViewById(R.id.register_login);
		ll.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), Login.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        		startActivity(intent);
			}
			
		});
		
		Button registerButton = (Button) findViewById(R.id.register_action);
		registerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String firstName = ((TextView) findViewById(R.id.register_firstname)).getText().toString();
				String lastName = ((TextView) findViewById(R.id.register_lastname)).getText().toString();
				String mail = ((TextView) findViewById(R.id.register_mail)).getText().toString();
				String pass = ((TextView) findViewById(R.id.register_pass)).getText().toString();
				
				if ((firstName.length() != 0) && (lastName.length() != 0) && (mail.length() != 0) && (pass.length() != 0)) {
					Toast.makeText(getApplicationContext(), R.string.registration_confirm, Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(getApplicationContext(), Login.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
	        		startActivity(intent);
				}
				else
					Toast.makeText(getApplicationContext(), R.string.registration_error, Toast.LENGTH_SHORT).show();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

}
