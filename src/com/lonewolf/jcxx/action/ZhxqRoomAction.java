package com.lonewolf.jcxx.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.base.action.BaseAction;
import com.common.util.EmptyFKHandler;
import com.lonewolf.jcxx.service.XiaoquService;
import com.lonewolf.jcxx.service.ZhxqRoomService;
import com.lonewolf.jcxx.model.XiaoquModel;
import com.lonewolf.jcxx.model.ZhxqRoomModel;
import com.lonewolf.jcxx.query.ZhxqRoomQuery;
import com.system.model.UserModel;
import com.system.service.UserService;
public class ZhxqRoomAction extends BaseAction {
	private static final long serialVersionUID = -4966432939099172304L;
	private XiaoquService xiaoquService;
	private ZhxqRoomService zhxqRoomService;
	private UserService userService;
	private ZhxqRoomModel zhxqRoomModel;
	private ZhxqRoomQuery zhxqRoomQuery;
	private JSONObject jsonObj=new JSONObject();
	private JSONArray jsonArray=new JSONArray();
	
	private InputStream inputStream;
	
	private File inputExcel;
	public String zhxqRoomIndex(){
		return SUCCESS;
	}
	public String zhxqRoomImport(){
		return SUCCESS;
	}

	public String zhxqRoomList(){
		Integer start=null;
		if(getRows()!=null&&getPage()!=null){
			start=(getPage() - 1) * getRows();
		}
		if(null==zhxqRoomQuery)
		{
			zhxqRoomQuery = new ZhxqRoomQuery();
		}
		UserModel use = (UserModel)getSession().getAttribute("user");
		XiaoquModel xiaoqu  = use.getXiaoqu();
		if(null!=xiaoqu)
		{
			zhxqRoomQuery.setXiaoquId(xiaoqu.getId());
		}
		List<ZhxqRoomModel> zhxqRooms = zhxqRoomService.getZhxqRoomList(start,getRows(),zhxqRoomQuery);
		int total = zhxqRoomService.getZhxqRoomCount(zhxqRoomQuery);
		System.out.println(zhxqRooms.size());
		JSONArray rows = new JSONArray();
		for (ZhxqRoomModel zhxqRoom : zhxqRooms) {
			rows.add(zhxqRoom.buildJson());
		}
		jsonObj.put("rows", rows);
		jsonObj.put("total", total);
		return SUCCESS;
	}
	
	public String zhxqRoomByYzIndex(){
		return SUCCESS;
	}
	
	public String zhxqRoomByYzList(){
		Integer start=null;
		if(getRows()!=null&&getPage()!=null){
			start=(getPage() - 1) * getRows();
		}
		if(null==zhxqRoomQuery)
		{
			zhxqRoomQuery = new ZhxqRoomQuery();
		}
		UserModel use = (UserModel)getSession().getAttribute("user");
		if(null!=use)
		{
			zhxqRoomQuery.setYezhuId(use.getId());
		}
		List<ZhxqRoomModel> zhxqRooms = zhxqRoomService.getZhxqRoomList(start,getRows(),zhxqRoomQuery);
		int total = zhxqRoomService.getZhxqRoomCount(zhxqRoomQuery);
		System.out.println(zhxqRooms.size());
		JSONArray rows = new JSONArray();
		for (ZhxqRoomModel zhxqRoom : zhxqRooms) {
			rows.add(zhxqRoom.buildJson());
		}
		jsonObj.put("rows", rows);
		jsonObj.put("total", total);
		return SUCCESS;
	}
	
	
	public String zhxqRoomEdit(){
		zhxqRoomModel=zhxqRoomService.getZhxqRoomModelById(zhxqRoomModel.getId());
		return SUCCESS;
	}
	
	public String zhxqRoomYzEdit(){
		zhxqRoomModel=zhxqRoomService.getZhxqRoomModelById(zhxqRoomModel.getId());
		return SUCCESS;
	}
	
