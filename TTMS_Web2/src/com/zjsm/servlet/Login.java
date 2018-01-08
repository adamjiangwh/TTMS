package com.zjsm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.zjsm.ctms.dao.UserDAO;
import com.zjsm.ctms.model.User;

//可以不用配置web.xml,在Servlet中通过注解设置Servlet
@WebServlet(urlPatterns = "/Login")
public class Login extends HttpServlet
{
	 @Override
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	 {
	     doPost(request, response);
	 }
	
	 @SuppressWarnings("unused")
	@Override
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	 {
	     request.setCharacterEncoding("UTF-8");
	     // 设置jsp页面编码
	     response.setContentType("text/html;charset=UTF-8");
	     PrintWriter out = response.getWriter();
	     
	     // TODO:一个账号登陆后，另一个账户登录，会访问前一个账户的内容，所以登陆后需要清除原session
	     request.getSession().setAttribute("login", null);
    	 request.getSession().setAttribute("home_root", null);
		 request.getSession().invalidate();
	     String user = request.getParameter("logname");
	     String pass = request.getParameter("logpass");
	    
	     String path = null;
	     
	     User info = new UserDAO().findUserByNo(user);
	     int type = info.getType();
	     
	     // 用户名密码正确，转发到result.jsp
	     if(info == null) {
	    	 request.setAttribute("desc", "该用户不存在！");
	    	 request.getRequestDispatcher("login.jsp").forward(request, response);
	     }
	     else if (user != null && user.equals(info.getEmp_no()) && pass != null && pass.equals(info.getEmp_pass()))
	     {
	    	 path = info.getHead_path();
	    	 request.getSession().setAttribute("path", path);
	    	 request.getSession().setAttribute("user", user);
	    	 request.getSession().setAttribute("type", type);
	    	 if(type == 1) {		// 管理员
		         // 转发携带原request封装的数据
		         request.getSession().setAttribute("home_root", "ok");
		         request.setAttribute("type", type);
	    	 } else if(type == 0) {		// 用户
		         // 转发携带原request封装的数据
		         request.setAttribute("type", type);
	    	 }
	    	 request.getSession().setAttribute("loginflag", "ok");
	    	 request.setAttribute("logname", user);
	    	 request.setAttribute("logpass", pass);
	    	 request.getRequestDispatcher("home.jsp").forward(request, response);
	     }
	     else
	     {
	         request.setAttribute("desc", "用户名或密码错误!");
	         request.getRequestDispatcher("login.jsp").forward(request, response);
	     }
	 }
}
