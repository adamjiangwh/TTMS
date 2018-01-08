package com.zjsm.ctms.model;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;


public class Sale implements Serializable
{
    private String sale_ID;
    private int emp_id;
    private Date sale_time;
    private BigDecimal sale_payment;
    private BigDecimal sale_change;
    private int sale_type;
    private int sale_status;

    public String getSale_ID()
    {
        return  sale_ID;
    }

    public void setSale_ID(String sale_ID)
    {
        this. sale_ID =  sale_ID;
    }

    public int getEmp_id()
    {
        return emp_id;
    }

    public void setEmp_id(int emp_id)
    {
        this.emp_id = emp_id;
    }

    public Date getSale_time()
    {
        return sale_time;
    }

    public void setSale_time(Date sale_time)
    {
        this.sale_time = sale_time;
    }

    public BigDecimal getSale_payment()
    {
        return sale_payment;
    }

    public void setSale_payment(BigDecimal sale_payment)
    {
        this.sale_payment = sale_payment;
    }

    public BigDecimal getSale_change()
    {
        return sale_change;
    }

    public void setSale_change(BigDecimal sale_change)
    {
        this.sale_change = sale_change;
    }

    public int getSale_type()
    {
        return sale_type;
    }

    public void setSale_type(int sale_type)
    {
        this.sale_type = sale_type;
    }

    public int getSale_status()
    {
        return sale_status;
    }

    public void setSale_status(int sale_status)
    {
        this.sale_status = sale_status;
    }
}
