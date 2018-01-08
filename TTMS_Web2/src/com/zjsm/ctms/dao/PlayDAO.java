package com.zjsm.ctms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zjsm.ctms.idao.IPlay;
import com.zjsm.ctms.model.Play;
import com.zjsm.util.ConnectionManager;


public class PlayDAO implements IPlay{
	public static final int PAGE_SIZE = 6; // 每页显示条数
    private int allCount; // 数据库中条数
    private int allPageCount; // 总页数
    private int currentPage; // 当前页

    public int getAllCount()
    {
        return allCount;
    }

    public int getAllPageCount()
    {
        return allPageCount;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    @SuppressWarnings("finally")
    public ArrayList<Play> findStudioByPage(int cPage, String play_name)
    {
        currentPage = cPage;
        ArrayList<Play> list = new ArrayList<Play>();

        if (play_name == null || play_name.equals(""))
        {
        	play_name = "";
        }
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取记录总数
            String sql1 = "select count(play_id) as AllRecord from play where play_name like ?";
            conn = ConnectionManager.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1, "%" + play_name + "%");
            rs = pstmt.executeQuery();
            if (rs.next())
                allCount = rs.getInt("AllRecord");
            rs.close();
            pstmt.close();

            // 记算总页数
            allPageCount = (allCount + PAGE_SIZE - 1) / PAGE_SIZE;

            // 如果当前页数大于总页数，则赋值为总页数
            if (allPageCount > 0 && currentPage > allPageCount)
                currentPage = allPageCount;

            // 获取第currentPage页数据
            String sql2 = "select * from play where play_name like ? limit ?,?";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, "%" + play_name + "%");
            pstmt.setInt(2, PAGE_SIZE * (currentPage - 1));
            pstmt.setInt(3, PAGE_SIZE);
            rs = pstmt.executeQuery();
            Play info = null;
            while (rs.next())
            {
            	info = new Play();
                info.setPlay_id(rs.getInt("play_id"));
                info.setPlay_type_id(rs.getInt("play_type_id"));
                info.setPlay_lang_id(rs.getInt("play_lang_id"));
                info.setPlay_name(rs.getString("play_name"));
                info.setPlay_introduction(rs.getString("play_introduction"));
                info.setPlay_image(rs.getString("play_image"));
                info.setPlay_length(rs.getInt("play_length"));
                info.setPlay_ticket_price(rs.getBigDecimal("play_ticket_price"));
                info.setPlay_status(rs.getInt("play_status"));

                // 将该用户信息插入列表
                list.add(info);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstmt, conn);
            return list;
        }
    }
        
	/**
     * 存储用户信息
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean insert(Play play)
    {
        boolean result = false;
        if(play == null)
            return result;

        // 获取Connection
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            String sql = "insert into Play(play_type_id, play_lang_id, play_name, play_introduction, play_image, play_length, play_ticket_price, play_status) values(?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, play.getPlay_type_id());
            pstmt.setInt(2, play.getPlay_lang_id());
            pstmt.setString(3, play.getPlay_name());
            pstmt.setString(4, play.getPlay_introduction());
            pstmt.setString(5, play.getPlay_image());
            pstmt.setInt(6, play.getPlay_length());
            pstmt.setBigDecimal(7, play.getPlay_ticket_price());
            pstmt.setInt(8, play.getPlay_status());

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
     * 删除用户(通过playId)
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean delete(int playId)
    {
        boolean result = false;
        if(playId == 0)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            // 删除子某个用户
            String sql = "delete from play where play_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, playId);
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
    public boolean update(Play play)
    {
        boolean result = false;
        if(play == null)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            String sql = "update play set play_type_id=?, play_lang_id=?, play_name=?, play_introduction=?, play_image=?, play_length=?, play_ticket_price=?, play_status=? where play_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, play.getPlay_type_id());
            pstmt.setInt(2, play.getPlay_lang_id());
            pstmt.setString(3, play.getPlay_name());
            pstmt.setString(4, play.getPlay_introduction());
            pstmt.setString(5, play.getPlay_image());
            pstmt.setInt(6, play.getPlay_length());
            pstmt.setBigDecimal(7, play.getPlay_ticket_price());
            pstmt.setInt(8, play.getPlay_status());
            pstmt.setInt(9, play.getPlay_id());
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
    public ArrayList<Play> findPlayAll()
    {
        ArrayList<Play> list = new ArrayList<Play>();
        Play info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from play");
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                info = new Play();

                info.setPlay_id(rs.getInt("play_id"));
                info.setPlay_type_id(rs.getInt("play_type_id"));
                info.setPlay_lang_id(rs.getInt("play_lang_id"));
                info.setPlay_name(rs.getString("play_name"));
                info.setPlay_introduction(rs.getString("play_introduction"));
                info.setPlay_image(rs.getString("play_image"));
                info.setPlay_length(rs.getInt("play_length"));
                info.setPlay_ticket_price(rs.getBigDecimal("play_ticket_price"));
                info.setPlay_status(rs.getInt("play_status"));

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
     * 根据用户名获取用户信息(一般用于和界面交互)
     * @return Employee列表
     */
    @SuppressWarnings("finally")
    public ArrayList<Play> findPlayByName(String playName)
    {
        if(playName == null || playName.equals(""))
            return null;

        ArrayList<Play> list = new ArrayList<Play>();
        Play info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据:模糊查询
            pstmt = con.prepareStatement("select * from play where play_name like ?");
            pstmt.setString(1, "%" + playName + "%");// 拼接模糊查询串
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                // 如果有值的话再实例化
                info = new Play();
                info.setPlay_id(rs.getInt("play_id"));
                info.setPlay_type_id(rs.getInt("play_type_id"));
                info.setPlay_lang_id(rs.getInt("play_lang_id"));
                info.setPlay_name(rs.getString("play_name"));
                info.setPlay_introduction(rs.getString("play_introduction"));
                info.setPlay_image(rs.getString("play_image"));
                info.setPlay_length(rs.getInt("play_length"));
                info.setPlay_ticket_price(rs.getBigDecimal("play_ticket_price"));
                info.setPlay_status(rs.getInt("play_status"));
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
    public ArrayList<Play> findPlayById(int playId)
    {
        Play info = null;
        if(playId == 0)
            return null;

        ArrayList<Play> list = new ArrayList<Play>();
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from play where play_id=?");
            pstmt.setInt(1, playId);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                // 如果有值的话再实例化
                info = new Play();
                info.setPlay_id(rs.getInt("play_id"));
                info.setPlay_type_id(rs.getInt("play_type_id"));
                info.setPlay_lang_id(rs.getInt("play_lang_id"));
                info.setPlay_name(rs.getString("play_name"));
                info.setPlay_introduction(rs.getString("play_introduction"));
                info.setPlay_image(rs.getString("play_image"));
                info.setPlay_length(rs.getInt("play_length"));
                info.setPlay_ticket_price(rs.getBigDecimal("play_ticket_price"));
                info.setPlay_status(rs.getInt("play_status"));
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
     * 根据用户名获取用户信息(一般用于数据内部关联操作)
     * @return 
     * @return 用户
     */
    @SuppressWarnings("finally")
    public Play findById(int playId)
    {
        Play info = null;
        if(playId == 0 )
            return info;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from play where play_id=?");
            pstmt.setInt(1, playId);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                // 如果有值的话再实例化
            	info = new Play();
            	info.setPlay_id(rs.getInt("play_id"));
                info.setPlay_type_id(rs.getInt("play_type_id"));
                info.setPlay_lang_id(rs.getInt("play_lang_id"));
                info.setPlay_name(rs.getString("play_name"));
                info.setPlay_introduction(rs.getString("play_introduction"));
                info.setPlay_image(rs.getString("play_image"));
                info.setPlay_length(rs.getInt("play_length"));
                info.setPlay_ticket_price(rs.getBigDecimal("play_ticket_price"));
                info.setPlay_status(rs.getInt("play_status"));
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

}
 