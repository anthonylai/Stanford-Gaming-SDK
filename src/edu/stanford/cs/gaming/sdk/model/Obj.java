package edu.stanford.cs.gaming.sdk.model;

public class Obj {
	public int id;
	public int app_id;
	public int user_id;
	public int group_id;
	public String type;
    public ObjProperty[] object_properties; 
    public Obj() {
	  
  }
    /*
  public Obj(String name) {
	  this.name = name;
	  array[0] = "000";
	  array[1] = "111";
	  array[2] = "222";
      for (int i = 0; i < gameObjectAttr.length; i++) {
    	  gameObjectAttr[i] = new ObjProperty();
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
		  for (ObjProperty attr : gameObjectAttr) {
			  if (attr != null)
				  string += "attr is: " + attr.intVal + "||";
		  }
	  }
	  return string;
	  
  }
  */
}
