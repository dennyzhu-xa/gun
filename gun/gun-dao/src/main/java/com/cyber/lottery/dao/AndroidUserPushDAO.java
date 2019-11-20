package com.cyber.lottery.dao;

import java.math.BigDecimal;
import java.util.List;

import com.gun.common.entity.AndroidUserPush;

/**
 * @author neiljing
 */
public interface AndroidUserPushDAO extends AbstractDAO<AndroidUserPush> {
	
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
	
	int updateStateByRound(String round);

}
