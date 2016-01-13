package edu.nju.onlineInterview.action;

import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.onlineInterview.common.SessionConstant;
import edu.nju.onlineInterview.model.Account;
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
		String name = "admin";
		String password = request.getParameter("password");
		Account account = accountService.verifyAccount(name, password);
		if (account != null) {
			session.put(SessionConstant.ACCOUNT_ID, account.getId());
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String logOut(){
		session.remove(SessionConstant.ACCOUNT_ID);
		return SUCCESS;
	}
}
