package com.cyber.lottery.dao;

import java.util.List;

import com.gun.common.entity.PerforatingProjectileLog;
import com.gun.common.entity.pojo.PerforatingProjectileLogDTO;

public interface PerforatingProjectileLogDAO extends AbstractDAO<PerforatingProjectileLog> {

	public List<PerforatingProjectileLogDTO> queryList(String equipId);
	
}
