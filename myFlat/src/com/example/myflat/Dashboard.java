package com.example.myflat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Dashboard extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		
		//zum Testen
		LinearLayout ll_water = (LinearLayout) findViewById(R.id.title_activity_show_zaehler_water);
		ll_water.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), DetailWater.class);
        		startActivity(i);
			}
		});
		
		
		LinearLayout ll_electro = (LinearLayout) findViewById(R.id.title_activity_show_zaehler_electro);
		ll_electro.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), DetailElectro.class);
        		startActivity(i);
			}
		});
		
		LinearLayout ll_gas = (LinearLayout) findViewById(R.id.title_activity_show_zaehler_gas);
		ll_gas.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), DetailGas.class);
        		startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboard, menu);
		return true;
	}

}
