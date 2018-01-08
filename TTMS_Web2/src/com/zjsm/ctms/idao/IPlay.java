package com.zjsm.ctms.idao;

import java.util.ArrayList;

import com.zjsm.ctms.model.Play;	

public interface IPlay {
	
	// 增
    public boolean insert(Play play);

    // 删
    public boolean delete(int playId);

    // 改
    public boolean update(Play play);

    // 查所有用户(一般用于和界面交互)
    public ArrayList<Play> findPlayAll();	

    // 根据用户名查(一般用于和界面交互)
    public ArrayList<Play> findPlayByName(String playName);

    // 根据用户id查(一般用于数据内部关联操作)
    public ArrayList<Play> findPlayById(int playId);
    
    // 根据用户名获取用户信息(一般用于数据内部关联操作)
    public Play findById(int playId);
    
    public ArrayList<Play> findStudioByPage(int cPage, String play_name);

}
 