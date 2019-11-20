package com.gun.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.exception.DataException;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.cyber.lottery.dao.AbstractDAOImpl;
import com.cyber.lottery.dao.UserInfoDAO;
import com.gun.common.entity.UserInfo;
import com.gun.common.entity.pojo.UserInfoDTO;
import com.gun.common.exception.DataAccessException;
@Repository
public class UserInfoDAOImpl extends AbstractDAOImpl<UserInfo> implements UserInfoDAO {

	private static final Log log = LogFactory.getLog(UserInfoDAOImpl.class);
	
	public UserInfo selectByUserAccount(String userId){
		UserInfo userInfo = null;
		SQLQuery sqlQuery = null;
		try{
			String sql = prepareSQLForUserInfo();
			sql += " AND u.USER_ID=:userId";
			sqlQuery =  this.getSession().createSQLQuery(sql);
			sqlQuery.setParameter(UserInfoDTO.ATTRIBUTE.USER_ID.getValue(), userId);
			sqlQuery.setResultTransformer(Transformers.aliasToBean(UserInfo.class));
			userInfo = (UserInfo) sqlQuery.uniqueResult();
		}catch (DataException e) {
			log.error(this.getClass().getName()+"selectByUserAccount failed!!" + e );
			throw new DataAccessException(e);
		}
		return userInfo;
	}
	
	public String prepareSQLForUserInfo(){
		String sql = "";
		sql = "SELECT u.ID as id ,u.USER_ID as \"userId\",u.USER_NAME as \"userName\",u.EMAIL as email,"
				+ " u.PASSWORD as password,u.ROLE_ID as \"roleId\",u.CREATE_BY_ID as \"createById\",u.CREATED_BY_NAME as \"createdByName\","
				+ " u.CREATED_DATE as \"createdDate\",u.UPDATED_BY_ID as \"updatedById\",u.UPDATED_BY_NAME as \"updatedByName\",u.UPDATED_DATE as \"updatedDate\""
				+ " FROM USER_INFO u WHERE 1=1";
		return sql;
	}
	
	public List<UserInfoDTO> queryByCriterias(String userId,String userName){
		
		List<UserInfoDTO> userInfoDTOList = null;
		SQLQuery sqlQuery = null;
		try{
			String sql = prepareSQLForUserInfo();
			if(StringUtils.hasText(userId)){
				sql += " AND u.USER_ID = :userId ";
			}
			if(StringUtils.hasText(userName)){
				sql += " AND u.USER_NAME like '%" + userName +"%'";
			}
			sqlQuery =  this.getSession().createSQLQuery(sql);
			if(StringUtils.hasText(userId)){
				sqlQuery.setParameter(UserInfoDTO.ATTRIBUTE.USER_ID.getValue(), userId);
			}
			sqlQuery.setResultTransformer(Transformers.aliasToBean(UserInfoDTO.class));
			userInfoDTOList = sqlQuery.list();
		} catch (DataException e) {
			log.error(this.getClass().getName()+"queryByCriterias failed!!" + e );
			throw new DataAccessException(e);
		}
		return userInfoDTOList;
	}
}
