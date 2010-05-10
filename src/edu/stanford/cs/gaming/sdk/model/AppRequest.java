package edu.stanford.cs.gaming.sdk.model;

public class AppRequest {
	public int request_id;
	public String app_name;
	public String app_api_key;
	public String action;
	public Object object;
	public Criterion[] criteria;
	
	public AppRequest(String app_name, String app_api_key) {
		this.app_name = app_name;
		this.app_api_key = app_api_key;
	}

}
