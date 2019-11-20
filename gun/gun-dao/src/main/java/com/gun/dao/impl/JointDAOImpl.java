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
import com.cyber.lottery.dao.JointDAO;
import com.gun.common.entity.Joint;
import com.gun.common.entity.pojo.EquipmentDTO;
import com.gun.common.entity.pojo.JointDTO;
import com.gun.common.exception.DataAccessException;

@Repository
public class JointDAOImpl extends AbstractDAOImpl<Joint> implements
		JointDAO {
	private static final Log log = LogFactory.getLog(JointDAOImpl.class);
	
	private String prepareSQLForEquipment(){
		String sql = " SELECT id as id,"
				+ "joint_name as \"jointName\", joint_external_diameter as \"jointExternalDiameter\","
				+ "joint_length as \"jointLength\", total_quantity AS \"totalQuantity\", "
				+ "delete_flag AS \"deleteFlag\", create_by_id AS \"createById\", created_by_name AS \"createdByName\","
				+ "created_date AS \"createdDate\", updated_by_id AS \"updatedById\", "
				+ "updated_by_name AS \"updatedByName\", updated_date AS \"updatedDate\""
				+ "FROM joint where delete_flag = 'N' ";
		return sql;
	}
	
	public List<JointDTO> queryList(String equipName){
		List<JointDTO> equipmentDTOList = null;
		SQLQuery sqlQuery = null;
		try{
			String sql = prepareSQLForEquipment();
			if(StringUtils.hasText(equipName)){
				sql += " AND joint_name like '%" + equipName +"%'";
				
			}
			sqlQuery =  this.getSession().createSQLQuery(sql);
			sqlQuery.setResultTransformer(Transformers.aliasToBean(JointDTO.class));
			equipmentDTOList = sqlQuery.list();
		} catch (DataException e) {
			log.error(this.getClass().getName()+"queryList failed!!" + e );
			throw new DataAccessException(e);
		}
		return equipmentDTOList;
	}
	
	public JointDTO queryByName(String equipName){
		List<JointDTO> equipmentDTOList = null;
		SQLQuery sqlQuery = null;
		try{
			String sql = prepareSQLForEquipment();
			if(StringUtils.hasText(equipName)){
				sql += " AND joint_name = :equipName";
				
			}
			sqlQuery =  this.getSession().createSQLQuery(sql);
			sqlQuery.setParameter("equipName", equipName);
			sqlQuery.setResultTransformer(Transformers.aliasToBean(JointDTO.class));
			equipmentDTOList = sqlQuery.list();
		} catch (DataException e) {
			log.error(this.getClass().getName()+"queryList failed!!" + e );
			throw new DataAccessException(e);
		}
		return equipmentDTOList.get(0);
	}
	
}
