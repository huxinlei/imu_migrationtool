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
	//ʱ���ʽ
	private static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**
	 * ִ�����������ݼ�
	 * @return
	 */
	public boolean saveTaskData(Task task){
		boolean result = true;
		try{
			//��ʼ��д���ļ���
			FileWriter writer = new FileWriter(task.getFile());
			//д������������
			writer.write(String.valueOf(task.getCount()));
			//���뻻��
			writer.write("\r\n");
			String columns = "";
			List<Column> cols = task.getColumns();
			//����Ƿ������һ���ڵ�
			for(int i = 0; i < cols.size(); i++){
				//����ֶ�����
				String str = cols.get(i).getName();
				columns += str;
				//���һ��ȥ������
				if(task.getColumns().size() != (i+1)){
					columns += ",";	
				}
			}
			//д��ڶ���
			writer.write(columns);
			writer.write("\r\n");
			ResultSet rs = task.getRs();//������ݼ�
			if(rs != null){
				//ѭ�����ݼ�д����������
				while(rs.next()){
					String row = "";
					//����Ƿ������һ���ڵ�
					for(int i = 0; i < cols.size(); i++){//cols.size()
						//����ֶ�����
						String type = cols.get(i).getType();
						//�������Ͳ�����ʱĬ��Ϊ��
						String str = "";
						//���û�ȡ�����±�
						int index = i + 1;
						if(type.equals("String")){
							str = rs.getString(index);
						}else if(type.equals("Int")){
							try{
								//intnull����Ϊtrueʱȡ�õĽ��Ϊ0����str��ֵΪnull
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
						//���һ��ȥ������
						if(cols.size() != (i+1)){
							row += ",";	
						}
					}
					//д���i����������
					writer.write(row);
					writer.write("\r\n");
				}
			}
			//��ǻ������ݼ�
			task.setRs(null);
			//д���ļ�
			writer.flush();
			//�ر��ļ�
			writer.close();
			result = true;
		}catch(Exception e){
			result = false;//д��ʧ��
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