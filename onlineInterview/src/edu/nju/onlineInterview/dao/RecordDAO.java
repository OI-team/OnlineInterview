package edu.nju.onlineInterview.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.nju.onlineInterview.model.Record;

public class RecordDAO extends BaseDAO<Record>{
	/**
	 * @author Robin
	 */
	private static final Logger log = LoggerFactory.getLogger(RecordDAO.class);
	//TODO
	public Record findRecordById(Integer r_id){
		log.debug("find record by id = " + r_id);
		return findById(Record.class, r_id);
	}
	
	public List<Record> findAllRecord(){
		log.debug("find all record");
		return findAll();
	}
}
