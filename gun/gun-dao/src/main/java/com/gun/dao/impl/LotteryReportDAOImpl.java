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
import com.cyber.lottery.dao.LotteryReportDAO;
import com.gun.common.entity.pojo.LotteryReportDTO;
import com.gun.common.entity.pojo.NearbyStoresDTO;
import com.gun.common.exception.DataAccessException;
import com.gun.common.pojo.PageInfo;
import com.gun.common.pojo.ParamItem;
import com.gun.common.pojo.form.LotteryReportForm;
import com.gun.common.utils.LotteryConstants;
import com.gun.common.utils.SqlSyntaxFactory;
import com.gun.common.utils.StringUtils;
/**
 * 
 * @author felixli
 *
 */
@Repository
public class LotteryReportDAOImpl  extends AbstractDAOImpl implements LotteryReportDAO{
	private static final Log log = LogFactory.getLog(LotteryReportDAOImpl.class);
	
	@SuppressWarnings("unchecked")
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

  /** (non-Javadoc)
   * @see com.cyber.lottery.dao.LotteryReportDAO#queryStockBalance(java.lang.String)
   */
  @Override
  public List<LotteryReportDTO> queryStockBalance(String periods) throws DataAccessException {
    List<LotteryReportDTO> results= null;
    SQLQuery sqlQuery = null;
    try {
      sqlQuery =  this.getSession().createSQLQuery("SELECT * FROM dbo.LOTTERY_NUMBER ln1 WHERE  ln1.LOTTERY_PERIODS=:periods");
      sqlQuery.setParameter(LotteryReportDTO.ATTRIBUTE.PERIODS.getValue(), periods);
      List list = sqlQuery.list(); 
      if(!CollectionUtils.isEmpty(list)){
        sqlQuery =  this.getSession().createSQLQuery(SqlSyntaxFactory.GET_LOTTERY_SALES_VOLUMES_BY_PERIODS1);
        log.debug(this.getClass().getName()+"[SQL] ---> queryStockBalance  SQL：" + SqlSyntaxFactory.GET_LOTTERY_SALES_VOLUMES_BY_PERIODS1);
      }else{
        sqlQuery =  this.getSession().createSQLQuery(SqlSyntaxFactory.GET_LOTTERY_SALES_VOLUMES_BY_PERIODS2);
        log.debug(this.getClass().getName()+"[SQL] ---> queryStockBalance  SQL：" + SqlSyntaxFactory.GET_LOTTERY_SALES_VOLUMES_BY_PERIODS2);
      }
      sqlQuery.setParameter(LotteryReportDTO.ATTRIBUTE.PERIODS.getValue(), periods);
      sqlQuery.setResultTransformer(Transformers.aliasToBean(LotteryReportDTO.class));
      results =sqlQuery.list(); 
    }catch (DataException e) {
      log.error(this.getClass().getName()+"queryStockBalance failed!!" + e );
      throw new DataAccessException(e);
    }
    return results;
  }

  /** (non-Javadoc)
   * @see com.cyber.lottery.dao.LotteryReportDAO#exportStockBalance(java.lang.String)
   */
  @Override
  public List<LotteryReportDTO> exportStockBalance(String periods) throws DataAccessException {
    List<LotteryReportDTO> results= new ArrayList<LotteryReportDTO>();
    LotteryReportDTO lotteryReportDTO= new LotteryReportDTO();
    SQLQuery sqlQuery = null;
    try {
        sqlQuery =  this.getSession().createSQLQuery(SqlSyntaxFactory.GET_CURRENT_SOLD1);
        log.debug(this.getClass().getName()+"[SQL] ---> exportStockBalance  SQL：" + SqlSyntaxFactory.GET_CURRENT_SOLD1);
        sqlQuery.setParameter(LotteryReportDTO.ATTRIBUTE.PERIODS.getValue(), periods);
        sqlQuery.setResultTransformer(Transformers.aliasToBean(LotteryReportDTO.class));
        lotteryReportDTO.setSoldList(sqlQuery.list());
        sqlQuery =  this.getSession().createSQLQuery(SqlSyntaxFactory.GET_CURRENT_UNSOLD1);
        log.debug(this.getClass().getName()+"[SQL] ---> exportStockBalance  SQL：" + SqlSyntaxFactory.GET_CURRENT_UNSOLD1);
        sqlQuery.setParameter(LotteryReportDTO.ATTRIBUTE.PERIODS.getValue(), periods);
        sqlQuery.setResultTransformer(Transformers.aliasToBean(LotteryReportDTO.class));
        lotteryReportDTO.setUnsoldList(sqlQuery.list());        
        results.add(lotteryReportDTO);
    }catch (DataException e) {
      log.error(this.getClass().getName()+"queryStockBalance failed!!" + e );
      throw new DataAccessException(e);
    }
    return results;
  }

