package edu.stanford.cs.gaming.sdk.model;

public class Group {
	public int id;
	public String name;
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
