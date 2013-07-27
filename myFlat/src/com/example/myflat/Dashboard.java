package com.example.myflat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class Dashboard extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		
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
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.context_dashboard_settings:
			Intent i = new Intent(getApplicationContext(), Settings.class);
			startActivity(i);
			break;
		case R.id.context_dashboard_logout:
			Intent j = new Intent(getApplicationContext(), Login.class);
			j.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(j);
			break;
		case R.id.context_dashboard_gas:
			Intent k = new Intent(getApplicationContext(), DetailGas.class);
			startActivity(k);
			break;
		case R.id.context_dashboard_electric:
			Intent l = new Intent(getApplicationContext(), DetailElectro.class);
			startActivity(l);
			break;
		case R.id.context_dashboard_water:
			Intent m = new Intent(getApplicationContext(), DetailWater.class);
			startActivity(m);
			break;

		default:
			break;
		}
		
		return true;
	}

}
