package edu.nju.onlineInterview.service;

import java.util.List;

import edu.nju.onlineInterview.dao.AccountDAO;
import edu.nju.onlineInterview.dao.AdminDAO;
import edu.nju.onlineInterview.dao.StudentDAO;
import edu.nju.onlineInterview.model.Account;
import edu.nju.onlineInterview.model.Student;

/**
 * 
 * @author mzdong E-mail:mzdong163.com
 * @description TO achieve the operation of business logic of manager
 * @date 2015年12月8日 上午10:06:00
 * @vesion 1.0
 */

public class AdminService {
	
	private StudentDAO studentDAO;
	
	/**
	 * view the enrollment information of student.
	 * @param
	 * @return List<Student>
	 */
	public List<Student> viewRegistration(){
		List<Student> results = studentDAO.findAllStudents();
		return results; 
	}
	
	/**
	 * Release the information of interview, including the interview time 
	 * of every student.
	 * only to upload excel table to complete the task.
	 * @param
	 * @return
	 */
	public boolean interviewNotice(String str){
		//TODO
		
		return false;
	}
	
	/**
	 * Manage the corresponding interviewed students of every teacher, 
	 * and the interviewing time.
	 * @param
	 * @return
	 */
	public Object teacherManage(){
		//TODO
		
		return null;
	}
	
	/**
	 * Download the interviewed information.
	 * Compressed to zip, including score,comment,snapshot,video,interviewingQ&A.
	 * @param
	 * @return
	 */
	public boolean downloadRecord(){
		return false;
	}

}
