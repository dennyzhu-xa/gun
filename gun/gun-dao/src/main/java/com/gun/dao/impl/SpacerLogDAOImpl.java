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
import com.cyber.lottery.dao.SpacerLogDAO;
import com.gun.common.entity.SpacerLog;
import com.gun.common.entity.pojo.MagazineLogDTO;
import com.gun.common.entity.pojo.SpacerLogDTO;
import com.gun.common.exception.DataAccessException;

@Repository
public class SpacerLogDAOImpl extends AbstractDAOImpl<SpacerLog> implements SpacerLogDAO {
	private static final Log log = LogFactory.getLog(SpacerLogDAOImpl.class);

	private String prepareSQLForEquipmentLog(){
		String sql = "SELECT id, spacer_id as \"spacerId\", log_type as \"logType\", "
				+ "old_quantity as \"oldQuantity\",update_quantity as \"updateQuantity\", "
				+ "log_remark as \"logRemark\", modify_id as \"modifyId\", modify_name as \"modifyName\", "
				+ "modify_date as \"modifyDate\" FROM spacer_log where 1=1";
		return sql;
	}
	
	public List<SpacerLogDTO> queryList(String equipId){
		List<SpacerLogDTO> equipmentLogDTOList = null;
		SQLQuery sqlQuery = null;
		try{
			String sql = prepareSQLForEquipmentLog();
			
			if(StringUtils.hasText(equipId)){
				sql += " AND spacer_id = :equipmentId ";
			}
			sqlQuery =  this.getSession().createSQLQuery(sql);
			if(StringUtils.hasText(equipId)){
				sqlQuery.setParameter(SpacerLogDTO.ATTRIBUTE.SPACER_ID.getValue(), Integer.valueOf(equipId).intValue());
			}
			sqlQuery.setResultTransformer(Transformers.aliasToBean(SpacerLogDTO.class));
			equipmentLogDTOList = sqlQuery.list();
		} catch (DataException e) {
			log.error(this.getClass().getName()+"queryList failed!!" + e );
			throw new DataAccessException(e);
		}
		return equipmentLogDTOList;
	}
	
}
