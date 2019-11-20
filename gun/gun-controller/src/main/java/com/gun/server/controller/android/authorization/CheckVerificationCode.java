package com.gun.server.controller.android.authorization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Purpose: 对需要检查验证码的Controller类和方法添加，会对客户端传过来的验证码进行检查
 * @see com.gun.server.controller.android.authorization.AuthorizationInterceptor
 * @author 
 * @since  JDK 1.7
 * @date   2017年8月25日
 * @MaintenancePersonnel 
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckVerificationCode {
	
}
