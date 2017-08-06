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
		<form id="userForm_edit" action="<n:base/>/jcxxyhgl/qyglySaveUpdate.action" style="margin:0px;padding:0px;width:100%;"  method="post" enctype="multipart/form-data" >
			<input type="hidden" name="userModel.id" value="<s:property value='userModel.id' />">
			<input type="hidden" name="userModel.insertTime" value="<s:property value='userModel.insertTime' />">
			<input type="hidden" name="userModel.usertype" value="<s:property value='3' />">
			<table class="bdtable">
				<tr>
					<td align="right">姓名：</td>
					<td align="left"><input style="width:157px;" type="text" name="userModel.userName"  class="easyui-validatebox" data-options="required:true" value="<s:property value='userModel.userName' />"/></td>
					<td align="right">登录帐号：</td>
					<td align="left"><input style="width:157px;" type="text" name="userModel.userAccount" class="easyui-validatebox" data-options="required:true" value="<s:property value='userModel.userAccount' />"/></td>
				</tr>
				<tr>
					<td align="right">性别：</td>
					<td align="left">
						<input style="border:none;" type="radio" name="userModel.sex" value="0" <s:if test="userModel.sex==0">checked="checked"</s:if>/>男
						<input style="border:none;" type="radio" name="userModel.sex" value="1" <s:if test="userModel.sex==1">checked="checked"</s:if>/>女
					</td>
				</tr>
				<tr>
					<td align="right">联系电话：</td>
					<td align="left"><input style="width:157px;" type="text" name="userModel.linkTel" value="<s:property value='userModel.linkTel' />"/></td>
					<td align="right">邮箱地址：</td>
					<td align="left"><input style="width:157px;" type="text"  name="userModel.email" class="easyui-validatebox" data-options="validType:'email'" value="<s:property value='userModel.email' />"/></td>
				</tr>
				<tr>
					<td align="right">管辖区域：</td>
					<td align="left">
						<input type="text" name="userModel.quyu.id" value="<s:property value='userModel.quyu.id' />" class="easyui-combotree" data-options="panelHeight:'auto',url:'<n:base/>/jcxx/comboBoxQuJsonArrayData.action',required:true" />
					</td>
				</tr>
				<tr>
					<td align="right">所属部门：</td>
					<td align="left">
						<input type="text" name="userModel.dept.id" value="<s:property value='userModel.dept.id' />" class="easyui-combotree" data-options="panelHeight:'auto',url:'<n:base/>/yhqx/deptTree.action',required:true" />
					</td>
					<td align="right">是否停用：</td>
					<td align="left">
						<input style="border:none;" type="radio" name="userModel.isStop" value="0" <s:if test="userModel.isStop==0">checked="checked"</s:if>/>否
						<input style="border:none;" type="radio" name="userModel.isStop" value="1" <s:if test="userModel.isStop==1">checked="checked"</s:if>/>是
					</td>
				</tr>
				<tr>
					<td align="right">用户授权：</td>
					<td align="left" colspan="3">
						<s:iterator value="#request.roleList" status="st" var="roleList">
							<input style="border:none;" type="radio" name="roleModel.id" value="<s:property value='id' />" id="<s:property value='id' />"  <s:if test="#roleList.id eq #request.userRole.id">checked="checked"</s:if> /><label for="<s:property value='id' />"><s:property value='roleName' /></label>
							<s:if test="#st.count%4==0"><br/></s:if>
						</s:iterator>
					</td>
				</tr>
			</table>
		</form>
		</center>
	</body>
</html>
