package edu.stanford.cs.gaming.sdk.service;
import edu.stanford.cs.gaming.sdk.model.*;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class GamingServiceConnection implements ServiceConnection  {
    public boolean connected = false;
	private static final String TAG = "GamingServiceConnection";	
	public static GamingRemoteService grs;
	public boolean binded = false;
	private Intent intent; 
	private Activity activity; 
	private BroadcastReceiver receiver;
	private int appId;
	private String appApiKey;
	public GamingServiceConnection(Activity activity, BroadcastReceiver receiver, 
			int appId, String appApiKey) {
		this.activity = activity;
//        this.intent = new Intent(activity, GamingService.class);	
		this.intent = new Intent("edu.stanford.cs.gaming.sdk.service");
        this.receiver = receiver;
        this.appId = appId;
        this.appApiKey = appApiKey;
	}
	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {

//		if (grs == null) {
			Log.d(TAG, "Service connecting");			
			grs = GamingRemoteService.Stub.asInterface(service);
			try {
				grs.addApp(appId, appApiKey);
//				try {
					activity.registerReceiver(receiver, new IntentFilter("edu.stanford.cs.gaming.sdk." + appId + ".Event"));
//				} catch (MalformedMimeTypeException e) {

//					e.printStackTrace();
//				}					
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connected = true;
			Log.d(TAG, "Service connected");
//		}
		
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		Log.d(TAG, "Service disconnectiong");
		
		try {
			grs.removeApp(appId);
			grs=null;	
			connected = false;				
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	public boolean bind() {
		Log.d(TAG, "binded in bind is " + binded);
		if (binded == false) {
			binded = true;
			Log.d(TAG, "bind service starting");
			activity.bindService(intent, this, Context.BIND_AUTO_CREATE);
			Log.d( getClass().getSimpleName(), "bind service complete" );
/*
			Uri.Builder uriBuilder = new Uri.Builder();
			uriBuilder.appendEncodedPath(appName);
			uriBuilder.appendEncodedPath(appApiKey);
			Uri uri = uriBuilder.build();
			*/

	

//			activity.registerReceiver(receiver, new IntentFilter(GamingService.BROADCAST_ACTION));
			
		}
		return true;
	}
	public boolean unbind() {
	   if (binded == true) {
		   binded = false;
		   Log.d(TAG, "Unbinding service");
		   if(connected) {
			   Log.d(TAG, "loggerConn is not null");
			   activity.unbindService(this);
			   activity.unregisterReceiver(receiver);
			   Log.d( getClass().getSimpleName(), "releaseService()");
		   }
	   }
	   return true;

	}
	public boolean testRequest(AppRequest request) {
		Util.toJson(request);
		return true;
	}
	public boolean registerUser(User user) {
	  return true;
	}
	
	public boolean getInvitableFriends() {
		return true;
	}
	
    public boolean getFriends() {
    	return true;
    }
    
}
