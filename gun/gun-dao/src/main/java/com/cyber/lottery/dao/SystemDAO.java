package com.cyber.lottery.dao;

import java.util.List;

import com.gun.common.entity.SysUserInfo;
import com.gun.common.entity.pojo.SysUserInfoDTO;
import com.gun.common.exception.DataAccessException;
import com.gun.common.pojo.PageInfo;
import com.gun.common.pojo.form.SysUserInfoForm;

/**
 * 
 * @author felixli
 *
 */
public interface SystemDAO extends AbstractDAO<SysUserInfo>{

	public List<SysUserInfoDTO> list(PageInfo pageInfo,SysUserInfoForm sysUserInfoForm) throws DataAccessException;
}
