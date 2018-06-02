package cn.edu.imu.migration.service;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

import cn.edu.imu.migration.entity.Task;
import cn.edu.imu.migration.jdbc.JDBCExecutor;

public interface TaskService {
	/**
	 * ִ���������
	 * @return
	 */
	boolean taskExecute(Task task);
	/**
	 * �������б��е��ļ�ѹ��
	 * @param list
	 * @return ѹ���ļ�·��������
	 */
	String tasksCompress(List<Task> list);
	/**
	 * ��ѹ���ļ��ϴ���ftp��������
	 * @param file
	 * @return 
	 */
	boolean tasksUpload(String file);
	/**
	 * ɾ�������б��ļ���ѹ���ļ�
	 * @param list
	 * @param file
	 * @return
	 */
	boolean tasksDelete(List<Task> list, String file);
}
