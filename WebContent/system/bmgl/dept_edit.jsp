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
	</head>
	<body>
		<center>
		<form id="deptForm_edit" action="<n:base/>/yhqx/deptSaveUpdate.action" style="margin:0px;padding:0px;width:100%;">
			<input type="hidden" name="deptModel.id" value="<s:property value='deptModel.id' />">
			<s:if test="deptModel.parent!=null">
				<input type="hidden" name="deptModel.parent.id" value="<s:property value='deptModel.parent.id' />">
			</s:if>
			<input type="hidden" name="deptModel.insertTime" value="<s:property value='deptModel.insertTime' />">
			<table class="bdtable">
				<tr>
					<td align="right">部门名称：</td>
					<td align="left"><input style="width:157px;" type="text" name="deptModel.deptName" class="easyui-validatebox" data-options="required:true" value="<s:property value='deptModel.deptName' />"/></td>
					<td align="right">部门编号：</td>
					<td align="left"><input style="width:157px;" type="text" name="deptModel.deptNo" value="<s:property value='deptModel.deptNo' />"/></td>
				</tr>
				<tr>
					<td align="right">负责人：</td>
					<td align="left"><input style="width:157px;" type="text" name="deptModel.principal" value="<s:property value='deptModel.principal' />"/></td>
					<td align="right">联系电话：</td>
					<td align="left"><input style="width:157px;" type="text" name="deptModel.linkTel" value="<s:property value='deptModel.linkTel' />"/></td>
				</tr>
				<tr>
					<td align="right">排序号：</td>
					<td align="left" colspan="3"><input style="width:157px;" type="text" name="deptModel.orderCode" value="<s:property value='deptModel.orderCode' />"/></td>
				</tr>
				<tr>
					<td align="right">备注：</td>
					<td align="left" colspan="3">
						<textarea rows="4" cols="30" style="width:350px;" name="deptModel.remark"><s:property value="deptModel.remark" /></textarea>
					</td>
				</tr>
			</table>
		</form>
		</center>
	</body>
</html>
