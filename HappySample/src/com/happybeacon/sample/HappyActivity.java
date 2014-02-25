package com.happybeacon.sample;

import java.util.HashSet;

import org.json.JSONException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.happybeacon.HB;
import com.happybeacon.HBBeacon;
import com.happybeacon.HBScanCallback;

public class HappyActivity extends Activity implements HBScanCallback {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_happy);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		HB.showLogs = true; // Show logs
		HB.autoEnableBluetooth = true; // Auto enable bluetooth if disabled
		HB.getInstance(this).go(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		HB.getInstance(this).gone(getApplication()); // Use application class for background
	}

	@Override
	public void beaconsInRange(HashSet<HBBeacon> beacons) {
		Log.d(HappyApplication.TAG, "Beacons in range " + beacons.toString()); 
	}

	@Override
	public void didEnterRangeOfBeacon(HBBeacon beacon) {
		Log.d(HappyApplication.TAG, "didEnterRangeOfBeacon " + beacon.toString());  
		if (beacon.getData() == null) {
			return;
		}
		try {
			HappyApplication.showAlert(this, beacon.getData().getString("message_enter"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void didExitRangeOfBeacon(HBBeacon beacon) {
		Log.d(HappyApplication.TAG, "didExitRangeOfBeacon " + beacon.toString()); 
		if (beacon.getData() == null) {
			return;
		}
		try {
			HappyApplication.showAlert(this, beacon.getData().getString("message_exit"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
