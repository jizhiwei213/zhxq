package com.system.remind.model;

import java.util.Date;

import com.base.model.BaseModel;

public class MessageModel extends BaseModel {
	private static final long serialVersionUID = -1016373909494063724L;

	private String content;// 消息内容
	private String type;
	private Date dueDate;// 截止日期
	private int isStop;
	
	public MessageModel() {

	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public int getIsStop() {
		return isStop;
	}

	public void setIsStop(int isStop) {
		this.isStop = isStop;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
