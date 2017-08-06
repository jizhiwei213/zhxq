package com.lonewolf.jcxx.service;

import java.util.List;

import com.lonewolf.jcxx.dao.ZhxqShenDAO;
import com.lonewolf.jcxx.model.ZhxqShenModel;
import com.lonewolf.jcxx.query.ZhxqShenShiquQuery;

public class ZhxqShenService {
	private ZhxqShenDAO zhxqShenDAO;
	
	public ZhxqShenDAO getZhxqShenDAO()
	{
		return zhxqShenDAO;
	}

	public void setZhxqShenDAO(ZhxqShenDAO ZhxqShenDAO)
	{
		this.zhxqShenDAO = ZhxqShenDAO;
	}

	public List<ZhxqShenModel> getZhxqShenList(Integer start, Integer rows,ZhxqShenShiquQuery zhxqShenQuery) {
		return zhxqShenDAO.getZhxqShenList(start,rows,zhxqShenQuery);
	}

	public int getZhxqShenCount(ZhxqShenShiquQuery zhxqShenQuery) {
		return zhxqShenDAO.getZhxqShenCount(zhxqShenQuery);
	}

	public ZhxqShenModel getZhxqShenModelById(String id) {
		return zhxqShenDAO.getZhxqShenModelById(id);
	}

	public void saveZhxqShen(ZhxqShenModel zhxqShenModel) {
		zhxqShenDAO.saveZhxqShen(zhxqShenModel);
	}

	public void updateZhxqShen(ZhxqShenModel zhxqShenModel) {
		zhxqShenDAO.updateZhxqShen(zhxqShenModel);
	}
	
	public void deleteZhxqShen(String id) {
		zhxqShenDAO.deleteZhxqShen(id);
	}
}
