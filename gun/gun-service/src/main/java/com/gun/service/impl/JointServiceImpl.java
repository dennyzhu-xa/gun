package com.gun.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.runtime.parser.node.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyber.lottery.dao.JointDAO;
import com.cyber.lottery.dao.JointLogDAO;
import com.gun.common.entity.Joint;
import com.gun.common.entity.JointLog;
import com.gun.common.entity.pojo.JointDTO;
import com.gun.common.entity.pojo.JointLogDTO;
import com.gun.common.exception.ConvertException;
import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.Model;
import com.gun.common.utils.DateTimeUtils;
import com.gun.common.utils.SimpleDtoDmoTransformer;
import com.gun.service.JointService;
@Service
public class JointServiceImpl implements JointService {

	private SimpleDtoDmoTransformer transformer = new SimpleDtoDmoTransformer();
	private static final Log log = LogFactory.getLog(JointServiceImpl.class);
	@Autowired
	private JointDAO jointDAO;
	
	@Autowired
	private JointLogDAO jointLogDAO;
	
	public void delEquip(String id){
		if(StringUtils.isEmpty(id)){
			return;
		}
		Joint equip = this.jointDAO.findById(Joint.class, Integer.valueOf(id));
		if(equip != null){
			equip.setDeleteFlag('Y');
		}
		this.jointDAO.saveOrUpdate(equip);
	}
	
	@Override
	public Model init(Object request) throws ServiceException {
		return null;
	}

	public void saveJointLog(JointLogDTO equipLogDTO){
		if(equipLogDTO == null){
			log.error(this.getClass().getName() + "equipLogDTO is null or equipLogDTO.getEquipmentId() is null " + equipLogDTO);
			return;
		}
		Joint eq = this.jointDAO.findById(Joint.class, Integer.valueOf(equipLogDTO.getJointId()));
		int oldQuanlity = eq == null ? 0 : eq.getTotalQuantity();
		JointLog equipLog = new JointLog();
		try {
			equipLog = (JointLog) transformer.transform(equipLogDTO, equipLog);
			equipLog.setOldQuantity(oldQuanlity);
			equipLog.setModifyDate(DateTimeUtils.getCurrentTimestamp());
			equipLog.setModifyId(null);
			equipLog.setModifyName(null);
			
		} catch (ConvertException e) {
			e.printStackTrace();
			log.error(this.getClass().getName() + "saveJointLog:",e);
		}
		this.jointLogDAO.save(equipLog);

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

	public List<JointDTO> queryJointList (String jointName){
		List<JointDTO> result = this.jointDAO.queryList(jointName);
		return result;
	}
	
	public JointDTO queryByName(String equipName){
		JointDTO jointDTO = this.jointDAO.queryByName(equipName);
		return jointDTO;
	}
	
	public List<JointLogDTO> queryList(String equipId){
		List<JointLogDTO> equipmentLogDTOList = this.jointLogDAO.queryList(equipId);
		return equipmentLogDTOList;
	}
	
	public JointDTO getEquip (String id){
		if(StringUtils.isEmpty(id)){
			return null;
		}
		Joint equip = this.jointDAO.findById(Joint.class, Integer.valueOf(id));
		JointDTO equipDTO = null;
		if(equip != null){
			equipDTO = new JointDTO();
			try {
				equipDTO = (JointDTO) transformer.transform(equip, equipDTO);
			} catch (ConvertException e) {
				e.printStackTrace();
				log.error(this.getClass().getName() + "getEquip error -- ",e);
			}
		}
		return equipDTO;
	}

	public void saveEquipModel(JointDTO jointDTO) {
		Integer id = jointDTO.getId();
		Joint joint = null;
		if(id != null && id > 0){
			// 更新
			joint = this.jointDAO.findById(Joint.class, id);
		} else {
			joint = new Joint();
		}
		joint.setJointName(jointDTO.getJointName());
		joint.setJointExternalDiameter(jointDTO.getJointExternalDiameter());
		joint.setJointLength(jointDTO.getJointLength());
		joint.setTotalQuantity(jointDTO.getTotalQuantity());
		joint.setDeleteFlag('N');
		if(id != null && id > 0){
			joint.setUpdatedById("");
			joint.setUpdatedByName("");
			joint.setUpdatedDate(DateTimeUtils.getCurrentTimestamp());
		} else {
			joint.setCreateById("");
			joint.setCreatedByName("");
			joint.setCreatedDate(DateTimeUtils.getCurrentTimestamp());
		}
		this.jointDAO.saveOrUpdate(joint);
	}
	
}
