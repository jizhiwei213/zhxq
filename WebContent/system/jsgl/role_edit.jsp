<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<body>
		<div style="margin:10px 20px;">
			<form id="roleForm_edit" action="<n:base/>/yhqx/roleSaveUpdate.action" style="margin:0px;padding:0px;">
				<input type="hidden" id="roleId_edit" name="roleModel.id" value="<s:property value='roleModel.id'/>" />
				<input type="hidden" id="roleId_edit" name="roleModel.insertTime" value="<s:property value='roleModel.insertTime'/>" />
				<span>角色名称：</span>
				<input type="text" id="roleName_edit" name="roleModel.roleName" value="<s:property value='roleModel.roleName'/>" class="easyui-validatebox" data-options="required:true" />
				<br/>
			</form>
			<span>勾选功能权限：</span>
			<%-- <ul id="funcTree_roleEdit" class="easyui-tree" data-options="
				checkbox: true,  
	            animate: false,   
	            lines: true,   
	            url: '<n:base/>/yhqx/funcTree.action?roleModel.id=<s:property value="roleModel.id"/>&rand='+Math.random(), 
	            onDblClick:function(node){  
	            	if(node.state=='closed'){
	            		$(this).tree('expand',node.target);
	            	}else if(node.state=='open'){
	            		$(this).tree('collapse',node.target);
	            	}
	            }
			"></ul> --%>
			<br/>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:false" onclick="expandAll()">展开/折叠</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:false" onclick="checkAll()">全选/全不选</a>
			<ul id="funcTree_roleEdit" class="ztree"></ul>
			<script type="text/javascript">
				var setting = {
					check: {
						enable: true , 
						chkboxType: { "Y" : "p", "N" : "ps" }
					},
					data: {
						simpleData: {
							enable: true
						}
					}
				};
				$(function(){
					$.getJSON('<n:base/>/yhqx/funcTree.action?roleModel.id=<s:property value="roleModel.id"/>&rand='+Math.random(),function(data){
						$.fn.zTree.init($("#funcTree_roleEdit"), setting, data);
					});
				});
				var expand=true;
				function expandAll(){
					var treeObj = $.fn.zTree.getZTreeObj("funcTree_roleEdit");
					expand=!treeObj.expandAll(expand);
				}
				var check=true;
				function checkAll(){
					var treeObj = $.fn.zTree.getZTreeObj("funcTree_roleEdit");
					treeObj.checkAllNodes(check);
					check=!check;
				}
			</script>
		</div>
	</body>
</html>
