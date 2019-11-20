package com.gun.service;

import java.util.List;

import com.gun.common.entity.pojo.EquipmentDTO;
import com.gun.common.entity.pojo.EquipmentLogDTO;

public interface EquipmentService extends LotteryService {

	public List<String> listEquipModel();
	
	public void saveEquipModel(EquipmentDTO equipmentDTO);
	
	public List<EquipmentDTO> queryList(String equipName,String equipModel);
	
	public EquipmentDTO getEquip (String id);
	
	public void delEquip(String id);
	
	public void saveEquipLog(EquipmentLogDTO equipLogDTO);
	
	public List<EquipmentLogDTO> queryList(String equipId);
	
	public List<EquipmentDTO> listEquipByModel(String model);
	
	public List<EquipmentDTO> queryByNameArray(String names);
}
