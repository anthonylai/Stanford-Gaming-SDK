package edu.stanford.cs.gaming.sdk;

import edu.stanford.cs.gaming.sdk.service.*;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.widget.TextView;

public class GamingTestActivity extends Activity {
	
	private static final String TAG = "GamingTestActivity";	
    private boolean binded = false;
	Intent loggerIntent = null;
	private GamingServiceConnection gameConn;
	private GameReceiver receiver;
	private TextView tv;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        receiver = new GameReceiver();
        setContentView(R.layout.main); 
        tv = (TextView) findViewById(R.id.TextView01);
        gameConn = new GamingServiceConnection(this, receiver);
        gameConn.bind();
        
      
    }
    

	    class GameReceiver extends BroadcastReceiver {
			public void onReceive(Context context, Intent intent) {
				Log.d(TAG, "onReceiver in GameReceiver");			
				try {
					Log.d(TAG, "GameReceiver Counter is: " + gameConn.grs.getCounter());
					tv.append("Counter is: "+ gameConn.grs.getCounter() + "\n");
//					tv.append("Location is: " + logger.getLocationString() + "\n");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}           	
	    }			
		
    
}