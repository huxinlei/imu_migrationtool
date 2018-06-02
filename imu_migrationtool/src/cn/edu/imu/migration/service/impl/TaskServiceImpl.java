package cn.edu.imu.migration.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import cn.edu.imu.migration.commons.Config;
import cn.edu.imu.migration.commons.FtpConnection;
import cn.edu.imu.migration.commons.FtpProperties;
import cn.edu.imu.migration.commons.ZipUtil;
import cn.edu.imu.migration.dao.TaskDataDao;
import cn.edu.imu.migration.dao.impl.TaskDataDaoImpl;
import cn.edu.imu.migration.entity.Task;
import cn.edu.imu.migration.io.TaskDataIo;
import cn.edu.imu.migration.io.impl.TaskDataIoImpl;
import cn.edu.imu.migration.service.TaskService;

public class TaskServiceImpl implements TaskService{
	private TaskDataDao taskDao;
	private TaskDataIo taskIo;
	
	public TaskServiceImpl(){
		taskDao = new TaskDataDaoImpl();
		taskIo = new TaskDataIoImpl();
	}
	public boolean taskExecute(Task task){
		boolean result = true;
		task = taskDao.initTask(task);
		if(taskIo.saveTaskData(task)){
			result = true;
		}else {
			result = false;
		}
		return result;
	}
	
	public String tasksCompress(List<Task> list){
		//Ĭ��Ϊ�����ļ�������
		String file = Config.FILE;
		if(list.size() > 0){
			//���ݽ���ѹ������
			String[] files = new String[list.size()];
			for(int i = 0; i < list.size(); i++){
				files[i] = list.get(i).getFile();
			}
			
			boolean flag = ZipUtil.filesToZip(Config.FILE, files);
			if(!flag){
				//ѹ��ʧ�ܷ���ֵΪnull
				file = null;
			}
		}
		return file;
	}
	
	public boolean tasksUpload(String file){
		//Ħ���ϴ�ʧ��
		boolean result = true;
		//ftp���������ò���
		try{
			FtpConnection ftp = new FtpConnection(FtpProperties.URL,FtpProperties.PORT,
					FtpProperties.USERNAME,FtpProperties.PASSWORD);
			String TIME_FORMAT = "yyyy_MM_dd_HH_mm_ss";
			Date now = new Date();
			SimpleDateFormat sf = new SimpleDateFormat(TIME_FORMAT);
			String str = sf.format(now);
			String ftpFile = Config.FTPFILE + str + ".zip";
			ftp.uploadFiles(file, ftpFile);
			ftp.logout();
		}catch(IOException e){
			result = false;
		}
		return result;
	}
	
	public boolean tasksDelete(List<Task> list, String file){
		boolean result = true;
		for(int i = 0; i < list.size(); i++){
			String fn = list.get(i).getFile();
			taskIo.deleteFile(fn);
		}
		taskIo.deleteFile(file);
		return result;
	}
	
}
