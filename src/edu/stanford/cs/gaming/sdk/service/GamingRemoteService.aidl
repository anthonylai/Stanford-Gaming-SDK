package edu.stanford.cs.gaming.sdk.service;

interface GamingRemoteService {

	int getCounter();
	
	String getLocationString();
	
	boolean addApp(int appId, String appApiKey, String intentFilterEvent);
	
	boolean setUserId(int appId, int userId);
	
	void doGet(String url);
	
//	void putGameObject(String gameObjJsonStr);
	
//	String getNextCompletedTask(int appId);
	
//    boolean removeApp(int appId);	
	
	boolean sendRequest(int appId, String request);
	
	boolean hasPendingNotification(int appId, String intentFilterEvent);

	String getNextPendingNotification(int appId, String intentFilterEvent); 
	
	int getLastConciergeId();

}
