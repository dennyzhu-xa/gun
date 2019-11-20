package com.cyber.lottery.server.timer;

import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * 定时执行需要删除任务的到期投掷项目
 * @author 
 *
 */
public class NFDFlightDataTimerTask extends TimerTask{
  
  private static final Log log = LogFactory.getLog(NFDFlightDataTimerTask.class);
  
  /**
   * Spring 容器
   */
  private static WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext(); 
  
  
  /** (non-Javadoc)
   * @see java.util.TimerTask#run()
   */
  @Override
  public void run() {
    try {
      log.debug("TimerTask----------->start");
      log.debug("TimerTask----------->end");
     } catch (Exception e) {
      log.error("Batch approval failed!"+e);
     }
  }

}
