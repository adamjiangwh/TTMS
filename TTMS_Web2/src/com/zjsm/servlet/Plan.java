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

import com.zjsm.ctms.dao.ScheduleDAO;
import com.zjsm.ctms.model.Schedule;


//@WebServlet(urlPatterns = "/TTMS_Web2/Order")
public class Plan extends HttpServlet {
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
        if (method.equalsIgnoreCase("searchById"))
        	searchById(request, response);

	}
	
	private void searchById(HttpServletRequest request, HttpServletResponse response) {
		int studio_id = Integer.parseInt(request.getParameter("studio_id"));
		ArrayList<Schedule> list = new ScheduleDAO().findScheduleByStudioId(studio_id);
		request.setAttribute("list", list);
		request.setAttribute("studio_id", studio_id);
		try
        {
            request.getRequestDispatcher("home/theatreplan.jsp").forward(request, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
}
