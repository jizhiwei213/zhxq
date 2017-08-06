package com.system.tag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.views.jsp.StrutsBodyTagSupport;

import com.opensymphony.xwork2.ActionContext;

public class AuthTag extends StrutsBodyTagSupport {
	private static final long serialVersionUID = 6302774407160450437L;

	private String code;

	public int doStartTag() throws JspException {
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = request.getSession();
		List<String> codes = null;
		if (session.getAttribute("operationCodeList") != null) {
			codes = (List<String>) session.getAttribute("operationCodeList");
		}
		if (codes != null && codes.contains(code)) {
			return EVAL_BODY_INCLUDE;
		}else{
			return SKIP_BODY;
		}
	}

	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
