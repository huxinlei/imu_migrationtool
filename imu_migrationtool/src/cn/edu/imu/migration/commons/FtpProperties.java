package cn.edu.imu.migration.commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FtpProperties {
	//属性列表
	private static Properties properties = new Properties();
	//配置文件的路径
	private static String CONFIG = "config/ftp.properties";
	//读取资源文件, 设置输入流
	private static InputStream is;
	//ftp连接路径
	public static String URL;
	//ftp端口
	public static Integer PORT;
	//ftp登录名
	public static String USERNAME;
	//ftp登录密码
	public static String PASSWORD;
	static {
		try {
			is = new FileInputStream(CONFIG);
			//加载输入流
			properties.load(is);
			//获得配置的各个属性
			URL = properties.getProperty("url");
			PORT = Integer.parseInt(properties.getProperty("port"));
			USERNAME = properties.getProperty("username");
			PASSWORD = properties.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
