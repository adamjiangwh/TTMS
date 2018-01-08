package com.zjsm.ctms.idao;

import com.zjsm.ctms.dao.EmployeeDAO;
import com.zjsm.ctms.dao.PlayDAO;
import com.zjsm.ctms.dao.SaleDAO;
import com.zjsm.ctms.dao.SaleItemDAO;
import com.zjsm.ctms.dao.ScheduleDAO;
import com.zjsm.ctms.dao.SeatDAO;
import com.zjsm.ctms.dao.StudioDAO;
import com.zjsm.ctms.dao.UserDAO;

public class DAOFactory
{
    public static IEmployee createEmployeeDAO()
    {
        return new EmployeeDAO();
    }
    
    public static IUser createUserDAO() {
    	return new UserDAO();
    }
    
    public static ISale createSaleDao() {
    	return new SaleDAO();
    }
    
    public static IPlay createPlayDAO() {
    	return new PlayDAO();
    }
    
    public static ISaleItem createSaleItemDAO() {
    	return new SaleItemDAO();
    }
    
    public static ISeat createSeatDAO() {
    	return new SeatDAO();
    }
    
    public static ISchedule createScheduleDAO() {
    	return new ScheduleDAO();
    }
    
    public static IStudio createStudioDAO() {
    	return new StudioDAO();
    }
}
