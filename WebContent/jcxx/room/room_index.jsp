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
					<th field="xiaoquname" width="150">所在小区</th>
					<th field="mendong" width="150">门栋</th>
					<th field="danyuan" width="150">单元</th>
					<th field="menpaihao" width="150">门牌号</th>
					<th field="huzhuname" width="150">户主姓名</th>
					<th field="mianji" width="100">面积(m2)</th>
					<th field="name" width="250">备注</th>
				</tr>
			</thead>
		</table>
	
		<div id="tb_datagrid_<s:property value='rand'/>">
			<div>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openUserDialog_<s:property value="rand"/>('ADD')">增加</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openUserDialog_<s:property value="rand"/>('UPDATE')">修改</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteUser_<s:property value="rand"/>()">删除</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="import_<s:property value='rand'/>()">导入</a>
			</div>
			<div>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;门牌号：</span>
				<input style="width:150px;height:24px;" type="text" id="menpaihaoQ" name="menpaihaoQ" class="easyui-validatebox" />
				<span>&nbsp;&nbsp;&nbsp;&nbsp;户主姓名：</span>
				<input style="width:150px;height:24px;" type="text" id="huzhunameQ" name="huzhunameQ" class="easyui-validatebox" />
				
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="search_<s:property value="rand"/>()">查询</a>
			</div>
		</div>
		<div id="edit_<s:property value='rand'/>" style="width:275;height:124;"></div>
		<script type="text/javascript">
			$(function(){
				$("#datagrid_<s:property value='rand'/>").datagrid({
					url:'<n:base/>/jcxx/zhxqRoomList.action',
					title: '小区信息',
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
					"zhxqRoomQuery.menpaihaoQ": $('#menpaihaoQ').val(),
					"zhxqRoomQuery.huzhunameQ":$('#huzhunameQ').val()
	    				});
			}
			function openUserDialog_<s:property value="rand"/>(action){
		    	var title='';
		    	var id='';
		    	if(action=='ADD'){
		    		title="添加小区房屋";
		    	}else if(action=='UPDATE'){
		    		var keys=$('#datagrid_<s:property value="rand"/>').datagrid('getSelections');
			    	if(keys.length>1){
			    		$.messager.alert('消息提醒','一次只能修改一条信息!','warning');
		         		return false;
			    	}else if(keys.length==0){
			    		$.messager.alert('消息提醒','请选择一条信息!','warning');
		         		return false;
			    	}else{
			    		id=keys[0].id;
		    			title='修改房屋信息';
			    	}
		    	}
		        $('#edit_<s:property value="rand"/>').dialog({
					title : title,
					width : 600,
					height : 'auto',
					modal : true,
					href:'<n:base/>/jcxx/zhxqRoomEdit.action?zhxqRoomModel.id='+id,
					iconCls:'icon-edit',
					resizable :true,
					buttons:[{
						text:'确定',
						iconCls:'icon-ok',
						handler:function(){
							submitUserForm();
						}
					},{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							$('#edit_<s:property value="rand"/>').dialog('close');
						}
					}]
				});
		    }
		
			function deleteUser_<s:property value='rand'/>(){
				var keys=$('#datagrid_<s:property value="rand"/>').datagrid('getSelections');
		    	var rows=[],ids=[];
		        if(keys.length>0){
			    	for(var i=0;i<keys.length;i++){
			    		rows.push(keys[i].name);
			    		ids.push(keys[i].id);
			    	}
					$.messager.confirm('消息提醒','您确定删除<span style="color:red">'+rows.join('、')+'</span>吗？',function(r){
						if(r){
							$.getJSON("<n:base/>/jcxx/zhxqRoomDelete.action",{"keys":ids.join(',')},function(data){
								$("#datagrid_<s:property value='rand'/>").datagrid('reload');
								var errorNames=[];
								var errors=data.errors;
								for(var i=0;i<errors.length;i++){
									errorNames.push(errors[i].name);
								}
								var msg='';
								if(errorNames.length>0){
									msg=errorNames.join('、')+'删除失败!';
								}else{
									msg='删除成功';
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
			function submitUserForm(){
				var f=$('#editForm').form('validate');
				if(f){
					$.get($('#editForm').attr('action'),$('#editForm').serialize(),function(){
						$('#datagrid_<s:property value="rand"/>').datagrid('reload');
						$('#edit_<s:property value="rand"/>').dialog('close');
						$.messager.show({
	            		title:'消息提示',
	            			msg:'操作成功',
	           			showType:'show'
	       				});
					});
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
					href:'<n:base/>/jcxx/zhxqRoomImport.action',
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
						url:'<n:base/>/jcxx/zhxqRoomUpload.action',
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
								$("#datagrid_<s:property value='rand'/>").datagrid("reload");
							}
						}
					});
				}
			}
			
		</script>
	</body>
</html>