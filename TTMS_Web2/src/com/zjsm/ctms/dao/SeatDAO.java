package com.zjsm.ctms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.zjsm.ctms.idao.ISeat;
import com.zjsm.ctms.model.Seat;
import com.zjsm.util.ConnectionManager;

public class SeatDAO implements ISeat
{
    /**
     * 存储用户信息
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean insert(Seat seat)
    {
        boolean result = false;
        if(seat == null)
            return result;

        // 获取Connection
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            String sql = "insert into seat(studio_id, seat_row, seat_column, seat_status) values(?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, seat.getStudio_id());
            pstmt.setInt(2, seat.getSeat_row());
            pstmt.setInt(3, seat.getSeat_col());
            pstmt.setInt(4, seat.getSeat_status());

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
    public boolean delete(int seatId)
    {
        boolean result = false;
        if(seatId == 0)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            // 删除子某个用户
            String sql = "delete from seat where seat_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, seatId);
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
    public boolean update(Seat seat)
    {
        boolean result = false;
        if(seat == null)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            String sql = "update seat set seat_status=? where seat_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, seat.getSeat_status());
            pstmt.setInt(2, seat.getSeat_id());

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
    public ArrayList<Seat> findSeatAll()
    {
        ArrayList<Seat> list = new ArrayList<Seat>();
        Seat info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from seat");
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                info = new Seat();

                info.setSeat_id(rs.getInt("seat_id"));
                info.setStudio_id(rs.getInt("studio_id"));
                info.setSeat_row(rs.getInt("seat_row"));
                info.setSeat_col(rs.getInt("seat_column"));
                info.setSeat_status(rs.getInt("seat_status"));
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
    public Seat findSeatById(int seatId)
    {
    	Seat info = null;
        if(seatId == 0)
            return info;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from seat where seat_id=?");
            pstmt.setInt(1, seatId);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                // 如果有值的话再实例化
                info = new Seat();
                info.setSeat_id(rs.getInt("seat_id"));
                info.setStudio_id(rs.getInt("studio_id"));
                info.setSeat_row(rs.getInt("seat_row"));
                info.setSeat_col(rs.getInt("seat_column"));
                info.setSeat_status(rs.getInt("seat_status"));
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
    public ArrayList<Seat> findSeatByStudioId(int studioId)
    {
    	Seat info = null;
        if(studioId == 0)
            return null;

        ArrayList<Seat> list = new ArrayList<Seat>();
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from seat where studio_id=?");
            pstmt.setInt(1, studioId);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                // 如果有值的话再实例化
                info = new Seat();
                info.setSeat_id(rs.getInt("seat_id"));
                info.setStudio_id(rs.getInt("studio_id"));
                info.setSeat_row(rs.getInt("seat_row"));
                info.setSeat_col(rs.getInt("seat_column"));
                info.setSeat_status(rs.getInt("seat_status"));
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
     * 判断是否存在studio_id
     * @return 所在行数
     */
    @SuppressWarnings("finally")
    public int findStudioId(int studioId)
    {
    	int count = 0;
        if(studioId == -1)
            return -1;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select count(*) from seat where studio_id=?");
            pstmt.setInt(1, studioId);
            rs=pstmt.executeQuery();
            if(rs.next()) {
            	count=rs.getInt(1);
//            	System.out.println("count:"+count);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstmt, con);
            return count;
        }
    }
}