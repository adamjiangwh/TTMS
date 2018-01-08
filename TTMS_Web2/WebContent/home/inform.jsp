<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html>
<html>
<head>
	<title>公告</title>
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
	<script src="home/js/jquery-1.12.0.min.js"></script>

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
                        <li class="active"><a href="home/inform.jsp"><i class="icon-notice icon"></i>公告</a></li>    
    				<%
    					} else if(type == 0) {
    						
    				%>
                        <li><a href="Movie?method=searchByPage"><i class="icon-movies icon"></i>影片管理</a></li>
                        <li><a href="Theatre?method=searchByPage"><i class="icon-theatre icon"></i>影厅管理</a></li>
                        <li><a href="Order?method=searchSaleByPage"><i class="icon-schedule icon"></i>订单管理</a></li>
                        <li class="active"><a href="home/inform.jsp"><i class="icon-notice icon"></i>公告</a></li>                        
					<%					
    					}
					%>
                         
                    </ul>
    			</div>
            </div>	
		</nav>
		<div id="page-wrapper" class="pa-wrp">
			<div class="grap">
				<div class="row rowset">
					<div class="col-md-12">
						<input type="button" name="" data-toggle="modal" data-target="#informModal" class="btn btn-primary bright" value="添加公告">
						<input type="button" name="" class="btn btn-primary bright" onclick="delbox()" value="批量操作">
						<button type="button" id="delete" class="btn btn-danger bright" style="display: none;">删除</button>
					</div>
				</div>
				
				<div class="row rowset">
					<div class="col-md-6">
						<div class="panel relativ">
							<div class="panel-heading head-green">
								观看3D电影须知
							</div>
							<div class="panel-body">
								<p>根据影院在11月份调查发现，很多观众希望影城能在影厅门口提供3D眼镜服务，为了节约观众观影时间，减少借用眼镜步骤，这就是命影院从2015年12月18日起，3D眼镜将在影厅门口发放，减少从卖品部眼镜出借用的环节。请大家相互告知。如有其他问题，可与影城值班经理联系。</p>
							</div>
						</div>
						<div class="absol">
							<input type="checkbox" class="flat" style="display: none;" name="table_records" id="informbox1">
							<label for="informbox1" class="labelchk" style="display: none;"></label>
						</div>						
					</div>
					<div class="col-md-6">
						<div class="panel">
							<div class="panel-heading head-org">
								这就是命影院无线网络
							</div>
							<div class="panel-body">
								<p>这就是命影院现已无线网络全面覆盖。看视频、刷微博分享，没有给力网络怎么行？在这就是命影院您可以享受舒适高速的上网冲浪。以下是我们的用户名和密码：
          						    <br> 用户名：zjsm888888
              	 					<br> 密码：zjsm888888
								</p>
							</div>
						</div>
						<div class="absol">
							<input type="checkbox" class="flat" style="display: none;" name="table_records" id="informbox2">
							<label for="informbox2" class="labelchk" style="display: none;"></label>
						</div>
					</div>
				</div>
				<!-- 模态框（Modal） -->
				<div class="modal fade" id="informModal" tabindex="-1" role="dialog" aria-labelledby="informLable" aria-hidden="true">
				    <div class="modal-dialog">
				        <div class="modal-content">
				            <div class="modal-header">
				                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				                <h4 class="modal-title" id="informLable">公告信息添加</h4>
				            </div>
				            <div class="modal-body">
				            	<form action="" method="post" class="form-horizontal" id="addform" role="form">
				            	  <div class="form-group">
				            	    <label for="addname" class="col-sm-2 control-label">内容:</label>
				            	    <div class="col-sm-12">
				            	      <textarea name="" rows="12" cols="68"></textarea>
				            	    </div>
				            	  </div>				              	  
				            	</form>
				            </div>
				            <div class="modal-footer">
				                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				                <button type="submit" id="submitinf" class="btn btn-primary">提交</button>
				            </div>
				        </div>
				    </div>
				</div>

			</div>
		</div>
	</div>

	<script>
		/*通过封装 getElementsByClassName 来处理兼容性*/
		function getElementsByClassName(className){
			var results=[];
			var tags=document.getElementsByTagName('*');
			for(var item in tags){
				if(tags[item].nodeType==1){
					if(tags[item].getAttribute('class')==className){
						results.push(tags[item]);
					}
				}
			}
			return results;
		}
		function delbox(){
			var del=document.getElementById('delete');
			var lab=document.getElementsByClassName('labelchk');
			var n=lab.length;
			for(var i=0;i<n;i++){
				lab[i].style.display="block";
			}
			del.style.display="inline-block";
		}
	</script>
	
	<script src="home/js/bootstrap.min.js" ></script>
</body>
</html>