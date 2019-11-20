package com.gun.service;

import java.math.BigDecimal;
import java.util.List;

import com.gun.common.entity.AndroidUser;
import com.gun.common.entity.UserInfo;
import com.gun.common.entity.pojo.AndroidUserDTO;
import com.gun.common.entity.pojo.UserInfoDTO;
import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.Model;
import com.gun.common.pojo.UserSessionContext;

/**
 * Purpose:  android用户接口
 * @since  JDK 1.7
 * @date   2019年3月25日
 * @MaintenancePersonnel 
 */
@SuppressWarnings("rawtypes")
public interface UserInfoService extends LotteryService{

	/**
	 * 注册方法
	 * @param androidUser 用户信息对象
	 */
	void register(AndroidUserDTO androidUser);

	/**
	 * 登录方法 by 用户名和密码	
	 * @param username 用户名(email)
	 * @param password 密码
	 * @return 包含token的用户信息
	 */
	UserInfoDTO login(String username, String password);

	/**
	 * 登录方法 by token
	 * @param token token
	 * @return 包含token的用户信息
	 */
	AndroidUserDTO loginWithToken(String token);

	/**
	 * Purpose: 通过旧密码修改密码
	 * @param username		用户名(email)
	 * @param oldPassword	旧密码
	 * @param newPassword	新密码
	 */
    void modifyPassword(String username, String oldPassword, String newPassword);

    /**
     * 检查token是否存在，存在则返回对应实体
     * @param token 要检查的token
     * @return 用户信息
     */
    AndroidUser checkToken(String token);

    /**
     * 获取用户信息
     * @param userId 用户ID
     * @return  包含token的用户信息
     */
    AndroidUserDTO getLoginAndroidUser(BigDecimal userId);
    
    /**
     * 修改密码
     * @param username		用户名(email)
     * @param newPassword	新密码
     */
	void modifyPassword(String username, String newPassword);

	/**
	 * Purpose: FaceBook登录
	 * @param facebookUserId	FaceBookUserID
	 * @return AndroidUserDTO   包含token的用户信息
	 */
	AndroidUserDTO loginWithFacebookUserId(String facebookUserId);

	/**
	 * FaceBook帐号登录
	 * @param androidUser
	 * @return 包含facebookUserId的用户信息
	 */
	AndroidUserDTO registerWithFacebook(AndroidUserDTO androidUser);

	/**
	 * Purpose: 编辑用户信息
	 * @author kevinshen
	 * @param bigDecimal 用户ID
	 * @param androidUserDTO 用户信息
	 * @return void
	 */
	void editInfo(BigDecimal bigDecimal, AndroidUserDTO androidUserDTO);
	
	/**
	 * Purpose: 编辑用户信息
	 * @param id
	 * @param userInfoDTO
	 */
	public void editInfo(Integer id,UserInfoDTO userInfoDTO);
	
	/**
	 * Purpose:query
	 * @param userId
	 * @param userName
	 * @return
	 */
	public List<UserInfoDTO> queryByCriterias(String userId,String userName);
	
	/**
	 * Purpose:delete
	 * @param id
	 */
	public void deleteById(String id);
	
	public UserInfoDTO getUser(String id);
	
	/**
	 * Purpose: 根据用户名查询用户信息
	 * @author KevinShen 
	 * @param username 用户名
	 * @return AndroidUserDTO 用户信息
	 */
	AndroidUserDTO getAndroidUserByUsername(String username);

}
