package edu.nju.onlineInterview.vo;

public class StudentBriefInfoVO {
	
	public String name;
	public String email;
	public String school;
	
	public StudentBriefInfoVO(String name, String email, String school) {
		this.name = name;
		this.email = email;
		this.school = school;
	}
	
	public String getName(){
		return name;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getSchool() {
		return school;
		
	}
}
