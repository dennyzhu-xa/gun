package com.gun.service;

import java.io.Serializable;
import java.util.List;

import com.gun.common.entity.pojo.FunctionTypeDTO;
import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.Model;
import com.gun.common.pojo.ParamItem;
import com.gun.common.pojo.form.FunctionTypeForm;

/**
 * @author felixli
 *
 */
@SuppressWarnings("rawtypes")
public interface FunctionTypeService extends LotteryService{

  /**
   * Purpose:获取全部Function
   * @author felixli
   * @throws ServiceException
   */
  public Model list(FunctionTypeForm functionTypeForm) throws ServiceException;
  /**
   * Purpose:获取全部父Function
   * @author felixli
   * @throws ServiceException
   */
  public List<ParamItem> queryParentFunctionList() throws ServiceException;
  /**
   * Purpose:保存Function
   * @author felixli
   * @throws ServiceException
   */
  public Model saveOrUpdate(FunctionTypeDTO functionTypeDTO) throws ServiceException;
  /**
   * Purpose:保存Function
   * @author felixli
   * @throws ServiceException
   */
  public boolean checkFunction(String functionCode) throws ServiceException;
  /**
   * Purpose:根据父Id,获取Function
   * @author felixli
   * @throws ServiceException
   */
  public List<FunctionTypeDTO> queryFunctionByParentId(String parentFunctionId) throws ServiceException;
  /**
   * Purpose:删除Function
   * @author felixli
   * @throws ServiceException
   */
  public Model deleteByPK(Serializable... id) throws ServiceException;
  
}
