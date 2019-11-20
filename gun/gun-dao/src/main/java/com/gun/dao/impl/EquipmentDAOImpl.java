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
import com.cyber.lottery.dao.EquipmentDAO;
import com.gun.common.entity.Equipment;
import com.gun.common.entity.pojo.EquipmentDTO;
import com.gun.common.exception.DataAccessException;

@Repository
public class EquipmentDAOImpl extends AbstractDAOImpl<Equipment> implements
		EquipmentDAO {
	private static final Log log = LogFactory.getLog(EquipmentDAOImpl.class);
	
	/**
	 * list所有的器材型号
	 */
	public List<String> listEquipModel(){
		String sql  = "select distinct(model) from equipment where delete_Flag='N'";
		List<String> result = super.getQueryList(sql, null, null, null);
		return result;
	}

	private String prepareSQLForEquipment(){
		String sql = " SELECT id as id,"
				+ "equipment_type AS \"equipmentType\", equipment_name AS \"equipmentName\", model AS \"model\","
				+ " spec AS \"spec\", "
				+ "unit AS \"unit\", euip_model_name AS \"euipModelName\", stock_upper_limit AS \"stockUpperLimit\","
				+ "stock_lower_limit AS \"stockLowerLimit\", remark AS \"remark\", "
				+ "external_diameter AS \"externalDiameter\","
				+ "length AS \"length\", single_blind_area AS \"singleBlindArea\", shot_number AS \"shotNumber\", "
				+ "shot_density AS \"shotDensity\", shot_space AS \"shotSpace\", total_quantity AS \"totalQuantity\", "
				+ "delete_flag AS \"deleteFlag\", create_by_id AS \"createById\", created_by_name AS \"createdByName\","
				+ "created_date AS \"createdDate\", updated_by_id AS \"updatedById\", "
				+ "updated_by_name AS \"updatedByName\", updated_date AS \"updatedDate\""
				+ "FROM public.equipment where delete_flag = 'N' and equipment_type = 'G' ";
		return sql;
	}
	
	public List<EquipmentDTO> queryList(String equipName,String equipModel){
		List<EquipmentDTO> equipmentDTOList = null;
		SQLQuery sqlQuery = null;
		try{
			String sql = prepareSQLForEquipment();
			if(StringUtils.hasText(equipName)){
				sql += " AND equipment_name like '%" + equipName +"%'";
				
			}
			if(StringUtils.hasText(equipModel)){
				sql += " AND model = :model ";
			}
			sqlQuery =  this.getSession().createSQLQuery(sql);
			if(StringUtils.hasText(equipModel)){
				sqlQuery.setParameter(EquipmentDTO.ATTRIBUTE.MODEL.getValue(), equipModel);
			}
			sqlQuery.setResultTransformer(Transformers.aliasToBean(EquipmentDTO.class));
			equipmentDTOList = sqlQuery.list();
		} catch (DataException e) {
			log.error(this.getClass().getName()+"queryList failed!!" + e );
			throw new DataAccessException(e);
		}
		return equipmentDTOList;
	}
	
	public List<EquipmentDTO> queryByNameArray(String names){
		List<EquipmentDTO> equipmentDTOList = null;
		SQLQuery sqlQuery = null;
		try{
			String sql = prepareSQLForEquipment();
			if(names != null && names.length() > 0){
				
				sql += " AND euip_model_name in (" + names +")";
				
			}
			sqlQuery =  this.getSession().createSQLQuery(sql);
			sqlQuery.setResultTransformer(Transformers.aliasToBean(EquipmentDTO.class));
			equipmentDTOList = sqlQuery.list();
		} catch (DataException e) {
			log.error(this.getClass().getName()+"queryList failed!!" + e );
			throw new DataAccessException(e);
		}
		return equipmentDTOList;
	}
	
}
