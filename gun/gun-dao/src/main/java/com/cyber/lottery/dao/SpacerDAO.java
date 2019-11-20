package com.cyber.lottery.dao;

import java.util.List;

import com.gun.common.entity.Spacer;
import com.gun.common.entity.pojo.SpacerDTO;

public interface SpacerDAO extends AbstractDAO<Spacer> {

	public List<String> listEquipModel();
	
	public List<SpacerDTO> queryList(String equipName,String equipModel);
	
}
