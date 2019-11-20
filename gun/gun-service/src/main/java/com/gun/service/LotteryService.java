package com.gun.service;


import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.Model;

/**
 * @author Felix li
 * @since  JDK 1.7
 * @date   2017/4/5
 * @maintenance Felix li
 */
@SuppressWarnings("rawtypes")
public interface LotteryService<T> {
	
  public abstract Model init(T request) throws ServiceException;
}
