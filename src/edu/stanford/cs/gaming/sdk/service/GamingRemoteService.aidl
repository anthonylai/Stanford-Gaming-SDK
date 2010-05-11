package edu.stanford.cs.gaming.sdk.service;

interface GamingRemoteService {

	int getCounter();
	
	String getLocationString();
	
	boolean addApp(int appId, String appApiKey);
	
	void doGet(String url);
	
	void putGameObject(String gameObjJsonStr);
	
	String getNextCompletedTask(int appId);
	
    boolean removeApp(int appId);	
	
	boolean sendRequest(int appId, String request);
	
	boolean hasPendingNotification(int appId);

	String getNextPendingNotification(int appId); 

}
