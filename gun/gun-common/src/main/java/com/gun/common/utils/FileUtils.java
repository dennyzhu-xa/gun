package com.gun.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Purpose: 處理文件上傳下載工具
 * @author candicechen
 * @since  JDK 1.5
 * @date   2014/9/15
 * @MaintenancePersonnel candicechen
 */
public class FileUtils {
	private static final Log log = LogFactory.getLog(FileUtils.class);
	public static final int BUFFER = 8192;
	public static final String ZIP_SUFFIX = ".zip";
	public static final String ZIP_TEMP_PATH = "C:\\CMS_FILE_TEMP\\";
	/**
	 * Purpose:文件上傳
	 * @param filePath:需要上傳Server的路徑
	 * @param inputFile:前段頁面選擇上傳的文件路徑
	 * @throws Exception:出錯時返回Exception
	 * @return void
	 */
	public static void upload(String filePath,String inputFile) throws Exception{
		upload(filePath,inputFile,null);
	}
	/**
	 * Purpose:文件上傳
	 * @param filePath:需要上傳Server的路徑
	 * @param inputFile:前段頁面選擇上傳的文件路徑
	 * @param inpjutFileName:需要寫入新文件的名稱
	 * @throws Exception:出錯時返回Exception
	 * @return void
	 */
	public static void upload(String filePath,String inputFile,String inputFileName) throws Exception{
		log.debug(" in FileUtils upload method filePath---@@@@@"+filePath);
		log.debug(" in FileUtils upload method inputFile---@@@@@"+inputFile);
		//上傳檔案路徑
		File fileFord = new File(filePath);
		//判斷儲存路徑是否存在，若不存在，則重新新建
		if (!fileFord.exists() || !fileFord.isDirectory()) {
			fileFord.mkdirs();
		}
		
		File file = new File(inputFile);
		String fileName = file.getName();
		String attachment = "";
		if(StringUtils.hasText(inputFileName)){
			attachment = filePath+inputFileName;
		}else{
			attachment = filePath+fileName;
		}
		File attachmentFile = new File(attachment);
		InputStream is = null;
		OutputStream fos = null;
		try {
			is = new FileInputStream(inputFile);
			fos = new FileOutputStream(attachmentFile);
			byte[] buffer=new byte[1024];
			int length=0;
			while(-1!=(length=(is.read(buffer))))
			{
				fos.write(buffer, 0, length);
			}
			fos.close();
		} catch (IOException e) {
			log.error("FileUtils upload Exception "+ e);
		} finally{
			try {
				if(is!=null){
					is.close();
				}				
			} catch (IOException e) {
				log.error("FileUtils upload Exception "+ e);
			}
			try {				
				if(fos!=null){
					fos.close();
				}
			} catch (IOException e) {
				log.error("FileUtils upload Exception "+ e);
			}
		}
	}
	/**
	 * Purpose:刪除server上已有的檔案
	 * @param inputfile:要刪除的檔案
	 * @throws Exception:出錯時拋出Exception
	 * @return void
	 */
	public static void removeFile(String inputfile) throws Exception{
		try{
			File file = new File(inputfile);
			if(file.exists()){
				if(file.isFile()){
					file.delete();
				}else if(file.isDirectory()){
					File files[] = file.listFiles(); 
					for(int i=0;i<files.length;i++){ 
						files[i].delete();
					}
					file.delete();
				}
			}
		}catch(Exception e){
			log.error("FileUtils removeFile Exception "+ e);
		}
	}
	
