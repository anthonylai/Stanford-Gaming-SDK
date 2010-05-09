package edu.stanford.cs.gaming.sdk.model;

import org.json.JSONException;
import org.json.JSONObject;

public class GameObject {
  public String name;
  public int value;
  public String[] array = new String[3];
  public GameObjectAttr[] gameObjectAttr = new GameObjectAttr[3];
  public GameObject() {
	  
  }
  public GameObject(String name) {
	  this.name = name;
	  array[0] = "000";
	  array[1] = "111";
	  array[2] = "222";
      for (int i = 0; i < gameObjectAttr.length; i++) {
    	  gameObjectAttr[i] = new GameObjectAttr();
      }
  }
  public JSONObject toJson() {
//	  String[] stringArray = null;
	  JSONObject obj = new JSONObject();
//	  String string;
//	  new JSONObject(string);
	  try {
		obj.put("name", name);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return obj;
	  
  }
  public String toString() {
	  String string = "Name: " + name + "|| value: " + value + "|| array: ";
	  if (array != null) {
	  for (String str : array) {
		  string += str + "||";
	  }
	  }
	  if (gameObjectAttr != null) {
		  for (GameObjectAttr attr : gameObjectAttr) {
			  if (attr != null)
				  string += "attr is: " + attr.intVal + "||";
		  }
	  }
	  return string;
	  
  }
}
