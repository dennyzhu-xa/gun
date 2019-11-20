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
import com.cyber.lottery.dao.JointLogDAO;
import com.gun.common.entity.JointLog;
import com.gun.common.entity.pojo.EquipmentLogDTO;
import com.gun.common.entity.pojo.JointLogDTO;
import com.gun.common.exception.DataAccessException;

@Repository
public class JointLogDAOImpl extends AbstractDAOImpl<JointLog> implements
		JointLogDAO {
	private static final Log log = LogFactory.getLog(JointLogDAOImpl.class);

	private String prepareSQLForEquipmentLog(){
		String sql = "SELECT id, joint_id as \"jointId\", log_type as \"logType\", "
				+ "old_quantity as \"oldQuantity\",update_quantity as \"updateQuantity\", "
				+ "log_remark as \"logRemark\", modify_id as \"modifyId\", modify_name as \"modifyName\", "
				+ "modify_date as \"modifyDate\" FROM joint_log where 1=1";
		return sql;
	}
	
	public List<JointLogDTO> queryList(String equipId){
		List<JointLogDTO> equipmentLogDTOList = null;
		SQLQuery sqlQuery = null;
		try{
			String sql = prepareSQLForEquipmentLog();
			
			if(StringUtils.hasText(equipId)){
				sql += " AND joint_id = :equipmentId ";
			}
			sqlQuery =  this.getSession().createSQLQuery(sql);
			if(StringUtils.hasText(equipId)){
				sqlQuery.setParameter(JointLogDTO.ATTRIBUTE.JOINT_ID.getValue(), Integer.valueOf(equipId).intValue());
			}
			sqlQuery.setResultTransformer(Transformers.aliasToBean(JointLogDTO.class));
			equipmentLogDTOList = sqlQuery.list();
		} catch (DataException e) {
			log.error(this.getClass().getName()+"queryList failed!!" + e );
			throw new DataAccessException(e);
		}
		return equipmentLogDTOList;
	}
	
}
