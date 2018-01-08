package com.zjsm.ctms.dao;

import java.util.ArrayList;

import com.zjsm.ctms.idao.DAOFactory;
import com.zjsm.ctms.model.Seat;
import com.zjsm.ctms.model.User;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ArrayList<Seat> list = new SeatDAO().findSeatByStudioId(1);
//		 
//		for(int i=0; i<list.size(); i++) {
//			System.out.println(list.get(i).getSeat_status());
//		}

//		Seat seat = new Seat();
//		seat.setSeat_id(10);
//		seat.setSeat_status(1);
//		SeatDAO dao = (SeatDAO) DAOFactory.createSeatDAO();
//		boolean result = dao.update(seat);
//		System.out.println(result);
		
		User info = new UserDAO().findUserByNo("8340539393");
		String path = info.getHead_path();
		System.out.println(path);
	}

}
