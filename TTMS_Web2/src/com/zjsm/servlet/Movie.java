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

import com.zjsm.ctms.dao.PlayDAO;

import com.zjsm.ctms.idao.DAOFactory;
import com.zjsm.ctms.model.Play;



/** 
* @Author : KiritoLiuSky
* @Name : Liu Yihao 
* @My QQ : 2502642350
* @Student's ID : 04153011
* @My E-mail : KiritoLiuyhSky@gmail.com
* @Date&Time : 2017年11月22日 下午5:34:12  
*/

//@WebServlet(urlPatterns = "/TTMS_Web2/Movie")
public class Movie extends HttpServlet {
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
		List<Play> info = new PlayDAO().findPlayAll();
		
		Iterator<Play> itr = info.iterator();
		StringBuilder sb = new StringBuilder();
		
		while (itr.hasNext()) {
			Play list_play = itr.next();
			/* play_id,int
			 * play_type_id,int  
			 * play_lang_id, int
			 * play_name, char
			 * play_introduction,char 
			 * play_image, char
			 * play_length, int
			 * play_ticket_price,decimal 
			 * play_status,int
			 * */
			int play_id = list_play.getPlay_id();
			int type_id = list_play.getPlay_type_id();
			int lang_id = list_play.getPlay_lang_id();
			String name = list_play.getPlay_name();
			String introduction = list_play.getPlay_introduction();
			String image = list_play.getPlay_image();
			int length = list_play.getPlay_length();
			BigDecimal ticket_price = list_play.getPlay_ticket_price();
			int status = list_play.getPlay_status();
			
			String type = null;
			if (5 == list_play.getPlay_type_id()) 
				type = "古装剧";
			else if (6 == list_play.getPlay_type_id()) 
				type = "动漫片";
			else if (7 == list_play.getPlay_type_id()) 
				type = "生活剧";
			else if (8 == list_play.getPlay_type_id()) 
				type = "恐怖片";
			else if (9 == list_play.getPlay_type_id()) 
				type = "战争片";
			else if (10 == list_play.getPlay_type_id()) 
				type = "科幻片";
			String lang = null;
			if (11 == list_play.getPlay_lang_id())
				lang = "国语";
			else if (12 == list_play.getPlay_lang_id())
				lang = "英语";
			String statuss = null;
			if(0 == list_play.getPlay_status())
				statuss = "待上架";
			else if (1 == list_play.getPlay_status())
				statuss = "已上架";
			else if (-1 == list_play.getPlay_status())
				statuss = "已下架";
			
			sb.append("<div class=\"col-sm-6 col-md-2\"><div class=\"thumbnail rela\"> <a href=\"Movie?method=searchById&play_id="+play_id+"\">");

//			<a href="PlayServlet?method=searchById&play_id=<%=list.get(i).getPlay_id()%>">
//			href="playServlet?method=searchById&emp_id=<%=list.get(i).getEmp_id()%>"
//			<a href=\"home/movdetails.jsp\">
			sb.append("<img src=\""+image+"\"alt=\"暂无海报\">");
			sb.append("<div class=\"caption\">"); 
			sb.append("<h3 class=\"name\">"+name+"</h3></div></a>");
//			sb.append("<div style=\"text-align:right;\" ><a onclick=\"delcfm('Movie?method=delete&play_id="+ play_id +"')\" class=\"pointer\">删除<i class=\"icon-delete-dustbin\"></i></a></div>");
			sb.append("<div style=\"text-align:right;\" ><span class=\"pointer\" data-toggle=\"modal\" data-target=\"#delcfmModal\"  onclick=\"delcfm('Movie?method=delete&play_id="+ play_id +"')\">删除<i class=\"icon-delete-dustbin\"></i></span></div>");
			sb.append("<label for=\"mbg3\" class=\"checkbox-img\">");
			sb.append("<input type=\"checkbox\" style=\"display: none;\" value=\"\" id=\"mbg3\" name=\"mymovie\" disabled=\"disabled\" >");
			sb.append("</label></div></div>");
			
		}
		//sb.deleteCharAt(sb.length()-1);
		
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
        int play_id = Integer.parseInt(request.getParameter("play_id"));
        PlayDAO dao = (PlayDAO) DAOFactory.createPlayDAO();
        Play play = dao.findById(play_id);
        request.setAttribute("play", play);
        
        int play_sta = play.getPlay_status();
        String play_status = null;
        if(play_sta == 1) {
        	play_status = "正在上映";
        } else if(play_sta == 0) {
        	play_status = "即将上映";
        } else if(play_sta == -1) {
        	play_status = "已下线";
        }
        request.setAttribute("play_status", play_status);
        
