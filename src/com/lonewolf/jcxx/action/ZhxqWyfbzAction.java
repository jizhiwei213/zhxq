package com.lonewolf.jcxx.action;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.base.action.BaseAction;
import com.common.util.EmptyFKHandler;
import com.lonewolf.jcxx.service.ZhxqWyfbzService;
import com.lonewolf.jcxx.model.XiaoquModel;
import com.lonewolf.jcxx.model.ZhxqWyfbzModel;
import com.system.model.UserModel;
public class ZhxqWyfbzAction extends BaseAction {
	private static final long serialVersionUID = -4966432939099172304L;

	private ZhxqWyfbzService zhxqWyfbzSevice;
	private ZhxqWyfbzModel zhxqWyfbzModel;
	private JSONObject jsonObj=new JSONObject();
	private JSONArray jsonArray=new JSONArray();
	public String zhxqWyfbzIndex(){
		return SUCCESS;
	}
	
	public String zhxqWyfbzList(){
		Integer start=null;
		if(getRows()!=null&&getPage()!=null){
			start=(getPage() - 1) * getRows();
		}
		List<ZhxqWyfbzModel> ZhxqWyfbzs = zhxqWyfbzSevice.getZhxqWyfbzList(start,getRows(),"-1");
		int total = zhxqWyfbzSevice.getZhxqWyfbzCount("-1");
		JSONArray rows = new JSONArray();
		for (ZhxqWyfbzModel ZhxqWyfbz : ZhxqWyfbzs) {
			rows.add(ZhxqWyfbz.buildJson());
		}
		jsonObj.put("rows", rows);
		jsonObj.put("total", total);
		return SUCCESS;
	}
	
	public String zhxqWyfbzEdit(){
		UserModel use = (UserModel)getSession().getAttribute("user");
		XiaoquModel xiaoqu  = use.getXiaoqu();
		String xiaoquId="-1";
		if(null!=xiaoqu)
		{
			xiaoquId = xiaoqu.getId();
		}
		List<ZhxqWyfbzModel> zhxqWyfbzs = zhxqWyfbzSevice.getZhxqWyfbzList(null,null,xiaoquId);
		if(null==zhxqWyfbzs||zhxqWyfbzs.size()==0)
		{
			if(null!=xiaoqu)
			{
				ZhxqWyfbzModel wyfbz = new ZhxqWyfbzModel();
				wyfbz.setBiaozhun(0d);
				wyfbz.setId("");
				wyfbz.setQitabz(0d);
				wyfbz.setXiaoqu(xiaoqu);
				zhxqWyfbzSevice.saveZhxqWyfbz(wyfbz);
			}
		}
		List<ZhxqWyfbzModel> zhxqWyfbzs2 = zhxqWyfbzSevice.getZhxqWyfbzList(null,null,xiaoquId);
		if(null!=zhxqWyfbzs2&&zhxqWyfbzs2.size()>0)
		{
			zhxqWyfbzModel = (ZhxqWyfbzModel)zhxqWyfbzs2.get(0);
		}
		zhxqWyfbzModel=zhxqWyfbzSevice.getZhxqWyfbzModelById(zhxqWyfbzModel.getId());
		return SUCCESS;
	}
	
	public String zhxqWyfbzSaveUpdate()throws Exception{
		EmptyFKHandler.handle(zhxqWyfbzModel);
		if("".equals(zhxqWyfbzModel.getId())){
			zhxqWyfbzSevice.saveZhxqWyfbz(zhxqWyfbzModel);
		}else{
			zhxqWyfbzSevice.updateZhxqWyfbz(zhxqWyfbzModel);
		}
		return SUCCESS;
	}
	
	public String zhxqWyfbzDelete(){
		String[] ids=getKeys().split(",");
		JSONArray errors=new JSONArray();
		for(String id : ids){
			try {
				zhxqWyfbzSevice.deleteZhxqWyfbz(id);
			} catch (Exception e) {
				e.printStackTrace();
				ZhxqWyfbzModel ZhxqWyfbz=zhxqWyfbzSevice.getZhxqWyfbzModelById(id);
				JSONObject json=new JSONObject();
				json.put("name", ZhxqWyfbz.getRemark());
				errors.add(json);
			}
		}
		jsonObj.put("errors",errors);
		return SUCCESS;
	}
	
	public ZhxqWyfbzModel getZhxqWyfbzModel() {
		return zhxqWyfbzModel;
	}

	public void setZhxqWyfbzModel(ZhxqWyfbzModel zhxqWyfbzModel) {
		this.zhxqWyfbzModel = zhxqWyfbzModel;
	}

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public ZhxqWyfbzService getZhxqWyfbzSevice()
	{
		return zhxqWyfbzSevice;
	}

	public void setZhxqWyfbzSevice(ZhxqWyfbzService zhxqWyfbzSevice)
	{
		this.zhxqWyfbzSevice = zhxqWyfbzSevice;
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
