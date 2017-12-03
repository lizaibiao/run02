<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global/global.jsp" %>
<html>
<head>
<title>业务字典</title>
<style type="text/css">
body {
	font: 12px/20px "微软雅黑", "宋体", Arial, sans-serif, Verdana, Tahoma;
	padding: 0;
	margin: 0;
}
</style>
<script type="text/javascript">
/*
 *多条件查询方法
*/
function doSearch2(){
		$('#userGrid2').datagrid('load','${ctx}/admin/user/getUserPage.do',{"name2":"5555"});
	};

</script>
</head>
<body>
	<div>业务字典</div>
	<table id="userGrid2"></table>
	<a href="#" class="easyui-linkbutton"  onclick="doSearch2()">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
</body>
</html>