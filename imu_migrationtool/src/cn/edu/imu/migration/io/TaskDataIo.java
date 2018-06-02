package cn.edu.imu.migration.io;

import java.sql.ResultSet;

import cn.edu.imu.migration.entity.Task;
import cn.edu.imu.migration.jdbc.JDBCExecutor;
public interface TaskDataIo {
	/**
	 * 执行任务获得数据集
	 * @return
	 */
	boolean saveTaskData(Task task);
	/**
	 * 删除文件file
	 * @param file
	 * @return
	 */
	boolean deleteFile(String file);
}
