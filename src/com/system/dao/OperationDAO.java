package com.system.dao;

import java.util.List;

import org.hibernate.Query;

import com.base.dao.BaseDAO;
import com.system.model.FunctionModel;
import com.system.model.OperationModel;
import com.system.model.RoleModel;

public class OperationDAO extends BaseDAO {

	public List<OperationModel> findOperationListByUser(String userId) {
		String hql="select ror.operation from RoleOperRelModel ror where ror.role = (select urr.role from UserRoleRelModel urr where urr.user.id=:userId)";
		Query query=getSession().createQuery(hql);
		query.setString("userId", userId);
		return query.list();
	}

	public List<OperationModel> findOperationListByFunction(FunctionModel functionModel) {
		return getHibernateTemplate().find("from OperationModel om where om.function=?", functionModel);
	}

	public List<String> getOperationIdListByRole(RoleModel roleModel) {
		return getHibernateTemplate().find("select ror.operation.id from RoleOperRelModel ror where ror.role=?",roleModel);
	}

}
