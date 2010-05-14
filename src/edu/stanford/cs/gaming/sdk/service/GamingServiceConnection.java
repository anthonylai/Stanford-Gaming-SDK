package edu.stanford.cs.gaming.sdk.service;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

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
	private int userId;
	private String intentFilterEvent;
	public GamingServiceConnection(Activity activity, BroadcastReceiver receiver, 
			int appId, String appApiKey, String intentFilterEvent) {
		this.activity = activity;
//        this.intent = new Intent(activity, GamingService.class);	
		this.intent = new Intent("edu.stanford.cs.gaming.sdk.service");
        this.receiver = receiver;
        this.appId = appId;
        this.appApiKey = appApiKey;
        this.intentFilterEvent = intentFilterEvent;
        if (intentFilterEvent == null) {
        	this.intentFilterEvent = "edu.stanford.cs.gaming.sdk." + appId + ".Event";
        } else {
        	this.intentFilterEvent = "edu.stanford.cs.gaming.sdk." + appId + "." + intentFilterEvent;       	
            Log.d(TAG, "Receiver INTENTFILTEREVENT IS "+ this.intentFilterEvent);
        }
	}
	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {

//		if (grs == null) {
			Log.d(TAG, "Service connecting");			
			grs = GamingRemoteService.Stub.asInterface(service);
			try {
				grs.addApp(appId, appApiKey);
//				try {
					activity.registerReceiver(receiver, new IntentFilter(intentFilterEvent));

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
	/* aslai Don't think this is needed
	public boolean hasPendingNotifications() {
		return true;
//		return grs.hasPendingNotification(appId);
	}
	*/
	public AppResponse getNextPendingNotification() throws RemoteException, JSONException {
		String response = grs.getNextPendingNotification(appId);
		if (response != null)
		  return (AppResponse) Util.fromJson(new JSONObject(response), null, null);
		return null;
	}
	public boolean testRequest(AppRequest request) {
		Util.toJson(request);
		return true;
	}
	public void setUserId(int userId)  {
		this.userId = userId;
	}
	public boolean registerUser(int request_id, User user) throws RemoteException {
	  return true;
	}
	
	public boolean getInvitableFriends(int request_id) throws RemoteException {
		return true;
	}
	
    public boolean getFriends(int request_id) throws RemoteException {
    	
    	return true;
    }
    public boolean createGroup(int request_id, Group group) throws RemoteException {
    	AppRequest appRequest = new AppRequest(request_id, appId, appApiKey, userId, intentFilterEvent);
    	appRequest.action = "post";
    	appRequest.model = "Group";
    	appRequest.path = "/groups/";
    	appRequest.object = group;
    	group.app_id = appId;
    	Log.d(TAG, "GROUP IN JSON IS: " + Util.toJson(appRequest));
    	grs.sendRequest(appId, Util.toJson(appRequest).toString());  	
    	return true;    	
    }
    public boolean getGroups(int request_id, String name, int user_id,
    		int limit, int rowsRet) 
    throws RemoteException {
    	AppRequest appRequest = new AppRequest(request_id, appId, appApiKey, userId, intentFilterEvent);
    	appRequest.action = "get";
    	appRequest.model = "Group";    	
    	appRequest.path = "/groups";
    	ArrayList<Criterion> criteriaList = new ArrayList<Criterion>();
    	if (name != null) criteriaList.add(new Criterion("name", name));
    	if (user_id != -1) criteriaList.add(new Criterion("user_id", ""+user_id));
    	if (limit != -1) criteriaList.add(new Criterion("limit", ""+limit));
    	if (rowsRet != -1) criteriaList.add(new Criterion("rowsRet", ""+rowsRet));
    	int count = 0;
    	if ((count = criteriaList.size()) > 0)
    		appRequest.criteria = new Criterion[count];        
    	for (int i = 0; i < count; i++) {
    		appRequest.criteria[i] = criteriaList.remove(0);
    	}
    	grs.sendRequest(appId, Util.toJson(appRequest).toString());  	
    	return true;
    }
    public boolean getGroup(int request_id, int group_id) 
    throws RemoteException {
    	AppRequest appRequest = new AppRequest(request_id, appId, appApiKey, userId, intentFilterEvent);
    	appRequest.action = "get";
    	appRequest.model = "Group";
    	appRequest.path = "/groups/" + group_id;
    	grs.sendRequest(appId, Util.toJson(appRequest).toString());  	
    	return true;
    }    
    public boolean deleteGroup(int request_id, int group_id) throws RemoteException {
    	AppRequest appRequest = new AppRequest(request_id, appId, appApiKey, userId, intentFilterEvent);
    	appRequest.action = "delete";
    	appRequest.model = "Group";
    	appRequest.path = "/groups/" + group_id;
    	grs.sendRequest(appId, Util.toJson(appRequest).toString());  	
    	return true;
    }
    
}
