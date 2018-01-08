<%@page import="com.zjsm.ctms.dao.PlayDAO"%>
<%@page import="com.zjsm.ctms.dao.StudioDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.zjsm.ctms.model.Schedule" %>
<%@ page import="com.zjsm.ctms.model.Play" %>
<%@ page import="com.zjsm.ctms.model.Studio" %>
<%@ page import="com.zjsm.ctms.model.Seat" %>
<%@ page import="com.zjsm.ctms.dao.SeatDAO" %>
<%@ page import="java.util.ArrayList" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
int studio_id = Integer.parseInt(request.getParameter("studio_id"));
%>
<!DOCTYPE html>
<html>
<head>
	<title>放映计划</title>
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
	<link rel="stylesheet" type="text/css" href="home/js/dataTables/dataTables.bootstrap.css">

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
					 放映计划
					</div>
				</div>
				<ol class="breadcrumb bg-head">		
					<li><a href="home.jsp">Home</a></li>
					<li><a href="Theatre?method=searchByPage">影厅管理</a></li>	   
				    <li class="active">放映计划</li>
				</ol>
				<div class="x_content">  
					<div class="click-btn clearfix">
						<a href="#addplan" data-toggle="modal" class="pull-left"><span class="spantxt">添加计划</span></a>
						<a href="#" class="pull-left mleft"><span  class="spantxt">批量删除</span></a>
						<%
							int count = new SeatDAO().findStudioId(studio_id);	//studio_id在seat表中的行数
							if(count == 0) {	//判断座位是否已添加
						%>
							<a onclick="javascript:win.alert('系统提示','尚未安排座位！')" class="pull-right"><span class="spantxt">座位管理</span></a>
						<%
							} else {
						%>
							<a href="Place?method=searchByStudioId&studio_id=<%=studio_id%>" class="pull-right"><span class="spantxt">座位管理</span></a>
						<%		
							}
						%>
						
					</div>       
					<input type="hidden" name="method" value="Plan?method=searchById"/>
	                <div class="table-responsive">
	                  <table class="table table-striped jambo_table bulk_action" id="dataTables-example">
	                    <thead>
	                      <tr class="headings">
	                        <th>
	                          <input type="checkbox" id="check-all" class="flat">
	                          <label for="check-all"></label>
	                        </th>
	                        <th class="column-title">影厅名称 </th>
	                        <th class="column-title">片名</th>
	                        <th class="column-title">放映时间</th>
	                        <th class="column-title">语言</th>
	                        <th class="column-title">类型</th>
	                        <th class="column-title">片长</th>
	                        <th class="column-title">价格</th>
	                        <th class="column-title no-link last"><span class="nobr">操作</span></th>		     
	                      </tr>
	                    </thead>

	                    <tbody>
	                    <%
				   		ArrayList<Schedule> list=(ArrayList<Schedule>)request.getAttribute("list");
	                    Studio studio = new StudioDAO().findById(studio_id);
	                    String studio_name = studio.getStu_name();
				   		if(list!=null && list.size()>0)
				   		{
				   			int index=0;
					   		for(int i=0;i<list.size();i++)
					   		{
					   		    int play_id = list.get(i).getPlay_id();
			                    Play list_play = new PlayDAO().findById(play_id);
			                    
					   		    if(i%2==0)
					   		        out.println("<tr class='even pointer'>");
					   		    else
					   		    	out.println("<tr class='odd pointer'>");
				   		%>
				   			<td class="a-center ">
	                          <input type="checkbox" class="flat" name="table_records" id="table_records<%=index%>">
	                          <label for="table_records<%=index%>"></label>
	                        </td>
				   			<td class=" "><%=studio_name%></td>
				   			<td class=" ">《<%=list_play.getPlay_name()%>》</td>
				   			<td class=" "><%=list.get(i).getSched_time()%></td>
				   			<td class=" "><%=list_play.getPlay_lang_id()%></td>
				   			<td class=" "><%=list_play.getPlay_type_id()%></td>
				   			<td class=" "><%=list_play.getPlay_length()%>分钟</td>
				   			<td class="a-right a-right ">￥<%=list_play.getPlay_ticket_price()%></td>
				   			<td class=" last">
                                <a href="#editModal" data-toggle="modal"><i class="icon-edit-pencil icon"></i></a>
                                <a class="pointer" onclick="delcfm('')"><i class="icon-delete-dustbin"></i></a>
	                        </td>
				   		</tr>
				   		<%
				   			index++;
				   			}
				   		}
				   		%>

	                    </tbody>
	                  </table>
	                </div>					
	            </div>								
			</div>

			<!-- 模态框 -->
			<div class="modal fade" id="addplan" tabindex="-1" role="dialog" aria-labelledby="addplanModalLabel" aria-hidden="true">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                <h4 class="modal-title" id="addplanModalLabel">演出计划信息添加</h4>
			            </div>
			            <div class="modal-body">
			            	<form action="" class="form-horizontal" id="addplanform" role="form">
			            	  <div class="form-group">
			            	    <label for="aplanmname" class="col-sm-2 control-label">片名:</label>
			            	    <div class="col-sm-10">
			            	      <input type="text" class="form-control" id="aplanmname" name="moviename" placeholder="请输入片名">
			            	    </div>
			            	  </div>
			            	  <div class="form-group">
			            	    <label for="aplandata" class="col-sm-2 control-label">放映日期:</label>
			            	    <div class="col-sm-10">
			            	      <input type="text" class="form-control" id="aplandata" name="playdata" onblur="dateRex(this,0)" placeholder="请输入放映日期(格式:2017-01-01)">
			            	      <p id="hintdate" style="color: #ff0000;"></p>
			            	    </div>
			            	  </div>
			            	  <div class="form-group">
			            	    <label for="aplanplaytime" class="col-sm-2 control-label">放映时间:</label>
			            	    <div class="col-sm-10">
			            	      <select>
			            	      	<option value="">放映时间</option>
			            	      </select>
			            	    </div>
			            	  </div>
			            	  <div class="form-group">
			            	    <label for="aplanalltime" class="col-sm-2 control-label">片长:</label>
			            	    <div class="col-sm-10">
			            	      <input type="text" class="form-control" id="aplanalltime" name="alltime" placeholder="请输入片长">
			            	    </div>
			            	  </div>
			            	  <div class="form-group">
			            	    <label for="aplanprice" class="col-sm-2 control-label">价格:</label>
			            	    <div class="col-sm-10">
			            	      <input type="text" class="form-control" id="aplanprice" name="price" placeholder="请输入价格">
			            	    </div>
			            	  </div>       	  
			            	</form>
			            </div>
			            <div class="modal-footer">
			                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			                <button type="submit" id="submitplan" class="btn btn-primary">提交更改</button>
			            </div>
			        </div>
			    </div>
			</div>

			<!-- 模态框 -->
			<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                <h4 class="modal-title" id="editModalLabel">演出计划信息修改</h4>
			            </div>
			            <div class="modal-body">
			            	<form action="" class="form-horizontal" id="editform" role="form">
			            	  <div class="form-group">
			            	    <label for="editmname" class="col-sm-2 control-label">片名:</label>
			            	    <div class="col-sm-10">
			            	      <input type="text" class="form-control" id="editmname" name="moviename" placeholder="请输入片名">
			            	    </div>
			            	  </div>
			            	  <div class="form-group">
			            	    <label for="editdata" class="col-sm-2 control-label">放映日期:</label>
			            	    <div class="col-sm-10">
			            	      <input type="text" class="form-control" id="editdata" name="playdata" onblur="dateRex(this)" placeholder="请输入放映日期">
			            	      <p id="hintde" style="color: #ff0000;"></p>
			            	    </div>
			            	  </div>
			            	  <div class="form-group">
			            	    <label for="editplaytime" class="col-sm-2 control-label">放映时间:</label>
			            	    <div class="col-sm-10">
			            	      <select>
			            	      	<option value="">放映时间</option>
			            	      </select>
			            	    </div>
			            	  </div>
			            	  <div class="form-group">
			            	    <label for="editalltime" class="col-sm-2 control-label">片长:</label>
			            	    <div class="col-sm-10">
			            	      <input type="text" class="form-control" id="editalltime" name="alltime" placeholder="请输入片长">
			            	    </div>
			            	  </div>
			            	  <div class="form-group">
			            	    <label for="editprice" class="col-sm-2 control-label">价格:</label>
			            	    <div class="col-sm-10">
			            	      <input type="text" class="form-control" id="editprice" name="price" placeholder="请输入价格">
			            	    </div>
			            	  </div>       	  
			            	</form>
			            </div>
			            <div class="modal-footer">
			                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			                <button type="submit" id="submitedit" class="btn btn-primary">提交更改</button>
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

	<script src="home/js/jquery-1.12.0.min.js"></script>
	<script src="home/js/delcfm.js"></script>
	<script src="home/js/dateRegexp.js"></script>
	<script src="home/js/theatreplan.js"></script>
	<script src="home/js/bootstrap.min.js" ></script>
	<script src="home/js/window.js" ></script>
	<script src="home/js/dataTables/jquery.dataTables.js"></script>
	<script src="home/js/dataTables/dataTables.bootstrap.js"></script>
	<script type="text/javascript">
		$(document).ready(function () {
	        $('#dataTables-example').dataTable();
		});
	</script>

</body>
</html>