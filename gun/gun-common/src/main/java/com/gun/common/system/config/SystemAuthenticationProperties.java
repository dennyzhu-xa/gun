package com.gun.common.system.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gun.common.exception.LotteryException;
import com.gun.common.utils.EncodeDecodeUtils;
import com.gun.common.utils.LotteryConstants;
import com.gun.common.utils.StringUtils;

/**
 * 
 * @author felixli
 *
 */
public class SystemAuthenticationProperties extends ConfigProperties {
		private static Log log = LogFactory.getLog(SystemAuthenticationProperties.class);
	
		public SystemAuthenticationProperties() {
		 	super();
		}

		 /**
		 * @param propertiesFile
		 * @throws ConfigException
		 */
		public SystemAuthenticationProperties(File propertiesFile)throws LotteryException {
			super(propertiesFile);
		}
		/**
		 * @param propertiesFileName
		 * @throws ConfigException
		 */
		public SystemAuthenticationProperties(String propertiesFileName) throws LotteryException {
				super(propertiesFileName);
		}
	    /**
	     *從外部設定檔中載入所有項目集合及其相對應之設定參數組。
	     * @param propertiesFile 外部設定檔File物件。
	     */
	    public void load(File propertiesFile) throws LotteryException{
	    	this.setPropertiesFile(propertiesFile);
	
	    	log.info("Start to load config properties file ("+propertiesFile.getAbsolutePath()+")..");
	    	LineNumberReader rowRecord = null;
	    	try {
	    		rowRecord = new LineNumberReader(new BufferedReader(new FileReader(propertiesFile)));
	
	    		String line = null;
	
				while ((line = rowRecord.readLine()) != null) {
						String[] attributes = StringUtils.toArray(line, LotteryConstants.MARK_COLON);
						Properties props = new Properties();
						if (attributes != null && attributes.length == 3) {
							props.setProperty(SystemConfigManager.PROPS_ATTR_ADMIN_ID, attributes[1]);
							props.setProperty(SystemConfigManager.PROPS_ATTR_ADMIN_PASSWORD, EncodeDecodeUtils.decryptDes(attributes[2], LotteryConstants.LOTTERY));
							this.setProperties(attributes[0], props);
						}	
				}
	    	}catch(FileNotFoundException fnfe) {
	    		log.error("Config Properties file is not found !!");
	    		throw new LotteryException("Config Properties file is not found !!"+fnfe.getMessage());
	    	}catch(Exception e) {
	    		log.error("Loading table list config file is failed !"+e);
	    		throw new LotteryException("Definition error in config properties file !!");
	    	}   
	    	finally {
				try {
					  if(rowRecord!=null) rowRecord.close();
				 }catch(Exception  ee) {} 
	    	}
	    	log.info("Loading table list config file ("+propertiesFile.getAbsolutePath()+") is finished !");
	    }
	    
	    public String getUserId(String systemId) {
	    		return this.getProperty(systemId, SystemConfigManager.PROPS_ATTR_ADMIN_ID);
	    }
	    public String getPassword(String systemId) {
	    		return this.getProperty(systemId, SystemConfigManager.PROPS_ATTR_ADMIN_PASSWORD);
	    }
	    
}
