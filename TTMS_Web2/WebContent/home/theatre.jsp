<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.zjsm.ctms.model.Studio" %>
<%@ page import="java.util.ArrayList" %>
<%

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html>
<html>
<head>
	<title>影厅管理</title>
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
					<div class="page-hd">
					    影厅管理
					</div>
				</div>
				<ol class="breadcrumb bg-head">		
					<li><a href="home.jsp">Home</a></li>	   
				    <li class="active">影厅管理</li>
				</ol>
				<div class="x_content">
            <div class="add-people">
              <a href="home/addTheatre.jsp" class="wrapa"><button type="button" class="btn btn-primary btright" id="addtheatre"><i class="icon-add icon"></i>添加影厅</button></a>
            </div>	          
          	<div class="row" id="node">

          			<%
				int currentPage=1;  //当前页
		   		int allCount=0;     //总记录数
		   		int allPageCount=0; //总页数
		   		Studio studio=null;
		   		//查看request中是否有currentPage信息，如没有，则说明首次访问该页
		   		if(request.getAttribute("allStudio")!=null)
		   		{
		   		    //获取Action返回的信息
		   		    currentPage=((Integer)request.getAttribute("currentPage")).intValue();
		   		    ArrayList<Studio> list=(ArrayList<Studio>)request.getAttribute("allStudio");
		   		    allCount=((Integer)request.getAttribute("allCount")).intValue();
		   		    allPageCount=((Integer)request.getAttribute("allPageCount")).intValue();
		   		if(list!=null && list.size()>0)
		   		{
			   		for(int i=0;i<list.size();i++)
			   		{
			   			if(list.get(i).getStu_flag()==-1)	//影厅不可用
			   		        out.println("<div class=\"col-sm-6 col-md-3\"><div class=\"thumbnail\"><a onclick=\"javascript:win.alert('系统提示','影厅不可用！')\">");
			   		    else
			   		    	out.println("<div class=\"col-sm-6 col-md-3\"><div class=\"thumbnail\"><a href=\"Plan?method=searchById&studio_id="+list.get(i).getStu_id()+"\">");
		   		%>   		
		   		<img src="<%=list.get(i).getStu_path()%>" alt="studioImage"></a><div class="caption stdbg clearfix">   
             	<h3 class="stdname"><%=list.get(i).getStu_name()%></h3><div class="operaicon">
             	<span class="pointer"><a href="Theatre?method=searchById&studio_id=<%=list.get(i).getStu_id()%>"><i class="icon-edit-pencil"></i></a></span>
             	<span class="pointer" data-toggle="modal" data-target="#delcfmModal"  onclick="delcfm('Theatre?method=delete&studio_id=<%=list.get(i).getStu_id()%>')">
             	<i class="icon-delete-dustbin"></i></span>
             	</div></div>
             	</label></a></div></div>
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
			    <li><a href="Theatre?method=searchByPage&currentPage=1&studio_name=${search_stu_id}">首页</a></li>
			    <li><a href="Theatre?method=searchByPage&currentPage=<%=(currentPage-1)<1?1:(currentPage-1)%>&studio_name=${search_stu_id}">上一页</a></li>
			    <li><a href="Theatre?method=searchByPage&currentPage=<%=(currentPage+1)>allPageCount?allPageCount:(currentPage+1)%>&studio_name=${search_stu_id}">下一页</a></li>
			    <li><a href="Theatre?method=searchByPage&currentPage=<%=allPageCount%>&studio_name=${search_stu_id}">末页</a></li>
			  </ul>
			</nav>
		</div>	

            <!-- 模态框（Modal） -->
            <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="addModalLabel">影厅信息添加</h4>
                        </div>
                        <div class="modal-body">
                          <form action="" class="form-horizontal" id="addform" role="form">
                            <div class="form-group">
                              <label for="theatretype" class="col-sm-2 control-label">影厅类型:</label>
                              <div class="col-sm-10">
                                <input type="text" class="form-control" id="theatretype" name="ttype" placeholder="请输入影厅类型">
                              </div>
                            </div>
                            <div class="form-group">
                              <label for="seattype" class="col-sm-2 control-label">座位类型:</label>
                              <div class="col-sm-10">
                                <select name="addsele">
                                  <option value="">请选择座位类型</option>
                                  <option value="">小型(6*8)</option>
                                  <option value="">中型(9*10)</option>
                                  <option value="">大型(10*12)</option>
                                </select>
                              </div>
                            </div>
                            <div class="form-group">
                              <label for="description" class="col-sm-2 control-label">描述:</label>
                              <div class="col-sm-10">
                                <input type="text" class="form-control" id="description" name="desc" placeholder="请输入描述">
                              </div>
                            </div>             
                          </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="submit" id="submitadd" class="btn btn-primary">添加</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 模态框（Modal） -->
            <div class="modal fade" id="modifyModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="addModalLabel">影厅信息修改</h4>
                        </div>
                        <div class="modal-body">
                          <form action="" class="form-horizontal" id="addform" role="form">
                            <div class="form-group">
                              <label for="theatype" class="col-sm-2 control-label">影厅类型:</label>
                              <div class="col-sm-10">
                                <input type="text" class="form-control" id="theatype" name="typem" placeholder="请输入影厅类型">
                              </div>
                            </div>
                            <div class="form-group">
                              <label for="seate" class="col-sm-2 control-label">座位类型:</label>
                              <div class="col-sm-10">
                                <select name="mdysele">
                                  <option value="">请选择座位类型</option>
                                  <option value="">小型(6*8)</option>
                                  <option value="">中型(9*10)</option>
                                  <option value="">大型(10*12)</option>
                                </select>
                              </div>
                            </div>
                            <div class="form-group">
                              <label for="tdescription" class="col-sm-2 control-label">描述:</label>
                              <div class="col-sm-10">
                                <input type="text" class="form-control" id="tdescription" name="descr" placeholder="请输入描述">
                              </div>
                            </div>             
                          </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="submit" id="submitadd" class="btn btn-primary">修改</button>
                        </div>
                    </div>
                </div>
            </div>


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
	</div>

	<script src="home/js/jquery-1.12.0.min.js"></script>
  <script src="home/js/delcfm.js"></script>
	<script src="home/js/bootstrap.min.js" ></script>
	<script src="home/js/window.js" ></script>
	<script>
	/* $.ajax({
		         url: "/TTMS_Web2/Theatre?method=show", 
		         dataType: "text",
		         type: "GET",
		         data: {}, 
		         success: function(result){ 
		            $('#node').html(result); 
		         }
		      }); */
	</script>
	
</body>
</html>