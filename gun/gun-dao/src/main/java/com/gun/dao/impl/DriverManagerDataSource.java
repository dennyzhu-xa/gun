package com.gun.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gun.common.system.config.WfSystemConfigManager;
import com.gun.common.utils.EncodeDecodeUtils;
import com.gun.common.utils.LotteryConstants;

/**
 * @author felixli
 *
 */
public class DriverManagerDataSource extends org.springframework.jdbc.datasource.DriverManagerDataSource {
  /**
   * 系统日志记录物件
   */
  private static final Log logger = LogFactory.getLog(DriverManagerDataSource.class);
  public void setUrl(String url) {
    logger.debug("DriverManagerDataSource()-----url"+url);
    try {
      url = EncodeDecodeUtils.decryptDes(WfSystemConfigManager.getProperty(LotteryConstants.SYSTEM_CONFIG_DB_CONNECTION, LotteryConstants.SYSTEM_CONFIG_DB_CONNECTION_URL),LotteryConstants.LOTTERY);
    } catch (Exception e) {
      logger.error("DriverManagerDataSource() ----->"+e,e);
    }
    logger.debug("DriverManagerDataSource()-----url"+url);
    super.setUrl(url);
  }
  public void setUsername(String username) {
    logger.debug("DriverManagerDataSource()-----url"+username);
    try {
      username = EncodeDecodeUtils.decryptDes(WfSystemConfigManager.getProperty(LotteryConstants.SYSTEM_CONFIG_DB_CONNECTION, LotteryConstants.SYSTEM_CONFIG_DB_CONNECTION_USER),LotteryConstants.LOTTERY);
    } catch (Exception e) {
      logger.error("DriverManagerDataSource() ----->"+e,e);
    }
    super.setUsername(username);
  }
  public void setPassword(String password) {
    logger.debug("DriverManagerDataSource()-----url"+password);
    try {
      password = EncodeDecodeUtils.decryptDes(WfSystemConfigManager.getProperty(LotteryConstants.SYSTEM_CONFIG_DB_CONNECTION, LotteryConstants.SYSTEM_CONFIG_DB_CONNECTION_PASSWORD),LotteryConstants.LOTTERY);
    } catch (Exception e) {
      logger.error("DriverManagerDataSource() ----->"+e,e);
    }
    super.setPassword(password);
    
  }
}
