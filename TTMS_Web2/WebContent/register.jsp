<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>注册</title>
<link rel="stylesheet" type="text/css" href="login/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="login/css/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="login/css/component.css" />
<link rel="stylesheet" type="text/css" href="home/css/window.css">
<!--[if IE]>
<script src="login/js/html5.js"></script>
<![endif]-->
</head>
<body>
		<div class="container demo-1">
			<div class="content">
				<div id="large-header" class="large-header">
					<canvas id="demo-canvas"></canvas>
					<div class="logo_box">
						<h3>这就是命</h3>
						<h3>影院票务管理系统</h3>
						<form action="Register" name="f" method="post">
							<div class="input_outer">
								<span class="u_user"></span>
								<input name="logname" class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入姓名" id="username" value="${logname}">
							</div>
							<div>
								<lable>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</lable>
								<lable><input name="sex" type="radio" style="width:30px;height:25px" checked="checked"  id="sex" value="男">男</lable>
								<lable>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</lable>
								<lable><input name="sex" type="radio" style="width:30px;height:25px"  id="sex" value="女">女</lable>
								<lable>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</lable>
							</div>
							<div class="input_outer">
								<span class="us_uer"></span>
								<input name="logpass" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="password"  placeholder="请输入密码" id="password">
							</div>
       							<div class="input_outer">
								<span class="lock"></span>
								<input name="logpassag" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="password" placeholder="请再次输入密码" id="passwordAgain">
							</div>
                                <div class="input_outer">
								<span class="phone"></span>
								<input name="phone" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="tel" verify="phone" placeholder="请输入手机号" id="phone" value="${phone}">
							</div>
							<div class="input_outer">
								<span class="address"></span>
								<input name="address" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="text" verify="address" placeholder="请输入地址" id="address" value="${address}">
							</div>
 							<div class="input_outer">
								<span class="email"></span>
								<input name="email" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="text" verify="email" placeholder="请输入邮箱" id="email" value="${email}">
							</div>
   							
                            <div class="mb2"><center><input class="act-but submit" type="submit" value="                            注册                            " style="color: #FFFFFF" onclick="javascript: return checkadduser();"></center></div>                 
							<center><a href="login.jsp"><font color="darkorange">已有账号？立即登录</font></a></center>
						<span id="account" style="display:none;">${logname}</span>
						</form>
					</div>
				</div>
			</div>
		</div><!-- /container -->
		<script src="login/js/TweenLite.min.js"></script>
		<script src="login/js/EasePack.min.js"></script>
		<script src="login/js/rAF.js"></script>
		<script src="login/js/demo-1.js"></script>
		<div style="text-align:center;">
</div>
	</body>

	<script src="home/js/window.js" ></script>
	 <script type="text/javascript">
	    if(document.getElementById('account').innerHTML!=""){
			   win.alert('错误提示',document.getElementById('account').innerHTML);
		   }
	    
function checkadduser(){
    var name = document.getElementById("username").value;
    var email = document.getElementById("email").value;
    var phone = document.getElementById("phone").value;
    var password = document.getElementById("password").value;
    var password1=document.getElementById("passwordAgain").value;
    var regex = /^[a-zA-Z0-9_-]{3,20}$/;
    var regex1 = /^[a-zA-Z0-9_-]{3,20}$/;
    var regex2 = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+$/ ;
    var regex3 = /^1+[0-9]{10}$/;
    
    if(!name.match(regex)){
        alert("用户名长度不正确或格式不正确!");
	document.f.logname.value="";
	document.f.logpass.value="";
	document.f.logname.focus();
        return false;
    }

    if(!password.match(regex1)){
        alert("密码长度不正确或格式不正确！");
	document.f.logpass.value="";
	document.f.logpass.focus();
        return false;
    }

    if(password!=password1){
    	alert("两次密码不同！请重新输入！");
	document.f.logpass.value="";
	document.f.logpassag.value="";
	document.f.logpassag.focus();
        return false;
    }


    if(!phone.match(regex3)){
        alert("手机格式不正确!");
	document.f.phone.value="";
	document.f.phone.focus();
        return false;
    }
   

    if(!email.match(regex2)){
        alert("邮箱格式不正确!");
	document.f.email.value="";
	document.f.email.focus();
        return false;
    }



    //alert("注册成功!"+document.getElementById('account').innerHTML);

   // window.location.href='../login/login.jsp';

}
      
  </script>
</html>