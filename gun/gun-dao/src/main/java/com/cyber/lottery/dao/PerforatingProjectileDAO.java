package com.cyber.lottery.dao;

import java.util.List;

import com.gun.common.entity.PerforatingProjectile;
import com.gun.common.entity.pojo.PerforatingProjectileDTO;

public interface PerforatingProjectileDAO extends AbstractDAO<PerforatingProjectile> {

	public List<String> listEquipModel();
	
	public List<PerforatingProjectileDTO> queryList(String equipName,String equipModel);
	
}
