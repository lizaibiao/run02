<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global/global.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <title>Bootstrap 101 Template</title>
  </head>
  <body>
    <h1>你好，世界！</h1>
     <button type="button" id="myButton" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off">Loading state</button>
  </body>
<script>
  $('#myButton').on('click', function () {
    var $btn = $(this).button('loading')
    // business logic...
    alert("reset");
    $btn.button('reset')
  })
</script>
</html>