package com.lonewolf.jcxx.model;

import net.sf.json.JSONObject;
import com.base.model.BaseModel;

/**
 */
public class ZhxqWygsModel extends BaseModel {
	private static final long serialVersionUID = 389262935748415781L;
	private String name;// 
	private String gsdz;// 
	private String remark;// 
	private String fuzeren;
	private String fuzerentel;
	public JSONObject buildJson() {
		JSONObject json = new JSONObject();

		json.put("id", getId());
		json.put("name", name);
		json.put("gsdz", gsdz);
		json.put("remark", remark);
		json.put("fuzeren", fuzeren);
		json.put("fuzerentel", fuzerentel);
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

	public String getGsdz()
	{
		return gsdz;
	}

	public void setGsdz(String gsdz)
	{
		this.gsdz = gsdz;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getFuzeren()
	{
		return fuzeren;
	}

	public void setFuzeren(String fuzeren)
	{
		this.fuzeren = fuzeren;
	}

	public String getFuzerentel()
	{
		return fuzerentel;
	}

	public void setFuzerentel(String fuzerentel)
	{
		this.fuzerentel = fuzerentel;
	}
	
	
}