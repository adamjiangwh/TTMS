package com.zjsm.ctms.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.zjsm.ctms.idao.ISale;
import com.zjsm.ctms.model.Sale;
import com.zjsm.util.ConnectionManager;

public  class SaleDAO implements ISale
{
	
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
    /**
     * 存储订单信息
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean insert(Sale sale)
    {
        boolean result = false;
        if(sale == null)
            return result;

        // 获取Connection
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
        	SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	String sql = "insert into sale (emp_id,sale_time,sale_payment,sale_change,sale_type,sale_status) value(?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, sale.getEmp_id());
            pstmt.setDate(2, (java.sql.Date) sale.getSale_time());
            Date date = new Date();
            String str = s.format(date);
            pstmt.setString(2,str);
            pstmt.setBigDecimal(3, sale.getSale_payment());
            pstmt.setBigDecimal(4, sale.getSale_change());
            pstmt.setInt(5,sale.getSale_type());
            pstmt.setInt(6,sale.getSale_status());
            

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
     * 删除订单(通过sale_ID)
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean delete(String sale_ID)
    {
        boolean result = false;
        if(sale_ID == null)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            // 删除子某个订单
            String sql = "delete from sale where sale_ID=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, sale_ID);
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
     * 修改订单信息
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean update(Sale sale)
    {
        boolean result = false;
        if(sale == null)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            String sql = "update sale set emp_id=?,sale_time=?,sale_payment=?,sale_change=?,sale_type=?,sale_status=? where sale_ID=?";
            pstmt = con.prepareStatement(sql);
            
            pstmt.setInt(1, sale.getEmp_id());
            //pstmt.setString(3, sale.getSale_time_());
            pstmt.setTimestamp(2,new Timestamp(((java.util.Date)sale.getSale_time()).getTime()));
            pstmt.setBigDecimal(3, sale.getSale_payment());
            pstmt.setBigDecimal(4, sale.getSale_change());
            pstmt.setInt(5,sale.getSale_type());
            pstmt.setInt(6,sale.getSale_status());

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
     * 获取所有订单信息(一般用于和界面交互)
     * @return Sale列表
     */
    @SuppressWarnings("finally")
    public ArrayList<Sale> findSaleAll()
    {
        ArrayList<Sale> list = new ArrayList<Sale>();
        Sale info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from sale");
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                info = new Sale();
                //Long time = rs.getTimestamp("sale_time").getTime();
                //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               // String d = format.format(time);
               // Date date = format.parse(d);
                info.setSale_ID(rs.getString(("sale_ID")));
                info.setEmp_id(rs.getInt("emp_id"));

                //info.setSale_time(rs.getDate("sale_time"));
                info.setSale_time(rs.getTimestamp("sale_time"));
                new java.sql.Timestamp(new java.util.Date().getTime());
                info.setSale_payment(rs.getBigDecimal("sale_payment"));
                info.setSale_change(rs.getBigDecimal("sale_change"));
                info.setSale_type(rs.getInt("sale_type"));
                info.setSale_status(rs.getInt("sale_status"));
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
    
    @SuppressWarnings("finally")
    public ArrayList<Sale> findBySale_ID(String sale_ID)
    {
    	if(sale_ID == null || sale_ID.equals(""))
            return null;
    	
        ArrayList<Sale> list = new ArrayList<Sale>();
        Sale info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from sale");
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                info = new Sale();
                //Long time = rs.getTimestamp("sale_time").getTime();
                //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               // String d = format.format(time);
               // Date date = format.parse(d);
                info.setSale_ID(rs.getString(("sale_ID")));
                info.setEmp_id(rs.getInt("emp_id"));

                //info.setSale_time(rs.getDate("sale_time"));
                info.setSale_time(rs.getTimestamp("sale_time"));
                new java.sql.Timestamp(new java.util.Date().getTime());
                info.setSale_payment(rs.getBigDecimal("sale_payment"));
                info.setSale_change(rs.getBigDecimal("sale_change"));
                info.setSale_type(rs.getInt("sale_type"));
                info.setSale_status(rs.getInt("sale_status"));
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
     * 根据订单号获取订单信息(一般用于和界面交互)
     * @return Sale列表
     */
    @SuppressWarnings("finally")
    public Sale findSaleBySale_ID(String sale_ID)
    {
        if(sale_ID == null || sale_ID.equals(""))
            return null;

        ArrayList<Sale> list = new ArrayList<Sale>();
        Sale info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据:模糊查询
            pstmt = con.prepareStatement("select * from sale where sale_ID like ?");
            pstmt.setString(1, "%" + sale_ID + "%");// 拼接模糊查询串
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                // 如果有值的话再实例化
                info = new Sale();
                Long time = rs.getTimestamp("sale_time").getTime();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String d = format.format(time);
                Date date = format.parse(d);
                info.setSale_ID(rs.getArray(("sale_ID")).toString());
                info.setEmp_id(rs.getInt("emp_id"));
                info.setSale_time(date);
                info.setSale_payment(rs.getBigDecimal("sale_payment"));
                info.setSale_change(rs.getBigDecimal("sale_change"));
                info.setSale_type(rs.getInt("sale_type"));
                info.setSale_status(rs.getInt("sale_status"));
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
            return info;
        }
    }

    /**
     * 根据Emp_id获取订单信息(一般用于数据内部关联操作)
     * @return 用户
     */
    @SuppressWarnings("finally")
    public Sale findSaleByEmp_id(int emp_id)
    {
        Sale info = null;
        if(emp_id == 0)
            return info;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from sale where sale_ID=?");
            pstmt.setInt(1, emp_id);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                // 如果有值的话再实例化
                info = new Sale();
                Long time = rs.getTimestamp("sale_time").getTime();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String d = format.format(time);
                Date date = format.parse(d);
                info.setSale_ID(rs.getArray(("sale_ID")).toString());
                info.setEmp_id(rs.getInt("emp_id"));
                info.setSale_time(date);
                info.setSale_payment(rs.getBigDecimal("sale_payment"));
                info.setSale_change(rs.getBigDecimal("sale_change"));
                info.setSale_type(rs.getInt("sale_type"));
                info.setSale_status(rs.getInt("sale_status"));
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

    @SuppressWarnings("finally")
    public ArrayList<Sale> findSaleByPage(int cPage, String sale_ID)
    {
        currentPage = cPage;
        ArrayList<Sale> list = new ArrayList<Sale>();

        // 若未指定查找某人，则默认全查
        if(sale_ID == null || sale_ID.equals(""))
        {
        	sale_ID = "";
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取记录总数
            String sql1 = "select count(sale_ID) as AllRecord from sale where sale_ID like ?";
            conn = ConnectionManager.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1, "%" + sale_ID + "%");
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
            String sql2 = "select * from sale where sale_ID like ? limit ?,?";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, "%" + sale_ID + "%");
            pstmt.setInt(2, PAGE_SIZE * (currentPage - 1));
            pstmt.setInt(3, PAGE_SIZE);
            rs = pstmt.executeQuery();
            Sale info = null;
            while (rs.next())
            {
            	 info = new Sale();
                 info.setSale_ID(rs.getString(("sale_ID")));
                 info.setEmp_id(rs.getInt("emp_id"));
                 info.setSale_time(rs.getTimestamp("sale_time"));
                 new java.sql.Timestamp(new java.util.Date().getTime());
                 info.setSale_payment(rs.getBigDecimal("sale_payment"));
                 info.setSale_change(rs.getBigDecimal("sale_change"));
                 info.setSale_type(rs.getInt("sale_type"));
                 info.setSale_status(rs.getInt("sale_status"));
                 // 加入列表
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
    
    

}