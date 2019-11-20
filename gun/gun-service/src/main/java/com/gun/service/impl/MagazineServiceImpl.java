package com.gun.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.runtime.parser.node.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyber.lottery.dao.MagazineDAO;
import com.cyber.lottery.dao.MagazineLogDAO;
import com.gun.common.entity.Magazine;
import com.gun.common.entity.MagazineLog;
import com.gun.common.entity.pojo.MagazineDTO;
import com.gun.common.entity.pojo.MagazineLogDTO;
import com.gun.common.exception.ConvertException;
import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.Model;
import com.gun.common.utils.DateTimeUtils;
import com.gun.common.utils.SimpleDtoDmoTransformer;
import com.gun.service.MagazineService;
@Service
public class MagazineServiceImpl implements MagazineService {

	private SimpleDtoDmoTransformer transformer = new SimpleDtoDmoTransformer();
	private static final Log log = LogFactory.getLog(MagazineServiceImpl.class);
	@Autowired
	private MagazineDAO euqipmentDAO;
	
	@Autowired
	private MagazineLogDAO equipmentLogDAO;
	
	public List<String> listEquipModel(){
		List<String> result = this.euqipmentDAO.listEquipModel();
		return result;
	}

	public void delEquip(String id){
		if(StringUtils.isEmpty(id)){
			return;
		}
		Magazine equip = this.euqipmentDAO.findById(Magazine.class, Integer.valueOf(id));
		if(equip != null){
			equip.setDeleteFlag('Y');
		}
		this.euqipmentDAO.saveOrUpdate(equip);
	}
	
	@Override
	public Model init(Object request) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MagazineDTO> queryList(String equipName,String equipModel){
		List<MagazineDTO> result = this.euqipmentDAO.queryList(equipName, equipModel);
		return result;
	}
	
	public void saveEquipLog(MagazineLogDTO equipLogDTO){
		if(equipLogDTO == null){
			log.error(this.getClass().getName() + "equipLogDTO is null or equipLogDTO.getEquipmentId() is null " + equipLogDTO);
			return;
		}
		Magazine eq = this.euqipmentDAO.findById(Magazine.class, Integer.valueOf(equipLogDTO.getMagazineId()));
		int oldQuanlity = eq == null ? 0 : eq.getTotalQuantity();
		MagazineLog equipLog = new MagazineLog();
		try {
			equipLog = (MagazineLog) transformer.transform(equipLogDTO, equipLog);
			equipLog.setOldQuantity(oldQuanlity);
			equipLog.setModifyDate(DateTimeUtils.getCurrentTimestamp());
			equipLog.setModifyId(null);
			equipLog.setModifyName(null);
			
		} catch (ConvertException e) {
			e.printStackTrace();
			log.error(this.getClass().getName() + "saveEquipLog:",e);
		}
		this.equipmentLogDAO.save(equipLog);

		// 更新主表资料
		char logType = equipLogDTO.getLogType();
		if(logType == 'I'){
			eq.setTotalQuantity((int) MathUtils.add(oldQuanlity, equipLog.getUpdateQuantity())); 
		} else if(logType == 'O'){
			int v = (int) MathUtils.subtract(oldQuanlity, equipLog.getUpdateQuantity());
			if(v < 0) v = 0;
			eq.setTotalQuantity(v);
		}
		
	}
	
	public List<MagazineLogDTO> queryList(String equipId){
		List<MagazineLogDTO> equipmentLogDTOList = this.equipmentLogDAO.queryList(equipId);
		return equipmentLogDTOList;
	}
	
	public MagazineDTO getEquip (String id){
		if(StringUtils.isEmpty(id)){
			return null;
		}
		Magazine equip = this.euqipmentDAO.findById(Magazine.class, Integer.valueOf(id));
		MagazineDTO equipDTO = null;
		if(equip != null){
			equipDTO = new MagazineDTO();
			try {
				equipDTO = (MagazineDTO) transformer.transform(equip, equipDTO);
			} catch (ConvertException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(this.getClass().getName() + "getEquip error -- ",e);
			}
		}
		return equipDTO;
	}
	
	public void saveEquipModel(MagazineDTO equipmentDTO) {
		Integer id = equipmentDTO.getId();
		Magazine equipment = null;
		if(id != null && id > 0){
			// 更新
			equipment = this.euqipmentDAO.findById(Magazine.class, id);
		} else {
			equipment = new Magazine();
		}
		equipment.setMagazineName(equipmentDTO.getMagazineName());
		equipment.setEuipModelName(equipmentDTO.getEuipModelName());
		equipment.setModel(equipmentDTO.getModel());
		String tempRemark = StringUtils.isEmpty(equipment.getRemark()) ? "":equipment.getRemark();
		String remark = StringUtils.isEmpty(equipmentDTO.getRemark()) ? "" : equipmentDTO.getRemark() + DateTimeUtils.getCurrentTimestamp();
		equipment.setRemark(remark + tempRemark);
		equipment.setSpec(equipmentDTO.getSpec());
		equipment.setStockLowerLimit(equipmentDTO.getStockLowerLimit());
		equipment.setStockUpperLimit(equipmentDTO.getStockUpperLimit());
		equipment.setTotalQuantity(equipmentDTO.getTotalQuantity());
		equipment.setUnit(equipmentDTO.getUnit());
		equipment.setDeleteFlag('N');
		if(id != null && id > 0){
			equipment.setUpdatedById("");
			equipment.setUpdatedByName("");
			equipment.setUpdatedDate(DateTimeUtils.getCurrentTimestamp());
		} else {
			equipment.setCreateById("");
			equipment.setCreatedByName("");
			equipment.setCreatedDate(DateTimeUtils.getCurrentTimestamp());
		}
		this.euqipmentDAO.saveOrUpdate(equipment);
	}
	
}
