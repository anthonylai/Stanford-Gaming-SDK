package edu.stanford.cs.gaming.sdk.service;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

public class GamingServiceConnection implements ServiceConnection  {
    public boolean connected = false;
	private static final String TAG = "GamingServiceConnection";	
	public static GamingRemoteService grs;
	public boolean binded = false;
	private Intent intent; 
	private Activity activity; 
	private BroadcastReceiver receiver;
	public GamingServiceConnection(Activity activity, BroadcastReceiver receiver) {
		this.activity = activity;
        this.intent = new Intent(activity, GamingService.class);	
        this.receiver = receiver;
	}
	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {

//		if (grs == null) {
			Log.d(TAG, "Service connecting");			
			grs = GamingRemoteService.Stub.asInterface(service);
			connected = true;
			Log.d(TAG, "Service connected");
//		}
		
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		Log.d(TAG, "Service disconnectiong");
		grs=null;	
		connected = false;		
		
	}
	public boolean bind() {
		Log.d(TAG, "binded in bind is " + binded);
		if (binded == false) {
			binded = true;
			Log.d(TAG, "bind service starting");
			activity.bindService(intent, this, Context.BIND_AUTO_CREATE);
			Log.d( getClass().getSimpleName(), "bind service complete" );
			activity.registerReceiver(receiver, new IntentFilter(GamingService.BROADCAST_ACTION));			
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

}
