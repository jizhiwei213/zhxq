package com.system.service;

import java.util.List;

import com.system.dao.RoleDAO;
import com.system.model.FunctionModel;
import com.system.model.OperationModel;
import com.system.model.RoleFunRelModel;
import com.system.model.RoleModel;
import com.system.model.RoleOperRelModel;
import com.system.model.UserModel;
import com.system.model.UserRoleRelModel;

public class RoleService {
	private RoleDAO roleDAO;

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public List<RoleModel> getRoleList(Integer start, Integer rows) {
		return roleDAO.getRoleList(start, rows);
	}

	public int getRoleCount() {
		return roleDAO.getRoleCount();
	}

	public RoleModel getRoleModelById(String id) {
		return roleDAO.getRoleModelById(id);
	}
	public void deleteRole(String id) {
		roleDAO.deleteRole(id);
	}
	
	public void saveUpdateRole(RoleModel roleModel, String[] functionIds, String[] operationIds) {
		if("".equals(roleModel.getId())){
			roleDAO.saveRole(roleModel);
		}else{
			roleDAO.updateRole(roleModel);
		}
		//先删除该角色对应的全部功能，然后再添加
		roleDAO.deleteRoleFunRelByRole(roleModel);
		roleDAO.deleteRoleOperationByRole(roleModel);
		if(functionIds!=null){
			for (String funId : functionIds) {
				FunctionModel func=new FunctionModel();
				func.setId(funId);
				
				RoleFunRelModel roleFunRel=new RoleFunRelModel();
				roleFunRel.setFun(func);
				roleFunRel.setRole(roleModel);
				roleDAO.saveRoleFunRel(roleFunRel);
			}
		}
		if(operationIds!=null){
			for (String operId : operationIds) {
				OperationModel oper=new OperationModel();
				oper.setId(operId);
				
				RoleOperRelModel roleOper=new RoleOperRelModel();
				roleOper.setRole(roleModel);
				roleOper.setOperation(oper);
				roleDAO.saveRoleOperRel(roleOper);
			}
		}
	}

	public List<RoleModel> getUserRoleByUserModel(UserModel userModel) {
		return roleDAO.getUserRoleByUserModel(userModel);
	}

	public void saveUserRole(UserModel userModel, RoleModel roleModel) {
		roleDAO.deleteUserRoleByUserModel(userModel);
		
		if(userModel!=null&&roleModel!=null){
			UserRoleRelModel userRoleRel=new UserRoleRelModel();
			userRoleRel.setUser(userModel);
			userRoleRel.setRole(roleModel);
			roleDAO.addUserRoleRelModel(userRoleRel);
		}
	}

	/**
	 * 获取专用角色
	 * @param start
	 * @param rows
	 * @return
	 */
	public List<RoleModel> getRoleListByXtyh(Integer start, Integer rows) {
		return roleDAO.getRoleListByXtyh(start, rows);
	}
	
	public List<RoleModel> getRoleListByRoleId(Integer start, Integer rows,String rolid) {
		return roleDAO.getRoleListByRoleId(start, rows,rolid);
	}
	
	
	public List<RoleModel> getRoleListByRoleId2(Integer start, Integer rows,String rolid,String roid2) {
		return roleDAO.getRoleListByRoleId2(start, rows,rolid,roid2);
	}
}
