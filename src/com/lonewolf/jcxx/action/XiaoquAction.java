package com.lonewolf.jcxx.action;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.base.action.BaseAction;
import com.common.util.EmptyFKHandler;
import com.lonewolf.jcxx.service.XiaoquService;
import com.lonewolf.jcxx.model.XiaoquModel;
import com.lonewolf.jcxx.query.XiaoquQuery;
public class XiaoquAction extends BaseAction {
	private static final long serialVersionUID = -4966432939099172304L;

	private XiaoquService xiaoquService;
	private XiaoquModel xiaoquModel;
	private XiaoquQuery xiaoquQuery;
	private JSONObject jsonObj=new JSONObject();
	private JSONArray jsonArray=new JSONArray();
	public String xiaoquIndex(){
		return SUCCESS;
	}
	
	public String xiaoquList(){
		Integer start=null;
		if(getRows()!=null&&getPage()!=null){
			start=(getPage() - 1) * getRows();
		}
		List<XiaoquModel> Xiaoqus = xiaoquService.getXiaoquList(start,getRows(),xiaoquQuery);
		int total = xiaoquService.getXiaoquCount(xiaoquQuery);
		JSONArray rows = new JSONArray();
		for (XiaoquModel Xiaoqu : Xiaoqus) {
			rows.add(Xiaoqu.buildJson());
		}
		jsonObj.put("rows", rows);
		jsonObj.put("total", total);
		return SUCCESS;
	}
	
	public String xiaoquEdit(){
		xiaoquModel=xiaoquService.getXiaoquModelById(xiaoquModel.getId());
		return SUCCESS;
	}
	
	public String xiaoquSaveUpdate()throws Exception{
		EmptyFKHandler.handle(xiaoquModel);
		if("".equals(xiaoquModel.getId())){
			xiaoquService.saveXiaoqu(xiaoquModel);
		}else{
			xiaoquService.updateXiaoqu(xiaoquModel);
		}
		return SUCCESS;
	}
	
	public String xiaoquDelete(){
		String[] ids=getKeys().split(",");
		JSONArray errors=new JSONArray();
		for(String id : ids){
			try {
				xiaoquService.deleteXiaoqu(id);
			} catch (Exception e) {
				e.printStackTrace();
				XiaoquModel Xiaoqu=xiaoquService.getXiaoquModelById(id);
				JSONObject json=new JSONObject();
				json.put("name", Xiaoqu.getXiaoquName());
				errors.add(json);
			}
		}
		jsonObj.put("errors",errors);
		return SUCCESS;
	}
	
	public String comboBoxXiaoquJsonArrayData(){
		List<XiaoquModel> list=xiaoquService.getXiaoquList(null, null, null);
		for (XiaoquModel dic : list) {
			JSONObject json=new JSONObject();
			json.put("id", dic.getId());
			json.put("text", dic.getXiaoquName());
			jsonArray.add(json);
		}
		return SUCCESS;
	}
	
	public XiaoquModel getXiaoquModel() {
		return xiaoquModel;
	}

	public void setXiaoquModel(XiaoquModel XiaoquModel) {
		this.xiaoquModel = XiaoquModel;
	}

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public XiaoquService getXiaoquService()
	{
		return xiaoquService;
	}

	public void setXiaoquService(XiaoquService xiaoquService)
	{
		this.xiaoquService = xiaoquService;
	}

	public JSONArray getJsonArray()
	{
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray)
	{
		this.jsonArray = jsonArray;
	}

	public XiaoquQuery getXiaoquQuery()
	{
		return xiaoquQuery;
	}

	public void setXiaoquQuery(XiaoquQuery xiaoquQuery)
	{
		this.xiaoquQuery = xiaoquQuery;
	}

	public void setJsonObj(JSONObject jsonObj)
	{
		this.jsonObj = jsonObj;
	}
}
