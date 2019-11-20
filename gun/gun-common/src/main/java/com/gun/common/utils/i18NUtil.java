package com.gun.common.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Purpose: Miscellaneous i18N utility methods, The name of resource bundle is CyberCard_Resource. 
 * @author: Felixli
 * @since: JDK 1.7
 * @date: 2017/07/17
 * @MaintenancePersonnel: Felxili
 */
public class i18NUtil {
	/**
	 * The default locale is Taiwan.
	 */
	public static Locale DEFAULT_LOCALE = Locale.ENGLISH;
	private static final Log log = LogFactory.getLog(i18NUtil.class); 
	/**
	 * Gets a string for the given id from this resource bundle or one of its parents with default locale.
	 * @param id the id for the desired string 
	 * @return the string for the given id 
	 */
	public static String getName(String id) {
		return getName(id, DEFAULT_LOCALE);
	}
	/**
	 * Gets a string for the given id from this resource bundle or one of its parents with given locale.
	 * @param id the id for the desired string 
	 * @param locale the locale for which a resource bundle is desired 
	 * @return the string for the given id and locale
	 */
	public static String getName(String id, Locale locale) {
	  	try {
	  		//framework message resource bundle
	  		ResourceBundle bundleFWK = (locale == null) 
	  				? ResourceBundle.getBundle(LotteryConstants.FWK_APPLICATION_RESOURCE_BUNDLE_DEFAULT) 
	  						: ResourceBundle.getBundle(LotteryConstants.FWK_APPLICATION_RESOURCE_BUNDLE, locale);
	  		if(bundleFWK.containsKey(id))
	  			return bundleFWK.getString(id);
	  		else
	  			return "";
	  	}catch(Throwable e) {
			log.error("i18NUtil.getName() error:"+e);
	  	}
	  	return "";
	}
	/**
   * Gets a string for the given id from this resource bundle or one of its parents with given locale.
   * @param id the id for the desired string 
   * @param locale the locale for which a resource bundle is desired 
   * @return the string for the given id and locale
   */
  public static String getName(String id, String locale) {
      try {
        if(StringUtils.hasText(locale)){
        //framework message resource bundle
          ResourceBundle bundleFWK = ResourceBundle.getBundle(LotteryConstants.FWK_APPLICATION_RESOURCE_BUNDLE+LotteryConstants.MARK_UNDERLINE+locale);
          if(bundleFWK.containsKey(id))
            return bundleFWK.getString(id);
          else
            return "";
        }else{
          ResourceBundle bundleFWK = ResourceBundle.getBundle(LotteryConstants.FWK_APPLICATION_RESOURCE_BUNDLE_DEFAULT);
          if(bundleFWK.containsKey(id))
            return bundleFWK.getString(id);
          else
            return "";
        }
      }catch(Throwable e) {
      log.error("i18NUtil.getName() error:"+e);
      }
      return "";
  }
	/**
	 * Gets a service name for the given service id from this resource bundle or one of its parents with default locale.
	 * @param serviceId service id
	 * @return the service name for the given service id 
	 */
	public static String getServiceName(String serviceId) {
		return getName(serviceId);
	}
	/**
	 * Gets a user case name for the given use case no from this resource bundle or one of its parents with default locale.
	 * @param ucNo use case no
	 * @return the use case name for the given use case no 
	 */
	public static String getUseCaseName(String ucNo){
		return getName(ucNo);
	}
	/**
	 * Gets a string for the given ids from this resource bundle or one of its parents with default locale.
	 * @param ids the array of id would be concatenated with "." for the desired string 
	 * @return the string for the given array of id 
	 */
	public static String getName(String[] ids) {
		if (ids == null || ids.length <= 0) return "";
		StringBuffer key = new StringBuffer("");
		for (int i=0; i<ids.length; i++) {
			if (i > 0) key.append(".");
				key.append(ids[i]);
			}
		return getName(key.toString());
	}
}