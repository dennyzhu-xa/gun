package com.gun.service;

import java.math.BigDecimal;
import java.util.List;

import com.gun.common.entity.AndroidUserPush;

/**
 * Purpose:  android用户推送接口
 * @author neiljing
 * @since  JDK 1.7
 * @date   2017年9月8日
 * @MaintenancePersonnel neiljing
 */
public interface AndroidUserPushService {

	/**
	 * 查询充值推送方法 by userId	
	 * @param userId
	 * @return List<AndroidUserPush>
	 */
	List<AndroidUserPush> getAndroidUserTopUpPush(BigDecimal userId);
	
	/**
	 * 查询中奖推送方法 by userId	
	 * @param userId
	 * @return List<AndroidUserPush>
	 */
	List<AndroidUserPush> getAndroidUserWinningPush(BigDecimal userId);
}
