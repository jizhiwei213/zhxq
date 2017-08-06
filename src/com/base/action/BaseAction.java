package com.base.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.common.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.system.model.UserModel;

public class BaseAction extends ActionSupport {
	private static final long serialVersionUID = 3533932099451601765L;
	private Integer rows;
	private Integer page;
	private String keys;
	private String rand;
	private String dataTime;

	public String getRand() {
		if(null==rand||"".equals(rand)){
			rand=StringUtil.getUUID();
		}
		return rand;
	}

	protected HttpServletRequest getRequest() {
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		return request;
	}

	public String getDataTime() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日 E");
		dataTime = f.format(new Date());
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	protected HttpServletResponse getResponse() {
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
		return response;
	}

	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	protected UserModel getSessionUser() {
		if (getSession().getAttribute("user") != null) {
			return (UserModel) getSession().getAttribute("user");
		}
		return null;
	}

	protected String getSessionUserId() {
		if (getSessionUser() != null) {
			return getSessionUser().getId();
		}
		return null;
	}

	protected String getRealPath(String fileName) {
		ServletContext sct = ServletActionContext.getServletContext();
		if (fileName != null && !"".equals(fileName)) {
			return sct.getRealPath(fileName);
		}
		return sct.getRealPath("");
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

}
