<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global/global.jsp" %>
<html>
<c:set var="ctx" value="<%=request.getContextPath()%>" />
<head>
<title>用户管理</title>
<style type="text/css">
body {
	font: 12px/20px "微软雅黑", "宋体", Arial, sans-serif, Verdana, Tahoma;
	padding: 0;
	margin: 0;
}
</style>

<script type="text/javascript" >
 $(document).ready(function(){
	 //用户list
  $("#userGrid").datagrid({
	   // url :"${ctx}/admin/user/getUserPage.do", //获取数据地址
	    iconCls : "icon-save", //图标
		pagination : true, //开启分页
		pageSize : 10, //每页显示数目 
		pageList : [10, 20, 30, 40, 50 ], //选择每页显示数目(与pageSize对应)
		fit : false, //适应大小
		fitColumns : false, //自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动
		nowrap : false, //是否关闭自动换行(true关闭，false开启)
		border : true, //边框
		remoteSort : true,//是否往后台传排序
		singleSelect : false,//设置为 true，则只允许选中一行。
		checkOnSelect:false,//点击整行,复选框是否被选中
		rownumbers : true,
    	sortName : "createTime",
		sortOrder : 'desc', 
		striped:true,
	    collapsible:true,
		columns : [[
		            {title : "序号",field : "xuid",align : 'center',checkbox:true,width : 200},
					{title : "ID",field : "id",align : 'center',checkbox:true,width : 100,hidden:true},
		            {title : "用户编码",field : "code",sortable:true,align : 'center',width : 200},
		            {title : "用户名",field : "name",sortable:true,align : 'center',width : 200},
		            {title : "手机号",field : "mobile",sortable:true,align : 'center',width : 200},
		            {title : "创建时间",field : "createTime",sortable:true,align : 'center',width : 200,formatter:function(time){
		            	return formatDateTime(time);
		            }},
		            {title : "最后登录时间",field : "lastLoginTime",sortable:true,align : 'center',width : 200,formatter:formatDateChina},
		            {title : "状态",field : "status",sortable:true,align : 'center',width : 200,
		                formatter: function (value, rec) {
		                   if(value=='1'){
		                	   return "启用";
		                   }else{
		                	   return "不启用";
		                   }
		                }
		            },
		            {title : "操作",field:"opt",sortable:true,width : 200,align : 'center',
		            	 formatter:function(value,rec){  
		                     var btn = '<a  onclick="editRow(\''+rec.id+'\')" href="javascript:void(0)">编辑</a>';  
		                     return btn;  
		                 }  
		            }
		]],
		toolbar: [{
			    text : '添加',iconCls : 'icon-add',handler : function() {
				$("#add").show();
				$('#add').dialog({title : '添加用户',width : 500,height :200,top:150,closed : false,cache : false,modal : true,
					href : '${ctx}/admin/usermodel/adduser.jsp',
					buttons : [
						{text : '提交',iconCls : 'icon-ok',
							handler : function() {//添加时,异步提交
							$.ajax({
										type : "POST",
										url : "${ctx}/admin/user/saveUser.do",
										data : $('#adduserForm').serialize(),// 你的formid
										async : false,
										error : function(request) {
											alert("Connection error");
										},
										success : function(data) {
											$("#add").dialog('close');
									 		$('#userGrid').datagrid('load','${ctx}/admin/user/getUserPage.do');
										}
										});
							}
						},
						{iconCls : 'icon-no',
							text : '关闭',
							handler : function() {
								$("#add").dialog('close');
							}
						} ]
			});
			}
		},
		{
		    text : '删除',iconCls : 'icon-remove',handler : function() {
		    	$.messager.confirm('Confirm',' 你确定要删除吗?',function(r){
		    	    if (r){//如果确定删除
		    	    	var selectedRow = $('#userGrid').datagrid('getSelections');  //获取选中行  
		    	    	if (selectedRow.length==0) {  
		    	            $.messager.alert("提示", "请选择要删除的行！", "info");  
		    	            return;  
		    	        }else{ 
		    	        	var temID="";  
		    	            //批量获取选中行的评估模板ID  
		    	            for (i = 0; i < selectedRow.length;i++) {  
		    	                if (temID =="") {  
		    	                    temID = selectedRow[i].id ;  
		    	                } else {  
		    	                    temID = selectedRow[i].id  + "," + temID;  
		    	                }                 
		    	            } 
		    	    		$.ajax({//进行删除的操作
								type : "GET",
								url : "${ctx}/admin/user/delUser.do?ids="+temID,
								async : false,
								error : function(request) {
									alert("Connection error");
								},
								success : function(data) {
							 		$('#userGrid').datagrid('load','${ctx}/admin/user/getUserPage.do');
								}
								});
		    	        }
		    	    }
		    	});		
				}
		}
		]
   });
 });
 
	/*  
	*格式话时间(中文)
	*/
 function formatDateChina(value,row,index){  
     var unixTimestamp = new Date(value);  
     return unixTimestamp.toLocaleString();  
     };
     
  /* *格式化时间（标准） */
