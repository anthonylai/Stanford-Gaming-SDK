package edu.stanford.cs.gaming.sdk.service;

interface GamingRemoteService {

	int getCounter();
	
	String getLocationString();
	
	boolean addApp(String appName, String appApiKey);
	
	void doGet(String url);
	
	void putGameObject(String gameObjJsonStr);
	
	String getNextCompletedTask();
	
}
