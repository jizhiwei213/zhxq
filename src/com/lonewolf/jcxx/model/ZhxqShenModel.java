package com.lonewolf.jcxx.model;

import net.sf.json.JSONObject;
import com.base.model.BaseModel;

/**
 */
public class ZhxqShenModel extends BaseModel {
	private static final long serialVersionUID = 389262935748415781L;
	private String name;// 
	public JSONObject buildJson() {
		JSONObject json = new JSONObject();

		json.put("id", getId());
		json.put("name", name);
		return json;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
}