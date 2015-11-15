package edu.nju.onlineInterview.action;

import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.onlineInterview.service.AccountService;
import edu.nju.onlineInterview.service.StudentService;

public class StudentRegisterAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6055722229010536136L;
	@Autowired
	private AccountService accountService;
	@Autowired
	private StudentService studentService;
	
	@Override
	public String execute(){
		//TODO realize it 
		return INPUT;
	}

}
