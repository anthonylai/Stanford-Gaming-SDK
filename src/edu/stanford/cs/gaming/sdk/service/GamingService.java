package edu.stanford.cs.gaming.sdk.service;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;
import java.util.*;

import java.io.*;





public class GamingService extends Service implements LocationListener {
	public static final String BROADCAST_ACTION=
		"edu.stanford.cs.gaming.sdk.Event";
	private Intent broadcast=new Intent(BROADCAST_ACTION);	
	private Timer timer = new Timer();
	ArrayList<Intent> intentArr = new ArrayList<Intent>();
	String testString = new String();
	private static final String TAG = "GamingService";	
	LocationManager lm = null;	
	NotificationManager nm;
    private int counter = 0;
    private Location location;
    private int currentMilli = 0;
    private String locationString = "No location yet";
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
//		return LoggerRemoteServiceStub;
		Log.d(TAG, "onBind");
//		intentArr.add(arg0);
        return gamingRemoteServiceStub;
	}

	private GamingRemoteService.Stub gamingRemoteServiceStub = new GamingRemoteService.Stub() {
		public int getCounter() throws RemoteException {
			Log.d("GamingRemoveServiceStub", "onBind()");
//            write("onBind() function called");			
			return counter;
		}
		public String getLocationString() throws RemoteException {
			return locationString;
		}
	};
		
	
	@Override
	public void onCreate() {
		super.onCreate();
//		nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);		
//		testString += "1";
		Log.d(TAG, "onCreate");
		write("onCreate");
//		Toast.makeText(this,"Service created " , Toast.LENGTH_LONG).show();
		
		_startService();
//        determineLocation();

		
		
	}
    public int onStartCommand(Intent intent, int flags, int startId) {
    	Log.d(TAG, "onStartCommand");
 //   	this.intent = intent;
//		Toast.makeText(Intent.,"Service created FIRST...", Toast.LENGTH_LONG).show();
/*
    	FileOutputStream os;
    	testString += "2";
		try {
			os = openFileOutput("sdcard/loggertxt.txt", Context.MODE_APPEND);

    	ObjectOutputStream oos = new ObjectOutputStream(os);
    	oos.writeObject("Testing ASLAI");
    	oos.flush();
    	oos.close();
    	os.close();
    	
		Toast.makeText(this,"Service created SECOND...", Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testString += "3";
    	startService();
    	return 1;
    	*/
    	write("onStartCommand");
    	return 1;
    }
    
	private void _startService() {
		showNotification();
		Log.d(TAG, "Start service started");
	//	write("startService");
		timer.scheduleAtFixedRate( new TimerTask() {
			public void run() {
				counter += 10;
 //               write("Service iteration: " + counter+ 10);
                Log.d(TAG, "COUNTER: " + counter);
    	        sendBroadcast(broadcast);
                
 //               showNotification();
//                for (Intent intent : intentArr) {
//                	intent.
//                }
                
			} 

			}, 0, 5000);

	}
	/*
	private void determineLocation() {
//        Location location = lm.getCurrentLocation("gps");
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000L, 5.0f, this);
        //        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 100, this);

	
	}
	*/
	private void _stopService() {
		Log.d(TAG, "Service stopping");
//        write("stopService");
//        nm.cancel(R.id.StartButton);
        
		if (timer != null){

		timer.cancel();

		}
		Log.d(TAG, "Service stopped");
		}

	@Override
	public void onDestroy() {
		super.onDestroy();
		write("destroy");
		_stopService();
	}
	public void write(String string) {
		write(string, "log.txt");
	}
	
	public void write(String string, String filename) {
		try {			
			BufferedWriter f = new BufferedWriter(new FileWriter("/sdcard/"+filename, true));
			f.write((new Date()).toString() + "\n");
            f.write(string + "\n");
			f.flush();
			f.close();
		} catch (Exception e) {
			testString += e.getMessage();
			e.printStackTrace();
		}	 	
	
	}
	
    private void showNotification() {
    	Log.d("LoggerService", "ShowNotification");
    	/*
    	 CharSequence text = "Service started 1";
    	 Notification notification = new Notification(R.drawable.android, text, System.currentTimeMillis());
    	 PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
    	                new Intent(this, RiZhi.class), 0);
    	notification.setLatestEventInfo(this, "Service started 2",
    	      text, contentIntent);
    	nm.notify(R.id.StartButton, notification);
    	*/
    	    }
	@Override
	public void onLocationChanged(Location location) {
        Log.d(TAG, "Location update");
	    if (location != null) { 
	    	locationString = "" + location.getLatitude() + ", " + location.getLongitude() + ": " + location.getAccuracy() + "\n";
//	    	tv.append("Location is " + location.getLatitude() + ", " + location.getLongitude() + "\n");
//	    	Geocoder geocoder = new Geocoder() 
	    	Geocoder geocoder = new Geocoder(this, Locale.getDefault());
	    	try {
	    	List<Address> addresses = geocoder.getFromLocation(new Double(location.getLatitude()), new Double(location.getLongitude()), 10);   
            Address address = addresses.get(0);
            if (address != null) {
//	        	tv.append("Premise:" + address.getPremises() + "\n");
//	        	tv.append("Address: \n");
	        	locationString += "Address: ";
	        	for (int i=0; i < address.getMaxAddressLineIndex(); i++) {
//	       	      tv.append(address.getAddressLine(i) + "\n");
	        		locationString += address.getAddressLine(i) + ", ";
	        	}
	        	locationString += "\n";
	        }
	    	} catch (Exception e) {
	    	  locationString += "Problem in getting addresses" + e.getMessage() + "\n";
	    	}
	        sendBroadcast(broadcast);
	        Log.d(TAG, "Location broadcasted");

	    	}		
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
    	    
	
}
