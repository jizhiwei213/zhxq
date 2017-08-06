<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="n" uri="/commonTag"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<n:nocache />
<div class="tdk">
	<ul>
		<s:iterator value="list" status="st">
			<li onclick="addTabs('<s:if test="funUrl.startsWith('http')"><s:property value="funUrl" /></s:if><s:else><n:base/>/<s:property value="funUrl" /></s:else>')">
				<s:property value="funName" />
			</li>
			<s:if test="#st.first&&isLoadOpen==1">
				<script type="text/javascript">
					$(document).ready(function(){addTabs('<s:if test="funUrl.startsWith('http')"><s:property value="funUrl" /></s:if><s:else><n:base/>/<s:property value="funUrl" /></s:else>');});
				</script>
			</s:if>
		</s:iterator>
	</ul>
</div>