package edu.nju.onlineInterview.model;

public enum RoleType {
	STUDENT("student"), TEACHER("teacher"), ADMIN("admin");
	
	RoleType(String _desc){
		desc = _desc;
	}
	
	public String getDesc(){
		return desc;
	}
	
	private String desc;
}
