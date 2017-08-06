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
		<center>
		<form id="userForm_edit" action="<n:base/>/jcxxyhgl/userInfoUpdate22.action" style="margin:0px;padding:0px;width:100%;"  method="post" enctype="multipart/form-data" >
			<input type="hidden" name="userModel.id" value="<s:property value='userModel.id' />">
			<table class="bdtable">
				<tr>
					<td align="right">姓名：</td>
					<td align="left"><input style="width:157px;" type="text" name="userModel.userName"  class="easyui-validatebox" data-options="required:true" value="<s:property value='userModel.userName' />" readonly/></td>
					<td align="right">级别：</td>
					<td align="left">
					<input style="border:none;" type="radio" name="userModel.userxingji" value="1" <s:if test="userModel.userxingji==1">checked="checked"</s:if>/>普通级
						<input style="border:none;" type="radio" name="userModel.userxingji" value="2" <s:if test="userModel.userxingji==2">checked="checked"</s:if>/>县级
					<input style="border:none;" type="radio" name="userModel.userxingji" value="3" <s:if test="userModel.userxingji==3">checked="checked"</s:if>/>市级
					<input style="border:none;" type="radio" name="userModel.userxingji" value="4" <s:if test="userModel.userxingji==4">checked="checked"</s:if>/>省级
					<input style="border:none;" type="radio" name="userModel.userxingji" value="5" <s:if test="userModel.userxingji==5">checked="checked"</s:if>/>董级
					
					</td>
				</tr>
			</table>
		</form>
		</center>
	</body>
</html>
