package com.example.projecttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	TextView tvStatus, tvResult;
	Button btnScan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tvStatus = (TextView)findViewById(R.id.main_status_tv);
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				tvStatus.setText(intent.getStringExtra("SCAN_RESULT_FORMAT"));
				tvResult.setText(intent.getStringExtra("SCAN_RESULT"));
			} else if (resultCode == RESULT_CANCELED) {
				tvStatus.setText(R.string.main_status_default);
				tvResult.setText("Scan cancelled");
			} 
		}
	}
}
