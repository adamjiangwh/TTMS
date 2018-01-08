package com.zjsm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.webkit.ContextMenu.ShowContext;
import com.zjsm.ctms.dao.EmployeeDAO;
import com.zjsm.ctms.dao.UserDAO;
import com.zjsm.ctms.idao.DAOFactory;
import com.zjsm.ctms.model.Employee;
import com.zjsm.ctms.model.User;

import sun.nio.cs.ext.DoubleByte.Encoder_DBCSONLY;

//可以不用配置web.xml,在Servlet中通过注解设置Servlet
//@WebServlet(urlPatterns = "/TTMS_Web2/People")
public class People extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 设置jsp页面编码
		response.setContentType("text/html;charset=UTF-8");

		
		String method = request.getParameter("method");
        if (method.equalsIgnoreCase("show"))
        	show(request, response);
        else if (method.equalsIgnoreCase("add"))
            add(request, response);
        else if (method.equalsIgnoreCase("delete"))
            delete(request, response);
        else if (method.equalsIgnoreCase("update"))
            update(request, response);
//        else if (method.equalsIgnoreCase("search"))
//            search(request, response);
//        else if (method.equalsIgnoreCase("searchById"))
//            searchById(request, response);
//        else if (method.equalsIgnoreCase("searchByPage"))
//            searchByPage(request, response);
	}
	
	private void show(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		List<Employee> info_emp = new EmployeeDAO().findEmployeeAll();
		List<User> info = new UserDAO().findUserAll();
		
		// 排序
		Comparator empcom = new Comparator<Object>() {  
            @Override  
            public int compare(Object o1, Object o2) {  
                // TODO Auto-generated method stub  
            	Employee e1 = (Employee) o1;
            	Employee e2 = (Employee) o2;
            	return e1.getEmp_no().compareTo(e2.getEmp_no());
            }  
        };        
        info_emp.sort(empcom);
		// 排序
		Comparator usercom = new Comparator<Object>() {  
            @Override  
            public int compare(Object o1, Object o2) {  
                // TODO Auto-generated method stub  
            	User e1 = (User) o1;
            	User e2 = (User) o2;
            	return e1.getEmp_no().compareTo(e2.getEmp_no());
            }  
        }; 
        info.sort(usercom);
        
		Iterator<Employee> itr = info_emp.iterator();
		Iterator<User> ituser = info.iterator();
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		while (itr.hasNext() && ituser.hasNext()) {
			Employee list_emp = itr.next();
			User list_user = ituser.next();
			
			String name = list_emp.getEmp_name();
			String sex = list_emp.getEmp_sex();
			// 性别 String
			String type = null;
			if (list_user.getType() == 0) {
				type = "用户";
			} else if (list_user.getType() == 1) {
				type = "管理员";
			}
			String phone = list_emp.getEmp_tel_num();
			String address = list_emp.getEmp_addr();
			String email = list_emp.getEmp_email();
			String no = list_emp.getEmp_no();
			String pass = list_user.getEmp_pass();

			sb.append("{\"name\":\""+name+"\",");
			sb.append("\"sex\":\""+sex+"\",");
			sb.append("\"type\":\""+type+"\",");
			sb.append("\"phone\":\""+phone+"\",");
			sb.append("\"address\":\""+address+"\",");
			sb.append("\"email\":\""+email+"\",");
			sb.append("\"no\":\""+no+"\",");
			sb.append("\"pass\":\""+pass+"\"},");
			
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.write(sb.toString());
		//System.out.println(sb.toString());
	}
	
    private void add(HttpServletRequest request, HttpServletResponse response)
    {
        
        String emp_name = request.getParameter("username");
        String emp_sex  = request.getParameter("sex");
        int type  = Integer.parseInt(request.getParameter("type"));
        String emp_tel_num = request.getParameter("phone");
        String emp_addr = request.getParameter("addr");
        String emp_email = request.getParameter("email");
        String emp_pass = request.getParameter("passwd");
        
        String boy = "home/img/headpic/boy.jpg";		//男头
        String girl = "home/img/headpic/girl.jpg";		//女头
         
        Employee employee = new Employee();
        User user = new User();
        String flag = getRandom();		// 存放随机数
	     while(flag.equals(user.getEmp_no())) {		// 判断此工号是否存在
	    	 flag = getRandom();
	    	 }
        
	    employee.setEmp_no(flag);
        employee.setEmp_name(emp_name);
        employee.setEmp_sex(emp_sex);
        employee.setEmp_tel_num(emp_tel_num);
        employee.setEmp_addr(emp_addr);
        employee.setEmp_email(emp_email);
        user.setEmp_no(flag);
        user.setType(type);
        user.setEmp_pass(emp_pass);
        
       
		if(emp_sex.equals("男")) {
   		 user.setHead_path(boy);
    	 } else if(emp_sex.equals("女")) {
   		 user.setHead_path(girl);
   	    }
   	 
        EmployeeDAO dao = (EmployeeDAO) DAOFactory.createEmployeeDAO();
        boolean result = dao.insert(employee);
        UserDAO dao2 = (UserDAO) DAOFactory.createUserDAO();
        boolean result2 = dao2.insert(user);
        
        try
        {
            if (result && result2)
                request.setAttribute("result", "添加成功!");
            else
                request.setAttribute("result", "添加失败!");
            response.sendRedirect("home_root/people.jsp");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }

  

	private void delete(HttpServletRequest request, HttpServletResponse response)
    {
        boolean result = false;
        String emp_no = request.getParameter("emp_no");
        if (emp_no != null && !emp_no.equals(""))
        {
            EmployeeDAO dao = (EmployeeDAO) DAOFactory.createEmployeeDAO();
            result = dao.delete(emp_no);
            UserDAO dao2 = (UserDAO) DAOFactory.createUserDAO();
            boolean result2 = dao2.delete(emp_no);
         
            
            try
            {
                if (result && result2)
                    request.setAttribute("result", "删除成功!");
                else
                    request.setAttribute("result", "删除失败!");
                response.sendRedirect("home_root/people.jsp");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            // 不分页时删除调用全查
            //show(request, response);
            // 分页时删除调用分页全查:使用分页index1.jsp时，把这里注释打开
            // searchByPage(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response)
    {
//      int emp_id = Integer.parseInt(request.getParameter("emp_id"));
        String emp_no = request.getParameter("no");
        String emp_name = request.getParameter("username");
        String emp_tel_num = request.getParameter("phone");
        String emp_addr = request.getParameter("addr");
        String emp_email = request.getParameter("email");
        String emp_sex = request.getParameter("sex");
        int emp_type = Integer.parseInt(request.getParameter("type"));
        String emp_pass = request.getParameter("pass");
        //获取emp_id
        Employee emp = new EmployeeDAO().findUserByNo(emp_no);
        int emp_id = emp.getEmp_id();
        
        String boy = "home/img/headpic/boy.jpg";		//男头
        String girl = "home/img/headpic/girl.jpg";		//女头
        
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
        if(emp_sex.equals("男")) {        	
        	user.setHead_path(boy);
        } else if(emp_sex.equals("女")){
        	user.setHead_path(girl);
        }
        user.setType(emp_type);
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
            request.getRequestDispatcher("home_root/people.jsp").forward(request, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

//    private void search(HttpServletRequest request, HttpServletResponse response)
//    {
//        String emp_name = request.getParameter("emp_name");
//        EmployeeDAO dao = (EmployeeDAO) DAOFactory.createEmployeeDAO();
//        ArrayList<Employee> list = null;
//        if (emp_name == null || emp_name.equals(""))
//            list = dao.findEmployeeAll();
//        else
//            list = dao.findEmployeeByName(emp_name);
//        try
//        {
//            request.setAttribute("search_emp_name", emp_name);
//            request.setAttribute("list", list);
//            request.getRequestDispatcher("index.jsp").forward(request, response);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//    private void searchById(HttpServletRequest request, HttpServletResponse response)
//    {
//        int emp_id = Integer.parseInt(request.getParameter("emp_id"));
//        if (emp_id > 0)
//        {
//            EmployeeDAO dao = (EmployeeDAO) DAOFactory.createEmployeeDAO();
//            Employee emp = dao.findEmployeeById(emp_id);
//            request.setAttribute("employee", emp);
//            try
//            {
//                request.getRequestDispatcher("update.jsp").forward(request, response);
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//        }
//    }

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
//    private void searchByPage(HttpServletRequest request, HttpServletResponse response)
//    {
//        int currentPage = 1; // 当前页默认为第一页
//        String strpage = request.getParameter("currentPage"); // 获取前台传入当前页
//        if (strpage != null && !strpage.equals(""))
//        {
//            currentPage = Integer.parseInt(strpage) < 1 ? 1 : Integer.parseInt(strpage); // 将字符串转换成整型
//        }
//        String emp_name = request.getParameter("emp_name");
//        EmployeeDAO dao = (EmployeeDAO) DAOFactory.creatEmployeeDAO();
//        // 从UserDAO中获取所有用户信息
//        ArrayList<Employee> list = dao.findEmployeeByPage(currentPage, emp_name);
//        // 从UserDAO中获取总记录数
//        int allCount = dao.getAllCount();
//        // 从UserDAO中获取总页数
//        int allPageCount = dao.getAllPageCount();
//        // 从UserDAO中获取当前页
//        currentPage = dao.getCurrentPage();
//
//        // 存入request中
//        request.setAttribute("allEmployee", list);
//        request.setAttribute("allCount", allCount);
//        request.setAttribute("allPageCount", allPageCount);
//        request.setAttribute("currentPage", currentPage);
//        request.setAttribute("search_emp_name", emp_name);
//
//        try
//        {
//            request.getRequestDispatcher("index1.jsp").forward(request, response);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
}
