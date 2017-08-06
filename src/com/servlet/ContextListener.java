package com.servlet;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import com.system.model.UserModel;
import com.system.service.UserService;

public class ContextListener extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	public void init()
	{
//		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
//        UserService userService= null;
//        userService = (UserService)wac.getBean("userService");
//        UserModel userM = userService.getUserModelById("1");
//        try
//		{
//			build(userService,userM);
//		}
//		catch (InterruptedException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        JxsallService jxsallService = null;
//        jxsallService = (JxsallService)wac.getBean("jxsallService");
//        List list = userService.getUserListByUserType(null, null, "2", null);
//        for(int i=0;null!=list&&i<list.size();i++)
//        {
//        	UserModel user =(UserModel)list.get(i);
//        	JxsallModel allm =  jxsallService.getJxsallByUserId(user.getId());
//        	if(null==allm)
//        	{
//        		allm = new JxsallModel();
//        		allm.setUser(user);
//        		allm.setJxsday(25);
//        		allm.setJxsyear(2016);
//        		allm.setJxsmonth(12);
//        		allm.setJxsxj(user.getUserxingji());
//        		allm.setJxstime(new Date());
//        		jxsallService.saveJxsall(allm);
//        	}
//        }
	}
	
}
