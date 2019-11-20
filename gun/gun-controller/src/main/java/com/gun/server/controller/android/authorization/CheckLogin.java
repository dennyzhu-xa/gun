package com.gun.server.controller.android.authorization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Purpose: 检查登录注解，给Controller类或者方法加上，则会在调用方法之前检查用户是否登录，未登录不能访问
 * @see com.gun.server.controller.android.authorization.AuthorizationFilter
 * @see com.gun.server.controller.android.authorization.AuthorizationInterceptor
 * @since  JDK 1.7
 * @date   2017年8月25日
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckLogin {
}
