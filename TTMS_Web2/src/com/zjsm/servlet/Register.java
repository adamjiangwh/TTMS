package com.zjsm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zjsm.ctms.dao.EmployeeDAO;
import com.zjsm.ctms.dao.UserDAO;
import com.zjsm.ctms.model.Employee;
import com.zjsm.ctms.model.User;

@WebServlet(urlPatterns = "/Register")
public class Register extends HttpServlet {
	@Override
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	 {
	     doPost(request, response);
	 }

	 @Override
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	 {
	     request.setCharacterEncoding("UTF-8");
	     // 设置jsp页面编码
	     response.setContentType("text/html;charset=UTF-8");
	     PrintWriter out = response.getWriter();
	     String name = request.getParameter("logname");
	     String sex = request.getParameter("sex");
	     String pass = request.getParameter("logpass");
	     String pass2 = request.getParameter("logpassag");
	     String phone = request.getParameter("phone");
	     String address = request.getParameter("address");
	     String email = request.getParameter("email");
	     String boy = "home/img/headpic/boy.jpg";
	     String girl = "home/img/headpic/girl.jpg";
	     
	     Employee info_emp = new Employee();
	     User info = new User();
	     
	     String flag = getRandom();		// 存放随机数
	     while(flag.equals(info.getEmp_no())) {		// 判断此工号是否存在
	    	 flag = getRandom();
	     }
	     
	     // 注册成功，转到login.jsp
	     if ((name != null && !name.equals("")) && (sex != null && !sex.equals("")) 
	    		 && (pass != null && !pass.equals("")) && (pass2 != null && !pass2.equals("")) 
	    		 && (phone != null && !phone.equals("")) && (address != null && !address.equals("")) 
	    		 && (email != null && !email.equals(""))) {
	    	 info_emp.setEmp_no(flag);
	    	 info_emp.setEmp_name(name);
	    	 info_emp.setEmp_sex(sex);
	    	 info_emp.setEmp_tel_num(phone);
	    	 info_emp.setEmp_addr(address);
	    	 info_emp.setEmp_email(email);
	    	 info.setEmp_no(flag);
	    	 info.setEmp_pass(pass);
	    	 info.setType(0);	// 默认都为普通用户
	    	 
	    	 if(sex.equals("男")) {
	    		 info.setHead_path(boy);
	    	 } else if(sex.equals("女")) {
	    		 info.setHead_path(girl);
	    	 }
	    	 
	    	 new EmployeeDAO().insert(info_emp);
	    	 new UserDAO().insert(info);
	    	 
	         request.setAttribute("logname", flag);
	         request.setAttribute("logpass", pass);
//	         out.println("<script type=\"text/javascript\">"
//	         		+ "win.alert('注册成功','页面即将跳转，请记住您的帐号："+flag+"')"
//	         		+ "</script>");
	         request.getRequestDispatcher("login.jsp").forward(request, response);
	     }
	     else
	     {
	         request.setAttribute("desc", "注册失败！");
	         request.setAttribute("logname", name);
	         request.setAttribute("sex", sex);
	         request.setAttribute("logpass", pass);
	         request.setAttribute("logpassag", pass2);
	         request.setAttribute("phone", phone);
	         request.setAttribute("address", address);
	         request.setAttribute("email", email);
	         request.setAttribute("boy", boy);
	         request.setAttribute("girl", girl);
	         request.getRequestDispatcher("register.jsp").forward(request, response);
	     }
	 }
	 
	 	/**
	     * 获取length位的随机位数数字
	     * @param length    想要生成的长度
	     * @return result
	     */
	    public static String getRandom() {
	        String result = "";
	        Integer length = 10;
	        Random rand = new Random();
	        int n = 20;
	        if (null != length && length > 0) {
	            n = length;
	        }
	        int randInt = 0;
	        for (int i = 0; i < n; i++) {
	            randInt = rand.nextInt(10);
	 
	            result += randInt;
	        }
	        return result;
	    }
}
