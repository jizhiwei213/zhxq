package com.lonewolf.jcxx.model;

import java.util.Set;

import net.sf.json.JSONObject;
import com.base.model.BaseModel;
import com.system.model.UserModel;

/**
 */
public class ZhxqRoomModel extends BaseModel {
	private static final long serialVersionUID = 389262935748415781L;
	private String name;// 
	private String mendong;
	private String danyuan;
	private String menpaihao;
	private String huzhuname;
	private XiaoquModel xiaoqu;
	private Double mianji;
	private UserModel yezhu;
	private Set<UserModel> users;
	public JSONObject buildJson() {
		JSONObject json = new JSONObject();

		json.put("id", getId());
		json.put("name", name);
		if(null!=mendong)
		{
			json.put("mendong", mendong+"栋");
		}
		else
		{
			json.put("mendong", mendong);
		}
		if(null!=danyuan)
		{
			json.put("danyuan", danyuan+"单元");
		}
		else
		{
			json.put("danyuan", danyuan);
		}
		json.put("menpaihao", menpaihao);
		json.put("huzhuname", huzhuname);
		json.put("mianji", mianji);
		if(null!=xiaoqu)
		{
			json.put("xiaoquid", xiaoqu.getId());
			json.put("xiaoquname", xiaoqu.getXiaoquName());
		}
		if(null!=yezhu)
		{
			json.put("yezhuid", yezhu.getId());
			json.put("yezhuname", yezhu.getUserName());
		}
		if (users != null && users.size() > 0)
		{
			String str = "";
			for (UserModel role : users)
			{
				str += role.getUserName() + ",";
			}
			json.put("users", str.substring(0, str.length() - 1));
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

	public String getMenpaihao()
	{
		return menpaihao;
	}

	public void setMenpaihao(String menpaihao)
	{
		this.menpaihao = menpaihao;
	}

	public String getHuzhuname()
	{
		return huzhuname;
	}

	public void setHuzhuname(String huzhuname)
	{
		this.huzhuname = huzhuname;
	}

	public XiaoquModel getXiaoqu()
	{
		return xiaoqu;
	}

	public void setXiaoqu(XiaoquModel xiaoqu)
	{
		this.xiaoqu = xiaoqu;
	}

	public Double getMianji()
	{
		return mianji;
	}

	public void setMianji(Double mianji)
	{
		this.mianji = mianji;
	}

	public UserModel getYezhu()
	{
		return yezhu;
	}

	public void setYezhu(UserModel yezhu)
	{
		this.yezhu = yezhu;
	}

	public Set<UserModel> getUsers()
	{
		return users;
	}

	public void setUsers(Set<UserModel> users)
	{
		this.users = users;
	}

	public String getMendong()
	{
		return mendong;
	}

	public void setMendong(String mendong)
	{
		this.mendong = mendong;
	}

	public String getDanyuan()
	{
		return danyuan;
	}

	public void setDanyuan(String danyuan)
	{
		this.danyuan = danyuan;
	}
	
	
}