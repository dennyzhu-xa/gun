package com.cyber.lottery.dao;

import java.util.List;

import com.gun.common.entity.UserInfo;
import com.gun.common.entity.pojo.UserInfoDTO;

public interface UserInfoDAO extends AbstractDAO<UserInfo>{

	public UserInfo selectByUserAccount(String userId);
	
	public List<UserInfoDTO> queryByCriterias(String userId,String userName);
	
}
