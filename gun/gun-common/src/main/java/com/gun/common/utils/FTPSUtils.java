package com.gun.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.springframework.util.StringUtils;

/**
 * Purpose: FTPS之公用程式
 * @author Ben Wang
 * @since  JDK 1.5
 * @date   2014/10/08
 * @MaintenancePersonnel Ben Wang
 */
public class FTPSUtils {
	/**
	 * 預設傳輸埠號<br/>
	 * 使用TCP 21 Port進行連接，就是Explicit SSL<br/>
	 * 使用TCP 990 Port進行連接，就是Implicit SSL<br/>
	 */
	public static int DEFAULT_PORT = 21;
	
	/**上傳完成代碼**/
	public static int REPLY_CODE_COMPLETED = 226;
	
	/**TLS/SSL**/
	public static String PROTOCOL = "TLS";
	
	/**
	 * 使用TCP 21 Port進行連接，就是Explicit SSL<br/>
	 * 使用TCP 990 Port進行連接，就是Implicit SSL<br/>
	 */
	public static boolean IS_IMPICIT = false;
	
	private static final Log log = LogFactory.getLog(FTPSUtils.class);
	
	private FTPSClient client;
	
	/**
	 * FTPSUtils建構子
	 */
	public FTPSUtils() {}
	
	/**
	 * FTPSUtils建構子	
	 * @param ip FTP Server IP
	 * @param port FTP Server Port
	 * @param userId FTP Server登入ID
	 * @param password FTP Server登入密碼
	 * @throws Exception
	 */
	public FTPSUtils(String ip, int port, String userId, String password) throws Exception {
		if (StringUtils.hasText(ip)) {
			client = new FTPSClient(PROTOCOL, IS_IMPICIT);
			connect(ip, port, userId, password);
		}
	}
	
	/**
	 * FTPSUtils建構子	
	 * @param ip FTP Server IP
	 * @param port FTP Server Port
	 * @param userId FTP Server登入ID
	 * @param password FTP Server登入密碼
	 * @param protocol
	 * @param isImpicit
	 * @throws Exception
	 */
	public FTPSUtils(String ip, int port, String userId, String password, String protocol, boolean isImpicit) throws Exception {
		if (StringUtils.hasText(ip)) {
			client = new FTPSClient(protocol, isImpicit);
			connect(ip, port, userId, password);
		}
	}
	
	/**
	 * FTPS連線
	 * @param ip FTP Server IP
	 * @param port FTP Server Port
	 * @param userId FTP Server登入ID
	 * @param password FTP Server登入密碼
	 * @throws Exception
	 * @return boolean
	 */ 
	public boolean connect(String ip, int port, String userId, String password) throws Exception {
		if (StringUtils.hasText(ip)) {
			log.debug("FTPS request connect to " + ip + ":" + port);
			client.connect(ip, port);
			int reply = client.getReplyCode();
			if (FTPReply.isPositiveCompletion(reply)) {
				if (client.login(userId, password)) {
					//設定ftps
					client.execPBSZ(0);// Set protection buffer size
					client.execPROT("P");// Set data channel protection to private
					client.enterLocalPassiveMode();// Enter local passive mode
					client.setFileType(FTP.BINARY_FILE_TYPE);
					return true;
				}
			}
			disconnect();
		}
		return false;
	}
	
	/**
	 * 登出
	 * @throws Exception
	 */
	public void logout() throws Exception {
		client.logout();
	}
	
	/**
	 * 中斷連線
	 * @throws Exception
	 */
	public void disconnect() throws Exception {
		client.disconnect();
	}
	
	/**
	 * 下載檔案
	 * @param remoteFile 欲下載之remote檔案
	 * @param localFile 下載後之local檔案
	 * @throws IOException
	 * @return boolean
	 */
	public boolean downloadFile(String remoteFile, String localFile) throws IOException {	  
		boolean rst = false;
		if (StringUtils.hasText(remoteFile) && StringUtils.hasText(localFile)) {
			FileOutputStream out = null;
			try {
				File file = new File(localFile);
				if (!file.exists()) {
					out = new FileOutputStream(localFile);
					rst = client.retrieveFile(remoteFile, out);
					out.flush();
				} else {
					rst = true;
				}
			} finally {
				if (out != null) {
					out.close();
				}
			}
		}
		log.debug("下載檔案" + remoteFile + "到" + localFile + " => " + rst);
		return rst;
	}
	
	/**
	 * 上傳檔案
	 * @param localFile 欲上傳之local檔案
	 * @param remoteFile 上傳後之remote檔案
	 * @throws IOException
	 * @return boolean
	 */
	public boolean uploadFile(String localFile, String remoteFile) throws IOException {
		return uploadFile(localFile, remoteFile, FTP.BINARY_FILE_TYPE);
	}
	
