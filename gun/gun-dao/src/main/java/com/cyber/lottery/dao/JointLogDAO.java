package com.cyber.lottery.dao;

import java.util.List;

import com.gun.common.entity.JointLog;
import com.gun.common.entity.pojo.JointLogDTO;

public interface JointLogDAO extends AbstractDAO<JointLog> {

	public List<JointLogDTO> queryList(String equipId);
	
}
