package com.gun.service;

import java.util.List;

import com.gun.common.entity.pojo.RoleAuthorityDTO;
import com.gun.common.entity.pojo.RoleDefDTO;
import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.Model;
import com.gun.common.pojo.ParamItem;
import com.gun.common.pojo.form.SystemForm;

/**
 * 
 * @author felixli
 *
 */
@SuppressWarnings("rawtypes")
public interface RoleAuthorityService extends LotteryService {

	public List<RoleAuthorityDTO> queryByProerties(Integer propValue) throws ServiceException;
	
	public Model queryUserRole(Integer roleId) throws ServiceException;
	
	public Model deleteByRoleId(Integer role) throws ServiceException;
	
	public Model saveOrUpdate(List<RoleAuthorityDTO> roleAuthorityList) throws ServiceException;
	
	public List<ParamItem> getRoleAll() throws ServiceException;
	
	public Model list(SystemForm systemForm) throws ServiceException;
	
	public Model saveRole(RoleDefDTO roleDefDTO) throws ServiceException;
	
	public Model deleteRoleDefById(Integer roleId) throws ServiceException;
}
