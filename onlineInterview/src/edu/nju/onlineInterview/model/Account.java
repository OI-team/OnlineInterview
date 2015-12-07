package edu.nju.onlineInterview.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @author margine
 * @description relate to the table account, dealing with login/logout operations 
 * @createTime 2015��11��15������6:40:38
 * @contact ch_margine@163.com
 */
@Entity
@Table(name="account", catalog="oi", uniqueConstraints=@UniqueConstraint(columnNames="account_name"))
public class Account implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8835469707655628380L;
	
	public static final String ACCOUNT_ID = "account_id";
	/**the name of the account. There are three situations. email for student, name for teacher, 'admin' for administrator*/
	public static final String ACCOUNT_NAME = "account_name";
	/**password of the account*/
	public static final String ACCOUNT_PWD = "account_pwd";
	/**type of the account. student|teacher|admin*/
	public static final String ACCOUNT_TYPE = "account_type";

	
	private Integer id;
	private String name;
	private String password;
	private String type;
	
	public Account(String _name, String _password, RoleType roleType){
		name = _name;
		password = _password;
		type = roleType.getDesc();
	}
	
	public Account(String _name, String _password) {
		name = _name;
		password = _password;
	}
	

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name=ACCOUNT_ID, unique=true, nullable=false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name=ACCOUNT_NAME, unique=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name=ACCOUNT_PWD, nullable=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name=ACCOUNT_TYPE)
	public String geType(){
		return type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public RoleType getRoleType(){
		return RoleType.valueOf(type);
	}
}
