package edu.nju.onlineInterview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.onlineInterview.dao.RecordDAO;
import edu.nju.onlineInterview.model.Record;

/**
 * 
 * @author Robin
 */
public class TeacherService {
	
	public final static int ERRORCODE = -1;
	
	@Autowired
	private RecordDAO recordDAO;


	/**
	 * add record
	 * @param record
	 * @return the id of the new record. id = -1 means fail
	 */
	@Transactional(rollbackFor=Exception.class)
	public int addRecord(Record record){
		List<Record> results = recordDAO.findListByProperty(Record.STU_ID, record.getS_id());
		if (results == null || results.size() == 0) {
			return (int) recordDAO.save(record);
		}
		return ERRORCODE;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public boolean deleteRecord(Integer r_id){
		Record Record = recordDAO.findRecordById(r_id);
		if (Record != null) {
			recordDAO.delete(Record);
			return true;
		}
		return false;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public boolean updateRecord(Record record){
		recordDAO.update(record);
		return true;
	}
}
