package edu.stanford.cs.gaming.sdk.model;

public class AppResponse {
	public int request_id;
	public AppRequest appRequest;
	public String result_code;
	public String error;
	public Object object;

	public String toString() {
		String str = "";
			str = "\n===================================\n";
			str += "Request id: " + request_id + "\n";
			str += "AppRequest: " + appRequest + "\n";
			str += "result_code: " + result_code + "\n";
			str += "Error: " + error + "\n";
			str += "Object: " + object + "\n";
			str += "===================================\n";
			return str;
	}
}
