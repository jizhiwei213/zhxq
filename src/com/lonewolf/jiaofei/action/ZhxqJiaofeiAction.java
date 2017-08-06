package com.lonewolf.jiaofei.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.base.action.BaseAction;
import com.lonewolf.jcxx.model.XiaoquModel;
import com.lonewolf.jcxx.model.ZhxqQuModel;
import com.lonewolf.jiaofei.service.ZhxqJiaofeiService;
import com.lonewolf.jiaofei.model.ZhxqJiaofeiModel;
import com.lonewolf.jiaofei.query.ZhxqJiaofeiQuery;
import com.system.model.UserModel;
public class ZhxqJiaofeiAction extends BaseAction {
	private static final long serialVersionUID = -4966432939099172304L;
	private ZhxqJiaofeiService zhxqJiaofeiService;
	private ZhxqJiaofeiModel zhxqJiaofeiModel;
	private ZhxqJiaofeiQuery zhxqJiaofeiQuery;
	private JSONObject jsonObj=new JSONObject();
	private JSONArray jsonArray=new JSONArray();
	private InputStream inputStream;
	
	private File inputExcel;
	private String jfxx;
	public String zhxqJiaofeiWyglyIndex(){
		return SUCCESS;
	}
	
	public String zhxqJiaofeiWyglyList(){
		Integer start=null;
		if(getRows()!=null&&getPage()!=null){
			start=(getPage() - 1) * getRows();
		}
		if(null==zhxqJiaofeiQuery)
		{
			zhxqJiaofeiQuery = new ZhxqJiaofeiQuery();
		}
		UserModel use = (UserModel)getSession().getAttribute("user");
		XiaoquModel xiaoqu  = use.getXiaoqu();
		if(null!=xiaoqu)
		{
			zhxqJiaofeiQuery.setXiaoquId(xiaoqu.getId());
		}
		List<ZhxqJiaofeiModel> zhxqJiaofeis = zhxqJiaofeiService.getZhxqJiaofeiList(start,getRows(),zhxqJiaofeiQuery);
		int total = zhxqJiaofeiService.getZhxqJiaofeiCount(zhxqJiaofeiQuery);
		JSONArray rows = new JSONArray();
		for (ZhxqJiaofeiModel zhxqJiaofei : zhxqJiaofeis) {
			rows.add(zhxqJiaofei.buildJson());
		}
		jsonObj.put("rows", rows);
		jsonObj.put("total", total);
		return SUCCESS;
	}
	
	public String zhxqJiaofeiWyglyIndex2(){
		return SUCCESS;
	}
	
	public String zhxqJiaofeiWyglyList2(){
		Integer start=null;
		if(getRows()!=null&&getPage()!=null){
			start=(getPage() - 1) * getRows();
		}
		if(null==zhxqJiaofeiQuery)
		{
			zhxqJiaofeiQuery = new ZhxqJiaofeiQuery();
		}
		UserModel use = (UserModel)getSession().getAttribute("user");
		XiaoquModel xiaoqu  = use.getXiaoqu();
		if(null!=xiaoqu)
		{
			zhxqJiaofeiQuery.setXiaoquId(xiaoqu.getId());
		}
		zhxqJiaofeiQuery.setJfzt("('0','1')");
		List<ZhxqJiaofeiModel> zhxqJiaofeis = zhxqJiaofeiService.getZhxqJiaofeiList(start,getRows(),zhxqJiaofeiQuery);
		int total = zhxqJiaofeiService.getZhxqJiaofeiCount(zhxqJiaofeiQuery);
		JSONArray rows = new JSONArray();
		for (ZhxqJiaofeiModel zhxqJiaofei : zhxqJiaofeis) {
			rows.add(zhxqJiaofei.buildJson());
		}
		jsonObj.put("rows", rows);
		jsonObj.put("total", total);
		return SUCCESS;
	}
	
	
	public String zhxqJiaofeiYwyIndex(){
		return SUCCESS;
	}
	
