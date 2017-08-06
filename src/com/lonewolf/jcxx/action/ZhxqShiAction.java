package com.lonewolf.jcxx.action;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.base.action.BaseAction;
import com.common.util.EmptyFKHandler;
import com.lonewolf.jcxx.query.ZhxqShenShiquQuery;
import com.lonewolf.jcxx.service.ZhxqShiService;
import com.lonewolf.jcxx.model.ZhxqShiModel;
public class ZhxqShiAction extends BaseAction {
	private static final long serialVersionUID = -4966432939099172304L;

	private ZhxqShiService zhxqShiService;
	private ZhxqShiModel zhxqShiModel;
	private ZhxqShenShiquQuery zhxqShenShiquQuery;
	private JSONObject jsonObj=new JSONObject();
	private JSONArray jsonArray=new JSONArray();
	public String zhxqShiIndex(){
		return SUCCESS;
	}
	
	public String zhxqShiList(){
		Integer start=null;
		if(getRows()!=null&&getPage()!=null){
			start=(getPage() - 1) * getRows();
		}
		List<ZhxqShiModel> ZhxqShis = zhxqShiService.getZhxqShiList(start,getRows(),zhxqShenShiquQuery);
		int total = zhxqShiService.getZhxqShiCount(zhxqShenShiquQuery);
		JSONArray rows = new JSONArray();
		for (ZhxqShiModel ZhxqShi : ZhxqShis) {
			rows.add(ZhxqShi.buildJson());
		}
		jsonObj.put("rows", rows);
		jsonObj.put("total", total);
		return SUCCESS;
	}
	
	public String zhxqShiEdit(){
		zhxqShiModel=zhxqShiService.getZhxqShiModelById(zhxqShiModel.getId());
		return SUCCESS;
	}
	
	public String zhxqShiSaveUpdate()throws Exception{
		EmptyFKHandler.handle(zhxqShiModel);
		if("".equals(zhxqShiModel.getId())){
			zhxqShiService.saveZhxqShi(zhxqShiModel);
		}else{
			zhxqShiService.updateZhxqShi(zhxqShiModel);
		}
		return SUCCESS;
	}
	
	public String zhxqShiDelete(){
		String[] ids=getKeys().split(",");
		JSONArray errors=new JSONArray();
		for(String id : ids){
			try {
				zhxqShiService.deleteZhxqShi(id);
			} catch (Exception e) {
				e.printStackTrace();
				ZhxqShiModel ZhxqShi=zhxqShiService.getZhxqShiModelById(id);
				JSONObject json=new JSONObject();
				json.put("name", ZhxqShi.getName());
				errors.add(json);
			}
		}
		jsonObj.put("errors",errors);
		return SUCCESS;
	}
	
	public String comboBoxShiJsonArrayData(){
		if(null==zhxqShenShiquQuery)
		{
			zhxqShenShiquQuery=new ZhxqShenShiquQuery();
		}
		List<ZhxqShiModel> list=zhxqShiService.getZhxqShiByShenIdList(zhxqShenShiquQuery.getShenId());
		for (ZhxqShiModel dic : list) {
			JSONObject json=new JSONObject();
			json.put("id", dic.getId());
			json.put("text", dic.getName());
			jsonArray.add(json);
		}
		return SUCCESS;
	}
	
	public ZhxqShiModel getZhxqShiModel() {
		return zhxqShiModel;
	}

	public void setZhxqShiModel(ZhxqShiModel ZhxqShiModel) {
		this.zhxqShiModel = ZhxqShiModel;
	}

	public JSONObject getJsonObj() {
		return jsonObj;
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

	public ZhxqShiService getZhxqShiService()
	{
		return zhxqShiService;
	}

	public void setZhxqShiService(ZhxqShiService zhxqShiService)
	{
		this.zhxqShiService = zhxqShiService;
	}

	public ZhxqShenShiquQuery getZhxqShenShiquQuery()
	{
		return zhxqShenShiquQuery;
	}

	public void setZhxqShenShiquQuery(ZhxqShenShiquQuery zhxqShenShiquQuery)
	{
		this.zhxqShenShiquQuery = zhxqShenShiquQuery;
	}
	
}
