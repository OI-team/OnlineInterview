package edu.nju.onlineInterview.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 
 * @author margine
 * @description
 * @createTime 2015��11��15������3:33:25
 * @contact ch_margine@163.com
 */

public class BaseDAO<T> extends HibernateDaoSupport implements IBaseDAO<T> {

	@Override
	public void save(T t) {
		String className = t.getClass().getName();
		log.debug("save " + className + " instance");
		try {
			getHibernateTemplate().save(t);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public T merge(T t) {
		String className = t.getClass().getName();
		log.debug("merge " + className + " instance");
		try {
			T result = getHibernateTemplate().merge(t);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(T t) {
		String className = t.getClass().getName();
		log.debug("attaching dirty " + className + " instance");
		try {
			getHibernateTemplate().saveOrUpdate(t);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}

	}

	@Override
	public void attachClean(T t) {
		String className = t.getClass().getName();
		log.debug("attaching dirty " + className + " instance");
		try {
			getHibernateTemplate().lock(t, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}

	}

	@Override
	public void delete(T t) {
		String className = t.getClass().getName();
		log.debug("deleting " + className + " instance");
		try {
			getHibernateTemplate().delete(t);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}

	}

	@Override
	public void deleteByProperty(Class<T> clazz, String propertyName, Object value) {
		String className = clazz.getName();
		log.debug("deleting " + className + " instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "delete from " + className + " as model where model." + propertyName + "= ?";
			getHibernateTemplate().bulkUpdate(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}

	}

	@Override
	public T findById(Class<T> clazz, Serializable id) {
		String className = clazz.getName();
		log.debug("getting " + className + " instance with id: " + id);
		try {
			T instance = getHibernateTemplate().get(clazz, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByExample(T t){
		String className = t.getClass().getName();
		log.debug("finding " + className + " instance by example");
		try {
			List<T> results = getHibernateTemplate()
					.findByExample(t);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByProperty(Class<T> clazz, String propertyName, Object value) {
		String className = clazz.getName();
		log.debug("finding " + className + " instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from " + className + " as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Class<T> clazz) {
		String className = clazz.getName();
		log.debug("finding all " + className + " instances");
		try {
			String queryString = "from " + className;
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	private static final Logger log = LoggerFactory.getLogger(BaseDAO.class);
}
