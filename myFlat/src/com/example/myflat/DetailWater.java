package com.example.myflat;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetailWater extends Activity {

	private static final String HOST = new Host().getHost();
	private static int userDay = 0;
	private static int userMonth = 0;
	private static int userYear = 0;
	private static boolean userDateSet = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_water);
		
		Button update = (Button) findViewById(R.id.detail_water_update);
		update.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent( DetailWater.this, ShowZaehlerWater.class );
				startActivity(intent);
			}
		});
		
		TextView date = (TextView) findViewById(R.id.detail_date);
		date.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				DialogFragment newFragment = new DatePickerFragment();
				newFragment.show(getFragmentManager(), "datePicker");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
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
		case R.id.show_list:
			Intent k = new Intent(getApplicationContext(), ShowZaehlerWater.class);
			startActivity(k);
			break;
		default:
			break;
		}
		
		return true;
	}
	
	public void saveZaehler( View view )
	{
		TextView dateEdit = (TextView)findViewById(R.id.detail_date);
		EditText zaehlerstandEdit = (EditText)findViewById(R.id.detail_stand);
		
		new SaveZaehler().execute(dateEdit.getText().toString(), zaehlerstandEdit.getText().toString() );
		//Log.d("Inhalte: ", dateEdit.getText().toString() + ";" + zaehlerstandEdit.getText().toString());
	}
	
	class SaveZaehler extends AsyncTask<String, Void, Boolean>
	{
		
		@Override
		protected Boolean doInBackground(String... params) 
		{
			String date = params[0];
			String zaehlerStand = params[1];
			String zaehlerart = "water";
			
			HttpClient httpClient = new DefaultHttpClient();
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("date", date ));
			nameValuePairs.add(new BasicNameValuePair("zaehlerStand", zaehlerStand ));
			nameValuePairs.add(new BasicNameValuePair("zaehlerart", zaehlerart));

			try
			{
				HttpPost post = new HttpPost("http://" + HOST + ":8080/fhws/zaehlers" );
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
			
			//Intent intent = new Intent( Detail.this, ShowZaehlerWater.class );
			//startActivity(intent);
		}
	}
	
	@SuppressLint("NewApi")
	public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
		
		@SuppressLint("NewApi")
		public Dialog onCreateDialog(Bundle savedInstanceState) {
		    // Use the current date as the default date in the picker
		    final Calendar c = Calendar.getInstance();
		    int year = c.get(Calendar.YEAR);
		    int month = c.get(Calendar.MONTH);
		    int day = c.get(Calendar.DAY_OF_MONTH);
		    
		    if (userDateSet)
		    	return new DatePickerDialog(getActivity(), this, userYear, userMonth - 1, userDay);
		    
		    return new DatePickerDialog(getActivity(), this, year, month, day);
		}
		
		@SuppressLint("NewApi")
		public void onDateSet(DatePicker view, int year, int month, int day) {
			userDateSet = true;
			userDay = day;
			userMonth = month + 1;
			userYear = year;
						
			TextView dateEdit = (TextView) getActivity().findViewById(R.id.detail_date);
			
			dateEdit.setText(userDay + "." + userMonth + "." + userYear);
		}
	}

}


