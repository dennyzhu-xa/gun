package com.gun.service;

import java.io.Serializable;

import com.gun.common.entity.pojo.SysUserInfoDTO;
import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.Model;
import com.gun.common.pojo.form.SysUserInfoForm;

/**
 * 
 * @author felixli
 *
 */
@SuppressWarnings("rawtypes")
public interface SystemService extends LotteryService{

	public Model query(SysUserInfoForm systemForm) throws ServiceException;
	
	public Long getcount() throws ServiceException;
	
	public boolean checkUserByName(String userName) throws ServiceException;
	
	public Model saveOrUpdate(SysUserInfoDTO sysUserDTO) throws ServiceException;

	public Model deleteByPK(Serializable... id) throws ServiceException; 
}
