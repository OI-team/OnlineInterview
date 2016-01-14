package edu.nju.onlineInterview.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import edu.nju.onlineInterview.dao.AccountDAO;
import edu.nju.onlineInterview.dao.StudentDAO;
import edu.nju.onlineInterview.model.Account;
import edu.nju.onlineInterview.model.Student;
import edu.nju.onlineInterview.vo.StudentBriefInfoVO;

/**
 * 
 * @author sunruiyang
 * @description provide service regarding students.
 * @createTime 2015��12��03������10:21:13
 * @contact 15852912893@163.com
 */
public class StudentService {

	@Autowired
	private StudentDAO studentDAO;
	
	@Autowired
	private AccountDAO accountDAO;

	/**
	 * verify studentInformation
	 * @param studentId
	 * @param studentName
	 * @param studentSex
	 * @param studentSchool
	 * @param studentMajor
	 * @param studentRank
	 * @param studentNum
	 * @param studentPhone
	 * @return
	 */
	
	public int addStudent(Student student) {
			return (int) studentDAO.save(student);
	}
	
	public Student findStudent(Integer studentId){
		Student student = studentDAO.findStudentById(studentId);
		return student;
	}
	
	public boolean deleteStudent(Integer studentId){
		Student student = studentDAO.findStudentById(studentId);
		if (student != null) {
			studentDAO.delete(student);
			return true;
		}
		return false;
	}
	
	public boolean updateStudent(Student student){
		studentDAO.update(student);
		return true;
	}
	
	public List<StudentBriefInfoVO> getStudentBriefInfoList(int pageIndex, int pageSize){
		List<Student> students = studentDAO.findByPage(pageIndex, pageSize);
		List<StudentBriefInfoVO> results = new ArrayList<>();
		if (students != null && students.size() > 0) {
			for(Student one : students){
				Account account = accountDAO.findByProperty(Account.ACCOUNT_ID, one.getAccountId());
				StudentBriefInfoVO info = new StudentBriefInfoVO(one.getName(), account.getName(), one.getSchool());
				results.add(info);
			}
		}
		return results;
	}
	
}
