package edu.nju.onlineInterview.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.nju.onlineInterview.model.Student;

public class StudentDAO extends BaseDAO<Student>{

	private static final Logger log = LoggerFactory.getLogger(StudentDAO.class);
	
	/*
	 * @author sunruiyang
	 * @findStudentById
	 */
	public Student findStudentById(Integer id) {
		log.debug("find student by id = " + id);
		return findById(Student.class, id);
	}
	
	/*
	 * @author mzdong
	 * @return List<Student>
	 * Find all students, used in AdminService.java.
	 */
	public List<Student> findAllStudents(){
		log.debug("find all students");
		return findAll();
	}
}
