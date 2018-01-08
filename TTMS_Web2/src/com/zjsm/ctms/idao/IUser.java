package com.zjsm.ctms.idao;

import java.util.ArrayList;

import com.zjsm.ctms.model.User;

/**
 * 定义对用户表的增删改查接口
 * @author 张荣
 */
public interface IUser
{
    // 增
    public boolean insert(User user);

    // 删
    public boolean delete(String userNo);

    // 改
    public boolean update(User user);

    // 查所有用户(一般用于和界面交互)
    public ArrayList<User> findUserAll();

    // 根据用户名查(一般用于和界面交互)
    public ArrayList<User> findUserByName(String UserName);

    // 根据用户id查(一般用于数据内部关联操作)
    public User findUserByNo(String UserId);
}