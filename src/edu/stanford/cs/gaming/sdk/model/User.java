package edu.stanford.cs.gaming.sdk.model;



public class User {
  public int id;	
  public String first_name;
  public String last_name;
  public String email;
  public int fb_id;
  public String fb_photo;


  public User() {	  
  }
  
	public String toString() {
	  return "User id: " + id + " first_name: " + first_name + " last_name: " + last_name + " email: " + email
	  + " fb_id: " + fb_id;
  }
  
}