	public String zhxqJiaofeiYwyList(){
		Integer start=null;
		if(getRows()!=null&&getPage()!=null){
			start=(getPage() - 1) * getRows();
		}
		if(null==zhxqJiaofeiQuery)
		{
			zhxqJiaofeiQuery = new ZhxqJiaofeiQuery();
		}
		UserModel use = (UserModel)getSession().getAttribute("user");
		ZhxqQuModel xiaoqu  = use.getQuyu();
		if(null!=xiaoqu)
		{
			zhxqJiaofeiQuery.setQuyuId(xiaoqu.getId());
		}
		List<ZhxqJiaofeiModel> zhxqJiaofeis = zhxqJiaofeiService.getZhxqJiaofeiList(start,getRows(),zhxqJiaofeiQuery);
		int total = zhxqJiaofeiService.getZhxqJiaofeiCount(zhxqJiaofeiQuery);
		JSONArray rows = new JSONArray();
		for (ZhxqJiaofeiModel zhxqJiaofei : zhxqJiaofeis) {
			rows.add(zhxqJiaofei.buildJson());
		}
		jsonObj.put("rows", rows);
		jsonObj.put("total", total);
		return SUCCESS;
	}
	public String ywyJiaofei(){
		String[] ids=getKeys().split(",");
		JSONArray errors=new JSONArray();
		UserModel use = (UserModel)getSession().getAttribute("user");
		for(String id : ids){
			try {
				ZhxqJiaofeiModel jiaofei=zhxqJiaofeiService.getZhxqJiaofeiModelById(id);
				String jiaofeiStatus = jiaofei.getJiaofeistatus();
				if(!"0".equals(jiaofeiStatus))
				{
					jiaofei.setJiaofeistatus("0");
					jiaofei.setYwy(use);
					jiaofei.setJiaofeidate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					zhxqJiaofeiService.updateZhxqJiaofei(jiaofei);
				}
			} catch (Exception e) {
				e.printStackTrace();
				ZhxqJiaofeiModel jiaofei=zhxqJiaofeiService.getZhxqJiaofeiModelById(id);
				JSONObject json=new JSONObject();
				json.put("name", jiaofei.getRoom().getName());
				errors.add(json);
			}
		}
		jsonObj.put("errors",errors);
		return SUCCESS;
	}
	
	public String zhxqJiaofeiYzIndex(){
		return SUCCESS;
	}
	
	public String zhxqJiaofeiYzList(){
		Integer start=null;
		if(getRows()!=null&&getPage()!=null){
			start=(getPage() - 1) * getRows();
		}
		if(null==zhxqJiaofeiQuery)
		{
			zhxqJiaofeiQuery = new ZhxqJiaofeiQuery();
		}
		UserModel use = (UserModel)getSession().getAttribute("user");
		if(null!=use)
		{
			zhxqJiaofeiQuery.setYezhuId(use.getId());
		}
		List<ZhxqJiaofeiModel> zhxqJiaofeis = zhxqJiaofeiService.getZhxqJiaofeiList(start,getRows(),zhxqJiaofeiQuery);
		int total = zhxqJiaofeiService.getZhxqJiaofeiCount(zhxqJiaofeiQuery);
		JSONArray rows = new JSONArray();
		for (ZhxqJiaofeiModel zhxqJiaofei : zhxqJiaofeis) {
			rows.add(zhxqJiaofei.buildJson());
		}
		jsonObj.put("rows", rows);
		jsonObj.put("total", total);
		return SUCCESS;
	}
	
	
	
	public String zhxqJiaofeiZjlIndex(){
		return SUCCESS;
	}
	
	public String zhxqJiaofeiZjlList(){
		Integer start=null;
		if(getRows()!=null&&getPage()!=null){
			start=(getPage() - 1) * getRows();
		}
		List<ZhxqJiaofeiModel> zhxqJiaofeis = zhxqJiaofeiService.getZhxqJiaofeiList(start,getRows(),zhxqJiaofeiQuery);
		int total = zhxqJiaofeiService.getZhxqJiaofeiCount(zhxqJiaofeiQuery);
		JSONArray rows = new JSONArray();
		for (ZhxqJiaofeiModel zhxqJiaofei : zhxqJiaofeis) {
			rows.add(zhxqJiaofei.buildJson());
		}
		jsonObj.put("rows", rows);
		jsonObj.put("total", total);
		return SUCCESS;
	}
	
	
	public String zhxqJiaofeiQyjlIndex(){
		return SUCCESS;
	}
	
