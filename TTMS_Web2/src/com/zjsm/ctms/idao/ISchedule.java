package com.zjsm.ctms.idao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.zjsm.ctms.model.Schedule;
import com.zjsm.util.ConnectionManager;

public interface ISchedule {
	public boolean insert(Schedule schedule);
	public boolean delete(int scheduleId);
	public boolean update(Schedule schedule);
	public ArrayList<Schedule> findScheduleAll();
	public Schedule findScheduleById(int schedId);
	public ArrayList<Schedule> findScheduleByStudioId(int studioId);
}
