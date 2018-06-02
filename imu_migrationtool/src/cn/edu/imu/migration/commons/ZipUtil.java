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
	 * ͬʱѹ������ļ�
	 * @param zipname ѹ���洢�ļ�����
	 * @param files ѹ��ԭ�ļ��б�,���벻����ͬ��
	 * @return �Ƿ�ѹ���ɹ�
	 */
	public static boolean filesToZip(String zipname, String[] files){
		//Ĭ��ѹ���ɹ�
		boolean result = true;
		if(files != null && files.length > 0){
			try{
				//����zip����ļ���
				FileOutputStream fout = new FileOutputStream(zipname);
				ZipOutputStream zout = new ZipOutputStream(fout);
				//�����ļ��б�
				for(String file : files){
					//���������ļ�
					File fileEntry = new File(file);
					fileToZip(zout, fileEntry);
				}
				zout.close();
			}catch(IOException e){
				//IO�쳣��ѹ��ʧ��
				result = false;
			}
		} else {
			//��������ѹ��ʧ��
			result = false;
		}
		return result;
	}
	/**
	 * ѹ��һ���ļ�,��ȡ����ʱ���ֽڶ���
	 * @param zipname ѹ���洢�ļ�����
	 * @param file ѹ��ԭ�ļ�
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