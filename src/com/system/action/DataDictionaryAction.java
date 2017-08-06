package com.system.action;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.base.action.BaseAction;
import com.common.util.EmptyFKHandler;
import com.system.model.DataDictionaryModel;
import com.system.service.DataDictionaryService;

public class DataDictionaryAction extends BaseAction {
	private static final long serialVersionUID = 8898376741963470711L;

	private DataDictionaryModel dataDictionary;
	private DataDictionaryService dataDictionaryService;

	private JSONObject jsonObj = new JSONObject();
	private JSONArray jsonArray = new JSONArray();
	private String isBlank;//返回数据字典的数据是否带有“请选择”
	public String dataDictionaryIndex(){
		return SUCCESS;
	}
	
	public String dataDictionaryList(){
		Integer start=null;
		if(getRows()!=null&&getPage()!=null){
			start=(getPage() - 1) * getRows();
		}
		List<DataDictionaryModel> listData=dataDictionaryService.getDataDictionaryList(dataDictionary.getDataType(),start,getRows());
		int total=dataDictionaryService.getDataDictionaryCount(dataDictionary.getDataType());
		JSONArray rows = new JSONArray();
		for (DataDictionaryModel dataDictionary : listData) {
			rows.add(dataDictionary.buildJson());
		}
		jsonObj.put("rows", rows);
		jsonObj.put("total", total);
		return SUCCESS;
	}
	
	public String dataDictionaryEdit(){
		String id=dataDictionary.getId();
		if(id!=null&&!"".equals(id)){
			//修改
			dataDictionary=dataDictionaryService.getDataDictionaryById(id);
		}else{
			//新增
			//查询父类
			if(dataDictionary.getParent()==null||"".equals(dataDictionary.getParent().getId())){
				dataDictionary.setParent(dataDictionaryService.getParentByDataType(dataDictionary.getDataType()));
			}
		}
		return SUCCESS;
	}
	
	public String dataDictionarySaveUpdate(){
		EmptyFKHandler.handle(dataDictionary);
		String id=dataDictionary.getId();
		if(id!=null&&!"".equals(id)){
			//update
			dataDictionaryService.updateDataDictionary(dataDictionary);
		}else{
			//save
			dataDictionaryService.saveDataDictionary(dataDictionary);
		}
		return SUCCESS;
	}
	
	public String dataDictionaryDelete(){
		String[] ids=getKeys().split(",");
		JSONArray errors=new JSONArray();
		for(String id : ids){
			try {
				dataDictionaryService.deleteDataDictionary(id);
			} catch (Exception e) {
				//e.printStackTrace();
				DataDictionaryModel dic=dataDictionaryService.getDataDictionaryById(id);
				System.out.println("数据字典  "+dic.getName()+" 删除失败");
				JSONObject json=new JSONObject();
				json.put("name", dic.getName());
				errors.add(json);
			}
		}
		jsonObj.put("errors",errors);
		return SUCCESS;
	}
	
	public String comboBoxJsonArrayData(){
		List<DataDictionaryModel> list=dataDictionaryService.getDateDictionaryListByDataType(dataDictionary.getDataType());
		if("true".equals(isBlank)){
			JSONObject json=new JSONObject();
			json.put("id", "");
			json.put("text", "---请选择---");
			jsonArray.add(json);
		}
		for (DataDictionaryModel dic : list) {
			JSONObject json=new JSONObject();
			json.put("id", dic.getId());
			json.put("text", dic.getName());
			jsonArray.add(json);
		}
		return SUCCESS;
	}
	
	public DataDictionaryModel getDataDictionary() {
		return dataDictionary;
	}

	public void setDataDictionary(DataDictionaryModel dataDictionary) {
		this.dataDictionary = dataDictionary;
	}

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
		this.dataDictionaryService = dataDictionaryService;
	}

	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public String getIsBlank() {
		return isBlank;
	}

	public void setIsBlank(String isBlank) {
		this.isBlank = isBlank;
	}
	
	
}
