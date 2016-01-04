package edu.nju.onlineInterview.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

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
	 * save instance
	 * 
	 * @param t
	 *            instance
	 * @return primary key
	 */
	public Object save(T t);

	/**
	 * delete instance
	 * 
	 * @param t
	 */
	public void delete(T t);

	/**
	 * update instance
	 * 
	 * @param t
	 */
	public void update(T t);

	/**
	 * if instance does not contain a primary key, save it, or update it
	 * 
	 * @param t
	 */
	public void saveOrUpdate(T t);

	/**
	 * save instances in bulk
	 * 
	 * @param instances
	 */
	public void saveAll(Collection<T> instances);

	/**
	 * delete instances in bulk
	 * 
	 * @param instances
	 */
	public void deleteAll(Collection<T> instances);

	/**
	 * update instances in bulk
	 * 
	 * @param instances
	 */
	public void updateAll(Collection<T> instances);

	public void saveOrUpdateAll(Collection<T> instances);

	public T find(CharSequence queryString, Map<String, ? extends Object> params);

	public List<T> findList(CharSequence queryString, Object... params);

	public List<T> findList(CharSequence queryString, Map<String, ? extends Object> params);

	/**
	 * get single instance by primary key
	 * 
	 * @param instance
	 *            class
	 * @param id
	 * @return instance
	 */
	public T findById(Class<T> clazz, Serializable id);

	/**
	 * get instance corresponding to query conditions, if there are several
	 * results, return the first;
	 * 
	 * @param params
	 * @return
	 */
	public T findByProperty(Map<String, ? extends Object> conditionMap);

	public T findByProperty(String name, Object value);

	/**
	 * get all instances corresponding to query condition
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public List<T> findListByProperty(String name, Object value);

	/**
	 * get all instances corresponding to query conditions
	 * 
	 * @param clazz
	 * @param conditionMap
	 * @return
	 */
	public List<T> findListByProperty(Map<String, ? extends Object> conditionMap);
	
	public List<T> findAll();

	public Boolean isExist(Map<String, ? extends Object> conditionMap);

	public Boolean isExist(String name, Object value);

}
