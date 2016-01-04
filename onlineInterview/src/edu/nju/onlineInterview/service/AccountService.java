package edu.nju.onlineInterview.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.onlineInterview.dao.AccountDAO;
import edu.nju.onlineInterview.model.Account;

/**
 * 
 * @author margine
 * @description 
 * @createTime 2015��11��15������5:14:24
 * @contact ch_margine@163.com
 */
public class AccountService {
	
	public final static int ERRORCODE = -1;
	
	@Autowired
	private AccountDAO accountDAO;
	
	/**
	 * verify login
	 * @param accountName
	 * @param password
	 * @return
	 */
	public Account verifyAccount(String accountName, String password){
		Map<String, String> conditionMap = new HashMap<>();
		conditionMap.put(Account.ACCOUNT_NAME, accountName);
		conditionMap.put(Account.ACCOUNT_PWD, password);
		List<Account> results= accountDAO.findListByProperty(conditionMap);
		if (results != null && results.size() >0) {
			return results.get(0);
		}
		return null;
	}
	
	/**
	 * register 
	 * @param account
	 * @return the id of the new account. <br/> -1 means fail
	 */
	@Transactional(rollbackFor=Exception.class)
	public int addAccount(Account account){
		List<Account> results = accountDAO.findListByProperty(Account.ACCOUNT_NAME, account.getName());
		if (results == null || results.size() == 0) {
			return (int) accountDAO.save(account);
		}
		return ERRORCODE;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public boolean deleteAccount(Integer accountId){
		Account account = accountDAO.findAccountById(accountId);
		if (account != null) {
			accountDAO.delete(account);
			return true;
		}
		return false;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public boolean updateAccount(Account account){
		accountDAO.update(account);
		return true;
	}
}
