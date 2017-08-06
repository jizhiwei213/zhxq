package com.lonewolf.jcxx.service;

import java.util.List;

import com.lonewolf.jcxx.dao.GsqUserDAO;
import com.system.model.UserModel;
import com.system.query.UserQuery;

public class GsqUserService {
	private GsqUserDAO gsqUserDAO;
	
	public GsqUserDAO getGsqUserDAO()
	{
		return gsqUserDAO;
	}

	public void setGsqUserDAO(GsqUserDAO gsqUserDAO)
	{
		this.gsqUserDAO = gsqUserDAO;
	}

	public UserModel findUserModelByCode(String userAccount) {
		return gsqUserDAO.findUserModelByCode(userAccount);
	}
	
	public List<UserModel> getUserList(Integer start, Integer rows,UserQuery userQuery) {
		return gsqUserDAO.getUserList(start,rows,userQuery);
	}

	public int getUserCount(UserQuery userQuery) {
		return gsqUserDAO.getUserCount(userQuery);
	}
	public List<UserModel> getUserSjList(Integer start, Integer rows,UserQuery userQuery) {
		return gsqUserDAO.getUserSjList(start,rows,userQuery);
	}

	public int getUserSjCount(UserQuery userQuery) {
		return gsqUserDAO.getUserSjCount(userQuery);
	}

	public UserModel getUserModelById(String id) {
		return gsqUserDAO.getUserModelById(id);
	}

	public void saveUser(UserModel userModel) {
		gsqUserDAO.saveUser(userModel);
	}

	public void updateUser(UserModel userModel) {
		gsqUserDAO.updateUser(userModel);
	}
	
	public void deleteUser(String id) {
		gsqUserDAO.deleteUser(id);
	}
	
	public void updateUserPasswordById(String userId,String newPwd) {
		gsqUserDAO.updateUserPasswordById(userId,newPwd);
	}
	
	public List<UserModel> getUserListByUserType(Integer start, Integer rows,String userTypeId,UserQuery userQuery) {
		return gsqUserDAO.getUserListByUserType(start,rows,userTypeId,userQuery);
	}

	public int getUserCountByUserType(String userTypeId,UserQuery userQuery) {
		return gsqUserDAO.getUserCountByUserType(userTypeId,userQuery);
	}
	
	public UserModel getUserModelByUserno(String userno) {
		return gsqUserDAO.getUserModelByUserno(userno);
	}
	public List getUserSjjxsList(String userid,String userno) {
		List lll = gsqUserDAO.getUserSjjxsList(userid,userno);
		return lll;
	}
	
	public List<UserModel> getUserXjjxsList(String userId) {
		List lll = gsqUserDAO.getUserXjjxsList(userId);
		return lll;
	}
}
