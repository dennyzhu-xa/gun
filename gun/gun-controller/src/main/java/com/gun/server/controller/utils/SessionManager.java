package com.gun.server.controller.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.gun.common.pojo.IUserSessionContext;
import com.gun.common.utils.LotteryConstants;


/**
 * @since  JDK 1.7
 * @date   2017/4/5
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class SessionManager {
	private static final Log log = LogFactory.getLog(SessionManager.class);	
	
  public static void setAttribute(HttpServletRequest request, String ucNo, String name, Object value) throws Exception {
			try {
				HttpSession session = request.getSession();
				if (session == null) throw new IllegalStateException("session timeout!");
				
				Map hstAttrs = (Map)session.getAttribute(ucNo);
				
				if(hstAttrs == null) hstAttrs = new HashMap();
				
				if(hstAttrs.containsKey(name)) hstAttrs.remove(name);
								
				hstAttrs.put(name, value);
				
				session.setAttribute(ucNo, hstAttrs);
			} catch(Exception e) {
				log.debug("set session attribute is failed:"+e, e);
				throw e;
			}
	}
	public static Object getAttribute(HttpServletRequest request, String ucNo, String name) throws Exception {
			try {
		 		HttpSession session = request.getSession();
		 		if (session == null) throw new IllegalStateException("session timeout!");
		 		Map attrs = (Map)session.getAttribute(ucNo);
		 		if (attrs == null) return null;
		 		return attrs.get(name);
			}catch(Exception e) {
				log.debug("get session attribute is failed:"+e, e);
				throw e;
			}
	}
	
	public static IUserSessionContext getUserSessionContext(HttpServletRequest request) throws Exception{
			return (IUserSessionContext)getAttribute(request, LotteryConstants.SESS_ATTR_COMMON, LotteryConstants.SESS_ATTR_USER_SESSION_CTX);
	}

	public static void setUserSessionContext(HttpServletRequest request, IUserSessionContext userSessionContext) throws Exception{
			setAttribute(request, LotteryConstants.SESS_ATTR_COMMON, LotteryConstants.SESS_ATTR_USER_SESSION_CTX, userSessionContext);
	}
	
	
	public static void clean(HttpServletRequest request)  {
			try {
				HttpSession session = request.getSession();
				 Enumeration attributeNames = session.getAttributeNames();
				 String attrName = null;
				 while(attributeNames.hasMoreElements())  {
					 attrName = (String)attributeNames.nextElement();
					 if(attrName != null && !attrName.equalsIgnoreCase(LotteryConstants.SESS_ATTR_COMMON)) {
						 try {
						 	session.removeAttribute(attrName);
						 } catch(Exception e) {
							 log.debug("clean session attribute is failed:"+e, e);
						 }
					 }	 
				 }
			}catch(Exception e) {
				log.debug("clean session attribute is failed:"+e, e);
				}	 
	 }
	/**
	 * Purpose:清除Session
	 * @param request:上下文request
	 * @return void
	 */
	public static void clearSession(HttpServletRequest request)  {
		try {
			HttpSession session = request.getSession();
			Enumeration attrs = session.getAttributeNames();
			String attrName = "";
			while(attrs.hasMoreElements())  {
				attrName = (String)attrs.nextElement();
				if(StringUtils.hasText(attrName)) {
					try {
					session.removeAttribute(attrName);
					} catch(Exception e) {
					log.debug("clear session exception:" + e);
					}
				}	 
			}
		}catch(Exception e) {}	 
	 }
}
