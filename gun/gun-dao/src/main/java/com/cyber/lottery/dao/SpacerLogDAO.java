package com.cyber.lottery.dao;

import java.util.List;

import com.gun.common.entity.SpacerLog;
import com.gun.common.entity.pojo.SpacerLogDTO;

public interface SpacerLogDAO extends AbstractDAO<SpacerLog> {

	public List<SpacerLogDTO> queryList(String equipId);
	
}
