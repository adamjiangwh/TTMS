package com.zjsm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.el.lang.ELArithmetic.BigDecimalDelegate;

import com.zjsm.ctms.dao.SaleItemDAO;
import com.zjsm.ctms.dao.SeatDAO;
import com.zjsm.ctms.dao.StudioDAO;
import com.zjsm.ctms.idao.DAOFactory;
import com.zjsm.ctms.model.SaleItem;
import com.zjsm.ctms.model.Seat;
import com.zjsm.ctms.model.Studio;

//@WebServlet(urlPatterns = "/TTMS_Web2/Movie")
public class Theatre extends HttpServlet {
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
        else if (method.equalsIgnoreCase("add"))
            add(request, response);
        else if (method.equalsIgnoreCase("delete"))
            delete(request, response);
        else if (method.equalsIgnoreCase("update"))
            update(request, response);
        else if (method.equalsIgnoreCase("searchById"))
        	searchById(request, response);
        else if (method.equalsIgnoreCase("searchByPage"))
        	searchByPage(request, response);

		
	}
	private void show(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Studio> info = new StudioDAO().findStudioAll();
		
		Iterator<Studio> itr = info.iterator();
		StringBuilder sb = new StringBuilder();
		
		while (itr.hasNext()) {
			Studio list_studio = itr.next();
			int studio_id = list_studio.getStu_id();
			String name = list_studio.getStu_name();
			int row_count = list_studio.getStu_row_count();
			int col_count = list_studio.getStu_col_count();
			String introduction = list_studio.getStu_introduction();
			String stu_path = list_studio.getStu_path();
			String stu_flag = null;
			
//			if (list_studio.getStu_flag()== 0) {
//				stu_flag = "尚未生成座位";
//			}
//			else if(list_studio.getStu_flag()== 1) {
//				stu_flag = "已安排座位";
//			}
			if(list_studio.getStu_flag()==-1) {	//影厅不可用
				sb.append("<div class=\"col-sm-6 col-md-3\"><div class=\"thumbnail\"><a onclick=\"javascript:win.alert('系统提示','影厅不可用！')\">");
			} else {
				sb.append("<div class=\"col-sm-6 col-md-3\"><div class=\"thumbnail\"><a href=\"Plan?method=searchById&studio_id="+studio_id+"\">");				
			}
			sb.append("<img src=\""+stu_path+"\" alt=\"studioImage\"></a><div class=\"caption stdbg clearfix\">");
			sb.append("<h3 class=\"stdname\">"+name+"</h3><div class=\"operaicon\">");
			sb.append("<span class=\"pointer\"><a href=\"Theatre?method=searchById&studio_id="+studio_id+"\"><i class=\"icon-edit-pencil\"></i></a></span>");
			sb.append("&nbsp;<span class=\"pointer\" data-toggle=\"modal\" data-target=\"#delcfmModal\"  onclick=\"delcfm('Theatre?method=delete&studio_id="+studio_id+"')\"><i class=\"icon-delete-dustbin\"></i></span>");
			sb.append("</div></div></div></div>");
			sb.append("</label></a></div></div>");
		}
		
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.write(sb.toString());
	}
	
	private void searchById(HttpServletRequest request, HttpServletResponse response)
    {
        int studio_id = Integer.parseInt(request.getParameter("studio_id"));
        StudioDAO dao = (StudioDAO) DAOFactory.createStudioDAO();
        Studio studio = dao.findById(studio_id);
        request.setAttribute("studio", studio);
        
        String studio_name = studio.getStu_name();
        int studio_row_count = studio.getStu_row_count();
        int studio_col_count = studio.getStu_col_count();
        String studio_introduction = studio.getStu_introduction();
        String studio_path = studio.getStu_path();
        
        int stu_flag = studio.getStu_flag();
        String studio_flag = null;
        if(stu_flag == 1) {
        	studio_flag = "已安排座位";
        } else if(stu_flag == 0) {
        	studio_flag = "尚未生成座位";
        } else if(stu_flag == -1) {
        	studio_flag = "影厅不可用";
        }
        
        request.setAttribute("studio_name", studio_name);
        request.setAttribute("studio_row_count", studio_row_count);
        request.setAttribute("studio_col_count", studio_col_count);
        request.setAttribute("studio_introduction", studio_introduction);
        request.setAttribute("studio_flag", studio_flag);
        request.setAttribute("studio_path", studio_path);
        request.setAttribute("studio_id", studio_id);
        
        try
        {
            request.getRequestDispatcher("home/modifyTheatre.jsp").forward(request, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
	
	private void add(HttpServletRequest request, HttpServletResponse response)
    {	
		String studio_name = request.getParameter("tname");
		int studio_row_count  = Integer.parseInt(request.getParameter("trowcount"));
		int studio_col_count  = Integer.parseInt(request.getParameter("tcolcount"));
		String studio_introduction = request.getParameter("tintroduction");
		String studio_path = request.getParameter("timgfile");

		String picnull = "home/img/studio/error.jpg";
		
		Studio studio = new Studio();
		studio.SetStu_name(studio_name);
		studio.SetStu_row_count(studio_row_count);
		studio.SetStu_col_count(studio_col_count);
		studio.SetStu_flag(0); //默认为0
		studio.SetStu_introduction(studio_introduction);
//		studio.SetStu_path(studio_path);

		if(studio_path == null || studio_path.equals("")) {
        	studio.SetStu_path(picnull);
        	studio_path = picnull;
        } else {
        	studio_path = "home/img/studio/"+studio_path;
        	studio.SetStu_path(studio_path);      	
        }
		
		StudioDAO dao = (StudioDAO) DAOFactory.createStudioDAO();
        boolean result = dao.insert(studio);
        
        try
        {
            if (result)
                request.setAttribute("result", "添加成功!");
            else
                request.setAttribute("result", "添加失败!");
            request.setAttribute("studio", studio);
            request.getRequestDispatcher("Theatre?method=searchByPage").forward(request, response);
        
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		
    }
	
	 private void update(HttpServletRequest request, HttpServletResponse response)
	 {
		 	int studio_id = Integer.parseInt(request.getParameter("studio_id"));
			String studio_name = request.getParameter("ytname");
			int studio_row_count = Integer.parseInt(request.getParameter("ytrowcount"));
			int studio_col_count = Integer.parseInt(request.getParameter("ytcolcount"));
			String studio_flag = request.getParameter("ytinflag");
			String studio_introduction = request.getParameter("ytintroduction");
			String studio_path = request.getParameter("ytimgfile");

	        Studio info = new StudioDAO().findById(studio_id);
			String picnull = info.getStu_path();

			Studio studio = new Studio();
			studio.SetStu_id(studio_id);
			studio.SetStu_name(studio_name);
			studio.SetStu_row_count(studio_row_count);
			studio.SetStu_col_count(studio_col_count);
			studio.SetStu_introduction(studio_introduction);
			
			int count = new SeatDAO().findStudioId(studio_id);	//studio_id在seat表中的行数
			
			if(studio_flag.equals("已安排座位")) {
				if(count == 0) {	// 判断座位是否存在
					for(int i=1; i<=studio_row_count; i++) {
						for(int j=1; j<=studio_col_count; j++) {
							Seat seat = new Seat();
							seat.setStudio_id(studio_id);
							seat.setSeat_row(i);
							seat.setSeat_col(j);
							seat.setSeat_status(1);
							SeatDAO sDao = (SeatDAO) DAOFactory.createSeatDAO();
							boolean seatResult = sDao.insert(seat);
						}
					}
				}
				studio.SetStu_flag(1);
			} else if(studio_flag.equals("尚未生成座位")) {
				//删除座位
				List<Seat> list_seat = new SeatDAO().findSeatByStudioId(studio_id);
				Iterator<Seat> itr = list_seat.iterator();
				while(itr.hasNext()) {
					Seat seat_del = itr.next();
					int seat_id = seat_del.getSeat_id();
					new SeatDAO().delete(seat_id);
				}
				studio.SetStu_flag(0);
			} else if(studio_flag.equals("影厅不可用")) {
				studio.SetStu_flag(-1);
			}
			
			 if(studio_path == null || studio_path.equals("")) {
		        	studio.SetStu_path(picnull);
		        	studio_path = picnull;
		        } else {
		        	studio_path = "home/img/studio/"+studio_path;
		        	studio.SetStu_path(studio_path);      	
		        }
			
			StudioDAO dao = (StudioDAO) DAOFactory.createStudioDAO();
	        boolean result = dao.update(studio);
	        
	        
	        
	        try
	        {
	            if (result)
	                request.setAttribute("result", "更新成功!");
	            else
	                request.setAttribute("result", "更新失败!");
	            request.setAttribute("studio", studio);

	            
	            request.setAttribute("studio_name", studio_name);
	            request.setAttribute("studio_row_count", studio_row_count);
	            request.setAttribute("studio_col_count", studio_col_count);
	            request.setAttribute("studio_introduction", studio_introduction);
	            request.setAttribute("studio_path", studio_path);
	            request.setAttribute("studio_flag", studio_flag);
	            request.setAttribute("studio_id", studio_id);
	            request.getRequestDispatcher("home/modifyTheatre.jsp").forward(request, response);
	        
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        
	 }
	 
	 private void delete(HttpServletRequest request, HttpServletResponse response)
	    {
	        boolean result = false;
	        int studio_id = Integer.parseInt(request.getParameter("studio_id"));
	        if (studio_id > 0)
	        {
	            StudioDAO dao = (StudioDAO) DAOFactory.createStudioDAO();
	            result = dao.delete(studio_id);
	            
	            try
	            {
	                if (result)
	                    request.setAttribute("result", "成功!");
	                else
	                    request.setAttribute("result", "失败!");
	                response.sendRedirect("Theatre?method=searchByPage");
	                return;
	            }
	            catch (Exception e)
	            {
	                e.printStackTrace();
	            }
	            show(request, response);
	            // searchByPage(request, response);
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
	        String studio_name = request.getParameter("studio_name");
	        StudioDAO dao = (StudioDAO) DAOFactory.createStudioDAO();
	        // 从UserDAO中获取所有用户信息
	        ArrayList<Studio> list = dao.findStudioByPage(currentPage, studio_name);
	        // 从UserDAO中获取总记录数
	        int allCount = dao.getAllCount();
	        // 从UserDAO中获取总页数
	        int allPageCount = dao.getAllPageCount();
	        // 从UserDAO中获取当前页
	        currentPage = dao.getCurrentPage();

	        // 存入request中
	        request.setAttribute("allStudio", list);
	        request.setAttribute("allCount", allCount);
	        request.setAttribute("allPageCount", allPageCount);
	        request.setAttribute("currentPage", currentPage);
	        request.setAttribute("search_stu_id", studio_name);
	        try
	        {
	            request.getRequestDispatcher("home/theatre.jsp").forward(request, response);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }
}
 