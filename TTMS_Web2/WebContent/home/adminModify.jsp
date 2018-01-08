<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html>
<html>
<head>
	<title>个人信息修改</title>
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
                        <li><a href="Theatre?method=searchByPage"><i class="icon-theatre icon"></i>影厅管理</a></li>
                        <li><a href="home_root/people.jsp"><i class="icon-human icon"></i>成员管理</a></li>
                        <li><a href="Order?method=searchSaleByPage"><i class="icon-schedule icon"></i>订单管理</a></li>
                        <li><a href="home_root/finance.jsp"><i class="icon-finance icon"></i>财务报表</a></li>
                        <li><a href="home/inform.jsp"><i class="icon-notice icon"></i>公告</a></li>    
    				<%
    					} else if(type == 0) {
    						
    				%>
                        <li><a href="Movie?method=searchByPage"><i class="icon-movies icon"></i>影片管理</a></li>
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
					    个人信息修改
					</div>
				</div>
				<div class="row">
					<div class="form-box">
						<form action="Image?method=edit" method="post" accept-charset="utf-8" class="form-horizontal" id="adminform" name="myform" role="form">
							<div class="col-sm-offset-1 col-sm-2">
								<br><br><br><br><br><img src="${user.head_path}" width="200px" heigth="200px" id="imgsource">
								<input type="file" id="headpath" name="headpath" value="" >
							</div>
							<input type="hidden" name="emp_id" value="${employee.emp_id}">
							<div class="col-sm-9">
								<div class="form-group">
								   	<label for="adminname" class="col-sm-2 control-label">姓名:</label>
								   	<div class="col-sm-8">
								    	<input type="text" class="form-control formbor" id="adminname" name="adminname"  pattern=".{2,10}"
											required="required" oninvalid="setCustomValidity('请输入真实姓名,10字以内!')"
											oninput="setCustomValidity('')"  value="${employee.emp_name}">
									</div>
								</div>
								<div class="form-group">
								  <label for="adminsex" class="col-sm-2 control-label">性别:</label>
								  <div class="col-sm-8">
								    <input type="radio" id="adminsexman" name="sex" value="男" ${employee.emp_sex == "男"?'checked':''}>男
				            	    <input type="radio" id="adminsexwomen" name="sex" value="女" ${employee.emp_sex == "女"?'checked':''}>女
								  </div>
								</div>
								
								<div class="form-group">
								  <label for="adminposition" class="col-sm-2 control-label">职位:</label>
								  <div class="col-sm-8">
								    <input type="text" class="form-control formbor" id="adminposition" name="posit" readonly="readonly"
								    value="${type}">
								  </div>
								</div>
								
								<div class="form-group">
								  <label for="adminphone" class="col-sm-2 control-label">电话:</label>
								  <div class="col-sm-8">
								    <input type="text" class="form-control formbor" id="adminphone" name="phone" pattern="1[3-8][0-9]{9}" 
										required="required" oninvalid="setCustomValidity('请输入正确手机号码!')"
										oninput="setCustomValidity('')" value="${employee.emp_tel_num}">
								  </div>
								</div>
								<div class="form-group">
								  <label for="adminaddr" class="col-sm-2 control-label">地址:</label>
								  <div class="col-sm-8">
								    <input type="text" class="form-control formbor" id="adminaddr" name="addr" pattern=".{2,30}" 
										required="required" oninvalid="setCustomValidity('请输入地址!')"
										oninput="setCustomValidity('')" value="${employee.emp_addr}">
								  </div>
								</div>
								<div class="form-group">
								  <label for="adminemail" class="col-sm-2 control-label">邮箱:</label>
								  <div class="col-sm-8">
								    <input type="text" class="form-control formbor" id="adminemail" name="email" oninvalid="setCustomValidity('请输入正确格式Email!')"
										pattern="([a-zA-Z0-9_-])+@[a-zA-Z0-9_-]+((\.[a-zA-Z0-9_-]{2,3}){1,2})"
										oninput="setCustomValidity('')" value="${employee.emp_email}">
								  </div>
								</div>
								<div class="form-group">
								  <label for="adminaccount" class="col-sm-2 control-label">账号:</label>
								  <div class="col-sm-8">
								    <input type="text" class="form-control formbor" id="adminaccount" name="account" readonly="readonly" value="${employee.emp_no}">
								  </div>
								</div>
								<div class="form-group">
								  <label for="adminpasswd" class="col-sm-2 control-label">密码:</label>
								  <div class="col-sm-8">
								    <input type="text" class="form-control formbor" id="adminpasswd" name="passwd"  oninvalid="setCustomValidity('请输入密码')"
								    oninput="setCustomValidity('')"  pattern=".{3,20}" value="${user.emp_pass}">
								  </div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-5 col-sm-8">
							    	<button type="submit" class="btn  btnmdy">提交</button>
								</div>
							</div>
							<span id="updatepeople" style="display:none;">${result}</span>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="home/js/jquery-1.12.0.min.js"></script>
	<script src="home/js/bootstrap.min.js" ></script>
	<script src="home/js/window.js" ></script>
	<script type="text/javascript">
	//操作成功
	if(document.getElementById('updatepeople').innerHTML!=""){
		   win.alert("系统提示",document.getElementById('updatepeople').innerHTML);
	   }
	
	//判断图片类型
	function isPic()
	{
	    var pic=myform.headpath.value;
	    var ext=pic.substring(pic.lastIndexOf(".")+1);
	    //可以再添加允许类型
	    if(ext.toLowerCase()=="jpg" || ext.toLowerCase()=="png" || ext.toLowerCase()=="gif" || pic==""){
	        return true;
	    }
	    else
	    {
	    	win.alert("图片类型错误","只能上传jpg、png、gif图像!");
	    	myform.headpath.value="";
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
			fileName = "home/img/headpic/" + fileName;
			$("#imgsource")[0].src=fileName;
		} 
	})
	</script>
</body>
</html>