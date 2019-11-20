package com.cyber.lottery.dao;

import java.util.Date;
import java.util.List;

import com.gun.common.entity.BaseParameterItemDef;
import com.gun.common.entity.pojo.BaseParameterItemDefDTO;
import com.gun.common.entity.pojo.BaseParameterItemDefIdDTO;
import com.gun.common.entity.pojo.InputWinninNoDTO;
import com.gun.common.entity.pojo.LotteryRuleDTO;
import com.gun.common.exception.DataAccessException;
import com.gun.common.pojo.PageInfo;
import com.gun.common.pojo.ParamItem;

/**
 * @author felixli
 *
 */
public interface BaseParameterItemDAO extends AbstractDAO<BaseParameterItemDef>{
  
  
  /**
   * Purpose:获取生效日期
   * @author felixli
   * @return List<ParamItem>
   * @throws DataAccessException
   */
  public List<ParamItem> getEffectiveDate()throws DataAccessException;
  /**
   * Purpose:按生效日期查詢系統參數
   * @author felixli
   * @param effectiveDate
   * @return List<BaseParameterItemDefDTO>
   * @throws DataAccessException
   */
  public List<BaseParameterItemDefDTO> queryParameterByEffectiveDate(PageInfo pageInfo,String bptdCode,String effectiveDate)throws DataAccessException;
  /**
   * Purpose:获取所有參數類型
   * @author felixli
   * @return List<ParamItem>
   * @throws DataAccessException
   */
  public List<ParamItem> getBaseParameterType()throws DataAccessException;
  
  /**
   * Purpose:获取參數
   * @author felixli
   * @return List<BaseParameterItemDefDTO>
   * @throws DataAccessException
   */
  public List<BaseParameterItemDefDTO> getBaseParameterItemDefDTOs(String bptdCode, Date initiateDate ,boolean maxEffectiveDate,String... view)throws DataAccessException;
  
  /**
   * Purpose:删除參數
   * @author felixli
   * @return int
   * @throws DataAccessException
   */
  public int deleteBaseParameterItemDef(String bptdCode, String initiateDate ) throws DataAccessException;
  /**
   * Purpose:複核參數
   * @author felixli
   * @return int
   * @throws DataAccessException
   */
  public int approvalBaseParameterItemDef(String bptdCode, String initiateDate ) throws DataAccessException;
  
  /**
   * Purpose:根据规则的category查询出对应图片	
   * @author felixli
   * @return int
   * @throws DataAccessException
   */
  public BaseParameterItemDefIdDTO showCategoryImage(LotteryRuleDTO lotteryRuleDTO) throws DataAccessException;
  
  public BaseParameterItemDefDTO queryByWinninName(InputWinninNoDTO inputWinninNoDTO) throws DataAccessException;
  
  /**
   * Purpose: 根据itemValue查找Category的当前生效记录
   * @author KevinShen
   * @param itemValue
   * @param currentDate
   * @throws DataAccessException
   * @return BaseParameterItemDefDTO
   */
  public BaseParameterItemDefDTO findCategoryByItemValue(String itemValue, Date currentDate) throws DataAccessException;
  public BaseParameterItemDefDTO findSubCategoryByItemValue(String itemValue, Date object) throws DataAccessException;
  
}
