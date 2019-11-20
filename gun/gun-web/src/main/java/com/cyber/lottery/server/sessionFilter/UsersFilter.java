package com.cyber.lottery.server.sessionFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gun.common.pojo.UserSessionContext;
import com.gun.common.utils.LotteryConstants;
import com.gun.common.utils.LotteryMessageCode;
import com.gun.common.utils.i18NUtil;
import com.gun.server.controller.utils.SessionManager;

/**
 * 
 * Purpose: 用戶訪問權限的過濾器
 * @author Felixli
 * @since  JDK 1.7
 * @date   2017/6/01
 * @MaintenancePersonnel Felixli
 */
public class UsersFilter implements Filter {
private static final Log log = LogFactory.getLog(UsersFilter.class);
  
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    try {
      if ((request instanceof HttpServletRequest) && (response instanceof HttpServletResponse)) {
              HttpServletRequest req = (HttpServletRequest) request;
              HttpServletResponse res = (HttpServletResponse) response;
              // is session expire control required for this request?
              String uri = validate(req.getRequestURI());
              String url = validate(req.getRequestURL().toString());
              String loginUrl = url.substring(0, url.length() - uri.length()) + req.getContextPath() + "/";
              String message = "";
              
              if((uri.indexOf("login.do")<=0 && uri.indexOf("BatchJob")<=0 && uri.indexOf(".do")>0 && uri.indexOf("Gui") <= 0 && uri.indexOf("AndroidDealer") <= 0) 
                      || (uri.indexOf("lottery_login.jsp")<=0 && uri.indexOf(".jsp")>0 && uri.indexOf("Gui") <= 0 && uri.indexOf("AndroidDealer") <= 0)) {
                UserSessionContext userSessionContext = (UserSessionContext) SessionManager.getUserSessionContext(req);
                if(userSessionContext==null) {
                  if(uri.indexOf("logout.do")>=0){
                    res.sendRedirect(req.getContextPath() + "/lottery_login.jsp");
                  }else{
                    PrintWriter out=res.getWriter();
                    message = i18NUtil.getName(LotteryMessageCode.FORCE_LOGOUT_FAILED, LotteryConstants.STRING_EMPTY);
                    out.println(
                      "<html><head>"
                      + "<link rel=\"stylesheet\" type=\"text/css\" href=\'"+loginUrl+"assets/bootstrap/lobibox/Lobibox.min.css\' />"
                      + "<script type=\"text/javascript\" src=\'"+loginUrl+"/assets/js/jquery-1.8.3.min.js\'></script>"
                      + "<script type=\"text/javascript\" src=\'"+loginUrl+"assets/bootstrap/lobibox/Lobibox.min.js\'></script>"
                      + "</head><body>" +
                        "<script type=\"text/javascript\">" +
                        "Lobibox.alert(\'info\', {msg: \'"+message+"\',callback: function ($this, type, ev) {if(type==\'ok\'){"
                                + " top.location.href = \'" + loginUrl + "\';}}});" +
                        " $(\".btn-close\").click(function(){ top.location.href = \'" + loginUrl + "\';  });" + 
                        " $(document).keyup(function(event){switch(event.keyCode) {case 27:top.location.href = \'" + loginUrl + "\'; } });" + 
                      "</script>" +
                        "</body></html>"
                      );
                  }
                  return;
                }
              }
              
              if(uri.indexOf("lottery_login.jsp")>=0){
                UserSessionContext userSessionContext = (UserSessionContext) SessionManager.getUserSessionContext(req);
                if(userSessionContext!=null){
                  if(req.getSession().getAttribute("sessionAlreadyExists")==null 
                          || !LotteryConstants.YES.equals(req.getSession().getAttribute("sessionAlreadyExists").toString())){
                    req.getSession().setAttribute("sessionAlreadyExists", LotteryConstants.YES);
                    res.sendRedirect(req.getContextPath() + "/lottery_login.jsp");
                    return;
                  }
                }
              }
              
              if(uri.indexOf("login.do")>=0){
                UserSessionContext userSessionContext = (UserSessionContext) SessionManager.getUserSessionContext(req);
                if(userSessionContext!=null){
                    res.sendRedirect(req.getContextPath() + "/lottery_login.jsp");
                    return;
                }
              }
              
             /* String conString = "";
              conString = validate(req.getHeader("REFERER"));////獲取父URL--如果不是直接輸入的話就是先前的訪問過來的頁面，要是用戶輸入了，這個父URL是不存在的 
                if("".equals(conString) || null==conString){ //判斷如果上一個目錄為空的話，說明是用戶直接輸入URL訪問的  
                    String servletPath = validate(req.getServletPath()); //當前請求URL，去掉幾個可以直接訪問的頁面  
                    if(!(servletPath.contains("/index.jsp") 
                            || servletPath.contains("/Android")
                            || servletPath.contains("/AndroidDealer")
                            || servletPath.contains("/lottery_login.jsp"))){ 
                      UserSessionContext userSessionContext = (UserSessionContext) SessionManager.getUserSessionContext(req);
                      if(userSessionContext!=null){
                        message = i18NUtil.getName(LotteryMessageCode.ILLEGAL_OPERATION_FAILED, userSessionContext.getLocale());
                        //清session ，然後轉導回登入頁面
                        SessionManager.clearSession(req);
                        req.getSession().setAttribute(LotteryMessageCode.ILLEGAL_OPERATION_FAILED, message);
                        //跳回登入頁面
                        res.sendRedirect(req.getContextPath() + "/lottery_login.jsp");// parasoft-suppress SECURITY.IBA.VRD-1 parameter is validated
                        return;
                      }
                    }
               }*/
          }
      chain.doFilter(request, response);
    }catch(Exception e) {
      log.error("doFilter failed!");
      throw new ServletException(e);
    }
  }

  
    public String validate(String strParam) {
      if(strParam==null) {
            return "";
        }else {
            return strParam;
        }   
    }
  
  @Override
  public void destroy() {   
  }
}
