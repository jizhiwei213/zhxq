package com.system.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class BaseTag extends SimpleTagSupport {
	private String src;
	private String type;

	public void doTag() throws JspException, IOException {
		JspContext jspcon = getJspContext();
		JspContext jspc = jspcon;
		JspWriter out = jspc.getOut();
		PageContext pcx = (PageContext) jspcon;
		HttpServletRequest request = (HttpServletRequest) pcx.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		String str = "";
		if (src != null && !"".equals(src.trim())) {
			if (src.indexOf("/") == 0) {
				basePath = basePath + src;
			} else {
				basePath = basePath + "/" + src;
			}
		}
		if (type != null && "script".equals(type.toLowerCase())) {
			str = "<script type='text/javascript' src='" + basePath + "'></script>";
		} else if (type != null && "style".equals(type.toLowerCase())) {
			str = "<link rel='stylesheet' href='" + basePath + "' type='text/css'/>";
		} else {
			str = basePath;
		}
		out.write(str);
	}

	protected JspFragment getJspBody() {
		return super.getJspBody();
	}

	protected JspContext getJspContext() {
		return super.getJspContext();
	}

	public JspTag getParent() {
		return super.getParent();
	}

	public void setJspBody(JspFragment jspBody) {
		super.setJspBody(jspBody);
	}

	public void setJspContext(JspContext pc) {
		super.setJspContext(pc);
	}

	public void setParent(JspTag parent) {
		super.setParent(parent);
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
