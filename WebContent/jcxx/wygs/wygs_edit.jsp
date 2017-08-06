<%@page import="com.system.define.SysParaDefine"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="n" uri="/commonTag"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<n:nocache />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		
		<script type="text/javascript" src="<n:base/>/javascript/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="<n:base/>/javascript/jquery-easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<n:base/>/javascript/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<n:base/>/javascript/My97DatePicker/WdatePicker.js"></script>
		
		<link rel="stylesheet" href="<n:base/>/javascript/jquery-easyui/themes/default/easyui.css"/>
		<link rel="stylesheet" href="<n:base/>/javascript/jquery-easyui/themes/default/main.css"/>
		<link rel="stylesheet" href="<n:base/>/javascript/jquery-easyui/themes/icon.css"/>
	</head>
	<body>
		<div style="margin:0px;padding:0px;width:100%;height:100%;">
		<form id="editForm" action="<n:base/>/jcxx/zhxqWygsSaveUpdate.action" style="margin:0px;padding:0px;width:100%;height:100%;">
			<s:token/>
			<input type="hidden" name="zhxqWygsModel.id" value="<s:property value='zhxqWygsModel.id' />">
			<table class="bdtable" cellpadding="2">
				<tr>
					<td align="right">物业公司名称：</td>
					<td align="left"><input style="width:170px;height:24px;" type="text" name="zhxqWygsModel.name" value="<s:property value='zhxqWygsModel.name' />" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<td align="right">负责人：</td>
					<td align="left"><input style="width:170px;height:24px;" type="text" name="zhxqWygsModel.fuzeren" value="<s:property value='zhxqWygsModel.fuzeren' />" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<td align="right">负责人联系电话：</td>
					<td align="left"><input style="width:170px;height:24px;" type="text" name="zhxqWygsModel.fuzerentel" value="<s:property value='zhxqWygsModel.fuzerentel' />" class="easyui-validatebox" /></td>
				</tr>
				<tr>
					<td align="right">物业公司地址：</td>
					<td align="left" >
						<textarea rows="4" cols="30" style="width:350px;" name="zhxqWygsModel.gsdz"><s:property value="zhxqWygsModel.gsdz" /></textarea>
					</td>
				</tr>
				<tr>
					<td align="right">描述：</td>
					<td align="left">
						<textarea rows="4" cols="30" style="width:350px;" name="zhxqWygsModel.remark"><s:property value="zhxqWygsModel.remark" /></textarea>
					</td>
				</tr>
			</table>
		</form>
		</div>
	</body>
</html>
