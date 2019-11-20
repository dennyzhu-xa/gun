package com.gun.service;

import java.util.List;

import com.gun.common.entity.pojo.EquipmentLogDTO;
import com.gun.common.entity.pojo.MagazineDTO;
import com.gun.common.entity.pojo.MagazineLogDTO;

public interface MagazineService extends LotteryService {

	public List<String> listEquipModel();
	
	public void saveEquipModel(MagazineDTO equipmentDTO);
	
	public List<MagazineDTO> queryList(String equipName,String equipModel);
	
	public MagazineDTO getEquip (String id);
	
	public void delEquip(String id);
	
	public void saveEquipLog(MagazineLogDTO equipLogDTO);
	
	public List<MagazineLogDTO> queryList(String equipId);
}
