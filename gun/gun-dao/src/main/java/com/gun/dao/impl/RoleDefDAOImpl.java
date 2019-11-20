package com.gun.dao.impl;

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
import com.cyber.lottery.dao.RoleDefDAO;
import com.gun.common.entity.RoleDef;
import com.gun.common.entity.pojo.RoleAuthorityDTO;
import com.gun.common.entity.pojo.RoleDefDTO;
import com.gun.common.exception.DataAccessException;
import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.PageInfo;
import com.gun.common.pojo.ParamItem;
import com.gun.common.utils.SqlSyntaxFactory;

/**
 * 
 * @author felixli
 *
 */
@Repository
public class RoleDefDAOImpl extends AbstractDAOImpl<RoleDef> implements RoleDefDAO {
	private static final Log log = LogFactory.getLog(RoleDefDAOImpl.class);
	
	@SuppressWarnings("unchecked")

  /** (non-Javadoc)
   * @see com.cyber.lottery.dao.RoleDefDAO#getRoleList()
   */
  public List<ParamItem> getRoleList() throws DataAccessException {
    List<ParamItem> results = new ArrayList<ParamItem>();
    SQLQuery sqlQuery = null;
    try {
      sqlQuery =  this.getSession().createSQLQuery(SqlSyntaxFactory.QUERY_ROLE_ALL);
      sqlQuery.setParameter(RoleDefDTO.ATTRIBUTE.ROLE_NAME.getValue(), null);
      log.debug(this.getClass().getName()+"[SQL] ---> getRoleList  SQL：" + SqlSyntaxFactory.QUERY_ROLE_ALL);
      sqlQuery.setResultTransformer(Transformers.aliasToBean(ParamItem.class));
      results = sqlQuery.list();
    } catch (DataException e) {
      log.error(this.getClass().getName()+"getRoleList failed!!" + e );
      throw new DataAccessException(e);
    }
    return results;
  }

  /** (non-Javadoc)
   * @see com.cyber.lottery.dao.RoleDefDAO#deleteByRoleId(java.lang.Integer)
   */
  public void deleteByRoleId(Integer roleId) throws ServiceException {
    SQLQuery sqlQuery = null;
    try {
      sqlQuery =  this.getSession().createSQLQuery("DELETE FROM ROLE_DEF WHERE ROLE_ID= ?");
      log.debug(this.getClass().getName()+"[SQL] ---> deleteByRoleId  SQL：" + "DELETE FROM ROLE_DEF WHERE ROLE_ID= ?");
      sqlQuery.setParameter(0, roleId);
      sqlQuery.executeUpdate();
    } catch (DataException e) {
      log.error(this.getClass().getName()+"deleteByRoleId failed!!" + e );
      throw new DataAccessException(e);
    }
  }

  /** (non-Javadoc)
   * @see com.cyber.lottery.dao.RoleDefDAO#list(com.gun.common.pojo.PageInfo)
   */
  @Override
  public List<ParamItem> list(PageInfo pageInfo) throws DataAccessException {
    List<ParamItem> results = null;
    String sqlQuery = null;
    try {
      sqlQuery =  SqlSyntaxFactory.QUERY_ROLE_ALL;
      Map<String, Object> params = new HashMap<String, Object>();
      params.put(RoleDefDTO.ATTRIBUTE.ROLE_NAME.getValue(), null);
      log.debug(this.getClass().getName()+"[SQL] ---> list  SQL：" + SqlSyntaxFactory.QUERY_ROLE_ALL);
      List list = super.getQueryList(sqlQuery, params, Transformers.aliasToBean(ParamItem.class), pageInfo);
      if(!CollectionUtils.isEmpty(list)){
        results = (List<ParamItem>)list;
      }
    }catch (DataException e) {
      log.error(this.getClass().getName()+"list failed!!" + e );
      throw new DataAccessException(e);
    }
    return results;
  }

  /** (non-Javadoc)
   * @see com.cyber.lottery.dao.RoleDefDAO#getRoleDefByRoleName(java.lang.String)
   */
  public boolean getRoleDefByRoleName(String roleName) throws DataAccessException {
    SQLQuery sqlQuery = null;
    boolean results = false;
    try {
      sqlQuery =  this.getSession().createSQLQuery(SqlSyntaxFactory.QUERY_ROLE_ALL);
      sqlQuery.setParameter(RoleDefDTO.ATTRIBUTE.ROLE_NAME.getValue(), roleName);
      sqlQuery.setResultTransformer(Transformers.aliasToBean(ParamItem.class));
      log.debug(this.getClass().getName()+"[SQL] ---> list  SQL：" + SqlSyntaxFactory.QUERY_ROLE_ALL);
      List list = sqlQuery.list();
      if(!CollectionUtils.isEmpty(list)){
        results = true;
      }
    }catch (DataException e) {
      log.error(this.getClass().getName()+"list failed!!" + e );
      throw new DataAccessException(e);
    }
    return results;
  }
}
