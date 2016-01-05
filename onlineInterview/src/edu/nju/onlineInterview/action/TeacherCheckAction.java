package edu.nju.onlineInterview.action;

import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.onlineInterview.model.Record;
import edu.nju.onlineInterview.service.TeacherService;

public class TeacherCheckAction extends BaseAction{
	
	/**
	 * @author Robin
	 */
	private static final long serialVersionUID = 4315353066410153137L;
	
	private Record record;
	
	
	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	@Autowired
	private TeacherService teacherService;
	
	@Override
	public String execute(){
		return SUCCESS;
	}
	
	public String addRecord(){
//		System.out.println(record.getComment());
//		System.out.println(record.getSnap());
//		System.out.println(record.getVideo());
//		System.out.println(record.getS_id());
//		System.out.println(record.getScore());
		if(teacherService.addRecord(record)!=-1){
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
}
