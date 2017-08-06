package com.system.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.Query;

import com.base.dao.BaseDAO;
import com.common.util.EncryptKey;
import com.system.model.DataDictionaryModel;
import com.system.model.DepartmentModel;
import com.system.model.RoleModel;
import com.system.model.UserModel;
import com.system.model.UserRoleRelModel;
import com.system.query.UserQuery;

public class UserDAO extends BaseDAO
{

	public UserModel findUserModelByLogin(String userAccount, String userPassword)
	{
		String hql = "from UserModel um where um.userAccount=:userAccount and um.userPassword=:userPassword and um.isStop=0";
		Query query = getSession().createQuery(hql);
		query.setString("userAccount", userAccount);
		query.setString("userPassword", userPassword);
		Object obj = query.uniqueResult();
		if (obj != null)
		{
			return (UserModel) obj;
		}
		else
		{
			return null;
		}
	}

	public List<UserModel> getUserList(Integer start, Integer rows, UserQuery userQuery)
	{
		String hql = "from UserModel u where 1=1 ";
		if (null != userQuery)
		{
			if (null != userQuery.getUserName())
			{
				hql += " and u.userName like '%" + userQuery.getUserName() + "%'";
			}
		}
		Query query = getSession().createQuery(hql);
		if (start != null && rows != null)
		{
			query.setFirstResult(start);
			query.setMaxResults(rows);
		}
		return query.list();
	}

	public int getUserCount(UserQuery userQuery)
	{
		String hql = "select count(*) from UserModel u where 1=1 ";
		if (null != userQuery)
		{
			if (null != userQuery.getUserName())
			{
				hql += " and u.userName like '%" + userQuery.getUserName() + "%'";
			}
		}
		Query query = getSession().createQuery(hql);
		return Integer.parseInt(query.uniqueResult() + "");
	}

	public UserModel getUserModelById(String id)
	{
		return getHibernateTemplate().get(UserModel.class, id);
	}

	public void saveUser(UserModel userModel)
	{
		userModel.setInsertTime(new Date());
		userModel.setUpdateTime(new Date());
		getHibernateTemplate().save(userModel);
	}

	public void updateUser(UserModel userModel)
	{
		userModel.setUpdateTime(new Date());
		getHibernateTemplate().update(userModel);
	}

	public void deleteUser(String id)
	{
		String hql2 = "delete from UserRoleRelModel ur where ur.user.id = :id";
		Query query2 = getSession().createQuery(hql2);
		query2.setParameter("id", id);
		query2.executeUpdate();

		String hql = "delete from UserModel ddm where ddm.id=:id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	public void updateUserPasswordById(String userId, String newPwd)
	{
		String hql = "update UserModel u set u.userPassword=:newPwd where u.id=:userId";
		Query query2 = getSession().createQuery(hql);
		query2.setParameter("userId", userId);
		query2.setParameter("newPwd", newPwd);
		query2.executeUpdate();
	}

	public UserModel findUserModelByCode(String userAccount)
	{
		String hql = "from UserModel um where um.userAccount=:userAccount and um.isStop=0";
		Query query = getSession().createQuery(hql);
		query.setString("userAccount", userAccount);
		Object obj = query.uniqueResult();
		if (obj != null)
		{
			return (UserModel) obj;
		}
		else
		{
			return null;
		}
	}

	public List<UserModel> getUserListByUserType(Integer start, Integer rows, String userTypeId, UserQuery userQuery)
	{
		String hql = "from UserModel us where 1=1 ";
		if (null != userTypeId)
		{
			hql += " and us.usertype='" + userTypeId + "'  ";
			hql+=" and (us.sjjxs is not null or (us.sjjxs is null and us.tjr is null))";
		}
		if (null != userQuery)
		{
			if (null != userQuery.getUserName())
			{
				hql += " and us.userName like '%" + userQuery.getUserName() + "%'";
			}
			if (null != userQuery.getStudyCode())
			{
				hql += " and us.studyCode like '%" + userQuery.getStudyCode() + "%'";
			}
		}
		Query query = getSession().createQuery(hql);
		if (start != null && rows != null)
		{
			query.setFirstResult(start);
			query.setMaxResults(rows);
		}
		return query.list();
	}

	public int getUserCountByUserType(String userTypeId, UserQuery userQuery)
	{
		String hql = "select count(*) from UserModel u where 1=1 ";
		if (null != userTypeId)
		{
			hql += " and us.usertype='" + userTypeId + "'  ";
			hql+=" and (us.sjjxs is not null or (us.sjjxs is null and us.tjr is null))";
		}
		if (null != userQuery)
		{
			if (null != userQuery.getUserName())
			{
				hql += " and u.userName like '%" + userQuery.getUserName() + "%'";
			}
			if (null != userQuery.getStudyCode())
			{
				hql += " and u.studyCode like '%" + userQuery.getStudyCode() + "%'";
			}
		}
		Query query = getSession().createQuery(hql);
		return Integer.parseInt(query.uniqueResult() + "");
	}

	public UserModel findUserModelByUserNo(String userNo)
	{
		String hql = "from UserModel um where um.userno=:userno ";
		Query query = getSession().createQuery(hql);
		query.setString("userno", userNo);
		Object obj = query.uniqueResult();
		if (obj != null)
		{
			return (UserModel) obj;
		}
		else
		{
			return null;
		}
	}

}
