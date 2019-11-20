package com.gun.service.login;

import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.Model;
import com.gun.common.pojo.UserSessionContext;
import com.gun.common.pojo.form.BaseLoginForm;
import com.gun.service.LotteryService;


/**
 * Purpose:登入服務介面 
 * @author felixli
 * @since  JDK 1.7
 * @date   2017/3/22
 * @MaintenancePersonnel felixli
 */
@SuppressWarnings("rawtypes")
public interface LoginService extends LotteryService {
	
	public Model init(Object request);
	
	 /**
   * Purpose:登录
   * @author felixli
   * @param command:登录信息
   * @throws ServiceException：出错时抛出ServiceException
   * @return Model：model
   */
  public Model login(BaseLoginForm command) throws ServiceException;
  
  /**
   * Purpose:錄登入者資訊於UserSessionContext中
   * @param userInfo
   * @throws Exception
   * @return UserSessionContext
   */
  public UserSessionContext saveSessionInfo(UserSessionContext userInfo ) throws Exception ;
  
  /**
   * Purpose:更新登陆时间
   * @author felixli
   * @param userInfo:UserSession
   * @param newPassWord:新密码
   * @throws ServiceException：出错时抛出ServiceException
   * @return Model：model
   */
  public void upadteUser(UserSessionContext userInfo,String newPassWord) throws ServiceException;
	
	}
