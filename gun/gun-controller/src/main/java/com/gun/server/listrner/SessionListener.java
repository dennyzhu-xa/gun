package com.gun.server.listrner;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gun.common.pojo.UserSessionContext;
import com.gun.common.utils.LotteryConstants;

/**
 * 
 * Purpose: Session過濾器
 * @since  JDK 1.7
 * @date   2017/6/01
 */
public class SessionListener implements HttpSessionListener  {

  /**
   *系統日誌記錄物件 
   */
  private static final Log logger = LogFactory.getLog(SessionListener.class);
  /**  
   * 该HashMap以用户名-HttpSession对象存储一个账号只能被一个人登陆的信息。  
   */  
  public static HashMap<String,HttpSession> sessionMap = new HashMap<String,HttpSession>();

  /** (non-Javadoc)
   * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
   */
  @Override
  public void sessionCreated(HttpSessionEvent arg0) {
    HttpSession session = arg0.getSession();  
  }

  /** (non-Javadoc)
   * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
   */
  @Override
  public void sessionDestroyed(HttpSessionEvent arg0) {
    HttpSession session = arg0.getSession();  
    delSession( session );  
  }
  public static synchronized void delSession(HttpSession session)  
  {  
      if(session != null)  
      {  
        Map attrs = (Map)session.getAttribute(LotteryConstants.SESS_ATTR_COMMON);
        if (attrs == null) return;
          // 删除单一登录中记录的变量  
          if(attrs.get(LotteryConstants.SESS_ATTR_USER_SESSION_CTX) != null)  
          {  
            UserSessionContext userSessionContext = (UserSessionContext) attrs.get(LotteryConstants.SESS_ATTR_USER_SESSION_CTX);
            SessionListener.sessionMap.remove( userSessionContext.getUserId());  
          }  
      }  
  } 
}
