package cn.edu.imu.migration.dao;

import java.sql.ResultSet;

import cn.edu.imu.migration.entity.Task;
import cn.edu.imu.migration.jdbc.JDBCExecutor;

public interface TaskDataDao {
	/**
	 * ִ�����������ݼ�
	 * @return
	 */
	ResultSet getDatas(String sql);
	/***
	 * ��ʼ�������б�
	 * @return 
	 */
	Task initTask(Task task);
}
