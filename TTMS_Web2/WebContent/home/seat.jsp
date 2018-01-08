<%@page import="com.sun.xml.internal.bind.CycleRecoverable.Context"%>
<%@page import="com.zjsm.ctms.dao.SeatDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.zjsm.ctms.model.Seat"%>
<%@ page import="com.zjsm.ctms.model.Studio"%>
<%@ page import="java.util.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	int studio_id = (int) request.getAttribute("studio_id");
%>
<!DOCTYPE html>
<html>
<head>
<title>座位管理</title>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript">
	
		//绑定事件，将页面平滑到指定窗口(初始化加载页面)
		addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); 
		function hideURLbar(){ window.scrollTo(0,1); } 

</script>
<link rel="stylesheet" type="text/css" href="home/css/reset.css">
<link rel="stylesheet" type="text/css" href="home/css/fonts.css">
<link rel="stylesheet" type="text/css" href="home/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="home/css/home.css">
<link rel="stylesheet" type="text/css" href="home/css/window.css">

</head>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-static-top" role="navigation">
			<!-- header部分 -->
			<div class="navbar-header">
				<!-- 适配移动端时的显示 -->
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#clickbar">
					<span class="sr-only">切换导航</span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
				<!-- 标题 -->
				<a class="navbar-brand" href="home.jsp">这就是命影院票务管理系统</a>
			</div>
			<!-- 登录人员信息设置 -->
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle fli" data-toggle="dropdown">
			    		<img src="/TTMS_Web2/Image?method=image" alt="无法显示">
			    	</a>
			    	<ul class="dropdown-menu">
			    		<li class="ph">
			    			<a href="/TTMS_Web2/Image?method=search"><i class="icon-modify icon"></i>修改信息</a>
			    		</li>
			    		<li class="ph">
			    			<a href="/TTMS_Web2/login.jsp"><i class="icon-logout icon"></i>退出登录</a>
			    		</li>
			    	</ul>
				</li>
			</ul>
			<!-- 全局搜索 -->
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control globals" value="Search..."
					onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = 'Search...';}">
			</form>
			<!-- 侧边信息栏 -->
			<div class=" navbar-nav sidebar">
				<div class="collapse navbar-collapse" id="clickbar">
					<ul class="nav">
						  <%
    					HttpSession hs = request.getSession();
    					int type = (int) hs.getAttribute("type");
    					if(type == 1) {
    				%>
    					<li><a href="Movie?method=searchByPage"><i class="icon-movies icon"></i>影片管理</a></li>
                        <li class="active"><a href="Theatre?method=searchByPage"><i class="icon-theatre icon"></i>影厅管理</a></li>
                        <li><a href="home_root/people.jsp"><i class="icon-human icon"></i>成员管理</a></li>
                        <li><a href="Order?method=searchSaleByPage"><i class="icon-schedule icon"></i>订单管理</a></li>
                        <li><a href="home_root/finance.jsp"><i class="icon-finance icon"></i>财务报表</a></li>
                        <li><a href="home/inform.jsp"><i class="icon-notice icon"></i>公告</a></li>    
    				<%
    					} else if(type == 0) {
    						
    				%>
                        <li><a href="Movie?method=searchByPage"><i class="icon-movies icon"></i>影片管理</a></li>
                        <li class="active"><a href="Theatre?method=searchByPage"><i class="icon-theatre icon"></i>影厅管理</a></li>
                        <li><a href="Order?method=searchSaleByPage"><i class="icon-schedule icon"></i>订单管理</a></li>
                        <li><a href="home/inform.jsp"><i class="icon-notice icon"></i>公告</a></li>                        
					<%					
    					}
					%>

					</ul>
				</div>
			</div>
		</nav>
		<div id="page-wrapper" class="pa-wrp">
			<div class="grap">
				<div class="header">
					<div class="page-hd">座位管理</div>
				</div>
				<ol class="breadcrumb bg-head">		
					<li><a href="home.jsp">Home</a></li>
					<li><a href="Theatre?method=searchByPage">影厅管理</a></li>	   
				    <li><a href="Plan?method=searchById&studio_id=<%=studio_id%>">放映计划</a></li>
				    <li class="active">座位管理</li>
				</ol>
				<div class="row rowset">
					<div class="seat-wrap">
						<div class="screen">荧幕</div>
						<div class="row">
							<div class="col-md-2"></div>
							<div class="col-sm-offset-3 col-md-10">
								<form action="Place?method=searchByStudioId" method="post"
									accept-charset="utf-8">
									<div class="allseat" id="seatid">
										<%
											Studio info = (Studio) request.getAttribute("info");
											ArrayList<Seat> list = new SeatDAO().findSeatByStudioId(studio_id);
											int m = info.getStu_col_count(); //行数
											int n = info.getStu_row_count(); //列数
											if (info != null) {
												for (int i = 0; i < info.getStu_row_count(); i++) {
										%>
										<ol class="clearfix ol">
											<%
												for (int j = 0; j < info.getStu_col_count(); j++) {
															if (list.get(i * m + j % n).getSeat_status() == 1) {
											%>
											<li id="<%=list.get(i * m + j % n).getSeat_id()%>"><i
												class="icon-seat-s" onclick="seatstatus(this);"></i></li>
											<%
												} else if (list.get(i * m + j % n).getSeat_status() == 0) {
											%>
											<li id="<%=list.get(i * m + j % n).getSeat_id()%>"><i
												class="icon-seat-k" onclick="seatstatus(this);"></i></li>
											<%
												} else if (list.get(i * m + j % n).getSeat_status() == -1) {
											%>
											<li id="<%=list.get(i * m + j % n).getSeat_id()%>"><i
												class="icon-delete-little" onclick="seatstatus(this);"></i></li>
											<%
												}

														}
											%>
										</ol>
										<%
											}
											}
										%>

									</div>
									<div class="col-sm-offset-2 col-md-10">
										<ol class="clearfix ol">
											<li><i class="icon-seat-s">可用</i></li>
											<li><i class="icon-delete-little">损坏</i></li>
											<li><i class="icon-seat-k">空缺</i></li>
											<!--  	<li><input type="button" name="" value="保存" onclick="javascript:location.href='People?method=update'"></li> -->
											
										</ol>
									</div>
									<div class="col-sm-offset-3 col-md-10">
										<ol class="clearfix ol">
											<li><input type="button" name="" value="保存" onclick="status()"></li>
										</ol>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<script src="home/js/jquery-1.12.0.min.js"></script>
		<script src="home/js/seat.js"></script>
		<script src="home/js/bootstrap.min.js"></script>
		<script src="home/js/window.js" ></script>
		<script type="text/javascript">
			function seatstatus(obj) {
				switch (obj.className) { //改变当前焦点控件的图片
				case "icon-seat-s":
					obj.className = "icon-seat-k";
					break;
				case "icon-seat-k":
					obj.className = "icon-delete-little";
					break;
				case "icon-delete-little":
					obj.className = "icon-seat-s";
					break;
				}
			}

			function status() {
				var seat_used = document.getElementsByClassName("icon-seat-s");
				var seat_empty = document.getElementsByClassName("icon-seat-k");
				var seat_demage = document.getElementsByClassName("icon-delete-little");
				var idString = "";
				var statuString = "";
				var i;
				var seatid;
				var status;
				for (i = 0; i < seat_used.length-1; i++) { //1
					var iElement = seat_used[i];
					seatid = iElement.parentNode.getAttribute("id");
					status = 1;
					idString = idString + seatid + ",";
					statuString = statuString + status+",";
				}
				for (i = 0; i < seat_empty.length-1; i++) { //0
					var iElement = seat_empty[i];
					seatid = iElement.parentNode.getAttribute("id");
					status = 0;
					idString = idString + seatid + ",";
					statuString = statuString + status+",";
				}
				for (i = 0; i < seat_demage.length-1; i++) { //-1
					var iElement = seat_demage[i];
					seatid = iElement.parentNode.getAttribute("id");
					status = -1;
					idString = idString + seatid + ",";
					statuString = statuString + status+",";
				}
				//			console.log(obj);
				$.ajax({
					type : "post",
					url : "/TTMS_Web2/Place?method=update",
					data : {
						ids : idString,
						status : statuString
					},
					success : function(data, status) {
						//alert("Data: " + data + "nStatus: " + status);
						win.alert("系统提示","修改成功！");
					}
				})
			}
		</script>
</body>
</html>