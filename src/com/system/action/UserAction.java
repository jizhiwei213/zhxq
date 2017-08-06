package com.system.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.base.action.BaseAction;
import com.common.util.EmptyFKHandler;
import com.common.util.EncryptKey;
import com.opensymphony.xwork2.ActionContext;
import com.system.model.RoleModel;
import com.system.model.UserModel;
import com.system.query.UserQuery;
import com.system.service.RoleService;
import com.system.service.UserService;

public class UserAction extends BaseAction {
	private static final long serialVersionUID = -4966432939099172304L;

	private UserService userService;
	private RoleService roleService;
	private UserModel userModel;
	private RoleModel roleModel;
	private UserQuery userQuery;
	private File uploadFile;// 上传的文件
	private String uploadFileFileName;// 上传的文件的文件名
	private InputStream inputStream;
	private JSONObject jsonObj=new JSONObject();
	private JSONArray jsonArray=new JSONArray();
	public String userIndex(){
		System.out.println(getRequest().getContextPath());
		return SUCCESS;
	}
	
	public String userList(){
		Integer start=null;
		if(getRows()!=null&&getPage()!=null){
			start=(getPage() - 1) * getRows();
		}
		List<UserModel> users = userService.getUserList(start,getRows(),userQuery);
		int total = userService.getUserCount(userQuery);
		JSONArray rows = new JSONArray();
		for (UserModel user : users) {
			rows.add(user.buildJson());
		}
		jsonObj.put("rows", rows);
		jsonObj.put("total", total);
		return SUCCESS;
	}
	
	public String userEdit(){
		userModel=userService.getUserModelById(userModel.getId());
		
		List<RoleModel> userRoles=roleService.getUserRoleByUserModel(userModel);
		if(userRoles.size()>0){
			ActionContext.getContext().put("userRole", userRoles.get(0));
		}
		
		List<RoleModel> roleList=roleService.getRoleList(null, null);
		ActionContext.getContext().put("roleList", roleList);
		return SUCCESS;
	}
	
	public String userSaveUpdate()throws Exception{
		String qyImg=userModel.getUserPhoto();
		EmptyFKHandler.handle(userModel);
		if(null!=uploadFile)
		{
			InputStream in = new FileInputStream(uploadFile);
			String dir = ServletActionContext.getRequest().getRealPath("/upload");
			String filePage = uploadFileFileName.substring(uploadFileFileName.indexOf("."));
			if(".png.gif.jpg".contains(filePage))
			{
				qyImg = userModel.getUserAccount()+filePage;
				File uploadF = new File(dir, userModel.getUserAccount()+filePage);
				OutputStream out = new FileOutputStream(uploadF);
				byte[] buffer = new byte[1024 * 1024]; 
				int length;            
				while ((length = in.read(buffer)) > 0) 
				{                
					out.write(buffer, 0, length);
				}             
				in.close();            
				out.close();
			}
		}
		userModel.setUserPhoto(qyImg);
		if("".equals(userModel.getId())){
			userModel.setUserPassword(EncryptKey.encrypt("123456"));
			userService.saveUser(userModel);
		}else{
			UserModel user=userService.getUserModelById(userModel.getId());
			if(user!=null){
				userModel.setUserPassword(user.getUserPassword());
			}
			userService.updateUser(userModel);
		}
		//授予角色
		roleService.saveUserRole(userModel,roleModel);
		return SUCCESS;
	}
	
	
	
	public String userInfoIndex(){
		return SUCCESS;
	}
	
	public String userInfoList(){
		UserModel users = (UserModel)getSession().getAttribute("user");
		UserModel userM = userService.getUserModelById(users.getId());
		JSONArray rows = new JSONArray();
		rows.add(userM.buildJson());
		jsonObj.put("rows", rows);
		return SUCCESS;
	}
	
	public String userInfo(){
		userModel=userService.getUserModelById(userModel.getId());
		return SUCCESS;
	}
	
	public String userInfoUpdate()throws Exception{
		String userId = userModel.getId();
		String userName = userModel.getUserName();
		String tel = userModel.getLinkTel();
		String email = userModel.getEmail();
		int sex = userModel.getSex();
		UserModel user = userService.getUserModelById(userId);
		user.setUserName(userName);
		user.setLinkTel(tel);
		user.setEmail(email);
		user.setSex(sex);
		userService.updateUser(user);
		return SUCCESS;
	}
	
	public String userInfo2(){
		userModel=userService.getUserModelById(userModel.getId());
		return SUCCESS;
	}
	
	public String userDelete(){
		String[] ids=getKeys().split(",");
		JSONArray errors=new JSONArray();
		for(String id : ids){
			try {
				userService.deleteUser(id);
			} catch (Exception e) {
				e.printStackTrace();
				UserModel user=userService.getUserModelById(id);
				JSONObject json=new JSONObject();
				json.put("name", user.getUserName());
				errors.add(json);
			}
		}
		jsonObj.put("errors",errors);
		return SUCCESS;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public RoleModel getRoleModel() {
		return roleModel;
	}

	public void setRoleModel(RoleModel roleModel) {
		this.roleModel = roleModel;
	}

	public File getUploadFile()
	{
		return uploadFile;
	}

	public void setUploadFile(File uploadFile)
	{
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName()
	{
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName)
	{
		this.uploadFileFileName = uploadFileFileName;
	}

	public InputStream getInputStream()
	{
		return inputStream;
	}

	public void setInputStream(InputStream inputStream)
	{
		this.inputStream = inputStream;
	}

	public UserQuery getUserQuery() {
		return userQuery;
	}

	public void setUserQuery(UserQuery userQuery) {
		this.userQuery = userQuery;
	}

	public JSONArray getJsonArray()
	{
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray)
	{
		this.jsonArray = jsonArray;
	}
	
}
