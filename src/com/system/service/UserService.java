package com.system.service;

import java.io.File;
import java.util.List;

import com.system.dao.UserDAO;
import com.system.model.DataDictionaryModel;
import com.system.model.UserModel;
import com.system.query.UserQuery;

public class UserService {
	private UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public UserModel findUserModelByLogin(String userAccount, String userPassword) {
		return userDAO.findUserModelByLogin(userAccount, userPassword);
	}

	public UserModel findUserModelByCode(String userAccount) {
		return userDAO.findUserModelByCode(userAccount);
	}
	
	public List<UserModel> getUserList(Integer start, Integer rows,UserQuery userQuery) {
		return userDAO.getUserList(start,rows,userQuery);
	}

	public int getUserCount(UserQuery userQuery) {
		return userDAO.getUserCount(userQuery);
	}

	public UserModel getUserModelById(String id) {
		return userDAO.getUserModelById(id);
	}

	public void saveUser(UserModel userModel) {
		userDAO.saveUser(userModel);
	}

	public void updateUser(UserModel userModel) {
		userDAO.updateUser(userModel);
	}
	
	public void deleteUser(String id) {
		userDAO.deleteUser(id);
	}
	
	public void updateUserPasswordById(String userId,String newPwd) {
		userDAO.updateUserPasswordById(userId,newPwd);
	}
	
	public List<UserModel> getUserListByUserType(Integer start, Integer rows,String userTypeId,UserQuery userQuery) {
		return userDAO.getUserListByUserType(start,rows,userTypeId,userQuery);
	}

	public int getUserCountByUserType(String userTypeId,UserQuery userQuery) {
		return userDAO.getUserCountByUserType(userTypeId,userQuery);
	}
	public UserModel findUserModelByUserNo(String userNo) {
		return userDAO.findUserModelByUserNo(userNo);
	}
	
}
