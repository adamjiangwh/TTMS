package com.zjsm.ctms.idao;

import java.util.ArrayList;

import com.zjsm.ctms.model.Studio;



public interface IStudio {
	// 增
    public boolean insert(Studio studio);

    // 删
    public boolean delete(int studioId);

    // 改
    public boolean update(Studio studio);

    // 查所有用户(一般用于和界面交互)
    public ArrayList<Studio> findStudioAll();

    // 根据用户名查(一般用于和界面交互)
    public ArrayList<Studio> findStudioByName(String StudioName);

    // 根据用户id查(一般用于数据内部关联操作)
    public ArrayList<Studio> findStudioById(int studioId);
    
    public Studio findById(int StudioId);
    
    public ArrayList<Studio> findStudioByPage(int cPage, String studio_name);
    
}
