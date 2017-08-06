package com.system.service;

import java.util.ArrayList;
import java.util.List;

import com.system.dao.OperationDAO;
import com.system.model.FunctionModel;
import com.system.model.OperationModel;
import com.system.model.RoleModel;

public class OperationService {
	private OperationDAO operationDAO;

	public void setOperationDAO(OperationDAO operationDAO) {
		this.operationDAO = operationDAO;
	}

	public List<String> findOperationCodeListByUser(String userId) {
		List<OperationModel> list=operationDAO.findOperationListByUser(userId);
		List<String> codeList=new ArrayList<String>();
		for (OperationModel operation : list) {
			codeList.add(operation.getCode());
		}
		return codeList;
	}

	public List<OperationModel> findOperationListByFunction(FunctionModel functionModel) {
		return operationDAO.findOperationListByFunction(functionModel);
	}

	public List<String> getOperationIdListByRole(RoleModel roleModel) {
		return operationDAO.getOperationIdListByRole(roleModel);
	}
}
