<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=100" />
<%
	response.setHeader("Charset","GB2312");
	response.setContentType("text/html;charset=UTF-8"); 
	response.setHeader("Pragma","No-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0);
%>
<c:set var="ctx" value="<%=request.getContextPath() %>"/>
<link rel="stylesheet" type="text/css" href="${ctx}/common/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/common/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/common/easyui/demo/demo.css">
<script type="text/javascript" src="${ctx}/common/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/common/easyui/jquery.easyui.min.js"></script>
<!-- easyui 中文化 -->
<script type="text/javascript" src="${ctx}/common/easyui/locale/easyui-lang-zh_CN.js"></script>


