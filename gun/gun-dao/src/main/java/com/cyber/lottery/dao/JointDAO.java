package com.cyber.lottery.dao;

import java.util.List;

import com.gun.common.entity.Joint;
import com.gun.common.entity.pojo.JointDTO;

public interface JointDAO extends AbstractDAO<Joint> {

	public List<JointDTO> queryList(String equipName);
	
	public JointDTO queryByName(String equipName);
}
