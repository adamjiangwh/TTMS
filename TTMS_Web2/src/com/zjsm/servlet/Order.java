package com.zjsm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zjsm.ctms.dao.SaleDAO;
import com.zjsm.ctms.dao.SaleItemDAO;
import com.zjsm.ctms.idao.DAOFactory;
import com.zjsm.ctms.model.Sale;
import com.zjsm.ctms.model.SaleItem;




//@WebServlet(urlPatterns = "/TTMS_Web2/Order")
public class Order extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	
		response.setContentType("text/html;charset=UTF-8");

		String method = request.getParameter("method");
        if (method.equalsIgnoreCase("show"))
        	show(request, response);
        else if (method.equalsIgnoreCase("searchById"))
        	searchById(request, response);
        else if (method.equalsIgnoreCase("searchByPage"))
        	searchByPage(request, response);
        else if (method.equalsIgnoreCase("searchSaleByPage"))
        	searchSaleByPage(request, response);
	}
	
	private void show(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		List<Sale> info = new SaleDAO().findSaleAll();
		

		Iterator<Sale> itr = info.iterator();
		
		StringBuilder sb = new StringBuilder();
		
		//颜色选取
		String[] color = {"blue", "teal", "orange", "red", "green", "yell"};
		int index = 0;
		
		//sb.append("[");
		while (itr.hasNext()) {

			Sale list_sale = itr.next();
			
		
			String sale_ID = list_sale.getSale_ID();
			int emp_id = list_sale.getEmp_id();
			String time = list_sale.getSale_time().toString();
			BigDecimal payment = list_sale.getSale_payment();
			BigDecimal change = list_sale.getSale_change();
			String type = null;
			if (list_sale.getSale_type() == 1) {
				type = "销售单";
			} else if (list_sale.getSale_type() == -1) {
				type = "退款单";
			}
			String status = null;
			if(list_sale.getSale_status() == 0)
				status = "待付款";
			else if (list_sale.getSale_status() == 1)
				status = "已付款";
			
			
			sb.append("<div class=\"col-md-4\"><div class=\"panel panel-" + color[index] + "\">");
			sb.append("<div class=\"panel-heading dark-overlay\"><a href = \"Order?method=searchByPage&sale_ID="+sale_ID+"\" style=\"color:#FFFFFF\">" + "订单编号：" +sale_ID + "</a></div><div class=\"panel-body\">");
			sb.append("<p>" + "员工编号：" + emp_id + "<br>");
			sb.append("订单时间：" + time + "<br>");
			sb.append("付款金额：" + payment + "<br>");
			sb.append("找零金额：" + change+ "<br>");
			sb.append("订单类型：" + type+ "<br>");
			sb.append("订单状态：" + status+ "<br>");
			sb.append("</p></div></div></div><!--/.col-->");
			
			if(index<5) {	//颜色的下标
				index++;
			} else {
				index = 0;
			}
			
		}

		
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.write(sb.toString());
		//request.getRequestDispatcher("people.jsp").forward(request, response);
//		System.out.println(sb.toString());
	}
	
	private void searchById(HttpServletRequest request, HttpServletResponse response) {
		String sale_ID = request.getParameter("sale_ID");
        SaleDAO dao = (SaleDAO) DAOFactory.createSaleItemDAO();
        ArrayList<Sale> list = dao.findBySale_ID(sale_ID);
        request.setAttribute("list", list);
        try
        {
            request.getRequestDispatcher("home/orderitem.jsp").forward(request, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
	
	  private void searchByPage(HttpServletRequest request, HttpServletResponse response)
	    {
	        int currentPage = 1; // 当前页默认为第一页
	        String strpage = request.getParameter("currentPage"); // 获取前台传入当前页
	        if (strpage != null && !strpage.equals(""))
	        {
	            currentPage = Integer.parseInt(strpage) < 1 ? 1 : Integer.parseInt(strpage); // 将字符串转换成整型
	        }
	        String sale_ID = request.getParameter("sale_ID");
	        SaleItemDAO dao = (SaleItemDAO) DAOFactory.createSaleItemDAO();
	        // 从UserDAO中获取所有用户信息
	        ArrayList<SaleItem> list = dao.findSaleItemByPage(currentPage, sale_ID);
	        // 从UserDAO中获取总记录数
	        int allCount = dao.getAllCount();
	        // 从UserDAO中获取总页数
	        int allPageCount = dao.getAllPageCount();
	        // 从UserDAO中获取当前页
	        currentPage = dao.getCurrentPage();

	        // 存入request中
	        request.setAttribute("allSaleItem", list);
	        request.setAttribute("allCount", allCount);
	        request.setAttribute("allPageCount", allPageCount);
	        request.setAttribute("currentPage", currentPage);
	        request.setAttribute("search_sale_id", sale_ID);

	        try
	        {
	            request.getRequestDispatcher("home/orderitem.jsp").forward(request, response);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }
	  
	  
	  private void searchSaleByPage(HttpServletRequest request, HttpServletResponse response)
	    {
	        int currentPage = 1; // 当前页默认为第一页
	        String strpage = request.getParameter("currentPage"); // 获取前台传入当前页
	        if (strpage != null && !strpage.equals(""))
	        {
	            currentPage = Integer.parseInt(strpage) < 1 ? 1 : Integer.parseInt(strpage); // 将字符串转换成整型
	        }
	        String sale_ID = request.getParameter("sale_ID");
	        SaleDAO dao = (SaleDAO) DAOFactory.createSaleDao();
	        // 从UserDAO中获取所有用户信息
	        ArrayList<Sale> list = dao.findSaleByPage(currentPage, sale_ID);
	        // 从UserDAO中获取总记录数
	        int allCount = dao.getAllCount();
	        // 从UserDAO中获取总页数
	        int allPageCount = dao.getAllPageCount();
	        // 从UserDAO中获取当前页
	        currentPage = dao.getCurrentPage();

	        // 存入request中
	        request.setAttribute("allSale", list);
	        request.setAttribute("allCount", allCount);
	        request.setAttribute("allPageCount", allPageCount);
	        request.setAttribute("currentPage", currentPage);
	        request.setAttribute("search_sale_id", sale_ID);
	      
	        try
	        {
	            request.getRequestDispatcher("home/order.jsp").forward(request, response);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }
}
