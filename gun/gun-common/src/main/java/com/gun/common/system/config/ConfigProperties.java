package com.gun.common.system.config;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

import com.gun.common.exception.LotteryException;
import com.gun.common.utils.StringUtils;



/**
 * 這是一個Singleton且持續化之系統組態設定管理者物件。所有系統整體性之設定參數均可
 * 於system.config之設定檔內。
 * @author Felixli
 */
public class ConfigProperties {
		private static final String LEFT_PARENTHESIS = "[";
		private static final String RIGHT_PARENTHESIS = "]";
		private static final String REMARK = "#";
		private long propertiesFileLastModified;
		private File propertiesFile = null;
		private Map<String, Properties> itemProperties = new HashMap<String, Properties>();
//        private List itemList = null;
        private static Log log = LogFactory.getLog(ConfigProperties.class);
        /**
         * ConfigProperties 建構子。
         */
        public ConfigProperties() {
        }
        /**
         * ConfigProperties 建構子。
         * @param propertiesFileName 相對路徑下之外部設定檔名。
         */
        public ConfigProperties(String propertiesFileName) throws LotteryException{
        	load(propertiesFileName);
        }
        /**
         * ConfigProperties 建構子。
         * @param propertiesFile 外部設定檔之File物件。
         */
        public ConfigProperties(File propertiesFile) throws LotteryException{
        	load(propertiesFile);
        }			
        /**
         *清除所有組態環境設定。
         */
        public void clear() {
        	if (this.itemProperties !=	null) this.itemProperties.clear(); 
//        	if (this.itemList != null) this.itemList.clear();
        }
        /**
         *判斷是否存在某一項目於此組態定義物件。
         * @param itemName 項目名稱。
         * @return boolean
         */
        public boolean containsKey(String itemName) {
        	if (this.itemProperties == null) return false;
        	return this.itemProperties.containsKey(itemName);
        }
        /**
         *取得所有項目名稱。
         * @return List - 所有項目集合
         */
        public String[] getkeys(){
        	if (CollectionUtils.isEmpty(this.itemProperties)) return null;
        	return this.itemProperties.keySet().toArray(new String[this.itemProperties.size()]);
        	//return itemList;
        }
        /**
         *取得某一項目下之所有設定資訊。
         * @param itemName 某一項目名稱。
         * @return Properties 
         */
        public Properties getProperties(String itemName) {
        	if (CollectionUtils.isEmpty(itemProperties)) return null;
        	if (!itemProperties.containsKey(itemName)) return null;
        	return itemProperties.get(itemName);
        }
        /**
         *取得某一項目下之某設定參數之設定值。
         * @param itemName 某一項目名稱。
         * @param propertyName 某一設定參數名稱。
         * @return String - 設定值 。
         */
        public String getProperty(String itemName, String propertyName) {
        	Properties props = getProperties(itemName);
        	if (props == null) return null;
        	return props.getProperty(propertyName);
        }
        public String[] getSimilarProperies(String itemName, String propertyName) {
        	Properties props = getProperties(itemName);
        	if (props == null) return null;
        	Iterator<?> keys = props.keySet().iterator();
        	String key = null;
        	List<String> values = new ArrayList<String>();
        	while (keys.hasNext()) {
        		key = (String)keys.next();
        		if (key != null && key.startsWith(propertyName)) {
        			values.add(props.getProperty(key));
        		}
        	}
        	if (CollectionUtils.isEmpty(values)) return null;
        	return values.toArray(new String[values.size()]);
        }


