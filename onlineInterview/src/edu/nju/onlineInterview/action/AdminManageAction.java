package edu.nju.onlineInterview.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.onlineInterview.common.FileConstant;
import edu.nju.onlineInterview.model.Account;
import edu.nju.onlineInterview.model.RoleType;
import edu.nju.onlineInterview.service.AccountService;
import edu.nju.onlineInterview.service.AdminService;
import edu.nju.onlineInterview.service.StudentService;
import edu.nju.onlineInterview.util.FileUtil;
import edu.nju.onlineInterview.util.MD5Util;
import edu.nju.onlineInterview.vo.StudentBriefInfoVO;

/**
 * @author mzdong E-mail:mzdong163.com
 * @description  Administrator manage the interview information, 
 * 				 including the range of time of every interviewing student.
 * @date 2015��12��8�� ����3:00:32
 * @vesion 1.0
 */
public class AdminManageAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4577595438491286254L;
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AdminService adminService;
	
	private List<StudentBriefInfoVO> briefInfo = new ArrayList<StudentBriefInfoVO>();
	
	@Override
	public String execute(){
		return SUCCESS;
	}

	public String loadStudentList(){
		int pageIndex = Integer.valueOf(request.getParameter("page"));
		int pageSize = Integer.valueOf(request.getParameter("pageSize"));
		briefInfo = studentService.getStudentBriefInfoList(pageIndex, pageSize);
		if (briefInfo != null) {
			return SUCCESS;
		}
		return ERROR;
	}

	public String downloadStudents(){
		String curPath = request.getSession().getServletContext().getRealPath("/");
		String fatherPath = new File(curPath).getParent();
		String absolutepath = fatherPath + FileConstant.STUDENT_INFO_PATH;
		adminService.downloadStuInfo(response, absolutepath);
		return SUCCESS;
	}
	
	public String addTeacher(){
		String name = request.getParameter("name");
		String password = MD5Util.encrypt(request.getParameter("password"));
		Account account = new Account(name, password, RoleType.TEACHER.getDesc());
		int code = accountService.addAccount(account);
		if (code != AccountService.ERRORCODE) {
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String uploadArrange(){
		String curPath = request.getSession().getServletContext()
				.getRealPath("/");
		String fatherPath = new File(curPath).getParent();
		String relativePath = File.separator + FileConstant.ADMIN_DIR;
		String filedir = fatherPath + relativePath;
		String newfilename = FileConstant.ARRANGE_PATH;
		FileUtil.Upload(request, filedir, newfilename, "arrange");
		return SUCCESS;
	}
	
	
	public String downloadRecords(){
		adminService.downloadRecords(response);
		return SUCCESS;
	}
	
	public List<StudentBriefInfoVO> getBriefInfo() {
		return briefInfo;
	}

	public void setBriefInfo(List<StudentBriefInfoVO> briefInfo) {
		this.briefInfo = briefInfo;
	}
	
}
