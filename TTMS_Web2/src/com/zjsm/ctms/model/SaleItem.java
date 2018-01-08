package com.zjsm.ctms.model;

import java.io.Serializable;

import java.math.BigDecimal;


public class SaleItem implements Serializable
{
    private String sale_item_id;
    private String ticket_id;
    private String sale_ID;
    private BigDecimal sale_item_price;
    
    
    public String getSale_item_id()
    {
        return  sale_item_id;
    }

    public void setSale_item_id(String sale_item_id)
    {
        this. sale_item_id =  sale_item_id;
    }
    
    public String getTicket_id()
    {
        return  ticket_id;
    }

    public void setTicket_id(String ticket_id)
    {
        this. ticket_id =  ticket_id;
    }
    

    public String getSale_ID()
    {
        return  sale_ID;
    }

    public void setSale_ID(String sale_ID)
    {
        this. sale_ID =  sale_ID;
    }
    
    
    public BigDecimal getSale_item_price()
    {
        return  sale_item_price;
    }

    public void setSale_item_price(BigDecimal sale_item_price)
    {
        this. sale_item_price =  sale_item_price;
    }

  
}
