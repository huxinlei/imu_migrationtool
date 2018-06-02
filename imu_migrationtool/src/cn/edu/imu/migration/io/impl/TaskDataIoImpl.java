package cn.edu.imu.migration.io.impl;

import java.io.FileWriter;
import java.io.File;
import java.sql.ResultSet;
import java.util.List;
import java.util.Iterator;
import java.text.SimpleDateFormat;

import cn.edu.imu.migration.entity.Column;
import cn.edu.imu.migration.entity.Task;
import cn.edu.imu.migration.io.TaskDataIo;
import cn.edu.imu.migration.jdbc.JDBCExecutor;

public class TaskDataIoImpl  implements TaskDataIo{
	//时间格式
	private static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 执行任务获得数据集
	 * @return
	 */
	public boolean saveTaskData(Task task){
		boolean result = true;
		try{
			//初始化写入文件类
			FileWriter writer = new FileWriter(task.getFile());
			//写入数据总行数
			writer.write(String.valueOf(task.getCount()));
			//输入换行
			writer.write("\r\n");
			String columns = "";
			List<Column> cols = task.getColumns();
			//标记是否是最后一个节点
			for(int i = 0; i < cols.size(); i++){
				//获得字段名称
				String str = cols.get(i).getName();
				columns += str;
				//最后一列去掉列名
				if(task.getColumns().size() != (i+1)){
					columns += ",";	
				}
			}
			//写入第二行
			writer.write(columns);
			writer.write("\r\n");
			ResultSet rs = task.getRs();//获得数据集
			if(rs != null){
				//循环数据集写入内容数据
				while(rs.next()){
					String row = "";
					//标记是否是最后一个节点
					for(int i = 0; i < cols.size(); i++){//cols.size()
						//获得字段名称
						String type = cols.get(i).getType();
						//基本类型不存在时默认为空
						String str = "";
						//设置获取数据下标
						int index = i + 1;
						if(type.equals("String")){
							str = rs.getString(index);
						}else if(type.equals("Int")){
							try{
								//intnull属性为true时取得的结果为0，则str赋值为null
								if(cols.get(i).getIntNull() && rs.getInt(index) == 0){
									str = "null";
								} else {
									str = String.valueOf(rs.getInt(index));
								}
							}catch(Exception e){
								str = "null";
							}
						}else if(type.equals("Long")){
							try{
								if(cols.get(i).getIntNull() && rs.getLong(index) == 0){
									str = "null";
								} else {
									str = String.valueOf(rs.getLong(index));	
								}
							}catch(Exception e){
								str = "null";
							}
						}else if(type.equals("Short")){
							try{
								str = String.valueOf(rs.getShort(index));
							}catch(Exception e){
								str = "null";
							}
						}else if(type.equals("Float")){
							try{
								str = String.valueOf(rs.getFloat(index));
							}catch(Exception e){
								str = "null";
							}
						}else if(type.equals("Double")){
							try{
								str = String.valueOf(rs.getDouble(index));
							}catch(Exception e){
								str = "null";
							}
							
						}else if(type.equals("Boolean")){
							try{
								str = String.valueOf(rs.getBoolean(index));
							}catch(Exception e){
								str = "null";
							}
							
						}else if(type.equals("Date")){
						    SimpleDateFormat sf = new SimpleDateFormat(TIME_FORMAT);
						    try{
						    	str = sf.format(rs.getDate(index));
						    }catch(Exception e){
						    	str = "null";
						    }
						}else if(type.equals("BigDecimal")){
							try{
								str = rs.getBigDecimal(index).toString();
							}catch(Exception e){
								str = "null";
							}	
						}
						row += str;
						//最后一列去掉列名
						if(cols.size() != (i+1)){
							row += ",";	
						}
					}
					//写入第i行内容数据
					writer.write(row);
					writer.write("\r\n");
				}
			}
			//标记回收数据集
			task.setRs(null);
			//写入文件
			writer.flush();
			//关闭文件
			writer.close();
			result = true;
		}catch(Exception e){
			result = false;//写入失败
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public boolean deleteFile(String file){
		boolean result = true;
		try{
			File f = new File(file);
			if(f.exists()){
				f.delete();
			}
		}catch(Exception e){
			result = false;
		}
		return result;
	}
}
