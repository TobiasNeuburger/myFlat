package com.example.myflat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Dialog;
import android.app.ListActivity;
import android.view.Menu;


public class ShowZaehlerWater extends ListActivity {

		private static final String HOST_HOME = "192.168.1.110";
		private static final String HOST_BIB = "10.32.11.142";

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
			getMenuInflater().inflate(R.menu.detail, menu);
			return true;
		}
		
		class LoadAllZaehlerstaende extends AsyncTask<Void,Void,DataAdapter>
		{
			Dialog progress;

		    @Override
		    protected void onPreExecute() {
//		        progress = ProgressDialog.show(ShowPersons.this, 
//		                "Loading data", "Please wait...");
		        super.onPreExecute();
		    }

			@Override
			protected DataAdapter doInBackground(Void... params) 
			{
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet( "http://" + HOST_BIB + ":8080/fhws/zaehlers" );
				DataAdapter adapter = new DataAdapter(ShowZaehlerWater.this);
				
				try
				{
					HttpResponse response = client.execute(get);
					InputStream is = response.getEntity().getContent();
					BufferedReader br = new BufferedReader( new InputStreamReader( is ));
					String line = null;
					
					while( (line = br.readLine()) != null )
					{
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
//		        progress.dismiss();
				ShowZaehlerWater.this.setListAdapter(result);
			}
		}

}
