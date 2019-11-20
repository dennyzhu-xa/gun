package com.cyber.lottery.dao;

import java.util.List;

import com.gun.common.entity.Equipment;
import com.gun.common.entity.pojo.EquipmentDTO;

public interface EquipmentDAO extends AbstractDAO<Equipment> {

	public List<String> listEquipModel();
	
	public List<EquipmentDTO> queryList(String equipName,String equipModel);
	
	public List<EquipmentDTO> queryByNameArray(String names);
}
