package com.system.action;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.base.action.BaseAction;
import com.system.model.FunctionModel;
import com.system.model.OperationModel;
import com.system.model.RoleModel;
import com.system.service.FunctionService;
import com.system.service.OperationService;
import com.system.service.RoleService;

public class RoleAction extends BaseAction {
	private static final long serialVersionUID = 9187307335989612915L;

	private RoleService roleService;
	private FunctionService functionService;
	private OperationService operationService;
	
	private RoleModel roleModel;
	private JSONObject jsonObj=new JSONObject();
	private JSONArray jsonArray=new JSONArray();
	
	private String funIds;
	private String operIds;

	public String roleIndex() {
		return SUCCESS;
	}

	public String roleList() {
		Integer start=null;
		if(getRows()!=null&&getPage()!=null){
			start=(getPage() - 1) * getRows();
		}
		List<RoleModel> roles = roleService.getRoleList(start,getRows());
		int total = roleService.getRoleCount();
		JSONArray rows = new JSONArray();
		for (RoleModel role : roles) {
			rows.add(role.buildJson());
		}
		jsonObj.put("rows", rows);
		jsonObj.put("total", total);
		return SUCCESS;
	}
	
	public String roleEdit(){
		roleModel=roleService.getRoleModelById(roleModel.getId());
		return SUCCESS;
	}
	
	public String roleSaveUpdate(){
		String[] funIdArray="".equals(funIds)||funIds==null?null:funIds.split(",");
		String[] operIdArray="".equals(operIds)||operIds==null?null:operIds.split(",");
		roleService.saveUpdateRole(roleModel,funIdArray,operIdArray);
		return SUCCESS;
	}
	
	public String roleDelete(){
		JSONArray errors=new JSONArray();
		try {
			roleService.deleteRole(roleModel.getId());
		} catch (Exception e) {
			//e.printStackTrace();
			RoleModel role=roleService.getRoleModelById(roleModel.getId());
			//System.out.println("角色 "+role.getRoleName()+" 删除失败");
			JSONObject json=new JSONObject();
			json.put("name", role.getRoleName());
			errors.add(json);
		}
		jsonObj.put("errors",errors);
		return SUCCESS;
	}
	
	public String funcTree(){
		List<FunctionModel> functionList=functionService.getAllFunction();
		List<String> roleFunctionIdList=functionService.getFunctionIdListByRole(roleModel);
		List<String> roleOperationIdList=operationService.getOperationIdListByRole(roleModel);
		for (FunctionModel functionModel : functionList) {
			JSONObject node=new JSONObject();
			node.put("id",functionModel.getId());
			node.put("name",functionModel.getFunName());
			if(functionModel.getParent()!=null){
				node.put("pId",functionModel.getParent().getId());
			}else{
				node.put("pId","");
			}
			node.put("open", functionModel.getType()==0?true:false);//模块展开，操作收缩
			if(roleFunctionIdList.contains(functionModel.getId())){
				node.put("checked", true);
			}
			node.put("type","function");//自定义属性
			jsonArray.add(node);
			
			//查询对应的按钮
			List<OperationModel> operationList=operationService.findOperationListByFunction(functionModel);
			for (OperationModel operationModel : operationList) {
				JSONObject operationNode=new JSONObject();
				operationNode.put("id",operationModel.getId());
				operationNode.put("name",operationModel.getName());
				if(operationModel.getFunction()!=null){
					operationNode.put("pId",operationModel.getFunction().getId());
				}else{
					operationNode.put("pId","");
				}
				if(roleOperationIdList.contains(operationModel.getId())){
					operationNode.put("checked",true);
				}
				operationNode.put("type","operation");//自定义属性
				jsonArray.add(operationNode);
			}
		}
		return SUCCESS;
	}
	
	
//	public String funcTree(){
//		List<String> roleFunctionIdList=functionService.getLastLevelFunctionListByRole(roleModel); //角色对应的最下级菜单项
//		
//		List<FunctionModel> firstLevels=functionService.getFunctionListByParent(null);//一级菜单项
//		List<TreeNodeModel> treeNodeList=new ArrayList<TreeNodeModel>();
//		buildFunctionTree(firstLevels, treeNodeList, roleFunctionIdList);
//		for (TreeNodeModel treeNode : treeNodeList) {
//			jsonArray.add(treeNode.buildJson());
//		}
//		return SUCCESS;
//	}
//	
//	/**
//	 * 
//	 * @param parentFunctionList 父节点对象集合
//	 * @param treeNodeList 父节点对象对应的TreeNodeModel集合
//	 * @param checkedFunctionList 需要勾选的节点对象的ID集合
//	 */
//	private void buildFunctionTree(List<FunctionModel> parentFunctionList,List<TreeNodeModel> treeNodeList,List<String> checkedFunctionList){
//		for (FunctionModel function : parentFunctionList) {
//			TreeNodeModel node=function.buildTreeJsonModel();
//			if(checkedFunctionList.contains(function.getId())){
//				node.setChecked(true);
//			}
//			treeNodeList.add(node);
//			List<FunctionModel> children=functionService.getFunctionListByParent(function);
//			buildFunctionTree(children,node.getChildren(),checkedFunctionList);
//		}
//	}
	
	public RoleModel getRoleModel() {
		return roleModel;
	}

	public void setRoleModel(RoleModel roleModel) {
		this.roleModel = roleModel;
	}

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}

	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}

	public String getFunIds() {
		return funIds;
	}

	public void setFunIds(String funIds) {
		this.funIds = funIds;
	}

	public String getOperIds() {
		return operIds;
	}

	public void setOperIds(String operIds) {
		this.operIds = operIds;
	}
}
