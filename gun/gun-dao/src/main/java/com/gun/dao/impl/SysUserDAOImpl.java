package com.gun.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.exception.DataException;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.cyber.lottery.dao.AbstractDAOImpl;
import com.cyber.lottery.dao.SysUserDAO;
import com.gun.common.entity.SysUserInfo;
import com.gun.common.entity.pojo.SysUserInfoDTO;
import com.gun.common.exception.DataAccessException;
import com.gun.common.utils.SqlSyntaxFactory;
/**
 * Purpose: 用户维护DAO 实现类
 * @author Felixli
 * @since  JDK 1.7
 * @date   2017年6月1日
 * @MaintenancePersonnel Felixli
 */
@Repository
public class SysUserDAOImpl  extends AbstractDAOImpl<SysUserInfo> implements SysUserDAO{
	private static final Log log = LogFactory.getLog(SysUserDAOImpl.class);
	
	@SuppressWarnings("unchecked")
  @Override
	public SysUserInfoDTO getSysUserByUserName(String userId) 	throws DataAccessException {
	  SysUserInfoDTO sysUser = null;
		SQLQuery sqlQuery = null;
		try {
			sqlQuery =  this.getSession().createSQLQuery(SqlSyntaxFactory.GET_USER_BY_NAME);
			sqlQuery.setParameter(SysUserInfoDTO.ATTRIBUTE.USER_ID.getValue(), userId);
			log.debug(this.getClass().getName()+"[SQL] ---> getSysUserByUserName  SQL：" + SqlSyntaxFactory.GET_USER_BY_NAME);
			sqlQuery.setResultTransformer(Transformers.aliasToBean(SysUserInfoDTO.class));
			List<SysUserInfoDTO> results =sqlQuery.list(); 
			if(!CollectionUtils.isEmpty(results)){
				sysUser = results.get(0);
			}
		}catch (DataException e) {
			log.error(this.getClass().getName()+"getSysUserByUserName failed!!" + e );
			throw new DataAccessException(e);
		}
		return sysUser;
	}

  /** (non-Javadoc)
   * @see com.cyber.lottery.dao.SysUserDAO#getSysUserByUserName(int)
   */
  @Override
  public List<SysUserInfoDTO> getSysUserByUserRoleId(int roleId) throws DataAccessException {
    List<SysUserInfoDTO> results=null;
    SQLQuery sqlQuery = null;
    try {
      sqlQuery =  this.getSession().createSQLQuery(SqlSyntaxFactory.GET_USER_BY_ROLE_ID);
      sqlQuery.setParameter(SysUserInfoDTO.ATTRIBUTE.ROLE_ID.getValue(), roleId);
      log.debug(this.getClass().getName()+"[SQL] ---> getSysUserByUserRoleId  SQL：" + SqlSyntaxFactory.GET_USER_BY_ROLE_ID);
      sqlQuery.setResultTransformer(Transformers.aliasToBean(SysUserInfoDTO.class));
      results =sqlQuery.list(); 
    }catch (DataException e) {
      log.error(this.getClass().getName()+"getSysUserByUserRoleId failed!!" + e );
      throw new DataAccessException(e);
    }
    return results;
  }
	
}
