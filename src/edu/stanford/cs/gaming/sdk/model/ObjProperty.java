package edu.stanford.cs.gaming.sdk.model;

import java.util.Date;

public class ObjProperty {
	 public static final int INT = 0;
	 public static final int FLOAT = 1;
	 public static final int STRING = 2;
	 public static final int BLOB = 3;
	 public static final int DATE = 4;
	 
     public int object_id;
     public int id;
     public String name;
     public int type;
     public int int_val;
     public float float_val;
     public String string_val;
     public byte[] blob_val;
     public Date date_val;
  
  
  
}
