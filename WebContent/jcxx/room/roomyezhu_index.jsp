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
					<th field="menpaihao" width="150">门牌号</th>
					<th field="huzhuname" width="150">户主姓名</th>
					<th field="users" width="150">相应住户信息</th>
					<th field="mianji" width="100">面积(m2)</th>
					<th field="name" width="150">备注</th>
				</tr>
			</thead>
		</table>
	
		<div id="tb_datagrid_<s:property value='rand'/>">
			<div>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openUserDialog_<s:property value="rand"/>('ADD')">增加</a>
			</div>
			<div>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;名称：</span>
				<input style="width:150px;height:24px;" type="text" id="nameQ" name="nameQ" class="easyui-validatebox" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="search_<s:property value="rand"/>()">查询</a>
			</div>
		</div>
			<div id="edit_<s:property value='rand'/>" style="width:275;height:124;"></div>
		<script type="text/javascript">
			$(function(){
				$("#datagrid_<s:property value='rand'/>").datagrid({
					url:'<n:base/>/jcxx/zhxqRoomByYzList.action',
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
					"zhxqRoomQuery.nameQ": $('#nameQ').val(),
					"zhxqRoomQuery.huzhunameQ":$('#huzhunameQ').val()
	    		});
			}
			
			function openUserDialog_<s:property value="rand"/>(action){
		    	var title='';
		    	var id='';
		    	if(action=='ADD'){
		    		title="添加住房";
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
					href:'<n:base/>/jcxx/zhxqRoomYzEdit.action?zhxqRoomModel.id='+id,
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
		</script>
	</body>
</html>