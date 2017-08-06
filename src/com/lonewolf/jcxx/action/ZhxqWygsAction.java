package com.lonewolf.jcxx.action;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.base.action.BaseAction;
import com.common.util.EmptyFKHandler;
import com.lonewolf.jcxx.service.ZhxqWygsService;
import com.lonewolf.jcxx.model.ZhxqWygsModel;
import com.lonewolf.jcxx.query.ZhxqWygsQuery;
public class ZhxqWygsAction extends BaseAction {
	private static final long serialVersionUID = -4966432939099172304L;

	private ZhxqWygsService zhxqWygsSevice;
	private ZhxqWygsModel zhxqWygsModel;
	private ZhxqWygsQuery zhxqWygsQuery;
	private JSONObject jsonObj=new JSONObject();
	private JSONArray jsonArray=new JSONArray();
	public String zhxqWygsIndex(){
		return SUCCESS;
	}
	
	public String zhxqWygsList(){
		Integer start=null;
		if(getRows()!=null&&getPage()!=null){
			start=(getPage() - 1) * getRows();
		}
		List<ZhxqWygsModel> ZhxqWygss = zhxqWygsSevice.getZhxqWygsList(start,getRows(),zhxqWygsQuery);
		int total = zhxqWygsSevice.getZhxqWygsCount(zhxqWygsQuery);
		JSONArray rows = new JSONArray();
		for (ZhxqWygsModel ZhxqWygs : ZhxqWygss) {
			rows.add(ZhxqWygs.buildJson());
		}
		jsonObj.put("rows", rows);
		jsonObj.put("total", total);
		return SUCCESS;
	}
	
	public String zhxqWygsEdit(){
		zhxqWygsModel=zhxqWygsSevice.getZhxqWygsModelById(zhxqWygsModel.getId());
		return SUCCESS;
	}
	
	public String zhxqWygsSaveUpdate()throws Exception{
		EmptyFKHandler.handle(zhxqWygsModel);
		if("".equals(zhxqWygsModel.getId())){
			zhxqWygsSevice.saveZhxqWygs(zhxqWygsModel);
		}else{
			zhxqWygsSevice.updateZhxqWygs(zhxqWygsModel);
		}
		return SUCCESS;
	}
	
	public String zhxqWygsDelete(){
		String[] ids=getKeys().split(",");
		JSONArray errors=new JSONArray();
		for(String id : ids){
			try {
				zhxqWygsSevice.deleteZhxqWygs(id);
			} catch (Exception e) {
				e.printStackTrace();
				ZhxqWygsModel ZhxqWygs=zhxqWygsSevice.getZhxqWygsModelById(id);
				JSONObject json=new JSONObject();
				json.put("name", ZhxqWygs.getName());
				errors.add(json);
			}
		}
		jsonObj.put("errors",errors);
		return SUCCESS;
	}
	
	public String comboBoxWygsJsonArrayData(){
		List<ZhxqWygsModel> list=zhxqWygsSevice.getZhxqWygsList(null, null, null);
		for (ZhxqWygsModel dic : list) {
			JSONObject json=new JSONObject();
			json.put("id", dic.getId());
			json.put("text", dic.getName());
			jsonArray.add(json);
		}
		return SUCCESS;
	}
	public ZhxqWygsModel getZhxqWygsModel() {
		return zhxqWygsModel;
	}

	public void setZhxqWygsModel(ZhxqWygsModel zhxqWygsModel) {
		this.zhxqWygsModel = zhxqWygsModel;
	}

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public ZhxqWygsService getZhxqWygsSevice()
	{
		return zhxqWygsSevice;
	}

	public void setZhxqWygsSevice(ZhxqWygsService zhxqWygsSevice)
	{
		this.zhxqWygsSevice = zhxqWygsSevice;
	}

	public ZhxqWygsQuery getZhxqWygsQuery()
	{
		return zhxqWygsQuery;
	}

	public void setZhxqWygsQuery(ZhxqWygsQuery ZhxqWygsQuery)
	{
		this.zhxqWygsQuery = ZhxqWygsQuery;
	}

	public void setJsonObj(JSONObject jsonObj)
	{
		this.jsonObj = jsonObj;
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
