package com.lonewolf.jcxx.service;

import java.util.List;

import com.lonewolf.jcxx.dao.ZhxqShiDAO;
import com.lonewolf.jcxx.model.ZhxqShiModel;
import com.lonewolf.jcxx.query.ZhxqShenShiquQuery;

public class ZhxqShiService {
	private ZhxqShiDAO zhxqShiDAO;
	
	public ZhxqShiDAO getZhxqShiDAO()
	{
		return zhxqShiDAO;
	}

	public void setZhxqShiDAO(ZhxqShiDAO ZhxqShiDAO)
	{
		this.zhxqShiDAO = ZhxqShiDAO;
	}

	public List<ZhxqShiModel> getZhxqShiList(Integer start, Integer rows,ZhxqShenShiquQuery zhxqShiQuery) {
		return zhxqShiDAO.getZhxqShiList(start,rows,zhxqShiQuery);
	}

	public int getZhxqShiCount(ZhxqShenShiquQuery zhxqShiQuery) {
		return zhxqShiDAO.getZhxqShiCount(zhxqShiQuery);
	}

	public ZhxqShiModel getZhxqShiModelById(String id) {
		return zhxqShiDAO.getZhxqShiModelById(id);
	}

	public void saveZhxqShi(ZhxqShiModel zhxqShiModel) {
		zhxqShiDAO.saveZhxqShi(zhxqShiModel);
	}

	public void updateZhxqShi(ZhxqShiModel zhxqShiModel) {
		zhxqShiDAO.updateZhxqShi(zhxqShiModel);
	}
	
	public void deleteZhxqShi(String id) {
		zhxqShiDAO.deleteZhxqShi(id);
	}
	public List<ZhxqShiModel> getZhxqShiByShenIdList(String shenId) {
		List<ZhxqShiModel> list =  zhxqShiDAO.getZhxqShiByShenIdList(shenId);
		return list;
	}
}
