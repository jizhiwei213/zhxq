<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="n" uri="/commonTag"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<n:nocache />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%
String dataType = request.getParameter("dataDictionary.dataType");
%>
	<head>
		<title></title>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<script type="text/javascript" src="<n:base/>/javascript/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="<n:base/>/javascript/jquery-easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<n:base/>/javascript/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
		
		<link rel="stylesheet" href="<n:base/>/javascript/jquery-easyui/themes/default/easyui.css"/>
		<link rel="stylesheet" href="<n:base/>/javascript/jquery-easyui/themes/default/main.css"/>
		<link rel="stylesheet" href="<n:base/>/javascript/jquery-easyui/themes/icon.css"/>
	</head>
	<body>
		<table id="sjzd_<s:property value='rand'/>" toolbar="#tb_sjzd_<s:property value='rand'/>" fitColumns="true">
			<thead>
				<tr>
					<th checkbox="true"></th>
					<th field="name" width="100">名称</th>
					<th field="dataType" width="100">类型</th>
					<th field="dataValue" width="100">数据值</th>
				</tr>
			</thead>
		</table>
	
		<div id="tb_sjzd_<s:property value='rand'/>">
			<div>
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"  onclick="openEditDialog_<s:property value='rand'/>('ADD')">新增</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"  onclick="openEditDialog_<s:property value='rand'/>('UPDATE')">修改</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteDataDictionary_<s:property value='rand'/>()">删除</a>
			</div>
		</div>
		
		<div id="edit_<s:property value='rand'/>" style="width:275;height:124;"></div>
		
		<script type="text/javascript">
			$(function(){
				loadInfo("sjzd_<s:property value='rand'/>","<n:base/>/sjzd/dataDictionaryList.action?dataDictionary.dataType=<%=dataType%>");
			});
			function loadInfo(datagridId,url){
				$("#"+datagridId).datagrid({
					url:url,
					loadMsg:'数据正在加载中请稍候......',
			        striped: true,
			        fit: true,
			        checkOnSelect:true,
			        singleSelect:false,
			        rownumbers:true,
			        pagination:true,
			        pageSize: 10,
			        pageList: [10,20,30,50,100]
				});
			}
		</script>
		<script type="text/javascript">
			function openEditDialog_<s:property value="rand"/>(action){
		    	var title="";
		    	var id="";
		    	if(action=='ADD'){
		    		title="新增";
		    	}else if(action=='UPDATE'){
		    		title="修改";
		    		var keys=$('#sjzd_<s:property value="rand"/>').datagrid('getSelections');
		        	if(keys==''){
		        		$.messager.alert('消息提醒','请选择要修改的信息!','warning');
		        		return false;
		        	}else if(keys.length==1){
		        		id = keys[0].id;
		        	}else{
		        		$.messager.alert('消息提醒','请勿一次修改多条信息!','warning');
		        		return false;
		        	}
		    	}
		      	$('#edit_<s:property value="rand"/>').dialog( {
					title : title,
					//width : 275,
					height : 'auto',
					href:'<n:base/>/sjzd/dataDictionaryEdit.action?dataDictionary.dataType=<%=dataType%>&dataDictionary.id='+id,
					modal : true,
					iconCls:'icon-save',
					resizable :true,
					buttons:[{
						text:'确定',
						iconCls:'icon-ok',
						handler:function(){
							sjzd_submit_<s:property value='dataDictionary.dataType'/>();
						}
					},{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							sjzd_close_<s:property value='dataDictionary.dataType'/>();
						}
					}]
				});
		    }
			//删除
			function deleteDataDictionary_<s:property value='rand'/>(){
				var keys=$('#sjzd_<s:property value="rand"/>').datagrid('getSelections');
		    	var rows=[],ids=[];
		        if(keys.length>0){
			    	for(var i=0;i<keys.length;i++){
			    		rows.push(keys[i].name);
			    		ids.push(keys[i].id);
			    	}
					$.messager.confirm('消息提醒','您确定删除<span style="color:red">'+rows.join('、')+'</span>吗？',function(r){
						if(r){
							$.getJSON("<n:base/>/sjzd/dataDictionaryDelete.action",{"keys":ids.join(',')},function(data){
								$("#sjzd_<s:property value='rand'/>").datagrid('reload');
								var errorNames=[];
								var errors=data.errors;
								for(var i=0;i<errors.length;i++){
									errorNames.push(errors[i].name);
								}
								var msg='';
								if(errorNames.length>0){
									msg='<span style="color:red">'+errorNames.join('、')+'</span> 删除失败！'
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
		</script>
		<script type="text/javascript">
			$(function(){
				$("#sjzd_Form").remove();
			});
			function sjzd_submit_<s:property value='dataDictionary.dataType'/>(){
				var v=$("#sjzd_Form").form('validate');
				if(v){
					$.post($("#sjzd_Form").attr("action"),$("#sjzd_Form").serialize(),function(data){
						$("#edit_<s:property value='rand'/>").dialog("close");
						$("#sjzd_<s:property value='rand'/>").datagrid("reload");
						$("#sjzd_Form").remove();
					});
				}
			}
			function sjzd_close_<s:property value='dataDictionary.dataType'/>(){
				$("#edit_<s:property value='rand'/>").dialog("close");
				$("#sjzd_Form").remove();
			}
		</script>
	</body>
</html>