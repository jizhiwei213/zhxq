package com.system.model;

import net.sf.json.JSONObject;

import com.base.model.BaseModel;

/**
 * 数据字典
 */
public class DataDictionaryModel extends BaseModel {
	private static final long serialVersionUID = 2229838284695754074L;
	private String name;//名称
	private String dataType;//类型
	private DataDictionaryModel parent;//父类
	private String dataValue;//数据值

	public JSONObject buildJson(){
		JSONObject json = new JSONObject();
		json.put("id", getId());
		json.put("name", name);
		json.put("dataType", dataType);
		json.put("dataValue", dataValue);
		return json;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataValue() {
		return this.dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public DataDictionaryModel getParent() {
		return parent;
	}

	public void setParent(DataDictionaryModel parent) {
		this.parent = parent;
	}
}