package com.system.model;

import net.sf.json.JSONObject;

import com.base.model.BaseModel;
import com.common.util.DateUtil;

public class RoleModel extends BaseModel {
	private static final long serialVersionUID = -2693289846521623347L;
	private String roleName;
	private Double rolePrice;
	
	public JSONObject buildJson(){
		JSONObject json=new JSONObject();
		json.put("id",getId());
		json.put("roleName",roleName);
		json.put("rolePrice",rolePrice);
		json.put("insertTime",DateUtil.formatUtilDateToString(getInsertTime(), "yyyy-MM-dd HH:mm:ss"));
		json.put("updateTime",DateUtil.formatUtilDateToString(getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
		return json;
	}

	public RoleModel() {

	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Double getRolePrice() {
		return rolePrice;
	}

	public void setRolePrice(Double rolePrice) {
		this.rolePrice = rolePrice;
	}

	
}