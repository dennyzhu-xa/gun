package com.gun.service;

import java.util.List;

import com.gun.common.entity.pojo.PerforatingProjectileDTO;
import com.gun.common.entity.pojo.PerforatingProjectileLogDTO;


public interface PerforatingProjectileService extends LotteryService {

	public List<String> listEquipModel();
	
	public void saveEquipModel(PerforatingProjectileDTO equipmentDTO);
	
	public List<PerforatingProjectileDTO> queryList(String equipName,String equipModel);
	
	public PerforatingProjectileDTO getEquip (String id);
	
	public void delEquip(String id);
	
	public void saveEquipLog(PerforatingProjectileLogDTO equipLogDTO);
	
	public List<PerforatingProjectileLogDTO> queryList(String equipId);
}
