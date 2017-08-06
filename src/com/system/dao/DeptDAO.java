package com.system.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import com.base.dao.BaseDAO;
import com.system.model.DepartmentModel;

public class DeptDAO extends BaseDAO {

	public List<DepartmentModel> getDeptList() {
		return getHibernateTemplate().find("from DepartmentModel d order by d.orderCode");
	}

	public DepartmentModel getDeptModelById(String id) {
		return getHibernateTemplate().get(DepartmentModel.class, id);
	}

	public void updateDept(DepartmentModel deptModel) {
		deptModel.setUpdateTime(new Date());
		getHibernateTemplate().update(deptModel);
	}

	public void saveDept(DepartmentModel deptModel) {
		deptModel.setInsertTime(new Date());
		deptModel.setUpdateTime(new Date());
		getHibernateTemplate().save(deptModel);
	}

	public List<DepartmentModel> getDeptListByParent(DepartmentModel parent) {
		if(parent==null){
			String hql="from DepartmentModel d where d.parent is null order by d.orderCode";
			Query query=getSession().createQuery(hql);
			return query.list();
		}else{
			String hql="from DepartmentModel d where d.parent is :parent order by d.orderCode";
			Query query=getSession().createQuery(hql);
			query.setParameter("parent", parent);
			return query.list();
		}
	}
	
	public void deleteDept(String id) {
		String hql="delete from DepartmentModel ddm where ddm.id=:id";
		Query query=getSession().createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
	}

}
