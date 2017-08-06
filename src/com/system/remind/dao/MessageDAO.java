package com.system.remind.dao;

import java.util.Date;
import java.util.List;

import com.base.dao.BaseDAO;
import com.system.remind.model.MessageModel;

public class MessageDAO extends BaseDAO {

	public void saveMessage(MessageModel message) {
		message.setInsertTime(new Date());
		message.setUpdateTime(new Date());
		getHibernateTemplate().save(message);
	}

	public List<MessageModel> getMessageList() {
		String hql="from MessageModel mm where mm.dueDate>=? and mm.isStop=0";
		return getHibernateTemplate().find(hql,new Date());
	}

}
