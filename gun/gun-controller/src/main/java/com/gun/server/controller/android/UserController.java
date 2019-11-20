package com.gun.server.controller.android;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gun.common.entity.UserInfo;
import com.gun.common.entity.pojo.AndroidUserDTO;
import com.gun.common.entity.pojo.UserInfoDTO;
import com.gun.server.controller.android.authorization.CheckLogin;
import com.gun.service.UserInfoService;
import com.gun.service.login.LoginService;
import com.gun.service.utils.MailComponent;

/**
 * Purpose: 用户  Controller
 * @since  JDK 1.7
 * @date   2019/3/20
 */
@RequestMapping("/Gui/User")
@Controller
public class UserController extends AbstractController{
	
	@Autowired
	protected UserInfoService userInfoService;
	
	@Autowired
	protected LoginService loginService;
	
	@Autowired
	private MailComponent mailComponent;
	
	/**
	 * Purpose: 通过旧密码修改密码
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @return void
	 */
	@RequestMapping(value = "/ModifyPasswordByOldPassword" , method = RequestMethod.POST)
	@CheckLogin
	public void modifyPasswordByOldPassword(String oldPassword, String newPassword) {
		userInfoService.modifyPassword(getUsername(), oldPassword, newPassword);
		writeJson();
	}
	
	/**
	 * Purpose: 登录
	 * @param userId 用户名
	 * @param password 密码
	 * @return void
	 */
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public void login(String userId, String password) {
		UserInfoDTO user =  userInfoService.login(userId, password);
		writeJson(user);
	}
	
	/**
	 * Purpose: 修改用户信息
	 * @return void
	 */
	@RequestMapping(value = "/EditUserInfo", method = RequestMethod.POST)
	public void editInfo(UserInfoDTO userInfoDTO) {
		userInfoService.editInfo(getUserId(), userInfoDTO);
		writeJson();
	}
	
	/**
	 * Purpose:查询用户资料功能
	 * @param userId
	 * @param userName
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	public void queryList(String userId,String userName){
		List<UserInfoDTO> results = userInfoService.queryByCriterias(userId, userName);
		writeJson(results);
	}

	/**
	 * Purpose:删除用户资料功能
	 * @param id
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public void deleteUser(String id){
		userInfoService.deleteById(id);
		writeJson();
	}
	@RequestMapping(value = "/getUser", method = RequestMethod.POST)
	public void getUser(String id){
		UserInfoDTO userInfo = userInfoService.getUser(id);
		writeJson(userInfo);
	}
	
	/**
	 * Purpose: 获取登录用户信息
	 * @return void
	 */
	@RequestMapping(value = "/LoginAndroidUser", method = RequestMethod.GET)
	@CheckLogin
	public void LoginAndroidUser() {
		AndroidUserDTO androidUser = userInfoService.getLoginAndroidUser(getAndroidUserId());
		writeJson(androidUser);
	}
	
	
}
