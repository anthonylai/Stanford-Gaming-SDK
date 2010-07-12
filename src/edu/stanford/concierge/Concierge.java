package edu.stanford.concierge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * This is a class to communicate with the Concierge platform.
 * <br><br>
 * You can download it here <a href="http://code.google.com/p/stanfordconcierge/downloads/list">http://code.google.com/p/stanfordconcierge/downloads/list</a>
 * <br><br>
 * It makes use of the JSON.simple class that can be found here
 * <a href="http://code.google.com/p/json-simple/">http://code.google.com/p/json-simple/</a>
 * <br><br>
 * The static methods are methods that do not require a name/key pair. All of the other methods require
 * a valid instance of Concierge to ensure that the name/key provided is valid.
 */

public class Concierge
{
	private String _name, _key, _conciergeURL;
	private int _principalID;
	

	/**
	 * Register a new principal
	 * 
	 * @param conciergeURL	the url of the concierge server
	 * @param name			the name of the principal
	 * @param key			the secret key for the principal
	 * @param callback		an optional url for callbacks
	 * @return				the id of the newly registered principal, or -1 if it fails
	 * @throws IOException 
	 */
	public static int registerPrincipal(String conciergeURL, String name, String key, String callback) throws IOException
	{
		int retVal = -1;

			URL url = new URL(conciergeURL);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			
			String data = URLEncoder.encode("do", "UTF-8") + "=" + URLEncoder.encode("registerPrincipal", "UTF-8");
		    data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
		    data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(key, "UTF-8");
		    data += "&" + URLEncoder.encode("callback", "UTF-8") + "=" + URLEncoder.encode(callback, "UTF-8");
			
		    String response = sendRequest(conciergeURL, data);
		    
		    retVal = Integer.parseInt(response);

		return retVal;
	}	
	
	/**
	 * Unregister a principal
	 * @param conciergeURL	the url of the concierge server
	 * @param name			the name of the principal
	 * @param key			the secret key for the principal
	 * @return				true of successful
	 * @throws IOException 
	 */
	public static boolean unregisterPrincipal(String conciergeURL, String name, String key) throws IOException
	{
		boolean retVal = false;

			URL url = new URL(conciergeURL);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			
			String data = URLEncoder.encode("do", "UTF-8") + "=" + URLEncoder.encode("unregisterPrincipal", "UTF-8");
		    data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
		    data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(key, "UTF-8");
			
		    String response = sendRequest(conciergeURL, data);
		    
		    retVal = Boolean.parseBoolean(response);

		return retVal;
	}
	
	/**
	 * Get the stream for a specific principal
	 * @param conciergeURL	the url of the concierge server
	 * @param name			the name of the principal
	 * @param since			id of the last message received
	 * @param limit			maximum number of messages to return
	 * @return				an array of all the messages in the stream
	 * @throws IOException 
	 * @throws JSONException 
	 */
	public static JSONArray getPrincipalStream(String conciergeURL, String name, String since, String limit) throws IOException, JSONException
	{
		JSONArray retVal = new JSONArray();

			URL url = new URL(conciergeURL);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			
			String data = URLEncoder.encode("do", "UTF-8") + "=" + URLEncoder.encode("getPrincipalStream", "UTF-8");
		    data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
		    data += "&" + URLEncoder.encode("since", "UTF-8") + "=" + URLEncoder.encode(since, "UTF-8");
		    data += "&" + URLEncoder.encode("limit", "UTF-8") + "=" + URLEncoder.encode(limit, "UTF-8");
			
		    String response = sendRequest(conciergeURL, data);
		    retVal = new JSONArray(response);
//		    Object obj = JSONValue.parse(response);
//		    retVal = (JSONArray) obj;
		    

		
		return retVal;
	}
	
	/**
	 * Get the global Stream
	 * @param conciergeURL	the url of the concierge server
	 * @param since			id of the last message received
	 * @param limit			maximum number of messages to return
	 * @return				an array of all the messages in the stream
	 * @throws IOException 
	 * @throws JSONException 
	 */
	public static JSONArray getGlobalStream(String conciergeURL, String since, String limit) throws IOException, JSONException
	{
		JSONArray retVal = new JSONArray();

			URL url = new URL(conciergeURL);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			
			String data = URLEncoder.encode("do", "UTF-8") + "=" + URLEncoder.encode("getGlobalStream", "UTF-8");
		    data += "&" + URLEncoder.encode("since", "UTF-8") + "=" + URLEncoder.encode(since, "UTF-8");
		    data += "&" + URLEncoder.encode("limit", "UTF-8") + "=" + URLEncoder.encode(limit, "UTF-8");
			
		    String response = sendRequest(conciergeURL, data);
		    retVal = new JSONArray(response);
//		    Object obj = JSONValue.parse(response);
//		    retVal = (JSONArray) obj;


		return retVal;
	}
	
