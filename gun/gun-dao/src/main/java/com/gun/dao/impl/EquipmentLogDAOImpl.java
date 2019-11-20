package com.gun.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.exception.DataException;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.cyber.lottery.dao.AbstractDAOImpl;
import com.cyber.lottery.dao.EquipmentLogDAO;
import com.gun.common.entity.EquipmentLog;
import com.gun.common.entity.pojo.EquipmentLogDTO;
import com.gun.common.exception.DataAccessException;

@Repository
public class EquipmentLogDAOImpl extends AbstractDAOImpl<EquipmentLog> implements
		EquipmentLogDAO {
	private static final Log log = LogFactory.getLog(EquipmentLogDAOImpl.class);

	private String prepareSQLForEquipmentLog(){
		String sql = "SELECT id, equipment_id as \"equipmentId\", log_type as \"logType\", "
				+ "old_quantity as \"oldQuantity\",update_quantity as \"updateQuantity\", "
				+ "log_remark as \"logRemark\", modify_id as \"modifyId\", modify_name as \"modifyName\", "
				+ "modify_date as \"modifyDate\" FROM equipment_log where 1=1";
		return sql;
	}
	
	public List<EquipmentLogDTO> queryList(String equipId){
		List<EquipmentLogDTO> equipmentLogDTOList = null;
		SQLQuery sqlQuery = null;
		try{
			String sql = prepareSQLForEquipmentLog();
			
			if(StringUtils.hasText(equipId)){
				sql += " AND equipment_id = :equipmentId ";
			}
			sqlQuery =  this.getSession().createSQLQuery(sql);
			if(StringUtils.hasText(equipId)){
				sqlQuery.setParameter(EquipmentLogDTO.ATTRIBUTE.EQUIPMENT_ID.getValue(), Integer.valueOf(equipId).intValue());
			}
			sqlQuery.setResultTransformer(Transformers.aliasToBean(EquipmentLogDTO.class));
			equipmentLogDTOList = sqlQuery.list();
		} catch (DataException e) {
			log.error(this.getClass().getName()+"queryList failed!!" + e );
			throw new DataAccessException(e);
		}
		return equipmentLogDTOList;
	}
	
}
