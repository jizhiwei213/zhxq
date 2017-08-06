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
		<form id="editForm" action="<n:base/>/jcxx/xiaoquSaveUpdate.action" style="margin:0px;padding:0px;width:100%;height:100%;">
			<s:token/>
			<input type="hidden" name="xiaoquModel.id" value="<s:property value='xiaoquModel.id' />">
			<table class="bdtable" cellpadding="2">
				<tr>
					<td align="right">小区名称：</td>
					<td align="left"><input style="width:170px;height:24px;" type="text" name="xiaoquModel.xiaoquName" value="<s:property value='xiaoquModel.xiaoquName' />" /></td>
					<td align="right">小区编号：</td>
					<td align="left"><input style="width:170px;height:24px;" type="text" name="xiaoquModel.xiaoquNo" value="<s:property value='xiaoquModel.xiaoquNo' />" /></td>
				</tr>
				<tr>
					<td align="right">小区联系人：</td>
					<td align="left"><input style="width:170px;height:24px;" type="text" name="xiaoquModel.xiaoquLianxiren" value="<s:property value='xiaoquModel.xiaoquLianxiren' />" /></td>
					<td align="right">小区联系人电话：</td>
					<td align="left"><input style="width:170px;height:24px;" type="text" name="xiaoquModel.xiaoquLianxidianhua" value="<s:property value='xiaoquModel.xiaoquLianxidianhua' />" /></td>
				</tr>
				<tr>
					<td align="right">公司负责人：</td>
					<td align="left"><input style="width:170px;height:24px;" type="text" name="xiaoquModel.fuzeren" value="<s:property value='xiaoquModel.fuzeren' />" class="easyui-validatebox" data-options="required:true" /></td>
					<td align="right">公司负责人联系电话：</td>
					<td align="left"><input style="width:170px;height:24px;" type="text" name="xiaoquModel.fuzerentel" value="<s:property value='xiaoquModel.fuzerentel' />" class="easyui-validatebox" /></td>
				</tr>
				<tr>
					<td align="right">所属物业公司：</td>
					<td align="left"><input style="width:170px;height:24px" type="text" name="xiaoquModel.wygs.id" class="easyui-combotree"  value="<s:property value='xiaoquModel.wygs.id' />"  data-options="panelHeight:'auto',url:'<n:base/>/jcxx/comboBoxWygsJsonArrayData.action',required:true" /></td>
					<td align="right">所在省份：</td>
					<td align="left"><input style="width:170px;height:24px" type="text" name="xiaoquModel.shen.id" class="easyui-combotree"  value="<s:property value='xiaoquModel.shen.id' />"  data-options="panelHeight:'auto',url:'<n:base/>/jcxx/comboBoxShenJsonArrayData.action',required:true" /></td>
				</tr>
				<tr>
					<td align="right">所在市：</td>
					<td align="left"><input style="width:170px;height:24px" type="text" name="xiaoquModel.shi.id" class="easyui-combotree"  value="<s:property value='xiaoquModel.shi.id' />"  data-options="panelHeight:'auto',url:'<n:base/>/jcxx/comboBoxShiJsonArrayData.action',required:true" /></td>
					<td align="right">所在区/县城：</td>
					<td align="left"><input style="width:170px;height:24px" type="text" name="xiaoquModel.qu.id" class="easyui-combotree"  value="<s:property value='xiaoquModel.qu.id' />"  data-options="panelHeight:'auto',url:'<n:base/>/jcxx/comboBoxQuJsonArrayData.action',required:true" /></td>
				</tr>
				<tr>
					<td align="right">小区地址：</td>
					<td align="left" colspan="3">
						<textarea rows="4" cols="30" style="width:350px;" name="xiaoquModel.xiaoquAddress"><s:property value="xiaoquModel.xiaoquAddress" /></textarea>
					</td>
				</tr>
				<tr>
					<td align="right">描述：</td>
					<td align="left" colspan="3">
						<textarea rows="4" cols="30" style="width:350px;" name="xiaoquModel.xiaoquRemark"><s:property value="xiaoquModel.xiaoquRemark" /></textarea>
					</td>
				</tr>
			</table>
		</form>
		</div>
	</body>
</html>
