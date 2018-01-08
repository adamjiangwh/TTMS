<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String flag=(String)session.getAttribute("loginflag");
if(flag==null || !flag.equals("ok"))
{
    request.getSession().setAttribute("desc", "请从入口登录");
    request.getRequestDispatcher("index.jsp").forward(request, response);
}
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<title>成员管理</title>
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
                        <li><a href="Movie?method=searchByPage"><i class="icon-movies icon"></i>影片管理</a></li>
                        <li><a href="Theatre?method=searchByPage"><i class="icon-theatre icon"></i>影厅管理</a></li>
                        <li  class="active"><a href="home_root/people.jsp"><i class="icon-human icon"></i>成员管理</a></li>
                        <li><a href="Order?method=searchSaleByPage"><i class="icon-schedule icon"></i>订单管理</a></li>
                        <li><a href="home_root/finance.jsp"><i class="icon-finance icon"></i>财务报表</a></li>
                        <li><a href="home/inform.jsp"><i class="icon-notice icon"></i>公告</a></li>
                         
                    </ul>
    			</div>
            </div>	
		</nav>
		<div id="page-wrapper" class="pa-wrp">

			<div class="grap">
				<div class="row">
                    <div class="col-md-12">
						<div class="header">
							<div class="page-hd">
							    成员管理
							</div>
						</div>
                    </div>
                </div> 
                 <!-- /. ROW  -->
				               
	            <div class="row">
	                <div class="col-md-12">
	                	<ol class="breadcrumb bg-head">	
	                		<li><a href="home.jsp">Home</a></li>		   
	                	    <li class="active">成员信息</li>
	                	</ol>
	                    <!-- Advanced Tables -->
	                    <div class="panel panel-default">
	                        <div class="panel-heading">
	                             员工信息表
	                        </div>
	                        <div class="panel-body">
	                            <div class="table-responsive">
	                            	<div class="add-people">
	                            		<button class="btn btn-primary" data-toggle="modal" data-target="#addModal">添加人员</button>
	                            	</div>
	                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
	                                    <thead>
	                                        <tr>
	                                            <th>姓名</th>
	                                            <th>性别</th>
	                                            <th>权限</th>
	                                            <th>电话</th>
	                                            <th>地址</th>
	                                            <th>邮箱</th>
	                                            <th>账号</th>
	                                            <th>密码</th>
	                                            <th>操作</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>

	                                    </tbody>
	                                </table>
	                            </div>
	                            
	                        </div>
	                    </div>
	                    <!--End Advanced Tables -->
	                </div>
	            </div>
			</div>

			<!-- 模态框（Modal） -->
			<div class="modal fade" id="modifyModal" tabindex="-1" role="dialog" aria-labelledby="modifyModalLabel" aria-hidden="true">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                <h4 class="modal-title" id="modifyModalLabel">人员信息修改</h4>
			            </div>
			            <div class="modal-body">
			            	<form action="People?method=update" method="post" class="form-horizontal" id="mdyform" role="form">
			            	  <div class="form-group">
			            	    <label for="mdyname" class="col-sm-2 control-label">姓名:</label>
			            	    <div class="col-sm-10">
			            	      <input type="text" class="form-control" id="mdyname" name="username" placeholder="请输入姓名"
			            	      pattern=".{2,10}"
								  required="required" oninvalid="setCustomValidity('请输入真实姓名,10字以内!')"
								  oninput="setCustomValidity('')">
			            	    </div>
			            	  </div>
			            	  <div class="form-group">
			            	    <label for="mdysex" class="col-sm-2 control-label">性别:</label>
			            	    <div class="col-sm-10">
			            	      <input type="radio" id="mdysexman" name="sex" value="男">男
			            	      <input type="radio" id="mdysexwomen" name="sex" value="女">女
			            	    </div>
			            	  </div>
			            	  <div class="form-group">
			            	    <label for="mdytype" class="col-sm-2 control-label">权限:</label>
			            	    <div class="col-sm-10">
			            	      <input type="radio" id="mdytypeadmin" name="type" value="1">管理员
			            	      <input type="radio" id="mdytypeuser" name="type" value="0">用户
			            	     <!-- <input type="text" class="form-control" id="mdyage" name="age" placeholder="请输入权限"> --> 
			            	    </div>
			            	  </div>
			            	  <div class="form-group">
			            	    <label for="mdyphone" class="col-sm-2 control-label">电话:</label>
			            	    <div class="col-sm-10">
			            	      <input type="text" class="form-control" id="mdyphone" name="phone" placeholder="请输入电话"
			            	      pattern="1[3-8][0-9]{9}" 
							      required="required" oninvalid="setCustomValidity('请输入正确手机号码!')"
							      oninput="setCustomValidity('')">
			            	    </div>
			            	  </div>
			            	  <div class="form-group">
			            	    <label for="mdyaddr" class="col-sm-2 control-label">地址:</label>
			            	    <div class="col-sm-10">
			            	      <input type="text" class="form-control" id="mdyaddr" name="addr" placeholder="请输入地址"
			            	      placeholder="请输入地址" pattern=".{2,30}" 
							      required="required" oninvalid="setCustomValidity('请输入地址!')"
							      oninput="setCustomValidity('')">
			            	    </div>
			            	  </div>	
			            	  <div class="form-group">
			            	    <label for="mdyemail" class="col-sm-2 control-label">邮箱:</label>
			            	    <div class="col-sm-10">
			            	      <input type="text" class="form-control" id="mdyemail" name="email" placeholder="请输入邮箱"
			            	      required="required"  
							      pattern="([a-zA-Z0-9_-])+@[a-zA-Z0-9_-]+((\.[a-z]{2,3}){1,2})"
							      oninvalid="setCustomValidity('请输入正确格式Email!')"
							      oninput="setCustomValidity('')">
			            	    </div>
			            	  </div>
			            	  <div class="form-group">
			            	    <label for="mdyno" class="col-sm-2 control-label">帐号:</label>
			            	    <div class="col-sm-10">
			            	      <input type="text" class="form-control" id="mdyno" name="no" readonly="readonly">
			            	    </div>
			            	  </div>
			            	  <div class="form-group">
			            	    <label for="mdypass" class="col-sm-2 control-label">密码:</label>
			            	    <div class="col-sm-10">
			            	      <input type="text" class="form-control" id="mdypass" name="pass" placeholder="请输入密码">
			            	    </div>
			            	  </div>       
  
			            <div class="modal-footer">
			                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			                <input type="submit" id="submitmdy" class="btn btn-primary"></input>
			            </div>
			            	</form>
			            </div>
			        </div>
			    </div>
			</div>

			<!-- 模态框（Modal） -->
			<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                <h4 class="modal-title" id="addModalLabel">人员信息添加</h4>
			            </div>
			            <div class="modal-body">
			            	<form action="People?method=add" method="post" class="form-horizontal" id="addform" role="form">
			            	  <div class="form-group">
			            	    <label for="addname" class="col-sm-2 control-label">姓名:</label>
			            	    <div class="col-sm-10">
			            	      <input type="text" class="form-control" id="addname" name="username" placeholder="请输入姓名"
			            	      placeholder="请输入真实姓名,10字以内" pattern=".{2,10}"
								  required="required" oninvalid="setCustomValidity('请输入真实姓名,10字以内!')"
								  oninput="setCustomValidity('')">
			            	    </div>
			            	  </div>
			            	  <div class="form-group">
			            	    <label for="addsex" class="col-sm-2 control-label">性别:</label>
			            	    <div class="col-sm-10">
			            	      <input type="radio"  id="addsexwan" name="sex" value="男">男
			            	      <input type="radio" id="addsexwomen" name="sex" value="女">女
			            	    </div>
			            	  </div>
			            	  <div class="form-group">
			            	    <label for="addposition" class="col-sm-2 control-label">职位:</label>
			            	    <div class="col-sm-10">
			            	      <input type="radio" id="addtypeadmin" name="type" value="1">管理员
			            	      <input type="radio" id="addtypeuser" name="type" value="0">用户
			            	    </div>
			            	  </div>
			            	  
			            	  <div class="form-group">
			            	    <label for="addphone" class="col-sm-2 control-label">电话:</label>
			            	    <div class="col-sm-10">
			            	      <input type="text" class="form-control" id="addphone" name="phone" 
			            	      placeholder="请输入手机号码" pattern="1[3-8][0-9]{9}" 
							      required="required" oninvalid="setCustomValidity('请输入正确手机号码!')"
							      oninput="setCustomValidity('')">
			            	    </div>
			            	  </div>
			            	  <div class="form-group">
			            	    <label for="addaddr" class="col-sm-2 control-label">地址:</label>
			            	    <div class="col-sm-10">
			            	      <input type="text" class="form-control" id="addaddr" name="addr" 
			            	      placeholder="请输入地址" pattern=".{2,30}" 
							      required="required" oninvalid="setCustomValidity('请输入地址!')"
							      oninput="setCustomValidity('')">
			            	    </div>
			            	  </div>
			            	  <div class="form-group">
			            	    <label for="addemail" class="col-sm-2 control-label">邮箱:</label>
			            	    <div class="col-sm-10">
			            	      <input type="text" class="form-control" id="addemail" name="email" 
			            	      placeholder="请输入Email" required="required"  
							      pattern="([a-zA-Z0-9_-])+@[a-zA-Z0-9_-]+((\.[a-z]{2,3}){1,2})"
							      oninvalid="setCustomValidity('请输入正确格式Email!')"
							      oninput="setCustomValidity('')">
			            	    </div>
			            	  </div>
			            	  
			            	  <div class="form-group">
			            	    <label for="addpasswd" class="col-sm-2 control-label">密码:</label>
			            	    <div class="col-sm-10">
			            	      <input type="text" class="form-control" id="addpasswd" name="passwd" placeholder="请输入密码"
			            	      pattern=".{3,20}"
								  required="required" oninvalid="setCustomValidity('请输入正确密码,3~20个字符!')"
								  oninput="setCustomValidity('')">
			            	    </div>
			            	  </div>          	  
			            <div class="modal-footer">
			                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			                <button type="submit" id="submitadd" class="btn btn-primary">提交更改</button>
			            </div>
			            	</form>
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
	<script src="home/js/edit.js"></script>
	<script src="home/js/dataTables/jquery.dataTables.js"></script>
	<script src="home/js/dataTables/dataTables.bootstrap.js"></script>
	<script src="home/js/bootstrap.min.js" ></script>
	<script>
		/*加载dataTable插件*/		
