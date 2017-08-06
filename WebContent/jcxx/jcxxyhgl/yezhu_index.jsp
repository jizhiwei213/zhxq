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
		
		<link rel="stylesheet" href="<n:base/>/javascript/jquery-easyui/themes/default/easyui.css"/>
		<link rel="stylesheet" href="<n:base/>/javascript/jquery-easyui/themes/default/main.css"/>
		<link rel="stylesheet" href="<n:base/>/javascript/jquery-easyui/themes/icon.css"/>
	</head>
	<body>
    	<table id="user_data_<s:property value="rand"/>">
			<thead>
				<tr>
					<th checkbox="true"></th>
					<th field="userName" width="100">姓名</th>
					<th field="userAccount" width="100">登录帐号</th>
					<th field="sex" width="100">性别</th>
					<th field="linkTel" width="100">联系电话</th>
					<th field="email" width="100">邮箱</th>
					<th field="isStop" width="50" formatter="statusFormatter" align="center">状态</th>
				</tr>
			</thead>
		</table> 
		<div id="tb_<s:property value="rand"/>" style="padding:3px;">
			<div>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;姓名 ：</span>
				<input style="width:150px;height:24px;" type="text" id="userName" name="userName" class="easyui-validatebox" />
				<span>电话 ：</span>
				<input style="width:150px;height:24px;" type="text" id="userTel" name="userTel" class="easyui-validatebox" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="search_<s:property value="rand"/>()">查询</a>
			</div>
			<div>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openUserDialog_<s:property value="rand"/>('ADD')">增加</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openUserDialog_<s:property value="rand"/>('UPDATE')">修改</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="stopUser_<s:property value="rand"/>()">停用</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="startUser_<s:property value="rand"/>()">启用</a>
				<!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteUser_<s:property value="rand"/>()">删除</a>
				 -->
			</div>
			<div>
				<span style="color:red;">提醒：用户初始密码为123456，请及时修改初始密码！</span>
			</div>
		</div>
		<div id="edit_<s:property value="rand"/>"></div>
		
		<script type="text/javascript">
			$(document).ready(function(){
				$('#user_data_<s:property value="rand"/>').datagrid({
			 		loadMsg: '数据正在加载中，请稍候......',
			        width: 'auto',
			        height: 'auto',
			        striped: true,
			        border: true,
			        fit: true,
			        fitColumns: true,
			        url: '<n:base/>/jcxxyhgl/yezhuList.action',
			        toolbar: '#tb_<s:property value="rand"/>',
			        checkOnSelect: true,
			        singleSelect: false,
			        rownumbers: true,
			        pagination: true,
			        pageSize: 30,
			        pageList: [10,20,30,50,100]
			    });  
			});
			
			function statusFormatter(value,row,index){
				if(value==0){
					return "<div style='color:yellow;background-color:green;margin:0px;padding:0px;'>正常</div>";
				}else if(value==1){
					return "<div style='color:red;background-color:#CCCCCC;margin:0px;padding:0px;'>停用</div>";
				}
			}
		
			function openUserDialog_<s:property value="rand"/>(action){
		    	var title='';
		    	var id='';
		    	if(action=='ADD'){
		    		title="添加用户信息";
		    	}else if(action=='UPDATE'){
		    		var keys=$('#user_data_<s:property value="rand"/>').datagrid('getSelections');
			    	if(keys.length>1){
			    		$.messager.alert('消息提醒','一次只能修改一条信息!','warning');
		         		return false;
			    	}else if(keys.length==0){
			    		$.messager.alert('消息提醒','请选择一条信息!','warning');
		         		return false;
			    	}else{
			    		id=keys[0].id;
		    			title='修改用户信息';
			    	}
		    	}
		        $('#edit_<s:property value="rand"/>').dialog({
					title : title,
					width : 600,
					height : 'auto',
					modal : true,
					href:'<n:base/>/jcxxyhgl/yezhuEdit.action?userModel.id='+id,
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
			
			function stopUser_<s:property value="rand"/>(){
				var keys=$('#user_data_<s:property value="rand"/>').datagrid('getSelections');
		    	var rows=[],ids=[];
		        if(keys.length>0){
			    	for(var i=0;i<keys.length;i++){
			    		rows.push(keys[i].name);
			    		ids.push(keys[i].id);
			    	}
					$.messager.confirm('消息提醒','您确定禁用<span style="color:red">'+rows.join('、')+'</span>吗？',function(r){
						if(r){
							$.getJSON("<n:base/>/jcxxyhgl/yezhuStop.action",{"keys":ids.join(',')},function(data){
								$("#user_data_<s:property value='rand'/>").datagrid('reload');
								var errorNames=[];
								var errors=data.errors;
								for(var i=0;i<errors.length;i++){
									errorNames.push(errors[i].name);
								}
								var msg='';
								if(errorNames.length>0){
									msg=errorNames.join('、')+'禁用失败!';
								}else{
									msg='禁用成功';
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
			function startUser_<s:property value="rand"/>(){
				var keys=$('#user_data_<s:property value="rand"/>').datagrid('getSelections');
		    	var rows=[],ids=[];
		        if(keys.length>0){
			    	for(var i=0;i<keys.length;i++){
			    		rows.push(keys[i].name);
			    		ids.push(keys[i].id);
			    	}
					$.messager.confirm('消息提醒','您确定启用<span style="color:red">'+rows.join('、')+'</span>吗？',function(r){
						if(r){
							$.getJSON("<n:base/>/jcxxyhgl/yezhuStart.action",{"keys":ids.join(',')},function(data){
								$("#user_data_<s:property value='rand'/>").datagrid('reload');
								var errorNames=[];
								var errors=data.errors;
								for(var i=0;i<errors.length;i++){
									errorNames.push(errors[i].name);
								}
								var msg='';
								if(errorNames.length>0){
									msg=errorNames.join('、')+'启用失败!';
								}else{
									msg='启用成功';
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
			function deleteUser_<s:property value='rand'/>(){
				var keys=$('#user_data_<s:property value="rand"/>').datagrid('getSelections');
		    	var rows=[],ids=[];
		        if(keys.length>0){
			    	for(var i=0;i<keys.length;i++){
			    		rows.push(keys[i].name);
			    		ids.push(keys[i].id);
			    	}
					$.messager.confirm('消息提醒','您确定删除<span style="color:red">'+rows.join('、')+'</span>吗？',function(r){
						if(r){
							$.getJSON("<n:base/>/jcxxyhgl/yezhuDelete.action",{"keys":ids.join(',')},function(data){
								$("#user_data_<s:property value='rand'/>").datagrid('reload');
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
				var f=$('#userForm_edit').form('validate');
				//if(f){
				//	$.get($('#userForm_edit').attr('action'),$('#userForm_edit').serialize(),function(){
				//		$('#user_data_<s:property value="rand"/>').datagrid('reload');
				//		$('#edit_<s:property value="rand"/>').dialog('close');
				//		$.messager.show({
	            //			title:'消息提示',
	            //			msg:'操作成功',
	           // 			showType:'show'
	       		//		});
				//	});
				//}
				if(f){
					$('#userForm_edit').submit();
				}
			}
			function search_<s:property value="rand"/>(){
				$('#user_data_<s:property value='rand'/>').datagrid('load',
						{       
					"userQuery.userName": $('#userName').val(),
					"userQuery.userTel":$('#userTel').val(),
					"userQuery.userCode":$('#userCode').val()
	    				});
			}
		</script>
	</body>
</html>
