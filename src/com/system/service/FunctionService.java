package com.system.service;

import java.util.List;

import com.system.dao.FunctionDAO;
import com.system.model.FunctionModel;
import com.system.model.RoleModel;

public class FunctionService {
	private FunctionDAO functionDAO;
	
	public void setFunctionDAO(FunctionDAO functionDAO) {
		this.functionDAO = functionDAO;
	}
	
	public List<FunctionModel> findAuthTopFunctionModelList(String userId) {
		return functionDAO.findAuthTopFunctionModelList(userId);
	}
	
	public List<FunctionModel> findAuthChildrenFunctionModelList(String userId, String parentId) {
		return functionDAO.findAuthChildrenFunctionModelList(userId, parentId);
	}
	

	public List<FunctionModel> getFunctionListByParent(FunctionModel parent) {
		return functionDAO.getFunctionListByParent(parent);
	}

	public List<String> getLastLevelFunctionListByRole(RoleModel roleModel) {
		return functionDAO.getLastLevelFunctionListByRole(roleModel);
	}

	public List<FunctionModel> getAllFunction() {
		return functionDAO.getAllFunction();
	}

	public List<String> getFunctionIdListByRole(RoleModel roleModel) {
		return functionDAO.getFunctionIdListByRole(roleModel);
	}

}
