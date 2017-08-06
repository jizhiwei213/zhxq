package com.lonewolf.jcxx.action;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.base.action.BaseAction;
import com.common.util.EmptyFKHandler;
import com.lonewolf.jcxx.query.ZhxqShenShiquQuery;
import com.lonewolf.jcxx.service.ZhxqShenService;
import com.lonewolf.jcxx.model.ZhxqShenModel;
public class ZhxqShenAction extends BaseAction {
	private static final long serialVersionUID = -4966432939099172304L;

	private ZhxqShenService zhxqShenService;
	private ZhxqShenModel ZhxqShenModel;
	private ZhxqShenShiquQuery zhxqShenShiquQuery;
	private JSONObject jsonObj=new JSONObject();
	private JSONArray jsonArray=new JSONArray();
	public String zhxqShenIndex(){
		return SUCCESS;
	}
	
	public String zhxqShenList(){
		Integer start=null;
		if(getRows()!=null&&getPage()!=null){
			start=(getPage() - 1) * getRows();
		}
		List<ZhxqShenModel> ZhxqShens = zhxqShenService.getZhxqShenList(start,getRows(),zhxqShenShiquQuery);
		int total = zhxqShenService.getZhxqShenCount(zhxqShenShiquQuery);
		JSONArray rows = new JSONArray();
		for (ZhxqShenModel ZhxqShen : ZhxqShens) {
			rows.add(ZhxqShen.buildJson());
		}
		jsonObj.put("rows", rows);
		jsonObj.put("total", total);
		return SUCCESS;
	}
	
	public String zhxqShenEdit(){
		ZhxqShenModel=zhxqShenService.getZhxqShenModelById(ZhxqShenModel.getId());
		return SUCCESS;
	}
	
	public String zhxqShenSaveUpdate()throws Exception{
		EmptyFKHandler.handle(ZhxqShenModel);
		if("".equals(ZhxqShenModel.getId())){
			zhxqShenService.saveZhxqShen(ZhxqShenModel);
		}else{
			zhxqShenService.updateZhxqShen(ZhxqShenModel);
		}
		return SUCCESS;
	}
	
	public String zhxqShenDelete(){
		String[] ids=getKeys().split(",");
		JSONArray errors=new JSONArray();
		for(String id : ids){
			try {
				zhxqShenService.deleteZhxqShen(id);
			} catch (Exception e) {
				e.printStackTrace();
				ZhxqShenModel ZhxqShen=zhxqShenService.getZhxqShenModelById(id);
				JSONObject json=new JSONObject();
				json.put("name", ZhxqShen.getName());
				errors.add(json);
			}
		}
		jsonObj.put("errors",errors);
		return SUCCESS;
	}
	
	public String comboBoxShenJsonArrayData(){
		List<ZhxqShenModel> list=zhxqShenService.getZhxqShenList(null, null, null);
		for (ZhxqShenModel dic : list) {
			JSONObject json=new JSONObject();
			json.put("id", dic.getId());
			json.put("text", dic.getName());
			jsonArray.add(json);
		}
		return SUCCESS;
	}
	
	public ZhxqShenModel getZhxqShenModel() {
		return ZhxqShenModel;
	}

	public void setZhxqShenModel(ZhxqShenModel ZhxqShenModel) {
		this.ZhxqShenModel = ZhxqShenModel;
	}

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public void setJsonObj(JSONObject jsonObj)
	{
		this.jsonObj = jsonObj;
	}

	public ZhxqShenService getZhxqShenService()
	{
		return zhxqShenService;
	}

	public void setZhxqShenService(ZhxqShenService zhxqShenService)
	{
		this.zhxqShenService = zhxqShenService;
	}

	public ZhxqShenShiquQuery getZhxqShenShiquQuery()
	{
		return zhxqShenShiquQuery;
	}

	public void setZhxqShenShiquQuery(ZhxqShenShiquQuery zhxqShenShiquQuery)
	{
		this.zhxqShenShiquQuery = zhxqShenShiquQuery;
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
