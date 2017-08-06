package com.lonewolf.jiaofei.model;

import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

import com.base.model.BaseModel;
import com.lonewolf.jcxx.model.ZhxqRoomModel;
import com.system.model.UserModel;

/**
 */
public class ZhxqJiaofeiModel extends BaseModel {
	private static final long serialVersionUID = 389262935748415781L;
	private static final Logger log = LoggerFactory.getLogger(ZhxqJiaofeiModel.class);
	private int zhxqyear;// 
	private int zhxqmonth;
	private Double wyf;
	private Double gnf;
	private Double otherfy;
	private Double dianfei;
	private Double dianliang;
	private Double ruanqifei;
	private Double ruanqifeiliang;
	private Double shuifei;
	private Double shuifeiliang;
	private Double cwf;
	private String jiaofeidate;
	private String dianliangdate;
	private String shuifeidate;
	private String ruanqifeidate;
	private String jiaofeistatus;
	private String dianliangstatus;
	private String shuifeistatus;
	private String ruanqifeistatus;
	private String gnfdate;
	private String gnfstatus;
	private ZhxqRoomModel room;
	private UserModel ywy;
	public JSONObject buildJson() {
		JSONObject json = new JSONObject();
		json.put("jiaofeidate", jiaofeidate);
		json.put("dianliangdate", dianliangdate);
		json.put("shuifeidate", shuifeidate);
		json.put("ruanqifeidate", ruanqifeidate);
		json.put("jiaofeistatus", jiaofeistatus);
		json.put("dianliangstatus", dianliangstatus);
		json.put("shuifeistatus", shuifeistatus);
		json.put("ruanqifeistatus", ruanqifeistatus);
		json.put("gnfdate", gnfdate);
		json.put("gnfstatus", gnfstatus);
		json.put("id", getId());
		json.put("zhxqyear", zhxqyear);
		json.put("zhxqmonth", zhxqmonth);
		try {
			json.put("wyf", new DecimalFormat("#.##").format(wyf));
			json.put("gnf", new DecimalFormat("#.##").format(gnf));
			json.put("otherfy", new DecimalFormat("#.##").format(otherfy));
			json.put("dianfei", new DecimalFormat("#.##").format(dianfei));
			json.put("dianliang", new DecimalFormat("#.##").format(dianliang));
			System.out.println("ruanqifei:"+ruanqifei);
			json.put("ruanqifei", new DecimalFormat("#.##").format(ruanqifei));
			json.put("ruanqifeiliang", new DecimalFormat("#.##").format(ruanqifeiliang));
			json.put("shuifei", new DecimalFormat("#.##").format(shuifei));
			json.put("shuifeiliang", new DecimalFormat("#.##").format(shuifeiliang));
			json.put("cwf", new DecimalFormat("#.##").format(cwf));
			//
//		json.put("wyf", wyf);
//		json.put("gnf", gnf);
//		json.put("otherfy", otherfy);
//		json.put("dianfei", dianfei);
//		json.put("dianliang", dianliang);
//		json.put("ruanqifei", ruanqifei);
//		json.put("ruanqifeiliang", ruanqifeiliang);
//		json.put("shuifei", shuifei);
//		json.put("shuifeiliang", shuifeiliang);
//		json.put("cwf", cwf);
			//
			if(null!=room)
			{
				json.put("roomid", room.getId());
				json.put("roomname", room.getName());
				json.put("menpaihao", room.getMenpaihao());
				json.put("mendong", room.getMendong());
				json.put("danyuan", room.getDanyuan());
				json.put("shenname", room.getXiaoqu().getShen().getName());
				json.put("shiname", room.getXiaoqu().getShi().getName());
				json.put("quname", room.getXiaoqu().getQu().getName());
				json.put("xiaoquname", room.getXiaoqu().getXiaoquName());
			}
			if(null!=ywy)
			{
				json.put("ywyid", ywy.getId());
				json.put("ywyname", ywy.getUserName());
			}
			else
			{
				if("0".equals(jiaofeistatus))
				{
					json.put("ywyname", "公众号缴费");
				}
			}
		} catch (Exception e) {
			log.error("buildJson error:",e);
		}
		return json;
	}
	
	public static void main(String[] args) {
		Double wyf = 0.0;
		System.out.println("0:"+new DecimalFormat("0.00").format(wyf));
		System.out.println("#"+new DecimalFormat("#.##").format(wyf));
		System.out.println("str"+new DecimalFormat("#.##").format(wyf+""));
	}

