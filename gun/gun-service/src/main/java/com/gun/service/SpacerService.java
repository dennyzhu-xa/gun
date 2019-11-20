package com.gun.service;

import java.util.List;

import com.gun.common.entity.pojo.SpacerDTO;
import com.gun.common.entity.pojo.SpacerLogDTO;


public interface SpacerService extends LotteryService {

	public List<String> listEquipModel();
	
	public void saveEquipModel(SpacerDTO equipmentDTO);
	
	public List<SpacerDTO> queryList(String equipName,String equipModel);
	
	public SpacerDTO getEquip (String id);
	
	public void delEquip(String id);
	
	public void saveEquipLog(SpacerLogDTO equipLogDTO);
	
	public List<SpacerLogDTO> queryList(String equipId);
}
