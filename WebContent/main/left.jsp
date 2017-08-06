<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="n" uri="/commonTag"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<n:nocache />
<div class="easyui-accordion" data-options="fit:true,border:true">
	<s:iterator value="list">
		<div title="&nbsp;&nbsp;<s:property value="funName" />" data-options="iconCls:'<s:property value="funIco" />',href:'<n:base/>/main/leftChildren.action?parentId=<s:property value="id" />'" align="center"></div>
	</s:iterator>
</div>