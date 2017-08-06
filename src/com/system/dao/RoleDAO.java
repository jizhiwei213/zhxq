package com.system.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import com.base.dao.BaseDAO;
import com.system.model.RoleFunRelModel;
import com.system.model.RoleModel;
import com.system.model.RoleOperRelModel;
import com.system.model.UserModel;
import com.system.model.UserRoleRelModel;

public class RoleDAO extends BaseDAO {

	public List<RoleModel> getRoleList(Integer start, Integer rows) {
		Query query=getSession().createQuery("from RoleModel");
		if(start!=null&&rows!=null){
			query.setFirstResult(start);
			query.setMaxResults(rows);
		}
		return query.list();
	}

	public int getRoleCount() {
		Query query=getSession().createQuery("select count(*) from RoleModel r");
		return Integer.parseInt(query.uniqueResult()+"");
	}

	public RoleModel getRoleModelById(String id) {
		return getHibernateTemplate().get(RoleModel.class, id);
	}

	public void saveRole(RoleModel roleModel) {
		roleModel.setInsertTime(new Date());
		roleModel.setUpdateTime(new Date());
		getHibernateTemplate().save(roleModel);
	}
	
	public void updateRole(RoleModel roleModel){
		roleModel.setUpdateTime(new Date());
		getHibernateTemplate().update(roleModel);
	}

	public void deleteRoleFunRelByRole(RoleModel roleModel) {
		String hql = "delete from RoleFunRelModel r where r.role = :roleModel";
		Query query=getSession().createQuery(hql);
		query.setParameter("roleModel", roleModel);
		query.executeUpdate();
	}
	
	public void saveRoleFunRel(RoleFunRelModel roleFunRel) {
		getHibernateTemplate().save(roleFunRel);
	}

	public List<RoleModel> getUserRoleByUserModel(UserModel userModel) {
		String hql="select ur.role from UserRoleRelModel ur where ur.user=:userModel";
		Query query=getSession().createQuery(hql);
		query.setParameter("userModel", userModel);
		return query.list();
	}

	public void deleteUserRoleByUserModel(UserModel userModel) {
		String hql = "delete from UserRoleRelModel ur where ur.user = :userModel";
		Query query=getSession().createQuery(hql);
		query.setParameter("userModel", userModel);
		query.executeUpdate();
	}

	public void addUserRoleRelModel(UserRoleRelModel userRoleRel) {
		getHibernateTemplate().save(userRoleRel);
	}

	public void deleteRole(String id) {
		String hql="delete from RoleModel ddm where ddm.id=:id";
		Query query=getSession().createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	public void deleteRoleOperationByRole(RoleModel roleModel) {
		String hql = "delete from RoleOperRelModel r where r.role = :roleModel";
		Query query=getSession().createQuery(hql);
		query.setParameter("roleModel", roleModel);
		query.executeUpdate();
	}

	public void saveRoleOperRel(RoleOperRelModel roleOper) {
		getHibernateTemplate().save(roleOper);
	}
	
	public List<RoleModel> getRoleListByXtyh(Integer start, Integer rows) {
		Query query=getSession().createQuery("from RoleModel rol where rol.id!='402880ee48592bf80148592c8fb10000' and rol.id!='ff8080815368d2d3015368d3f7600000' ");
		if(start!=null&&rows!=null){
			query.setFirstResult(start);
			query.setMaxResults(rows);
		}
		return query.list();
	}
	
	public List<RoleModel> getRoleListByRoleId(Integer start, Integer rows,String rolid) {
		Query query=getSession().createQuery("from RoleModel rol where rol.id='"+rolid+"' ");
		if(start!=null&&rows!=null){
			query.setFirstResult(start);
			query.setMaxResults(rows);
		}
		return query.list();
	}
	
	
	public List<RoleModel> getRoleListByRoleId2(Integer start, Integer rows,String rolid,String rolid2) {
		Query query=getSession().createQuery("from RoleModel rol where rol.id='"+rolid+"' or rol.id='"+rolid2+"' ");
		if(start!=null&&rows!=null){
			query.setFirstResult(start);
			query.setMaxResults(rows);
		}
		return query.list();
	}
}
