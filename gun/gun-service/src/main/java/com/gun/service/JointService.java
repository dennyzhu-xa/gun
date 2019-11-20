package com.gun.service;

import java.util.List;

import com.gun.common.entity.pojo.JointDTO;
import com.gun.common.entity.pojo.JointLogDTO;

public interface JointService extends LotteryService {

	public void saveJointLog(JointLogDTO equipLogDTO);
	public List<JointLogDTO> queryList(String equipId);
	public JointDTO getEquip (String id);
	public void saveEquipModel(JointDTO jointDTO);
	public void delEquip(String id);
	public List<JointDTO> queryJointList (String jointName);
	public JointDTO queryByName(String equipName);
}
