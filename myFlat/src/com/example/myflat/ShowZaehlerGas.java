package com.example.myflat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.myflat.ShowZaehlerWater.LoadAllZaehlerstaende;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class ShowZaehlerGas extends ListActivity {

	private static final String HOST = new Host().getHost();

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_zaehler_gas);
		new LoadAllZaehlerstaende().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(R.menu.detail_gas, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent i = new Intent(getApplicationContext(), Settings.class);
			startActivity(i);
			break;
		case R.id.actions_logout:
			Intent j = new Intent(getApplicationContext(), Login.class);
			j.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | 
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(j);
			break;
		default:
			break;
		}
		
		return true;
	}
	
	class LoadAllZaehlerstaende extends AsyncTask<Void,Void,DataAdapter>
	{
		Dialog progress;

		@Override
		protected DataAdapter doInBackground(Void... params) 
		{
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet( "http://" + HOST + ":8080/fhws/zaehlers" );
			DataAdapter adapter = new DataAdapter(ShowZaehlerGas.this);
			
			try
			{
				HttpResponse response = client.execute(get);
				InputStream is = response.getEntity().getContent();
				BufferedReader br = new BufferedReader( new InputStreamReader( is ));
				String line = null;
				
				while( (line = br.readLine()) != null )
				{
					if(line.contains("gas"))
					adapter.addData(line);
				}
			}
			catch( Exception e )
			{
				// ignore
			}
			
			return adapter;
		}

		@Override
		protected void onPostExecute(DataAdapter result) {
//	        progress.dismiss();
			ShowZaehlerGas.this.setListAdapter(result);
		}
	}

}
