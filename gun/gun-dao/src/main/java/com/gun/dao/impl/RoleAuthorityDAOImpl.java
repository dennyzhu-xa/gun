package com.gun.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.exception.DataException;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.cyber.lottery.dao.AbstractDAOImpl;
import com.cyber.lottery.dao.RoleAuthorityDAO;
import com.gun.common.entity.RoleAuthority;
import com.gun.common.entity.pojo.RoleAuthorityDTO;
import com.gun.common.exception.DataAccessException;
import com.gun.common.utils.SqlSyntaxFactory;

/**
 * 
 * @author felixli
 *
 */
@Repository
public class RoleAuthorityDAOImpl extends AbstractDAOImpl<RoleAuthority> implements RoleAuthorityDAO {
	private static final Log log = LogFactory.getLog(RoleAuthorityDAOImpl.class);
	
	@SuppressWarnings("unchecked")
  @Override
	public List<RoleAuthorityDTO> queryRoleAuthorityByRole(Integer roleId) throws DataAccessException{
		List<RoleAuthorityDTO> results = null;
		SQLQuery sqlQuery = null;
		try {
			sqlQuery =  this.getSession().createSQLQuery(SqlSyntaxFactory.QUERY_ROLE_AUTHORITY_BY_ROLE);
//			sqlQuery =  this.getSession().createSQLQuery("SELECT ra.* FROM role_authority ra WHERE ra.role= ?");
			log.debug(this.getClass().getName()+"[SQL] ---> queryRoleAuthorityByRole  SQL：" + SqlSyntaxFactory.QUERY_ROLE_AUTHORITY_BY_ROLE);
			sqlQuery.setParameter(0, roleId);
			sqlQuery.setResultTransformer(Transformers.aliasToBean(RoleAuthorityDTO.class));
			results = sqlQuery.list();
		}catch (DataException e) {
			log.error(this.getClass().getName()+"queryRoleAuthorityByRole failed!!" + e );
			throw new DataAccessException(e);
		}
		return results;
	}

	@Override
	public void deleteByRoleId(Integer roleId) throws DataAccessException {
		SQLQuery sqlQuery = null;
		try {
			sqlQuery =  this.getSession().createSQLQuery("DELETE FROM ROLE_AUTHORITY WHERE ROLE_ID= ?");
			log.debug(this.getClass().getName()+"[SQL] ---> deleteByRoleId  SQL：" + "DELETE FROM role_authority ra WHERE ra.role= ?");
			sqlQuery.setParameter(0, roleId);
			sqlQuery.executeUpdate();
		}catch (DataException e) {
			log.error(this.getClass().getName()+"deleteByRoleId failed!!" + e );
			throw new DataAccessException(e);
		}
	}

}