	public String zhxqRoomSaveUpdate()throws Exception{
		EmptyFKHandler.handle(zhxqRoomModel);
		UserModel use = (UserModel)getSession().getAttribute("user");
		XiaoquModel xiaoqu  = use.getXiaoqu();
		if(null!=xiaoqu)
		{
			zhxqRoomModel.setXiaoqu(xiaoqu);
		}
		if("".equals(zhxqRoomModel.getId())){
			zhxqRoomService.saveZhxqRoom(zhxqRoomModel);
		}else{
			zhxqRoomService.updateZhxqRoom(zhxqRoomModel);
		}
		return SUCCESS;
	}
	public String zhxqRoomYzSaveUpdate(){
	try
	{
		EmptyFKHandler.handle(zhxqRoomModel);
		String roomId = zhxqRoomModel.getId();
		String huzhuname = zhxqRoomModel.getHuzhuname();
		Double mianji = zhxqRoomModel.getMianji();
		ZhxqRoomModel room = zhxqRoomService.getZhxqRoomModelById(roomId);
		UserModel use = (UserModel)getSession().getAttribute("user");
		String useId = use.getId();
		if(null!=room)
		{
			UserModel us = userService.getUserModelById(useId);
			String roomhuzhu = room.getHuzhuname();
			Double roomMianji = room.getMianji();
			if(huzhuname.equals(roomhuzhu)&&(roomMianji-mianji==0))
			{
				zhxqRoomService.saveUserRoomByRoomAndUser(room, us);
				
				if(huzhuname.equals(us.getUserName()))
				{
					room.setYezhu(us);
					zhxqRoomService.updateZhxqRoom(room);
				}
				
			}
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String zhxqRoomDelete(){
		String[] ids=getKeys().split(",");
		JSONArray errors=new JSONArray();
		for(String id : ids){
			try {
				zhxqRoomService.deleteZhxqRoom(id);
			} catch (Exception e) {
				e.printStackTrace();
				ZhxqRoomModel zhxqRoom=zhxqRoomService.getZhxqRoomModelById(id);
				JSONObject json=new JSONObject();
				json.put("name", zhxqRoom.getName());
				errors.add(json);
			}
		}
		jsonObj.put("errors",errors);
		return SUCCESS;
	}
	
	public String comboBoxZhxqRoomJsonArrayData(){
		UserModel use = (UserModel)getSession().getAttribute("user");
		List<ZhxqRoomModel> list=zhxqRoomService.getUserRoomByNoUser(use);
		for (ZhxqRoomModel dic : list) {
			JSONObject json=new JSONObject();
			json.put("id", dic.getId());
			json.put("text", dic.getMenpaihao());
			jsonArray.add(json);
		}
		return SUCCESS;
	}
	
	public String comboBoxZhxqRoomForMendongJsonArrayData(){
		for (int i=1;i<=50;i++) {
			JSONObject json=new JSONObject();
			json.put("id", i);
			json.put("text", i+"栋");
			jsonArray.add(json);
		}
		return SUCCESS;
	}
	
	public String comboBoxZhxqRoomForDanyuanJsonArrayData(){
		for (int i=1;i<=20;i++) {
			JSONObject json=new JSONObject();
			json.put("id", i);
			json.put("text", i+"单元");
			jsonArray.add(json);
		}
		return SUCCESS;
	}
	
	public String zhxqRoomUpload()
	{
		
		UserModel use = (UserModel)getSession().getAttribute("user");
		XiaoquModel xiaoqu  = use.getXiaoqu();
		if(inputExcel != null) {
			try {
				System.out.println("进入房屋导入环节");
				inputStream = new ByteArrayInputStream(zhxqRoomService.uploadFile(inputExcel,xiaoqu).getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				System.out.println(e.getMessage());
				//e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	public String roomExportTemplet(){
		try {
			getResponse().setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode("住房信息.xls","UTF-8"));
			InputStream in=getRequest().getServletContext().getResourceAsStream("templet/room");
			Workbook wb =new HSSFWorkbook(in);
			OutputStream out = getResponse().getOutputStream();
			wb.write(out);
			out.flush();
			out.close();
			out =null;
			getResponse().flushBuffer();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ZhxqRoomModel getZhxqRoomModel() {
		return zhxqRoomModel;
	}

	public void setZhxqRoomModel(ZhxqRoomModel ZhxqRoomModel) {
		this.zhxqRoomModel = ZhxqRoomModel;
	}

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public ZhxqRoomService getZhxqRoomService()
	{
		return zhxqRoomService;
	}

	public void setZhxqRoomService(ZhxqRoomService ZhxqRoomService)
	{
		this.zhxqRoomService = ZhxqRoomService;
	}

	public JSONArray getJsonArray()
	{
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray)
	{
		this.jsonArray = jsonArray;
	}

	public ZhxqRoomQuery getZhxqRoomQuery()
	{
		return zhxqRoomQuery;
	}

	public void setZhxqRoomQuery(ZhxqRoomQuery ZhxqRoomQuery)
	{
		this.zhxqRoomQuery = ZhxqRoomQuery;
	}

	public void setJsonObj(JSONObject jsonObj)
	{
		this.jsonObj = jsonObj;
	}

	public XiaoquService getXiaoquService()
	{
		return xiaoquService;
	}

	public void setXiaoquService(XiaoquService xiaoquService)
	{
		this.xiaoquService = xiaoquService;
	}

	public InputStream getInputStream()
	{
		return inputStream;
	}

	public void setInputStream(InputStream inputStream)
	{
		this.inputStream = inputStream;
	}

	public File getInputExcel()
	{
		return inputExcel;
	}

	public void setInputExcel(File inputExcel)
	{
		this.inputExcel = inputExcel;
	}
	public UserService getUserService()
	{
		return userService;
	}
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}
}
