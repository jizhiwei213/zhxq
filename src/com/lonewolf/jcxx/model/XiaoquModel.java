package com.lonewolf.jcxx.model;

import net.sf.json.JSONObject;
import com.base.model.BaseModel;

/**
 * 小区信息
 */
public class XiaoquModel extends BaseModel {
	private static final long serialVersionUID = 389262935748415781L;
	private String xiaoquName;// 
	private String xiaoquNo;//
	private String xiaoquAddress;// 
	private String xiaoquRemark;//
	private String xiaoquLianxiren;//
	private String xiaoquLianxidianhua;
	private ZhxqShenModel shen;
	private ZhxqShiModel shi;
	private ZhxqQuModel qu;
	private String fuzeren;
	private String fuzerentel;
	private ZhxqWygsModel wygs;
	public JSONObject buildJson() {
		JSONObject json = new JSONObject();

		json.put("id", getId());
		json.put("xiaoquName", xiaoquName);
		json.put("xiaoquNo", xiaoquNo);
		json.put("xiaoquAddress", xiaoquAddress);
		json.put("xiaoquRemark", xiaoquRemark);
		json.put("xiaoquLianxiren", xiaoquLianxiren);
		json.put("xiaoquLianxidianhua", xiaoquLianxidianhua);
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
		if(null!=wygs)
		{
			json.put("wygsid", wygs.getId());
			json.put("wygsname", wygs.getName());
		}
		if(null!=qu)
		{
			json.put("quid", qu.getId());
			json.put("quname", qu.getName());
		}
		json.put("fuzeren", fuzeren);
		json.put("fuzerentel", fuzerentel);
		return json;
	}
	public String getXiaoquName()
	{
		return xiaoquName;
	}
	public void setXiaoquName(String xiaoquName)
	{
		this.xiaoquName = xiaoquName;
	}
	public String getXiaoquNo()
	{
		return xiaoquNo;
	}
	public void setXiaoquNo(String xiaoquNo)
	{
		this.xiaoquNo = xiaoquNo;
	}
	public String getXiaoquAddress()
	{
		return xiaoquAddress;
	}
	public void setXiaoquAddress(String xiaoquAddress)
	{
		this.xiaoquAddress = xiaoquAddress;
	}
	public String getXiaoquRemark()
	{
		return xiaoquRemark;
	}
	public void setXiaoquRemark(String xiaoquRemark)
	{
		this.xiaoquRemark = xiaoquRemark;
	}
	public String getXiaoquLianxiren()
	{
		return xiaoquLianxiren;
	}
	public void setXiaoquLianxiren(String xiaoquLianxiren)
	{
		this.xiaoquLianxiren = xiaoquLianxiren;
	}
	public String getXiaoquLianxidianhua()
	{
		return xiaoquLianxidianhua;
	}
	public void setXiaoquLianxidianhua(String xiaoquLianxidianhua)
	{
		this.xiaoquLianxidianhua = xiaoquLianxidianhua;
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
	public ZhxqQuModel getQu()
	{
		return qu;
	}
	public void setQu(ZhxqQuModel qu)
	{
		this.qu = qu;
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
	public ZhxqWygsModel getWygs()
	{
		return wygs;
	}
	public void setWygs(ZhxqWygsModel wygs)
	{
		this.wygs = wygs;
	}
}