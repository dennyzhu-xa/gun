package com.cyber.lottery.dao;

import java.util.List;

import com.gun.common.entity.EquipmentLog;
import com.gun.common.entity.pojo.EquipmentLogDTO;

public interface EquipmentLogDAO extends AbstractDAO<EquipmentLog> {

	public List<EquipmentLogDTO> queryList(String equipId);
	
}
