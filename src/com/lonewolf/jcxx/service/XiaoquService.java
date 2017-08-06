package com.lonewolf.jcxx.service;

import java.util.List;

import com.lonewolf.jcxx.dao.XiaoquDAO;
import com.lonewolf.jcxx.model.XiaoquModel;
import com.lonewolf.jcxx.query.XiaoquQuery;

public class XiaoquService {
	private XiaoquDAO xiaoquDAO;
	
	public XiaoquDAO getXiaoquDAO()
	{
		return xiaoquDAO;
	}

	public void setXiaoquDAO(XiaoquDAO xiaoquDAO)
	{
		this.xiaoquDAO = xiaoquDAO;
	}

	public List<XiaoquModel> getXiaoquList(Integer start, Integer rows,XiaoquQuery xiaoquQuery) {
		return xiaoquDAO.getXiaoquList(start,rows,xiaoquQuery);
	}

	public int getXiaoquCount(XiaoquQuery xiaoquQuery) {
		return xiaoquDAO.getXiaoquCount(xiaoquQuery);
	}

	public XiaoquModel getXiaoquModelById(String id) {
		return xiaoquDAO.getXiaoquModelById(id);
	}

	public void saveXiaoqu(XiaoquModel xiaoquModel) {
		xiaoquDAO.saveXiaoqu(xiaoquModel);
	}

	public void updateXiaoqu(XiaoquModel xiaoquModel) {
		xiaoquDAO.updateXiaoqu(xiaoquModel);
	}
	
	public void deleteXiaoqu(String id) {
		xiaoquDAO.deleteXiaoqu(id);
	}
}
