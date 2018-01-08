<%@page import="com.zjsm.servlet.GetParamServlet"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


%>
<!DOCTYPE html>
<html>
<head>
	<title>影厅信息</title>
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
	<link rel="stylesheet" type="text/css" href="home/css/movies.css">
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
					<li><a href="Theatre?method=searchByPage">影厅管理</a></li>	   
				    <li class="active">影厅信息</li>
				</ol>
				<div class="updamov">
					<form action="Theatre?method=update" method="post" accept-charset="utf-8" role="form" class="from-horizontal" id="sgtheatre" name="myform">
						<div class="row">
							<div class="col-md-6">
								 <div class="form-group">
							  	<input type="hidden" id="studio_id" name="studio_id" value="${studio_id}">		  	
							      
							      <label for="ytname" class="col-sm-3 control-label" >影厅名称:</label>							     
							      <div class="col-sm-9">
							        <input type="text" class="form-control inpbg" id="ytname" name="ytname" 
							        oninvalid="setCustomValidity('请输入影厅名称')" 
							        oninput="setCustomValidity('')" value="${studio_name}"  pattern=".{2,10}"
							        required="required" oninvalid="setCustomValidity('请输入正确影厅名称,2~10个字符!')"
								  oninput="setCustomValidity('')">
							      </div>
							    </div>
							  <div class="form-group">	  	
							      <label for="ytrowcount" class="col-sm-3 control-label" >影厅行数:</label>							     
							      <div class="col-sm-9">
							        <input type="text" class="form-control inpbg" id="ytrowcount" name="ytrowcount" 
							        oninvalid="setCustomValidity('请输入影厅行数')" 
							        oninput="setCustomValidity('')" value="${studio_row_count}" readonly="readonly"
							         pattern="[0-9]{1,2}"
							         required="required" oninvalid="setCustomValidity('请输入正确座位行数!')"
								  oninput="setCustomValidity('')">
							      </div>
							    </div>
							    <div class="form-group">
							      <label for="ytcolcount" class="col-sm-3 control-label">影厅列数:</label>
							      <div class="col-sm-9">
							        <input type="text"  class="form-control inpbg" id="ytcolcount" name="ytcolcount" 
									oninvalid="setCustomValidity('请输入影厅列数')" 
							        oninput="setCustomValidity('')" value="${studio_col_count}" readonly="readonly"
							         pattern="[0-9]{1,2}"
							         required="required" oninvalid="setCustomValidity('请输入正确座位列数!')"
								  oninput="setCustomValidity('')">
							      </div>
							    </div>
							    
							    <div class="form-group">
							      <label for="ytinflag" class="col-sm-3 control-label">影厅状态:</label>
							      <div class="col-sm-9">
							        <!-- 
							        <input type="text"  class="form-control inpbg" id="ytinflag" name="ytinflag" 
							         value="${studio_flag}" readonly="readonly">
							          -->
							          <select class="form-control inpbg" id="ytinflag" name="ytinflag">
							          	<option value="已安排座位" ${studio_flag == "已安排座位"?'selected':''}>已安排座位</option>
							          	<option value="尚未生成座位" ${studio_flag == "尚未生成座位"?'selected':''}>尚未生成座位</option>
							          	<option value="影厅不可用" ${studio_flag == "影厅不可用"?'selected':''}>影厅不可用</option>
							          </select>
							      </div>
							    </div>
							    
							    <div class="form-group">
							      <label for="ytintroduction" class="col-sm-3 control-label">影厅简介:</label>
							      <div class="col-sm-9">
							      <!-- 
							        <input type="text"  class="form-control inpbg" id="ytintroduction" name="ytintroduction" 
									oninvalid="setCustomValidity('请输入影厅简介')" 
							        oninput="setCustomValidity('')" value="${studio_introduction}">
							         -->
							         <textarea class="form-control inpbg" id="ytintroduction" name="ytintroduction"
							         	 style="width:520px;height:150px;">${studio_introduction}</textarea>
							      </div>
							    </div>
							</div>	
							
							<div class="col-md-6 lmarg">
								<div class="form-group" style="display: none;" id="filesub">
								    <label for="ytimgfile">请选择要上传的电影海报(尺从大小最好是400*300)</label>
								    <input type="file" id="ytimgfile" name="ytimgfile" value="">    
								</div>
								<div class="imgdivstu">
									<img src="${studio_path}" alt="尚未添加图片" id="timgf" name="timgf">				
								</div>				
							</div>
						
						</div>
						<div class="row foot-btn">
							<div class="col-sm-12">
								<div class="form-group">								      
								    <button type="button" class="btn btn-default modifybtn" id="modifymess">修改信息</button>
								    <button type="submit" class="btn btn-primary" style="display: none;" id="submess">提交</button> 
								    <a href="Theatre?method=searchByPage"><button type="button" class="btn btn-default modifybtn" id="back">返回</button></a>
								</div>
							</div>
						</div>	
						<span id="updatethea" style="display:none;">${result}</span>
					</form>
				</div>									
			</div>
		</div>
	</div>
 	<script src="home/js/jquery-1.12.0.min.js"></script> 
 	<script src="home/js/mdfymov.js"></script>
	<script src="home/js/bootstrap.min.js" ></script> 
 	<script src="home/js/window.js" ></script>
 	<script type="text/javascript"> 
	//操作成功
	if(document.getElementById('updatethea').innerHTML!=""){
		   win.alert("系统提示",document.getElementById('updatethea').innerHTML);
	   }
	
	//判断图片类型
	function isPic()
	{
	    var pic=myform.ytimgfile.value;
	    var ext=pic.substring(pic.lastIndexOf(".")+1);
	    //可以再添加允许类型
	    if(ext.toLowerCase()=="jpg" || ext.toLowerCase()=="png" || ext.toLowerCase()=="gif" || pic==""){
	        return true;
	    }
	    else
	    {
	    	win.alert("图片类型错误","只能上传jpg、png、gif图像!");
	    	myform.ytimgfile.value="";
	        return false;
	    }
	}
	
	
	// 图片响应
	$("input[type=file]").change(function(e){
		if(isPic()){
			var fileName="";
			if(typeof(fileName) != "undefined") {			
				fileName = $(this).val().split("\\").pop();  
		        fileName=fileName.substring(0, fileName.length);
			}
			fileName = "home/img/studio/" + fileName;
			$("#timgf")[0].src=fileName;
		} 
	})
 	</script>
 </body>
 </html>