package com.lonewolf.jcxx.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.base.dao.BaseDAO;
import com.system.model.UserModel;
import com.system.query.UserQuery;

public class GsqUserDAO extends BaseDAO {

	public List<UserModel> getUserList(Integer start, Integer rows,UserQuery userQuery) {
		String hql ="from UserModel u where 1=1 ";
		if(null!=userQuery)
		{
			if(null!=userQuery.getUserName()&&!"".equals(userQuery.getUserName()))
			{
				hql+=" and u.userName like '%"+userQuery.getUserName()+"%'";
			}
			if(null!=userQuery.getUserTel()&&!"".equals(userQuery.getUserTel()))
			{
				hql+=" and u.linkTel like '%"+userQuery.getUserTel()+"%'";
			}
			if(null!=userQuery.getUserCode()&&!"".equals(userQuery.getUserCode()))
			{
				hql+=" and u.sfzhm like '%"+userQuery.getUserCode()+"%'";
			}
			if(null!=userQuery.getUserType()&&!"".equals(userQuery.getUserType()))
			{
				hql+=" and u.usertype = '"+userQuery.getUserType()+"'";
			}
			if(null!=userQuery.getStatus()&&!"".equals(userQuery.getStatus()))
			{
				hql+=" and u.status = '"+userQuery.getStatus()+"'";
			}
			if(null!=userQuery.getUserstatus()&&!"".equals(userQuery.getUserstatus()))
			{
				hql+=" and u.userstatus = '"+userQuery.getUserstatus()+"'";
			}
			if(null!=userQuery.getNotUserId()&&!"".equals(userQuery.getNotUserId()))
			{
				hql+=" and u.id <> '"+userQuery.getNotUserId()+"'";
			}
			if(null!=userQuery.getUserno()&&!"".equals(userQuery.getUserno()))
			{
				hql+=" and u.userno like '%"+userQuery.getUserno()+"%'";
			}
			if(null!=userQuery.getUserceng()&&!"".equals(userQuery.getUserceng()))
			{
				hql+=" and u.userceng like '"+userQuery.getUserceng()+"%'";
			}
		}
		System.out.println(hql);
		Query query=getSession().createQuery(hql);
		if(start!=null&&rows!=null){
			query.setFirstResult(start);
			query.setMaxResults(rows);
		}
		return query.list();
	}

	public int getUserCount(UserQuery userQuery) {
		String hql ="select count(*) from UserModel u where 1=1 ";
		if(null!=userQuery)
		{
			if(null!=userQuery.getUserName()&&!"".equals(userQuery.getUserName()))
			{
				hql+=" and u.userName like '%"+userQuery.getUserName()+"%'";
			}
			if(null!=userQuery.getUserTel()&&!"".equals(userQuery.getUserTel()))
			{
				hql+=" and u.linkTel like '%"+userQuery.getUserTel()+"%'";
			}
			if(null!=userQuery.getUserCode()&&!"".equals(userQuery.getUserCode()))
			{
				hql+=" and u.sfzhm like '%"+userQuery.getUserCode()+"%'";
			}
			if(null!=userQuery.getUserType()&&!"".equals(userQuery.getUserType()))
			{
				hql+=" and u.usertype = '"+userQuery.getUserType()+"'";
			}
			if(null!=userQuery.getStatus()&&!"".equals(userQuery.getStatus()))
			{
				hql+=" and u.status = '"+userQuery.getStatus()+"'";
			}
			if(null!=userQuery.getUserstatus()&&!"".equals(userQuery.getUserstatus()))
			{
				hql+=" and u.userstatus = '"+userQuery.getUserstatus()+"'";
			}
			if(null!=userQuery.getNotUserId()&&!"".equals(userQuery.getNotUserId()))
			{
				hql+=" and u.id <> '"+userQuery.getNotUserId()+"'";
			}
			if(null!=userQuery.getUserno()&&!"".equals(userQuery.getUserno()))
			{
				hql+=" and u.userno like '%"+userQuery.getUserno()+"%'";
			}
			if(null!=userQuery.getUserceng()&&!"".equals(userQuery.getUserceng()))
			{
				hql+=" and u.userceng like '"+userQuery.getUserceng()+"%'";
			}
		}
		System.out.println(hql);
		Query query=getSession().createQuery(hql);
		return Integer.parseInt(query.uniqueResult()+"");
	}

