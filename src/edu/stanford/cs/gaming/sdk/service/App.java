package edu.stanford.cs.gaming.sdk.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import edu.stanford.cs.gaming.sdk.model.*;

public class App {
  public String appName;
  public Intent intent;
  public List<AppResponse> responseQ;

  public App(String appName) {
	  this.appName = appName;
	  this.intent = new Intent("edu.stanford.cs.gaming.sdk." + appName + ".Event");
	  this.responseQ = new ArrayList<AppResponse>();
  }
}
