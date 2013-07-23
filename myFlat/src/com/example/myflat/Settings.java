package com.example.myflat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends Activity {
	private String firstName = "";
	private String lastName = "";
	private String mail = "";
	private String pass = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		
		
		firstName = ((TextView) findViewById(R.id.account_firstname)).getText().toString();
		lastName = ((TextView) findViewById(R.id.account_lastname)).getText().toString();
		mail = ((TextView) findViewById(R.id.account_mail)).getText().toString();
		pass = ((TextView) findViewById(R.id.account_pass)).getText().toString();
		
		
		if (savedInstanceState != null) {
			TextView tvFirstName = (TextView) findViewById(R.id.account_firstname);
			tvFirstName.setText(savedInstanceState.getString("firstname"));
			firstName = savedInstanceState.getString("firstname");
		}
		
		Button updateButton = (Button) findViewById(R.id.account_action);
		updateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				firstName = ((TextView) findViewById(R.id.account_firstname)).getText().toString();
				lastName = ((TextView) findViewById(R.id.account_lastname)).getText().toString();
				mail = ((TextView) findViewById(R.id.account_mail)).getText().toString();
				pass = ((TextView) findViewById(R.id.account_pass)).getText().toString();
				
				if ((firstName.length() != 0) && (lastName.length() != 0) && (mail.length() != 0) && (pass.length() != 0))
					Toast.makeText(getApplicationContext(), R.string.account_update_confirm, Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(getApplicationContext(), R.string.account_update_error, Toast.LENGTH_SHORT).show();
				
								
			}
		});
		
		Button backButton = (Button) findViewById(R.id.account_back);
		backButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),Dashboard.class);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	
	
	public void onSaveInstanceState(Bundle savedInstanceState){
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putString("firstName",firstName);
		savedInstanceState.putString("lastName",lastName);
		savedInstanceState.putString("mail",mail);
		savedInstanceState.putString("pass",pass);
	}

}
