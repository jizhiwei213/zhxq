package com.lonewolf.jcxx.model;

import net.sf.json.JSONObject;
import com.base.model.BaseModel;

/**
 */
public class ZhxqQuModel extends BaseModel {
	private static final long serialVersionUID = 389262935748415781L;
	private String name;// 
	private ZhxqShenModel shen;
	private ZhxqShiModel shi;
	public JSONObject buildJson() {
		JSONObject json = new JSONObject();

		json.put("id", getId());
		json.put("name", name);
		if(null!=shen)
		{
			json.put("shenid", shen.getId());
			json.put("shenname", shen.getName());
		}
		if(null!=shi)
		{
			json.put("shiid", shi.getId());
			json.put("shiname", shi.getName());
		}
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

	public ZhxqShenModel getShen()
	{
		return shen;
	}

	public void setShen(ZhxqShenModel shen)
	{
		this.shen = shen;
	}

	public ZhxqShiModel getShi()
	{
		return shi;
	}

	public void setShi(ZhxqShiModel shi)
	{
		this.shi = shi;
	}
	
}