	public String zhxqJiaofeiQyjlList(){
		Integer start=null;
		if(getRows()!=null&&getPage()!=null){
			start=(getPage() - 1) * getRows();
		}
		if(null==zhxqJiaofeiQuery)
		{
			zhxqJiaofeiQuery = new ZhxqJiaofeiQuery();
		}
		UserModel use = (UserModel)getSession().getAttribute("user");
		ZhxqQuModel xiaoqu  = use.getQuyu();
		if(null!=xiaoqu)
		{
			zhxqJiaofeiQuery.setQuyuId(xiaoqu.getId());
		}
		zhxqJiaofeiQuery.setIsJfcx("1");
		List<ZhxqJiaofeiModel> zhxqJiaofeis = zhxqJiaofeiService.getZhxqJiaofeiList(start,getRows(),zhxqJiaofeiQuery);
		int total = zhxqJiaofeiService.getZhxqJiaofeiCount(zhxqJiaofeiQuery);
		JSONArray rows = new JSONArray();
		for (ZhxqJiaofeiModel zhxqJiaofei : zhxqJiaofeis) {
			rows.add(zhxqJiaofei.buildJson());
		}
		jsonObj.put("rows", rows);
		jsonObj.put("total", total);
		return SUCCESS;
	}
	public String chexiaoJiaofei(){
		String[] ids=getKeys().split(",");
		JSONArray errors=new JSONArray();
		UserModel use = (UserModel)getSession().getAttribute("user");
		for(String id : ids){
			try {
				ZhxqJiaofeiModel jiaofei=zhxqJiaofeiService.getZhxqJiaofeiModelById(id);
					jiaofei.setJiaofeistatus("1");
					jiaofei.setYwy(null);
					zhxqJiaofeiService.updateZhxqJiaofei(jiaofei);
			} catch (Exception e) {
				e.printStackTrace();
				ZhxqJiaofeiModel jiaofei=zhxqJiaofeiService.getZhxqJiaofeiModelById(id);
				JSONObject json=new JSONObject();
				json.put("name", jiaofei.getRoom().getName());
				errors.add(json);
			}
		}
		jsonObj.put("errors",errors);
		return SUCCESS;
	}
	
	
	public String zhxqJiaofeiWyglyConf(){
		String[] ids=getKeys().split(",");
		JSONArray errors=new JSONArray();
		for(String id : ids){
			try {
				ZhxqJiaofeiModel jiaofei=zhxqJiaofeiService.getZhxqJiaofeiModelById(id);
					jiaofei.setJiaofeistatus("1");
					jiaofei.setDianliangstatus("1");
					jiaofei.setShuifeistatus("1");
					jiaofei.setRuanqifeistatus("1");
					zhxqJiaofeiService.updateZhxqJiaofei(jiaofei);
			} catch (Exception e) {
				e.printStackTrace();
				ZhxqJiaofeiModel jiaofei=zhxqJiaofeiService.getZhxqJiaofeiModelById(id);
				JSONObject json=new JSONObject();
				json.put("name", jiaofei.getRoom().getName());
				errors.add(json);
			}
		}
		jsonObj.put("errors",errors);
		return SUCCESS;
	}
	
	public String zhxqJiaofeiCwIndex(){
		return SUCCESS;
	}
	
	public String zhxqJiaofeiCwList(){
		Integer start=null;
		if(getRows()!=null&&getPage()!=null){
			start=(getPage() - 1) * getRows();
		}
		List<ZhxqJiaofeiModel> zhxqJiaofeis = zhxqJiaofeiService.getZhxqJiaofeiList(start,getRows(),zhxqJiaofeiQuery);
		int total = zhxqJiaofeiService.getZhxqJiaofeiCount(zhxqJiaofeiQuery);
		JSONArray rows = new JSONArray();
		for (ZhxqJiaofeiModel zhxqJiaofei : zhxqJiaofeis) {
			rows.add(zhxqJiaofei.buildJson());
		}
		jsonObj.put("rows", rows);
		jsonObj.put("total", total);
		return SUCCESS;
	}
	
	public String zhxqJiaofeiWyglyImport(){
		return SUCCESS;
	}

	public String zhxqJiaofeiWyglyUpload()
	{
		UserModel use = (UserModel)getSession().getAttribute("user");
		XiaoquModel xiaoqu  = use.getXiaoqu();
		if(inputExcel != null) {
			try {
				System.out.println("进入水电费导入环节");
				inputStream = new ByteArrayInputStream(zhxqJiaofeiService.uploadFile(inputExcel,xiaoqu).getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				System.out.println(e.getMessage());
				//e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	public String zhxqJiaofeiWyglyExportTemplet(){
		try {
			getResponse().setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode("水电气用量.xls","UTF-8"));
			InputStream in=getRequest().getServletContext().getResourceAsStream("templet/shuidian");
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
	public ZhxqJiaofeiModel getZhxqJiaofeiModel() {
		return zhxqJiaofeiModel;
	}

	public void setZhxqJiaofeiModel(ZhxqJiaofeiModel ZhxqJiaofeiModel) {
		this.zhxqJiaofeiModel = ZhxqJiaofeiModel;
	}

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public ZhxqJiaofeiService getZhxqJiaofeiService()
	{
		return zhxqJiaofeiService;
	}

	public void setZhxqJiaofeiService(ZhxqJiaofeiService ZhxqJiaofeiService)
	{
		this.zhxqJiaofeiService = ZhxqJiaofeiService;
	}

	public JSONArray getJsonArray()
	{
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray)
	{
		this.jsonArray = jsonArray;
	}

	public ZhxqJiaofeiQuery getZhxqJiaofeiQuery()
	{
		return zhxqJiaofeiQuery;
	}

	public void setZhxqJiaofeiQuery(ZhxqJiaofeiQuery ZhxqJiaofeiQuery)
	{
		this.zhxqJiaofeiQuery = ZhxqJiaofeiQuery;
	}

	public void setJsonObj(JSONObject jsonObj)
	{
		this.jsonObj = jsonObj;
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
	
}
