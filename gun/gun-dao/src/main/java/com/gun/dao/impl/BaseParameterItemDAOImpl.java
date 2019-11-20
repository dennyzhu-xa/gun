package com.gun.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.exception.DataException;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.cyber.lottery.dao.AbstractDAOImpl;
import com.cyber.lottery.dao.BaseParameterItemDAO;
import com.gun.common.entity.BaseParameterItemDef;
import com.gun.common.entity.pojo.BaseParameterItemDefDTO;
import com.gun.common.entity.pojo.BaseParameterItemDefIdDTO;
import com.gun.common.entity.pojo.InputWinninNoDTO;
import com.gun.common.entity.pojo.LotteryRuleDTO;
import com.gun.common.exception.DataAccessException;
import com.gun.common.pojo.PageInfo;
import com.gun.common.pojo.ParamItem;
import com.gun.common.pojo.form.BaseParameterItemForm;
import com.gun.common.utils.DateTimeUtils;
import com.gun.common.utils.LotteryConstants;
import com.gun.common.utils.LotteryMessageCode;
import com.gun.common.utils.SqlSyntaxFactory;
import com.gun.common.utils.StringUtils;


/**
 * @author felixli
 *
 */
@Repository
public class BaseParameterItemDAOImpl extends AbstractDAOImpl<BaseParameterItemDef> implements BaseParameterItemDAO {
  /**
   * 日志记录物件
   */
  private static final Log log = LogFactory.getLog(BaseParameterItemDAOImpl.class);
  
  /**
   * (non-Javadoc)
   * @see com.cyber.lottery.dao.BaseParameterItemDAO#getEffectiveDate()
   */
  public List<ParamItem> getEffectiveDate() throws DataAccessException {
  //查询结果集
    List<ParamItem> results = new ArrayList<ParamItem>();
    try{
      SQLQuery sqlQuery  = this.getSession().createSQLQuery(SqlSyntaxFactory.GET_EFFECTIVE_DATE);
      sqlQuery.setResultTransformer(Transformers.aliasToBean(ParamItem.class));
      results = sqlQuery.list();
    } catch (DataAccessException e) {
      log.error(this.getClass().getName() + ".getEffectiveDate failed!!!!!",e);
      throw new DataAccessException(LotteryMessageCode.DATA_ACCESS_FAILED, e);
    }
    return results;
  }
  
  /**
   * (non-Javadoc)
   * @see com.cyber.lottery.dao.BaseParameterItemDAO#getBaseParameterType()
   */
  public List<ParamItem> getBaseParameterType() throws DataAccessException {
  //查询结果集
    List<ParamItem> results = new ArrayList<ParamItem>();
    try{
      SQLQuery sqlQuery  = this.getSession().createSQLQuery(SqlSyntaxFactory.GET_BASE_PARAMETER_TYPE);
      sqlQuery.setResultTransformer(Transformers.aliasToBean(ParamItem.class));
      results = sqlQuery.list();
    } catch (DataAccessException e) {
      log.error(this.getClass().getName() + ".getBaseParameterType failed!!!!!",e);
      throw new DataAccessException(LotteryMessageCode.DATA_ACCESS_FAILED, e);
    }
    return results;
  }

