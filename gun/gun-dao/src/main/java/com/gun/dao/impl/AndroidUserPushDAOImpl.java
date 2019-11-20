package com.gun.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.exception.DataException;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.cyber.lottery.dao.AbstractDAOImpl;
import com.cyber.lottery.dao.AndroidUserPushDAO;
import com.gun.common.entity.AndroidUserPush;
import com.gun.common.entity.pojo.InputWinninNoDTO;
import com.gun.common.exception.DataAccessException;
import com.gun.common.pojo.PageInfo;
import com.gun.common.utils.LotteryConstants;
import com.gun.common.utils.SqlSyntaxFactory;

@Repository
public class AndroidUserPushDAOImpl extends AbstractDAOImpl<AndroidUserPush> implements AndroidUserPushDAO {

	private static final Log log = LogFactory.getLog(AndroidUserPushDAOImpl.class);
	
	@Override
	public List<AndroidUserPush> getAndroidUserTopUpPush(BigDecimal userId) {
		List<AndroidUserPush> androidUserPushs = new ArrayList<AndroidUserPush>();
		String sql = "";
		try {
			sql = SqlSyntaxFactory.GET_ANDROID_USER_PUSH_BY_USERID;
			Map<String,Object> params = new HashMap<String, Object>();
			if(userId != null){
				//获得当前日期（推送不设置失效时间）
//				Date date=new Date();
//				DateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//				String nowTime=format.format(date);
				sql += " AND a.PUSH_TYPE = '"+LotteryConstants.PUSH_TYPE_TOPUP+"'";
				sql += " AND a.USER_ID = '"+userId+"'";
				sql += " AND a.PUSH_STATE > "+LotteryConstants.ZERO;
//				sql += " AND a.FAILURE_TIME >= '"+nowTime+"'";
			}
			log.debug(this.getClass().getName()+"[SQL] ---> selectByUsername  SQL：" + sql);
			List list = super.getQueryList(sql, params, Transformers.aliasToBean(AndroidUserPush.class), null);
			if(!CollectionUtils.isEmpty(list)){
				androidUserPushs = (List<AndroidUserPush>)list;
			}
		}catch (DataException e) {
			log.error(this.getClass().getName()+"getAndroidUserTopUpPush failed!!" + e );
			throw new DataAccessException(e);
		}
		return androidUserPushs;
	}

	@Override
	public List<AndroidUserPush> getAndroidUserWinningPush(BigDecimal userId) {
		List<AndroidUserPush> androidUserPushs = new ArrayList<AndroidUserPush>();
		String sql = "";
		try {
			sql = SqlSyntaxFactory.GET_ANDROID_USER_PUSH_BY_USERID;
			Map<String,Object> params = new HashMap<String, Object>();
			if(userId != null){
				//获得当前日期（推送不设置失效时间）
//				Date date=new Date();
//				DateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//				String nowTime=format.format(date);
				sql += " AND (a.PUSH_TYPE = '"+LotteryConstants.PUSH_TYPE_WINNING+"'" + " OR a.PUSH_TYPE = '"+LotteryConstants.PUSH_TYPE_CHECK+"')";
				sql += " AND a.USER_ID = '"+userId+"'";
				sql += " AND a.PUSH_STATE > "+LotteryConstants.ZERO;
//				sql += " AND a.FAILURE_TIME >= '"+nowTime+"'";
			}
			log.debug(this.getClass().getName()+"[SQL] ---> selectByUsername  SQL：" + sql);
			List list = super.getQueryList(sql, params, Transformers.aliasToBean(AndroidUserPush.class), null);
			if(!CollectionUtils.isEmpty(list)){
				androidUserPushs = (List<AndroidUserPush>)list;
			}
		}catch (DataException e) {
			log.error(this.getClass().getName()+"getAndroidUserTopUpPush failed!!" + e );
			throw new DataAccessException(e);
		}
		return androidUserPushs;
	}

	@Override
	public int updateStateByRound(String round) {
		int result = -1;
		try{
			String sql = "UPDATE dbo.ANDROID_USER_PUSH SET PUSH_STATE = 0 WHERE LOTTERY_ROUND = :round";
			SQLQuery sqlUpdate = this.getSession().createSQLQuery(sql);
			sqlUpdate.setParameter("round", round);
			result = sqlUpdate.executeUpdate();
		}catch(Exception e){
			log.error(this.getClass().getName()+"updateStateByRound failed!!" + e );
			throw new DataAccessException(e);
		}
		return result;
	}

}
