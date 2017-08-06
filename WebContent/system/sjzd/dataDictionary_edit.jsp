<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="n" uri="/commonTag"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<n:nocache />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
</head>
<body>
	<center>
		<form style="margin:0px;padding:5px 1px 1px 1px;" id="sjzd_Form" action="<n:base/>/sjzd/dataDictionarySaveUpdate.action">
			<input type="hidden" name="dataDictionary.id" value="<s:property value='dataDictionary.id'/>" />
			<input type="hidden" name="dataDictionary.parent.id" value="<s:property value='dataDictionary.parent.id'/>" />
			<input type="hidden" name="dataDictionary.dataType" value="<s:property value='dataDictionary.parent.dataType'/>" />
			<table border="0" cellpadding="2" cellspacing="0" class="bdtable">
				<tr>
					<td width="80" height="22" align="right">名称：</td>
					<td width="170" align="left"><input type="text" name="dataDictionary.name" value="<s:property value='dataDictionary.name'/>" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]',missingMessage:'请输入名称!',invalidMessage:'名称不得超过100个字符!'" /></td>
				</tr>
				<tr>
					<td width="80" height="22" align="right">数据值：</td>
					<td width="170" align="left"><input type="text" name="dataDictionary.dataValue" value="<s:property value='dataDictionary.dataValue'/>" class="easyui-validatebox" data-options="required:true,validType:'length[0,32]',missingMessage:'请输入数据值',invalidMessage:'数据值不得超过32个字符!'" /></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>