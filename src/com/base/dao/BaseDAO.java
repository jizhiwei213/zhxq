package com.base.dao;

import java.io.Serializable;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDAO extends HibernateDaoSupport {

	protected boolean saveObject(Object obj) {
		try {
			if (obj != null) {
				getHibernateTemplate().save(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	protected boolean updateObject(Object obj) {
		try {
			if (obj != null) {
				getHibernateTemplate().update(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

//	protected boolean saveOrUpdateObject(Object obj) {
//		try {
//			if (obj != null) {
//				getHibernateTemplate().merge(obj);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}

	protected Object findObject(Class clazz, Serializable id) {
		try {
			if (id != null) {
				return getHibernateTemplate().get(clazz, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected boolean delObject(Class clazz, Serializable id) {
		try {
			getHibernateTemplate().delete(findObject(clazz, id));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	protected void clearSession() {
		getSession().flush();
		getSession().clear();
	}

}
