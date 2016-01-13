package edu.nju.onlineInterview.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.onlineInterview.common.FileConstant;
import edu.nju.onlineInterview.common.HeaderConstant;
import edu.nju.onlineInterview.dao.AccountDAO;
import edu.nju.onlineInterview.dao.AdminDAO;
import edu.nju.onlineInterview.dao.StudentDAO;
import edu.nju.onlineInterview.model.Account;
import edu.nju.onlineInterview.model.Student;
import edu.nju.onlineInterview.util.ExcelUtil;
import edu.nju.onlineInterview.util.FileUtil;

/**
 * 
 * @author mzdong E-mail:mzdong163.com
 * @description TO achieve the operation of business logic of manager
 * @date 2015��12��8�� ����10:06:00
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
	public boolean downloadRecords(HttpServletResponse response){
		FileUtil.ZipFile(FileConstant.RECORDS_ZIP, FileConstant.STUDENT_DIR);
		FileUtil.Download(response, FileConstant.RECORD_FPRMAT_PATH);
		return true;
	}
	
	public void downloadStuInfo(HttpServletResponse response, String absolutepath){
		generateStuInfoExcel();
		FileUtil.Download(response, absolutepath);
	}
	
	private boolean generateStuInfoExcel(){
		if (!FileUtil.isFileExist(FileConstant.STUDENT_INFO_PATH)) {
			String[] headers = new String[]{HeaderConstant.ID, HeaderConstant.NAME, HeaderConstant.GENDER, HeaderConstant.SCHOOL, HeaderConstant.MAJOR,HeaderConstant.NUM, HeaderConstant.RANK, HeaderConstant.PHONE};
			String savePath = FileConstant.ADMIN_DIR;
			
			List<Map<String, String>> contents = new ArrayList<>();
			ArrayList<Student> students = (ArrayList<Student>) studentDAO.findAll();
			if (students != null && !students.isEmpty()) {
				for(int i = 0; i< students.size(); i++){
					Student one = students.get(i);
					Map<String, String> map = new HashMap<>();
					map.put(HeaderConstant.ID, String.valueOf(i+1));
					map.put(HeaderConstant.NAME, one.getName());
					map.put(HeaderConstant.GENDER, one.getGender());
					map.put(HeaderConstant.MAJOR, one.getMajor());
					map.put(HeaderConstant.NUM, one.getNumber());
					map.put(HeaderConstant.PHONE, one.getPhone());
					map.put(HeaderConstant.RANK, String.valueOf(one.getRank()));
					contents.add(map);
				}
			}
			ExcelUtil.createExcel(savePath, FileConstant.STUDENT_INFO, headers, contents);
		}
		return true;
	}
}
