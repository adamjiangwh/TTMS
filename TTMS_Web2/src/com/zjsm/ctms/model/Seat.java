package com.zjsm.ctms.model;

import java.io.Serializable;

public class Seat implements Serializable
{
    private int seat_id;
    private int studio_id;
    private int seat_row;
    private int seat_col;
    private int seat_status;

    public int getSeat_id()
    {
        return seat_id;
    }

    public void setSeat_id(int seat_id)
    {
        this.seat_id = seat_id;
    }

    public int getStudio_id()
    {
        return studio_id;
    }

    public void setStudio_id(int studio_id)
    {
        this.studio_id = studio_id;
    }

    public int getSeat_row()
    {
        return seat_row;
    }

    public void setSeat_row(int seat_row)
    {
        this.seat_row = seat_row;
    }
    
    public int getSeat_col()
    {
        return seat_col;
    }

    public void setSeat_col(int seat_col)
    {
        this.seat_col = seat_col;
    }
    
    public int getSeat_status()
    {
        return seat_status;
    }

    public void setSeat_status(int seat_status)
    {
        this.seat_status = seat_status;
    }
}

