package com.lonewolf.jcxx.service;

import java.util.List;

import com.lonewolf.jcxx.dao.ZhxqQuDAO;
import com.lonewolf.jcxx.model.ZhxqQuModel;
import com.lonewolf.jcxx.query.ZhxqShenShiquQuery;

public class ZhxqQuService {
	private ZhxqQuDAO zhxqQuDAO;
	
	public ZhxqQuDAO getZhxqQuDAO()
	{
		return zhxqQuDAO;
	}

	public void setZhxqQuDAO(ZhxqQuDAO ZhxqQuDAO)
	{
		this.zhxqQuDAO = ZhxqQuDAO;
	}

	public List<ZhxqQuModel> getZhxqQuList(Integer start, Integer rows,ZhxqShenShiquQuery zhxqQuQuery) {
		return zhxqQuDAO.getZhxqQuList(start,rows,zhxqQuQuery);
	}

	public int getZhxqQuCount(ZhxqShenShiquQuery zhxqQuQuery) {
		return zhxqQuDAO.getZhxqQuCount(zhxqQuQuery);
	}

	public ZhxqQuModel getZhxqQuModelById(String id) {
		return zhxqQuDAO.getZhxqQuModelById(id);
	}

	public void saveZhxqQu(ZhxqQuModel zhxqQuModel) {
		zhxqQuDAO.saveZhxqQu(zhxqQuModel);
	}

	public void updateZhxqQu(ZhxqQuModel zhxqQuModel) {
		zhxqQuDAO.updateZhxqQu(zhxqQuModel);
	}
	
	public void deleteZhxqQu(String id) {
		zhxqQuDAO.deleteZhxqQu(id);
	}
	public List<ZhxqQuModel> getZhxqQuByShenIdList(String shenId) {
		return zhxqQuDAO.getZhxqQuByShenIdList(shenId);
	}
	public List<ZhxqQuModel> getZhxqQuByShiIdList(String shiId) {
		return zhxqQuDAO.getZhxqQuByShiIdList(shiId);
	}
}
