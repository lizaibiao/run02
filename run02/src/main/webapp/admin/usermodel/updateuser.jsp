<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global/global.jsp" %>
<html>
<head>
<title>修改用户</title>
<style type="text/css">
body {
	font: 12px/20px "微软雅黑", "宋体", Arial, sans-serif, Verdana, Tahoma;
	padding: 0;
	margin: 0;
}
</style>


</head>
<body>
 <!--修改用户 -->
	 <form id="updateuserForm">
	  <input   type="hidden"  id="Uid" name="id"/>
		<table style="width: 100%;border-collapse:separate; border-spacing:5px;padding-left:30px;padding-top:10px;" >
				<tr>
					<td style="text-align:right;width:120px;">用户编码<span style="color: red;">*</span></td>
					<td style="width: 280px;"><input class="easyui-textbox"  id="Ucode"  name="code"/></td>
				</tr>
				<tr>
					<td style="text-align:right;width:120px;">用户名<span style="color: red;">*</span></td>
					<td><input type="text" class="easyui-textbox"   id="Uname" name="name"/></td>
				</tr>
			    <tr>
					<td style="text-align:right;width:120px;">手机号<span style="color: red;">*</span></td>
					<td><input type="text" class="easyui-textbox"  id="Umobile"  name="mobile" /></td>
				</tr>
				<tr>
					<td style="text-align:right;width:120px;">状态</td>
					<td>
						<select>
						  <option>1</option>
						  <option>2</option>
						</select>
					</td>
				</tr>
			</table>
	</form>
</body>
</html>