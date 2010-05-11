package edu.stanford.cs.gaming.sdk.service;

import java.util.ArrayList;
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

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import edu.stanford.cs.gaming.sdk.model.*;

import java.io.*;





public class GamingService extends Service implements LocationListener {
//	public static final String BROADCAST_ACTION=
//		"edu.stanford.cs.gaming.sdk.Event";
//	private Intent broadcast=new Intent(BROADCAST_ACTION);	
	public static String gamingServer = "http://98.210.19.161:3000";
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
    private Hashtable<Integer, App> appHash = new Hashtable<Integer, App>();
    private List<String> taskList = new ArrayList<String>();
    private List<String> completedTaskList = new ArrayList<String>();
    public static List<AppRequest> requestQ;
    private GamingService gamingService = this;

    @Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
//		return LoggerRemoteServiceStub;
		Log.d(TAG, "onBind");
//		intentArr.add(arg0);
        return gamingRemoteServiceStub;
	}

	private GamingRemoteService.Stub gamingRemoteServiceStub = new GamingRemoteService.Stub() {
        public boolean addApp(int appId, String appApiKey) throws RemoteException {
    		Log.d("GamingRemoteServiceStub", "addApp()");
        	if (appHash.get(appId) == null) {
        		appHash.put(appId, new App(appId, gamingService));

        	}
        	return true;
        	
        }

    	public boolean sendRequest(int appId, String request) throws RemoteException {
    		Log.d("GamingRemoteServiceStub", "sendRequest(), appId: " + appId + ", request: " + request);
    		App app = appHash.get(appId);
    		try {
    			AppRequest appRequest;
					appRequest = (AppRequest) Util.fromJson(new JSONObject(request), null, null);

				app.requestQ.put((AppRequest) appRequest);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		return true;
    	}
        
        public boolean removeApp(int appId) throws RemoteException {
        	Log.d("GamingRemoveServiceStub", "removeApp()");
        	if (appHash.get(appId) == null) {
        		App app = appHash.remove(appId);
        		app.stopService();
        	}
        	return true;
        }
		public int getCounter() throws RemoteException {
			Log.d("GamingRemoteServiceStub", "onBind()");
//            write("onBind() function called");			
			return counter;
		}
		public String getLocationString() throws RemoteException {
			return locationString;
		}

		public void doGet(String url) throws RemoteException {
			taskList.add(url);
			return;
		}
		public String getNextCompletedTask(int appId) throws RemoteException {
			if (!appHash.get(appId).responseQ.isEmpty()) {
				Object object = null;
				try {
					object = appHash.get(appId).responseQ.take();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
//				Object object = appHash.get(appId).responseQ.remove(0);
				Log.d("GamingRemoteServiceStub", "nextTask returned is: \n" + object);
				return Util.toJson(object).toString();
			}
			return null;
		}
		public void putGameObject(String gameObjJsonStr) throws RemoteException {
//			JSONObject gameObjJson;
//			try {
				Log.d("GamingRemoteServiceStub", "Putting JSON Object");
//				gameObjJson = new JSONObject(gameObjJsonStr);
//				taskList.add(gameObjJson.getString("name"));		
				taskList.add(gameObjJsonStr);
				Log.d("GamingRemoteServiceStub", "Finished putting JSON Object");
				
//			} catch (JSONException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

			return;
		}
		
		public boolean hasPendingNotification(int appId) throws RemoteException {
			return !appHash.get(appId).responseQ.isEmpty();
		}

		public String getNextPendingNotification(int appId) throws RemoteException {
			if (!appHash.get(appId).responseQ.isEmpty()) {
				Object object = null;
				try {
					object = appHash.get(appId).responseQ.take();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
//				Object object = appHash.get(appId).responseQ.remove(0);
				Log.d("GamingRemoteServiceStub", "nextTask returned is: \n" + object);
				return Util.toJson(object).toString();
			}
			return null;
		} 		
	};
	public GamingService() {
		/*
		if (requestQ == null) {
			requestQ = new ArrayList<AppRequest>();
		}
*/
		
	}
	@Override
	public void onCreate() {
		super.onCreate();
//		nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);		
//		testString += "1";
		Log.d(TAG, "onCreate");
//		write("onCreate");
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
                /*
                GameObject go = new GameObject("Testing");
                JSONObject jo = (JSONObject) Util.toJson(go);
                Log.d(TAG, "TOJSON IS: " + jo);
                Log.d(TAG, "------------");
                Log.d(TAG, "Before FROMJSON");
                try {
                Log.d(TAG, "FROMJSON IS: " + Util.fromJson(jo, null, null));
                } catch (Exception e) {
                	e.printStackTrace();
                }
                Log.d(TAG, "After FROMJSON");
                */

                //   	        sendBroadcast(broadcast);
    	    	for (int appId : appHash.keySet()) {
                    Log.d(TAG, "123 Broadcasting to : " + appId);
                    while (taskList.size() > 0) {
                    Log.d(TAG, "HERE 1");
                    String name = taskList.remove(0);
                    Log.d(TAG, "HERE 2");
                    
                    completedTaskList.add(name);
                    Log.d(TAG, "HERE 3");
                   
                    }
                    Log.d(TAG, "HERE 4");
                    
//                    makeGet("http://www.stanford.edu");
    		        sendBroadcast(appHash.get(appId).intent);
    		    	}                
 //               showNotification();
//                for (Intent intent : intentArr) {
//                	intent.
//                }
                
			} 

			}, 0, 30000);

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
//		write("destroy");
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
	    	for (int appId : appHash.keySet()) {
//	        sendBroadcast(broadcast, appId);
	    	}
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
