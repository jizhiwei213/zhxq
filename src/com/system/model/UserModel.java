package com.system.model;

import java.util.Date;
import java.util.Set;

import net.sf.json.JSONObject;

import com.base.model.BaseModel;
import com.common.util.DateUtil;
import com.lonewolf.jcxx.model.XiaoquModel;
import com.lonewolf.jcxx.model.ZhxqQuModel;
import com.lonewolf.jcxx.model.ZhxqWygsModel;
import com.system.define.SysParaDefine;

/**
 * 鐢ㄦ埛琛�
 */
public class UserModel extends BaseModel
{
	private static final long serialVersionUID = 389262935748415781L;
	private String userName;// 鐢ㄦ埛濮撳悕(鏄电О)
	private String userAccount;// 鐢ㄦ埛甯愬彿
	private String userPassword;// 鐢ㄦ埛瀵嗙爜
	private String linkTel;// 鑱旂郴鐢佃瘽
	private String email;// 閭欢
	private int isStop;// 鏄惁鍋滅敤
	private int sex; // 鎬у埆
	private String userPhoto;
	private DepartmentModel dept;// 鎵�灞為儴闂�

	private Set<RoleModel> roles;
	private String usertype;
	private ZhxqWygsModel wygs;
	private XiaoquModel xiaoqu;
	private ZhxqQuModel quyu;
	public JSONObject buildJson()
	{
		JSONObject json = new JSONObject();

		json.put("id", getId());
		json.put("userName", userName);
		json.put("userAccount", userAccount);
		json.put("linkTel", linkTel);
		json.put("email", email);
		json.put("isStop", isStop);
		if (sex == SysParaDefine.MALE)
		{
			json.put("sex", "男");
		}
		else if (sex == SysParaDefine.FEMALE)
		{
			json.put("sex", "女");
		}
		if (dept != null)
		{
			json.put("dept", dept.getDeptName());
		}
		if (roles != null && roles.size() > 0)
		{
			String str = "";
			for (RoleModel role : roles)
			{
				str += role.getRoleName() + ",";
			}
			json.put("roles", str.substring(0, str.length() - 1));
		}
		
		if (wygs != null)
		{
			json.put("wygsName", wygs.getName());
		}
		if (xiaoqu != null)
		{
			json.put("xiaoquName", xiaoqu.getXiaoquName());
		}
		if (quyu != null)
		{
			json.put("quyuName", quyu.getName());
		}
		json.put("userPhoto", userPhoto);
		json.put("usertype", usertype);
		return json;
	}

	// public JSONObject buildTreeJson() {
	// JSONObject json = new JSONObject();
	// if (getId() != null)
	// json.put("id", getId());
	// if (userName != null) {
	// json.put("name", userName);
	// }
	// ActionContext ctx = ActionContext.getContext();
	// HttpServletRequest request = (HttpServletRequest)
	// ctx.get(ServletActionContext.HTTP_REQUEST);
	// String path = request.getContextPath();
	// String basePath = request.getScheme() + "://" + request.getServerName() +
	// ":" + request.getServerPort() + path;
	// json.put("icon", basePath + "/images/user.png");
	//
	// if (unit != null){
	// json.put("pId", unit.getId());
	// }else{
	// json.put("pId", "0");
	// }
	// return json;
	// }

	public UserModel()
	{

	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getUserAccount()
	{
		return userAccount;
	}

	public void setUserAccount(String userAccount)
	{
		this.userAccount = userAccount;
	}

	public String getUserPassword()
	{
		return userPassword;
	}

	public void setUserPassword(String userPassword)
	{
		this.userPassword = userPassword;
	}

	public String getLinkTel()
	{
		return linkTel;
	}

	public void setLinkTel(String linkTel)
	{
		this.linkTel = linkTel;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public int getSex()
	{
		return sex;
	}

	public void setSex(int sex)
	{
		this.sex = sex;
	}

	public int getIsStop()
	{
		return isStop;
	}

	public void setIsStop(int isStop)
	{
		this.isStop = isStop;
	}

	public DepartmentModel getDept()
	{
		return dept;
	}

	public void setDept(DepartmentModel dept)
	{
		this.dept = dept;
	}

	public Set<RoleModel> getRoles()
	{
		return roles;
	}

	public void setRoles(Set<RoleModel> roles)
	{
		this.roles = roles;
	}

	public String getUserPhoto()
	{
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto)
	{
		this.userPhoto = userPhoto;
	}

	public String getUsertype()
	{
		return usertype;
	}

	public void setUsertype(String usertype)
	{
		this.usertype = usertype;
	}

	public ZhxqWygsModel getWygs()
	{
		return wygs;
	}

	public void setWygs(ZhxqWygsModel wygs)
	{
		this.wygs = wygs;
	}

	public XiaoquModel getXiaoqu()
	{
		return xiaoqu;
	}

	public void setXiaoqu(XiaoquModel xiaoqu)
	{
		this.xiaoqu = xiaoqu;
	}

	public ZhxqQuModel getQuyu()
	{
		return quyu;
	}

	public void setQuyu(ZhxqQuModel quyu)
	{
		this.quyu = quyu;
	}
}