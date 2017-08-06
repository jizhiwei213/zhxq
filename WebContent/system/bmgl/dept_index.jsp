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
    	<table id="dept_data_<s:property value="rand"/>">
			<thead>
				<tr>
					<!-- <th checkbox="true"></th> -->
					<th field="deptName" width="100">部门名称</th>
					<th field="deptNo" width="100">部门编号</th>
					<th field="principal" width="100">负责人</th>
					<th field="linkTel" width="100">联系电话</th>
					<th field="remark" width="300">备注</th>
				</tr>
			</thead>
		</table> 
		<div id="tb_<s:property value="rand"/>" style="padding:3px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="$('#dept_data_<s:property value="rand"/>').treegrid('reload')">刷新</a>
		</div>
		<div id="edit_<s:property value="rand"/>"></div>
		
		<div id="context_menu_<s:property value="rand"/>" class="easyui-menu">
		    <div data-options="iconCls:'icon-add'">
		        <span>新增</span>
		        <div style="width:150px;">
		            <div onclick="openDeptDialog_<s:property value="rand"/>('ADD')">同级部门</div>
		            <div class="menu-sep"></div>
		            <div onclick="openDeptDialog_<s:property value="rand"/>('ADD_NEXT')">下级部门</div>
		        </div>
		    </div>
		    <div class="menu-sep"></div>
		    <div data-options="iconCls:'icon-edit'" onclick="openDeptDialog_<s:property value="rand"/>('UPDATE')">修改</div>
		    <n:auth code="deleteDept">
		    <div class="menu-sep"></div>
		    <div data-options="iconCls:'icon-remove'" onclick="openDeptDialog_<s:property value="rand"/>('DELETE')">删除</div>
		    </n:auth>
		</div>
		
		<script type="text/javascript">
			$(document).ready(function(){
				$('#dept_data_<s:property value="rand"/>').treegrid({
			 		loadMsg: '数据正在加载中，请稍候......',
			        width: 'auto',
			        height: 'auto',
			        idField: 'id',//treegrid 必需属性
			        treeField: 'deptName',//treegrid 必需属性 
			        striped: true,
			        border: true,
			        fit: true,
			        fitColumns: true,
			        url: '<n:base/>/yhqx/deptList.action',
			        toolbar: '#tb_<s:property value="rand"/>',
			        checkOnSelect: true,
			        singleSelect: true,
			        rownumbers: true,
			        onContextMenu: function(e, row){
			        	e.preventDefault();
			        	selectRow=row;//缓存点击的行数据
			        	$(this).treegrid('select', row.id);
			        	$('#context_menu_<s:property value="rand"/>').menu('show',{
			        		left:e.pageX,
			        		top:e.pageY
			        	});
			        }
			    });  
			});
			
			var selectRow={};//缓存点击的行数据
			function openDeptDialog_<s:property value="rand"/>(action){
		    	var title='';
		    	var href='';
		    	if(action=='DELETE')
		    	{
		    		$.messager.confirm('消息提醒','您确定删除该部门吗？',function(r){
						if(r){
							var id=selectRow.id;
							$.getJSON("<n:base/>/yhqx/deptDelete.action?deptModel.id="+id,function(data){
								$('#dept_data_<s:property value="rand"/>').treegrid('reload');
								var errorNames=[];
								var errors=data.errors;
								for(var i=0;i<errors.length;i++){
									errorNames.push(errors[i].name);
								}
								var msg='';
								if(errorNames.length>0){
									alert(errorNames.join('、')+'删除失败!');
								}else{
									msg='删除成功';
									$.messager.show({
				            			title:'消息提示',
				            			msg: msg,
				            			showType:'show'
				       				});
								}
							});
						}
					});	 
		    	}
		    	else
		    	{
			    	if(action=='ADD'){
			    		title="添加部门信息";
			    		var parentId='';
			    		if(selectRow._parentId){
			    			parentId=selectRow._parentId;
			    		}
			    		if(parentId==''){
			    			href='<n:base/>/yhqx/deptEdit.action';
			    		}else{
			    			href='<n:base/>/yhqx/deptEdit.action?deptModel.parent.id='+parentId;
			    		}
			    	}else if(action=='ADD_NEXT'){
			    		title="添加部门信息";
			    		parentId=selectRow.id;
			    		href='<n:base/>/yhqx/deptEdit.action?deptModel.parent.id='+parentId;
			    	}else if(action=='UPDATE'){
			    		title="修改部门信息";
			    		var id=selectRow.id;
			    		href='<n:base/>/yhqx/deptEdit.action?deptModel.id='+id;
			    	}
			    	else{
			    		return false;
			    	}
			        $('#edit_<s:property value="rand"/>').dialog({
						title : title,
						width : 468,
						height : 'auto',
						modal : true,
						href: href,
						iconCls:'icon-edit',
						resizable :true,
						buttons:[{
							text:'确定',
							iconCls:'icon-ok',
							handler:function(){
								submitDeptForm();
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
			
			function submitDeptForm(){
				var f=$('#deptForm_edit').form('validate');
				if(f){
					$.get($('#deptForm_edit').attr('action'),$('#deptForm_edit').serialize(),function(){
						$('#dept_data_<s:property value="rand"/>').treegrid('reload');
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
