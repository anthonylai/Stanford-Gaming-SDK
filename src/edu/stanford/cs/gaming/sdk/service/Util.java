package edu.stanford.cs.gaming.sdk.service;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.stanford.cs.gaming.sdk.model.GameObject;

import android.util.Log;


public class Util {
  public static final String TAG = "Util";
  public static JSONObject toJson(Field field) {
     return null;
  }

  private static final HashSet<Class<?>> WRAPPER_TYPES = getWrapperTypes();

  public static boolean isWrapperType(Class<?> clazz)
  {
      return WRAPPER_TYPES.contains(clazz);
  }

  private static HashSet<Class<?>> getWrapperTypes()
  {
      HashSet<Class<?>> ret = new HashSet<Class<?>>();
      ret.add(Boolean.class);
      ret.add(Character.class);
      ret.add(Byte.class);
      ret.add(Short.class);
      ret.add(Integer.class);
      ret.add(Long.class);
      ret.add(Float.class);
      ret.add(Double.class);
      ret.add(Void.class);
      ret.add(String.class);
      return ret;
  }  

  /*
  public static JSONArray toJsonArray(Collection obj) {
	  JSONArray array = new JSONArray();
	  return null;
  }
  */
/*
  public static JSONObject toJson(Object obj) {
	  return (JSONObject) toJson(obj.getClass().getName(), obj);
  }
  */
  public static Object toJson(Object obj) {
	  if (obj != null)
	  Log.d(TAG, obj.getClass().getName());


	  try {
	  if (obj == null)
		  return null;
	  if (obj.getClass().isPrimitive() || isWrapperType(obj.getClass())) {
        return obj;
			
	  }
	  Log.d(TAG, "isArray: " + obj.getClass().isArray());
	  if (obj.getClass().isArray()) {
		  Log.d(TAG, "ARRAY!!!");
		  int arrLen = Array.getLength(obj);
		  JSONArray jsonArray = new JSONArray();
		  for (int i=0; i < arrLen; i++) {
			  Object obj1 = Array.get(obj, i);
		      jsonArray.put(toJson(obj1));
		  }
//		  Log.d(TAG, "ARRAY PUTTING: " + name + ":" + jsonArray);
//		  jsonObj.put(name, jsonArray);
		  return jsonArray;
	  }
	
	  // It is a complex object
//	  JSONArray jsonArray = new JSONArray();		  
	      JSONObject jsonObj = new JSONObject();
	      JSONObject jsonObj1 = new JSONObject();
  		  Field[] fields = obj.getClass().getFields();
		  for (Field field: fields) {
			  Object obj1 = field.get(obj);
			  Log.d(TAG, "HERE: " + field.getName() + field.get(obj));
			  Log.d(TAG, "Primitive check for: " + obj1.getClass() + ":" + obj1.getClass().isPrimitive() + ":" + isWrapperType(obj1.getClass()));
					jsonObj1.put(field.getName(), toJson(obj1));	
//	            jsonObj1.put(field.getName(), toJson(obj1);	
 //            jsonArray.put(toJson(field.getName(), field.get(obj)));
				  
//					 Log.d(TAG, "NOT EQUALS");
//				     Log.d(TAG, field.getName() + ":" + field.getType().toString() + ":" + field.get(obj));

		  }	  
 
		jsonObj.put(obj.getClass().getName(), jsonObj1);
		return jsonObj;
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
	return null;


  }
  
    public static Object fromJson(Object obj, String name, String field) {
    	Log.d(TAG, "HERE 1: " + obj);
    	if (obj == null)
    		return null;
    	Log.d(TAG, "Object not null");
    	
    	if (obj.getClass().isPrimitive() || isWrapperType(obj.getClass())) {
    		return obj;
    	}
    	Log.d(TAG, "Object not primitive");

    	if (obj instanceof JSONArray) {
//    		JSONArray arr = (JSONArray) obj;
//    		Array.newInstance(field.g, size)
            return null;
    	}
    	Log.d(TAG, "Object not a JSON Array");

    	//Complex object
    	if (obj instanceof JSONObject) {
        	Log.d(TAG, "Object is JSON");
    		
    		JSONObject jsonObj = (JSONObject) obj;
    		Object obj1 = null;
    		try {
    			Iterator<String> keys = (Iterator<String>) jsonObj.keys();
    			while (keys.hasNext()) {
    				String className = keys.next();
    		    	Log.d(TAG, "Classname is: " + className);
    				
    			    obj1 = Class.forName(className).newInstance(); 
    			    Log.d(TAG, "Class.forName(className): " + Class.forName(className).getName());
    			    Log.d(TAG, "obj1 instance of GameObject: " + (obj1 instanceof GameObject));
    			    Log.d(TAG, "Object class is: " + ((Object)(new GameObject("111"))).getClass());
        	    		Field[] fields = obj1.getClass().getFields();
//    			        Field[] fields = GameObject.class.getFields();
        	    		Log.d(TAG, "Fields length is: " + fields.length);
        	    		Hashtable<String, Field> fieldsHash = new Hashtable<String, Field>();
        	    		for (Field field1 : fields) {
            	        	Log.d(TAG, "field inserted is: " + field1.getName());
        	    			
		                   fieldsHash.put(field1.getName(), field1);
    			    }
        	    	JSONObject jsonObj2 = (JSONObject) jsonObj.get(className);
        	    	Iterator<String> keys2 = (Iterator<String>) jsonObj2.keys(); 
        	    	while (keys2.hasNext()) {
        	    		Field field3 = null;
        	    		String key3 = keys2.next();
        	        	Log.d(TAG, "variable name is: " + key3);
        	    		
        	    		field3 = fieldsHash.get(key3);
        	    		if (field3 != null) {
            	        	Log.d(TAG, "field match found: " + key3 + "||" + field3.getType().getName());
            	        	Log.d(TAG, "instanceOf Array: " + (field3.getType().isArray()));
                            if (field3.getType().isArray()) {
                            	JSONArray jsonArr = (JSONArray) jsonObj2.get(key3);
                            	if (jsonArr != null) {

//                              	  Object arr = Array.newInstance(Class.forName("java.lang.String"), jsonArr.length());                            		
                            	  Object arr = Array.newInstance(field3.getType().getComponentType(), jsonArr.length());
                            	  Log.d(TAG, "arr is of type: " + arr.getClass());
                            	  for (int i = 0; i < jsonArr.length(); i++) {
                            		  Object objTmp = fromJson(jsonArr.get(i), null, null);
                            		  Log.d(TAG, "Object retured of class: " + objTmp.getClass());
    //                          		  Array.set(arr, i, "333");          

                            		  Array.set(arr, i, fromJson(jsonArr.get(i), null, null));
                            	  }
                            	  field3.set(obj1, arr);
                            	}
                            } else {
							field3.set(obj1, fromJson(jsonObj2.get(key3), null, null));
                            }

        	    	} else {
        	        	Log.d(TAG, "field NO MATCH: " + key3);
        	    		
        	    	}
        	    	
    			}
    			}
    			return obj1;
    		} catch (Exception e) {
    			// TODO Auto-generated catch block			
    			e.printStackTrace();
    			return obj1;
    		}

        	
        }
    	
    	return null;
    }
  
  /*
  public static Object toJson(String name, Object obj) {
	  if (obj != null)
	  Log.d(TAG, obj.getClass().getName());

	  JSONObject jsonObj = new JSONObject();


	  try {
	  if (obj == null)
		  return null;
	  if (obj.getClass().isPrimitive() || isWrapperType(obj.getClass())) {
		jsonObj.put(name, obj);
        return jsonObj;
			
	  }
//	  Log.d(TAG, "OBJECT CLASS IS: " + obj.getClass());
	  Log.d(TAG, "isArray: " + obj.getClass().isArray());
	  if (obj.getClass().isArray()) {
		  Log.d(TAG, "ARRAY!!!");
//		  Array arr = (Array) obj;
		  int arrLen = Array.getLength(obj);
		  JSONArray jsonArray = new JSONArray();
		  for (int i=0; i < arrLen; i++) {
			  Object obj1 = Array.get(obj, i);
			  if (obj1.getClass().isPrimitive() || isWrapperType(obj1.getClass())) {
				  jsonArray.put(obj1);
			  }  else {
				  jsonArray.put(toJson(obj1.getClass().getName(), obj1));
					  
			  }
		  }
//		  jsonObj.put
		  Log.d(TAG, "ARRAY PUTTING: " + name + ":" + jsonArray);
//		  jsonObj.put(name, jsonArray);
		  return jsonArray;
	  }
	
	  // It is a complex object
//	  JSONArray jsonArray = new JSONArray();		  
	      JSONObject jsonObj1 = new JSONObject();
	      
  		  Field[] fields = obj.getClass().getFields();
		  for (Field field: fields) {
			  Object obj1 = field.get(obj);
			  Log.d(TAG, "HERE: " + field.getName() + field.get(obj));
			  Log.d(TAG, "Primitive check for: " + obj1.getClass() + ":" + obj1.getClass().isPrimitive() + ":" + isWrapperType(obj1.getClass()));
			  if (obj1.getClass().isPrimitive() || isWrapperType(obj1.getClass())) {
					jsonObj1.put(field.getName(), obj1);	
			  } else {
//	            jsonObj1.put(field.getName(), toJson(obj1);	
			  }
 //            jsonArray.put(toJson(field.getName(), field.get(obj)));
				  
//					 Log.d(TAG, "NOT EQUALS");
//				     Log.d(TAG, field.getName() + ":" + field.getType().toString() + ":" + field.get(obj));

		  }	  
 
		jsonObj.put(name, jsonObj1);
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
	  return jsonObj;


  }
*/
  /*.equals(String.class) || obj.getClass().equals(Integer.class) ||
  obj.getClass().equals(Float.class) || obj.getClass().equals(Long.class) ||
  obj.getClass().equals(Double.class) || obj.getClass().equals(Boolean.class))*/

  /*
  jsonObj.
  jsonObj.
  jsonObj.
  jsonObj.put.put(jsonObj, obj.getClass().getName());

  */

  /*
  public JSONObject toJson(Object object, String names[]) {
	  JSONObject jsonObj = new JSONObject();
      Class c = object.getClass();
      for (int i = 0; i < names.length; i += 1) {
          String name = names[i];
          try {
              jsonObj.putOpt(name, c.getField(name).get(object));
          } catch (Exception ignore) {
          }
      }
      return jsonObj;
  }
*/
  
}
