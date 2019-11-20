package com.cyber.lottery.dao;

import java.util.List;

import com.gun.common.entity.SysUserInfo;
import com.gun.common.entity.pojo.SysUserInfoDTO;
import com.gun.common.exception.DataAccessException;

/**
 * Purpose: 用户维护DAO interface
 * @author Felixli
 * @since  JDK 1.7
 * @date   2017年6月1日
 * @MaintenancePersonnel Felixli
 */
public interface SysUserDAO extends AbstractDAO<SysUserInfo> {	
	
  /**
   * 
   * @param userName:用户名
   * @return SysUserInfoDTO:返回登陆者信息
   * @throws DataAccessException:出错是返回DataAccessException
   */
	public SysUserInfoDTO getSysUserByUserName(String userName) throws DataAccessException;	
	/**
   * 
   * @param userName:权限Id
   * @return SysUserInfoDTO:返回用户信息
   * @throws DataAccessException:出错是返回DataAccessException
   */
  public List<SysUserInfoDTO> getSysUserByUserRoleId(int roleId) throws DataAccessException;
}
