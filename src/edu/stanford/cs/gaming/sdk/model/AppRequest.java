package edu.stanford.cs.gaming.sdk.model;

public class AppRequest {
	public int id;
	public int app_id;
	public String app_api_key;
	public int user_id;
	public String action;
	public String model;
	public String path;
	public Object object;
	public Criterion[] criteria;
	public String intentFilterEvent;
	public Object reference;
	public String token;
	public long fb_id;
	public AppRequest() {
		
	}
	public AppRequest(int id, int app_id, String app_api_key, int user_id, long fb_id, String token, String intentFilterEvent) {
		this.id = id;
		this.app_id = app_id;
		this.app_api_key = app_api_key;
		this.user_id = user_id;
		this.fb_id = fb_id;
		this.token = token;
		this.intentFilterEvent = intentFilterEvent;
	}
	
	public String toString() {
		String str = "";
		str = "\n===============\n";
		str += "Request id: " + id + "\n";
		str += "App id: " + app_id + "\n";
		str += "App api key: " + app_api_key + "\n";
		str += "Action: " + action + "\n";
		str += "Path: " + path + "\n";		
		str += "Object: " + object + "\n";
		str += "================\n";
		return str;
		
	}

}
