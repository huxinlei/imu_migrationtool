package cn.edu.imu.migration.commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FtpProperties {
	//�����б�
	private static Properties properties = new Properties();
	//�����ļ���·��
	private static String CONFIG = "config/ftp.properties";
	//��ȡ��Դ�ļ�, ����������
	private static InputStream is;
	//ftp����·��
	public static String URL;
	//ftp�˿�
	public static Integer PORT;
	//ftp��¼��
	public static String USERNAME;
	//ftp��¼����
	public static String PASSWORD;
	static {
		try {
			is = new FileInputStream(CONFIG);
			//����������
			properties.load(is);
			//������õĸ�������
			URL = properties.getProperty("url");
			PORT = Integer.parseInt(properties.getProperty("port"));
			USERNAME = properties.getProperty("username");
			PASSWORD = properties.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}