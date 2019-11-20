package com.gun.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.exception.DataException;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.cyber.lottery.dao.AbstractDAOImpl;
import com.cyber.lottery.dao.AndroidUserDAO;
import com.gun.common.entity.AndroidUser;
import com.gun.common.entity.pojo.AndroidUserDTO;
import com.gun.common.entity.pojo.CustomerWinningDTO;
import com.gun.common.exception.DataAccessException;
import com.gun.common.pojo.PageInfo;
import com.gun.common.pojo.ParamItem;
import com.gun.common.utils.SqlSyntaxFactory;
import com.gun.common.utils.StringUtils;

@Repository
public class AndroidUserDAOImpl extends AbstractDAOImpl<AndroidUser> implements AndroidUserDAO {
	
	private static final Log log = LogFactory.getLog(AndroidUserDAOImpl.class);
	
	@Override
	public AndroidUser selectByUsername(String username) {
		AndroidUser androidUser = null;
		SQLQuery sqlQuery = null;
		try {
			String sql = SqlSyntaxFactory.GET_ANDROID_USER_BY_ANDROIDUSER;
			sql += " AND a.USERNAME=:username";
			sqlQuery =  this.getSession().createSQLQuery(sql);
			sqlQuery.setParameter(AndroidUserDTO.ATTRIBUTE.USERNAME.getValue(), username);
			log.debug(this.getClass().getName()+"[SQL] ---> selectByUsername  SQL：" + sql);
			sqlQuery.setResultTransformer(Transformers.aliasToBean(AndroidUser.class));
			androidUser = (AndroidUser) sqlQuery.uniqueResult();
		}catch (DataException e) {
			log.error(this.getClass().getName()+"selectByUsername failed!!" + e );
			throw new DataAccessException(e);
		}
		return androidUser;
	}

	@Override
	public AndroidUser selectByToken(String token) {
		AndroidUser androidUser = null;
		SQLQuery sqlQuery = null;
		try {
			String sql = SqlSyntaxFactory.GET_ANDROID_USER_BY_ANDROIDUSER;
			sql += " AND a.TOKEN=:token";
			sqlQuery =  this.getSession().createSQLQuery(sql);
			sqlQuery.setParameter(AndroidUserDTO.ATTRIBUTE.TOKEN.getValue(), token);
			log.debug(this.getClass().getName()+"[SQL] ---> selectByToken  SQL：" + sql);
			sqlQuery.setResultTransformer(Transformers.aliasToBean(AndroidUser.class));
			androidUser = (AndroidUser) sqlQuery.uniqueResult();
		}catch (DataException e) {
			log.error(this.getClass().getName()+"selectByToken failed!!" + e );
			throw new DataAccessException(e);
		}
		return androidUser;
	}

	@Override
	public AndroidUserDTO selectById(BigDecimal userId) {
		AndroidUserDTO androidUser = null;
		SQLQuery sqlQuery = null;
		try {
			sqlQuery =  this.getSession().createSQLQuery(SqlSyntaxFactory.GET_ANDROID_USER_BY_ID)
					.addScalar("id", BigDecimalType.INSTANCE)
					.addScalar("username", StringType.INSTANCE)
					.addScalar("fullName", StringType.INSTANCE)
					.addScalar("nrc", StringType.INSTANCE)
					.addScalar("birthday", StringType.INSTANCE)
					.addScalar("gender", StringType.INSTANCE)
					.addScalar("address", StringType.INSTANCE)
					.addScalar("mobileNo", StringType.INSTANCE)
					.addScalar("facebookAccount", StringType.INSTANCE)
					.addScalar("createdDate", TimestampType.INSTANCE)
					.addScalar("updatedDate", TimestampType.INSTANCE)
					.addScalar("lastLogonTime", TimestampType.INSTANCE)
					.addScalar("detailAddress", StringType.INSTANCE)
					.addScalar("lastPurchaseTime", TimestampType.INSTANCE)
					.addScalar("balance", BigDecimalType.INSTANCE)
					.addScalar("bankCardNumber", StringType.INSTANCE);
			sqlQuery.setParameter(AndroidUserDTO.ATTRIBUTE.ID.getValue(), userId);
			log.debug(this.getClass().getName()+"[SQL] ---> selectById  SQL：" + SqlSyntaxFactory.GET_ANDROID_USER_BY_ID);
			sqlQuery.setResultTransformer(Transformers.aliasToBean(AndroidUserDTO.class));
			androidUser = (AndroidUserDTO) sqlQuery.uniqueResult();
		}catch (DataException e) {
			log.error(this.getClass().getName()+"selectById failed!!" + e );
			throw new DataAccessException(e);
		}
		return androidUser;
	}
	

