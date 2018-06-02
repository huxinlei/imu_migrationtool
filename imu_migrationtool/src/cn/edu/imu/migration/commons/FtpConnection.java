package cn.edu.imu.migration.commons;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FTP���������ӣ����ڷ���FTP��������ʵ�ֳ��õĴ���Ŀ¼��ɾ��Ŀ¼���ϴ��ļ��������ļ��Ȳ�����
 * @author qinhuan
 * @version 2013-03-01 17:13:39
 */
public class FtpConnection {
	
	/**
	 * �ļ��ϴεĻ�������С
	 */
	private static final int BUF_SIZE = 1024*1024;


	private Logger logger = LoggerFactory.getLogger(FtpConnection.class);
	

	private String hostname;
	private Integer port;
	private String username;
	private String password;
	
	FTPClient client;

	/**
	 * ����һ����FTP�����������ӡ�
	 * @param url ������IP��ַ
	 * @param prot ����˿�
	 * @param username �û���
	 * @param password ����
	 */
	public FtpConnection(String url, Integer prot, String username, String password) {
		this.hostname = url;
		this.port = prot;
		this.username = username;
		this.password = password;
		client = new FTPClient();
		try {
			client.connect(hostname, port);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("FtpConection:����FTP�����������쳣��");
		}
		
		try {
			client.login(username, password);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("FtpConection:��½FTP�����������쳣��");
		}
	}
	
	/**
	 * ���ԭʼ��FTPClient����
	 * @return FTPClient����
	 */
	public FTPClient getClient() {
		return client;
	}

	/**
	 * �������Ӻ͵�¼�Ƿ�ɹ�
	 * @return Booleanֵ��true-���Ӳ���¼�ɹ���false-���ӳ�ʱ���¼ʧ�ܡ�
	 */
	public boolean isConnected() {
		boolean result = false;
        int reply = client.getReplyCode();
//		String replyString = client.getReplyString();
        String info = null;
        switch (reply) {
		case 0:
//			System.out.println(reply);  
//			System.out.println("���ӳ�ʱ��");  
//			System.out.println(replyString); 
			info = "���ӳ�ʱ��";
			break;
		case 230:
//			System.out.println(reply);  
//			System.out.println("���ӳɹ�����½�ɹ���");  
//			System.out.println(replyString); 
			info = "���ӳɹ�����½�ɹ���";
			result = true;
			break;
		case 530:
//			System.out.println(reply);  
//			System.out.println("�û������������");  
//			System.out.println(replyString); 
			info = "�û������������";
			break;
		}
        logger.info(info);
		return result;
	}
	
	/**
	 * ����Ŀ¼
	 * @param path Ŀ¼�ĵ�ַ���磺a\b\c��
	 * @return Booleanֵ��true-�����ɹ���false-����ʧ�ܣ�Ŀ¼���ڻ�·�����󣩡�
	 * @throws IOException
	 */
	public boolean createDirectory(String path) throws IOException {
		return client.makeDirectory(path);
	}
	
	/**
	 * ɾ��Ŀ¼����Ŀ¼��Ϊ��ʱ��ͬʱ�ݹ�ɾ��Ŀ¼�������ļ��к��ļ���
	 * @param path ��ɾ���ķ�����Ŀ¼·�����磺a\b\c\����
	 * @return Booleanֵ��true-ɾ���ɹ���false-ɾ��ʧ�ܣ�Ŀ¼�����ڣ���
	 * @throws IOException 
	 */
	public boolean deleteDirectory(String path) throws IOException {
		boolean result = true;
		
		FTPFile directory = client.mlistFile(path);
		if (directory == null) {
			logger.info("FtpConection:��ɾ�����ļ��в����ڡ�");
			return false;
		}
		if (directory.isDirectory()) {
			FTPFile[] files = client.mlistDir(path);
			for (FTPFile f : files) {
				if (f.isFile()) {
					client.deleteFile(path + "\\" + f.getName());
					logger.info("FtpConection:ɾ���ļ�" + path + "\\" + f.getName());
				}
				if (f.isDirectory()) {
					deleteDirectory(path + "\\" + f.getName());
				}
			}
			client.removeDirectory(path);
			logger.info("FtpConection:ɾ��Ŀ¼" + path);
		} else {
			logger.info("FtpConection:��ɾ���Ĳ���Ŀ¼��");
			result = false;
		}

		return result;
	}
	
	/**
	 * �ϴ��ļ�
	 * @param localPath �����ļ�·�������ļ�����
	 * @param serverPath FTP�������洢·�������ļ�����
	 * @return Booleanֵ��true-�ϴ��ɹ���false-�ϴ�ʧ�ܣ��ļ����ڻ�Ŀ¼���󣩡�
	 * @throws IOException
	 */
	public boolean uploadFiles(String localPath, String serverPath) throws IOException {
		boolean result = false;

		client.setFileType(FTP.BINARY_FILE_TYPE);
		client.enterLocalPassiveMode();
		client.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
		client.setBufferSize(BUF_SIZE);
		
		InputStream local = null;
		try {
			local = new FileInputStream(localPath);
			result = client.storeFile(serverPath, local);
		} finally {
			if (local != null) {
				local.close();
			}
		}
	
		return result;
	}

