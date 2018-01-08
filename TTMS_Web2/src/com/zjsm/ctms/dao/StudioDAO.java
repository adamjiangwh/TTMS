package com.zjsm.ctms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zjsm.ctms.idao.IStudio;
import com.zjsm.ctms.model.Studio;
import com.zjsm.util.ConnectionManager;

public class StudioDAO implements IStudio {
    public static final int PAGE_SIZE = 8; // 每页显示条数
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
    public ArrayList<Studio> findStudioByPage(int cPage, String studio_name)
    {
        currentPage = cPage;
        ArrayList<Studio> list = new ArrayList<Studio>();

        // 若未指定查找某人，则默认全查
        if (null == studio_name || studio_name.equals("null"))
        {
        	studio_name = "";
        }
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取记录总数
            String sql1 = "select count(studio_id) as AllRecord from studio where studio_name like ?";
            conn = ConnectionManager.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1, "%" + studio_name + "%");
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
            String sql2 = "select * from studio where studio_id like ? limit ?,?";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, "%" + studio_name + "%");
            pstmt.setInt(2, PAGE_SIZE * (currentPage - 1));
            pstmt.setInt(3, PAGE_SIZE);
            rs = pstmt.executeQuery();
            Studio info = null;
            while (rs.next())
            {
            	info = new Studio();
                info.SetStu_id(rs.getInt("studio_id"));
                info.SetStu_name(rs.getString("studio_name"));
                info.SetStu_row_count(rs.getInt("studio_row_count"));
                info.SetStu_col_count(rs.getInt("studio_col_count"));
                info.SetStu_introduction(rs.getString("studio_introduction"));
                info.SetStu_flag(rs.getInt("studio_flag"));
                info.SetStu_path(rs.getString("studio_path"));

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
    public boolean insert(Studio stu)
    {
        boolean result = false;
        if(stu == null)
            return result;
        // 获取Connection
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            String sql = "insert into studio(studio_id, studio_name, studio_row_count, studio_col_count, studio_introduction,studio_flag,studio_path) values(?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, stu.getStu_id());
            pstmt.setString(2, stu.getStu_name());
            pstmt.setInt(3, stu.getStu_row_count());
            pstmt.setInt(4, stu.getStu_row_count());
            pstmt.setString(5, stu.getStu_introduction());
            pstmt.setInt(6, stu.getStu_flag());
            pstmt.setString(7, stu.getStu_path());
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
     * 删除放映厅(通过studioId)
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean delete(int studioId)
    {
        boolean result = false;
        if(studioId == 0)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            // 删除子某个用户
            String sql = "delete from studio where studio_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, studioId);
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
     * 修改放映厅信息
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean update(Studio studio)
    {
        boolean result = false;
        if(studio == null)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
           String sql = "update studio set studio_name=?,studio_row_count=?,studio_col_count=?,studio_introduction=?,studio_flag=?,studio_path=? where studio_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, studio.getStu_name());
            pstmt.setInt(2, studio.getStu_row_count());
            pstmt.setInt(3, studio.getStu_col_count());
            pstmt.setString(4, studio.getStu_introduction());
            pstmt.setInt(5, studio.getStu_flag());
            pstmt.setString(6, studio.getStu_path());
            pstmt.setInt(7, studio.getStu_id());

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
     * 获取所有放映厅信息(一般用于和界面交互)
     * @return Studio列表
     */
    @SuppressWarnings("finally")
    public ArrayList<Studio> findStudioAll()
    {
        ArrayList<Studio> list = new ArrayList<Studio>();
        Studio info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from studio");
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                info = new Studio();

                info.SetStu_id(rs.getInt("studio_id"));
                info.SetStu_name(rs.getString("studio_name"));
                info.SetStu_row_count(rs.getInt("studio_row_count"));
                info.SetStu_col_count(rs.getInt("studio_col_count"));
                info.SetStu_introduction(rs.getString("studio_introduction"));
                info.SetStu_flag(rs.getInt("studio_flag"));
                info.SetStu_path(rs.getString("studio_path"));
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
     * 根据放映厅名称获取放映厅信息(一般用于和界面交互)
     * @return Studio列表
     */
    @SuppressWarnings("finally")
    public ArrayList<Studio> findStudioByName(String StudioName)
    {
        if(StudioName == null || StudioName.equals(""))
            return null;

        ArrayList<Studio> list = new ArrayList<Studio>();
        Studio info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据:模糊查询
            pstmt = con.prepareStatement("select * from studio where studio_name like ?");
            pstmt.setString(1, "%" + StudioName + "%");// 拼接模糊查询串
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                // 如果有值的话再实例化
                info = new Studio();
                info.SetStu_id(rs.getInt("studio_id"));
                info.SetStu_name(rs.getString("studio_name"));
                info.SetStu_row_count(rs.getInt("studio_row_count"));
                info.SetStu_col_count(rs.getInt("studio_col_count"));
                info.SetStu_introduction(rs.getString("studio_introduction"));
                info.SetStu_flag(rs.getInt("studio_flag"));
                info.SetStu_path(rs.getString("studio_path"));
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
     * 根据studio_id获取用户信息(一般用于数据内部关联操作)
     * @return 用户
     */
    @SuppressWarnings("finally")
    public ArrayList<Studio> findStudioById(int StudioId)
    {
    	Studio info = null;
        if(StudioId == 0)
            return null;

        ArrayList<Studio> list = new ArrayList<Studio>();
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from studio where studio_id=?");
            pstmt.setInt(1, StudioId);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                // 如果有值的话再实例化
                info = new Studio();
                info.SetStu_id(StudioId);                
                info.SetStu_name(rs.getString("studio_name"));
                info.SetStu_row_count(rs.getInt("studio_row_count"));
                info.SetStu_col_count(rs.getInt("studio_col_count"));
                info.SetStu_introduction(rs.getString("Studio_introduction"));
                info.SetStu_flag(rs.getInt("studio_flag"));
                info.SetStu_path(rs.getString("studio_path"));
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
     * 根据studio_id获取用户信息(一般用于数据内部关联操作)
     * @return 用户
     */
    @SuppressWarnings("finally")
    public Studio findById(int StudioId)
    {
    	Studio info = null;
        if(StudioId == 0)
            return info;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from studio where studio_id=?");
            pstmt.setInt(1, StudioId);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                // 如果有值的话再实例化
                info = new Studio();
                info.SetStu_id(StudioId);                
                info.SetStu_name(rs.getString("studio_name"));
                info.SetStu_row_count(rs.getInt("studio_row_count"));
                info.SetStu_col_count(rs.getInt("studio_col_count"));
                info.SetStu_introduction(rs.getString("Studio_introduction"));
                info.SetStu_flag(rs.getInt("studio_flag"));
                info.SetStu_path(rs.getString("studio_path"));
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