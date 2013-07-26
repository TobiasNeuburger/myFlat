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
import android.util.Log;
import android.view.Menu;

public class ShowZaehlerElectro extends ListActivity {

	private static final String HOST = new Host().getHost();

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_zaehler_electro);
		new LoadAllZaehlerstaende().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(R.menu.detail_electro, menu);
		return true;
	}
	
	class LoadAllZaehlerstaende extends AsyncTask<Void,Void,DataAdapter>
	{
		Dialog progress;

	    @Override
	    protected void onPreExecute() {
//	        progress = ProgressDialog.show(ShowPersons.this, 
//	                "Loading data", "Please wait...");
	        super.onPreExecute();
	    }

		@Override
		protected DataAdapter doInBackground(Void... params) 
		{
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet( "http://" + HOST + ":8080/fhws/zaehlers" );
			DataAdapter adapter = new DataAdapter(ShowZaehlerElectro.this);
			
			try
			{
				HttpResponse response = client.execute(get);
				InputStream is = response.getEntity().getContent();
				BufferedReader br = new BufferedReader( new InputStreamReader( is ));
				String line = null;
				
				while( (line = br.readLine()) != null)
				{
					if(line.contains("electric"))
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
			ShowZaehlerElectro.this.setListAdapter(result);
		}
	}

}
