package edu.nju.onlineInterview.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author margine
 * @description define the base interface for DAO.
 * @createTime 2015��11��15������3:23:20
 * @contact ch_margine@163.com
 * @param <T>
 */
public interface IBaseDAO<T> {
	   /**
	    * save a new object
	    * @param t
	    */
	   public void save(T t);
	   
	   /**
	    * update or insert
	    * @param t
	    * @return persistent object
	    */
	   public T merge(T t);
	   
	   /**
	    * make the object in parameter persistent. 
	    * If t is transient, call method save(). Else t is detached, call method update().
	    * @param t
	    */
	   public void attachDirty(T t);
	   
	   /**
	    * make the object in parameter transient.
	    * @param t
	    */
	   public void attachClean(T t);
	   
	   /**
	    * delete persistent object
	    * @param t
	    */
	   public void delete(T t);
	   public void deleteByProperty(Class<T> clazz, String propertyName, Object vlaue);
	   
	   
	   public T findById(Class<T> clazz, Serializable id);
	   public List<T> findByExample(T t);
	   public List<T> findByProperty(Class<T> clazz, String propertyName, Object value);
	   public List<T> findAll(Class<T> clazz);
}