	@Override
	public List<AndroidUserDTO> exportUser(PageInfo pageInfo,String username,String mobileNo)throws DataAccessException {
		List<AndroidUserDTO> listAndroidUserDTO= null;
		String  sql = null;
		try {
			sql = SqlSyntaxFactory.CUSTOMER_INFORMATION;
			Map<String,Object> params = new HashMap<String, Object>();
			if(StringUtils.hasText(username)){
				sql+=" AND au.USERNAME like :username ";
				params.put("username", "%" + username + "%");
			}
			if(StringUtils.hasText(mobileNo)){
				sql+=" AND au.MOBILE_NO like :mobileNo ";
				params.put("mobileNo", "%" + mobileNo + "%");
			}
			SQLQuery sqlQuery  = this.getSession().createSQLQuery(sql)
					.addScalar("id", BigDecimalType.INSTANCE)
					.addScalar("username", StringType.INSTANCE)
					.addScalar("facebookAccount", StringType.INSTANCE)
					.addScalar("fullName", StringType.INSTANCE)
					.addScalar("lastLogonTime", TimestampType.INSTANCE)
					.addScalar("mobileNo", StringType.INSTANCE)
					.addScalar("nrc", StringType.INSTANCE)
					.addScalar("birthday", StringType.INSTANCE)
					.addScalar("gender", StringType.INSTANCE)
					.addScalar("totalExpenditure", BigDecimalType.INSTANCE)
					.addScalar("lastPurchaseTime", TimestampType.INSTANCE)
					.addScalar("address", StringType.INSTANCE)
					.addScalar("totalPrizeAmount", BigDecimalType.INSTANCE)
					.addScalar("pieces", IntegerType.INSTANCE)
					.addScalar("winningTimes", IntegerType.INSTANCE)
					.addScalar("balance", BigDecimalType.INSTANCE)
					.addScalar("purchaseTimes", IntegerType.INSTANCE);
			log.debug(this.getClass().getName()+".getQueryList SQL======>"+sql);
			if(!CollectionUtils.isEmpty(params)){
				Set<String> keys =  params.keySet();
				for (String key : keys) {
					sqlQuery.setParameter(key, params.get(key));
					log.debug(this.getClass().getName()+".exportUser "+key+":======>"+params.get(key));
				}
			}
			if (pageInfo != null) {
				List results = sqlQuery.list();
				int totalCnt = 0;
				if(!CollectionUtils.isEmpty(results)){
					totalCnt = results.size();
				}
				pageInfo.setTotal(totalCnt);
				// 分頁資料撈取
				sqlQuery.setMaxResults(pageInfo.getSize());
				sqlQuery.setFirstResult(pageInfo.getPage() * pageInfo.getSize());
			}
			sqlQuery.setResultTransformer(Transformers.aliasToBean(AndroidUserDTO.class));
			List list = sqlQuery.list();
			if(!CollectionUtils.isEmpty(list)){
				listAndroidUserDTO = (List<AndroidUserDTO>)list;
			}
			log.debug(this.getClass().getName()+"[SQL] ---> exportUser  SQL：" + sql);
		}catch (DataException e) {
			log.error(this.getClass().getName()+"exportUser failed!!" + e );
			throw new DataAccessException(e);
		}
		return listAndroidUserDTO;
	}

	@Override
	public AndroidUser selectByFaceBookUserId(String facebookUserId) {
		AndroidUser androidUser = null;
		SQLQuery sqlQuery = null;
		try {
			String sql = SqlSyntaxFactory.GET_ANDROID_USER_BY_ANDROIDUSER;
			sql += " AND a.FACEBOOK_USER_ID=:facebookUserId";
			sqlQuery =  this.getSession().createSQLQuery(sql);
			sqlQuery.setParameter(AndroidUserDTO.ATTRIBUTE.FACEBOOK_USER_ID.getValue(), facebookUserId);
			log.debug(this.getClass().getName()+"[SQL] ---> selectByUsername  SQL：" + sql);
			sqlQuery.setResultTransformer(Transformers.aliasToBean(AndroidUser.class));
			androidUser = (AndroidUser) sqlQuery.uniqueResult();
		}catch (DataException e) {
			log.error(this.getClass().getName()+"selectByUsername failed!!" + e );
			throw new DataAccessException(e);
		}
		return androidUser;
	}


