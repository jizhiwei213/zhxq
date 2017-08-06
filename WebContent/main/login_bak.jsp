<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="n" uri="/commonTag"%>
<n:nocache/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>教务服务平台</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		 
		<script type="text/javascript" src="<n:base/>/javascript/jquery-1.11.0.min.js"></script>
		
		<link rel="stylesheet" href="<n:base/>/javascript/jquery-easyui/themes/default/easyui.css"/>
		<link rel="stylesheet" href="<n:base/>/javascript/jquery-easyui/themes/default/login.css"/>
		
		<script type="text/javascript">
			function changeNextImage(obj) {
				obj.src = "<n:base/>/out/verificationCode.action?random=" + Math.random();
			}
			function subMitAjaxForm(){
				if(checkInput()) {
					document.getElementById("userFrm").submit();
				}
			}
			function checkInput(){
				var account= $("#userAccount").val(); 
				var pwd = $("#userPassword").val(); 
				var yzm= $("#verificationCode").val();
				if(account==""){
					alert('请输入用户名!');
					return false;
				}
				if(pwd==""){
					alert('请输入密码!');
					return false;
				}
				if(yzm==""){
					alert('请输入验证码!');
					return false;
				}
				return true;
			}
			$(document).keydown(function(event){ 
				if(event.keyCode==13){
					 subMitAjaxForm();
				} 
			});
			$(function(){
				var errorType='<s:property value="errorType" />';
				if(errorType=='accountError'){
					alert('用户名密码有误或账号被禁用！');
				}else if(errorType=='vcodeError'){
					alert('验证码填写有误！');
				}
				else if(errorType=='vcodeDelayError'){
					alert('软件试用期已过！！');
				}
			});
		</script>
	</head>
	<body>
		<form id="userFrm" action="<n:base/>/out/login.action" method="post" style="margin:0px;padding:0px;">
			<table width="100%" height="100%">
				<tr><td valign="middle" align="center">
			<table id="__01" width="1025" height="626" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td rowspan="7">
			<img src="<n:base/>/images/psd6694_01.gif" width="241" height="626" alt=""></td>
		<td rowspan="7">
			<img src="<n:base/>/images/psd6694_02.gif" width="1" height="626" alt=""></td>
		<td colspan="8">
			<img src="<n:base/>/images/psd6694_03.gif" width="507" height="165" alt=""></td>
		<td rowspan="7">
			<img src="<n:base/>/images/psd6694_04.gif" width="275" height="626" alt=""></td>
		<td>
			<img src="<n:base/>/images/split.gif" width="1" height="165" alt=""></td>
	</tr>
	<tr>
		<td colspan="7">
			<img src="<n:base/>/images/psd6694_05.gif" width="506" height="154" alt=""></td>
		<td rowspan="5">
			<img src="<n:base/>/images/psd6694_06.gif" width="1" height="263" alt=""></td>
		<td>
			<img src="<n:base/>/images/split.gif" width="1" height="154" alt=""></td>
	</tr>
	<tr>
		<td rowspan="4">
			<img src="<n:base/>/images/psd6694_07.gif" width="49" height="109" alt=""></td>
		<td>
			<img src="<n:base/>/images/psd6694_08.gif" width="77" height="31" alt=""></td>
		<td colspan="2" background="<n:base/>/images/psd6694_09.gif"  width="219" height="31" >
				&nbsp;<input name="userAccount" type="text" id="userAccount"  style="width:200" tabindex="1" placeholder="用户名" />
		</td>
		<td rowspan="4">
			<img src="<n:base/>/images/psd6694_10.gif" width="19" height="109" alt=""></td>
		<td rowspan="3">
			<img src="<n:base/>/images/psd6694_11.gif" width="79" height="81" alt="" border="0" onclick="subMitAjaxForm()"></td>
		<td rowspan="4">
			<img src="<n:base/>/images/psd6694_12.gif" width="63" height="109" alt=""></td>
		<td>
			<img src="<n:base/>/images/split.gif" width="1" height="31" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="<n:base/>/images/psd6694_13.gif" width="77" height="35" alt=""></td>
		<td colspan="2" background="<n:base/>/images/psd6694_14.gif" width="219" height="35" >
		&nbsp;<input type="password" name="userPassword" id="userPassword"  style="width:200" tabindex="2" placeholder="密码" /> 
		</td>
		<td>
			<img src="<n:base/>/images/split.gif" width="1" height="35" alt=""></td>
	</tr>
	<tr>
		<td rowspan="2">
			<img src="<n:base/>/images/psd6694_15.gif" width="77" height="43" alt=""></td>
		<td rowspan="2" background="<n:base/>/images/psd6694_16.gif" width="144" height="43" >
		
		</td>
		<td rowspan="2" background="<n:base/>/images/psd6694_17.gif" width="75" height="43">
		</td>
		<td>
			<img src="<n:base/>/images/split.gif" width="1" height="15" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="<n:base/>/images/psd6694_18.gif" width="79" height="28" alt=""></td>
		<td>
			<img src="<n:base/>/images/split.gif" width="1" height="28" alt=""></td>
	</tr>
	<tr>
		<td colspan="8">
			<img src="<n:base/>/images/psd6694_19.gif" width="507" height="198" alt=""></td>
		<td>
			<img src="<n:base/>/images/split.gif" width="1" height="198" alt=""></td>
	</tr>
</table>
				</td></tr>
			</table>
		</form>
		<script type="text/javascript">
			//显示验证码图片
			$("input[name=verificationCode]").focus(function () { 
	            $("#m_tagsItem").show();
	        });
		</script>
		<script type="text/javascript">
			function isPlaceholder(){
				var input=document.createElement('input');
				return 'placeholder' in input;
			}
			if(!isPlaceholder()){
				$('input').each(function(){
					this.value=this.placeholder;
				});
				$('input').focus(function(){
					if(this.value==this.placeholder){
						this.value='';
					}
				});
				$('input').blur(function(){
					if(this.value==''){
						this.value=this.placeholder;
					}
				});
			}
		</script>
	</body>
</html>
