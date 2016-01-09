package edu.nju.onlineInterview.action;

import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.onlineInterview.model.Student;
import edu.nju.onlineInterview.service.AccountService;
import edu.nju.onlineInterview.service.StudentService;

public class StudentRegisterAction extends BaseAction {
	/**
	 * Complete add features
	 * 2016.1.9
	 */
	private static final long serialVersionUID = -6055722229010536136L;
	
	private String studentName;
	private String studentSex;
	private String studentSchool;
	private String studentMajor;
	private int   studentRank;
	private String studentNum;
	private String studentPhone;
	

	@Autowired
	private AccountService accountService;
	@Autowired
	private StudentService studentService;
	
	@Override
	public String execute(){
		studentName = request.getParameter("stuname");
		studentSex = request.getParameter("stusex");
		studentSchool = request.getParameter("stuschool");
		studentMajor = request.getParameter("stumajor");
		studentRank = Integer.parseInt(request.getParameter("sturank"));
		studentNum = request.getParameter("stunum");
		studentPhone = request.getParameter("stuphone");
	//硬性固定了accountId
		Student student=new Student(1,studentName,studentSex,studentSchool,studentMajor,studentRank,studentNum,studentPhone);
		int studentAdd=studentService.addStudent(student);
		if(studentAdd==-1){
			return ERROR;
		}else{
			return SUCCESS;
		}
	
	}

}
