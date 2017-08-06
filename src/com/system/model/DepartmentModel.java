package com.system.model;

import net.sf.json.JSONObject;

import com.base.model.BaseModel;

public class DepartmentModel extends BaseModel {
	private static final long serialVersionUID = -2347832057817904088L;

	private String deptName;// 部门名称
	private String deptNo;// 部门编号
	private String principal;// 负责人
	private String linkTel;// 联系电话
	private String status;// 状态
	private String remark;// 备注

	private DepartmentModel parent;// 上级部门
	
	public Object buildJson() {
		JSONObject json=new JSONObject();
		json.put("id", getId());
		json.put("deptName", deptName);
		json.put("deptNo", deptNo);
		json.put("principal", principal);
		json.put("linkTel", linkTel);
		if(parent!=null){
			json.put("_parentId", parent.getId());//方便treegrid使用
		}
		return json;
	}
	
	public TreeNodeModel buildTreeJsonModel(){
		TreeNodeModel treeJson=new TreeNodeModel();
		treeJson.setId(getId());
		treeJson.setText(deptName);
		return treeJson;
	}

	public DepartmentModel() {

	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getLinkTel() {
		return linkTel;
	}

	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public DepartmentModel getParent() {
		return parent;
	}

	public void setParent(DepartmentModel parent) {
		this.parent = parent;
	}

}
