package com.gun.common.system.config;

import java.io.File;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.gun.common.exception.LotteryException;
import com.gun.common.utils.i18NUtil;

/**
 * 
 * @author felixli
 *
 */
public class GenericConfigManager {
  private static Log log = LogFactory.getLog(GenericConfigManager.class);
  public static final String DEFAULT_CONFIG_FILE = "system.config";
  public static Locale DEFAULT_LOCALE = Locale.TAIWAN;
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
  public static final String COMMON_PROPS_RULE_CONFIG_FILE = "rule.config.file";
  public static final String COMMON_PROPS_TESTING = "testing";
  private static String[] TMP_DIRs = { "TEMP", "TMP" };
  private static GenericConfigManager cfgMgr = null;
  private static ConfigProperties configProperties = null;

  static {
    cfgMgr = new GenericConfigManager();
  }

  private GenericConfigManager() {
    try {
      init("system.config");
    } catch (Exception localException) {
    }
  }

  public static GenericConfigManager getInstance() {
    return getInstance("system.config");
  }

  public static GenericConfigManager getInstance(String cfgFileName) {
    try {
      init(cfgFileName);
    } catch (Exception e) {
      log.error("config manager init error:" + e, e);
    }
    return cfgMgr;
  }

  public static GenericConfigManager getInstance(File cfgFile) {
    try {
      init(cfgFile);
    } catch (Exception e) {
      log.error("config manager init error:" + e, e);
    }
    return cfgMgr;
  }

  private static synchronized void init(String configFileName) throws LotteryException {
    if (configProperties == null)
      configProperties = new ConfigProperties();
    if (!(configProperties.isEmpty()))
      return;
    configProperties.load(configFileName);
  }

  private static synchronized void init(File configFile) throws LotteryException {
    if (configProperties == null)
      configProperties = new ConfigProperties();
    if (!(configProperties.isEmpty()))
      return;
    configProperties.load(configFile);
  }

  public synchronized Properties getProperties(String moduleName) {
    if (configProperties == null)
      configProperties = new ConfigProperties();
    return configProperties.getProperties(moduleName);
  }

  public synchronized String getProperty(String propertyName) {
    if (configProperties == null)
      configProperties = new ConfigProperties();
    return configProperties.getProperty("COMMON", propertyName);
  }

  public synchronized String getProperty(String moduleName, String propertyName) {
    if (configProperties == null)
      configProperties = new ConfigProperties();
    return configProperties.getProperty(moduleName, propertyName);
  }

  public synchronized String getProperty(String moduleName, String indexPropertyName, String postPropertyName) {
    String propertyName = indexPropertyName + "." + postPropertyName;
    return getProperty(moduleName, propertyName);
  }

  public synchronized String[] getSimilarProperies(String moduleName, String propertyName) {
    if (configProperties == null)
      configProperties = new ConfigProperties();
    return configProperties.getSimilarProperies(moduleName, propertyName);
  }

  public synchronized void setProperties(String moduleName, Properties properties) {
    if (configProperties == null)
      configProperties = new ConfigProperties();
    configProperties.setProperties(moduleName, properties);
  }

  public synchronized void setProperty(String moduleName, String propertyName, String propertyValue) {
    if (configProperties == null)
      configProperties = new ConfigProperties();
    configProperties.setProperty(moduleName, propertyName, propertyValue);
  }

  public synchronized void remove(String moduleName) {
    if (configProperties == null)
      return;
    configProperties.remove(moduleName);
  }

  public synchronized void remove(String moduleName, String propertyName) {
    if (configProperties == null)
      return;
    configProperties.remove(moduleName, propertyName);
  }

  public synchronized void clear() {
    if (configProperties == null)
      return;
    configProperties.clear();
  }

  public synchronized void refresh() {
    if (configProperties == null)
      return;
    configProperties.refresh();
  }

  public synchronized String getTempDir() {
    String tempDir = getProperty("temp.dir");
    if (StringUtils.hasText(tempDir))
      try {
        File dir = new File(tempDir);
        if ((!(dir.exists())) || (!(dir.isDirectory())))
          tempDir = null;
      } catch (Exception localException) {
      }
    if (!(StringUtils.hasText(tempDir)))
      label75: for (int i = 0; i < TMP_DIRs.length; ++i)
        try {
          tempDir = System.getenv(TMP_DIRs[i]);
          if (!(StringUtils.hasText(tempDir)))
            break label75;
        } catch (Exception localException1) {
        }
    return tempDir;
  }

  public boolean isTesting() {
    boolean isTesting = false;
    try {
      String value = getProperty("testing");
      if (StringUtils.hasText(value))
        isTesting = Boolean.valueOf(value).booleanValue();
    } catch (Exception localException) {
    }
    return isTesting;
  }

  public String getSystemCode() {
    String systemCode = getProperty("system");
    return systemCode;
  }

  public String getSystemName() {
    String systemCode = getSystemCode();
    Locale locale = getLocale();
    String systemName = i18NUtil.getName(systemCode, locale);
    return systemName;
  }

  public Locale getLocale() {
    String localeString = getProperty("locale");
    Locale locale = StringUtils.parseLocaleString(localeString);

    if (locale == null)
      locale = DEFAULT_LOCALE;
    return locale;
  }

  public void setLocale(String locale) {
    if (!(StringUtils.hasText(locale)))
      return;
    setProperty("COMMON", "locale", locale);
  }
}
