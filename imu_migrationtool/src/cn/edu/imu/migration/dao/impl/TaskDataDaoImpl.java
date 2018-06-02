package cn.edu.imu.migration.dao.impl;
import java.sql.ResultSet;
import java.util.Collection;
import cn.edu.imu.migration.commons.DataUtil;
import cn.edu.imu.migration.dao.TaskDataDao;
import cn.edu.imu.migration.entity.Task;
import cn.edu.imu.migration.entity.ValueObject;
import cn.edu.imu.migration.jdbc.JDBCExecutor;

public class TaskDataDaoImpl implements TaskDataDao{
	//����JDBCExecutor����
	private JDBCExecutor getJDBCExecutor() {
		return JDBCExecutor.getJDBCExecutor();
	}
	
	//���ݲ�����SQL, ��Ž���ļ��϶���, �;�������ݿ�ӳ����󷵻�һ������
	public ResultSet getDatas(String sql) {
		//ִ��SQL����ResultSet����
		ResultSet rs = getJDBCExecutor().executeQuery(sql);
		//��ResultSet���з�װ�����ؼ���
		return rs;
	}
	//��ʼ������������Ϣ
	public Task initTask(Task task){
		if(task.getSql().size() == 1){
			try{
				//��������ܳ���
				ResultSet rs = getDatas(task.getSql().get(0));
				//�Ƶ����һ��
				rs.last();
				task.setCount(rs.getRow());
				//�Ƶ���һ��֮ǰ
				rs.beforeFirst();
				//rs.first();
				//�������ݼ�
				task.setRs(rs);
			}catch(Exception e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return task;
	}
}
