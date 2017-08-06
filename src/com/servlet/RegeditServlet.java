package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.envers.tools.StringTools;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.common.util.CommonUtil;
import com.common.util.EncryptKey;
import com.system.model.DepartmentModel;
import com.system.model.RoleModel;
import com.system.model.UserModel;
import com.system.service.DeptService;
import com.system.service.RoleService;
import com.system.service.UserService;

/**
 * Servlet implementation class RegeditServlet
 */
public class RegeditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegeditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init() throws ServletException {  
        // Put your code here  
      
    }  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();  
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
	        UserService userService= null;
	        userService = (UserService)wac.getBean("userService");
	        RoleService roleService = null;
	        roleService = (RoleService)wac.getBean("roleService");
	        DeptService deptService = null;
	        deptService = (DeptService)wac.getBean("deptService");
	        request.setCharacterEncoding("UTF-8");  
	        response.setContentType("text/html; charset=utf-8");  
	        PrintWriter out = response.getWriter();  
	        String userName = request.getParameter("userName");  
	        String userAccount = request.getParameter("userAccount");  
	        String userPassword = request.getParameter("userPassword"); 
	        String linkTel = request.getParameter("linkTel"); 
	        RoleModel roleModel = roleService.getRoleModelById("402881e958fb84910158fb876b270004");
	        DepartmentModel deptModel = deptService.getDeptModelById("402881865bc7734c015bc77559180006");
	        	UserModel uuu = userService.findUserModelByCode(userAccount);
		        if(uuu!=null)
		        {
		        	 out.println("注册失败,用户名已经存在！");  
		        	 response.sendRedirect(path + "/main/regist.jsp?error=2");  
		        }
		        else
		        {
			        UserModel userModel = new UserModel();
			        userModel.setUserAccount(userAccount);
			        userModel.setUserName(userName);
			        userModel.setUserPassword(EncryptKey.encrypt(userPassword));
			        userModel.setIsStop(0);
			        userModel.setDept(deptModel);
			        userModel.setLinkTel(linkTel);
			        userService.saveUser(userModel); 
			        UserModel uss = userService.findUserModelByCode(userAccount);
			        roleService.saveUserRole(uss,roleModel);
			        request.getSession().setAttribute("user", uss);
			        out.println("注册成功");  
			        response.sendRedirect(path + "/main/regist.jsp?error=5"); 
		        }
	        out.flush();  
	        out.close();  
	}

	private String getUUId(UserService userService)
	{
		//String uuid = CommonUtil.generateShortUuid();
		String uuid = CommonUtil.generateNumber2();
		 UserModel tjr = userService.findUserModelByUserNo(uuid);
		 if(null==tjr)
		 {
			 return uuid;
		 }
		 else
		 {
			 return getUUId(userService);
		 }
	}
}
