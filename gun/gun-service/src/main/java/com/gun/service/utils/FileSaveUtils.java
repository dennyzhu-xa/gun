package com.gun.service.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

import com.gun.common.system.config.WfSystemConfigManager;
import com.gun.common.utils.LotteryConstants;
import com.gun.common.utils.StringUtils;

/**
 * Purpose: 保存文件的工具类
 * @author KevinShen
 * @since  JDK 1.7
 * @date   2017年10月24日
 * @MaintenancePersonnel KevinShen
 */
public class FileSaveUtils {
	
	/**
	 * Purpose:
	 * @author KevinShen
	 * @param file 文件
	 * @param fileName 文件名称
	 * @throws IOException 异常
	 * @return String 文件保存的相对路径
	 */
	public static String saveFile(MultipartFile file, String fileName) throws IOException{
		return saveFile(file, LotteryConstants.DEFAULT_TYPE, fileName);
	}
	
	/**
	 * Purpose:
	 * @author KevinShen
	 * @param file 文件
	 * @param type 分类路径
	 * @param fileName 文件名称
	 * @throws IOException 异常
	 * @return String 文件保存的相对路径
	 */
	public static String saveFile(MultipartFile file, String typePath, String fileName) throws IOException{
		if (file == null || StringUtils.isEmpty(typePath) || StringUtils.isEmpty(fileName))
			return null;
		String rootPath = WfSystemConfigManager.getProperty(LotteryConstants.FILE, LotteryConstants.PATH);
		String mappingPath = typePath + LotteryConstants.MARK_SEPARATE + fileName;
		boolean result = copyFile(file.getInputStream(), rootPath + LotteryConstants.MARK_SEPARATE + mappingPath, true);
		return result ? mappingPath : null;
	}
	
	/**
	 * Purpose:
	 * @author KevinShen
	 * @param in 输入流
	 * @param destFileName 保存的文件全路径
	 * @param overlay 文件存在是否删除
	 * @return boolean 是否成功
	 */
	public static boolean copyFile(InputStream in, String destFileName, boolean overlay) {  
        // 判断目标文件是否存在  
        File destFile = new File(destFileName);  
        if (destFile.exists()) {  
            // 如果目标文件存在并允许覆盖  
            if (overlay) {  
                // 删除已经存在的目标文件，无论目标文件是目录还是单个文件  
                new File(destFileName).delete();  
            }  
        } else {  
            // 如果目标文件所在目录不存在，则创建目录  
            if (!destFile.getParentFile().exists()) {  
                // 目标文件所在目录不存在  
                if (!destFile.getParentFile().mkdirs()) {  
                    // 复制文件失败：创建目标文件所在目录失败  
                    return false;  
                }  
            }
        }
        // 复制文件  
        int byteread = 0; // 读取的字节数  
        OutputStream out = null;  
        try {  
            out = new FileOutputStream(destFile);  
            byte[] buffer = new byte[1024];  
  
            while ((byteread = in.read(buffer)) != -1) {  
                out.write(buffer, 0, byteread);  
            }  
            return true;  
        } catch (FileNotFoundException e) {  
            return false;  
        } catch (IOException e) {  
            return false;  
        } finally {  
            try {  
                if (out != null)  
                    out.close();  
                if (in != null)  
                    in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  

}
