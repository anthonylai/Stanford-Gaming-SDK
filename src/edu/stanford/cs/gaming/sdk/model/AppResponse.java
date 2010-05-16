package edu.stanford.cs.gaming.sdk.model;

import java.lang.reflect.Array;

public class AppResponse {

	public int request_id;
	public AppRequest appRequest;
	public String result_code;
	public String[] error;
	public Object object;
	

	public String toString() {
		String str = "";
			str = "\n===================================\n";
			str += "Request id: " + request_id + "\n";
			str += "AppRequest: " + appRequest + "\n";
			str += "result_code: " + result_code + "\n";
			if (error != null) {
			for (int i = 0; i < error.length; i++) {
  			  str +=  error[i] + "\n";
			}
			}
			if (object == null || !object.getClass().isArray())
				str += "Object: " + object + "\n";
			else {
				str += "Object array:\n";
				for (int i = 0; i < Array.getLength(object); i++) {
					str += "Object " + i + ":\n";
					str += Array.get(object, i);
				}
			}
			str += "===================================\n";
			return str;
	}
}
