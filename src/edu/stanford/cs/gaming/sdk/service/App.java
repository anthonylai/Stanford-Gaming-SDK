package edu.stanford.cs.gaming.sdk.service;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import android.content.Intent;
import android.util.Log;
import edu.stanford.cs.gaming.sdk.model.*;

public class App {
  public int appId;
  public Intent intent;
  public BlockingQueue<AppRequest> requestQ; 
  public BlockingQueue<AppResponse> responseQ;   
  public Thread processThread;
  public GamingService gamingService;
  public String tag;


  public App(int appId, GamingService gamingService) {
	  this.appId = appId;
	  this.intent = new Intent("edu.stanford.cs.gaming.sdk." + appId + ".Event");
      this.requestQ = new LinkedBlockingQueue<AppRequest>(); 
      this.responseQ = new LinkedBlockingQueue<AppResponse>(); 
      this.gamingService = gamingService;
      tag = "App " + appId + "thread";
      processThread = new ProcessThread();
      processThread.start();
      
      
  }
  
  public void stopService() {
	  processThread.interrupt();
  }
  
  class ProcessThread extends Thread {
	  public void run() {
		  try {
			  while (true) {
				  Log.d(tag, "Request waiting on new request");
				  
				  AppRequest request = requestQ.take();
				  Log.d(tag, "Request received is: " + request);
//				  sleep(2000);
				  AppResponse response = new AppResponse();
				  response.request_id = request.id;
				  response.object = Util.makeGet("http://www.stanford.edu");
//				  response.object = request;
				  responseQ.put(response);
  		          gamingService.sendBroadcast(intent);
				  
			  }
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
  }
}
