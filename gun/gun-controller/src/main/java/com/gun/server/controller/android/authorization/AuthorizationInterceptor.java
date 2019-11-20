/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gun.server.controller.android.authorization;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gun.common.exception.BusinessException;
import com.gun.common.pojo.ExceptionType;
import com.gun.common.utils.ObjectUtils;
import com.gun.common.utils.VerificationCodeUtils;
import com.gun.server.controller.android.AbstractController;

/**
 * android用户登录拦截器, 拦截需要登录的用户, 拦截需要验证码的用户
 * @see com.gun.server.controller.android.authorization.CheckLogin
 * @see com.gun.server.controller.android.authorization.CheckVerificationCode
 * @author 
 *
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	public static final String CODE_SOURCE_ATTRIBUTE_KEY = "codeSource";
	public static final String ENCRYPT_CODE_SOURCE_ATTRIBUTE_KEY = "encryptCodeSource";
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        
        checkStatus(request);
        
        CheckVerificationCode checkVerificationCode = handlerMethod.getMethodAnnotation(CheckVerificationCode.class);
        if (checkVerificationCode != null) {
        	checkVerificationCode(request);
        }
        checkVerificationCode = handlerMethod.getBeanType().getAnnotation(CheckVerificationCode.class);
        if (checkVerificationCode != null) {
        	checkVerificationCode(request);
        }
        
        CheckLogin checkLogin = handlerMethod.getMethodAnnotation(CheckLogin.class);
        if (checkLogin != null) {
            checkLogin(request);
        }
//        checkLogin = handlerMethod.getBeanType().getDeclaredAnnotation(CheckLogin.class);
        checkLogin = handlerMethod.getBeanType().getAnnotation(CheckLogin.class);
        if (checkLogin != null) {
            checkLogin(request);
        }
        return super.preHandle(request, response, handler);
    }

    private void checkStatus(HttpServletRequest request) {
    	if (request.getAttribute(AbstractController.STATUS_ATTRIBUTE_KEY) != null && (byte)request.getAttribute(AbstractController.STATUS_ATTRIBUTE_KEY) != 1) {
            throw new BusinessException(ExceptionType.ACCOUNT_DISABLE);
        }
	}

	private void checkVerificationCode(HttpServletRequest request) throws ParseException {
    	String source = request.getParameter(CODE_SOURCE_ATTRIBUTE_KEY);
    	String encryptSource = request.getParameter(ENCRYPT_CODE_SOURCE_ATTRIBUTE_KEY);
    	VerificationCodeUtils.checkVerificationCode(source, encryptSource);
	}

	private void checkLogin(HttpServletRequest request) {
        if (ObjectUtils.isEmpty(request.getAttribute(AbstractController.ANDROID_USER_ID_ATTRIBUTE_KEY))) {
            throw new BusinessException(ExceptionType.AUTHORIZATION_ERROR);
        }
    }

}