function formatDateTime(inputTime) {    
    var date = new Date(inputTime);  
    var y = date.getFullYear();    
    var m = date.getMonth() + 1;    
    m = m < 10 ? ('0' + m) : m;    
    var d = date.getDate();    
    d = d < 10 ? ('0' + d) : d;    
    var h = date.getHours();  
    h = h < 10 ? ('0' + h) : h;  
    var minute = date.getMinutes();  
    var second = date.getSeconds();  
    minute = minute < 10 ? ('0' + minute) : minute;    
    second = second < 10 ? ('0' + second) : second;   
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;    
};  
     
  /*
   *多条件查询方法
  */
  function doSearch(){
	  	var postData={};
		 postData["code"]=$("#code").val();
		 postData["name"]=$("#name").val();
	     postData["createTime"]=$("#createTime").datetimebox('getValue');
		 postData["createTime2"]=$("#createTime2").datetimebox('getValue'); 
 		//$('#userGrid').datagrid('load','${ctx}/admin/user/getUserPage.do',postData);
 		$('#userGrid').datagrid('options').queryParams=postData;
 		$('#userGrid').datagrid('load','${ctx}/admin/user/getUserPage.do');
 	};
/* 重置
 */  
  function doReset(){
	//$("#tb input,#tb select").val("");
	 $("#code").textbox('clear');
	 $("#name").textbox('clear');
	 $("#createTime").textbox('clear');
	 $("#createTime2").textbox('clear');
	};
	
/*
 * 编辑
 */
	 function editRow(id){
		  $.post("${ctx}/admin/user/getOneUser.do",{id:id},function(result){
			  if(result !=null){
		        $("#update").show();
				$('#update').dialog({title : '修改用户',width : 500,height :300,top:150,closed : false,cache : false,modal : true,
				href : '${ctx}/admin/usermodel/updateuser.jsp',
			    onLoad: function () {
					$("#Uname").textbox("setValue",result.en.name);
					$("#Ucode").textbox("setValue",result.en.code);
					$("#Umobile").textbox("setValue",result.en.mobile);
					$("#Uid").val(result.en.id);
				},
					buttons : [
						{text : '提交',iconCls : 'icon-ok',
							handler : function() {//添加时,异步提交
							$.ajax({
										type : "POST",
										url : "${ctx}/admin/user/updateUser.do",
										data : $('#updateuserForm').serialize(),// 你的formid
										async : false,
										error : function(request) {
											alert("Connection error");
										},
										success : function(data) {
											$("#update").dialog('close');
									 		$('#userGrid').datagrid('load','${ctx}/admin/user/getUserPage.do');
										}
										});
							}
						},
						{iconCls : 'icon-no',
							text : '关闭',
							handler : function() {
								$("#update").dialog('close');
							}
						} ]
			});
			  }else{
					alert("Connection error");
			  }
			  });
	}
	
	
</script>

</head>
<body>
	<div id="tb" style="padding:3px">
	<span>用户编码</span>
	<input id="code" class="easyui-textbox" />
	<span>用户名</span>
	<input id="name"  class="easyui-textbox" />
	<span>创建时间</span>
	<input id="createTime"  data-options="showSeconds:true"  style="width:150px" class="easyui-datetimebox" />
	<span>至</span>
	<input id="createTime2"  data-options="showSeconds:true" style="width:150px" class="easyui-datetimebox" />
	<br/>
	<a href="#" class="easyui-linkbutton"  onclick="doSearch()">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="easyui-linkbutton"  onclick="doReset()">重置</a>
</div>
<table id="userGrid"></table>
<div id="add" style="display: none"></div>
<div id="update" style="display: none"></div>


</body>
</html>