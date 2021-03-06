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
@Table(name="account", catalog="interview", uniqueConstraints=@UniqueConstraint(columnNames="a_name"))
public class Account implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8835469707655628380L;
	
	public static final String ACCOUNT_ID = "a_id";
	/**the name of the account. There are three situations. email for student, name for teacher, 'admin' for administrator*/
	public static final String ACCOUNT_NAME = "a_name";
	/**password of the account*/
	public static final String ACCOUNT_PWD = "a_pwd";
	/**type of the account. student|teacher|admin*/
	public static final String ACCOUNT_TYPE = "a_type";

	
	private Integer id;
	private String name;
	private String password;
	private String type;
	
	public Account(String _name, String _password, String roleType){
		name = _name;
		password = _password;
		type = roleType;
	}
	
	public Account(String _name, String _password) {
		name = _name;
		password = _password;
	}
	
	public Account(){}
	

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
	public String getType(){
		return type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
}
