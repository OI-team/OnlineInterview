package edu.nju.onlineInterview.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.nju.onlineInterview.model.Account;

public class AccountDAO extends BaseDAO<Account>{
	
	private static final Logger log = LoggerFactory.getLogger(AccountDAO.class);
	
	public Account findAccountById(Integer id){
		log.debug("find account by id = " + id);
		return findById(Account.class, id);
	}

}