  /** (non-Javadoc)
   * @see com.cyber.lottery.dao.BaseParameterItemDAO#queryParameterByEffectiveDate(java.lang.String)
   */
  @Override
  public List<BaseParameterItemDefDTO> queryParameterByEffectiveDate(PageInfo pageInfo,String bptdCode,String effectiveDate) throws DataAccessException {
  //查询结果集
    List<BaseParameterItemDefDTO> results = new ArrayList<BaseParameterItemDefDTO>();
    try{
      String sql = SqlSyntaxFactory.QUERY_BASE_PARAMETER_LIST;
      Map<String,Object> params = new HashMap<String, Object>();
      if(StringUtils.hasText(bptdCode)){
        params.put(BaseParameterItemForm.QUERY_BPTD_CODE, bptdCode);
      }else{
        params.put(BaseParameterItemForm.QUERY_BPTD_CODE, null);
      }
      if(StringUtils.hasText(effectiveDate)){
        params.put(BaseParameterItemForm.QUERY_EFFECTIVE_DATE, effectiveDate);
      }else{
        params.put(BaseParameterItemForm.QUERY_EFFECTIVE_DATE, null);
      }
      log.debug(this.getClass().getName()+"[SQL] ---> list  SQL：" + SqlSyntaxFactory.QUERY_BASE_PARAMETER_LIST);
      
      SQLQuery sqlQuery  = this.getSession().createSQLQuery(sql);
		log.debug(this.getClass().getName()+".getQueryList SQL======>"+sql);
		if(!CollectionUtils.isEmpty(params)){
			Set<String> keys =  params.keySet();
			for (String key : keys) {
				sqlQuery.setParameter(key, params.get(key));
				log.debug(this.getClass().getName()+".getQueryList "+key+":======>"+params.get(key));
			}
		}
		if (pageInfo != null) {
			SQLQuery countSqlQuery  = this.getSession().createSQLQuery(SqlSyntaxFactory.QUERY_BASE_PARAMETER_LIST_COUNT);
			if(!CollectionUtils.isEmpty(params)){
				Set<String> keys =  params.keySet();
				for (String key : keys) {
					countSqlQuery.setParameter(key, params.get(key));
					log.debug(this.getClass().getName()+".getQueryList "+key+":======>"+params.get(key));
				}
			}
			int totalCnt = (int) countSqlQuery.uniqueResult();
			pageInfo.setTotal(totalCnt);
			// 分頁資料撈取
			sqlQuery.setMaxResults(pageInfo.getSize());
			sqlQuery.setFirstResult(pageInfo.getPage() * pageInfo.getSize());
		}
		sqlQuery.setResultTransformer(Transformers.aliasToBean(BaseParameterItemDefDTO.class));
		List list = sqlQuery.list();
      if(!CollectionUtils.isEmpty(list)){
        results = (List<BaseParameterItemDefDTO>)list;
      }
    } catch (DataAccessException e) {
      log.error(this.getClass().getName() + ".queryParameterByEffectiveDate failed!!!!!",e);
      throw new DataAccessException(LotteryMessageCode.DATA_ACCESS_FAILED, e);
    }
    return results;
  }

  /** (non-Javadoc)
   * @see com.cyber.lottery.dao.BaseParameterItemDAO#getBaseParameterItemDefDTOs(java.lang.String, java.util.Date, java.lang.String[])
   */
  @Override
  public List<BaseParameterItemDefDTO> getBaseParameterItemDefDTOs(String bptdCode, Date initiateDate, boolean maxEffectiveDate,String... view) throws DataAccessException {
  //查询结果集
    List<BaseParameterItemDefDTO> results = new ArrayList<BaseParameterItemDefDTO>();
    try{
      if(initiateDate == null){
        initiateDate = DateTimeUtils.getCurrentDate();
      }
      String initiateDateString = DateTimeUtils.toString(initiateDate, DateTimeUtils.DT_FMT_YYYYMMDD_DASH);
      String sql;
      if(maxEffectiveDate){
        sql = SqlSyntaxFactory.GET_BASE_PARAMETER_ITEM_DEFS;
      }else{
        sql = SqlSyntaxFactory.GET_BASE_PARAMETER_BY_EFFECTIVE_DATE_AND_BPTDCODE;
      }
      SQLQuery sqlQuery  = this.getSession().createSQLQuery(sql);
      sqlQuery.setParameter("bptdCode", bptdCode);
      sqlQuery.setParameter("effectiveDate", initiateDateString);
      if(view!=null && view.length>0){
        if(LotteryConstants.YES.equals(view[0])){
          sqlQuery.setParameter("approvedFlag", LotteryConstants.NO);
        }else{
          sqlQuery.setParameter("approvedFlag", LotteryConstants.YES);
        }
      }else{
        if(maxEffectiveDate){
          sqlQuery.setParameter("approvedFlag", LotteryConstants.YES);
        }else{
          sqlQuery.setParameter("approvedFlag", null);
        }
      }
      sqlQuery.setResultTransformer(Transformers.aliasToBean(BaseParameterItemDefDTO.class));
      results = (List<BaseParameterItemDefDTO>)sqlQuery.list();
    } catch (DataAccessException e) {
      log.error(this.getClass().getName() + ".getBaseParameterItemDefDTOs failed!!!",e);
      throw new DataAccessException(LotteryMessageCode.DATA_ACCESS_FAILED, e);
    }
    return results;
  }

