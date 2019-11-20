package com.gun.server.controller.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gun.common.exception.LotteryException;
import com.gun.common.pojo.Model;
import com.gun.common.pojo.UserSessionContext;
import com.gun.common.pojo.form.BaseLoginForm;
import com.gun.common.pojo.form.SystemForm;
import com.gun.common.utils.EncodeDecodeUtils;
import com.gun.common.utils.LotteryConstants;
import com.gun.server.controller.utils.ForceLogoutUtils;
import com.gun.server.controller.utils.JsonUtils;
import com.gun.server.controller.utils.SessionManager;
import com.gun.server.listrner.SessionListener;
import com.gun.service.login.LoginService;


/**
 * Purpose: 登入Controller 
 * @author 
 * @since  JDK 1.7
 * @date   2017/4/5
 * @MaintenancePersonnel 
 */
@SuppressWarnings("rawtypes")
@Controller
public class LoginController {
	/**
	 * 日志记录
	 */
	private static final Log log = LogFactory.getLog(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	
	/**
   * 发送邮件
   */
//	@Autowired
//  private MailComponent mailComponent; 
	
	/**
	 * LogonController建構子
	 */
	public LoginController() {
		
	}
	
	
  	/**
     * Purpose:一般登入(透過網頁)
     * @param request
     * @param command
     * @throws Exception
     * @throws IOException 
     */
    @RequestMapping(value=LotteryConstants.LOGIN)
    public ModelAndView login(HttpServletRequest request,HttpServletResponse response, BaseLoginForm command) throws IOException {
      ModelAndView mav = new ModelAndView();
      UserSessionContext userSessionContext = null;
      //用戶名
      String aliasName = command.getUserAccount();
      //密碼
      String password = command.getPassWord();
      try{
        if(!StringUtils.hasText(aliasName) || !StringUtils.hasText(password)){
          mav.setViewName("../" + LotteryConstants.LOTTERY_LOGIN);
          return mav;
        }
        command.setPassWord(EncodeDecodeUtils.encryptDes(command.getPassWord(),LotteryConstants.LOTTERY));
        Model loginModel = this.loginService.login(command);
        userSessionContext = (UserSessionContext)loginModel.getResponse();
        //判斷是否登入成功
        if ( null != userSessionContext && loginModel.getMessage().getStatus()==1) {
          userSessionContext = this.loginService.saveSessionInfo(userSessionContext);
          userSessionContext.setLocale(command.getLocale());
          /** 
           * 该账号已经被登陆 
           */  
          if(null != SessionListener.sessionMap.get( command.getUserAccount() ))  
          {  
              /** 
               * 将已经登陆的信息拿掉,将新的用户登录信息放进去 
               */ 
              ForceLogoutUtils.forceUserLogout( command.getUserAccount() );  

              SessionListener.sessionMap.put( command.getUserAccount(), request.getSession() );  
          }  
          /** 
           * 该账号未被登陆 
           */  
          else  
          {  
            SessionListener.sessionMap.put( command.getUserAccount(), request.getSession() );  
          }
          //登录信息放入session中
          SessionManager.setUserSessionContext(request, userSessionContext);
          String url = "forward:/" + LotteryConstants.FIRST_PAGE_INIT + LotteryConstants.DOT_DO;
          mav.setViewName(url);
        } else {
          mav.setViewName("../" + LotteryConstants.LOTTERY_LOGIN);
          mav.addObject(LotteryConstants.SESS_ATTR_COMMON, command);
          mav.addObject(loginModel);
        }
    } catch (Exception e) {
      log.error(this.getClass().getName()+" login failed!! "+e);
    }
    return mav;
  }
    /**
     * Purpose:登出
     * @param request:上下文request
     * @throws Exception:出錯是返回Exception
     * @return ModelAndView
     */
    @RequestMapping(value=LotteryConstants.LOGOUT)
    public ModelAndView Logout(HttpServletRequest request) throws LotteryException{
      ModelAndView mav = new ModelAndView();
      try{
        //清session ，然後轉導回登入頁面
        SessionManager.clearSession(request);
        mav.setViewName("../" + LotteryConstants.LOTTERY_LOGIN);
      }catch(Exception e){
        log.error(this.getClass().getName()+".Logout failure!! ");
      }
      return mav;
    }
    
    @RequestMapping(value = LotteryConstants.CHECK_PASSWORD)
    @ResponseBody
    public Map<String, Object> checkPassword(HttpServletRequest request,String oldPassword) throws LotteryException {
      Map<String, Object> map = new HashMap<String, Object>();
      try {
        UserSessionContext userSessionContext = (UserSessionContext) SessionManager.getUserSessionContext(request);
        if (!userSessionContext.getPassword().equals(EncodeDecodeUtils.encryptDes(oldPassword,LotteryConstants.LOTTERY))) {
          map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), false);
          map.put(SystemForm.ATTRIBUTE.MESSAGE.getValue(), "Old password entry error");
        }else{
          map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), true);
        }
      } catch (Exception e) {
        log.error(this.getClass().getName()+".init failed!! " + e);
      }
       return map;
    }
    
    @RequestMapping(value=LotteryConstants.RESET_PASSWORD)
    public ModelAndView resetPassword(HttpServletRequest request,BaseLoginForm command) throws LotteryException{
      ModelAndView mav = new ModelAndView();
      Model model = null;
      try {
        UserSessionContext userSessionContext = (UserSessionContext) SessionManager.getUserSessionContext(request);
        if(StringUtils.hasText(command.getNewPassword())){
          loginService.upadteUser(userSessionContext,EncodeDecodeUtils.encryptDes(command.getNewPassword(),LotteryConstants.LOTTERY));
        }
        String url = "redirect:/" + LotteryConstants.LOGOUT + LotteryConstants.DOT_DO;
        mav.setViewName(url);
      } catch (Exception e) {
        log.error(this.getClass().getName()+".resetPassword failure!! ");
      }
      return mav;
    }
    
    @RequestMapping(value=LotteryConstants.FORGOT_PASSWORD)
    public void forgotPassword(HttpServletRequest request, HttpServletResponse response,BaseLoginForm command) throws IOException {
      Map<String, Object> result = new HashMap<String, Object>();
      UserSessionContext userSessionContext = null;
      try {
        String rand = (String) request.getSession().getAttribute(LotteryConstants.RAND);
        String inputStr = command.getInputRand();
        Model loginModel = this.loginService.login(command);
        userSessionContext = (UserSessionContext)loginModel.getResponse();
      if (userSessionContext == null) { // 用户名有误或已被禁用
        result.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), false);
        result.put(SystemForm.ATTRIBUTE.MESSAGE.getValue(), "Account does not exist!");
        JsonUtils.writeJSON(response, result);
        return;
      }
      if(!rand.toUpperCase().equals(inputStr.toUpperCase())){
        result.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), false);
        result.put(SystemForm.ATTRIBUTE.MESSAGE.getValue(), "Validation code entry error");
        JsonUtils.writeJSON(response, result);
        return;
      }
      SessionManager.setUserSessionContext(request, userSessionContext);
      result.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), true);
      JsonUtils.writeJSON(response, result);
      } catch (Exception e) {
        result.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), false);
        result.put(SystemForm.ATTRIBUTE.MESSAGE.getValue(), "System exception, please wait a moment...");
        JsonUtils.writeJSON(response, result);
        log.error(this.getClass().getName()+" forgotPassword failed!! ");
      }
    }
    
    @RequestMapping(value=LotteryConstants.VALIDATION_CODE)
    public void getValidationCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
      Map<String, Object> result = new HashMap<String, Object>();
    //生成随机类  
      Random random = new Random();
      try {
        String sRand = "";  
        // 这里直接写中文会出乱码，必须将中文转换为unicode编码  
        String[] str = {"1", "2", "3", "4", "5", "6", "7", "8", "9" };  
      
        for (int i = 0; i < 6; i++) {  
            String rand = str[random.nextInt(str.length)];  
            sRand += rand;  
        }
        request.getSession().setAttribute(LotteryConstants.MAIL_VALIDATION_CODE, sRand);
        result.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), true);
      JsonUtils.writeJSON(response, result);
      } catch (Exception e) {
        result.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), false);
        result.put(SystemForm.ATTRIBUTE.MESSAGE.getValue(), "System exception, please wait a moment...");
        JsonUtils.writeJSON(response, result);
        log.error(this.getClass().getName()+" forgotPassword failed!! ");
      }
    }
}
