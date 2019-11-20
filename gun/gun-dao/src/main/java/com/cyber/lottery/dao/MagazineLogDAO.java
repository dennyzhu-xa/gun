package com.cyber.lottery.dao;

import java.util.List;

import com.gun.common.entity.MagazineLog;
import com.gun.common.entity.pojo.MagazineLogDTO;

public interface MagazineLogDAO extends AbstractDAO<MagazineLog> {

	public List<MagazineLogDTO> queryList(String equipId);
	
}
