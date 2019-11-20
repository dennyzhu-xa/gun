package com.gun.service.login.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cyber.lottery.dao.FunctionDefDAO;
import com.cyber.lottery.dao.SysUserDAO;
import com.gun.common.entity.FunctionType;
import com.gun.common.entity.SysUserInfo;
import com.gun.common.entity.pojo.SysUserInfoDTO;
import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.LotteryMessage;
import com.gun.common.pojo.Model;
import com.gun.common.pojo.UserSessionContext;
import com.gun.common.pojo.form.BaseLoginForm;
import com.gun.common.utils.DateTimeUtils;
import com.gun.common.utils.LotteryMessageCode;
import com.gun.common.utils.StringUtils;
import com.gun.service.login.LoginService;


/**
 * Purpose: 登入服務
 * @author Felixli
 * @since  JDK 1.7
 * @date   2017/6/01
 * @MaintenancePersonnel Felixli
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {
	/**
	    * 日志记录组件
	 */
	private static final Log log = LogFactory.getLog(LoginServiceImpl.class);
	
	/**
   * 用戶DAO
   */
  @Autowired(required=true)
  private SysUserDAO sysUserDAO;
  
  /**
   * 功能清單檔DAO
   */
  @Autowired(required=true)
  private FunctionDefDAO functionDefDAO;

	public LoginServiceImpl() {
	}

	@SuppressWarnings("rawtypes")
  @Override
	public Model init(Object request) throws ServiceException {
		Model model = new Model();
		return model;
	}
	
	/**
	 * (non-Javadoc)
	 * @see com.cyber.mpos.service.login.LoginService#login(com.cyber.mpos.common.pojo.form.BaseLoginForm)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
	public Model login(BaseLoginForm command)  throws ServiceException{
		Model model = new Model();
		LotteryMessage message = null;
		UserSessionContext userInfo = null;	
		SysUserInfoDTO sysUserInfoDTO = null;
		try {
			//判断用户名密码是否为空
			if (StringUtils.hasText(command.getUserAccount())) {
				//根据用户名查询用户信息
			  sysUserInfoDTO = this.sysUserDAO.getSysUserByUserName(command.getUserAccount());
				//判断用户是否存在
				if (sysUserInfoDTO !=null) {
				    if(command.getPassWord().equals(sysUserInfoDTO.getPassword())){
				      userInfo = new UserSessionContext();
		              userInfo.setAliasName(sysUserInfoDTO.getUserName());
		              userInfo.setUserId(sysUserInfoDTO.getUserId());
		              userInfo.setRoleId(sysUserInfoDTO.getRoleId());
		              userInfo.setPassword(sysUserInfoDTO.getPassword());
		              userInfo.setId(sysUserInfoDTO.getId().intValue());
		              message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS);
		              SysUserInfo sysUserInfo= this.sysUserDAO.findById(SysUserInfo.class, sysUserInfoDTO.getId().intValue());
		              sysUserInfo.setLastLogintime(DateTimeUtils.getCurrentTimestamp());
		              this.sysUserDAO.saveOrUpdate(sysUserInfo);
				    }else{
				      message = new LotteryMessage(LotteryMessage.STATUS_FAILURE, LotteryMessageCode.PASSWORD_ERROR);
				      log.debug("login failed !! ");
				    }
				}else{
					message = new LotteryMessage(LotteryMessage.STATUS_FAILURE, LotteryMessageCode.USER_NOT_EXIST, new String[]{command.getUserAccount()});
					log.debug("login failed !! ");
				}
			}
		} catch(Exception e) {
			log.error(".login failed:" + e);
			message = new LotteryMessage(LotteryMessage.STATUS_FAILURE, LotteryMessageCode.LOOGIN_FAILED);
		}
		model.setResponse(userInfo);
		model.setMessage(message);
		return model;
	}
	
	 public UserSessionContext saveSessionInfo(UserSessionContext userInfo) throws Exception {
	    //功能權限清單查詢
	    List<FunctionType> qryFuncList = this.functionDefDAO.queryFunctionsList(userInfo.getRoleId());
	    if(null != qryFuncList && !CollectionUtils.isEmpty(qryFuncList)){
	      //整理功能權限清單
	      List<FunctionType> functionList = new ArrayList<FunctionType>();
	      Map<String, List<FunctionType>> subFunctionList = new HashMap<String, List<FunctionType>>();
	      
	      for(FunctionType functionDef : qryFuncList){
	        String funcId = functionDef.getFunctionId();
	        String parentFuncid = functionDef.getParentFunctionId();
	        
	        //若為一級選單
	        if(funcId.equals(parentFuncid) || !StringUtils.hasText(parentFuncid)){
	          functionList.add(functionDef);
	        }
	        //若為二級選單
	        else{
	          if(subFunctionList.get(parentFuncid) != null){
	            subFunctionList.get(parentFuncid).add(functionDef);
	          }else{
	            subFunctionList.put(parentFuncid, new ArrayList<FunctionType>());
	            subFunctionList.get(parentFuncid).add(functionDef);
	          }
	        }
	          
	      }
	      
	      userInfo.setFunctionList(functionList);
	      userInfo.setSubFunctionList(subFunctionList);
	    }
	    return userInfo;
	    }

  /** (non-Javadoc)
   * @see com.gun.service.login.LoginService#upadteUser(java.lang.String)
   */
  @Override
  public void upadteUser(UserSessionContext userInfo,String newPassWord) throws ServiceException {
    try {
        SysUserInfo sysUserInfo= this.sysUserDAO.findById(SysUserInfo.class, userInfo.getId());
        sysUserInfo.setPassword(newPassWord);
        sysUserInfo.setUpdatedByName(userInfo.getAliasName());
        sysUserInfo.setUpdatedById(userInfo.getUserId());
        sysUserInfo.setUpdatedDate(DateTimeUtils.getCurrentTimestamp());
        this.sysUserDAO.saveOrUpdate(sysUserInfo);
    } catch(Exception e) {
      log.error(".upadteUser failed:" + e);
      throw new ServiceException(e);
    }
  }
	
}
