package com.example.myflat;


import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Detail extends Activity {

	private static final String HOST = "backend.applab.fhws.de";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}
	
	public void saveZaehlerWater( View view )
	{
		EditText dateEdit = (EditText)findViewById(R.id.detail_date);
		EditText zaehlerstandEdit = (EditText)findViewById(R.id.detail_stand);
		
		new SaveZaehlerWater().execute(dateEdit.getText().toString(), zaehlerstandEdit.getText().toString() );
		
	}
	
	class SaveZaehlerWater extends AsyncTask<String, Void, Boolean>
	{
		
		@Override
		protected Boolean doInBackground(String... params) 
		{
			String date = params[0];
			String zaehlerStand = params[1];
			
			HttpClient httpClient = new DefaultHttpClient();
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("date", date ));
			nameValuePairs.add(new BasicNameValuePair("zaehlerStand", zaehlerStand ));

			try
			{
				HttpPost post = new HttpPost("http://" + HOST + ":8080/fhws/persons" );
				post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpClient.execute(post);
				int status = response.getStatusLine().getStatusCode();
				return status == HttpStatus.SC_CREATED;
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
			
			return false;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			String text = "";
			
			if( result == true )
			{
				text = "Data saved";
			}
			else
			{
				text = "Data not saved";
			}
			
			Toast.makeText(getApplication(), text, Toast.LENGTH_SHORT).show();
			
			Intent intent = new Intent( Detail.this, ShowZaehlerWater.class );
			startActivity(intent);
		}
	}

}


