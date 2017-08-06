package com.lonewolf.jiaofei.service;

import java.io.File;
import java.util.List;

import com.lonewolf.jcxx.model.XiaoquModel;
import com.lonewolf.jiaofei.dao.ZhxqJiaofeiDAO;
import com.lonewolf.jiaofei.model.ZhxqJiaofeiModel;
import com.lonewolf.jiaofei.query.ZhxqJiaofeiQuery;

public class ZhxqJiaofeiService {
	private ZhxqJiaofeiDAO zhxqJiaofeiDAO;
	
	public ZhxqJiaofeiDAO getZhxqJiaofeiDAO()
	{
		return zhxqJiaofeiDAO;
	}

	public void setZhxqJiaofeiDAO(ZhxqJiaofeiDAO zhxqJiaofeiDAO)
	{
		this.zhxqJiaofeiDAO = zhxqJiaofeiDAO;
	}

	public List<ZhxqJiaofeiModel> getZhxqJiaofeiList(Integer start, Integer rows,ZhxqJiaofeiQuery zhxqJiaofeiQuery) {
		return zhxqJiaofeiDAO.getZhxqJiaofeiList(start,rows,zhxqJiaofeiQuery);
	}

	public int getZhxqJiaofeiCount(ZhxqJiaofeiQuery zhxqJiaofeiQuery) {
		return zhxqJiaofeiDAO.getZhxqJiaofeiCount(zhxqJiaofeiQuery);
	}

	public ZhxqJiaofeiModel getZhxqJiaofeiModelById(String id) {
		return zhxqJiaofeiDAO.getZhxqJiaofeiModelById(id);
	}

	public void saveZhxqJiaofei(ZhxqJiaofeiModel zhxqJiaofeiModel) {
		zhxqJiaofeiDAO.saveZhxqJiaofei(zhxqJiaofeiModel);
	}

	public void updateZhxqJiaofei(ZhxqJiaofeiModel zhxqJiaofeiModel) {
		zhxqJiaofeiDAO.updateZhxqJiaofei(zhxqJiaofeiModel);
	}
	
	public void deleteZhxqJiaofei(String id) {
		zhxqJiaofeiDAO.deleteZhxqJiaofei(id);
	}
	
	public String uploadFile(File inputExcel,XiaoquModel xiaoqu) {
		return zhxqJiaofeiDAO.uploadFile(inputExcel,xiaoqu);
	}
}