  /** (non-Javadoc)
   * @see com.cyber.lottery.dao.BaseParameterItemDAO#deleteBaseParameterItemDef(java.lang.String, java.util.Date)
   */
  @Override
  public int deleteBaseParameterItemDef(String bptdCode, String initiateDate) throws DataAccessException {
    int results = 0;
    try{
      String sql = SqlSyntaxFactory.DELETE_BASE_PARAMETER;
      SQLQuery sqlQuery  = this.getSession().createSQLQuery(sql);
      sqlQuery.setParameter("bptdCode", bptdCode);
      sqlQuery.setParameter("effectiveDate",initiateDate);
      results = sqlQuery.executeUpdate();
    } catch (DataAccessException e) {
      log.error(this.getClass().getName() + ".deleteBaseParameterItemDef failed!!!!!",e);
      throw new DataAccessException(LotteryMessageCode.DATA_ACCESS_FAILED, e);
    }
    return results;
  }

  /** (non-Javadoc)
   * @see com.cyber.lottery.dao.BaseParameterItemDAO#approvalBaseParameterItemDef(java.lang.String, java.lang.String)
   */
  @Override
  public int approvalBaseParameterItemDef(String bptdCode, String initiateDate) throws DataAccessException {
    int results = 0;
    try{
      String sqlUpdate = SqlSyntaxFactory.UPDATE_BASE_PARAMETER;
      SQLQuery sqlQueryUpdate  = this.getSession().createSQLQuery(sqlUpdate);
      sqlQueryUpdate.setParameter("bptdCode", bptdCode);
      sqlQueryUpdate.setParameter("effectiveDate",initiateDate);
      sqlQueryUpdate.setParameter("approvedFlag", LotteryConstants.YES);
      sqlQueryUpdate.executeUpdate();
      
      String sql = SqlSyntaxFactory.APPROVED_BASE_PARAMETER;
      SQLQuery sqlQuery  = this.getSession().createSQLQuery(sql);
      sqlQuery.setParameter("bptdCode", bptdCode);
      sqlQuery.setParameter("effectiveDate",initiateDate);
      sqlQuery.setParameter("approvedFlag", LotteryConstants.YES);
      results = sqlQuery.executeUpdate();
      
    } catch (DataAccessException e) {
      log.error(this.getClass().getName() + ".approvalBaseParameterItemDef failed!!!!!",e);
      throw new DataAccessException(LotteryMessageCode.DATA_ACCESS_FAILED, e);
    }
    return results;
  }

