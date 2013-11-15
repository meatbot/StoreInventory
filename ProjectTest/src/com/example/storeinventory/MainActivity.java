package com.example.storeinventory;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	SharedPreferences sharedPrefs = getPreferences(MODE_PRIVATE);
	
	EditText etQuery;
	TextView tvResult;
	Button btnScan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etQuery = (EditText)findViewById(R.id.main_query_et);
		tvResult = (TextView)findViewById(R.id.main_result_tv);
		btnScan = (Button)findViewById(R.id.main_scan_btn);
		
		btnScan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				try {
					Intent intent = new Intent("com.google.zxing.client.android.SCAN");
					intent.putExtra("SCAN_MODE", "ONE_D_MODE");
					startActivityForResult(intent, 0);
				} catch (Exception ex) {
					ex.printStackTrace();
					Toast.makeText(getApplicationContext(), "ERROR: " + ex, 1).show();
				}
			}
		});
		
		etQuery.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (v.getId() == R.id.main_query_et) {
					//conditions under which to run query
					//timer?
					//N number of characters entered?
					//must be asynchronous.
				}
				return false;
			}			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.mainMenu_preferences)
		{
			Intent intent = new Intent(MainActivity.this, PreferencesActivity.class);
			startActivity(intent);
			return true;
		} else {
			return true;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				//Query for results
				etQuery.setText(intent.getStringExtra("SCAN_RESULT"));
			} else if (resultCode == RESULT_CANCELED) {
				etQuery.setText("");
				tvResult.setText("Scan cancelled");
			} 
		}
	}
}
