package com.system.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.base.action.BaseAction;
import com.system.model.DepartmentModel;
import com.system.model.TreeNodeModel;
import com.system.service.DeptService;

public class DeptAction extends BaseAction {
	private static final long serialVersionUID = 4388533727858709220L;

	private DeptService deptService;
	private DepartmentModel deptModel; 
	
	private JSONObject jsonObj=new JSONObject();
	private JSONArray jsonArray=new JSONArray();
	
	public String deptIndex(){
		return SUCCESS;
	}
	
	public String deptList(){
		List<DepartmentModel> deptList=deptService.getDeptList();
		JSONArray rows = new JSONArray();
		for (DepartmentModel dept : deptList) {
			rows.add(dept.buildJson());
		}
		jsonObj.put("rows", rows);
		return SUCCESS;
	}
	
	public String deptEdit(){
		if(deptModel!=null&&deptModel.getId()!=null&&!"".equals(deptModel.getId())){
			deptModel=deptService.getDeptModelById(deptModel.getId());
		}
		return SUCCESS;
	}
	
	public String deptDelete(){
		JSONArray errors=new JSONArray();
		try {
			deptService.deleteDept(deptModel.getId());
		} catch (Exception e) {
			//e.printStackTrace();
			DepartmentModel dept=deptService.getDeptModelById(deptModel.getId());
			System.out.println("部门  "+dept.getDeptName()+" 删除失败");
			JSONObject json=new JSONObject();
			json.put("name", dept.getDeptName());
			errors.add(json);
		}
		jsonObj.put("errors",errors);
		return SUCCESS;
	}
	
	public String deptSaveUpdate(){
		if(!"".equals(deptModel.getId())){
			deptService.updateDept(deptModel);
		}else{
			deptService.saveDept(deptModel);
		}
		return SUCCESS;
	}
	
	public String deptTree(){
		List<DepartmentModel> firstLevels=deptService.getDeptListByParent(null);//一级部门
		List<TreeNodeModel> treeNodeList=new ArrayList<TreeNodeModel>();
		buildDeptTree(firstLevels, treeNodeList);
		for (TreeNodeModel treeNode : treeNodeList) {
			jsonArray.add(treeNode.buildJson());
		}
		
		return SUCCESS;
	}
	
	public String deptTree2(){
		List<DepartmentModel> firstLevels=deptService.getDeptList();//一级部门
		for (DepartmentModel treeNode : firstLevels) {
			JSONObject json=new JSONObject();
			json.put("id", treeNode.getId());
			json.put("text", treeNode.getDeptName());
			jsonArray.add(json);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 
	 * @param deptList 父节点对象集合
	 * @param treeNodeList 父节点对象对应的TreeNodeModel集合
	 */
	private void buildDeptTree(List<DepartmentModel> deptList,List<TreeNodeModel> treeNodeList){
		for (DepartmentModel dept : deptList) {
			TreeNodeModel node=dept.buildTreeJsonModel();
			
			treeNodeList.add(node);
			List<DepartmentModel> children=deptService.getDeptListByParent(dept);
			buildDeptTree(children,node.getChildren());
		}
	}

	public DepartmentModel getDeptModel() {
		return deptModel;
	}

	public void setDeptModel(DepartmentModel deptModel) {
		this.deptModel = deptModel;
	}

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public JSONArray getJsonArray() {
		return jsonArray;
	}
	
}
