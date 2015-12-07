package edu.nju.onlineInterview.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.nju.onlineInterview.model.Account;
import edu.nju.onlineInterview.model.Student;

public class StudentDAO extends BaseDAO<Student>{

	private static final Logger log = LoggerFactory.getLogger(StudentDAO.class);
	
	public Student findStudentById(Integer id) {
		log.debug("find student by id = " + id);
		return findById(Student.class, id);
	}
	
}
