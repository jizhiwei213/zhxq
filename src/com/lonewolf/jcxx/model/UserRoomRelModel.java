package com.lonewolf.jcxx.model;

import com.base.model.BaseModel;
import com.system.model.UserModel;

/**
 */
public class UserRoomRelModel extends BaseModel {
	private static final long serialVersionUID = -6899324176875783682L;
	private UserModel user;// 用户ID
	private ZhxqRoomModel room;// 角色ID

	public UserRoomRelModel() {

	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public ZhxqRoomModel getRoom()
	{
		return room;
	}

	public void setRoom(ZhxqRoomModel room)
	{
		this.room = room;
	}
}