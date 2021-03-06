package cn.edu.imu.migration.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
public class ZipUtil {
	private static int BUFFER = 8192;
	/**
	 * 同时压缩多个文件
	 * @param zipname 压缩存储文件名称
	 * @param files 压缩原文件列表,输入不允许同名
	 * @return 是否压缩成功
	 */
	public static boolean filesToZip(String zipname, String[] files){
		//默认压缩成功
		boolean result = true;
		if(files != null && files.length > 0){
			try{
				//设置zip输出文件流
				FileOutputStream fout = new FileOutputStream(zipname);
				ZipOutputStream zout = new ZipOutputStream(fout);
				//遍历文件列表
				for(String file : files){
					//处理单个文件
					File fileEntry = new File(file);
					fileToZip(zout, fileEntry);
				}
				zout.close();
			}catch(IOException e){
				//IO异常，压缩失败
				result = false;
			}
		} else {
			//参数错误，压缩失败
			result = false;
		}
		return result;
	}
	/**
	 * 压缩一个文件,读取数据时按字节读入
	 * @param zipname 压缩存储文件名称
	 * @param file 压缩原文件
	 * @throws IOException
	 */
	public static void fileToZip(ZipOutputStream zout, File file) throws IOException{
		if(!file.exists()){
			return ;
		}
        BufferedInputStream bis = new BufferedInputStream(     
                new FileInputStream(file));     
        ZipEntry entry = new ZipEntry(file.getName());     
        zout.putNextEntry(entry);     
        int count;     
        byte data[] = new byte[BUFFER];     
        while ((count = bis.read(data, 0, BUFFER)) != -1) {     
            zout.write(data, 0, count);     
        }     
        bis.close();  
	}
}
