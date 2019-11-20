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
import com.cyber.lottery.dao.FunctionDefDAO;
import com.gun.common.entity.FunctionType;
import com.gun.common.entity.pojo.FunctionTypeDTO;
import com.gun.common.exception.DataAccessException;
import com.gun.common.pojo.PageInfo;
import com.gun.common.pojo.ParamItem;
import com.gun.common.utils.LotteryMessageCode;
import com.gun.common.utils.SqlSyntaxFactory;
import com.gun.common.utils.StringUtils;

/**
 * @author felixli
 *
 */
@Repository
public class FunctionDefDAOImpl extends AbstractDAOImpl<FunctionType> implements FunctionDefDAO {
	
	/**
	 * 日志记录物件
	 */
	private static final Log log = LogFactory.getLog(FunctionDefDAOImpl.class);


	/** (non-Javadoc)
	 * @see com.cyber.mpos.dao.FunctionDefDAO#queryFunctionsList()
	 */
	public List<FunctionType> queryFunctionsList(Integer roleId) throws DataAccessException {
		//查询结果集
		List<FunctionType> results = null;
		try{
			SQLQuery sqlQuery  = this.getSession().createSQLQuery(SqlSyntaxFactory.QUERY_FUNCTION_TPYE);
			sqlQuery.setParameter("roleId", roleId);
			sqlQuery.setResultTransformer(Transformers.aliasToBean(FunctionType.class));
			results = sqlQuery.list();
		} catch (DataAccessException e) {
			log.error(this.getClass().getName() + ".queryFunctionsList failed!!!!!",e);
			throw new DataAccessException(LotteryMessageCode.DATA_ACCESS_FAILED, e);
		}
		return results;
	}


  /** (non-Javadoc)
   * @see com.cyber.lottery.dao.FunctionDefDAO#queryFunctionsList(com.gun.common.pojo.PageInfo)
   */
  public List<FunctionTypeDTO> list(PageInfo pageInfo) throws DataAccessException {
    List<FunctionTypeDTO> results = null;
    String sqlQuery = null;
    try {
      sqlQuery =  SqlSyntaxFactory.QUERY_FUNCTION_TPYE_LIST;
      log.debug(this.getClass().getName()+"[SQL] ---> list  SQL：" + SqlSyntaxFactory.QUERY_FUNCTION_TPYE_LIST);
      List list = super.getQueryList(sqlQuery, null, Transformers.aliasToBean(FunctionTypeDTO.class), pageInfo);
      if(!CollectionUtils.isEmpty(list)){
        results = (List<FunctionTypeDTO>)list;
      }
    }catch (DataException e) {
      log.error(this.getClass().getName()+"list failed!!" + e );
      throw new DataAccessException(e);
    }
    return results;
  }


  /** (non-Javadoc)
   * @see com.cyber.lottery.dao.FunctionDefDAO#queryParentFunctionList()
   */
  @Override
  public List<ParamItem> queryParentFunctionList() throws DataAccessException {
  //查询结果集
    List<ParamItem> results = null;
    try{
      SQLQuery sqlQuery  = this.getSession().createSQLQuery(SqlSyntaxFactory.QUERY_PARENT_FUNCTION_TPYE);
      
      sqlQuery.setResultTransformer(Transformers.aliasToBean(ParamItem.class));
      results = sqlQuery.list();
    } catch (DataAccessException e) {
      log.error(this.getClass().getName() + ".queryParentFunctionList failed!!!!!",e);
      throw new DataAccessException(LotteryMessageCode.DATA_ACCESS_FAILED, e);
    }
    return results;
  }


  /** (non-Javadoc)
   * @see com.cyber.lottery.dao.FunctionDefDAO#queryFunctionsByParentId(java.lang.String)
   */
  @Override
  public List<FunctionTypeDTO> queryFunctionsByParentId(String parentFunctionId) throws DataAccessException {
  //查询结果集
    List<FunctionTypeDTO> results = null;
    try{
      String sql = SqlSyntaxFactory.QUERY_FUNCTION_TPYE_LIST;
      if(StringUtils.hasText(parentFunctionId)){
        sql +="AND fun.PARENT_FUNCTION_ID != fun.FUNCTION_ID AND (:parentFunctionId IS NULL OR fun.PARENT_FUNCTION_ID=:parentFunctionId) ";
      }
      SQLQuery sqlQuery  = this.getSession().createSQLQuery(sql);
      if(StringUtils.hasText(parentFunctionId)){
        sqlQuery.setParameter(FunctionTypeDTO.ATTRIBUTE.PARENT_FUNCTION_ID.getValue(), parentFunctionId);
      }
      sqlQuery.setResultTransformer(Transformers.aliasToBean(FunctionTypeDTO.class));
      results = sqlQuery.list();
    } catch (DataAccessException e) {
      log.error(this.getClass().getName() + ".queryFunctionsList failed!!!!!",e);
      throw new DataAccessException(LotteryMessageCode.DATA_ACCESS_FAILED, e);
    }
    return results;
  }
  
}
