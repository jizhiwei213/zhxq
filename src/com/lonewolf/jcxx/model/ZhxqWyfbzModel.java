package com.lonewolf.jcxx.model;

import net.sf.json.JSONObject;
import com.base.model.BaseModel;

/**
 * 小区信息
 */
public class ZhxqWyfbzModel extends BaseModel {
	private static final long serialVersionUID = 389262935748415781L;
	private Double biaozhun;// 
	private Double sfbiaozhun;// 
	private Double dfbiaozhun;// 
	private Double rqbiaozhun;// 
	private Double nqf;// 
	private Double qitabz;//
	private String remark;// 
	private XiaoquModel xiaoqu;
	public JSONObject buildJson() {
		JSONObject json = new JSONObject();

		json.put("id", getId());
		json.put("biaozhun", biaozhun);
		json.put("sfbiaozhun", sfbiaozhun);
		json.put("dfbiaozhun", dfbiaozhun);
		json.put("rqbiaozhun", rqbiaozhun);
		json.put("nqf", nqf);
		
		json.put("qitabz", qitabz);
		json.put("remark", remark);
		if(null!=xiaoqu)
		{
			json.put("xiaoquid", xiaoqu.getId());
			json.put("xiaoquname", xiaoqu.getXiaoquName());
		}
		return json;
	}
	public Double getBiaozhun()
	{
		return biaozhun;
	}
	public void setBiaozhun(Double biaozhun)
	{
		this.biaozhun = biaozhun;
	}
	public Double getQitabz()
	{
		return qitabz;
	}
	public void setQitabz(Double qitabz)
	{
		this.qitabz = qitabz;
	}
	public String getRemark()
	{
		return remark;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	public XiaoquModel getXiaoqu()
	{
		return xiaoqu;
	}
	public void setXiaoqu(XiaoquModel xiaoqu)
	{
		this.xiaoqu = xiaoqu;
	}
	public Double getSfbiaozhun()
	{
		return sfbiaozhun;
	}
	public void setSfbiaozhun(Double sfbiaozhun)
	{
		this.sfbiaozhun = sfbiaozhun;
	}
	public Double getDfbiaozhun()
	{
		return dfbiaozhun;
	}
	public void setDfbiaozhun(Double dfbiaozhun)
	{
		this.dfbiaozhun = dfbiaozhun;
	}
	public Double getRqbiaozhun()
	{
		return rqbiaozhun;
	}
	public void setRqbiaozhun(Double rqbiaozhun)
	{
		this.rqbiaozhun = rqbiaozhun;
	}
	public Double getNqf()
	{
		return nqf;
	}
	public void setNqf(Double nqf)
	{
		this.nqf = nqf;
	}
	
	
}