    /**
     * 
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public BaseParameterItemDefIdDTO showCategoryImage(LotteryRuleDTO lotteryRuleDTO) throws DataAccessException {
		BaseParameterItemDefIdDTO baseParameterItemDefIdDTO = new BaseParameterItemDefIdDTO();
		String sqlQuery = null;
		try {
			sqlQuery = SqlSyntaxFactory.FIND_CATEGORY_IMAGE;
			Map<String,Object> params = new HashMap<String, Object>();
			StringBuffer newSqlQuery = new StringBuffer(sqlQuery);
			Date currentDate = DateTimeUtils.parseDate(DateTimeUtils.format(DateTimeUtils.getCurrentTimestamp(), DateTimeUtils.DT_FMT_YYYYMMDD_DASH), DateTimeUtils.DT_FMT_YYYYMMDD_DASH);
			if (StringUtils.hasText(lotteryRuleDTO.getLotteryCategory())) {
				params.put("lotteryCategory", lotteryRuleDTO.getLotteryCategory());
				newSqlQuery.append(" AND bpid.ITEM_VALUE = :lotteryCategory");
			}
			if (lotteryRuleDTO.getCreatedDate() != null) {
				currentDate = DateTimeUtils.parseDate(lotteryRuleDTO.getCreatedDate(), DateTimeUtils.DT_FMT_YYYYMMDD_DASH);
			}
			params.put("createDate", currentDate);
			newSqlQuery.append(" AND bpid.EFFECTIVE_DATE = (SELECT MAX(bp.EFFECTIVE_DATE) FROM BASE_PARAMETER_ITEM_DEF bp LEFT JOIN dbo.BASE_PARAMETER_TYPE_DEF bt ON bt.BPTD_CODE = bp.BPTD_CODE "
					+ "WHERE :createDate >= bp.EFFECTIVE_DATE AND bp.APPROVED_FLAG = 'Y' AND bt.BPTD_CODE = 'LOTTERY_CATEGORY' )");
			sqlQuery = newSqlQuery.toString();
			log.debug(this.getClass().getName()+"[SQL] ---> showCategoryImage  SQL：" + sqlQuery);
			List list = super.getQueryList(sqlQuery, params, Transformers.aliasToBean(BaseParameterItemDefIdDTO.class), null);
			if(!CollectionUtils.isEmpty(list)){
				baseParameterItemDefIdDTO = ((List<BaseParameterItemDefIdDTO>)list).get(0);
			}
		}catch (DataException e) {
			log.error(this.getClass().getName()+"getCurrentRound() failed!!" + e );
			throw new DataAccessException(e);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return baseParameterItemDefIdDTO;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public BaseParameterItemDefDTO queryByWinninName(InputWinninNoDTO inputWinninNoDTO)
			throws DataAccessException {
 		BaseParameterItemDefDTO baseParameterItemDefDTO = new BaseParameterItemDefDTO();
		String sqlQuery = null;
		try {
			sqlQuery = SqlSyntaxFactory.QUERY_BY_WINNIN_NAME;
			Map<String,Object> params = new HashMap<String, Object>();
			StringBuffer newSqlQuery = new StringBuffer(sqlQuery);
			Date currentDate = DateTimeUtils.parseDate(DateTimeUtils.format(DateTimeUtils.getCurrentTimestamp(), DateTimeUtils.DT_FMT_YYYYMMDD_DASH), DateTimeUtils.DT_FMT_YYYYMMDD_DASH);
//			Date currentDate = DateTimeUtils.getCurrentDate();
			if (StringUtils.hasText(inputWinninNoDTO.getWinninName())) {
				params.put("itemValue", inputWinninNoDTO.getWinninName());
				newSqlQuery.append(" AND bpid.ITEM_VALUE = :itemValue");
			}
			if (inputWinninNoDTO.getCreatedDate() != null) {
				currentDate = DateTimeUtils.parseDate(DateTimeUtils.format(inputWinninNoDTO.getCreatedDate(), DateTimeUtils.DT_FMT_YYYYMMDD_DASH), DateTimeUtils.DT_FMT_YYYYMMDD_DASH);
			}
			params.put("createDate", currentDate);
			newSqlQuery.append(" AND bpid.EFFECTIVE_DATE = (SELECT MAX(bp.EFFECTIVE_DATE) FROM BASE_PARAMETER_ITEM_DEF bp LEFT JOIN dbo.BASE_PARAMETER_TYPE_DEF bt ON bt.BPTD_CODE = bp.BPTD_CODE "
					+ "WHERE :createDate >= bp.EFFECTIVE_DATE and bp.APPROVED_FLAG = 'Y' AND bt.BPTD_CODE = 'LOTTERY_WINNIN_NAME' )");
			sqlQuery = newSqlQuery.toString();
			log.debug(this.getClass().getName()+"[SQL] ---> showCategoryImage  SQL：" + sqlQuery);
			List list = super.getQueryList(sqlQuery, params, Transformers.aliasToBean(BaseParameterItemDefDTO.class), null);
			if(!CollectionUtils.isEmpty(list)){
				baseParameterItemDefDTO = ((List<BaseParameterItemDefDTO>)list).get(0);
			}
		}catch (DataException e) {
			log.error(this.getClass().getName()+"getCurrentRound() failed!!" + e );
			throw new DataAccessException(e);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return baseParameterItemDefDTO;
	}
	
	@Override
	public BaseParameterItemDefDTO findCategoryByItemValue(String itemValue, Date currentDate){
		return findByBptdCodeAndItemValue(LotteryConstants.LOTTERY_CATEGORY, itemValue, currentDate);
	}
	
	@Override
	public BaseParameterItemDefDTO findSubCategoryByItemValue(String itemValue, Date currentDate) throws DataAccessException {
		return findByBptdCodeAndItemValue(LotteryConstants.LOTTERY_SUB_CATEGORY, itemValue, currentDate);
	}
	
	/**
	 * Purpose: 根据bptdCode、itemValue和当前时间确定一条参数记录
	 * @author KevinShen
	 * @param bptdCode
	 * @param itemValue
	 * @param currentDate
	 * @throws DataAccessException
	 * @return BaseParameterItemDefDTO
	 */
	public BaseParameterItemDefDTO findByBptdCodeAndItemValue(String bptdCode, String itemValue, Date currentDate) throws DataAccessException {
		BaseParameterItemDefDTO baseParameterItemDefDTO = null;
		if (!StringUtils.hasText(bptdCode)){
			log.error(this.getClass().getName()+"findByBptdCodeAndItemValue() failed!! bptdCode不能为空");
			throw new DataAccessException("bptdCode不能为空");
		}
		if (!StringUtils.hasText(itemValue)){
			log.error(this.getClass().getName()+"findByBptdCodeAndItemValue() failed!! itemValue不能为空");
			throw new DataAccessException("itemValue不能为空");
		}
		if (currentDate == null){
			currentDate = DateTimeUtils.getCurrentDate();
		}
		try {
			String sql = "SELECT bpid.BPID_ID AS bpidId,bpid.BPTD_CODE AS bptdCode,bpid.EFFECTIVE_DATE AS effectiveDate,bpid.ITEM_NAME AS itemName,bpid.ITEM_VALUE AS itemValue,"+
		            "bpid.REFERENCE_CODE AS referenceCode,bpid.ITEM_DESC AS itemDesc,bpid.ITEM_ORDER AS itemOrder,"+
		            "bpid.ITEM_DEPTH AS itemDepth, bpid.TEXT_FIELD1 AS textField1,bpid.TEXT_FIELD2 AS textField2,"+
		            "bpid.TEXT_FIELD3 AS textField3,bpid.TEXT_FIELD4 AS textField4,bpid.TEXT_FIELD5 AS textField5,"+
		            "bpid.NUMBER_FIELD1 AS numberField1,bpid.NUMBER_FIELD2 AS numberField2,bpid.IMG_FLAG AS imgFlag,"+
		            "bpid.PARENT_BPID_ID AS parentBpidId,bpid.UPDATED_BY_ID AS updatedById, bpid.UPDATED_BY_NAME AS updatedByName,bpid.UPDATED_DATE AS updatedDate "+
		            " FROM BASE_PARAMETER_ITEM_DEF bpid LEFT JOIN  BASE_PARAMETER_TYPE_DEF bptd ON bpid.BPTD_CODE = bptd.BPTD_CODE WHERE bptd.BPTD_CODE = :bptdCode AND bpid.ITEM_VALUE = :itemValue"
		            + " AND bpid.EFFECTIVE_DATE = (SELECT MAX(bp.EFFECTIVE_DATE) FROM BASE_PARAMETER_ITEM_DEF bp LEFT JOIN dbo.BASE_PARAMETER_TYPE_DEF bt ON bt.BPTD_CODE = bp.BPTD_CODE "
					+ "WHERE :currentDate >= bp.EFFECTIVE_DATE and bp.APPROVED_FLAG = 'Y' AND bt.BPTD_CODE = :bptdCode )";
			SQLQuery sqlQuery  = this.getSession().createSQLQuery(sql);
			sqlQuery.setParameter("bptdCode", bptdCode);
			sqlQuery.setParameter("itemValue", itemValue);
			sqlQuery.setParameter("currentDate", currentDate);
			sqlQuery.setResultTransformer(Transformers.aliasToBean(BaseParameterItemDefDTO.class));
			baseParameterItemDefDTO = (BaseParameterItemDefDTO) sqlQuery.uniqueResult();	
		}catch (DataException e) {
			log.error(this.getClass().getName()+"findByBptdCodeAndItemValue() failed!!" + e );
			throw new DataAccessException(e);
		}
		return baseParameterItemDefDTO;
	}

}
