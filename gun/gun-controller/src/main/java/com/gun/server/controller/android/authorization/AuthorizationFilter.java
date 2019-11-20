package com.gun.server.controller.android.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gun.common.authorization.AuthorizationHelper;
import com.gun.common.entity.AndroidUser;
import com.gun.common.utils.ObjectUtils;
import com.gun.common.utils.StringUtils;
import com.gun.server.controller.android.AbstractController;
import com.gun.service.UserInfoService;

/**
 * android用户登录过滤器, 获取登录用户信息
 * @author 
 *
 */
public class AuthorizationFilter extends HandlerInterceptorAdapter {

	@Autowired
	private UserInfoService androidUserService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getHeader("token");
		if (StringUtils.isEmpty(token)){
			return true;
		}
		AndroidUser androidUser = androidUserService.checkToken(token);
		if (ObjectUtils.isNull(androidUser)){
			return true;
		}
		if (AuthorizationHelper.isTokenExpired(token, androidUser.getPassword())) {
            return true;
        }
		
		request.setAttribute(AbstractController.STATUS_ATTRIBUTE_KEY, androidUser.getStatus());
		request.setAttribute(AbstractController.USERNAME_ATTRIBUTE_KEY, androidUser.getUsername());
		request.setAttribute(AbstractController.ANDROID_USER_ID_ATTRIBUTE_KEY, androidUser.getId());
		return super.preHandle(request, response, handler);
	}
}