	public UserModel getUserModelById(String id) {
		return getHibernateTemplate().get(UserModel.class, id);
	}

	public void saveUser(UserModel userModel) {
		userModel.setInsertTime(new Date());
		userModel.setUpdateTime(new Date());
		getHibernateTemplate().save(userModel);
	}

	public void updateUser(UserModel userModel) {
		userModel.setUpdateTime(new Date());
		getHibernateTemplate().update(userModel);
	}

	public void deleteUser(String id) {
		String hql2 = "delete from UserRoleRelModel ur where ur.user.id = :id";
		Query query2=getSession().createQuery(hql2);
		query2.setParameter("id", id);
		query2.executeUpdate();
		String hql="delete from UserModel ddm where ddm.id=:id";
		Query query=getSession().createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	public void updateUserPasswordById(String userId, String newPwd) {
		String hql="update UserModel u set u.userPassword=:newPwd where u.id=:userId";
		Query query2=getSession().createQuery(hql);
		query2.setParameter("userId", userId);
		query2.setParameter("newPwd", newPwd);
		query2.executeUpdate();
	}
	
	public UserModel findUserModelByCode(String userAccount) {
		String hql="from UserModel um where um.userAccount=:userAccount and um.isStop=0";
		Query query=getSession().createQuery(hql);
		query.setString("userAccount", userAccount);
		Object obj=query.uniqueResult();
		if(obj!=null)
		{
			return (UserModel) obj;
		}else{
			return null;
		}
	}
	
	public List<UserModel> getUserListByUserType(Integer start, Integer rows,String userTypeId,UserQuery userQuery) {
		String hql ="from UserModel us where 1=1 ";
		if(null!=userTypeId)
		{
			hql+=" and us.userType.id='"+userTypeId+"'  ";
		}
		if(null!=userQuery)
		{
			if(null!=userQuery.getUserName())
			{
				hql+=" and us.userName like '%"+userQuery.getUserName()+"%'";
			}
			if(null!=userQuery.getStudyCode())
			{
				hql+=" and us.studyCode like '%"+userQuery.getStudyCode()+"%'";
			}
		}
		Query query=getSession().createQuery(hql);
		if(start!=null&&rows!=null){
			query.setFirstResult(start);
			query.setMaxResults(rows);
		}
		return query.list();
	}

	public int getUserCountByUserType(String userTypeId,UserQuery userQuery) {
		String hql ="select count(*) from UserModel u where 1=1 ";
		if(null!=userTypeId)
		{
			hql+=" and u.userType.id='"+userTypeId+"'  ";
		}
		if(null!=userQuery)
		{
			if(null!=userQuery.getUserName())
			{
				hql+=" and u.userName like '%"+userQuery.getUserName()+"%'";
			}
		}
		Query query=getSession().createQuery(hql);
		return Integer.parseInt(query.uniqueResult()+"");
	}
	
	public UserModel getUserModelByUserno(String userno) {
		String hql="from UserModel um where um.userno=:userno and um.usertype='2' ";
		Query query=getSession().createQuery(hql);
		query.setString("userno", userno);
		Object obj=query.uniqueResult();
		if(obj!=null)
		{
			return (UserModel) obj;
		}else{
			return null;
		}
	}
	
	
	public List<UserModel> getUserSjList(Integer start, Integer rows,UserQuery userQuery) {
		String hql ="from UserModel u where 1=1 ";
		if(null!=userQuery)
		{
			if(null!=userQuery.getUserName()&&!"".equals(userQuery.getUserName()))
			{
				hql+=" and u.userName like '%"+userQuery.getUserName()+"%'";
			}
			if(null!=userQuery.getUserTel()&&!"".equals(userQuery.getUserTel()))
			{
				hql+=" and u.linkTel like '%"+userQuery.getUserTel()+"%'";
			}
			if(null!=userQuery.getUserCode()&&!"".equals(userQuery.getUserCode()))
			{
				hql+=" and u.sfzhm like '%"+userQuery.getUserCode()+"%'";
			}
			if(null!=userQuery.getUserType()&&!"".equals(userQuery.getUserType()))
			{
				hql+=" and u.usertype = '"+userQuery.getUserType()+"'";
			}
			if(null!=userQuery.getStatus()&&!"".equals(userQuery.getStatus()))
			{
				hql+=" and u.status = '"+userQuery.getStatus()+"'";
			}
			if(null!=userQuery.getUserstatus()&&!"".equals(userQuery.getUserstatus()))
			{
				hql+=" and u.userstatus = '"+userQuery.getUserstatus()+"'";
			}
			if(null!=userQuery.getNotUserId()&&!"".equals(userQuery.getNotUserId()))
			{
				hql+=" and u.id <> '"+userQuery.getNotUserId()+"'";
			}
			if(null!=userQuery.getUserno()&&!"".equals(userQuery.getUserno()))
			{
				hql+=" and u.userno like '%"+userQuery.getUserno()+"%'";
			}
			if(null!=userQuery.getUserno()&&!"".equals(userQuery.getUserno()))
			{
				hql+=" and u.userno like '%"+userQuery.getUserno()+"%'";
			}
			hql+=" and DATE_FORMAT(u.insertTime, '%Y-%m-%d') ='"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"'";
			hql+=" and u.id not in (select m.sjjxs.id from UserModel m where m.sjjxs is not null)";
		}
		Query query=getSession().createQuery(hql);
		if(start!=null&&rows!=null){
			query.setFirstResult(start);
			query.setMaxResults(rows);
		}
		return query.list();
	}

	public int getUserSjCount(UserQuery userQuery) {
		String hql ="select count(*) from UserModel u where 1=1 ";
		if(null!=userQuery)
		{
			if(null!=userQuery.getUserName()&&!"".equals(userQuery.getUserName()))
			{
				hql+=" and u.userName like '%"+userQuery.getUserName()+"%'";
			}
			if(null!=userQuery.getUserTel()&&!"".equals(userQuery.getUserTel()))
			{
				hql+=" and u.linkTel like '%"+userQuery.getUserTel()+"%'";
			}
			if(null!=userQuery.getUserCode()&&!"".equals(userQuery.getUserCode()))
			{
				hql+=" and u.sfzhm like '%"+userQuery.getUserCode()+"%'";
			}
			if(null!=userQuery.getUserType()&&!"".equals(userQuery.getUserType()))
			{
				hql+=" and u.usertype = '"+userQuery.getUserType()+"'";
			}
			if(null!=userQuery.getStatus()&&!"".equals(userQuery.getStatus()))
			{
				hql+=" and u.status = '"+userQuery.getStatus()+"'";
			}
			if(null!=userQuery.getUserstatus()&&!"".equals(userQuery.getUserstatus()))
			{
				hql+=" and u.userstatus = '"+userQuery.getUserstatus()+"'";
			}
			if(null!=userQuery.getNotUserId()&&!"".equals(userQuery.getNotUserId()))
			{
				hql+=" and u.id <> '"+userQuery.getNotUserId()+"'";
			}
			if(null!=userQuery.getUserno()&&!"".equals(userQuery.getUserno()))
			{
				hql+=" and u.userno like '%"+userQuery.getUserno()+"%'";
			}
			hql+=" and DATE_FORMAT(u.insertTime, '%Y-%m-%d') ='"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"'";
			hql+=" and u.id not in (select m.sjjxs.id from UserModel m where m.sjjxs is not null)";
		}
		Query query=getSession().createQuery(hql);
		return Integer.parseInt(query.uniqueResult()+"");
	}
	
	/**
	 * 获取上级经销商
	 * @return
	 */
	public List getUserSjjxsList(String userId,String userno) {
		String hql ="SELECT u.id,u.user_name FROM tb_user u WHERE 1=1 AND u.user_type = '2' and u.id<>'"+userId+"' AND u.status = '0' and";
		hql+=" (u.id NOT IN (SELECT m.sjjxs_id FROM tb_user m WHERE sjjxs_id IS NOT NULL) ";
		hql+=" OR u.id IN (SELECT id FROM tb_user WHERE sjjxs_id IN (SELECT sjjxs_id FROM tb_user GROUP BY sjjxs_id HAVING COUNT(1) <2))) ";
		hql+=" and u.user_no='"+userno+"' ";
		SQLQuery query=getSession().createSQLQuery(hql);
		List list = query.list();
		return list;
	}
	
	public List<UserModel> getUserXjjxsList(String userId) {
		String hql ="from UserModel user where user.sjjxs.id='"+userId+"' ";
		Query query=getSession().createQuery(hql);
		return query.list();
	}
}
