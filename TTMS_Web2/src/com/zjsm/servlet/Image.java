package com.zjsm.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zjsm.ctms.dao.EmployeeDAO;
import com.zjsm.ctms.dao.UserDAO;
import com.zjsm.ctms.idao.DAOFactory;
import com.zjsm.ctms.model.Employee;
import com.zjsm.ctms.model.User;

//可以不用配置web.xml,在Servlet中通过注解设置Servlet
@WebServlet(urlPatterns = "/TTMS_Web2/Image")
public class Image extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		// 设置jsp页面编码
		response.setContentType("text/html;charset=UTF-8");
		
		String method = request.getParameter("method");
        if (method.equalsIgnoreCase("image"))
        	image(request, response);
        else if (method.equalsIgnoreCase("edit"))
            edit(request, response);
        else if (method.equalsIgnoreCase("search"))
            search(request, response);
	
	}
	
	private void image(HttpServletRequest request, HttpServletResponse response) {
		 
		HttpSession hs = request.getSession();
		String imagePath = hs.getAttribute("path").toString();
		
//		String imagePath = "home/img/1.jpg";
		FileInputStream fis;
		try {
			fis = new FileInputStream(this.getClass().getClassLoader().getResource("/").getPath()+"../../"+imagePath);
//		    System.out.println(this.getClass().getClassLoader().getResource("/").getPath()+"../../"+imagePath);
			int size =fis.available(); //得到文件大小 
			byte data[]=new byte[size]; 
			fis.read(data);  //读数据 
			fis.close(); 
			response.setContentType("image/jpg"); //设置返回的文件类型 
	        OutputStream os = response.getOutputStream();
	        os.write(data);
	        os.flush();
	        os.close();        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void edit(HttpServletRequest request, HttpServletResponse response)
	{
		
		
		int emp_id = Integer.parseInt(request.getParameter("emp_id"));
        String emp_name = request.getParameter("adminname");
        String emp_sex = request.getParameter("sex");
        String emp_type = request.getParameter("posit");
        String emp_tel_num = request.getParameter("phone");
        String emp_addr = request.getParameter("addr");
        String emp_email = request.getParameter("email");
        String emp_no = request.getParameter("account");
        String emp_pass = request.getParameter("passwd");
        String path = request.getParameter("headpath");
        
    	User info = new UserDAO().findUserByNo(emp_no);
    	String pathnull = info.getHead_path();
              
        Employee employee = new Employee();
       
        employee.setEmp_id(emp_id);
        employee.setEmp_no(emp_no);
        employee.setEmp_name(emp_name);
        employee.setEmp_tel_num(emp_tel_num);
        employee.setEmp_addr(emp_addr);
        employee.setEmp_email(emp_email);
        employee.setEmp_sex(emp_sex); 
        EmployeeDAO dao = (EmployeeDAO) DAOFactory.createEmployeeDAO();
        boolean result = dao.update(employee);
        User user = new User();
        user.setEmp_no(emp_no);
        user.setEmp_pass(emp_pass);
        //头像处理
        if(path == null || path.equals("")) {
        	user.setHead_path(pathnull);
        	path = pathnull;
        } else {
        	path = "home/img/headpic/"+path;
        	user.setHead_path(path);        	
        }
        int type=0;
        if (emp_type.equals("用户")) {
			type = 0;
		} else if (emp_type.equals("管理员")) {
			type = 1;
		}
        user.setType(type);
        UserDAO dao2 = (UserDAO) DAOFactory.createUserDAO();
        boolean result2 = dao2.update(user);
        try
        {
            if (result && result2)
                request.setAttribute("result", "更新成功!");
            else
                request.setAttribute("result", "更新失败!");
            request.setAttribute("employee", employee);
            request.setAttribute("user", user);
            request.getSession().setAttribute("path", path);
            request.getRequestDispatcher("Image?method=search").forward(request, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
	
	private void search(HttpServletRequest request, HttpServletResponse response) {
		HttpSession hs = request.getSession();
		String emp_no = hs.getAttribute("user").toString();
		
		Employee emp_info = new EmployeeDAO().findUserByNo(emp_no);
		User user_info = new UserDAO().findUserByNo(emp_no);
		
		String type = null;
		if (user_info.getType() == 0) {
			type = "用户";
		} else if (user_info.getType() == 1) {
			type = "管理员";
		}
			
		 request.setAttribute("employee", emp_info);
		 request.setAttribute("user", user_info);
		 request.setAttribute("type", type);
		 try
        {
            request.getRequestDispatcher("home/adminModify.jsp").forward(request, response);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		
//	        User user_info = new UserDAO().findUserByNo(no);
//	        int emp_id = emp_info.getEmp_id();
//	        String emp_name = emp_info.getEmp_name();
//	        String emp_sex =emp_info.getEmp_sex();
//	        int emp_type =user_info.getType();
//	        String emp_tel_num = emp_info.getEmp_tel_num();
//	        String emp_addr = emp_info.getEmp_email();
//	        String emp_email = emp_info.getEmp_email();
//	        String emp_pass = user_info.getEmp_pass();
//	        String path = user_info.getHead_path();
//	      
	}
}
