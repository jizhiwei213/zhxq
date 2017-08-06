package com.lonewolf.jcxx.action;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.base.action.BaseAction;
import com.common.util.EmptyFKHandler;
import com.common.util.EncryptKey;
import com.lonewolf.jcxx.service.GsqUserService;
import com.opensymphony.xwork2.ActionContext;
import com.system.model.RoleModel;
import com.system.model.UserModel;
import com.system.query.UserQuery;
import com.system.service.RoleService;

public class WyryUserAction extends BaseAction {
	private static final long serialVersionUID = -4966432939099172304L;

	private GsqUserService gsqUserService;
	private RoleService roleService;
	private UserModel userModel;
	private RoleModel roleModel;
	private UserQuery userQuery;
	private JSONObject jsonObj=new JSONObject();
	
	public String wyryIndex(){
		return SUCCESS;
	}
	
	public String wyryList(){
		Integer start=null;
		if(getRows()!=null&&getPage()!=null){
			start=(getPage() - 1) * getRows();
		}
		if(null==userQuery)
		{
			userQuery=new UserQuery();
		}
		userQuery.setUserType("2");
		List<UserModel> users = gsqUserService.getUserList(start,getRows(),userQuery);
		int total = gsqUserService.getUserCount(userQuery);
		JSONArray rows = new JSONArray();
		for (UserModel user : users) {
			rows.add(user.buildJson());
		}
		jsonObj.put("rows", rows);
		jsonObj.put("total", total);
		return SUCCESS;
	}
	
	public String wyryEdit(){
		userModel=gsqUserService.getUserModelById(userModel.getId());
		List<RoleModel> userRoles=roleService.getUserRoleByUserModel(userModel);
		if(userRoles.size()>0){
			ActionContext.getContext().put("userRole", userRoles.get(0));
		}
		
		List<RoleModel> roleList=roleService.getRoleListByRoleId(null, null, "20000000000000000000000000000000");
		ActionContext.getContext().put("roleList", roleList);
		return SUCCESS;
	}
	
	public String wyrySaveUpdate()throws Exception{
		EmptyFKHandler.handle(userModel);
		if("".equals(userModel.getId())){
			userModel.setUserPassword(EncryptKey.encrypt("123456"));
			gsqUserService.saveUser(userModel);
		}else{
			UserModel user=gsqUserService.getUserModelById(userModel.getId());
			if(user!=null){
				userModel.setUserPassword(user.getUserPassword());
			}
			gsqUserService.updateUser(userModel);
		}
		roleService.saveUserRole(userModel,roleModel);
		return SUCCESS;
	}
	
	public String wyryDelete(){
		String[] ids=getKeys().split(",");
		JSONArray errors=new JSONArray();
		for(String id : ids){
			try {
				gsqUserService.deleteUser(id);
			} catch (Exception e) {
				e.printStackTrace();
				UserModel user=gsqUserService.getUserModelById(id);
				JSONObject json=new JSONObject();
				json.put("name", user.getUserName());
				errors.add(json);
			}
		}
		jsonObj.put("errors",errors);
		return SUCCESS;
	}
	
	public String wyryStop(){
		String[] ids=getKeys().split(",");
		JSONArray errors=new JSONArray();
		for(String id : ids){
			try {
				UserModel user=gsqUserService.getUserModelById(id);
				user.setIsStop(1);
				gsqUserService.updateUser(user);
			} catch (Exception e) {
				e.printStackTrace();
				UserModel user=gsqUserService.getUserModelById(id);
				JSONObject json=new JSONObject();
				json.put("name", user.getUserName());
				errors.add(json);
			}
		}
		jsonObj.put("errors",errors);
		return SUCCESS;
	}
	
	public String wyryStart(){
		String[] ids=getKeys().split(",");
		JSONArray errors=new JSONArray();
		for(String id : ids){
			try {
				UserModel user=gsqUserService.getUserModelById(id);
				user.setIsStop(0);
				gsqUserService.updateUser(user);
			} catch (Exception e) {
				e.printStackTrace();
				UserModel user=gsqUserService.getUserModelById(id);
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

	public UserQuery getUserQuery() {
		return userQuery;
	}

	public void setUserQuery(UserQuery userQuery) {
		this.userQuery = userQuery;
	}

	public GsqUserService getGsqUserService()
	{
		return gsqUserService;
	}

	public void setGsqUserService(GsqUserService gsqUserService)
	{
		this.gsqUserService = gsqUserService;
	}
	
}
