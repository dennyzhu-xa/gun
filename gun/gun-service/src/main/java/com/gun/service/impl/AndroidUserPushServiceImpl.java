package com.gun.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyber.lottery.dao.AndroidUserPushDAO;
import com.gun.common.entity.AndroidUserPush;
import com.gun.common.utils.LotteryConstants;
import com.gun.service.AndroidUserPushService;

/**
 * Purpose: 用户推送Service实现类
 * @author neiljing
 * @since  JDK 1.7
 * @date   2017年9月8日
 * @MaintenancePersonnel neiljing
 */
@Service
public class AndroidUserPushServiceImpl implements AndroidUserPushService {
	
	private static final Log log = LogFactory.getLog(AndroidUserPushServiceImpl.class);
	
	@Autowired
	private AndroidUserPushDAO androidUserPushDAO;
	
	@Override
	public List<AndroidUserPush> getAndroidUserTopUpPush(BigDecimal userId) {
		List<AndroidUserPush> androidUserPushs = new ArrayList<AndroidUserPush>();
		androidUserPushs = androidUserPushDAO.getAndroidUserTopUpPush(userId);
		for(AndroidUserPush androidUserPush : androidUserPushs){
			//用户接收到推送后，将pushState（推送状态）设置成0
			if(androidUserPush != null){
				AndroidUserPush androidUserPush2 = new AndroidUserPush();
				androidUserPush2 = androidUserPush;
				androidUserPush2.setPushState(LotteryConstants.ZERO);
				androidUserPushDAO.update(androidUserPush2);
			}
		}
		return androidUserPushs;
	}

	@Override
	public List<AndroidUserPush> getAndroidUserWinningPush(BigDecimal userId) {
		List<AndroidUserPush> androidUserPushs = new ArrayList<AndroidUserPush>();
		androidUserPushs = androidUserPushDAO.getAndroidUserWinningPush(userId);
		for(AndroidUserPush androidUserPush : androidUserPushs){
			//用户接收到推送后，将pushState（推送状态）由Y改为N
			if(androidUserPush != null){
				AndroidUserPush androidUserPush2 = new AndroidUserPush();
				androidUserPush2 = androidUserPush;
				androidUserPush2.setPushState(androidUserPush.getPushState() - 1);
				androidUserPushDAO.update(androidUserPush2);
			}
		}
		return androidUserPushs;
	}

}
