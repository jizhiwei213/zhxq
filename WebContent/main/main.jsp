<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.system.define.SysParaDefine"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="n" uri="/commonTag"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<n:nocache />
<!DOCTYPE html>
<html>
	<head>
		<title>智慧小区运营平台</title>
		<script type="text/javascript" src="<n:base/>/javascript/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="<n:base/>/javascript/jquery-easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<n:base/>/javascript/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
		
		<link rel="stylesheet" href="<n:base/>/javascript/jquery-easyui/themes/icon.css"/>
		<link rel="stylesheet" href="<n:base/>/javascript/jquery-easyui/themes/default/easyui.css"/>
		<link rel="stylesheet" href="<n:base/>/javascript/jquery-easyui/themes/default/main.css"/>
		<style type="text/css">
		    a:active {
		    	outline: none;
		    	star:expression(this.onFocus=this.blur());
		    }
		    .msg{
		    	margin: 0px 10px;
		    }
		</style>
		<script type="text/javascript">
			function keydown(){
				if(event.keyCode==8){
					//if(document.activeElement.type=='text'){
						if(document.activeElement.readOnly==true){
							return false;
						}
					//}
				}
				return true;
			}
			
			function addTabs(href) {
				$('#ifr').attr('src',href);
			}
			function loadLeftMenu(obj, parentId) {
				$("#top_menu").find(".select").removeClass("select");
				$(obj).addClass("select");
				$('#mainlayout').layout('remove','west');
				$('#mainlayout').layout('add',{   
			   		 	region: 'west',
			   		 	width: 180, 
			    		border:true,
			    		iconCls:'icon-ok',
			    		title: '管理选项',   
			    		split: true,
			    		href:'<n:base/>/main/left.action?parentId='+parentId
				});
			}
			function loadDefalutLeftMenu(parentId) {
				$('#mainlayout').layout('remove','west');
				$('#mainlayout').layout('add',{   
			   		 	region: 'west',
			   		 	width: 180, 
			    		border:true,
			    		iconCls:'icon-ok',
			    		title: '管理选项',   
			    		split: true,
			    		href:'<n:base/>/main/left.action?parentId='+parentId
				});
			}
		</script>
	</head>
	<body class="easyui-layout" id="mainlayout" onkeydown="keydown">
		<div data-options="region:'north',border:false" style="height: 60px;">
			<table width="100%" data-options="fit:true" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="248" height="60" class="bgtop">&nbsp;</td>
					<td class="topbg" valign="bottom">
						<div class="nav">
							<ul id="top_menu">
								<s:iterator value="list" status="st">
									<s:if test="#st.first">
										<li class="select" onclick="loadLeftMenu(this,'<s:property value="id" />')"><s:property value="funName" /></li>
										<script type="text/javascript">
											$(document).ready(function(){loadDefalutLeftMenu('<s:property value="id" />');})
										</script>
									</s:if>
									<s:else>
										<li onclick="loadLeftMenu(this,'<s:property value="id" />')"><s:property value="funName" /></li>
									</s:else>
								</s:iterator>
							</ul>
						</div>
					</td>
					<td width="164" align="center" class="topbt">
						<table width="154" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<a href="javascript:void(0);"><img src="<n:base/>/javascript/jquery-easyui/themes/default/images/png_bt.gif" width="38" height="26" /></a>
								</td>
								<td>
									<a href="#" onclick="changePwd();"><img src="<n:base/>/javascript/jquery-easyui/themes/default/images/png_bt.gif" width="38" height="26" title="修改密码" /></a>
								</td>
								<td>
									<a href="javascript:void(0);"><img src="<n:base/>/javascript/jquery-easyui/themes/default/images/png_bt.gif" width="38" height="26" /></a>
								</td>
								<td>
									<a href="<n:base/>/out/logOut.action"><img src="<n:base/>/javascript/jquery-easyui/themes/default/images/png_bt.gif" width="38" height="26" title="退出系统" /></a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
		<div data-options="region:'west',split:true,border:true,iconCls:'icon-ok'" title="管理选项" class="left" id="leftPanel"></div>
		<div data-options="region:'south',border:true" style="height:30px;">
			<table width="100%" style="margin:0px;padding:0px;" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="28" align="center" class="bottombg">
							<span> </span>
					</td>
				</tr>
			</table>
		</div>
		<div data-options="region:'center',border:true,iconCls:'icon-tip'" title="欢迎您：<s:property value="#session.user.userName"/>&nbsp;&nbsp;" class="center">
			<iframe id="ifr" scrolling="auto" src="" width="100%" frameborder="0" height="100%"></iframe>
		</div>
		<div id="dialog_changePwd" class="easyui-dialog" style="width:300px;height:200px;"
			data-options="
				closed:true,
				title:'修改密码',
				iconCls:'icon-edit',
				resizable:true,
				modal:true,
				buttons:[{
					text:'确定',
					iconCls:'icon-ok',
					handler:function(){
						submitForm();
					}
				},{
					text:'取消',
					iconCls:'icon-cancel',
					handler:function(){
						$('#dialog_changePwd').dialog('close');
					}
				}]">
			<form id="form_changePwd" action="<n:base/>/main/changePwd.action" method="post" style="margin:0px;padding:0px;width:100%;height:100%;">
				<table class="bdtable" style="width:100%;height:100%;">
					<tr>
						<td align="right">旧 密 码：</td>
						<td align="left"><input type="password" id="oldPwd" name="oldPwd" class="easyui-validatebox" data-options="required:true" /></td>
					</tr>
					<tr>
						<td align="right">新 密 码：</td>
						<td align="left"><input type="password" id="newPwd" name="newPwd" class="easyui-validatebox" data-options="required:true" /></td>
					</tr>
					<tr>
						<td align="right">重复密码：</td>
						<td align="left"><input type="password" id="repeatPwd" name="repeatPwd" class="easyui-validatebox" data-options="required:true" /></td>
					</tr>
				</table>
			</form>
		</div>
	</body>
	<script type="text/javascript">
			function changePwd(){
				$('#dialog_changePwd').dialog('open');
			}
			function submitForm(){
				var f=$('#form_changePwd').form('validate');
				if(f){
					if($('#newPwd').val()!=$('#repeatPwd').val()){
						$.messager.show({
	            			title:'消息提示',
	            			msg:'两次密码不一致！',
	            			showType:'show'
	       				});
						$('#newPwd').val('');
						$('#repeatPwd').val('');
						$('#newPwd').focus();
					}else{
						$.getJSON($('#form_changePwd').attr('action'),$('#form_changePwd').serialize(),function(data){
							var code=data.code;
							if(code=='success'){
								$('#dialog_changePwd').dialog('close');
								$.messager.show({
			            			title:'消息提示',
			            			msg:'修改成功！',
			            			showType:'show'
			       				});
							}else if(code=='oldPwdError'){
								$('#oldPwd').val('');
								$('#oldPwd').focus();
								$.messager.show({
			            			title:'消息提示',
			            			msg:'旧密码有误！',
			            			showType:'show'
			       				});
							}
						});
					}
				}
			}
		</script>
</html>
