package cn.edu.imu.migration.io;

import java.sql.ResultSet;

import cn.edu.imu.migration.entity.Task;
import cn.edu.imu.migration.jdbc.JDBCExecutor;
public interface TaskDataIo {
	/**
	 * ִ�����������ݼ�
	 * @return
	 */
	boolean saveTaskData(Task task);
	/**
	 * ɾ���ļ�file
	 * @param file
	 * @return
	 */
	boolean deleteFile(String file);
}