/*		$(document).ready(function () {
            $('#dataTables-example').dataTable();
			$.get("/TTMS_Web2/People",function(data,status){
				$("#ajax").append(data);
			});
		});*/
		
		$(function(){
			$.ajax({
				url:"/TTMS_Web2/People?method=show",
				type:"post",
				success: function(data){
				var obj=JSON.parse(data);
				var tbody=$('<tbody></tbody>');
				$(obj).each(function (index){
				var val=obj[index];
				//console.log(val);
				var tr=$('<tr class="even gradeX"></tr>');
				tr.append('<td>'+ val.name + '</td>' + '<td>'+ val.sex + '</td>' +'<td>'+ val.type + '</td>'
						+ '<td>' + val.phone + '</td>' + '<td>' + val.address + '</td>' + '<td>' + val.email + '</td>'
						+ '<td>' + val.no + '</td>' + '<td>' + val.pass + '</td>'
						+ '<td>'
						+'<a onclick="editInfo(this)"><i class="icon-edit-pencil icon"></i></a>'
						+'<a onclick="delcfm(\'People?method=delete&emp_no='+ val.no +'\')" class="pointer"><i class="icon-delete-dustbin"></i></a>'
           				+'</td>');
				tbody.append(tr);
				});
				$('#dataTables-example tbody').replaceWith(tbody);
				$('#dataTables-example').dataTable();
				}
			});
		});
    </script>

</body>
</html>