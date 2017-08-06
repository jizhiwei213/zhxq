package com.system.model;

import com.base.model.BaseModel;

/**
 * 功能表
 * 
 */
public class FunctionModel extends BaseModel {
	private static final long serialVersionUID = 101068780366405822L;
	private String funName;// 功能名称
	private String funUrl;// 功能路径
	private FunctionModel parent;// 父功能
	private String funIco;// 功能图标样式
	private int type = 0;// 0 功能模块 1 1操作
	private String allPath;// 全路径 /parentAllPath/id 组成
	private int isLoadOpen = 0; // 默认加载父菜单时打开 0 否 1 是
	private String funCode;// 功能编码


	public TreeNodeModel buildTreeJsonModel(){
		TreeNodeModel treeJson=new TreeNodeModel();
		treeJson.setId(getId());
		treeJson.setText(funName);
		return treeJson;
	}
	
	public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	public String getFunUrl() {
		return funUrl;
	}

	public void setFunUrl(String funUrl) {
		this.funUrl = funUrl;
	}

	public FunctionModel getParent() {
		return parent;
	}

	public void setParent(FunctionModel parent) {
		this.parent = parent;
	}

	public String getFunIco() {
		return funIco;
	}

	public void setFunIco(String funIco) {
		this.funIco = funIco;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAllPath() {
		return allPath;
	}

	public void setAllPath(String allPath) {
		this.allPath = allPath;
	}

	public int getIsLoadOpen() {
		return isLoadOpen;
	}

	public void setIsLoadOpen(int isLoadOpen) {
		this.isLoadOpen = isLoadOpen;
	}

	public String getFunCode() {
		return funCode;
	}

	public void setFunCode(String funCode) {
		this.funCode = funCode;
	}
}
