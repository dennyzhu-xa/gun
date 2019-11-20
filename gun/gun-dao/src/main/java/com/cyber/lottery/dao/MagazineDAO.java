package com.cyber.lottery.dao;

import java.util.List;

import com.gun.common.entity.Magazine;
import com.gun.common.entity.pojo.MagazineDTO;

public interface MagazineDAO extends AbstractDAO<Magazine> {

	public List<String> listEquipModel();
	
	public List<MagazineDTO> queryList(String equipName,String equipModel);
	
}
