package cn.edu.imu.migration.commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class Config {
	//属性列表
	private static Properties properties = new Properties();
	//配置文件的路径
	private static String CONFIG = "config/config.properties";
	//读取资源文件, 设置输入流
	private static InputStream is;
	//压缩文件路径和名称
	public static String FILE;
	//FTP存储文件名称
	public static String FTPFILE;
	//时间间隔
	public static String INTERVAL;
	//执行时间
	public static String DATE;

	static {
		try {
			is = new FileInputStream(CONFIG);
			//加载输入流
			properties.load(is);
			//获得配置的各个属性
			FILE = properties.getProperty("file");
			FTPFILE = properties.getProperty("ftpfile");
			INTERVAL = properties.getProperty("interval");
			DATE = properties.getProperty("date");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 刷新配置信息
	 */
	public static void refreshConfig(){
		try {
			is = new FileInputStream(CONFIG);
			//加载输入流
			properties.load(is);
			//获得配置的各个属性
			FILE = properties.getProperty("file");
			FTPFILE = properties.getProperty("ftpfile");
			INTERVAL = properties.getProperty("interval");
			DATE = properties.getProperty("date");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 配置执行间隔和执行时间
	 * @param interval
	 * @param date
	 */
	public static void config(String interval, String date){
		try {
			FileOutputStream fos = new FileOutputStream(CONFIG);
			//设置新的属性值
			properties.setProperty("interval", interval);
			INTERVAL  = interval;
			properties.setProperty("date", date);
			DATE = date;
			//保存到磁盘
			properties.store(fos, "update properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
