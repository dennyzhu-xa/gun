package com.gun.server.controller.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gun.common.utils.LotteryConstants;
import com.gun.server.controller.SystemController;
import com.gun.server.listrner.SessionListener;

public class ForceLogoutUtils {
  
  private static final Log log = LogFactory.getLog(SystemController.class);
  
  public static void forceUserLogout(String username)  
  {  
      try {
        if(SessionListener.sessionMap.get( username ) != null)  
        {  
            HttpSession session = SessionListener.sessionMap.get( username );  

            SessionListener.sessionMap.remove( username );  

            Enumeration e = session.getAttributeNames();  

            while(e.hasMoreElements())  
            {  
                String sessionName = (String) e.nextElement();  

                session.removeAttribute( sessionName );  
            }  
            session.removeAttribute(LotteryConstants.SESS_ATTR_COMMON);   
        }
      } catch (Exception e) {
        log.error("Session already invalidated!");
      }
  }
}
