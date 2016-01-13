package edu.nju.onlineInterview.action;

import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.onlineInterview.common.SessionConstant;
import edu.nju.onlineInterview.model.Account;
import edu.nju.onlineInterview.service.AccountService;
import edu.nju.onlineInterview.util.MD5Util;

public class StudentLoginAction extends BaseAction{
	private static final long serialVersionUID = -1261337108300582415L;
	
	@Autowired
	private AccountService accountService;
	
	@Override
	public String execute(){
		String email = request.getParameter("email");
		String password = MD5Util.encrypt(request.getParameter("password"));
		Account account = accountService.verifyAccount(email, password);
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
