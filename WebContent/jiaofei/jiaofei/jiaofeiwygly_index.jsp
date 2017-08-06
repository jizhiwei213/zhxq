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
		<script type="text/javascript" src="<n:base/>/javascript/ajaxfileupload.js"></script>
		<link rel="stylesheet" href="<n:base/>/javascript/jquery-easyui/themes/default/easyui.css"/>
		<link rel="stylesheet" href="<n:base/>/javascript/jquery-easyui/themes/default/main.css"/>
		<link rel="stylesheet" href="<n:base/>/javascript/jquery-easyui/themes/icon.css"/>
	</head>
	<body>
		<table id="datagrid_<s:property value='rand'/>" toolbar="#tb_datagrid_<s:property value='rand'/>" fitColumns="true">
			<thead>
				<tr>
					<th checkbox="true"></th>
					<th field="mendong" width="40">门栋</th>
					<th field="danyuan" width="40">单元</th>
					<th field="menpaihao" width="100">门牌号</th>
					<th field="zhxqyear" width="50">年</th>
					<th field="zhxqmonth" width="30">月</th>
					<th field="wyf" width="80">物业费</th>
					<th field="cwf" width="80">车位费</th>
					<th field="otherfy" width="80">其他费用</th>
					<th field="jiaofeistatus" width="80" formatter="statusFormatter" align="center">缴费状态</th>
					<th field="dianfei" width="80">电费</th>
					<th field="dianliangstatus" width="80" formatter="statusFormatter" align="center">缴费状态</th>
					<th field="shuifei" width="80">水费</th>
					<th field="shuifeistatus" width="80" formatter="statusFormatter" align="center">缴费状态</th>
					<th field="ruanqifei" width="80">燃气费</th>
					<th field="ruanqifeistatus" width="80" formatter="statusFormatter" align="center">缴费状态</th>
				</tr>
			</thead>
		</table>
	
		<div id="tb_datagrid_<s:property value='rand'/>">
			<div>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;年：</span>
				<input style="width:150px;height:24px;" type="text" id="yearQ" name="yearQ" class="easyui-validatebox" />
				<span>&nbsp;&nbsp;&nbsp;&nbsp;月：</span>
				<input style="width:150px;height:24px;" type="text" id="monthQ" name="monthQ" class="easyui-validatebox" />
				<span>&nbsp;&nbsp;&nbsp;&nbsp;房屋：</span>
				<input style="width:150px;height:24px;" type="text" id="roomNameQ" name="roomNameQ" class="easyui-validatebox" />
				
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="search_<s:property value="rand"/>()">查询</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="import_<s:property value='rand'/>()">导入</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="conf_<s:property value='rand'/>()">确认</a>
			</div>
		</div>
		<div id="edit_<s:property value='rand'/>" style="width:275;height:124;"></div>
		<script type="text/javascript">
			$(function(){
				$("#datagrid_<s:property value='rand'/>").datagrid({
					url:'<n:base/>/jcxx/zhxqJiaofeiWyglyList.action',
					title: '缴费信息',
					loadMsg: '数据正在加载中请稍候......',
			        striped: true,
			        fit: true,
			        checkOnSelect:true,
			        singleSelect:false,
			        rownumbers:true,
			        pagination:true,
			        pageSize: 30,
			        pageList: [10,20,30,50,100]
				});
			});
		</script>
		<script type="text/javascript">
			function search_<s:property value="rand"/>(){
				$('#datagrid_<s:property value='rand'/>').datagrid('load',
						{       
					"zhxqJiaofeiQuery.yearQ": $('#yearQ').val(),
					"zhxqJiaofeiQuery.monthQ": $('#monthQ').val(),
					"zhxqJiaofeiQuery.roomNameQ":$('#roomNameQ').val()
	    				});
			}
			function statusFormatter(value,row,index){
				if(value==0){
					return "<div style='color:yellow;background-color:green;margin:0px;padding:0px;'>已交</div>";
				}else if(value==1){
					return "<div style='color:red;background-color:#CCCCCC;margin:0px;padding:0px;'>未交</div>";
				}else if(value==2){
					return "<div style='color:red;background-color:blue;margin:0px;padding:0px;'>待确认</div>";
				}
			}
			function conf_<s:property value='rand'/>(){
				var keys=$('#datagrid_<s:property value="rand"/>').datagrid('getSelections');
		    	var rows=[],ids=[];
		        if(keys.length>0){
			    	for(var i=0;i<keys.length;i++){
			    		rows.push(keys[i].name);
			    		ids.push(keys[i].id);
			    	}
					$.messager.confirm('消息提醒','您确定费用没有错误吗？',function(r){
						if(r){
							$.getJSON("<n:base/>/jcxx/zhxqJiaofeiWyglyConf.action",{"keys":ids.join(',')},function(data){
								$("#datagrid_<s:property value='rand'/>").datagrid('reload');
								var errorNames=[];
								var errors=data.errors;
								for(var i=0;i<errors.length;i++){
									errorNames.push(errors[i].name);
								}
								var msg='';
								if(errorNames.length>0){
									msg=errorNames.join('、')+'确认失败!';
								}else{
									msg='操作成功';
								}
								$.messager.show({
			            			title:'消息提示',
			            			msg: msg,
			            			showType:'show'
			       				});
							});
						}
					});	 
		         }else{
		         	$.messager.alert('消息提醒','请选择一条记录!','warning');
		         }
			}
			function import_<s:property value='rand'/>(){
				$('#edit_<s:property value="rand"/>').dialog({
					title : '导入',
					bgiframe : true,
					width : 400,
					height : 214,
					modal : true,
					resizable :true,
					href:'<n:base/>/jcxx/zhxqJiaofeiWyglyImport.action',
					iconCls:'icon-save',
					buttons:[{
						text:'提交',
						iconCls:'icon-save',
						handler:function(){submit_form_import()}
					},{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							$("#edit_<s:property value='rand'/>").dialog("close");
							$("#editForm").remove();}
					}]
				});
			}
			
			function submit_form_import(){
				var f = $('#editForm').form('validate');
				if(f){
					$.messager.progress();
					$.ajaxFileUpload({
						url:'<n:base/>/jcxx/zhxqJiaofeiWyglyUpload.action',
						secureuri:false,
						fileElementId:'inputExcel',
						dataType: 'HTML',
						success: function (data, status){
							$.messager.progress('close');
							$("#edit_<s:property value='rand'/>").dialog("close");
							$("#editForm").remove();
							if(data == '导入成功') {
								$("#datagrid_<s:property value='rand'/>").datagrid("reload");
								msg='导入成功';
								$.messager.show({
			            			title:'消息提示',
			            			msg: msg,
			            			showType:'show'
			       				});
							} else {
								$.messager.alert('消息提醒',data,'warning');
							}
						}
					});
				}
			}
		</script>
	</body>
</html>