	/**
	 * �����ļ�
	 * @param serverPath FTP�������ļ�·�������ļ�����
	 * @param localPath ���ش洢�ļ�·�������ļ�����
	 * @return Booleanֵ��true-���سɹ���false-����ʧ�ܣ��ļ�Ŀ¼�����ڻ��ļ������ڣ���
	 * @throws IOException
	 */
	public boolean downloadFile(String serverPath, String localPath) throws IOException {
		boolean result = false;

		client.setFileType(FTP.BINARY_FILE_TYPE);
		client.enterLocalPassiveMode();
		client.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
		client.setBufferSize(BUF_SIZE);
		
		OutputStream local = null;
		try {
			local = new FileOutputStream(localPath);
			result = client.retrieveFile(serverPath, local);
		} finally {
			try {
				local.close();
			} catch (Exception e) {
				// ����Ŀ¼�����ڣ�����ʧ�ܡ�
				logger.error("FTP �����ļ��ڱ��ر�������쳣������·�������ڣ�");
				return false;
			}
		}
		
		return result;
	}
	
	/**
	 * ɾ��FTP�������ϵ�ָ��·�����ļ�
	 * @param serverPath Ҫɾ�����ļ��ڷ������ϵ�·�����磺files\a.txt����
	 * @return Booleanֵ��true-ɾ���ɹ���false-ɾ��ʧ�ܣ��ļ������ڻ�·��ΪĿ¼����
	 * @throws IOException 
	 */
	public boolean deleteServerFile(String serverPath) throws IOException {
		boolean result = false;
		
		FTPFile mlistFile = client.mlistFile(serverPath);
		if (mlistFile == null) {
			logger.info("��ɾ�����ļ��ڷ������ϲ����ڡ���" + serverPath + "��");
			return result;
		} else if(mlistFile.isDirectory()) {
			logger.info("ָ����·��ΪĿ¼���޷�ɾ������" + serverPath + "��");
			return result;
		}
		
		result = client.deleteFile(serverPath);
		
		return result;
	}
	
	/**
	 * ������FTP�������ϵ��ļ���Ŀ¼
	 * @param from ԭ����
	 * @param to ��������
	 * @return Booleanֵ��true-�޸ĳɹ���false-�޸�ʧ�ܡ�
	 * @throws IOException
	 */
	public boolean rename(String from, String to) throws IOException {
		FTPFile mlistFile = client.mlistFile(from);
		if (mlistFile == null) {
			logger.info("��������Դ�ļ���ԭĿ¼�����ڡ���" + from + "��");
			return false;
		}

		return client.rename(from, to);
	}
	
	/**
	 * ���ָ���ļ���Ŀ¼����Ϣ
	 * @param serverPath ������·��
	 * @return ��Ӧ�ļ���FTPFile����null��ʾ�ļ������ڣ���
	 * @throws IOException
	 */
	public FTPFile findOne(String serverPath) throws IOException {
		return client.mlistFile(serverPath);
	}
	/**
	 * ���ָ���ļ���Ŀ¼����Ϣ
	 * @param serverPath ������·��
	 * @return ��Ӧ�ļ���FTPFile����null��ʾ�ļ������ڣ���
	 * @throws IOException
	 */
	public boolean isfindFile(String serverPath) throws IOException {
		return client.listFiles(serverPath).length>0?true:false;
	}
	
	/**
	 * ���ָ��Ŀ¼�µ������ļ���Ŀ¼���������飩
	 * @param serverPath ������·��
	 * @return FTPFile�ļ��������飨���ļ����ؿ����飩��
	 * @throws IOException
	 */
	public FTPFile[] list(String serverPath) throws IOException {
		FTPFile mlistFile = client.mlistFile(serverPath);
		if (mlistFile == null || !mlistFile.isDirectory()) {
			logger.info("ָ����·������һ����Ч��Ŀ¼·������" + serverPath + "��");
			return null;
		}
		
		return client.listFiles(serverPath);
	}
	
	/**
	 * ���ָ��Ŀ¼�µ�����Ŀ¼��������
	 * @param serverPath ������·��
	 * @return FTPFile�ļ��������飨���ļ����ؿ����飩��
	 * @throws IOException
	 */
	public FTPFile[] listDir(String serverPath) throws IOException {
		FTPFile mlistFile = client.mlistFile(serverPath);
		if (mlistFile == null || !mlistFile.isDirectory()) {
			logger.info("ָ����·������һ����Ч��Ŀ¼·������" + serverPath + "��");
			return null;
		}
		
		return client.listDirectories(serverPath);
	}
	
	/**
	 * ���ָ��Ŀ¼�µ������ļ��������飬�������ö����չ�����ƣ���չ�������ִ�Сд��
	 * @param serverPath ������·��
	 * @param suffixes �ɱ������Ҫɸѡ���ļ���չ�����磺".png",".jpej",".gif"����
	 * @return FTPFile�ļ��������飨���ļ����ؿ����飩��
	 * @throws IOException
	 */
	public FTPFile[] listFiles(String serverPath, final String ... suffixes) throws IOException {
		FTPFile[] result;

		result = client.listFiles(serverPath, new FTPFileFilter() {
			
			@Override
			public boolean accept(FTPFile file) {
				if (file.isFile()) {
					// ��suffixesΪnullʱ�����û�û��������չ�����ƣ���ȫ������true��
					if (suffixes == null || suffixes.length == 0) {
						return true;
					}
					
					String name = file.getName();
					String suffix = null;
					try {
						suffix = name.substring(name.lastIndexOf('.'));
						suffix = suffix.toLowerCase();
					} catch (Exception e) {
						// �����±����Խ�磬ԭ�����ļ���Ŀ¼����û����չ������������û��'.'�������Ժ��ԡ�
						// e.printStackTrace();
					}
					for (String s : suffixes) {
						if (s.toLowerCase().equals(suffix)) {
							return true;
						}
					}
				}
				return false;
			}
		});
		
		return result;
	}
	
	/**
	 * �˳��ѵ�¼��FTP�û�
	 * @return Booleanֵ��true-�˳��ɹ���false-�˳�ʧ�ܣ�����Ϊ��¼����
	 * @throws IOException
	 */
	public boolean logout() throws IOException {
		return client.logout();
	}
}
