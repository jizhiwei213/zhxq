package com.system.model;

import com.base.model.BaseModel;

/**
 * 角色功能关联表
 */
public class RoleFunRelModel extends BaseModel {
	private static final long serialVersionUID = 6205038695112196037L;
	private RoleModel role;// 角色ID
	private FunctionModel fun;// 功能ID

	public RoleFunRelModel() {

	}

	public RoleModel getRole() {
		return role;
	}

	public void setRole(RoleModel role) {
		this.role = role;
	}

	public FunctionModel getFun() {
		return fun;
	}

	public void setFun(FunctionModel fun) {
		this.fun = fun;
	}

}