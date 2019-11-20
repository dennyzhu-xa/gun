package com.gun.server.controller.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gun.common.utils.LotteryConstants;

public class FileManager {
	
	private static final Log log = LogFactory.getLog(FileManager.class);	
	
	/** 
     * 获得指定文件的byte数组 
     */  
	public byte[] getBytes(File file){  
        byte[] buffer = null;  
        try {  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(3000);  
            byte[] b = new byte[3000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer;  
    }
    
    /** 
     * 根据byte数组，生成文件 
     */  
    public static void getFile(byte[] bfile, String filePath,String fileName) {  
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        try {  
            File dir = new File(filePath);  
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在  
                dir.mkdirs();  
            }  
            file = new File(filePath+"\\"+fileName);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(bfile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
    } 
    
    public static void downFile(String filepath,String fileName,HttpServletResponse response) { 
    	   FileInputStream fileinputstream = null;
    	   PrintWriter out = null;
    	   try {  
    	   response.reset();  
    	   File file = new File(filepath +LotteryConstants.MARK_SLASH+ fileName);
    	   if (file.exists()) {
    	    response.setContentType("application/x-download");// 设置response内容的类型
    	    response.setHeader("Content-disposition","attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));// 设置头部信息   
    	    fileinputstream = new FileInputStream(filepath +LotteryConstants.MARK_SLASH+ fileName);
    	    response.setContentLength((int)fileinputstream.getChannel().size());
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
    
}
