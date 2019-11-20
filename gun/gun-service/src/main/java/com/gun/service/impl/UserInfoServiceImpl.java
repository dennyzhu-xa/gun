package com.gun.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cyber.lottery.dao.AndroidUserDAO;
import com.cyber.lottery.dao.UserInfoDAO;
import com.gun.common.entity.AndroidUser;
import com.gun.common.entity.UserInfo;
import com.gun.common.entity.pojo.AndroidUserDTO;
import com.gun.common.entity.pojo.UserInfoDTO;
import com.gun.common.exception.BusinessException;
import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.ExceptionType;
import com.gun.common.pojo.LotteryMessage;
import com.gun.common.pojo.Model;
import com.gun.common.pojo.PageInfo;
import com.gun.common.pojo.UserSessionContext;
import com.gun.common.utils.DateTimeUtils;
import com.gun.common.utils.LotteryConstants;
import com.gun.common.utils.LotteryMessageCode;
import com.gun.common.utils.ObjectUtils;
import com.gun.common.utils.StringUtils;
import com.gun.common.utils.ValidateUtils;
import com.gun.service.UserInfoService;

/**
 * Purpose: 用户Service实现类
 * @author 
 * @since  JDK 1.7
 * @date   2017年8月25日
 * @MaintenancePersonnel 
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

	private static final Log log = LogFactory.getLog(UserInfoServiceImpl.class);
	
	@Autowired
	private AndroidUserDAO androidUserDAO;

	@Autowired
	private UserInfoDAO userInfoDAO;
	
	/**
	 * (non-Javadoc)
	 * @see com.gun.service.UserInfoService#register(com.gun.common.entity.pojo.AndroidUserDTO)
	 */
	@Override
	public void register(AndroidUserDTO androidUserDTO) {
		// 参数验证
		ValidateUtils.required(androidUserDTO.getUsername(), "Username cannot be empty");
		ValidateUtils.required(androidUserDTO.getPassword(), "Password cannot be empty");
		ValidateUtils.email(androidUserDTO.getUsername(), "Username format error, ex: moeyan@gamil.com");
		// 邮箱是否重复验证
        if(!checkUsername(androidUserDTO.getUsername())){
        	throw new BusinessException(ExceptionType.MAILBOX_IS_USED);
        };
        
        if (StringUtils.isEmpty(androidUserDTO.getPasswordSalt())) {
        	androidUserDTO.setPasswordSalt(androidUserDTO.getUsername());
        }
        
        if (StringUtils.isEmpty(androidUserDTO.getFullName())){
        	androidUserDTO.setFullName(LotteryConstants.STRING_EMPTY);
        }
        
        if (StringUtils.isEmpty(androidUserDTO.getNrc())){
        	androidUserDTO.setNrc(LotteryConstants.STRING_EMPTY);
        }
        
        // 新增用户数据
        AndroidUser androidUser = androidUserDTO.transformer(null);
        androidUser.encodePassword();
        androidUser.generateToken();
        androidUser.setStatus((byte)1);
        //非空的栏位默认设置为""
//        androidUser.setFullName(LotteryConstants.STRING_EMPTY);
//        androidUser.setNrc(LotteryConstants.STRING_EMPTY);
//        androidUser.setBirthday(LotteryConstants.STRING_EMPTY);
//        androidUser.setGender("man");
//        androidUser.setAddress("Yangon-Ahlone");
        AndroidUser saveUser = androidUserDAO.save(androidUser);
        
	}

	/**
	 * (non-Javadoc)
	 * @see com.gun.service.UserInfoService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public UserInfoDTO login(String username, String password) {
		log.debug("---> " + this.getClass().getName() + " login() param username: " + username);
		log.debug("---> " + this.getClass().getName() + " login() param password: " + password);
		
		// 参数验证
		ValidateUtils.required(username, "UserAccount cannot be empty");
		ValidateUtils.required(password, "Password cannot be empty");
		
//		AndroidUser androidUser = androidUserDAO.selectByUsername(username);
		UserInfo user = userInfoDAO.selectByUserAccount(username);
		
		// 用户是否存在验证
		if (ObjectUtils.isEmpty(user)){
			throw new BusinessException(ExceptionType.USER_NOT_FOUND);
		}
		
		// 用户密码验证
		if (!user.checkPassword(password)){
			throw new BusinessException(ExceptionType.USERNAME_PASSWORD_ERROR);
		}
		
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		userInfoDTO.setUserName(user.getUserName());
		userInfoDTO.setUserId(user.getUserId());
        return userInfoDTO;
	}

	/**
	 * (non-Javadoc)
	 * @see com.gun.service.UserInfoService#loginWithToken(java.lang.String)
	 */
	@Override
	public AndroidUserDTO loginWithToken(String token) {
		log.debug("---> AndroidUserServiceImpl loginWithToken() param token: " + token);

		// 参数验证
		ValidateUtils.required(token);
		
		AndroidUser androidUser = androidUserDAO.selectByToken(token);
		
		// 用户是否存在验证
		if (ObjectUtils.isEmpty(androidUser)){
			throw new BusinessException(ExceptionType.USER_NOT_FOUND);
		}
		
		// 检查账户是否被禁用
		if (androidUser.checkDisable()){
			throw new BusinessException(ExceptionType.ACCOUNT_DISABLE);
		}
		
		// 登录数据处理
		androidUser.generateToken();
		androidUser.setLastLogonTime(DateTimeUtils.getCurrentTimestamp());
//		androidUser.setUpdatedDate(DateTimeUtils.getCurrentTimestamp());
		androidUserDAO.update(androidUser);
		
		AndroidUserDTO androidUserDTO = new AndroidUserDTO();
		androidUserDTO.setToken(androidUser.getToken());
		
        return androidUserDTO;
	}

	/**
	 * (non-Javadoc)
	 * @see com.gun.service.UserInfoService#modifyPassword(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void modifyPassword(String username, String oldPassword, String newPassword) {
		login(username, oldPassword);
		androidUserDAO.getSession().clear();
		modifyPassword(username, newPassword);
	}
	
	/**
	 * (non-Javadoc)
	 * @see com.gun.service.UserInfoService#modifyPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public void modifyPassword(String username, String newPassword) {
		ValidateUtils.required(newPassword);
		AndroidUser androidUser = androidUserDAO.selectByUsername(username);
		if (androidUser == null){
			throw new BusinessException(ExceptionType.USER_NOT_FOUND);
		}
		androidUser.setPassword(newPassword);
		androidUser.encodePassword();
		androidUser.generateToken();
//		androidUser.setUpdatedDate(DateTimeUtils.getCurrentTimestamp());
		androidUserDAO.update(androidUser);
	}

	/**
	 * Purpose: 检查用户名是否重复
	 * @param username 用户名
	 * @return boolean 是否重复
	 */
	public boolean checkUsername(String username) {
		return ObjectUtils.isEmpty(userInfoDAO.selectByUserAccount(username));
	}

	/**
	 * (non-Javadoc)
	 * @see com.gun.service.UserInfoService#checkToken(java.lang.String)
	 */
	@Override
	public AndroidUser checkToken(String token) {
		AndroidUser androidUser = androidUserDAO.selectByToken(token);
		return androidUser;
	}

	/**
	 * (non-Javadoc)
	 * @see com.gun.service.UserInfoService#getLoginAndroidUser(java.math.BigDecimal)
	 */
	@Override
	public AndroidUserDTO getLoginAndroidUser(BigDecimal userId) {
		AndroidUserDTO androidUser = androidUserDAO.selectById(userId);
		if (androidUser == null){
			throw new BusinessException(ExceptionType.USER_NOT_FOUND);
		}
		return androidUser;
	}
	
	/**
	 * (non-Javadoc)
	 * @see com.gun.service.UserInfoService#getAndroidUserByUsername(java.lang.String)
	 */
	@Override
	public AndroidUserDTO getAndroidUserByUsername(String username){
		AndroidUser androidUser = androidUserDAO.selectByUsername(username);
		if (androidUser == null){
			throw new BusinessException(ExceptionType.USER_NOT_FOUND);
		}
		AndroidUserDTO androidUserDTO = new AndroidUserDTO();
		androidUserDTO.setUsername(androidUser.getUsername());
		androidUserDTO.setFullName(androidUser.getFullName());
		androidUserDTO.setGender(androidUser.getGender());
		androidUserDTO.setPictureUri(androidUser.getPictureUri());
		androidUserDTO.setStatus(androidUser.getStatus());
		return androidUserDTO;
	}
	
	/**
	 * (non-Javadoc)
	 * @see com.gun.service.UserInfoService#loginWithFacebookUserId(java.lang.String)
	 */
	@Override
	public AndroidUserDTO loginWithFacebookUserId(String facebookUserId) {
		// 参数验证
		ValidateUtils.required(facebookUserId, "FacebookUserId cannot be empty");
		AndroidUser androidUser = androidUserDAO.selectByFaceBookUserId(facebookUserId);
		// 用户是否存在
		if (ObjectUtils.isEmpty(androidUser)){
			throw new BusinessException(ExceptionType.USER_NOT_FOUND);
		}
		
		// 检查账户是否被禁用
		if (androidUser.checkDisable()){
			throw new BusinessException(ExceptionType.ACCOUNT_DISABLE);
		}
		
		// 登录后数据处理
		androidUser.generateToken();
		androidUser.setLastLogonTime(DateTimeUtils.getCurrentTimestamp());
//		androidUser.setUpdatedDate(DateTimeUtils.getCurrentTimestamp());
		androidUserDAO.saveOrUpdate(androidUser);
		
		AndroidUserDTO androidUserDTO = new AndroidUserDTO();
		androidUserDTO.setToken(androidUser.getToken());
        return androidUserDTO;
	}

	/**
	 * (non-Javadoc)
	 * @see com.gun.service.UserInfoService#registerWithFacebook(com.gun.common.entity.pojo.AndroidUserDTO)
	 */
	@Override
	public AndroidUserDTO registerWithFacebook(AndroidUserDTO androidUserDTO) {
		// 参数验证
		ValidateUtils.required(androidUserDTO);
//		ValidateUtils.required(androidUserDTO.getUsername(), "Username cannot be empty");
//		ValidateUtils.required(androidUserDTO.getPassword(), "Password cannot be empty");
//		ValidateUtils.required(androidUserDTO.getNrc(), "Nrc cannot be empty");
//		ValidateUtils.required(androidUserDTO.getFullName(), "Name cannot be empty");
		ValidateUtils.required(androidUserDTO.getFacebookUserId(), "FacebookUserId cannot be empty");
//		ValidateUtils.email(androidUserDTO.getUsername(), "Username format error");
		// 用户邮箱是否重复
        if(StringUtils.hasText(androidUserDTO.getUsername()) && !checkUsername(androidUserDTO.getUsername())){
        	AndroidUser androidUser = androidUserDAO.selectByUsername(androidUserDTO.getUsername());
        	if (StringUtils.hasText(androidUser.getFacebookUserId()) && StringUtils.isEmpty(androidUser.getCreatedByName())){
        		androidUser.setCreatedByName(androidUser.getFacebookUserId());
        		androidUser.setFacebookUserId(androidUserDTO.getFacebookUserId());
        		androidUser.generateToken();
        		androidUser.setLastLogonTime(DateTimeUtils.getCurrentTimestamp());
        		androidUserDAO.saveOrUpdate(androidUser);
        		
        		AndroidUserDTO originAndroidUser = new AndroidUserDTO();
                originAndroidUser.setFacebookUserId(androidUserDTO.getFacebookUserId());
                return originAndroidUser;
        	} else {
        		throw new BusinessException(ExceptionType.MAILBOX_IS_USED);
        	}
        };
        
        AndroidUserDTO originAndroidUser = new AndroidUserDTO();
        originAndroidUser.setFacebookUserId(androidUserDTO.getFacebookUserId());
        
        if (StringUtils.isEmpty(androidUserDTO.getPassword())) {
        	androidUserDTO.setPassword(androidUserDTO.getFacebookUserId());
        }
        
        if (StringUtils.isEmpty(androidUserDTO.getPasswordSalt())) {
        	androidUserDTO.setPasswordSalt(androidUserDTO.getFacebookUserId());
        }
        
        if (StringUtils.isEmpty(androidUserDTO.getFullName())){
        	androidUserDTO.setFullName(LotteryConstants.STRING_EMPTY);
        }
        
        if (StringUtils.isEmpty(androidUserDTO.getNrc())){
        	androidUserDTO.setNrc(LotteryConstants.STRING_EMPTY);
        }
      
        AndroidUser androidUser = androidUserDTO.transformer(null);
        // 新增用户
        androidUser.encodePassword();
        androidUser.generateToken();
        androidUser.setStatus((byte)1);
        androidUser.setCreatedByName(androidUserDTO.getFacebookUserId());
        AndroidUser saveUser = androidUserDAO.save(androidUser);
        
		return originAndroidUser;
	}

	/**
	 * @param id
	 * @param userInfoDTO
	 */
	public void editInfo(Integer id,UserInfoDTO userInfoDTO){
		if(userInfoDTO == null){
			throw new BusinessException(ExceptionType.USER_NOT_FOUND);
		}
		id = userInfoDTO.getId();
		UserInfo userInfo = null;
		if(id == null){
			userInfo = new UserInfo();
		} else {
			userInfo = userInfoDAO.findById(UserInfo.class, id);
			if(userInfo == null){
				throw new BusinessException(ExceptionType.USER_NOT_FOUND);
			}
		}
		// 验证员工帐号是否重复(新增状态下)
		if(StringUtils.isEmpty(userInfo.getUserId()) && StringUtils.hasText(userInfoDTO.getUserId())){
			if(!checkUsername(userInfoDTO.getUserId())){
				throw new BusinessException(ExceptionType.USER_ID_IS_USED);
			}
			userInfo.setUserId(userInfoDTO.getUserId());
			userInfo.setPassword(userInfoDTO.getUserId());
			userInfo.setCreateById("");
			userInfo.setCreatedByName("");
			userInfo.setCreatedDate(DateTimeUtils.getCurrentDate());
		} else {
			userInfo.setUpdatedById("");
			userInfo.setUpdatedByName("");
			userInfo.setUpdatedDate(DateTimeUtils.getCurrentDate());
		}
		userInfo.setUserName(userInfoDTO.getUserName());
		userInfo.setEmail(userInfoDTO.getEmail());
		userInfo.setRoleId(userInfoDTO.getRoleId());
		this.userInfoDAO.saveOrUpdate(userInfo);
	}
	
	/**
	 * 
	 */
	public List<UserInfoDTO> queryByCriterias(String userId,String userName){
		List<UserInfoDTO> userInfoDTOList = null;
		userInfoDTOList = this.userInfoDAO.queryByCriterias(userId, userName);
		return userInfoDTOList;
	}
	
	public void deleteById(String id){
		if(StringUtils.hasText(id)){
			UserInfo userInfo = this.userInfoDAO.findById(UserInfo.class, Integer.valueOf(id));
			if(userInfo == null){
				throw new BusinessException(ExceptionType.USER_NOT_FOUND);
			}
			this.userInfoDAO.delete(userInfo);
		} else {
			throw new BusinessException(ExceptionType.USER_NOT_FOUND);
		}
	}
	
	public UserInfoDTO getUser(String id){
		UserInfoDTO userInfoDTO = null;
		if(StringUtils.hasText(id)){
			UserInfo userInfo = this.userInfoDAO.findById(UserInfo.class, Integer.valueOf(id));
			if(userInfo != null){
				userInfoDTO = new UserInfoDTO();
				userInfoDTO.setId(userInfo.getId());
				userInfoDTO.setUserId(userInfo.getUserId());
				userInfoDTO.setUserName(userInfo.getUserName());
				userInfoDTO.setEmail(userInfo.getEmail());
				userInfoDTO.setRoleId(userInfo.getRoleId());
			}
			return userInfoDTO;
		} else {
			throw new BusinessException(ExceptionType.USER_NOT_FOUND);
		}
	}
	
	/**
	 * (non-Javadoc)
	 * @see com.gun.service.UserInfoService#editInfo(java.math.BigDecimal, com.gun.common.entity.pojo.AndroidUserDTO)
	 */
	@Override
	public void editInfo(BigDecimal androidUserId, AndroidUserDTO androidUserDTO) {
		AndroidUser androidUser = androidUserDAO.findById(AndroidUser.class, androidUserId);
		if (androidUser == null) {
			throw new BusinessException(ExceptionType.USER_NOT_FOUND);
		}
		
		if (StringUtils.isEmpty(androidUser.getUsername()) && StringUtils.hasText(androidUserDTO.getUsername())){
			// 用户邮箱是否重复
	        if(!checkUsername(androidUserDTO.getUsername())){
	        	throw new BusinessException(ExceptionType.MAILBOX_IS_USED);
	        }
			androidUser.setUsername(androidUserDTO.getUsername());
		}
		if (StringUtils.hasText(androidUserDTO.getNrc())){
			androidUser.setNrc(androidUserDTO.getNrc());
		}
		if (StringUtils.hasText(androidUserDTO.getFullName())){
			androidUser.setFullName(androidUserDTO.getFullName());
		}
		androidUser.setBirthday(androidUserDTO.getBirthday());
		androidUser.setGender(androidUserDTO.getGender());
		androidUser.setAddress(androidUserDTO.getAddress());
		androidUser.setDetailAddress(androidUserDTO.getDetailAddress());
		androidUser.setMobileNo(androidUserDTO.getMobileNo());
		androidUser.setFacebookAccount(androidUserDTO.getFacebookAccount());
		androidUser.setBankCardNumber(androidUserDTO.getBankCardNumber());
		
		androidUserDAO.update(androidUser);
	}

  /** (non-Javadoc)
   * @see com.gun.service.LotteryService#init(java.lang.Object)
   */
  public Model init(Object request) throws ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

}
