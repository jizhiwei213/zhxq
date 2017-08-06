package com.system.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class NoCacheTag extends SimpleTagSupport {

	public void doTag() throws JspException, IOException {
		JspContext jspcon = getJspContext();
		PageContext pcx = (PageContext) jspcon;
		HttpServletResponse response = (HttpServletResponse) pcx.getResponse();
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		super.doTag();
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

}
