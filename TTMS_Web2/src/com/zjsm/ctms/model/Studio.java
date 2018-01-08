package com.zjsm.ctms.model;

import java.io.Serializable;

public class Studio implements Serializable {
	private int studio_id;
    private String studio_name;
    private int studio_row_count;
    private int studio_col_count;
    private String studio_introduction;
    private int studio_flag;
    private String studio_path;

    public int getStu_id()
    {
        return studio_id;
    }

    public void SetStu_id(int studio_id)
    {
        this.studio_id = studio_id;
    }

    public String getStu_name()
    {
        return studio_name;
    }

    public void SetStu_name(String studio_name)
    {
        this.studio_name = studio_name;
    }

    public int getStu_row_count()
    {
        return studio_row_count;
    }

    public void SetStu_row_count(int studio_row_count)
    {
        this.studio_row_count = studio_row_count;
    }

    public int getStu_col_count()
    {
        return studio_col_count;
    }

    public void SetStu_col_count(int studio_col_count)
    {
        this.studio_col_count = studio_col_count;
    }

    public String getStu_introduction()
    {
        return studio_introduction;
    }

    public void SetStu_introduction(String studio_introduction)
    {
        this.studio_introduction = studio_introduction;
    }

    public int getStu_flag()
    {
        return studio_flag;
    }

    public void SetStu_flag(int studio_flag)
    {
        this.studio_flag= studio_flag;
    }
    public String getStu_path()
    {
        return studio_path;
    }

    public void SetStu_path(String studio_path)
    {
        this.studio_path= studio_path;
    }

}