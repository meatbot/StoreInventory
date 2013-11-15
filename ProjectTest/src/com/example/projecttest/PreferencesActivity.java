package com.example.projecttest;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.Menu;

//Font size in Item View
//Number of items in incremental search
//Where to store local db - App folder or User's choice on SD card
//		Have checkbox for App folder, when unchecked enable choose folder option.
//Update interval - Daily
//      Would be nice to receive updates of items that have changed on a regular basis.
//TextView to set db url
//At first have it locked. Must open group or check box to enable editing of the field.
//		http://acp.winply.ca/acp/exportinv.php - GZipped ~1/4 of the size

public class PreferencesActivity 
	extends PreferenceActivity
	implements OnSharedPreferenceChangeListener
{
	SharedPreferences sharedPrefs;
	Preference itemFontSize;
	Preference editDbUrlString;
	Preference dbUrlString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		
		sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		sharedPrefs.registerOnSharedPreferenceChangeListener(this);
		
		itemFontSize = findPreference("itemFontSize");
		editDbUrlString = findPreference("editDbUrlString");
		dbUrlString = findPreference("dbUrlString");
	}
	
	@Override
	protected void onResume() {
		super.onResume();		
		
		itemFontSize.setSummary(sharedPrefs.getString("itemFontSize", "12dp").replace("dp", ""));
		dbUrlString.setSummary(sharedPrefs.getString("dbUrlString", getResources().getString(R.string.preferences_dbUrlString)));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.preferences, menu);
		return true;
	}
	
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPrefs, String key) {
		if (key.equals("itemFontSize")) {			
			itemFontSize.setSummary(sharedPrefs.getString(key, "").replace("dp",  ""));
		} else if (key.equals("dbUrlString")) {
			dbUrlString.setSummary(sharedPrefs.getString(key, getResources().getString(R.string.preferences_dbUrlString)));
		}
	}
}
	

