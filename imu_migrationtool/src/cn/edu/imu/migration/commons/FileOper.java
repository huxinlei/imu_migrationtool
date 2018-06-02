package cn.edu.imu.migration.commons;

import java.io.FileWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.List;

import cn.edu.imu.migration.entity.Column;
import cn.edu.imu.migration.entity.Task;

public class FileOper {
	public static boolean saveStrToFile(String file, String str){
		boolean result = true;
		try{
			//��ʼ��д���ļ���
			FileWriter writer = new FileWriter(file);
			//д������������
			writer.write(str);
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
}
