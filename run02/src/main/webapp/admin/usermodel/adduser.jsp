<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global/global.jsp" %>
<html>
<head>
<title>添加用户</title>
<style type="text/css">
body {
	font: 12px/20px "微软雅黑", "宋体", Arial, sans-serif, Verdana, Tahoma;
	padding: 0;
	margin: 0;
}
</style>


</head>
<body>
 <!--添加用户 -->
	 <form id="adduserForm">
		<table style="width: 100%;border-collapse:separate; border-spacing:5px;padding-left:30px;padding-top:10px;" >
				<tr>
					<td style="text-align:right;width:120px;">用户编码<span style="color: red;">*</span></td>
					<td style="width: 280px;"><input class="easyui-textbox" name="code"/></td>
				</tr>
				<tr>
					<td style="text-align:right;width:120px;">用户名<span style="color: red;">*</span></td>
					<td><input type="text" class="easyui-textbox"  name="name"/></td>
				</tr>
			    <tr>
					<td style="text-align:right;width:120px;">手机号<span style="color: red;">*</span></td>
					<td><input type="text" class="easyui-textbox" name="mobile" /></td>
				</tr>
			</table>
	</form>
</body>
</html>