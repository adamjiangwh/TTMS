<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.zjsm.ctms.model.Play" %>
<%@ page import="java.util.ArrayList" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html>
<html>
<head>
	<title>影片管理</title>
	<meta charset="utf-8">
	<base href="<%=basePath%>">
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
	<link rel="stylesheet" type="text/css" href="home/css/movies.css">
	

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
			    			<a href="login.jsp"><i class="icon-logout icon"></i>退出登录</a>
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
    					<li class="active"><a href="Movie?method=searchByPage"><i class="icon-movies icon"></i>影片管理</a></li>
                        <li><a href="Theatre?method=searchByPage"><i class="icon-theatre icon"></i>影厅管理</a></li>
                        <li><a href="home_root/people.jsp"><i class="icon-human icon"></i>成员管理</a></li>
                        <li><a href="Order?method=searchSaleByPage"><i class="icon-schedule icon"></i>订单管理</a></li>
                        <li><a href="home_root/finance.jsp"><i class="icon-finance icon"></i>财务报表</a></li>
                        <li><a href="home/inform.jsp"><i class="icon-notice icon"></i>公告</a></li>    
    				<%
    					} else if(type == 0) {
    						
    				%>
                        <li class="active"><a href="Movie?method=searchByPage"><i class="icon-movies icon"></i>影片管理</a></li>
                        <li><a href="Theatre?method=searchByPage"><i class="icon-theatre icon"></i>影厅管理</a></li>
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
					<div class="page-hd">
					    影片管理
					</div>
				</div>
				<ol class="breadcrumb bg-head">		
					<li><a href="home.jsp">Home</a></li>	   
				    <li class="active">影片管理</li>
				</ol>
 			<!-- 	<form action="" method="get" accept-charset="utf-8"> -->
					<div class="head-btn">
						<a href="home/addForm.jsp" class="wrapa"><button type="button" class="btn btn-primary btright" id="addmovie"><i class="icon-add icon"></i>添加影片</button></a>
<!--						<input type="button" name="" class="btn btn-primary btright" id="edit-operation" value="编辑">
						<div class="pull-right" id="hidde-btn" style="display: none;">
							<input type="button" name="" class="btn btn-primary btright" id="checkall" value="全选"> 
					 		<button type="button" class="btn btn-primary btright" id="del">删除</button> 
						</div>-->
					</div>
					
					
			<div class="row rowset" id="node">
				<%
				int currentPage=1;  //当前页
		   		int allCount=0;     //总记录数
		   		int allPageCount=0; //总页数
		   		Play play=null;
		   		//查看request中是否有currentPage信息，如没有，则说明首次访问该页
		   		if(request.getAttribute("allPlay")!=null)
		   		{
		   		    //获取Action返回的信息
		   		    currentPage=((Integer)request.getAttribute("currentPage")).intValue();
		   		    ArrayList<Play> list=(ArrayList<Play>)request.getAttribute("allPlay");
		   		    allCount=((Integer)request.getAttribute("allCount")).intValue();
		   		    allPageCount=((Integer)request.getAttribute("allPageCount")).intValue();
		   		if(list!=null && list.size()>0)
		   		{
			   		for(int i=0;i<list.size();i++)
			   		{
			   		  
		   		%>
						<div class="col-sm-6 col-md-2">
							<div class="thumbnail rela"> 
							<a href="Movie?method=searchById&play_id=<%=list.get(i).getPlay_id()%>"><img src="<%=list.get(i).getPlay_image()%>"alt="暂无海报"></a>
							<div class="caption">
							<h3 class="name"><%=list.get(i).getPlay_name()%></h3>
							</div>
							<div style="text-align:right;" >
							<span class="pointer" data-toggle="modal" data-target="#delcfmModal"  onclick="delcfm('Movie?method=delete&play_id=<%=list.get(i).getPlay_id()%>')">删除<i class="icon-delete-dustbin"></i>
							</span>
							</div>
							<label for="mbg3" class="checkbox-img">
							<input type="checkbox" style="display: none;" value="" id="mbg3" name="mymovie" disabled="disabled" >
							</label>
						</div>
						</div><!--/.col-->
					
				<%
		   			}
		   		}
		   		}
		   		%>
		   		</div>
		   			<!-- 分页 -->
		   		<div class="text-center">
			<nav>
			  <ul class="pagination">
			    <li><a href="Movie?method=searchByPage&currentPage=1&play_name=${search_play_name}">首页</a></li>
			    <li><a href="Movie?method=searchByPage&currentPage=<%=(currentPage-1)<1?1:(currentPage-1)%>&play_name=${search_play_name}">上一页</a></li>
			    <li><a href="Movie?method=searchByPage&currentPage=<%=(currentPage+1)>allPageCount?allPageCount:(currentPage+1)%>&play_name=${search_play_name}">下一页</a></li>
			    <li><a href="Movie?method=searchByPage&currentPage=<%=allPageCount%>&play_name=${search_play_name}">末页</a></li>
			  </ul>
			</nav>
		</div>   		   		   	
		</div>				
			<!--	</form>		-->			
					<!-- 信息删除确认 -->  
			<div class="modal fade" id="delcfmModel">  
			  <div class="modal-dialog">  
			    <div class="modal-content message_align">  
			      <div class="modal-header">  
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>  
			        <h4 class="modal-title">提示信息</h4>  
			      </div>  
			      <div class="modal-body">  
			        <p>您确认要删除吗？</p>  
			      </div>  
			      <div class="modal-footer">  
			         <input type="hidden" id="url"/>  
			         <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>  
			         <a  onclick="urlSubmit()" class="btn btn-success" data-dismiss="modal">确定</a>  
			      </div>  
			    </div>
			  </div> 
			</div>
			</div>
		</div>
		
		
	</div>

	<script src="home/js/jquery-1.12.0.min.js"></script>
	<script src="home/js/movies.js"></script>
	<script src="home/js/delcfm.js"></script>
	<script src="home/js/bootstrap.min.js" ></script>
	<script>
/*	$.ajax({
		         url: "/TTMS_Web2/Movie?method=show", 
		         dataType: "text",
		         type: "GET",
		         data: {}, 
		         success: function(result){ 
		            $('#node').html(result); 
		         }
		      });*/
	</script>
</body>
</html>