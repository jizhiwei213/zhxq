package com.system.tag;

import java.io.IOException;
import java.util.Date;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.common.util.DateUtil;

public class DateTag extends SimpleTagSupport {
	private String format;
	private Date value;

	public void doTag() throws JspException, IOException {
		if (format != null && !"".equals(format.trim())) {
			JspContext jspcon = getJspContext();
			JspContext jspc = jspcon;
			JspWriter out = jspc.getOut();
			if (value != null) {
				out.write(DateUtil.formatUtilDateToString(value, format));
			} else {
				out.write(DateUtil.getDateTime(format));
			}
		}
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

	public Date getValue() {
		return value;
	}

	public void setValue(Date value) {
		this.value = value;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
