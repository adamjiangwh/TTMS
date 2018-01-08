package com.zjsm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zjsm.ctms.dao.SeatDAO;
import com.zjsm.ctms.dao.StudioDAO;
import com.zjsm.ctms.idao.DAOFactory;
import com.zjsm.ctms.model.Seat;
import com.zjsm.ctms.model.Studio;

//@WebServlet(urlPatterns = "/TTMS_Web2/Place")
public class Place extends HttpServlet {
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
		if (method.equalsIgnoreCase("searchByStudioId"))
			searchByStudioId(request, response);
		else if (method.equalsIgnoreCase("update"))
			update(request, response);
	}

	private void searchByStudioId(HttpServletRequest request, HttpServletResponse response) {
		int studio_id = Integer.parseInt(request.getParameter("studio_id"));
		Studio info = new StudioDAO().findById(studio_id);
		request.setAttribute("info", info);
		request.setAttribute("studio_id", studio_id);

		try {
			request.getRequestDispatcher("home/seat.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		String ids = request.getParameter("ids");
		String status = request.getParameter("status");
//		int studio_id = 2;
//		System.out.println(ids);
//		System.out.println(status);
		String[] idlist = ids.split(",");
		String[] split = status.split(",");
		
		for(int i=0;i<idlist.length;i++) {
			if(idlist[i]!=null && !"".equals(idlist[i]) &&!"null".equals(idlist[i])
					&& split[i]!=null && !"".equals(split[i]) &&!"null".equals(split[i])) {				
//			System.out.println(idlist[i]+" "+split[i]+" "+studio_id);
				Seat seat = new Seat();
				seat.setSeat_id(Integer.parseInt(idlist[i]));
				seat.setSeat_status(Integer.parseInt(split[i]));
				SeatDAO dao = (SeatDAO) DAOFactory.createSeatDAO();
				boolean result = dao.update(seat);
//				System.out.println(result);
			}
		}
	}
}