package com.zjsm.ctms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.zjsm.ctms.idao.ISchedule;
import com.zjsm.ctms.model.Schedule;
import com.zjsm.util.ConnectionManager;

public class ScheduleDAO implements ISchedule {
    /**
     * 存储用户信息
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean insert(Schedule schedule)
    {
        boolean result = false;
        if(schedule == null)
            return result;

        // 获取Connection
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
        	SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sql = "insert into schedule(studio_id, play_id, sched_time, sched_ticket_price) values(?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, schedule.getStudio_id());
            pstmt.setInt(2, schedule.getPlay_id());
            pstmt.setString(3, s.format(schedule.getSched_time()));
            pstmt.setBigDecimal(4, schedule.getSched_ticket_price());

            pstmt.executeUpdate();
            result = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            // 关闭连接
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    /**
     * 删除用户(通过employeeId)
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean delete(int scheduleId)
    {
        boolean result = false;
        if(scheduleId == 0)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            // 删除子某个用户
            String sql = "delete from schedule where sched_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, scheduleId);
            pstmt.executeUpdate();
            ConnectionManager.close(null, pstmt, con);

            result = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            // 关闭连接
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    /**
     * 修改用户信息
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean update(Schedule schedule)
    {
        boolean result = false;
        if(schedule == null)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
        	SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sql = "update schedule set studio_id=?,play_id=?,sched_time=?,sched_ticket_price=? where sched_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, schedule.getStudio_id());
            pstmt.setInt(2, schedule.getPlay_id());
            pstmt.setString(3, s.format(schedule.getSched_time()));
            pstmt.setBigDecimal(4, schedule.getSched_ticket_price());
            pstmt.setInt(5, schedule.getSched_id());

            pstmt.executeUpdate();
            result = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            // 关闭连接
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    /**
     * 获取所有用户信息(一般用于和界面交互)
     * @return Employee列表
     */
    @SuppressWarnings("finally")
    public ArrayList<Schedule> findScheduleAll()
    {
        ArrayList<Schedule> list = new ArrayList<Schedule>();
        Schedule info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from schedule");
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                info = new Schedule();

                info.setSched_id(rs.getInt("sched_id"));
                info.setStudio_id(rs.getInt("studio_id"));
                info.setPlay_id(rs.getInt("play_id"));
                info.setSched_time(rs.getTimestamp("sched_time"));
                new java.sql.Timestamp(new java.util.Date().getTime());
                info.setSched_ticket_price(rs.getBigDecimal("sched_ticket_price"));
                // 加入列表
                list.add(info);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstmt, con);
            return list;
        }
    }


    /**
     * 根据employee_id获取用户信息(一般用于数据内部关联操作)
     * @return 用户
     */
    @SuppressWarnings("finally")
    public Schedule findScheduleById(int schedId)
    {
    	Schedule info = null;
        if(schedId == 0)
            return info;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from schedule where sched_id=?");
            pstmt.setInt(1, schedId);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                // 如果有值的话再实例化
                info = new Schedule();
                info.setSched_id(rs.getInt("sched_id"));
                info.setStudio_id(rs.getInt("studio_id"));
                info.setPlay_id(rs.getInt("play_id"));
                info.setSched_time(rs.getTimestamp("sched_time"));
                new java.sql.Timestamp(new java.util.Date().getTime());
                info.setSched_ticket_price(rs.getBigDecimal("sched_ticket_price"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstmt, con);
            return info;
        }
    }
    
    /**
     * 根据employee_id获取用户信息(一般用于数据内部关联操作)
     * @return 用户
     */
    @SuppressWarnings("finally")
    public ArrayList<Schedule> findScheduleByStudioId(int studioId)
    {
    	Schedule info = null;
        if(studioId == 0)
            return null;

        ArrayList<Schedule> list = new ArrayList<Schedule>();
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from schedule where studio_id=?");
            pstmt.setInt(1, studioId);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                // 如果有值的话再实例化
                info = new Schedule();
                info.setSched_id(rs.getInt("sched_id"));
                info.setStudio_id(rs.getInt("studio_id"));
                info.setPlay_id(rs.getInt("play_id"));
                info.setSched_time(rs.getTimestamp("sched_time"));
                new java.sql.Timestamp(new java.util.Date().getTime());
                info.setSched_ticket_price(rs.getBigDecimal("sched_ticket_price"));
                list.add(info);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstmt, con);
            return list;
        }
    }
}