  @Override
	public List<CustomerWinningDTO> queryCustomerWinning(PageInfo pageInfo,String period,String username)throws DataAccessException {
		List<CustomerWinningDTO> listCustomerWinningDTOs= null;
		String  sql = null;
		try {
			sql = SqlSyntaxFactory.CUSTOMER_WINNING;
			Map<String,Object> params = new HashMap<String, Object>();
			if(StringUtils.hasText(period)){
				sql+=" AND WINNIN_PERIOD= :period ";
				params.put("period", period);
			}
			if(StringUtils.hasText(username)){
				sql+=" AND USERNAME like :username ";
				params.put("username", "%" + username + "%");
			}
			sql+=" ORDER BY au.FULL_NAME DESC, wn.WINNIN_GRADE ASC";
			SQLQuery sqlQuery  = this.getSession().createSQLQuery(sql)
					.addScalar("period", StringType.INSTANCE)
					.addScalar("username", StringType.INSTANCE)
					.addScalar("fullName", StringType.INSTANCE)
					.addScalar("nrc", StringType.INSTANCE)
					.addScalar("mobileNo", StringType.INSTANCE)
					.addScalar("lotteryCategory", StringType.INSTANCE)
					.addScalar("lotteryCategoryName", StringType.INSTANCE)
					.addScalar("subCategoryId", IntegerType.INSTANCE)
					.addScalar("subCategoryName", StringType.INSTANCE)
					.addScalar("lotteryNumber", StringType.INSTANCE)
					.addScalar("winninGrade", IntegerType.INSTANCE)
					.addScalar("winninName", StringType.INSTANCE)
					.addScalar("winninNumber", StringType.INSTANCE)
					.addScalar("winninSum", BigDecimalType.INSTANCE)
					.addScalar("winninId", StringType.INSTANCE);
			log.debug(this.getClass().getName()+".getQueryList SQL======>"+sql);
			if(!CollectionUtils.isEmpty(params)){
				Set<String> keys =  params.keySet();
				for (String key : keys) {
					sqlQuery.setParameter(key, params.get(key));
					log.debug(this.getClass().getName()+".getQueryList "+key+":======>"+params.get(key));
				}
			}
			if (pageInfo != null) {
				List results = sqlQuery.list();
				int totalCnt = 0;
				if(!CollectionUtils.isEmpty(results)){
					totalCnt = results.size();
				}
				pageInfo.setTotal(totalCnt);
				// 分頁資料撈取
				sqlQuery.setMaxResults(pageInfo.getSize());
				sqlQuery.setFirstResult(pageInfo.getPage() * pageInfo.getSize());
			}
			sqlQuery.setResultTransformer(Transformers.aliasToBean(CustomerWinningDTO.class));
			List list = sqlQuery.list();
			if(!CollectionUtils.isEmpty(list)){
				listCustomerWinningDTOs = (List<CustomerWinningDTO>)list;
			}
			log.debug(this.getClass().getName()+"[SQL] ---> queryCustomerWinning  SQL：" + sql);
		}catch (DataException e) {
			log.error(this.getClass().getName()+"queryCustomerWinning failed!!" + e );
			throw new DataAccessException(e);
		}
		return listCustomerWinningDTOs;
	}
  
  	@Override
	public List<ParamItem> getNumberOfPeriods() throws DataAccessException {
	  List<ParamItem> results= null;
		SQLQuery sqlQuery = null;
		try {
			sqlQuery =  this.getSession().createSQLQuery(SqlSyntaxFactory.GET_NUMBER_OF_PERIODS);
			log.debug(this.getClass().getName()+"[SQL] ---> getNumberOfPeriods  SQL：" + SqlSyntaxFactory.GET_NUMBER_OF_PERIODS);
			sqlQuery.setMaxResults(12);
			sqlQuery.setFirstResult(0);
			sqlQuery.setResultTransformer(Transformers.aliasToBean(ParamItem.class));
			results =sqlQuery.list(); 
		}catch (DataException e) {
			log.error(this.getClass().getName()+"getNumberOfPeriods failed!!" + e );
			throw new DataAccessException(e);
		}
		return results;
	}
}
