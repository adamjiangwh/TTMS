<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<title>添加影片</title>
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
				    <li><a href="Movie?method=searchByPage">影片列表</a></li>
				    <li class="active">影片添加</li>
				</ol>
				<div class="form-wrap">
					<form action="Movie?method=add" method="post" accept-charset="utf-8" role="form" class="from-horizontal" id="addmovie" name="myform">
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group">
								    <label for="addname">影片名称</label>
								    <input type="text" class="form-control inpbg" id="mname" name="mname" 
							        placeholder="请输入影片名称"  pattern=".{2,20}"
							        required="required" oninvalid="setCustomValidity('请输入正确影片名称,20字以内!')"
								  oninput="setCustomValidity('')">    
								</div>
								<div class="form-group">
								    <label for="addtime">影片时长</label>
								    <input type="text"  class="form-control inpbg" id="mtime" name="mtime" 
									placeholder="请输入影片时长"  pattern="[0-9]{1,3}"
									required="required" oninvalid="setCustomValidity('请输入正确影片时长!')"
								  oninput="setCustomValidity('')">    
								</div>
								<div class="form-group">
								    <label for="addtypeid">影片类型</label>
								    <input type="text"  class="form-control inpbg" id="mtypeid" name="mtypeid" 
									placeholder="请输入影片类型"  pattern="[0-9]{1,2}"
									required="required" oninvalid="setCustomValidity('请输入正确影片类型!')"
								  oninput="setCustomValidity('')">   
								</div>
												
								<div class="form-group">
								    <label for="addlang">影片语言</label>
								    <input type="text"  class="form-control inpbg" id="mlang" name="mlang"  
									placeholder="请输入影片语言"  pattern="[0-9]{1,2}"
									required="required" oninvalid="setCustomValidity('请输入正确影片语言!')"
								  oninput="setCustomValidity('')">
								</div>
								<div class="form-group">
								    <label for="addprice">影片价格</label>
								    <input type="text"  class="form-control inpbg" id="mprice" name="mprice" 
									placeholder="请输入影片价格"  pattern="[0-9]+([.]{1}[0-9]+){0,1}"
									required="required" oninvalid="setCustomValidity('请输入正确影片价格!')"
								  oninput="setCustomValidity('')">
								</div>
								<div class="form-group">
								    <label for="addintroduction">影片简介</label>
								<!-- 
								    <input type="text"  class="form-control inpbg" id="mintroduction" name="mintroduction" 
									placeholder="请输入影片简介" >    
								 -->		
								 <textarea class="form-control inpbg" id="mintroduction" name="mintroduction"
							         	style="width:700px;height:150px;" placeholder="请输入影片简介"></textarea>					
								</div>			
							</div>
							<div class="col-sm-6">
								<div class="form-group">
								    <label for="addimgfile">请选择要上传的影片海报(尺从大小最好是270*360)</label>
								    <input type="file" id="mimgfile" name="mimgfile" value="" >    
								</div>	
								<div class="imgdiv">
									<img src="" id="imagesrc" name="imagesrc" > 
								</div>
							</div>
						</div>
						<div class="row commitbtn">
							<div class="col-sm-12">
								<div class="form-group">
								    <button type="submit" class="btn btn-primary add-sub">提交</button>  
								    <button type="reset" class="btn btn-default bg-color">重置</button> 
								</div>
							</div>
						</div>
					</form>					
				</div>					
			</div>
		</div>
	</div>
	
	<script src="home/js/jquery-1.12.0.min.js"></script>
	<script src="home/js/addForm.js"></script>
	<script src="home/js/bootstrap.min.js" ></script>
	<script src="home/js/window.js" ></script>
	<script type="text/javascript">
	function isPic()
	{
	    var pic=myform.mimgfile.value;
	    var ext=pic.substring(pic.lastIndexOf(".")+1);
	    //可以再添加允许类型
	    if(ext.toLowerCase()=="jpg" || ext.toLowerCase()=="png" || ext.toLowerCase()=="gif" || pic==""){
	        return true;
	    }
	    else
	    {
	    	win.alert("图片类型错误","只能上传jpg、png、gif图像!");
	    	myform.mimgfile.value="";
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
			fileName = "home/img/movies/" + fileName;
			$("#imagesrc")[0].src=fileName;
		}
	})
	</script>
</body>
</html>