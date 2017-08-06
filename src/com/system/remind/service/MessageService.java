package com.system.remind.service;

import java.util.List;

import com.system.remind.dao.MessageDAO;
import com.system.remind.model.MessageModel;


public class MessageService {
	private MessageDAO messageDAO;

	public void setMessageDAO(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}
	
	public void saveMessage(MessageModel message){
		messageDAO.saveMessage(message);
	}

	public List<MessageModel> getMessageList() {
		return messageDAO.getMessageList();
	}
	
}
