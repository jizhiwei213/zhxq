package com.lonewolf.jcxx.action;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.base.action.BaseAction;
import com.common.util.EmptyFKHandler;
import com.lonewolf.jcxx.query.ZhxqShenShiquQuery;
import com.lonewolf.jcxx.service.ZhxqQuService;
import com.lonewolf.jcxx.model.ZhxqQuModel;
public class ZhxqQuAction extends BaseAction {
	private static final long serialVersionUID = -4966432939099172304L;

	private ZhxqQuService zhxqQuService;
	private ZhxqQuModel zhxqQuModel;
	private ZhxqShenShiquQuery zhxqShenShiquQuery;
	private JSONObject jsonObj=new JSONObject();
	private JSONArray jsonArray=new JSONArray();
	public String zhxqQuIndex(){
		return SUCCESS;
	}
	
	public String zhxqQuList(){
		Integer start=null;
		if(getRows()!=null&&getPage()!=null){
			start=(getPage() - 1) * getRows();
		}
		List<ZhxqQuModel> ZhxqQus = zhxqQuService.getZhxqQuList(start,getRows(),zhxqShenShiquQuery);
		int total = zhxqQuService.getZhxqQuCount(zhxqShenShiquQuery);
		JSONArray rows = new JSONArray();
		for (ZhxqQuModel ZhxqQu : ZhxqQus) {
			rows.add(ZhxqQu.buildJson());
		}
		jsonObj.put("rows", rows);
		jsonObj.put("total", total);
		return SUCCESS;
	}
	
	public String zhxqQuEdit(){
		zhxqQuModel=zhxqQuService.getZhxqQuModelById(zhxqQuModel.getId());
		return SUCCESS;
	}
	
	public String zhxqQuSaveUpdate()throws Exception{
		EmptyFKHandler.handle(zhxqQuModel);
		if("".equals(zhxqQuModel.getId())){
			zhxqQuService.saveZhxqQu(zhxqQuModel);
		}else{
			zhxqQuService.updateZhxqQu(zhxqQuModel);
		}
		return SUCCESS;
	}
	
	public String zhxqQuDelete(){
		String[] ids=getKeys().split(",");
		JSONArray errors=new JSONArray();
		for(String id : ids){
			try {
				zhxqQuService.deleteZhxqQu(id);
			} catch (Exception e) {
				e.printStackTrace();
				ZhxqQuModel ZhxqQu=zhxqQuService.getZhxqQuModelById(id);
				JSONObject json=new JSONObject();
				json.put("name", ZhxqQu.getName());
				errors.add(json);
			}
		}
		jsonObj.put("errors",errors);
		return SUCCESS;
	}
	
	public String comboBoxQuJsonArrayData(){
		if(null==zhxqShenShiquQuery)
		{
			zhxqShenShiquQuery=new ZhxqShenShiquQuery();
		}
		List<ZhxqQuModel> list=zhxqQuService.getZhxqQuByShiIdList(zhxqShenShiquQuery.getShiId());
		for (ZhxqQuModel dic : list) {
			JSONObject json=new JSONObject();
			json.put("id", dic.getId());
			json.put("text", dic.getName());
			jsonArray.add(json);
		}
		return SUCCESS;
	}
	
	public ZhxqQuModel getZhxqQuModel() {
		return zhxqQuModel;
	}

	public void setZhxqQuModel(ZhxqQuModel ZhxqQuModel) {
		this.zhxqQuModel = ZhxqQuModel;
	}

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public void setJsonObj(JSONObject jsonObj)
	{
		this.jsonObj = jsonObj;
	}

	public void setZhxqShenShiquQuery(ZhxqShenShiquQuery ZhxqShenShiquQuery)
	{
		this.zhxqShenShiquQuery = ZhxqShenShiquQuery;
	}

	public JSONArray getJsonArray()
	{
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray)
	{
		this.jsonArray = jsonArray;
	}

	public ZhxqQuService getZhxqQuService()
	{
		return zhxqQuService;
	}

	public void setZhxqQuService(ZhxqQuService zhxqQuService)
	{
		this.zhxqQuService = zhxqQuService;
	}

	public ZhxqShenShiquQuery getZhxqShenShiquQuery()
	{
		return zhxqShenShiquQuery;
	}
	
	
}
