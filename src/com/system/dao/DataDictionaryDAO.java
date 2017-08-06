package com.system.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import com.base.dao.BaseDAO;
import com.system.model.DataDictionaryModel;

public class DataDictionaryDAO extends BaseDAO {

	public List<DataDictionaryModel> getDateDictionaryListByDataType(String dataType) {
		if(dataType==null){
			return null;
		}else{
			return getHibernateTemplate().find("from DataDictionaryModel ddm where ddm.dataType=? and ddm.parent.id is not null",dataType);
		}
	}
	
	public List<DataDictionaryModel> getDataDictionaryList(String dataType, Integer start, Integer rows) {
		String hql = " from DataDictionaryModel ddm where ddm.dataType=:dataType and ddm.parent.id is not null ";
		Query query = getSession().createQuery(hql);
		query.setParameter("dataType", dataType);
		if(start!=null&&rows!=null){
			query.setFirstResult(start);
			query.setMaxResults(rows);
		}
		return query.list();
	}

	public int getDataDictionaryCount(String dataType) {
		String hql = "select count(*) from DataDictionaryModel ddm where ddm.dataType=:dataType and ddm.parent.id is not null ";
		Query query = getSession().createQuery(hql);
		query.setParameter("dataType", dataType);
		return Integer.parseInt(query.uniqueResult()+"");
	}

	public DataDictionaryModel getDataDictionaryById(String id) {
		return getHibernateTemplate().get(DataDictionaryModel.class, id);
	}

	
	public DataDictionaryModel getParentByDataType(String dataType) {
		String hql = "from DataDictionaryModel ddm where ddm.dataType=:dataType and ddm.parent is null ";
		Query query = getSession().createQuery(hql);
		query.setParameter("dataType", dataType);
		return (DataDictionaryModel) query.uniqueResult();
	}

	public void updateDataDictionary(DataDictionaryModel dataDictionary) {
		dataDictionary.setUpdateTime(new Date());
		getHibernateTemplate().update(dataDictionary);
	}

	public void saveDataDictionary(DataDictionaryModel dataDictionary) {
		dataDictionary.setInsertTime(new Date());
		dataDictionary.setUpdateTime(new Date());
		getHibernateTemplate().save(dataDictionary);
	}

	public void deleteDataDictionary(String id) {
		String hql="delete from DataDictionaryModel ddm where ddm.id=:id";
		Query query=getSession().createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	public DataDictionaryModel getDataDictionaryByNameAndDataType(String name,String dataType) {
		String hql = "from DataDictionaryModel ddm where ddm.dataType=:dataType and ddm.name =:name ";
		Query query = getSession().createQuery(hql);
		query.setParameter("dataType", dataType);
		query.setParameter("name", name);
		return (DataDictionaryModel) query.uniqueResult();
	}
}
