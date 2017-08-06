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
		<form id="editForm" action="<n:base/>/jcxx/zhxqRoomSaveUpdate.action" style="margin:0px;padding:0px;width:100%;height:100%;">
			<s:token/>
			<input type="hidden" name="zhxqRoomModel.id" value="<s:property value='zhxqRoomModel.id' />">
			<table class="bdtable" cellpadding="2">
				<tr>
					<td align="right">门栋：</td>
					<td align="left"><input style="width:170px;height:24px" type="text" name="zhxqRoomModel.mendong" class="easyui-combotree"  value="<s:property value='zhxqRoomModel.mendong' />"  data-options="panelHeight:'auto',url:'<n:base/>/jcxx/comboBoxZhxqRoomForMendongJsonArrayData.action',required:true" /></td>
				</tr>
				<tr>
					<td align="right">单元：</td>
					<td align="left"><input style="width:170px;height:24px" type="text" name="zhxqRoomModel.danyuan" class="easyui-combotree"  value="<s:property value='zhxqRoomModel.danyuan' />"  data-options="panelHeight:'auto',url:'<n:base/>/jcxx/comboBoxZhxqRoomForDanyuanJsonArrayData.action',required:true" /></td>
				</tr>
				<tr>
					<td align="right">门牌号：</td>
					<td align="left"><input style="width:170px;height:24px;" type="text" name="zhxqRoomModel.menpaihao" value="<s:property value='zhxqRoomModel.menpaihao' />" /></td>
				</tr>
				<tr>
					<td align="right">户主：</td>
					<td align="left"><input style="width:170px;height:24px;" type="text" name="zhxqRoomModel.huzhuname" value="<s:property value='zhxqRoomModel.huzhuname' />" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<td align="right">面积(m3)：</td>
					<td align="left"><input style="width:170px;height:24px;" type="text" name="zhxqRoomModel.mianji" value="<s:property value='zhxqRoomModel.mianji' />" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<td align="right">描述：</td>
					<td align="left">
						<textarea rows="4" cols="30" style="width:350px;" name="zhxqRoomModel.name"><s:property value="zhxqRoomModel.name" /></textarea>
					</td>
				</tr>
			</table>
		</form>
		</div>
	</body>
</html>
