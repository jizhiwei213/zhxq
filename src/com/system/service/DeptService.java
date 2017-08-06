package com.system.service;

import java.util.List;

import com.system.dao.DeptDAO;
import com.system.model.DepartmentModel;

public class DeptService {
	private DeptDAO deptDAO;

	public void setDeptDAO(DeptDAO deptDAO) {
		this.deptDAO = deptDAO;
	}

	public List<DepartmentModel> getDeptList() {
		return deptDAO.getDeptList();
	}

	public DepartmentModel getDeptModelById(String id) {
		return deptDAO.getDeptModelById(id);
	}

	public void updateDept(DepartmentModel deptModel) {
		deptDAO.updateDept(deptModel);
	}

	public void saveDept(DepartmentModel deptModel) {
		deptDAO.saveDept(deptModel);
	}

	public List<DepartmentModel> getDeptListByParent(DepartmentModel parent) {
		return deptDAO.getDeptListByParent(parent);
	}
	
	public void deleteDept(String id) {
		deptDAO.deleteDept(id);
	}

}
