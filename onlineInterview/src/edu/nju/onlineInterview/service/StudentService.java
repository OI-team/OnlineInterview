package edu.nju.onlineInterview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.onlineInterview.dao.StudentDAO;
import edu.nju.onlineInterview.model.Student;

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
		List<Student> results = studentDAO.findByExample(student);
		if (results == null || results.size() == 0) {
			Student newStudent = studentDAO.merge(student);
			if (newStudent != null) {
				return newStudent.getId();
			}
		}
		return -1;
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
		studentDAO.attachDirty(student);
		return true;
	}
}
