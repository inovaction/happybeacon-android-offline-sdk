package com.happybeacon.sample;

import java.util.HashSet;

import org.json.JSONException;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import com.happybeacon.HBBeacon;
import com.happybeacon.HBScanCallback;

public class HappyApplication extends Application implements HBScanCallback {
	protected static final String TAG = "HappySample";
	
	protected static void showAlert(Context context, String message) {
		new AlertDialog.Builder(context)
		.setTitle(context.getString(R.string.app_name))
		.setMessage(message)
		.setPositiveButton("Happy", null).create().show();
	}
	
	protected static void showNotification(Context context, String message) {
		NotificationManager mNM = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification.Builder builder = new Notification.Builder(context);
		
        long[] pattern = {500, 500, 500, 500, 500, 500, 500, 500, 500};
		builder.setSmallIcon(R.drawable.ic_launcher)
			.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher))
			.setTicker(context.getString(R.string.app_name))
			.setWhen(System.currentTimeMillis())
			.setAutoCancel(true)
			.setLights(Color.BLUE, 500, 500)
			.setVibrate(pattern)
			.setContentTitle(context.getString(R.string.app_name))
			.setContentText(message);
		Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		if (alarmSound != null) {
			builder.setSound(alarmSound);
		}
		
		Notification notification = builder.build();
		mNM.notify(0, notification); // Send notification
	}

	@Override
	public void beaconsInRange(HashSet<HBBeacon> beacons) {
		Log.d(TAG, "Beacons in range " + beacons.toString()); 
	}

	@Override
	public void didEnterRangeOfBeacon(HBBeacon beacon) {
		Log.d(TAG, "didEnterRangeOfBeacon " + beacon.toString());
		if (beacon.getData() == null) {
			return;
		} 
		try {
			showNotification(this, beacon.getData().getString("message_enter"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void didExitRangeOfBeacon(HBBeacon beacon) {
		Log.d(TAG, "didExitRangeOfBeacon " + beacon.toString()); 
		if (beacon.getData() == null) {
			return;
		}
		try {
			showNotification(this, beacon.getData().getString("message_exit"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