	public int getZhxqyear()
	{
		return zhxqyear;
	}

	public void setZhxqyear(int zhxqyear)
	{
		this.zhxqyear = zhxqyear;
	}

	public int getZhxqmonth()
	{
		return zhxqmonth;
	}

	public void setZhxqmonth(int zhxqmonth)
	{
		this.zhxqmonth = zhxqmonth;
	}

	public Double getWyf()
	{
		return wyf;
	}

	public void setWyf(Double wyf)
	{
		this.wyf = wyf;
	}

	public Double getCwf()
	{
		return cwf;
	}

	public void setCwf(Double cwf)
	{
		this.cwf = cwf;
	}

	public Double getDianfei()
	{
		return dianfei;
	}

	public void setDianfei(Double dianfei)
	{
		this.dianfei = dianfei;
	}

	public Double getDianliang()
	{
		return dianliang;
	}

	public void setDianliang(Double dianliang)
	{
		this.dianliang = dianliang;
	}

	public Double getRuanqifei()
	{
		return ruanqifei;
	}

	public void setRuanqifei(Double ruanqifei)
	{
		this.ruanqifei = ruanqifei;
	}

	public String getJiaofeidate()
	{
		return jiaofeidate;
	}

	public void setJiaofeidate(String jiaofeidate)
	{
		this.jiaofeidate = jiaofeidate;
	}

	public String getJiaofeistatus()
	{
		return jiaofeistatus;
	}

	public void setJiaofeistatus(String jiaofeistatus)
	{
		this.jiaofeistatus = jiaofeistatus;
	}

	public ZhxqRoomModel getRoom()
	{
		return room;
	}

	public void setRoom(ZhxqRoomModel room)
	{
		this.room = room;
	}

	public UserModel getYwy()
	{
		return ywy;
	}

	public void setYwy(UserModel ywy)
	{
		this.ywy = ywy;
	}

	public Double getGnf()
	{
		return gnf;
	}

	public void setGnf(Double gnf)
	{
		this.gnf = gnf;
	}

	public Double getOtherfy()
	{
		return otherfy;
	}

	public void setOtherfy(Double otherfy)
	{
		this.otherfy = otherfy;
	}

	public Double getRuanqifeiliang()
	{
		return ruanqifeiliang;
	}

	public void setRuanqifeiliang(Double ruanqifeiliang)
	{
		this.ruanqifeiliang = ruanqifeiliang;
	}

	public Double getShuifei()
	{
		return shuifei;
	}

	public void setShuifei(Double shuifei)
	{
		this.shuifei = shuifei;
	}

	public Double getShuifeiliang()
	{
		return shuifeiliang;
	}

	public void setShuifeiliang(Double shuifeiliang)
	{
		this.shuifeiliang = shuifeiliang;
	}

	public String getDianliangdate()
	{
		return dianliangdate;
	}

	public void setDianliangdate(String dianliangdate)
	{
		this.dianliangdate = dianliangdate;
	}

	public String getShuifeidate()
	{
		return shuifeidate;
	}

	public void setShuifeidate(String shuifeidate)
	{
		this.shuifeidate = shuifeidate;
	}

	public String getRuanqifeidate()
	{
		return ruanqifeidate;
	}

	public void setRuanqifeidate(String ruanqifeidate)
	{
		this.ruanqifeidate = ruanqifeidate;
	}

	public String getDianliangstatus()
	{
		return dianliangstatus;
	}

	public void setDianliangstatus(String dianliangstatus)
	{
		this.dianliangstatus = dianliangstatus;
	}

	public String getShuifeistatus()
	{
		return shuifeistatus;
	}

	public void setShuifeistatus(String shuifeistatus)
	{
		this.shuifeistatus = shuifeistatus;
	}

	public String getRuanqifeistatus()
	{
		return ruanqifeistatus;
	}

	public void setRuanqifeistatus(String ruanqifeistatus)
	{
		this.ruanqifeistatus = ruanqifeistatus;
	}

	public String getGnfdate()
	{
		return gnfdate;
	}

	public void setGnfdate(String gnfdate)
	{
		this.gnfdate = gnfdate;
	}

	public String getGnfstatus()
	{
		return gnfstatus;
	}

	public void setGnfstatus(String gnfstatus)
	{
		this.gnfstatus = gnfstatus;
	}
	
}