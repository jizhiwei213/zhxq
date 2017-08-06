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
		<form id="editForm" action="<n:base/>/jcxx/zhxqWyfbzSaveUpdate.action" style="margin:0px;padding:0px;width:100%;height:100%;">
			<s:token/>
			<input type="hidden" name="zhxqWyfbzModel.id" value="<s:property value='zhxqWyfbzModel.id' />">
			<input type="hidden" name="zhxqWyfbzModel.xiaoqu.id" value="<s:property value='zhxqWyfbzModel.xiaoqu.id' />">
			<table class="bdtable" cellpadding="2">
				<tr>
					<td align="right">管理的小区：</td>
					<td align="left"><input style="width:170px;height:24px" type="text" name="zhxqWyfbzModel.xiaoqu.xiaoquName" value="<s:property value='zhxqWyfbzModel.xiaoqu.xiaoquName' />" readonly/></td>
				</tr>
				<tr>
					<td align="right">物业标准(元/平米)：</td>
					<td align="left"><input style="width:170px;height:24px;" type="text" name="zhxqWyfbzModel.biaozhun" value="<s:property value='zhxqWyfbzModel.biaozhun' />" /></td>
					<td align="right">其他费用：</td>
					<td align="left"><input style="width:170px;height:24px;" type="text" name="zhxqWyfbzModel.qitabz" value="<s:property value='zhxqWyfbzModel.qitabz' />" /></td>
				</tr>
				<tr>
					<td align="right">水费标准(元/立方)：</td>
					<td align="left"><input style="width:170px;height:24px;" type="text" name="zhxqWyfbzModel.sfbiaozhun" value="<s:property value='zhxqWyfbzModel.sfbiaozhun' />" /></td>
					<td align="right">电费标准(元/度)：</td>
					<td align="left"><input style="width:170px;height:24px;" type="text" name="zhxqWyfbzModel.dfbiaozhun" value="<s:property value='zhxqWyfbzModel.dfbiaozhun' />" /></td>
				</tr>
				<tr>
					<td align="right">燃气费标准(元/立方)：</td>
					<td align="left"><input style="width:170px;height:24px;" type="text" name="zhxqWyfbzModel.rqbiaozhun" value="<s:property value='zhxqWyfbzModel.rqbiaozhun' />" /></td>
					<td align="right">供暖费(元/立方)：</td>
					<td align="left"><input style="width:170px;height:24px;" type="text" name="zhxqWyfbzModel.nqf" value="<s:property value='zhxqWyfbzModel.nqf' />" /></td>
				</tr>
				<tr>
					<td align="right">描述：</td>
					<td align="left" colspan="3">
						<textarea rows="4" cols="30" style="width:350px;" name="zhxqWyfbzModel.remark"><s:property value="zhxqWyfbzModel.remark" /></textarea>
					</td>
				</tr>
			</table>
			<div>
				<a href="#" class="easyui-linkbutton" style="align:right;" iconCls="icon-add" plain="true"  onclick="checkSub();">确定</a>
			</div>
		</form>
		</div>
	</body>
	<script type="text/javascript">
	function checkSub()
	{
			var v=$("#editForm").form('validate');
			if(v){
				$.post($("#editForm").attr("action"),$("#editForm").serialize(),function(data){
					location.reload();
				});
			}
	}
	</script>
</html>
