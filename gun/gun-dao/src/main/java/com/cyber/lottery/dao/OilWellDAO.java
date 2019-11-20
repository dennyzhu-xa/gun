package com.cyber.lottery.dao;

import java.util.List;

import org.apache.axis2.databinding.types.soapencoding.DateTime;

import com.gun.common.entity.OilWell;
import com.gun.common.entity.pojo.OilWellDTO;

public interface OilWellDAO extends AbstractDAO<OilWell> {

	public OilWellDTO getOilWellDTO(String wellNo,DateTime updateTime);
	
	public List<OilWellDTO> listOilWellDTO(String user);
}
