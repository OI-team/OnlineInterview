package edu.nju.onlineInterview.action;

import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.onlineInterview.service.AccountService;

public class AdminLoginAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1821015730865395341L;

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
