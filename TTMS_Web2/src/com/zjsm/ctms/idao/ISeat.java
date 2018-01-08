package com.zjsm.ctms.idao;

import java.util.ArrayList;

import com.zjsm.ctms.model.Seat;


public interface ISeat {
    // 增
    public boolean insert(Seat seat);

    // 删
    public boolean delete(int seatId);

    // 改
    public boolean update(Seat seat);

    // 查所有用户(一般用于和界面交互)
    public ArrayList<Seat> findSeatAll();

    // 根据用户id查(一般用于数据内部关联操作)
    public Seat findSeatById(int seatId);
    
    public ArrayList<Seat> findSeatByStudioId(int studioId);
    
    public int findStudioId(int studioId);
}
