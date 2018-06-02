package cn.edu.imu.migration.commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class Config {
	//�����б�
	private static Properties properties = new Properties();
	//�����ļ���·��
	private static String CONFIG = "config/config.properties";
	//��ȡ��Դ�ļ�, ����������
	private static InputStream is;
	//ѹ���ļ�·��������
	public static String FILE;
	//FTP�洢�ļ�����
	public static String FTPFILE;
	//ʱ����
	public static String INTERVAL;
	//ִ��ʱ��
	public static String DATE;

	static {
		try {
			is = new FileInputStream(CONFIG);
			//����������
			properties.load(is);
			//������õĸ�������
			FILE = properties.getProperty("file");
			FTPFILE = properties.getProperty("ftpfile");
			INTERVAL = properties.getProperty("interval");
			DATE = properties.getProperty("date");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ˢ��������Ϣ
	 */
	public static void refreshConfig(){
		try {
			is = new FileInputStream(CONFIG);
			//����������
			properties.load(is);
			//������õĸ�������
			FILE = properties.getProperty("file");
			FTPFILE = properties.getProperty("ftpfile");
			INTERVAL = properties.getProperty("interval");
			DATE = properties.getProperty("date");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ����ִ�м����ִ��ʱ��
	 * @param interval
	 * @param date
	 */
	public static void config(String interval, String date){
		try {
			FileOutputStream fos = new FileOutputStream(CONFIG);
			//�����µ�����ֵ
			properties.setProperty("interval", interval);
			INTERVAL  = interval;
			properties.setProperty("date", date);
			DATE = date;
			//���浽����
			properties.store(fos, "update properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
