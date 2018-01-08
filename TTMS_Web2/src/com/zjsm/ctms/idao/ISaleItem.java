package com.zjsm.ctms.idao;


import java.util.ArrayList;


import com.zjsm.ctms.model.SaleItem;

/**
 * 定义对用户表的增删改查接口
 * @author 张荣
 */
public interface ISaleItem
{
   
    public ArrayList<SaleItem> findSaleItemBySale_ID(String sale_ID);
  
    public ArrayList<SaleItem> findSaleItemAll();
    
    public ArrayList<SaleItem> findSaleItemByPage(int cPage, String sale_item_id);



    


}
