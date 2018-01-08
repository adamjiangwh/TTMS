package com.zjsm.ctms.idao;


import java.util.ArrayList;

import com.zjsm.ctms.model.Sale;


/**
 * 定义对用户表的增删改查接口
 * @author 张荣
 */
public interface ISale
{
    // 增
    public boolean insert(Sale sale);

    // 删
    public boolean delete(String sale_ID);

    // 改
    public boolean update(Sale sale);

  
    public ArrayList<Sale> findSaleAll();


    public  Sale findSaleBySale_ID(String sale_ID);
    

    public Sale findSaleByEmp_id(int emp_id );
    
    public ArrayList<Sale> findSaleByPage(int cPage, String sale_ID);
    
    public ArrayList<Sale> findBySale_ID(String sale_ID);
}
