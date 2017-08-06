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
		<link rel="stylesheet" href="<n:base/>/javascript/jquery-easyui/themes/icon.css"/>
		
		
		<link rel="stylesheet" href="<n:base/>/javascript/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<script type="text/javascript" src="<n:base/>/javascript/zTree/js/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="<n:base/>/javascript/zTree/js/jquery.ztree.excheck-3.5.js"></script>
	</head>
	<body>
    	<table id="role_data_<s:property value="rand"/>">
			<thead>
				<tr>
					<th field="roleName" width="100">角色名称</th>
					<th field="insertTime" width="100">插入时间</th>
					<th field="updateTime" width="100">修改时间</th>
				</tr>
			</thead>
		</table> 
		<div id="tb_<s:property value="rand"/>" style="padding:3px;">
			<div>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openRoleDialog_<s:property value="rand"/>('ADD')">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openRoleDialog_<s:property value="rand"/>('UPDATE')">修改</a>
			</div>
		</div> 
		<div id="edit_<s:property value="rand"/>"></div>
		
		<script type="text/javascript">
			$(document).ready(function(){
				$('#role_data_<s:property value="rand"/>').datagrid({
			 		loadMsg: '数据正在加载中，请稍候......',
			        width: 'auto',
			        height: 'auto',
			        striped: true,
			        border: true,
			        fit: true,
			        fitColumns: true,
			        url: '<n:base/>/yhqx/roleList.action',
			        toolbar: '#tb_<s:property value="rand"/>',
			        checkOnSelect: true,
			        singleSelect: true,
			        rownumbers: true,
			        pagination: true,
			        pageSize: 10,
			        pageList: [10,20,30,50,100]
			    });  
			});
		
			function openRoleDialog_<s:property value="rand"/>(action){
		    	var title='';
		    	var id='';
		    	if(action=='DELETE')
		    	{
		    		$.messager.confirm('消息提醒','您确定删除该角色吗？',function(r){
						if(r){
							var keys=$('#role_data_<s:property value="rand"/>').datagrid('getSelections');
					    	if(keys.length==0){
					    		$.messager.alert('消息提醒','请选择一条记录删除!','warning');
				         		return false;
					    	}else{
					    		id=keys[0].id;
					    	}
							$.getJSON("<n:base/>/yhqx/roleDelete.action?roleModel.id="+id,function(data){
								$('#role_data_<s:property value="rand"/>').datagrid('reload');
								var errorNames=[];
								var errors=data.errors;
								for(var i=0;i<errors.length;i++){
									errorNames.push(errors[i].name);
								}
								var msg='';
								if(errorNames.length>0){
									msg = errorNames.join('、')+'删除失败!';
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
		    	}
		    	else
		    	{
			    	if(action=='ADD'){
			    		title="添加角色信息";
			    	}else if(action=='UPDATE'){
			    		var keys=$('#role_data_<s:property value="rand"/>').datagrid('getSelections');
				    	if(keys.length>1){
				    		$.messager.alert('消息提醒','一次只能修改一条信息!','warning');
			         		return false;
				    	}else if(keys.length==0){
				    		$.messager.alert('消息提醒','请选择一条信息!','warning');
			         		return false;
				    	}else{
				    		id=keys[0].id;
				    		var roleName = keys[0].roleName;
			    			title='修改<span style="color:red;">'+roleName+'</span>角色对应的功能(已勾选)信息';
				    	}
			    	}
			        $('#edit_<s:property value="rand"/>').dialog({
						title : title,
						width : 450,
						height : 500,
						modal : true,
						href:'<n:base/>/yhqx/roleEdit.action?roleModel.id='+id,
						iconCls:'icon-edit',
						resizable :true,
						buttons:[{
							text:'确定',
							iconCls:'icon-ok',
							handler:function(){
								submitRoleForm();
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
		    }
			
			function submitRoleForm(){
				var f=$('#roleForm_edit').form('validate');
				if(f){
					var functionIds=[];
					var operationIds=[];
					var treeObj = $.fn.zTree.getZTreeObj("funcTree_roleEdit");
					var nodes = treeObj.getCheckedNodes(true);
					for(var i=0;i<nodes.length;i++){
						var type=nodes[i].type;
						if(type=='function'){
			           		functionIds.push(nodes[i].id);
						}else if(type=='operation'){
							operationIds.push(nodes[i].id);
						}
		       		}
					$.get($('#roleForm_edit').attr('action'),$('#roleForm_edit').serialize()+"&funIds="+functionIds.join(',')+"&operIds="+operationIds.join(','),function(){
						$('#edit_<s:property value="rand"/>').dialog('close');
						$('#role_data_<s:property value="rand"/>').datagrid('reload');
						$.messager.show({
	            			title:'消息提示',
	            			msg:'操作成功',
	            			showType:'show'
	       				});
					});
				}
			}
			
			/* 
			function submitRoleForm(){
				var f=$('#roleForm_edit').form('validate');
				if(f){
					var ids=[];
					var nodes = $('#funcTree_roleEdit').tree('getChecked', ['checked','indeterminate']);//获取选中节点和半选节点
					for(var i=0;i<nodes.length;i++){
		           		ids.push(nodes[i].id);
		       		}
					$.get($('#roleForm_edit').attr('action'),$('#roleForm_edit').serialize()+"&keys="+ids.join(','),function(){
						$('#edit_<s:property value="rand"/>').dialog('close');
						$('#role_data_<s:property value="rand"/>').datagrid('reload');
						$.messager.show({
	            			title:'消息提示',
	            			msg:'操作成功',
	            			showType:'show'
	       				});
					});
				}
			}
			 */
		</script>
	</body>
</html>
