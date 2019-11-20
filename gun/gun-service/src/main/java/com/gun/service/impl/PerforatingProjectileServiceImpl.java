package com.gun.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.runtime.parser.node.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyber.lottery.dao.PerforatingProjectileDAO;
import com.cyber.lottery.dao.PerforatingProjectileLogDAO;
import com.gun.common.entity.PerforatingProjectile;
import com.gun.common.entity.PerforatingProjectileLog;
import com.gun.common.entity.pojo.PerforatingProjectileDTO;
import com.gun.common.entity.pojo.PerforatingProjectileLogDTO;
import com.gun.common.exception.ConvertException;
import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.Model;
import com.gun.common.utils.DateTimeUtils;
import com.gun.common.utils.SimpleDtoDmoTransformer;
import com.gun.service.PerforatingProjectileService;
@Service
public class PerforatingProjectileServiceImpl implements PerforatingProjectileService {

	private SimpleDtoDmoTransformer transformer = new SimpleDtoDmoTransformer();
	private static final Log log = LogFactory.getLog(PerforatingProjectileServiceImpl.class);
	@Autowired
	private PerforatingProjectileDAO euqipmentDAO;
	
	@Autowired
	private PerforatingProjectileLogDAO equipmentLogDAO;
	
	public List<String> listEquipModel(){
		List<String> result = this.euqipmentDAO.listEquipModel();
		return result;
	}

	public void delEquip(String id){
		if(StringUtils.isEmpty(id)){
			return;
		}
		PerforatingProjectile equip = this.euqipmentDAO.findById(PerforatingProjectile.class, Integer.valueOf(id));
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

	public List<PerforatingProjectileDTO> queryList(String equipName,String equipModel){
		List<PerforatingProjectileDTO> result = this.euqipmentDAO.queryList(equipName, equipModel);
		return result;
	}
	
	public void saveEquipLog(PerforatingProjectileLogDTO equipLogDTO){
		if(equipLogDTO == null){
			log.error(this.getClass().getName() + "equipLogDTO is null or equipLogDTO.getEquipmentId() is null " + equipLogDTO);
			return;
		}
		PerforatingProjectile eq = this.euqipmentDAO.findById(PerforatingProjectile.class, Integer.valueOf(equipLogDTO.getPerforatingId()));
		int oldQuanlity = eq == null ? 0 : eq.getTotalQuantity();
		PerforatingProjectileLog equipLog = new PerforatingProjectileLog();
		try {
			equipLog = (PerforatingProjectileLog) transformer.transform(equipLogDTO, equipLog);
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
	
	public List<PerforatingProjectileLogDTO> queryList(String equipId){
		List<PerforatingProjectileLogDTO> equipmentLogDTOList = this.equipmentLogDAO.queryList(equipId);
		return equipmentLogDTOList;
	}
	
	public PerforatingProjectileDTO getEquip (String id){
		if(StringUtils.isEmpty(id)){
			return null;
		}
		PerforatingProjectile equip = this.euqipmentDAO.findById(PerforatingProjectile.class, Integer.valueOf(id));
		PerforatingProjectileDTO equipDTO = null;
		if(equip != null){
			equipDTO = new PerforatingProjectileDTO();
			try {
				equipDTO = (PerforatingProjectileDTO) transformer.transform(equip, equipDTO);
			} catch (ConvertException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(this.getClass().getName() + "getEquip error -- ",e);
			}
		}
		return equipDTO;
	}
	
	public void saveEquipModel(PerforatingProjectileDTO equipmentDTO) {
		Integer id = equipmentDTO.getId();
		PerforatingProjectile equipment = null;
		if(id != null && id > 0){
			// 更新
			equipment = this.euqipmentDAO.findById(PerforatingProjectile.class, id);
		} else {
			equipment = new PerforatingProjectile();
		}
		equipment.setPerforatingName(equipmentDTO.getPerforatingName());
		equipment.setModel(equipmentDTO.getModel());
		String tempRemark = StringUtils.isEmpty(equipment.getRemark()) ? "":equipment.getRemark();
		String remark = StringUtils.isEmpty(equipmentDTO.getRemark()) ? "" : equipmentDTO.getRemark() + DateTimeUtils.getCurrentTimestamp();
		equipment.setRemark(remark + tempRemark);
		equipment.setStockLowerLimit(equipmentDTO.getStockLowerLimit());
		equipment.setStockUpperLimit(equipmentDTO.getStockUpperLimit());
		equipment.setTotalQuantity(equipmentDTO.getTotalQuantity());
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
