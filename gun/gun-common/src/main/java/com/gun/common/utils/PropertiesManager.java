package com.gun.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 本地参数文件信息获取
 * @author davidsheng
 *
 */
public class PropertiesManager {

	private static Log LOGGER = LogFactory.getLog(PropertiesManager.class);
	
	private static final String PRO_FILE_NAME 							= "constant.properties";
	public static final int LOCAL_WEB_IP								= 1;
	public static final int LOCAL_WEB_PORT								= 2;
	public static final int SERVER_API_IP								= 3;
	public static final int SERVER_API_PORT								= 4;
	public static final int TMS_SOCKET_IP								= 5;
	public static final int TMS_SOCKET_PORT								= 6;
	public static final int PEM_PATH									= 7;
	public static final int APK_PATH									= 8;
	public static final int ADVER_PATH									= 9;
	public static final int GOOGLE_API_SERVER_IP						= 10;
	public static final int GOOGLE_API_SERVER_MERCHANT_PORT				= 11;
	public static final int GOOGLE_API_SERVER_CYBERSMART_PORT			= 12;
	public static final int REPORT_PATH									= 13;
	
	public static final String LOCAL_WEB_IP_KEY							= "LOCAL_WEB_IP";
	public static final String LOCAL_WEB_PORT_KEY						= "LOCAL_WEB_PORT";
	public static final String SERVER_API_IP_KEY						= "SERVER_API_IP";
	public static final String SERVER_API_PORT_KEY						= "SERVER_API_PORT";
	public static final String TMS_SOCKET_IP_KEY						= "TMS_SOCKET_IP";
	public static final String TMS_SOCKET_PORT_KEY						= "TMS_SOCKET_PORT";
	public static final String PEM_PATH_KEY								= "PEM_PATH";
	public static final String APK_PATH_KEY								= "APK_FOLDER";
	public static final String ADVER_PATH_KEY							= "ADVER_FOLDER";
	public static final String GOOGLE_API_SERVER_IP_KEY					= "GOOGLE_API_SERVER_IP";
	public static final String GOOGLE_API_SERVER_MERCHANT_PORT_KEY		= "GOOGLE_API_SERVER_MERCHANT_PORT";
	public static final String GOOGLE_API_SERVER_CYBERSMART_PORT_KEY	= "GOOGLE_API_SERVER_CYBERSMART_PORT";
	public static final String REPORT_PATH_KEY							= "REPORT_PATH";
	
	public static  String LOCAL_WEB_IP_VALUE							= null;		
	public static  String LOCAL_WEB_PORT_VALUE							= null;
	public static  String SERVER_API_IP_VALUE							= null;
	public static  String SERVER_API_PORT_VALUE							= null;
	public static  String TMS_SOCKET_IP_VALUE							= null;
	public static  String TMS_SOCKET_PORT_VALUE							= null;
	public static  String PEM_PATH_VALUE								= null;
	public static  String APK_PATH_VALUE								= null;
	public static  String ADVER_PATH_VALUE								= null;
	public static  String GOOGLE_API_SERVER_IP_VALUE					= null;
	public static  String GOOGLE_API_SERVER_MERCHANT_PORT_VALUE			= null;
	public static  String GOOGLE_API_SERVER_CYBERSMART_PORT_VALUE		= null;
	public static  String REPORT_PATH_VALUE								= null;
	
	
	
	private static Properties pro = null;