	/**
	 * Purpose:下載檔案
	 * @param response:上下文response請求
	 * @param filePath:需要下載的檔案
	 * @return void
	 */
	public static void download(HttpServletResponse response,String filePath) {
		InputStream ins = null;
		BufferedInputStream bins = null;
		OutputStream outs = null;
		BufferedOutputStream bouts = null;
		try {
			filePath = validate(filePath);
			File file = new File(filePath);
			String fileName = file.getName();
			if (file.exists()) {
				filePath = validate(filePath);
				ins = new FileInputStream(filePath);    
				bins = new BufferedInputStream(ins);// 放到缓冲流里面    
				outs = response.getOutputStream();// 获取文件输出IO流    
				bouts = new BufferedOutputStream(outs);    
				response.setContentType("application/x-download");// 设置response内容的类型
				response.setHeader("Content-disposition","attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));// 设置头部信息    
				int bytesRead = 0;    
				byte[] buffer = new byte[ins.available()];    
				//开始向网络传输文件流    
				while ((bytesRead = bins.read(buffer, 0, 1024)) != -1) {    
					bouts.write(buffer, 0, bytesRead);    
				}    
			} else{
				log.error("FileUtils downFile 文件不存在!!!!");
			}
			if( bouts != null )
				bouts.flush();// 這裡一定要調用flush()方法			
			if( ins != null )
				ins.close();			
			if( bins != null )
				bins.close();			
			if( outs != null )
				outs.close();				
			if( bouts != null )
				bouts.close();				
		} catch (Exception e) {
			log.error("FileUtils downFile Exception "+ e);
		}
		/*
		 * Security:Variable 'ins' is not closed within a "finally" block
		 * Security:Variable 'bins' is not closed within a "finally" block
		 * Security:Variable 'bouts' is not closed within a "finally" block
		 * Modified by Akuma 20141208
		 */
		finally{
			try{
				if (ins != null) ins.close();
				if (bins != null) bins.close();
				if (bouts != null) bouts.close();				
			}catch(Exception ee){
				log.error(" download failed !! " + ee );
			}
		}
	}
	 public static void downFile(String filepath,String fileName,HttpServletResponse response) {	
			FileInputStream fileinputstream = null;
			PrintWriter out = null;
			try {		
			response.reset();		
			File file = new File(filepath);
//			String fileName = file.getName();
			if (file.exists()) {
				response.setContentType("application/x-download");// 设置response内容的类型
				response.setHeader("Content-disposition","attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));// 设置头部信息   
				fileinputstream = new FileInputStream(validate(filepath));
				out = response.getWriter();
				int i;			
				//开始向网络传输文件流    
				while ((i = fileinputstream.read()) != -1) {
							out.write(i);
							}	
			} else{
				log.error("FileUtils downFile 文件不存在!!!!");
			}
			if(out != null) out.flush();
			if(out != null) out.close();
			if(fileinputstream != null) fileinputstream.close();
			} catch (Exception e) {
				log.error("FileUtils downFile Exception " + e);
			}finally{
				try{
				if(out != null)  out.close();
	    			if(fileinputstream != null)fileinputstream.close();
						} catch (IOException ee) {
							log.error(" download failed !! " + ee);
						}
			}
}
	/**
	 * Purpose:上傳檔案
	 * @param:需要上傳至文件的路徑
	 * @param file:要上傳的檔案
	 * @return void
	 */
    public static File upload(String uploadFilePath,MultipartFile file){
    	return upload(uploadFilePath,file,null);
    }
    
    /**
     * Purpose::上傳檔案
     * @param uploadFilePath:需要上傳至文件的路徑
     * @param file:要上傳的檔案
     * @param outPutFileName:輸出的文件名稱
     * @return File
     */
    public static File upload(String uploadFilePath,MultipartFile file,String outPutFileName){
    	BufferedOutputStream buffStream = null;
    	FileOutputStream outPutStream = null;
    	if (!file.isEmpty()) {
            try {
                String fileName = "";
                byte[] bytes = file.getBytes();
                File fileDir = new File(uploadFilePath);
        		//判斷儲存路徑是否存在，若不存在，則重新新建
        		if (!fileDir.exists() || !fileDir.isDirectory()) {
        			fileDir.mkdirs();
        		}
                //讀取文件
        		if(StringUtils.hasText(outPutFileName)){
        			fileName = outPutFileName;
        		}else{
        			fileName = file.getOriginalFilename();
        		}
                File uploadFile = new File(uploadFilePath+fileName);
                //定義outputStream
                outPutStream = new FileOutputStream(uploadFile);
                buffStream = new BufferedOutputStream(outPutStream);
                buffStream.write(bytes);
                buffStream.close();
                return uploadFile;
            } catch (Exception e) {
            	log.error("FileUtils downFile Exception "+ e);
            }finally{
            	try {
            		if(outPutStream!=null){
            			outPutStream.close();
            		}
					if(buffStream!=null){
						buffStream.close();
					}
				} catch (IOException e) {
					log.error("FileUtils downFile Exception "+ e);
				}
            }
        }
    	return null;
    }
    /**
     * 
     * Purpose:
     * @param src
     * @param dest
     * @param newName
     * @throws Exception
     * @return void
     */
    public static void copyFile(String src ,String dest,String newName) throws Exception {
		File fileFord = new File(dest);
		//判斷儲存路徑是否存在，若不存在，則重新新建
		if (!fileFord.exists() || !fileFord.isDirectory()) {
			fileFord.mkdirs();
		}
    	if(StringUtils.hasText(src)){
    		File file = new File(src);
        	if(file.exists()&&file.isFile()){
        		InputStream is = null;
        	    OutputStream os = null;
        	    try {
        	        is = new FileInputStream(src);
        	        os = new FileOutputStream(dest+newName);
        	        byte[] buffer = new byte[1024];
        	        int length;
        	        while ((length = is.read(buffer)) > 0) {
        	            os.write(buffer, 0, length);
        	        }
        	        os.close();
        	    }catch(Exception e){
        	    	throw new Exception(e);
        	    }finally {
        			try {
        				if(is!=null){
        					is.close();
        				}				
        			} catch (IOException e) {
        				log.error("FileUtils copyFile Exception "+ e);
        			}
        			try {				
        				if(os!=null){
        					os.close();
        				}
        			} catch (IOException e) {
        				log.error("FileUtils copyFile Exception "+ e);
        			}
//        	    	if(os != null)
//        	    		os.close();
//        	    	if(is != null)
//        	    		is.close();
        	    }
    		}else{
    			log.error("文件不存在");
    		}
    	}else{
    		log.error("文件不存在");
    	}
	}
    
    /**
     * Purpose:压缩文件
     * @param zipFile:压缩后的文件路徑
     * @param zipFileName:壓縮后文件名稱
     * @param srcPathNames:待壓縮文件
     * @throws RuntimeException
     * @throws IOException
     * @return void
     */
    public static void compress(String zipFile, String zipFileName,List<String> srcPathNames) throws RuntimeException, IOException{
    	log.debug(FileUtils.class.getName()+".compress zipFile ==== >"+zipFile);
    	log.debug(FileUtils.class.getName()+".compress zipFileName ==== >"+zipFileName);
    	ZipOutputStream zos = null;
		try {
			File fileFord = new File(zipFile);
			if (!fileFord.exists() || !fileFord.isDirectory()) {
				fileFord.mkdirs();
			}
			OutputStream os = new FileOutputStream(zipFile+zipFileName);
		    BufferedOutputStream bos = new BufferedOutputStream(os);
		    zos = new ZipOutputStream(bos);
		    zos.setEncoding(FileUtils.validate(System.getProperty("sun.jnu.encoding")));//设置文件名编码方式
	    	List<File> fileList = new ArrayList<File>();
	    	File file = null;
	    	if(!CollectionUtils.isEmpty(srcPathNames)){
	    		for (String srcPathName : srcPathNames) {
	    			log.debug(FileUtils.class.getName()+".compress srcPathName ==== >"+srcPathName);
	    			file = new File(srcPathName);
	    			if (!file.exists()){
	    				log.error(FileUtils.class.getName()+".compress "+srcPathName+" 不存在！");
	    			}else{
	    				fileList.add(file);
	    			}
				}
	    		compressFiles(fileList, zos);
	    		zos.close();
	    	}else{
	    		log.debug(FileUtils.class.getName()+".compress 带压缩文件为空");
	    	}
		} catch (Exception e) {
			log.error(FileUtils.class.getName()+".compress is Failed!!!!",e);
			throw new RuntimeException(e);
		}finally {
		   if(zos != null) {
			    zos.closeEntry();
			    zos.close();
		   }
	   }
	}

    /**
     * Purpose:將多個文件壓縮為1個
     * @param fileLists:帶壓縮文件集合
     * @param out:ZIP輸出流
     * @throws RuntimeException
     * @return void
     */
	private static void compressFiles(List<File> fileLists, ZipOutputStream out) throws RuntimeException {
		try{
			if (!CollectionUtils.isEmpty(fileLists)){
				log.debug(FileUtils.class.getName()+".compressFiles fileLists size ====》 "+fileLists.size());
				for (File file : fileLists) {
					log.debug(FileUtils.class.getName()+".compressFiles file ====》 "+file.getAbsolutePath()+file.getName());
					if(file.exists()){
						compressFile(file, out, file.getParent());
					}
				}
			}else{
				log.debug(FileUtils.class.getName()+".compressFiles 带压缩文件为空");
			}
		}catch (Exception e) {
			log.error(FileUtils.class.getName()+".compressFiles is Failed!!!!",e);
			throw new RuntimeException(e);
		}
		
	}
	/**
	 * Purpose:压缩单个文件
	 * @param file:待压缩文件
	 * @param out:输出的文件流
	 * @param basedir:压缩存放的位置
	 * @return void
	 * @throws IOException 
	 */
    private static void compressFile(File file, ZipOutputStream out, String baseDir) throws IOException {
    	BufferedInputStream bis = null;
		try {
			log.debug(FileUtils.class.getName()+".compressFile file Name ====》 "+file.getAbsolutePath()+file.getName());
			if (!file.exists()) {
				return;
			}
			bis = new BufferedInputStream(new FileInputStream(file));
			String pathName = file.getPath().substring(baseDir.length() + 1);
			out.putNextEntry(new ZipEntry(pathName));
			int count = 0;
			byte data[] = new byte[BUFFER];
			while ((count = bis.read(data)) != -1) {
				out.write(data, 0, count);
			}
		} catch (Exception e) {
			log.error(FileUtils.class.getName()+".compressFile is Failed!!!!",e);
			throw new RuntimeException(e);
		}finally {
		   if(bis != null) {
			   bis.close();
		   }
	   }
	}
    
    public static String validate(String strParam) {
    	//log.debug(" strParam >>> " + temp );
		return strParam;    	
    }
    
    public static void encrImg(byte[] pic) throws Exception {  

      for (int i = 0; i < pic.length; i++) {
        pic[i] = (byte) (pic[i] ^ LotteryConstants.XOR_CONST);
      }
      
  }
    
    public static void encrImg(File src, File dest) throws Exception {  
      ImageInputStream fis = new FileImageInputStream(src);  
      ImageOutputStream fos = new FileImageOutputStream(dest);  

      int read;  
      while ((read = fis.read()) > -1) {  
          fos.write(read ^ LotteryConstants.XOR_CONST);  
      }  
      fos.flush();  
      fos.close();  
      fis.close();  
  }
}
