package cn.edu.imu.migration.dao.impl;
import java.sql.ResultSet;
import java.util.Collection;
import cn.edu.imu.migration.commons.DataUtil;
import cn.edu.imu.migration.dao.TaskDataDao;
import cn.edu.imu.migration.entity.Task;
import cn.edu.imu.migration.entity.ValueObject;
import cn.edu.imu.migration.jdbc.JDBCExecutor;

public class TaskDataDaoImpl implements TaskDataDao{
	//返回JDBCExecutor对象
	private JDBCExecutor getJDBCExecutor() {
		return JDBCExecutor.getJDBCExecutor();
	}
	
	//根据参数的SQL, 存放结果的集合对象, 和具体的数据库映射对象返回一个集合
	public ResultSet getDatas(String sql) {
		//执行SQL返回ResultSet对象
		ResultSet rs = getJDBCExecutor().executeQuery(sql);
		//对ResultSet进行封装并返回集合
		return rs;
	}
	//初始化任务数据信息
	public Task initTask(Task task){
		if(task.getSql().size() == 1){
			try{
				//获得数据总长度
				ResultSet rs = getDatas(task.getSql().get(0));
				//移到最后一行
				rs.last();
				task.setCount(rs.getRow());
				//移到第一行之前
				rs.beforeFirst();
				//rs.first();
				//设置数据集
				task.setRs(rs);
			}catch(Exception e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return task;
	}
}
