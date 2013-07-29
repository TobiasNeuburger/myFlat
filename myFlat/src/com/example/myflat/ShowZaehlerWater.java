package com.example.myflat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class ShowZaehlerWater extends ListActivity {

		private static final String HOST = new Host().getHost();

		@Override
		protected void onCreate(Bundle savedInstanceState) 
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_show_zaehler_water);
			new LoadAllZaehlerstaende().execute();
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) 
		{
			getMenuInflater().inflate(R.menu.show_zaehler_water, menu);
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
			@Override
			protected DataAdapter doInBackground(Void... params) 
			{
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet( "http://" + HOST + ":8080/fhws/zaehlers" );
				DataAdapter adapter = new DataAdapter(ShowZaehlerWater.this);
				
				try
				{
					HttpResponse response = client.execute(get);
					InputStream is = response.getEntity().getContent();
					BufferedReader br = new BufferedReader( new InputStreamReader( is ));
					String line = null;
					
					while( (line = br.readLine()) != null )
					{
						if(line.contains("water"))
						adapter.addData(line);
					}
				}
				catch( Exception e )
				{
					e.printStackTrace();
				}
				
				return adapter;
			}

			@Override
			protected void onPostExecute(DataAdapter result) {
				ShowZaehlerWater.this.setListAdapter(result);
			}
		}

}
