package edu.nju.onlineInterview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.onlineInterview.dao.AccountDAO;
import edu.nju.onlineInterview.dao.StudentDAO;
import edu.nju.onlineInterview.model.Account;

/**
 * 
 * @author margine
 * @description 
 * @createTime 2015年11月15日下午5:14:24
 * @contact ch_margine@163.com
 */
public class AccountService {
	
	@Autowired
	private AccountDAO accountDAO;
	
	public Account verifyAccount(String accountName, String password){
		//TODO
		return null;
	}
	
	/**
	 * 
	 * @param account
	 * @return the id of the new added account
	 */
	@Transactional(rollbackFor=Exception.class)
	public int addAccount(Account account){
		//TODO
		return -1;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public boolean deleteAccount(Integer accountId){
		//TODO
		return false;
	}
	
	public boolean updateAccount(Account account){
		//TODO
		return false;
	}
}
