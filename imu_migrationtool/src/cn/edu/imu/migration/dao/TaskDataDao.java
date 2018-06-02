package cn.edu.imu.migration.dao;

import java.sql.ResultSet;

import cn.edu.imu.migration.entity.Task;
import cn.edu.imu.migration.jdbc.JDBCExecutor;

public interface TaskDataDao {
	/**
	 * 执行任务获得数据集
	 * @return
	 */
	ResultSet getDatas(String sql);
	/***
	 * 初始化任务列表
	 * @return 
	 */
	Task initTask(Task task);
}