	/**
	 * 上傳檔案
	 * @param localFile 欲上傳之local檔案
	 * @param remoteFile 上傳後之remote檔案
	 * @param fileType FTP.ASCII_FILE_TYPE 或 FTP.BINARY_FILE_TYPE 等等
	 * @throws IOException
	 * @return boolean
	 */
	public boolean uploadFile(String localFile, String remoteFile, int fileType) throws IOException {
		boolean isSuccess = false;
		if (StringUtils.hasText(remoteFile) && StringUtils.hasText(localFile)) {
			FileInputStream in = new FileInputStream(FileUtils.validate(localFile));
			try {
				client.setFileType(fileType);
				isSuccess = client.storeFile(remoteFile, in);
			} finally {
				in.close();
			}
		}
		log.debug("上傳檔案" + localFile + "到" + remoteFile + " => " + isSuccess);
		return isSuccess;
	}
	
	/**
	 * 變更工作目錄
	 * @param remotePath 欲變更的工作目錄
	 * @throws IOException
	 * @return boolean
	 */
	public boolean changeDir(String remotePath) throws IOException {
		boolean isSuccess = client.changeWorkingDirectory(remotePath);
		log.debug("變更工作目錄到" + remotePath + " => " + isSuccess);
		return isSuccess;
    }
	
	/**
	 * 建立目錄
	 * @param remotePath 欲建立的目錄
	 * @throws IOException
	 * @return boolean
	 */
	public boolean createDirectory(String remotePath) throws IOException {
		boolean isSuccess = client.makeDirectory(remotePath);
		log.debug("建立目錄" + remotePath + " => " + isSuccess);
		return isSuccess;
	}
	
	/**
	 * 刪除空目錄
	 * @param remotePath 目錄路徑
	 * @throws IOException
	 * @return boolean
	 */
	public boolean removeDirectory(String remotePath) throws IOException {
		boolean isSuccess = client.removeDirectory(remotePath);
		log.debug("刪除空目錄" + remotePath + " => " + isSuccess);
	    return isSuccess;
	}
	
	/** 
     * 刪除目錄
     * @param remotePath 目錄路徑
     * @param isAll 是否删除所有子目錄和檔案(如果有的話)
     * @throws IOException
     * @return boolean
     */  
	public boolean removeDirectory(String remotePath, boolean isAll) throws IOException {
		if (!isAll) {
			return removeDirectory(remotePath);
		}
		//遍尋子目錄和檔案
		FTPFile[] ftpFileArr = client.listFiles(remotePath);
		if (ftpFileArr == null || ftpFileArr.length == 0) {
			return removeDirectory(remotePath);
		}
		
		for (int i = 0; i < ftpFileArr.length; i++) {
			FTPFile ftpFile = ftpFileArr[i];
			String name = ftpFile.getName();
			if(ftpFile.isDirectory()) {
				removeDirectory(remotePath + "/" + name, true);
			} else if (ftpFile.isFile()) {
				deleteFile(remotePath + "/" + name);
			} else if (ftpFile.isSymbolicLink()) {
				
			} else if (ftpFile.isUnknown()) {
				
			}
		}
		return removeDirectory(remotePath);
	}
	
	/**
     * 删除檔案
     * @param remoteFile 上傳後之remote檔案 
     * @throws IOException 
     * @return boolean
     */  
	public boolean deleteFile(String remoteFile) throws IOException {
		boolean isSuccess = client.deleteFile(remoteFile);
		log.debug("删除檔案" + remoteFile + " => " + isSuccess);
	    return isSuccess;
	}
	
	/**
	 * 找出remoteDir有哪些檔案
	 * @param remoteDir
	 * @throws Exception
	 * @return Vector<String>
	 */
	public Vector<String> listFileInDir(String remoteDir) throws Exception {
        if (changeDir(remoteDir)) {
            FTPFile[] files = client.listFiles();
            Vector<String> v = new Vector<String>();
            for (FTPFile file : files) {
                if (!file.isDirectory()) {
                    v.addElement(file.getName());
                }
            }
            return v;
        } else {
            return null;
        }
    }
	
	/**
	 * 找出remoteDir有哪些子目錄
	 * @param remoteDir
	 * @throws Exception
	 * @return Vector<String>
	 */
	public Vector<String> listSubDirInDir(String remoteDir) throws Exception {
        if (changeDir(remoteDir)) {
            FTPFile[] files = client.listFiles();
            Vector<String> v = new Vector<String>();
            for (FTPFile file : files) {
                if (file.isDirectory()) {
                    v.addElement(file.getName());
                }
            }
            return v;
        } else {
            return null;
        }
    }
	
	/**
	 * 取得FTPSClient物件
	 */
	public FTPSClient getClient() {
		return client;
	}
	
	/**
	 * 設定FTPSClient物件
	 * @param client FTPSClient物件
	 */
	public void setClient(FTPSClient client) {
		this.client = client;
	}
	
}