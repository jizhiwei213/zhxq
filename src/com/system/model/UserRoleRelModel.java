package com.system.model;

import com.base.model.BaseModel;

/**
 * 用户角色关联
 */
public class UserRoleRelModel extends BaseModel {
	private static final long serialVersionUID = -6899324176875783682L;
	private UserModel user;// 用户ID
	private RoleModel role;// 角色ID

	public UserRoleRelModel() {

	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public RoleModel getRole() {
		return role;
	}

	public void setRole(RoleModel role) {
		this.role = role;
	}

}