	/**
	 * Get the stream for a specific set of tags
	 * @param conciergeURL	the url of the concierge server
	 * @param tags			an array of the tags
	 * @param since			id of the last message received
	 * @param limit			maximum number of messages to return
	 * @return				an array of all the messages in the stream
	 */
	public static JSONArray getTaglistStream(String conciergeURL, String[] tags, String since, String limit) throws IOException, JSONException
	{
		JSONArray retVal = new JSONArray();

			URL url = new URL(conciergeURL);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			
			String data = URLEncoder.encode("do", "UTF-8") + "=" + URLEncoder.encode("getTaglistStream", "UTF-8");
		    data += "&" + URLEncoder.encode("tags", "UTF-8") + "=" + URLEncoder.encode(arrayToString(tags), "UTF-8");
		    data += "&" + URLEncoder.encode("since", "UTF-8") + "=" + URLEncoder.encode(since, "UTF-8");
		    data += "&" + URLEncoder.encode("limit", "UTF-8") + "=" + URLEncoder.encode(limit, "UTF-8");
			
		    String response = sendRequest(conciergeURL, data);
		    retVal = new JSONArray(response);
//		    Object obj = JSONValue.parse(response);
//		    retVal = (JSONArray) obj;
		    

		return retVal;
	}
	
	/**
	 * Send a request to the server
	 * @param conciergeURL	the url of the concierge server
	 * @param data			the data payload for the server
	 * @return				the response from the server
	 * @throws IOException 
	 */
	private static String sendRequest(String conciergeURL, String data) throws IOException
	{	
		
		String line = "";

			URL url = new URL(conciergeURL);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			
		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data);
		    wr.flush();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    line = rd.readLine();
		    wr.close();
		    rd.close();

		
		return line;
		    
	}
	

	/**
	 * Constructor for the Concierge class, also authenticates the name/key
	 * @param conciergeURL	the url of the concierge server
	 * @param name			the name of the principal
	 * @param key			the secret key for the principal
	 * @throws Exception	if the name/key did not authenticate
	 */
	public Concierge(String conciergeURL, String name, String key) throws Exception
	{	

			String data = URLEncoder.encode("do", "UTF-8") + "=" + URLEncoder.encode("authenticatePrincipal", "UTF-8");
		    data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
		    data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(key, "UTF-8");
			
		    String response = sendRequest(conciergeURL, data);
		    
		    if(response.equalsIgnoreCase("false"))
		    {
		    	throw new Exception("Invalid name/key.");
		    }
		    else
		    {
		    	this._conciergeURL = conciergeURL;
		    	this._name = name;
		    	this._key = key;
		    	this._principalID = Integer.parseInt(response);
		    }

	}
	


	/**
	 * Register an interest on a set of tags
	 * @param tags			an array of tags
	 * @return				true if it was successful
	 * @throws IOException 
	 */
	public boolean registerTagInterest(String[] tags) throws IOException
	{	
		boolean retVal = false;

			String data = URLEncoder.encode("do", "UTF-8") + "=" + URLEncoder.encode("registerTagInterest", "UTF-8");
		    data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(_name, "UTF-8");
		    data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(_key, "UTF-8");
		    data += "&" + URLEncoder.encode("tags", "UTF-8") + "=" + URLEncoder.encode(arrayToString(tags), "UTF-8");
			
		    String response = sendRequest(_conciergeURL, data);
		    
		    retVal = Boolean.parseBoolean(response);

		
		return retVal;
	}

	/**
	 * Unregister an interest on a set of tags
	 * @param tags			an array of tags
	 * @return				true if it was successful
	 * @throws IOException 
	 */
	public boolean unregisterTagInterest(String[] tags) throws IOException
	{	
		boolean retVal = false;

			String data = URLEncoder.encode("do", "UTF-8") + "=" + URLEncoder.encode("unregisterTagInterest", "UTF-8");
		    data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(_name, "UTF-8");
		    data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(_key, "UTF-8");
		    data += "&" + URLEncoder.encode("tags", "UTF-8") + "=" + URLEncoder.encode(arrayToString(tags), "UTF-8");
			
		    String response = sendRequest(_conciergeURL, data);
		    
		    retVal = Boolean.parseBoolean(response);

		return retVal;
	}

	/**
	 * Unregister all tag interests
	 * @return				true if it was successful
	 * @throws IOException 
	 */
	public boolean unregisterAllTagInterests() throws IOException
	{	
		boolean retVal = false;

			String data = URLEncoder.encode("do", "UTF-8") + "=" + URLEncoder.encode("unregisterAllTagInterests", "UTF-8");
		    data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(_name, "UTF-8");
		    data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(_key, "UTF-8");
			
		    String response = sendRequest(_conciergeURL, data);
		    
		    retVal = Boolean.parseBoolean(response);

		
		return retVal;
	}

	/**
	 * Gets all of your tag interests
	 * @return				an array of string arrays containing the tags
	 * @throws IOException 
	 */
	public String[][] getTagInterests() throws IOException
	{	
		String[][] retVal = new String[0][0];

			String data = URLEncoder.encode("do", "UTF-8") + "=" + URLEncoder.encode("getTagInterests", "UTF-8");
		    data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(_name, "UTF-8");
		    data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(_key, "UTF-8");
			
		    String response = sendRequest(_conciergeURL, data);
		    response = response.replace("[[", "");
		    response = response.replace("]]", "");
		    
		    String[] tagSets = response.split("(\\],\\[)");
		    
		    retVal = new String[tagSets.length][];
		    for(int i = 0; i < tagSets.length; i++)
		    {
		    	String[] tagSet = tagSets[i].split("(\\\",\\\")|(\\\")");
		    	retVal[i] = new String[tagSet.length];
		    	for(int j = 0; j < tagSet.length; j++)
		    	{
		    		retVal[i][j] = tagSet[j];
		    	}
		    }
		    //retVal = Boolean.parseBoolean(response);

		
		return retVal;
	}
	
	/**
	 * Register an interest in the global stream
	 * @return				true if it was successful
	 * @throws IOException 
	 */
	public boolean registerGlobalInterest() throws IOException
	{	
		boolean retVal = false;

			String data = URLEncoder.encode("do", "UTF-8") + "=" + URLEncoder.encode("registerGlobalInterest", "UTF-8");
		    data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(_name, "UTF-8");
		    data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(_key, "UTF-8");
			
		    String response = sendRequest(_conciergeURL, data);
		    
		    retVal = Boolean.parseBoolean(response);

		
		return retVal;
	}
	
	/**
	 * Unregister an interest in the global stream
	 * @return				true if it was successful
	 * @throws IOException 
	 */
	public boolean unregisterGlobalInterest() throws IOException
	{	
		boolean retVal = false;

			String data = URLEncoder.encode("do", "UTF-8") + "=" + URLEncoder.encode("unregisterGlobalInterest", "UTF-8");
		    data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(_name, "UTF-8");
		    data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(_key, "UTF-8");
			
		    String response = sendRequest(_conciergeURL, data);
		    
		    retVal = Boolean.parseBoolean(response);

		return retVal;
	}
	
	/**
	 * Register an interest in a specific Principal
	 * @param name			the name of the principal
	 * @return				true if it was successful
	 * @throws IOException 
	 */
	public boolean registerPrincipalInterest(String name) throws IOException
	{	
		boolean retVal = false;

			String data = URLEncoder.encode("do", "UTF-8") + "=" + URLEncoder.encode("registerPrincipalInterest", "UTF-8");
		    data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(_name, "UTF-8");
		    data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(_key, "UTF-8");
		    data += "&" + URLEncoder.encode("other", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
			
		    String response = sendRequest(_conciergeURL, data);
		    
		    retVal = Boolean.parseBoolean(response);

		
		return retVal;
	}	
	
	/**
	 * Get the set of principals you have an interest in
	 * @return				the set of principals you have an interest in
	 */
	public JSONArray getPrincipalInterests() throws IOException, JSONException
	{
		JSONArray retVal = new JSONArray();

			String data = URLEncoder.encode("do", "UTF-8") + "=" + URLEncoder.encode("getPrincipalInterests", "UTF-8");
		    data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(_name, "UTF-8");
		    data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(_key, "UTF-8");
			
		    String response = sendRequest(_conciergeURL, data);
		    retVal = new JSONArray(response);
//		    Object obj = JSONValue.parse(response);
//		    retVal = (JSONArray) obj;
		    

		return retVal;
	}
	
	/**
	 * Unregister an interest in a specific Principal
	 * @param name			the name of the principal
	 * @return				true if it was successful
	 * @throws IOException 
	 */
	public boolean unregisterPrincipalInterest(String name) throws IOException
	{	
		boolean retVal = false;

			String data = URLEncoder.encode("do", "UTF-8") + "=" + URLEncoder.encode("unregisterPrincipalInterest", "UTF-8");
		    data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(_name, "UTF-8");
		    data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(_key, "UTF-8");
		    data += "&" + URLEncoder.encode("other", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
			
		    String response = sendRequest(_conciergeURL, data);
		    
		    retVal = Boolean.parseBoolean(response);

		
		return retVal;
	}	
	
	/**
	 * Unregister your interest in all of the principals
	 * @return				true if it was successful
	 * @throws IOException 
	 */
	public boolean unregisterAllPrincipalInterests() throws IOException
	{	
		boolean retVal = false;

			String data = URLEncoder.encode("do", "UTF-8") + "=" + URLEncoder.encode("unregisterAllPrincipalInterests", "UTF-8");
		    data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(_name, "UTF-8");
		    data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(_key, "UTF-8");
			
		    String response = sendRequest(_conciergeURL, data);
		    
		    retVal = Boolean.parseBoolean(response);

		
		return retVal;
	}	
	
	/**
	 * Unregister all of your interests
	 * @return				true if it was successful
	 * @throws IOException 
	 */
	public boolean unregisterAllInterests() throws IOException
	{	
		boolean retVal = false;

			String data = URLEncoder.encode("do", "UTF-8") + "=" + URLEncoder.encode("unregisterAllInterests", "UTF-8");
		    data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(_name, "UTF-8");
		    data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(_key, "UTF-8");
			
		    String response = sendRequest(_conciergeURL, data);
		    
		    retVal = Boolean.parseBoolean(response);

		
		return retVal;
	}
	
	/**
	 * Post a message to Concierge
	 * @param content		The actual message
	 * @param tags			an array of tags
	 * @return				the id of the new message, or -1 if it fails
	 * @throws IOException 
	 */
	public int postMessage(JSONObject content, String[] tags) throws IOException
	{	
		int retVal = -1;

			String data = URLEncoder.encode("do", "UTF-8") + "=" + URLEncoder.encode("postMessage", "UTF-8");
		    data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(_name, "UTF-8");
		    data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(_key, "UTF-8");
//		    data += "&" + URLEncoder.encode("content", "UTF-8") + "=" + URLEncoder.encode(content.toJSONString(), "UTF-8");
		    data += "&" + URLEncoder.encode("content", "UTF-8") + "=" + URLEncoder.encode(content.toString(), "UTF-8");    
		    data += "&" + URLEncoder.encode("tags", "UTF-8") + "=" + URLEncoder.encode(arrayToString(tags), "UTF-8");
			Log.d("CONCIERGE", "SENDING REQUEST");
		    String response = sendRequest(_conciergeURL, data);
			Log.d("CONCIERGE", "FINISHED SENDING REQUEST");
		    
		    if(!response.equalsIgnoreCase("false"))
		    {
		    	retVal = Integer.parseInt(response);
		    }
			Log.d("CONCIERGE", "MESSAGE_ID IS" + retVal);


		return retVal;
	}	
	
	/**
	 * Get the stream for all of your current interests
	 * @param since			id of the last message received
	 * @param limit			maximum number of messages to return
	 * @return				an array of all the messages in the stream
	 */
	public JSONArray getPrincipalInterestStream(String since, String limit) throws IOException, JSONException
	{
		JSONArray retVal = new JSONArray();

			String data = URLEncoder.encode("do", "UTF-8") + "=" + URLEncoder.encode("getPrincipalInterestStream", "UTF-8");
		    data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(_name, "UTF-8");
		    data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(_key, "UTF-8");
		    data += "&" + URLEncoder.encode("since", "UTF-8") + "=" + URLEncoder.encode(since, "UTF-8");
		    data += "&" + URLEncoder.encode("limit", "UTF-8") + "=" + URLEncoder.encode(limit, "UTF-8");
			
		    String response = sendRequest(_conciergeURL, data);
		    retVal = new JSONArray(response);
//		    Object obj = JSONValue.parse(response);
//		    retVal = (JSONArray) obj;
		    

		
		return retVal;
	}
	

	/**
	 * Get the current principal's name
	 * @return 				the principal's name
	 */
	public String getName()
	{
		return this._name;
	}

	/**
	 * Get the current principal's key
	 * @return				the principal's key
	 */
	public String getKey()
	{
		return this._key;
	}

	
	/**
	 * Get the current principal's id number
	 * @return				the principal's id number
	 */
	public int getPrincipalID()
	{
		return this._principalID;
	}
	

	/**
	 * Convert an array of strings to a comma delineated string
	 * @param strings		an array of strings
	 * @return				a comma delineated string 
	 */
	private static String arrayToString(String[] strings)
	{
		StringBuffer result = new StringBuffer();
		if(strings.length > 0)
		{
			result.append(strings[0]);
			for(int i = 1; i < strings.length; i++)
			{
				result.append(",");
				result.append(strings[i]);
			}
		}
		Log.d("CONCEIGE", "RESULT IS: " + result);
		return result.toString();
	}
}