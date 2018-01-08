package com.zjsm.ctms.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.zjsm.ctms.idao.ISaleItem;
import com.zjsm.ctms.model.SaleItem;
import com.zjsm.util.ConnectionManager;



public  class SaleItemDAO implements ISaleItem
{
	
	 public static final int PAGE_SIZE = 9; // 每页显示条数
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
/*    @SuppressWarnings("finally")*/
/*    public boolean insert(SaleItem saleItem)
    {
        boolean result = false;
        if(saleItem == null)
            return result;

        // 获取Connection
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
        	
        	String sql = "insert into sale (sale_item_id,ticket_id,sale_ID,sale_item_price) value(?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,saleItem.getSale_item_id());
            pstmt.setString(2, saleItem.getTicket_id());
            pstmt.setString(3, saleItem.getSale_ID());
            pstmt.setBigDecimal(4, saleItem.getSale_item_price());
         
       

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
*/
    
    /**
     * 获取所有订单信息(一般用于和界面交互)
     * @return SaleItem列表
     */
    @SuppressWarnings("finally")
    public ArrayList<SaleItem> findSaleItemAll()
    {
        ArrayList<SaleItem> list = new ArrayList<SaleItem>();
        SaleItem info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from sale_item");
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                info = new SaleItem();
                info.setSale_item_id(rs.getString("sale_item_id"));
                info.setTicket_id(rs.getString("ticket_id"));
                info.setSale_ID(rs.getString(("sale_ID")));
                info.setSale_item_price(rs.getBigDecimal("sale_item_price"));
               
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
    public ArrayList<SaleItem> findSaleItemBySale_ID(String sale_ID)
    {
        if(sale_ID == null || sale_ID.equals(""))
            return null;

        ArrayList<SaleItem> list = new ArrayList<SaleItem>();
        SaleItem info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据:模糊查询
            pstmt = con.prepareStatement("select * from sale_item where sale_ID like ?");
            pstmt.setString(1, "%" + sale_ID + "%");// 拼接模糊查询串
            rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                // 如果有值的话再实例化
                info = new SaleItem();
                info.setSale_item_id(rs.getString("sale_item_id"));
                info.setTicket_id(rs.getString("ticket_id"));
                info.setSale_ID(rs.getString(("sale_ID")));
                info.setSale_item_price(rs.getBigDecimal("sale_item_price"));
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
    public ArrayList<SaleItem> findSaleItemByPage(int cPage, String sale_ID)
    {
        currentPage = cPage;
        ArrayList<SaleItem> list = new ArrayList<SaleItem>();

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
            String sql1 = "select count(sale_item_id) as AllRecord from sale_item where sale_ID like ?";
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
            String sql2 = "select * from sale_item where sale_ID like ? limit ?,?";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, "%" + sale_ID + "%");
            pstmt.setInt(2, PAGE_SIZE * (currentPage - 1));
            pstmt.setInt(3, PAGE_SIZE);
            rs = pstmt.executeQuery();
            SaleItem info = null;
            while (rs.next())
            {
                info = new SaleItem();
                info.setSale_item_id(rs.getString("sale_item_id"));
                info.setTicket_id(rs.getString("ticket_id"));
                info.setSale_ID(rs.getString(("sale_ID")));
                info.setSale_item_price(rs.getBigDecimal("sale_item_price"));
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
    

}