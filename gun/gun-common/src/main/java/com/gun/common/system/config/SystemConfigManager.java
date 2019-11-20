package com.gun.common.system.config;

import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gun.common.pojo.BeanUtils;
import com.gun.common.utils.StringUtils;


/**
 * @author felixli
 *
 */
@SuppressWarnings("rawtypes")
public class SystemConfigManager {
  private static final Log log = LogFactory.getLog(SystemConfigManager.class);
  public static final String COMMON = "COMMON";
  public static final String SERVICE = "SERVICE";
  public static final String DAO = "DAO";
  public static final String CONTROLLER = "CONTROLLER";
  public static final String WS = "WS";
  public static final String MS = "MS";
  public static final String COMMON_PROPS_SYSTEM_CODE = "system";
  public static final String COMMON_PROPS_LOCALE = "locale";
  public static final String COMMON_PROPS_RESOURCE_BOUNDLE = "resource.boundle";
  public static final String COMMON_PROPS_TEMP_DIR = "temp.dir";
  public static final String COMMON_PROPS_SESSION_TIMEOUT_REDIRECT_URL = "session.timeout.redirecturl";
  public static final String COMMON_PROPS_SERVICE_APPLICATION_CONTEXT = "service.application.context";
  public static final String COMMON_PROPS_TESTING = "testing";
  public static final String COMMON_PROPS_PASSWORD_FILE = "passwordFile";
  public static final String PROPS_ATTR_ADMIN_ID = "adminId";
  public static final String PROPS_ATTR_ADMIN_PASSWORD = "adminPassword";
  public static final String PROPS_ATTR_HOST = "host";
  public static final String PROPS_ATTR_PORT = "port";
  public static final String PROPS_ATTR_USER = "user";
  public static final String PROPS_ATTR_URL = "url";
  public static final String PROPS_ATTR_DURATION = "duration";
  public static final String PROPS_ATTR_FREQUENCY = "frequency";
  public static final String PROPS_ATTR_INTERVAL = "interval";
  public static final String PROPS_ATTR_RMI = "rmi";
  public static final String PROPS_ATTR_RMI_CONTEXT_FACTORY = "contextFactory";
  private static SystemAuthenticationProperties systemAuthenticationProperties;

  static {
    String passwordFile = getProperty("passwordFile");
    if (StringUtils.hasText(passwordFile)){
    try {
      systemAuthenticationProperties = new SystemAuthenticationProperties(passwordFile);
      if (systemAuthenticationProperties != null) {
        String[] items = systemAuthenticationProperties.getkeys();
        if ((items != null) && (items.length > 0)) {
          Properties props = null;
          for (int i = 0; i < items.length; ++i) {
            props = systemAuthenticationProperties.getProperties(items[i]);
            if ((props != null) && (!(props.isEmpty()))) {
              Iterator keys = props.keySet().iterator();
              String key = null;
              String value = null;
              while (keys.hasNext()) {
                key = (String) keys.next();
                value = props.getProperty(key);
                setProperty(items[i], key, value);
              }
            }
          }
        }
      }
     
    } catch (Exception e) {
      log.info("load password file is failed:" + e, e);
    }
    }
  }

  public static Properties getProperties(String moduleName) {
    return GenericConfigManager.getInstance().getProperties(moduleName);
  }

  public static String getProperty(String propertyName) {
    return GenericConfigManager.getInstance().getProperty(propertyName);
  }

  public static String getProperty(String moduleName, String propertyName) {
    return GenericConfigManager.getInstance().getProperty(moduleName, propertyName);
  }

  public static String getProperty(String moduleName, String indexPropertyName, String postPropertyName) {
    return GenericConfigManager.getInstance().getProperty(moduleName, indexPropertyName, postPropertyName);
  }

  public static void setProperty(String moduleName, String propertyName, String propertyValue) {
    GenericConfigManager.getInstance().setProperty(moduleName, propertyName, propertyValue);
  }

  public static String getTempDir() {
    return GenericConfigManager.getInstance().getTempDir();
  }

  public static boolean isTesting() {
    return GenericConfigManager.getInstance().isTesting();
  }

  public static UsernamePasswordCredentials getModuleAdminCredentials(String moduleName) {
    String adminId = getProperty(moduleName, "adminId");
    String adminPassword = getProperty(moduleName, "adminPassword");
    UsernamePasswordCredentials credentials = null;
    if ((StringUtils.hasText(adminId)) && (StringUtils.hasText(adminPassword))) {
      credentials = new UsernamePasswordCredentials(adminId, adminPassword);
    }
    return credentials;
  }

  public static String getSystemCode() {
    return GenericConfigManager.getInstance().getSystemCode();
  }

  public static String getSystemName() {
    return GenericConfigManager.getInstance().getSystemName();
  }

  public static Locale getLocale() {
    return GenericConfigManager.getInstance().getLocale();
  }

  public static void setLocale(String locale) {
    GenericConfigManager.getInstance().setLocale(locale);
  }

  public static void configFromProperties(Object object, String[][] selectedProperties) {
    if (object == null)
      return;
    if ((selectedProperties != null) && (selectedProperties.length > 0))
      for (int i = 0; i < selectedProperties.length; ++i) {
        String value = getProperty(selectedProperties[i][0], selectedProperties[i][1]);
        if (!(StringUtils.hasText(value)))
          continue;
        try {
          BeanUtils.invokeSetter(object, selectedProperties[i][1], value);
        } catch (Exception localException) {
        }
      }
  }
}
