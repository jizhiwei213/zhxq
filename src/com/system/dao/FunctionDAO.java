package com.system.dao;

import java.util.List;

import org.hibernate.Query;

import com.base.dao.BaseDAO;
import com.system.model.FunctionModel;
import com.system.model.RoleModel;

public class FunctionDAO extends BaseDAO {

	public List<FunctionModel> findAuthTopFunctionModelList(String userId) {
		String hql="select rfrm.fun from RoleFunRelModel rfrm where rfrm.fun.parent.id is null and exists(from UserRoleRelModel urrm where urrm.user.id=:userId and urrm.role.id=rfrm.role.id) order by rfrm.fun.orderCode";
		Query query=getSession().createQuery(hql);
		query.setString("userId", userId);
		return query.list();
	}

	public List<FunctionModel> findAuthChildrenFunctionModelList(String userId, String parentId) {
		String hql="select rfrm.fun from RoleFunRelModel rfrm where rfrm.fun.parent.id =:parentId and exists(from UserRoleRelModel urrm where urrm.user.id=:userId and urrm.role.id=rfrm.role.id) order by rfrm.fun.orderCode";
		Query query=getSession().createQuery(hql);
		query.setString("userId", userId);
		query.setString("parentId",parentId);
		return query.list();
	}

	public List<FunctionModel> getFunctionListByParent(FunctionModel parent) {
		if(parent==null){
			String hql="from FunctionModel f where f.parent is null order by f.orderCode";
			Query query=getSession().createQuery(hql);
			return query.list();
		}else{
			String hql="from FunctionModel f where f.parent is :parent order by f.orderCode";
			Query query=getSession().createQuery(hql);
			query.setParameter("parent", parent);
			return query.list();
		}
	}

	public List<String> getLastLevelFunctionListByRole(RoleModel roleModel) {
		return getHibernateTemplate().find("select rf.fun.id from RoleFunRelModel rf where rf.role= ? and rf.fun not in (select f.parent from FunctionModel f)",roleModel);
	}

	public List<FunctionModel> getAllFunction() {
		return getHibernateTemplate().find("from FunctionModel fm order by fm.orderCode");
	}

	public List<String> getFunctionIdListByRole(RoleModel roleModel) {
		return getHibernateTemplate().find("select rf.fun.id from RoleFunRelModel rf where rf.role=?",roleModel);
	}

}
