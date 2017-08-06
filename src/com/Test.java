package com;

import java.util.List;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.common.util.EncryptKey;
import com.lonewolf.jcxx.model.ZhxqRoomModel;
import com.lonewolf.jcxx.service.ZhxqRoomService;
import com.system.service.RoleService;
import com.system.service.UserService;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String te = EncryptKey.encrypt("1");
		System.out.println(te);
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        ZhxqRoomService zhxqRoomService= null;
        zhxqRoomService = (ZhxqRoomService)wac.getBean("zhxqRoomService");
        
       List list =  zhxqRoomService.getZhxqRoomList(null, null, null);
        if(null!=list&&list.size()>0)
        {
        	for(int i=0;i<list.size();i++)
        	{
        		ZhxqRoomModel room =(ZhxqRoomModel)list.get(i);
        		String menpaihao  = room.getMenpaihao();
        		System.out.println(menpaihao.substring(4));
        	}
        }
        
	}

}