	private static void getPro(){
		InputStream in = null;
		try {
			pro = new Properties();
			in = PropertiesManager.class.getResourceAsStream(PRO_FILE_NAME);
			pro.load(in);
		} catch (Exception e) {
			LOGGER.error("PropertiesManager.getPro() is error --> " + e);
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					LOGGER.error("PropertiesManager.getPro() is error --> " + e);
				}
			}
		}
	}
	
	public static final String getProValueByKey(int key){
		String value = "";
		String keyString = "";
		try {
			if(pro == null) getPro();
			switch (key) {
			case LOCAL_WEB_IP:
				if(StringUtils.hasText(LOCAL_WEB_IP_VALUE)){
					value = LOCAL_WEB_IP_VALUE;
				}else{
					keyString = LOCAL_WEB_IP_KEY;
				}
				break;
			case LOCAL_WEB_PORT:
				if(StringUtils.hasText(LOCAL_WEB_PORT_VALUE)){
					value = LOCAL_WEB_PORT_VALUE;
				}else{
					keyString = LOCAL_WEB_PORT_KEY;
				}
				break;
			case SERVER_API_IP:
				if(StringUtils.hasText(SERVER_API_IP_VALUE)){
					value = SERVER_API_IP_VALUE;
				}else{
					keyString = SERVER_API_IP_KEY;
				}
				break;
			case SERVER_API_PORT:
				if(StringUtils.hasText(SERVER_API_PORT_VALUE)){
					value = SERVER_API_PORT_VALUE;
				}else{
					keyString = SERVER_API_PORT_KEY;
				}
				break;
			case TMS_SOCKET_IP:
				if(StringUtils.hasText(TMS_SOCKET_IP_VALUE)){
					value = TMS_SOCKET_IP_VALUE;
				}else{
					keyString = TMS_SOCKET_IP_KEY;
				}
				break;
			case TMS_SOCKET_PORT:
				if(StringUtils.hasText(TMS_SOCKET_PORT_VALUE)){
					value = TMS_SOCKET_PORT_VALUE;
				}else{
					keyString = TMS_SOCKET_PORT_KEY;
				}
				break;
			case PEM_PATH:
				if(StringUtils.hasText(PEM_PATH_VALUE)){
					value = PEM_PATH_VALUE;
				}else{
					keyString = PEM_PATH_KEY;
				}
				break;
			case APK_PATH:
				if(StringUtils.hasText(APK_PATH_VALUE)){
					value = APK_PATH_VALUE;
				}else{
					keyString = APK_PATH_KEY;
				}
				break;
			case ADVER_PATH:
				if(StringUtils.hasText(ADVER_PATH_VALUE)){
					value = ADVER_PATH_VALUE;
				}else{
					keyString = ADVER_PATH_KEY;
				}
				break;
			case GOOGLE_API_SERVER_IP:
				if(StringUtils.hasText(GOOGLE_API_SERVER_IP_VALUE)){
					value = GOOGLE_API_SERVER_IP_VALUE;
				}else{
					keyString = GOOGLE_API_SERVER_IP_KEY;
				}
				break;
			case GOOGLE_API_SERVER_MERCHANT_PORT:
				if(StringUtils.hasText(GOOGLE_API_SERVER_MERCHANT_PORT_VALUE)){
					value = GOOGLE_API_SERVER_MERCHANT_PORT_VALUE;
				}else{
					keyString = GOOGLE_API_SERVER_MERCHANT_PORT_KEY;
				}
				break;
			case GOOGLE_API_SERVER_CYBERSMART_PORT:
				if(StringUtils.hasText(GOOGLE_API_SERVER_CYBERSMART_PORT_VALUE)){
					value = GOOGLE_API_SERVER_CYBERSMART_PORT_VALUE;
				}else{
					keyString = GOOGLE_API_SERVER_CYBERSMART_PORT_KEY;
				}
				break;
			case REPORT_PATH:
				if(StringUtils.hasText(REPORT_PATH_VALUE)){
					value = REPORT_PATH_VALUE;
				}else{
					keyString = REPORT_PATH_KEY;
				}
				break;
			default:
				break;
			}
			if(!StringUtils.hasText(value)) value = pro.getProperty(keyString);
			switch (key) {
			case LOCAL_WEB_IP:
				LOCAL_WEB_IP_VALUE = value;
				break;
			case LOCAL_WEB_PORT:
				LOCAL_WEB_PORT_VALUE = value ;
				break;
			case SERVER_API_IP:
				SERVER_API_IP_VALUE = value;
				break;
			case SERVER_API_PORT:
				SERVER_API_PORT_VALUE = value;
				break;
			case TMS_SOCKET_IP:
				TMS_SOCKET_IP_VALUE = value;
				break;
			case TMS_SOCKET_PORT:
				TMS_SOCKET_PORT_VALUE = value;
				break;
			case PEM_PATH:
				PEM_PATH_VALUE = value;
				break;
			case APK_PATH:
				APK_PATH_VALUE = value;
				break;
			case ADVER_PATH:
				ADVER_PATH_VALUE = value;
				break;
			case GOOGLE_API_SERVER_IP:
				GOOGLE_API_SERVER_IP_VALUE = value;
				break;
			case GOOGLE_API_SERVER_MERCHANT_PORT:
				GOOGLE_API_SERVER_MERCHANT_PORT_VALUE = value;
				break;
			case GOOGLE_API_SERVER_CYBERSMART_PORT:
				GOOGLE_API_SERVER_CYBERSMART_PORT_VALUE = value;
				break;
			case REPORT_PATH:
				REPORT_PATH_VALUE = value;
				break;
			default:
				break;
			}
		} catch (Exception e) {
			LOGGER.error("PropertiesManager.getPro() is error --> " + e);
		}
		return value;
	}
	
	
}
