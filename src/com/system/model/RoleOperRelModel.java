package com.system.model;

import com.base.model.BaseModel;

public class RoleOperRelModel extends BaseModel {
	private static final long serialVersionUID = 7757095755071917175L;

	private RoleModel role;
	private OperationModel operation;

	public RoleOperRelModel() {

	}

	public RoleModel getRole() {
		return role;
	}

	public void setRole(RoleModel role) {
		this.role = role;
	}

	public OperationModel getOperation() {
		return operation;
	}

	public void setOperation(OperationModel operation) {
		this.operation = operation;
	}

}
