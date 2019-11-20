package com.cyber.lottery.dao;

import java.util.List;

import com.gun.common.entity.WellLevel;
import com.gun.common.entity.pojo.WellLevelDTO;

public interface WellLevelDAO extends AbstractDAO<WellLevel> {

	public void deleteByWellId(Integer wellId);
	
	public List<WellLevelDTO> listByWellId(Integer wellId);
}
