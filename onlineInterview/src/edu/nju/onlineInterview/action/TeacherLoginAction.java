package edu.nju.onlineInterview.action;

import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.onlineInterview.service.AccountService;

public class TeacherLoginAction extends BaseAction{
	private static final long serialVersionUID = 3109662084815307479L;
	
	@Autowired
	private AccountService accountService;
	
	@Override
	public String execute(){
		//TODO realize it 
		return INPUT;
	}
	
	public String logOut(){
		//TODO 
		return SUCCESS;
	}

}
