package edu.nju.onlineInterview.service;

import java.util.List;

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
	
	@Autowired
	private AccountDAO accountDAO;
	
	/**
	 * verify login
	 * @param accountName
	 * @param password
	 * @return
	 */
	public Account verifyAccount(String accountName, String password){
		Account example = new Account(accountName, password);
		List<Account> results= accountDAO.findByExample(example);
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
		List<Account> results = accountDAO.findByExample(account);
		if (results == null || results.size() == 0) {
			Account newAccount = accountDAO.merge(account);
			if (newAccount != null) {
				return newAccount.getId();
			}
		}
		return -1;
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
		accountDAO.attachDirty(account);
		return true;
	}
}
