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
		<form id="editForm" action="<n:base/>/jcxx/zhxqQuSaveUpdate.action" style="margin:0px;padding:0px;width:100%;height:100%;">
			<s:token/>
			<input type="hidden" name="zhxqQuModel.id" value="<s:property value='zhxqQuModel.id' />">
			<table class="bdtable" cellpadding="2">
				<tr>
					<td align="right">名称：</td>
					<td align="left"><input style="width:170px;height:24px;" type="text" name="zhxqQuModel.name" value="<s:property value='zhxqQuModel.name' />" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<td align="right">所在省份：</td>
					<td align="left"><input style="width:170px;height:24px" type="text" name="zhxqQuModel.shen.id" class="easyui-combotree"  value="<s:property value='zhxqQuModel.shen.id' />"  data-options="panelHeight:'auto',url:'<n:base/>/jcxx/comboBoxShenJsonArrayData.action',required:true" /></td>
				</tr>
				<tr>
					<td align="right">所在市：</td>
					<td align="left"><input style="width:170px;height:24px" type="text" name="zhxqQuModel.shi.id" class="easyui-combotree"  value="<s:property value='zhxqQuModel.shi.id' />"  data-options="panelHeight:'auto',url:'<n:base/>/jcxx/comboBoxShiJsonArrayData.action',required:true" /></td>
				</tr>
			</table>
		</form>
		</div>
	</body>
</html>
