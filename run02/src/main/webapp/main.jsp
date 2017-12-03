<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global/global.jsp" %>
<html>
<head>
<title>首页</title>
<style>
body {
	font: 12px/20px "微软雅黑", "宋体", Arial, sans-serif, Verdana, Tahoma;
	padding: 0;
	margin: 0;
}

a:link {
	text-decoration: none;
}

a:visited {
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

a:active {
	text-decoration: none;
}

.cs-north {
	height: 60px;
	background: #B3DFDA;
}

.cs-north-bg {
	width: 100%;
	height: 100%;
	background: url(themes/gray/images/header_bg.png) repeat-x;
}

.cs-north-logo {
	height: 40px;
	padding: 15px 0px 0px 5px;
	color: #fff;
	font-size: 22px;
	font-weight: bold;
	text-decoration: none
}

.cs-west {
	width: 200px;
	padding: 0px;
	border-left: 1px solid #99BBE8;
}

.cs-south {
	height: 25px;
	background: url('themes/gray/images/panel_title.gif') repeat-x;
	padding: 0px;
	text-align: center;
}

.cs-navi-tab {
	padding: 5px;
}

.cs-tab-menu {
	width: 120px;
}

.cs-home-remark {
	padding-top:200px;
	padding-left: 400px;
}
</style>
<script type="text/javascript">
function createFrame(url) {//创建iframe
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}

function addTab(title, url){
	if ($('#tabs').tabs('exists', title)){
		$('#tabs').tabs('select', title);//选中并刷新
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		if(url != undefined && currTab.panel('options').title != 'Home') {
			$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url)
				}
			})
		}
	} else {
		var content = createFrame(url);
		$('#tabs').tabs('add',{
			title:title,
			content:content,
			closable:true
		});
	}
	tabClose();
}

function tabClose() {/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})

	$(".tabs-inner").bind('contextmenu',function(e){	/*为选项卡绑定右键*/
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}	

function tabCloseEven() {//绑定右键菜单事件
	$('#mm-tabupdate').click(function(){//刷新
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		if(url != undefined && currTab.panel('options').title != 'Home') {
			$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url)
				}
			})
		}
	})
	
	$('#mm-tabclose').click(function(){//关闭当前
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	
	$('#mm-tabcloseall').click(function(){//全部关闭
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t != 'Home') {
				$('#tabs').tabs('close',t);
			}
		});
	});
	
	$('#mm-tabcloseother').click(function(){//关闭除当前之外的TAB
		var prevall = $('.tabs-selected').prevAll();
		var nextall = $('.tabs-selected').nextAll();		
		if(prevall.length>0){
			prevall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != 'Home') {
					$('#tabs').tabs('close',t);
				}
			});
		}
		if(nextall.length>0) {
			nextall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != 'Home') {
					$('#tabs').tabs('close',t);
				}
			});
		}
		return false;
	});

	$('#mm-tabcloseright').click(function(){	//关闭当前右侧的TAB
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	
	$('#mm-tabcloseleft').click(function(){//关闭当前左侧的TAB
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}

$(function() {
	tabCloseEven();
	$('.cs-navi-tab').click(function() {
		var $this = $(this);
		var href = $this.attr('src');
		var title = $this.text();
		addTab(title, href);
	});
});
</script>
</head>
<body>
	<!-- easyui-layout 布局属性  fit：true 高度自适应 -->
	<div class="easyui-layout" data-options="fit:true" style="width:100%;">
		<!--上 -->
		<div data-options="region:'north'" style="height: 80px;background: grey;">
         	<div style="text-align: center;width: 100%;height: 60px;font-size:28px;padding-top: 18px;" >Jquery EasyUI DEMO</div>
		</div>
		<!--下-->
		<div data-options="region:'south',split:true" style="color:blue;font-size:25px;height: 50px;text-align:center;padding-top:10px;">用心去生活</div>
		<!--右 -->
		<!-- <div data-options="region:'east',split:false" title="排行榜"
			style="width: 150px;"></div> -->
		<!--左 -->
		<div  region="west" border="true" split="true" title="导航" class="cs-west">
			<div class="easyui-accordion" fit="true" border="false">
				<div title="系统配置">
					<a href="javascript:void(0);" src="${ctx}/admin/dicmodel/diclist.jsp" class="cs-navi-tab">业务字典</a><br>
					<a href="javascript:void(0);" src="${ctx}/admin/usermodel/userlist.jsp"  class="cs-navi-tab">用户管理</a><br> 
				<%-- 	<a href="javascript:void(0);" src=""  class="cs-navi-tab">角色管理</a><br> 
					<a href="javascript:void(0);" src="${ctx}/positionmodel/demo2.jsp"  class="cs-navi-tab">岗位管理</a><br>
				    <a href="javascript:void(0);" src="${ctx}/menumodel/menulist.jsp" class="cs-navi-tab">菜单管理</a><br>
					<a href="javascript:void(0);" src="${ctx}/picmodel/addpic.jsp" class="cs-navi-tab">图片管理</a><br> --%>
				</div>
			</div>
			</div>
			<!--中 -->
			<div data-options="region:'center'">
			 		<div id="tabs" class="easyui-tabs"  fit="true" border="false" >
	               	    <div title="首页">
							<div class="cs-home-remark" >
								<div style="font-size: 40px;color:#F28770">WELCOME>>>>>></div>
						    </div>
				        </div>
        		    </div>
			</div>
		</div>
		<!-- <div id="mm" class="easyui-menu cs-tab-menu">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseother">关闭其他</div>
		<div id="mm-tabcloseall">关闭全部</div>
	 </div> -->
</body>
</html>