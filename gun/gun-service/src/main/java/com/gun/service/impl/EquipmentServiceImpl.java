package com.gun.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.runtime.parser.node.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyber.lottery.dao.EquipmentDAO;
import com.cyber.lottery.dao.EquipmentLogDAO;
import com.gun.common.entity.Equipment;
import com.gun.common.entity.EquipmentLog;
import com.gun.common.entity.pojo.EquipmentDTO;
import com.gun.common.entity.pojo.EquipmentLogDTO;
import com.gun.common.exception.ConvertException;
import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.Model;
import com.gun.common.utils.DateTimeUtils;
import com.gun.common.utils.SimpleDtoDmoTransformer;
import com.gun.service.EquipmentService;
@Service
public class EquipmentServiceImpl implements EquipmentService {

	private SimpleDtoDmoTransformer transformer = new SimpleDtoDmoTransformer();
	private static final Log log = LogFactory.getLog(EquipmentServiceImpl.class);
	@Autowired
	private EquipmentDAO euqipmentDAO;
	
	@Autowired
	private EquipmentLogDAO equipmentLogDAO;
	
	public List<String> listEquipModel(){
		List<String> result = this.euqipmentDAO.listEquipModel();
		return result;
	}

	public List<EquipmentDTO> listEquipByModel(String model){
		List<EquipmentDTO> result = this.euqipmentDAO.queryList(null,model);
		return result;
	}
	
	public void delEquip(String id){
		if(StringUtils.isEmpty(id)){
			return;
		}
		Equipment equip = this.euqipmentDAO.findById(Equipment.class, Integer.valueOf(id));
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

	public List<EquipmentDTO> queryList(String equipName,String equipModel){
		List<EquipmentDTO> result = this.euqipmentDAO.queryList(equipName, equipModel);
		return result;
	}
	
	public void saveEquipLog(EquipmentLogDTO equipLogDTO){
		if(equipLogDTO == null){
			log.error(this.getClass().getName() + "equipLogDTO is null or equipLogDTO.getEquipmentId() is null " + equipLogDTO);
			return;
		}
		Equipment eq = this.euqipmentDAO.findById(Equipment.class, Integer.valueOf(equipLogDTO.getEquipmentId()));
		int oldQuanlity = eq == null ? 0 : eq.getTotalQuantity();
		EquipmentLog equipLog = new EquipmentLog();
		try {
			equipLog = (EquipmentLog) transformer.transform(equipLogDTO, equipLog);
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
	
	public List<EquipmentLogDTO> queryList(String equipId){
		List<EquipmentLogDTO> equipmentLogDTOList = this.equipmentLogDAO.queryList(equipId);
		return equipmentLogDTOList;
	}
	
	public EquipmentDTO getEquip (String id){
		if(StringUtils.isEmpty(id)){
			return null;
		}
		Equipment equip = this.euqipmentDAO.findById(Equipment.class, Integer.valueOf(id));
		EquipmentDTO equipDTO = null;
		if(equip != null){
			equipDTO = new EquipmentDTO();
			try {
				equipDTO = (EquipmentDTO) transformer.transform(equip, equipDTO);
			} catch (ConvertException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(this.getClass().getName() + "getEquip error -- ",e);
			}
		}
		return equipDTO;
	}
	public List<EquipmentDTO> queryByNameArray(String names){
		 List<EquipmentDTO> result = this.euqipmentDAO.queryByNameArray(names);
		 return result;
	}
	
	public void saveEquipModel(EquipmentDTO equipmentDTO) {
		Integer id = equipmentDTO.getId();
		Equipment equipment = null;
		if(id != null && id > 0){
			// 更新
			equipment = this.euqipmentDAO.findById(Equipment.class, id);
		} else {
			equipment = new Equipment();
		}
		equipment.setEquipmentName(equipmentDTO.getEquipmentName());
		equipment.setEquipmentType(equipmentDTO.getEquipmentType());
		equipment.setEuipModelName(equipmentDTO.getEuipModelName());
		equipment.setExternalDiameter(equipmentDTO.getExternalDiameter());
		equipment.setLength(equipmentDTO.getLength());
		equipment.setModel(equipmentDTO.getModel());
		String tempRemark = StringUtils.isEmpty(equipment.getRemark()) ? "":equipment.getRemark();
		String remark = StringUtils.isEmpty(equipmentDTO.getRemark()) ? "" : equipmentDTO.getRemark() + DateTimeUtils.getCurrentTimestamp();
		equipment.setRemark(remark + tempRemark);
		equipment.setShotDensity(equipmentDTO.getShotDensity());
		equipment.setShotNumber(equipmentDTO.getShotNumber());
		equipment.setShotSpace(equipmentDTO.getShotSpace());
		equipment.setSingleBlindArea(equipmentDTO.getSingleBlindArea());
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
