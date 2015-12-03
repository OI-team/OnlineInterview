package edu.nju.onlineInterview.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author margine
 * @description the basic information of student 
 * @createTime 2015��11��15������4:55:08
 * @contact ch_margine@163.com
 */

@Entity
@Table(name="student", catalog="interview")
public class Student implements Serializable{

	/**
	 * 
	 */
	public static final String STU_ID = "s_id";
	public static final String STU_NAME = "s_name";
	public static final String STU_GENDER = "s_gender";
	public static final String STU_SCHOOL = "s_school";
	public static final String STU_MAJOR = "s_major";
	/**the id of the student is his(her) major*/
	public static final String STU_NUM = "s_num";
	/**the rank of student's score*/
	public static final String STU_RANK = "s_rank";
	public static final String STU_PHONE = "s_phone";
	
	private static final long serialVersionUID = -1939141102377888343L;
	private Integer id;
	private String name;
	private String sex;
	private String school;
	private String major;
	private Integer rank;
	private String number;
	private String phone;
	
	public Student(Integer _id, String _name, String _sex, String _school, String _major, Integer _rank, String _number, String _phone){
		id = _id;
		name = _name;
		sex = _sex;
		school = _school;
		major = _major;
		rank = _rank;
		number = _number;
		phone = _phone;
	}
	
	public Student(String _name, String _sex, String _school, String _major, Integer _rank, String _number, String _phone) {
		name = _name;
		sex = _sex;
		school = _school;
		major = _major;
		rank = _rank;
		number = _number;
		phone = _phone;

	}
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name=STU_ID, unique=true, nullable=false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name=STU_NAME, nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name=STU_GENDER)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name= STU_SCHOOL)
	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Column(name=STU_MAJOR)
	public String getMajor() {
		return major;
	}
	
	public void setMajor(String major) {
		this.major = major;
	}
	
	@Column(name=STU_RANK)
	public Integer getRank() {
		return rank;
	}
	
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Column(name=STU_NUM)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name=STU_PHONE, nullable=false)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
