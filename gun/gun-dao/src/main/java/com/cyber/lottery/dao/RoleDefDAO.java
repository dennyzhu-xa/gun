package com.cyber.lottery.dao;

import java.util.List;

import com.gun.common.entity.RoleDef;
import com.gun.common.exception.DataAccessException;
import com.gun.common.pojo.PageInfo;
import com.gun.common.pojo.ParamItem;

/**
 * 
 * @author felixli
 *
 */
public interface RoleDefDAO extends AbstractDAO<RoleDef>{
  
  public List<ParamItem> list(PageInfo pageInfo) throws DataAccessException;
  
  public List<ParamItem> getRoleList() throws DataAccessException;
  
  public void deleteByRoleId(Integer role) throws DataAccessException;
  
  public boolean getRoleDefByRoleName(String roleName) throws DataAccessException;
  
}
