package com.gun.server.controller.android;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.gun.common.utils.GsonUtils;
import com.gun.common.utils.Json;
import com.gun.server.controller.utils.JsonUtils;

/**
 * Purpose: android api 抽象类
 * @since  JDK 1.7
 * @date   2017年8月25日
 */
public abstract class AbstractController {
	
	protected Log log = LogFactory.getLog(getClass());
	
	public static final String STATUS_ATTRIBUTE_KEY = "status";
	
	public static final String USERNAME_ATTRIBUTE_KEY = "username";

    public static final String ANDROID_USER_ID_ATTRIBUTE_KEY = "androidUserId";
    
    public static final String USER_ID_ATTRIBUTE_KEY	= "id";

	private static ThreadLocal<HttpServletRequest> httpServletRequestThreadLocal = new ThreadLocal<>();
	
	private static ThreadLocal<HttpServletResponse> httpServletResponseThreadLocal = new ThreadLocal<>();
	
	
	protected String getUsername() {
        return (String) getRequest().getAttribute(USERNAME_ATTRIBUTE_KEY);
    }
	
	protected BigDecimal getAndroidUserId(){
		return (BigDecimal) getRequest().getAttribute(ANDROID_USER_ID_ATTRIBUTE_KEY);
	}
	
	protected Integer getUserId(){
		return (Integer) getRequest().getAttribute(USER_ID_ATTRIBUTE_KEY);
	}
	
	@ModelAttribute
	protected void setThreadLocal(HttpServletRequest request, HttpServletResponse response) throws ParseException{
		httpServletRequestThreadLocal.set(request);
		httpServletResponseThreadLocal.set(response);
	}
	
	protected HttpServletRequest getRequest(){
		return httpServletRequestThreadLocal.get();
	}
	
	protected HttpSession getSession(){
		return getRequest().getSession();
	}
	
	protected HttpServletResponse getResponse(){
		return httpServletResponseThreadLocal.get();
	}
	
	protected ServletContext getContext(){
		return getSession().getServletContext();
	}
	
	protected void writeJson(){
		writeJson(getResponse(), null);
	}
	
	protected void writeJson(Object obj){
		writeJson(getResponse(), obj);
	}
	
	/**
	 * Purpose: 输出json数据
	 * @param response response
	 * @param obj	要输出的对象
	 */
	protected void writeJson(HttpServletResponse response, Object obj){
		try {
			if (obj instanceof Exception) {
				JsonUtils.writeJSON(response, GsonUtils.toJson(new Json((Exception)obj)));
			} else {
				JsonUtils.writeJSON(response, GsonUtils.toJson(new Json(obj)));
			}
		} catch (IOException e) {
			log.error(this.getClass().getSimpleName()+" writeJson failed!! ", e);
		}
	}
	
	/**
	 * Purpose: 异常处理方法
	 * @author kevinshen
	 * @param response	response
	 * @param e			捕获的异常
	 */
	@ExceptionHandler(Exception.class)
	public void handlerException(HttpServletResponse response, Exception e){
		log.error(this.getClass().getSimpleName()+" handlerException failed!! ", e);
		writeJson(response, e);
	}
}
