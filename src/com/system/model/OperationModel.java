package com.system.model;

import com.base.model.BaseModel;

public class OperationModel extends BaseModel {
	private static final long serialVersionUID = 7222270420913660272L;

	private String name;
	private String code;
	private String action;
	private FunctionModel function;// 所属的菜单项

	public OperationModel() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public FunctionModel getFunction() {
		return function;
	}

	public void setFunction(FunctionModel function) {
		this.function = function;
	}

}
