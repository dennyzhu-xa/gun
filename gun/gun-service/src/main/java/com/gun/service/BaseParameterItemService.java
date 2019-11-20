package com.gun.service;

import java.util.Date;
import java.util.List;

import com.gun.common.entity.pojo.BaseParameterItemDefDTO;
import com.gun.common.entity.pojo.BaseParameterItemDefIdDTO;
import com.gun.common.exception.DataAccessException;
import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.Model;
import com.gun.common.pojo.form.BaseParameterItemForm;

/**
 * @author felixli
 *
 */
@SuppressWarnings("rawtypes")
public interface BaseParameterItemService extends LotteryService{

  public Model initEdit(BaseParameterItemForm command) throws ServiceException;
  
  public Model query(BaseParameterItemForm command) throws ServiceException;
  
  public List<BaseParameterItemDefDTO> getBaseParameterItemDefDTOs(BaseParameterItemForm command) throws ServiceException;
  
  public Model save(BaseParameterItemForm command) throws ServiceException;
  
  public Model delete(BaseParameterItemForm command) throws ServiceException;
  
  public boolean checkBaseParameterItemDef(String bptdCode, String effectiveDate)throws ServiceException;
  
  public byte[] loadCategoryImg(BaseParameterItemDefIdDTO baseParameterItemDefIdDTO)throws DataAccessException;
}
