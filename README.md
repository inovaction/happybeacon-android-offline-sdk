HappyBeacon
Android Offline Library

Eclipse integration
------------------------------------------------------------------------------------------

1) Import the HappyBeacon Android Project Library in Eclipse

2) Use Project Settings -> Android to reference HappyBeacon in your Android project

3) Create the hbdata.json file with same structure as the one provided in the HappySample project

4) Call the methods for foreground & background use in your main activity

```java
@Override
protected void onResume() {
    super.onResume();
    HB.getInstance(this).go(this);
}

@Override
protected void onPause() {
    super.onPause();
    HB.getInstance(this).gone(this);
}
```

5) Add required Android permissions

```java
<uses-permission android:name="android.permission.BLUETOOTH"/>
<uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
```

6) Declare the HBService in your AndroidManifest file

```java
<service android:name="com.happybeacon.HBService" android:exported="false" >
    <intent-filter>
        <action android:name="com.happybeacon.HBService" />
    </intent-filter>
</service>
```

7) Implement the HBScanCallback in your main activity for custom UI behavior

```android
public void beaconsInRange(HashSet<HBBeacon> beacons);
public void didEnterRangeOfBeacon(HBBeacon beacon);
public void didExitRangeOfBeacon(HBBeacon beacon);
```

Sample
------------------------------------------------------------------------------------------

See attached HappySample project which handles entering/exiting by showing a simple alert.

FAQ
------------------------------------------------------------------------------------------

Question 1 : Is there a delay for the detection when the application is in background ?

Answer : When the application is in background you can set the delay using backgroundDelay static variable of HB.

Question 2 : Is there a way to start automatically the bluetooth option ?

Answer : You can start the bluetooth service by using autoEnableBluetooth static variable of HB.
