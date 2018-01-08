<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.zjsm.ctms.model.Sale" %>
<%@ page import="com.zjsm.ctms.model.SaleItem" %>
<%@ page import="java.util.ArrayList" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html>
<html>
<head>
	<title>订单表</title>
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
	<link rel="stylesheet" type="text/css" href="home/js/page/jquery.page.css">


</head>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-static-top" role="navigation">
			<!-- header部分 -->
		    <div class="navbar-header">
		    	<!-- 适配移动端时的显示 -->
		    	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#clickbar">
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
              <input type="text" class="form-control globals" value="Search..." onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search...';}">
            </form> 
    		<!-- 侧边信息栏 -->
            <div class=" navbar-nav sidebar">
                <div class="collapse navbar-collapse"  id="clickbar">
    				<ul class="nav">
                       <%
    					HttpSession hs = request.getSession();
    					int type = (int) hs.getAttribute("type");
    					if(type == 1) {
    				%>
    					<li><a href="Movie?method=searchByPage"><i class="icon-movies icon"></i>影片管理</a></li>
                        <li><a href="Theatre?method=searchByPage"><i class="icon-theatre icon"></i>影厅管理</a></li>
                        <li><a href="home_root/people.jsp"><i class="icon-human icon"></i>成员管理</a></li>
                        <li class="active"><a href="Order?method=searchSaleByPage"><i class="icon-schedule icon"></i>订单管理</a></li>
                        <li><a href="home_root/finance.jsp"><i class="icon-finance icon"></i>财务报表</a></li>
                        <li><a href="home/inform.jsp"><i class="icon-notice icon"></i>公告</a></li>    
    				<%
    					} else if(type == 0) {
    						
    				%>
                        <li><a href="Movie?method=searchByPage"><i class="icon-movies icon"></i>影片管理</a></li>
                        <li><a href="Theatre?method=searchByPage"><i class="icon-theatre icon"></i>影厅管理</a></li>
                        <li class="active"><a href="Order?method=searchSaleByPage"><i class="icon-schedule icon"></i>订单管理</a></li>
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
					<div class="page-hd">
					    订单管理
					</div>
				</div>
				
				<ol class="breadcrumb bg-head">		
					<li><a href="home.jsp">Home</a></li>	   
				    <li class="active">订单信息</li>
				</ol>
				<div class="panel panel-default">
	             <div class="panel-heading">
	                     		        订单表
				</div>
				<div><br></div>
				<div class="row rowset" id="rowset">
				<%
				int currentPage=1;  //当前页
		   		int allCount=0;     //总记录数
		   		int allPageCount=0; //总页数
		   		Sale sale=null;
		   		//查看request中是否有currentPage信息，如没有，则说明首次访问该页
		   		if(request.getAttribute("allSale")!=null)
		   		{
		   		    //获取Action返回的信息
		   		    currentPage=((Integer)request.getAttribute("currentPage")).intValue();
		   		    ArrayList<Sale> list=(ArrayList<Sale>)request.getAttribute("allSale");
		   		    allCount=((Integer)request.getAttribute("allCount")).intValue();
		   		    allPageCount=((Integer)request.getAttribute("allPageCount")).intValue();
		   		if(list!=null && list.size()>0)
		   		{
			   		for(int i=0;i<list.size();i++)
			   		{
			   		    if(i%5==0)
			   		        out.println("<div class=\"col-md-4\"><div class=\"panel panel-blue\">");
			   		    else if(i%5==1)
			   		    	out.println("<div class=\"col-md-4\"><div class=\"panel panel-teal\">");
			   		    else if(i%5==2)
			   		    	out.println("<div class=\"col-md-4\"><div class=\"panel panel-orange\">");
			   		    else if(i%5==3)
			   		    	out.println("<div class=\"col-md-4\"><div class=\"panel panel-red\">");
			   		    else if(i%5==4)
			   		    	out.println("<div class=\"col-md-4\"><div class=\"panel panel-green\">");
			   		    else if(i%5==5)
			   		    	out.println("<div class=\"col-md-4\"><div class=\"panel panel-yell\">");
			   		    	%>
			   		    	
			   		    <div class="panel-heading dark-overlay"><a href = "Order?method=searchByPage&sale_ID=<%=list.get(i).getSale_ID() %>" style="color:#FFFFFF">订单编号：<%=list.get(i).getSale_ID() %></a></div>
			   		    <div class="panel-body">
								<p>
								员工编号：<%=list.get(i).getEmp_id() %><br>
								订单时间：<%=list.get(i).getSale_time()%><br>
								付款金额：<%=list.get(i).getSale_payment() %><br>
								找零金额：<%=list.get(i).getSale_change() %><br>
								订单类型：<%=list.get(i).getSale_type() %><br>
								订单状态：<%=list.get(i).getSale_status()%><br>
								</p>
							
						</div>
				</div>
				</div>
					<%
		   			}
		   		}
		   		}
		   		%>
			</div>
				<!-- 分页 -->
				</div>
		<div class="text-center">
			<nav>
			  <ul class="pagination">
			    <li><a href="Order?method=searchSaleByPage&currentPage=1&sale_ID=${search_sale_id}">首页</a></li>
			    <li><a href="Order?method=searchSaleByPage&currentPage=<%=(currentPage-1)<1?1:(currentPage-1)%>&sale_ID=${search_sale_id}">上一页</a></li>
			    <li><a href="Order?method=searchSaleByPage&currentPage=<%=(currentPage+1)>allPageCount?allPageCount:(currentPage+1)%>&sale_ID=${search_sale_id}">下一页</a></li>
			    <li><a href="Order?method=searchSaleByPage&currentPage=<%=allPageCount%>&sale_ID=${search_sale_id}">末页</a></li>
			  </ul>
			</nav>
		</div>
			</div>
		</div>
	</div>

	<script src="home/js/jquery-1.12.0.min.js"></script>
	<script src="home/js/bootstrap.min.js" ></script>
	<script type="text/javascript" src="home/js/page/jquery.min.js"></script>
	<script type="text/javascript" src="home/js/page/jquery.page2.0.js"></script>

	
	<script>
		/*加载dataTable插件*/		
/*		$(document).ready(function () {
            $('#rowset').dataTable();
			$.get("/TTMS_Web2/Order",function(data,status){
				$("#ajax").append(data);
		});
	});*/

/*  	$.ajax({
	         url: "/TTMS_Web2/Order?method=show", 
	         dataType: "text",
	         type: "GET",
	         data: {}, 
	         success: function(result){ 
	            $('#rowset').html(result); 
	         }
	      });
		  */
		
/*		$(function(){
			$.ajax({
				url:"/TTMS_Web2/Order",
				type:"post",
				success: function(data){
				var obj=JSON.parse(data);
				$(obj).each(function (index){
				var val=obj[index];
				console.log(val);
				var div=$('<div class="col-md-4"></div>');
				div.append('<div class="panel panel-blue"><div class="panel-heading dark-overlay">'
						+ val.sale_ID + '</div><div class="panel-body"><p>' 
						+ val.emp_id + val.time + val.payment + val.change
						+ val.type + val.status + '</p></div>'
						+ '</div><!--/.col-->')
				div.append(tr);
				});
				$('#rowset').replaceWith(div);
				}
			});
		});*/
    </script>
	
</body>
</html>