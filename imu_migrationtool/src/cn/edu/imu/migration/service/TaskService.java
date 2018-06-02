package cn.edu.imu.migration.service;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

import cn.edu.imu.migration.entity.Task;
import cn.edu.imu.migration.jdbc.JDBCExecutor;

public interface TaskService {
	/**
	 * 执行任务服务
	 * @return
	 */
	boolean taskExecute(Task task);
	/**
	 * 将任务列表中的文件压缩
	 * @param list
	 * @return 压缩文件路径和名称
	 */
	String tasksCompress(List<Task> list);
	/**
	 * 将压缩文件上传到ftp服务器中
	 * @param file
	 * @return 
	 */
	boolean tasksUpload(String file);
	/**
	 * 删除任务列表文件和压缩文件
	 * @param list
	 * @param file
	 * @return
	 */
	boolean tasksDelete(List<Task> list, String file);
}
