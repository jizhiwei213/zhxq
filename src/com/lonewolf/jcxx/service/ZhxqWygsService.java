package com.lonewolf.jcxx.service;

import java.util.List;

import com.lonewolf.jcxx.dao.ZhxqWygsDAO;
import com.lonewolf.jcxx.model.ZhxqWygsModel;
import com.lonewolf.jcxx.query.ZhxqWygsQuery;

public class ZhxqWygsService {
	private ZhxqWygsDAO zhxqWygsDAO;
	
	public ZhxqWygsDAO getZhxqWygsDAO()
	{
		return zhxqWygsDAO;
	}

	public void setZhxqWygsDAO(ZhxqWygsDAO zhxqWygsDAO)
	{
		this.zhxqWygsDAO = zhxqWygsDAO;
	}

	public List<ZhxqWygsModel> getZhxqWygsList(Integer start, Integer rows,ZhxqWygsQuery zhxqWygsQuery) {
		return zhxqWygsDAO.getZhxqWygsList(start,rows,zhxqWygsQuery);
	}

	public int getZhxqWygsCount(ZhxqWygsQuery zhxqWygsQuery) {
		return zhxqWygsDAO.getZhxqWygsCount(zhxqWygsQuery);
	}

	public ZhxqWygsModel getZhxqWygsModelById(String id) {
		return zhxqWygsDAO.getZhxqWygsModelById(id);
	}

	public void saveZhxqWygs(ZhxqWygsModel zhxqWygsModel) {
		zhxqWygsDAO.saveZhxqWygs(zhxqWygsModel);
	}

	public void updateZhxqWygs(ZhxqWygsModel zhxqWygsModel) {
		zhxqWygsDAO.updateZhxqWygs(zhxqWygsModel);
	}
	
	public void deleteZhxqWygs(String id) {
		zhxqWygsDAO.deleteZhxqWygs(id);
	}
}
