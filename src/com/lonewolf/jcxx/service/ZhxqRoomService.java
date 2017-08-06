package com.lonewolf.jcxx.service;

import java.io.File;
import java.util.List;

import com.lonewolf.jcxx.dao.ZhxqRoomDAO;
import com.lonewolf.jcxx.model.UserRoomRelModel;
import com.lonewolf.jcxx.model.XiaoquModel;
import com.lonewolf.jcxx.model.ZhxqRoomModel;
import com.lonewolf.jcxx.query.ZhxqRoomQuery;
import com.system.model.UserModel;

public class ZhxqRoomService {
	private ZhxqRoomDAO zhxqRoomDAO;
	
	public ZhxqRoomDAO getZhxqRoomDAO()
	{
		return zhxqRoomDAO;
	}

	public void setZhxqRoomDAO(ZhxqRoomDAO zhxqRoomDAO)
	{
		this.zhxqRoomDAO = zhxqRoomDAO;
	}

	public List<ZhxqRoomModel> getZhxqRoomList(Integer start, Integer rows,ZhxqRoomQuery zhxqRoomQuery) {
		return zhxqRoomDAO.getZhxqRoomList(start,rows,zhxqRoomQuery);
	}

	public int getZhxqRoomCount(ZhxqRoomQuery zhxqRoomQuery) {
		return zhxqRoomDAO.getZhxqRoomCount(zhxqRoomQuery);
	}

	public ZhxqRoomModel getZhxqRoomModelById(String id) {
		return zhxqRoomDAO.getZhxqRoomModelById(id);
	}

	public void saveZhxqRoom(ZhxqRoomModel zhxqRoomModel) {
		zhxqRoomDAO.saveZhxqRoom(zhxqRoomModel);
	}

	public void updateZhxqRoom(ZhxqRoomModel zhxqRoomModel) {
		zhxqRoomDAO.updateZhxqRoom(zhxqRoomModel);
	}
	
	public void deleteZhxqRoom(String id) {
		zhxqRoomDAO.deleteZhxqRoom(id);
	}
	
	public void saveUserRoomByRoomAndUser(ZhxqRoomModel zhxqRoomModel,UserModel userModel) {
		List dataList = zhxqRoomDAO.getUserRoomByRoomAndUser(zhxqRoomModel, userModel);
		if(userModel!=null&&zhxqRoomModel!=null){
			if(null==dataList||dataList.size()==0)
			{
				UserRoomRelModel userRoom = new UserRoomRelModel();
				userRoom.setUser(userModel);
				userRoom.setRoom(zhxqRoomModel);
				zhxqRoomDAO.saveUserRoomByRoomAndUser(userRoom);
			}
		}
	}
	public List<UserRoomRelModel> getUserRoomByRoomAndUser(ZhxqRoomModel zhxqRoomModel, UserModel userModel)
	{
		return zhxqRoomDAO.getUserRoomByRoomAndUser(zhxqRoomModel, userModel);
	}
	
	public List<ZhxqRoomModel> getUserRoomByNoUser(UserModel userModel)
	{
		return zhxqRoomDAO.getUserRoomByNoUser(userModel);
	}
	public String uploadFile(File inputExcel,XiaoquModel xiaoqu) {
		return zhxqRoomDAO.uploadFile(inputExcel,xiaoqu);
	}
}
