package edu.nju.onlineInterview.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 
 * @author margine
 * @description
 * @createTime 2015��11��15������3:33:25
 * @contact ch_margine@163.com
 */

public class BaseDAO<T> extends HibernateDaoSupport implements IBaseDAO<T> {

	protected Class<T> entityClazz;

	@SuppressWarnings("unchecked")
	public BaseDAO() {
		Type type = getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			this.entityClazz = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
		} else
			this.entityClazz = null;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object save(T t) {
		String className = t.getClass().getName();
		log.debug("save " + className + " instance");
		try {
			return (T) getCurrentSession().save(t);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(T t) {
		String className = t.getClass().getName();
		log.debug("deleting " + className + " instance");
		try {
			getCurrentSession().delete(t);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public void update(T t) {
		String className = t.getClass().getName();
		log.debug("update " + className + " instance");
		getCurrentSession().update(t);
	}

	@Override
	public void saveOrUpdate(T t) {
		String className = t.getClass().getName();
		log.debug("saveOrUpdate " + className + " instance");
		getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void saveAll(java.util.Collection<T> instances) {
		String className = instances.getClass().getName();
		log.debug("saveAll " + className + " instances");
		for (@SuppressWarnings("rawtypes")
		Iterator localIterator = instances.iterator(); localIterator.hasNext();) {
			@SuppressWarnings("unchecked")
			T instance = (T) localIterator.next();
			getCurrentSession().save(instance);
		}
	}

	@Override
	public void deleteAll(java.util.Collection<T> instances) {
		String className = instances.getClass().getName();
		log.debug("deleteAll " + className + " instances");
		for (@SuppressWarnings("rawtypes")
		Iterator localIterator = instances.iterator(); localIterator.hasNext();) {
			@SuppressWarnings("unchecked")
			T instance = (T) localIterator.next();
			getCurrentSession().delete(instance);
		}
	}

	@Override
	public void updateAll(Collection<T> instances) {
		String className = instances.getClass().getName();
		log.debug("updateAll " + className + " instances");
		for (@SuppressWarnings("rawtypes")
		Iterator localIterator = instances.iterator(); localIterator.hasNext();) {
			@SuppressWarnings("unchecked")
			T instance = (T) localIterator.next();
			getCurrentSession().update(instance);
		}

	}

	@Override
	public void saveOrUpdateAll(Collection<T> instances) {
		String className = instances.getClass().getName();
		log.debug("saveOrUpdateAll " + className + " instances");
		for (@SuppressWarnings("rawtypes")
		Iterator localIterator = instances.iterator(); localIterator.hasNext();) {
			@SuppressWarnings("unchecked")
			T instance = (T) localIterator.next();
			getCurrentSession().saveOrUpdate(instance);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T find(CharSequence queryString, Map<String, ? extends Object> params) {
		Query qry = getCurrentSession().createQuery(queryString.toString());
		setParameter(qry, params);
		@SuppressWarnings("rawtypes")
		List list = qry.setMaxResults(1).list();
		if (list.isEmpty())
			return null;
		return (T) list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findList(CharSequence queryString, Object... params) {
		Query query = getCurrentSession().createQuery(queryString.toString());
		for (int i = 0; i < params.length; ++i) {
			query.setParameter(i, params[i]);
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findList(CharSequence queryString, Map<String, ? extends Object> params) {
		Query query = getCurrentSession().createQuery(queryString.toString());
		setParameter(query, params);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(Class<T> clazz, Serializable id) {
		String className = clazz.getName();
		log.debug("getting " + className + " instance with id: " + id);
		try {
			T instance = (T) getCurrentSession().get(clazz, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<T> findListByProperty(String name, Object value) {
		String hql = "from  " + entityClazz.getSimpleName() + " where " + name + "=? ";
		return findList(hql, value);
	}

	@Override
	public T findByProperty(Map<String, ? extends Object> conditionMap) {
		List<T> results = findListByProperty(conditionMap);
		if (results != null && results.size() > 0) {
			return results.get(0);
		}
		return null;
	}

	@Override
	public T findByProperty(String name, Object value) {
		List<T> results = findListByProperty(name, value);
		if (results != null && results.size() > 0) {
			return results.get(0);
		}
		return null;
	}

	@Override
	public List<T> findListByProperty(Map<String, ? extends Object> conditionMap) {
		StringBuilder hql = new StringBuilder();
		hql.append("from  " + entityClazz.getSimpleName());
		if (!conditionMap.isEmpty()) {
			Iterator<String> it = conditionMap.keySet().iterator();
			String key = it.next();
			hql.append(" where  " + key + "=:" + key);
			while (it.hasNext()) {
				key = it.next();
				hql.append(" and  " + key + "=:" + key);
			}
		}
		return findList(hql.toString(), conditionMap);
	}

	protected Query setParameter(Query query, Map<String, ? extends Object> parameterMap) {
		for (@SuppressWarnings("rawtypes")
		Iterator iterator = parameterMap.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			query.setParameter(key, parameterMap.get(key));
		}
		return query;
	}

	@Override
	public Boolean isExist(Map<String, ? extends Object> conditionMap) {
		return findByProperty(conditionMap) == null ? false : true;
	}

	@Override
	public Boolean isExist(String name, Object value) {
		return findByProperty(name, value) == null ? false : true;
	}

	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger log = LoggerFactory.getLogger(BaseDAO.class);
}
