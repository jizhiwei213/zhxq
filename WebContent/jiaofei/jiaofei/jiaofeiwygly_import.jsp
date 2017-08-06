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
<body style="text-align: center;">

	<form id="editForm" style="margin:0px;padding:0px;width:100%;height:100%;">
		<table style="width: 100%;height:100%;" cellpadding="0" cellspacing="0" >
			<tr>
				<td>
					<table style="width: 100%;height:100%;" cellpadding="2" cellspacing="0" class="bdtable">
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td style="text-align: right;">Excel文件：</td>
							<td><input type="file" id="inputExcel" name="inputExcel" class="easyui-validatebox" data-options="required:true"  /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td style="text-align: right;">模板下载：</td>
							<td>
								<a href="<n:base/>/jcxx/zhxqJiaofeiWyglyExportTemplet.action" style="text-decoration:none;">水电气用量信息.xls</a>
								
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<!-- 
								<a href="<n:base/>/templet/（模板）装备档案.xlsx" style="text-decoration:none;">（模板）装备档案.xlsx</a>  -->
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</html>