        /**
         *判斷此組態定義物件之內容是否為空。
         * @return boolean
         */
        public boolean isEmpty(){
        	return CollectionUtils.isEmpty(itemProperties);
        }
        /**
         *從外部設定檔中載入所有項目集合及其相對應之設定參數組。
         * @param propertiesFileName 相對路徑下之外部設定檔名。
         */
        public void load(String propertiesFileName) throws LotteryException{
        	File file = new File(propertiesFileName);
        	if (!file.exists()) {
        		try {
        			URL url = Thread.currentThread().getContextClassLoader().getResource(propertiesFileName);
        			file = new File(url.getFile());
        			//file=new File(ConfigProperties.class.getResource(propertiesFileName).getFile());
        		}catch(Exception e) {
        			log.error("Config Properties file is not found !!");
        			throw new LotteryException("Config Properties file is not found !!"+e.getMessage());
        		}	 		
        	}
        	load(file);
        }			
        /**
         *從外部設定檔中載入所有項目集合及其相對應之設定參數組。
         * @param propertiesFile 外部設定檔File物件。
         */
        public void load(File propertiesFile) throws LotteryException{
        	this.propertiesFile = propertiesFile;

        	log.info("Start to load config properties file ("+propertiesFile.getAbsolutePath()+")..");
        	LineNumberReader rowRecord = null;
        	try {
        		rowRecord = new LineNumberReader(new BufferedReader(new FileReader(propertiesFile)));

        		String line = null;

        		if (itemProperties == null) itemProperties = new HashMap<String, Properties>();
										        
				String itemName = "";

				while ((line = rowRecord.readLine()) != null) {
					if (line.startsWith(LEFT_PARENTHESIS) && line.endsWith(RIGHT_PARENTHESIS)) {
						Properties props = new Properties();
						int startIndex = line.indexOf(LEFT_PARENTHESIS) + 1;
						int endIndex = line.lastIndexOf(RIGHT_PARENTHESIS);
						itemName = StringUtils.trimWhitespace(line.substring(startIndex, endIndex));
						itemProperties.put(itemName, props);
						continue;
					}

					if (line.startsWith(REMARK)) continue;
					
					Properties props = itemProperties.get(itemName);
					props.load(new ByteArrayInputStream(line.getBytes()));
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
					  if (rowRecord != null) rowRecord.close();
				 }catch(Exception  ee) {} 
        	}
        	log.info("Loading table list config file ("+propertiesFile.getAbsolutePath()+") is finished !");
        }
        /**
         *設定某一項目集合及其相對之設定參數組。
         * @param itemName 某一項目名稱。
         * @param itemProperties Properties資料型態之設定參數組。
         */
        public void setProperties(String itemName, Properties itemProperties) {
        	if (!StringUtils.hasText(itemName) || itemProperties == null) return;
        	if (this.itemProperties == null) this.itemProperties = new HashMap<String, Properties>();
        	this.itemProperties.put(itemName,itemProperties);
        }
        /**
         *設定某一項目集合及其相對之設定參數組。
         * @param itemName 某一項目名稱。
         * @param propertyName 某一設定參數名稱。
         * @param propertyValue 某一設定參數名稱相對應之參數值。
         */
        public void setProperty(String itemName, String propertyName, String propertyValue) {
        	if (!StringUtils.hasText(itemName) || !StringUtils.hasText(propertyName)) return;
        	if (this.itemProperties == null) this.itemProperties = new HashMap<String, Properties>();

        	Properties props = null;

        	if (!this.itemProperties.containsKey(itemName)) {
        		props = new Properties();
        		props.setProperty(propertyName, propertyValue);
        		this.itemProperties.put(itemName, props);
        	} else {
        		props = this.itemProperties.get(itemName);
        		props.setProperty(propertyName,propertyValue);
        	}       
        }
        /**
         *取得設定項目集合之數目。
         * @return int
         */
        public int size() {
        	if (CollectionUtils.isEmpty(itemProperties)) return 0;
        	return itemProperties.size();
        }
//        /**
//         * 
//         * @return
//         */
//        public long lastModified() {
//        	if (this.propertiesFile == null) return -1;
//        	return this.propertiesFile.lastModified();
//        }
        /**
         *若組態檔內容已變更,則更新所有組態環境設定。
         */
        public synchronized void refresh(){
//        	long lastModified = lastModified();
//        	if (lastModified <= 0) return;
        	try {
        		File tempFile = new File(this.propertiesFile.getAbsolutePath());
										
        		long tmpLastModified = tempFile.lastModified();
        		if (tmpLastModified > this.propertiesFileLastModified) {
        			this.load(tempFile);
        			this.propertiesFileLastModified = tmpLastModified;
        		}
        	}catch(Exception e) {
        		log.error("refresh config properties are failed:"+e.getMessage());
        	}
        }
        
        public void remove(String itemName) {
        	if (!StringUtils.hasText(itemName) || CollectionUtils.isEmpty(itemProperties)) return;
        	itemProperties.remove(itemName);
        }
        public void remove(String itemName, String propertyName) {
        	if (!StringUtils.hasText(itemName) || 
        		!StringUtils.hasText(propertyName) || 
        		CollectionUtils.isEmpty(itemProperties) ||
        		!itemProperties.containsKey(itemName)) return;
        	Properties props = itemProperties.get(itemName);
        	props.remove(propertyName);
        }
        /**
         *取得設定檔物件。
         * @return File - 一個File物件
         */
        public File getPropertiesFile() {
        	return this.propertiesFile;
        }
        
        protected void setPropertiesFile(File propertiesFile) {
        	this.propertiesFile = propertiesFile;
        }
}

	
