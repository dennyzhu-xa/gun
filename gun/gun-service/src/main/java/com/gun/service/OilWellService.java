package com.gun.service;

import java.util.List;

import org.apache.axis2.databinding.types.soapencoding.DateTime;

import com.gun.common.entity.pojo.ArrGunFormDTO;

public interface OilWellService extends LotteryService {

	public void saveOilWell(ArrGunFormDTO formDTO);
	
	public ArrGunFormDTO getArrGunForm(String wellNo,DateTime updateTime);
	
	public List<ArrGunFormDTO> listOilWellDTOByUser(String userId);
	
	public ArrGunFormDTO getArrGunFormById(String id);
}
