package com.base.model;

import java.io.Serializable;
import java.util.Date;

public class BaseModel implements Serializable {
	private static final long serialVersionUID = 2667016405913906203L;

	private String id;
	private Date insertTime;
	private Date updateTime;
	private int orderCode;// 排序号

	public BaseModel() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

}