        try
        {
            request.getRequestDispatcher("home/movdetails.jsp").forward(request, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
	
	private void add(HttpServletRequest request, HttpServletResponse response)
    {
//		int no = Integer.parseInt(request.getParameter("play_id"));
		String play_name = request.getParameter("mname");
		int play_length  = Integer.parseInt(request.getParameter("mtime"));
		int play_type_id  = Integer.parseInt(request.getParameter("mtypeid"));
		String play_introduction = request.getParameter("mintroduction");
		int play_lang_id  = Integer.parseInt(request.getParameter("mlang"));
		BigDecimal play_ticket_price = new BigDecimal(request.getParameter("mprice"));
		String play_image = request.getParameter("mimgfile");
		
		String picnull = "home/img/movies/error.jpg";
		
		Play play = new Play();
		play.setPlay_name(play_name);
		play.setPlay_length(play_length);
		play.setPlay_type_id(play_type_id);
		play.setPlay_introduction(play_introduction);
		play.setPlay_lang_id(play_lang_id);
		play.setPlay_ticket_price(play_ticket_price);
		play.setPlay_status(0); 	//默认为0
//		play.setPlay_image("home/img/movies/"+play_image);
		
		if(play_image == null || play_image.equals("")) {
        	play.setPlay_image(picnull);
        	play_image = picnull;
        } else {
        	play_image = "home/img/movies/"+play_image;
        	play.setPlay_image(play_image);      	
        }
		
		PlayDAO dao = (PlayDAO) DAOFactory.createPlayDAO();
        boolean result = dao.insert(play);
        
        try
        {
            if (result)
                request.setAttribute("result", "添加成功!");
            else
                request.setAttribute("result", "添加失败!");
            request.setAttribute("play", play);
            request.getRequestDispatcher("Movie?method=searchByPage").forward(request, response);
        
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		
    }
	
	 private void update(HttpServletRequest request, HttpServletResponse response)
	 {
		 	int no = Integer.parseInt(request.getParameter("play_id"));
			String play_name = request.getParameter("dyname");
			int play_length  = Integer.parseInt(request.getParameter("dytime"));
			int play_type_id  = Integer.parseInt(request.getParameter("dytypeid"));
			String play_introduction = request.getParameter("dyintroduction");
			int play_lang_id  = Integer.parseInt(request.getParameter("dylang"));
			BigDecimal play_ticket_price = new BigDecimal(request.getParameter("dyprice"));
			String play_status = request.getParameter("dystatus");
			String play_image = request.getParameter("dyimgfile");
			
			
			
			Play movie = new PlayDAO().findById(no);
	        int play_id = movie.getPlay_id();
	        
	        Play info = new PlayDAO().findById(play_id);
			String picnull = info.getPlay_image();
			
			Play play = new Play();
			play.setPlay_id(play_id);
			play.setPlay_name(play_name);
			play.setPlay_length(play_length);
			play.setPlay_type_id(play_type_id);
			play.setPlay_introduction(play_introduction);
			play.setPlay_lang_id(play_lang_id);
			play.setPlay_ticket_price(play_ticket_price);
			
			if(play_status.equals("正在上映")) {				
				play.setPlay_status(1); 
			} else if(play_status.equals("即将上映")) {
				play.setPlay_status(0); 
			} else if(play_status.equals("已下线")) {
				play.setPlay_status(-1); 
			}
//			play.setPlay_image("home/img/movies/"+play_image);
			
			 if(play_image == null || play_image.equals("")) {
		        	play.setPlay_image(picnull);
		        	play_image = picnull;
		        } else {
		        	play_image = "home/img/movies/"+play_image;
		        	play.setPlay_image(play_image);      	
		        }
			
			PlayDAO dao = (PlayDAO) DAOFactory.createPlayDAO();
	        boolean result = dao.update(play);
	        
	        
	        
	        try
	        {
	            if (result)
	                request.setAttribute("result", "更新成功!");
	            else
	                request.setAttribute("result", "更新失败!");
	            request.setAttribute("play", play);
	            request.setAttribute("play_status", play_status);
	            request.getRequestDispatcher("home/movdetails.jsp").forward(request, response);
	        
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        
	 }
	 
	 private void delete(HttpServletRequest request, HttpServletResponse response)
	    {
	        boolean result = false;
	        int play_id = Integer.parseInt(request.getParameter("play_id"));
	        if (play_id > 0)
	        {
	            PlayDAO dao = (PlayDAO) DAOFactory.createPlayDAO();
	            result = dao.delete(play_id);
	            
	            try
	            {
	                if (result)
	                    request.setAttribute("result", "删除成功!");
	                else
	                    request.setAttribute("result", "删除失败!");
	                response.sendRedirect("Movie?method=searchByPage");
	            }
	            catch (Exception e)
	            {
	                e.printStackTrace();
	            }
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
	        String play_name = request.getParameter("play_name");
	        PlayDAO dao = (PlayDAO) DAOFactory.createPlayDAO();
	        // 从PlayDAO中获取所有用户信息
	        ArrayList<Play> list = dao.findStudioByPage(currentPage, play_name);
	        // 从PlayDAO中获取总记录数
	        int allCount = dao.getAllCount();
	        // 从PlayDAO中获取总页数
	        int allPageCount = dao.getAllPageCount();
	        // 从PlayDAO中获取当前页
	        currentPage = dao.getCurrentPage();

	        // 存入request中
	        request.setAttribute("allPlay", list);
	        request.setAttribute("allCount", allCount);
	        request.setAttribute("allPageCount", allPageCount);
	        request.setAttribute("currentPage", currentPage);
	        request.setAttribute("search_play_name", play_name);

	        try
	        {
	            request.getRequestDispatcher("home/movies.jsp").forward(request, response);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }

}
 