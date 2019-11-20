package com.cyber.lottery.dao;

import java.util.List;

import com.gun.common.entity.RoleAuthority;
import com.gun.common.entity.pojo.RoleAuthorityDTO;
import com.gun.common.exception.DataAccessException;
import com.gun.common.pojo.ParamItem;

/**
 * 
 * @author felixli
 *
 */
public interface RoleAuthorityDAO extends AbstractDAO<RoleAuthority>{

	public List<RoleAuthorityDTO> queryRoleAuthorityByRole(Integer roleId) throws DataAccessException;
	
	public void deleteByRoleId(Integer roleId) throws DataAccessException;
	
}