  /** (non-Javadoc)
   * @see com.cyber.lottery.dao.LotteryReportDAO#querySaleTicketList(com.gun.common.pojo.PageInfo, java.lang.String)
   */
  @Override
  public List<LotteryReportDTO> querySaleTicketList(PageInfo pageInfo, String periods) throws DataAccessException {
    List<LotteryReportDTO> results = null;
    String sql = "";
    SQLQuery sqlQuery = null;
    try {
      sqlQuery =  this.getSession().createSQLQuery("SELECT * FROM dbo.LOTTERY_NUMBER ln1 WHERE  ln1.LOTTERY_PERIODS=:periods");
      sqlQuery.setParameter(LotteryReportDTO.ATTRIBUTE.PERIODS.getValue(), periods);
      List list = sqlQuery.list();
      if(!CollectionUtils.isEmpty(list)){
        sql = SqlSyntaxFactory.QUERY_SALE_TICKET_LIST1;
      }else{
        sql = SqlSyntaxFactory.QUERY_SALE_TICKET_LIST2;
      }
      Map<String,Object> params = new HashMap<String,Object>();
      params.put(LotteryReportDTO.ATTRIBUTE.PERIODS.getValue(), periods);
      log.debug(this.getClass().getName()+"[SQL] ---> getAllNearbyStores  SQL：" + sql);
      results = super.getQueryList(sql, params, Transformers.aliasToBean(LotteryReportDTO.class), pageInfo);
    }catch (DataException e) {
      log.error(this.getClass().getName()+"querySaleTicketList failed!!" + e );
      throw new DataAccessException(e);
    }
    return results;
  }

  /**
   * (non-Javadoc)
   * @see com.cyber.lottery.dao.LotteryReportDAO#queryMerchantSettlement(com.gun.common.pojo.PageInfo, com.gun.common.pojo.form.LotteryReportForm)
   */
  @Override
  public List<NearbyStoresDTO> queryMerchantSettlement(PageInfo pageInfo, LotteryReportForm lotteryReportForm)
		  throws DataAccessException {
	    List<NearbyStoresDTO> results = null;
	    String sqlQuery = null;
	    try {
	    	sqlQuery = SqlSyntaxFactory.MERCHANT_SETTLEMENT;
	    	StringBuffer newSqlQuery = new StringBuffer(sqlQuery);
	    	Map<String,Object> params = new HashMap<String, Object>();
	    	if(StringUtils.hasText(lotteryReportForm.getQueryDealerName())){
	    		newSqlQuery.append(" AND ns.DEALER_NAME like :queryDealerName");
	    		params.put("queryDealerName", LotteryConstants.MARK_PERCENT+lotteryReportForm.getQueryDealerName()+LotteryConstants.MARK_PERCENT);
	    	}
	    	if(StringUtils.hasText(lotteryReportForm.getQueryDealerCategory()) && !(LotteryConstants.ALL_STRING.equals(lotteryReportForm.getQueryDealerCategory()))){
	            params.put(NearbyStoresDTO.ATTRIBUTE.DEALER_CATEGORY.getValue(), lotteryReportForm.getQueryDealerCategory());
	            newSqlQuery.append(" AND ns.DEALER_CATEGORY=:dealerCategory"); 
	        }
	    	sqlQuery = newSqlQuery.toString();
	    	log.debug(this.getClass().getName()+"[SQL] ---> queryMerchantSettlement  SQL：" + sqlQuery);
	    	List list = super.getQueryList(sqlQuery, params, Transformers.aliasToBean(NearbyStoresDTO.class), pageInfo);
	    	if(!CollectionUtils.isEmpty(list)){
	    		results = (List<NearbyStoresDTO>)list;
			}
	    }catch (DataException e) {
	      log.error(this.getClass().getName()+"queryMerchantSettlement failed!!" + e );
	      throw new DataAccessException(e);
	    }
	    return results;
  }
	
}
