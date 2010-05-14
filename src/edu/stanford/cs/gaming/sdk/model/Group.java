package edu.stanford.cs.gaming.sdk.model;

public class Group {
	public int id;
	public int app_id;
	public String name;
	public User[] users;

	public Group() {}
	public Group(String name) {
		this.name = name;
	}

	public String toString() {
		String str = "";
		str = "\n===================================\n";
		str += "Group id: " + id + "\n";
		str += "Name: " + name + "\n";
		str += "===================================\n";
		return str;
				
	}
}
