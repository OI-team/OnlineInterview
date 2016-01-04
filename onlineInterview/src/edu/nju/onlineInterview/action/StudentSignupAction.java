package edu.nju.onlineInterview.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;

import edu.nju.onlineInterview.model.Account;
import edu.nju.onlineInterview.model.RoleType;
import edu.nju.onlineInterview.model.Student;
import edu.nju.onlineInterview.service.AccountService;
import edu.nju.onlineInterview.service.StudentService;

public class StudentSignupAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1780385101106915376L;
	
	private String email;
	private String studentName;
	private String password;
	private String confirmPwd;
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private StudentService studentService;
	
	@Override
	public String execute(){
		email = request.getParameter("email");
		studentName = request.getParameter("studentName");
		password = request.getParameter("password");
		confirmPwd = request.getParameter("confirmPassword");
		
		Account account = new Account(email, password, RoleType.STUDENT.getDesc());
		System.out.println(RoleType.STUDENT.getDesc());
		int accountId = accountService.addAccount(account);
		if (accountId == AccountService.ERRORCODE) {
			return ERROR;
		}
		Student student = new Student(accountId, studentName);
		studentService.addStudent(student);
		session.put("accountId", accountId);
		return SUCCESS;
	}

}
