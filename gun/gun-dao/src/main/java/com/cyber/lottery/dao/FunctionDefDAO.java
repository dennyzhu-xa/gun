package com.cyber.lottery.dao;

import java.util.List;

import com.gun.common.entity.FunctionType;
import com.gun.common.entity.pojo.FunctionTypeDTO;
import com.gun.common.exception.DataAccessException;
import com.gun.common.pojo.PageInfo;
import com.gun.common.pojo.ParamItem;

/**
 * @author felixli
 *
 */
public interface FunctionDefDAO extends AbstractDAO<FunctionType>{

	/**
	 * Purpose:functionTree List
	 * @author felixli
	 * @throws DataAccessException：出错时抛出DataAccessException
	 * @return List<FunctionType>
	 */
	public List<FunctionType> queryFunctionsList(Integer roleId) throws DataAccessException;
	/**
   * Purpose:获取父functionTree List
   * @author felixli
   * @throws DataAccessException：出错时抛出DataAccessException
   * @return List<ParamItem>
   */
  public List<ParamItem> queryParentFunctionList() throws DataAccessException;
	/**
   * Purpose:functionTree List
   * @author felixli
   * @param pageInfo:分页元件
   * @throws DataAccessException：出错时抛出DataAccessException
   * @return List<FunctionTypeDTO>
   */
  public List<FunctionTypeDTO> list(PageInfo pageInfo) throws DataAccessException;
  /**
   * Purpose:functionTree List
   * @author felixli
   * @throws DataAccessException：出错时抛出DataAccessException
   * @return List<FunctionType>
   */
  public List<FunctionTypeDTO> queryFunctionsByParentId(String parentFunctionId) throws DataAccessException;
}
