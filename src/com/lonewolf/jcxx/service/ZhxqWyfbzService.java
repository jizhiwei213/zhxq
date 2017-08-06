package com.lonewolf.jcxx.service;

import java.util.List;

import com.lonewolf.jcxx.dao.ZhxqWyfbzDAO;
import com.lonewolf.jcxx.model.ZhxqWyfbzModel;

public class ZhxqWyfbzService {
	private ZhxqWyfbzDAO zhxqWyfbzDAO;
	
	public ZhxqWyfbzDAO getZhxqWyfbzDAO()
	{
		return zhxqWyfbzDAO;
	}

	public void setZhxqWyfbzDAO(ZhxqWyfbzDAO zhxqWyfbzDAO)
	{
		this.zhxqWyfbzDAO = zhxqWyfbzDAO;
	}

	public List<ZhxqWyfbzModel> getZhxqWyfbzList(Integer start, Integer rows,String xiaoquId) {
		return zhxqWyfbzDAO.getZhxqWyfbzList(start,rows,xiaoquId);
	}

	public int getZhxqWyfbzCount(String xiaoquId) {
		return zhxqWyfbzDAO.getZhxqWyfbzCount(xiaoquId);
	}

	public ZhxqWyfbzModel getZhxqWyfbzModelById(String id) {
		return zhxqWyfbzDAO.getZhxqWyfbzModelById(id);
	}

	public void saveZhxqWyfbz(ZhxqWyfbzModel zhxqWyfbzModel) {
		zhxqWyfbzDAO.saveZhxqWyfbz(zhxqWyfbzModel);
	}

	public void updateZhxqWyfbz(ZhxqWyfbzModel zhxqWyfbzModel) {
		zhxqWyfbzDAO.updateZhxqWyfbz(zhxqWyfbzModel);
	}
	
	public void deleteZhxqWyfbz(String id) {
		zhxqWyfbzDAO.deleteZhxqWyfbz(id);
	}
}
