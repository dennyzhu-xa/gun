package com.gun.dao.impl;

import java.util.List;

import org.apache.axis2.databinding.types.soapencoding.DateTime;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.cyber.lottery.dao.AbstractDAOImpl;
import com.cyber.lottery.dao.OilWellDAO;
import com.gun.common.entity.OilWell;
import com.gun.common.entity.pojo.OilWellDTO;

@Repository
public class OilWellDAOImpl extends AbstractDAOImpl<OilWell> implements
		OilWellDAO {
	private static final Log log = LogFactory.getLog(OilWellDAOImpl.class);
	
	public List<OilWellDTO> listOilWellDTO(String user){
		List<OilWellDTO> result = null;
		String sql  = this.prepareSQLForOilWell();
		if(StringUtils.hasText(user)){
			sql += " and updated_by_id = :updateById ";
		}
		SQLQuery sqlQuery = this.getSession().createSQLQuery(sql);
		if(StringUtils.hasText(user)){
			sqlQuery.setParameter("updateById", user);
		}
		sqlQuery.setResultTransformer(Transformers.aliasToBean(OilWellDTO.class));
		result = sqlQuery.list();
		return result;
	}
	
	public OilWellDTO getOilWellDTO(String wellNo,DateTime updateTime){
		OilWellDTO oilWellDTO = null;
		String sql = this.prepareSQLForOilWell();
		if(StringUtils.hasText(wellNo)){
			sql += " and oil_no = :wellNo ";
		}
		if(updateTime != null){
			sql += " and updated_date = :updateDate ";
		}
		SQLQuery sqlQuery = this.getSession().createSQLQuery(sql);
		
		if(StringUtils.hasText(wellNo)){
			sqlQuery.setParameter("wellNo", wellNo);
		}
		if(updateTime != null){
			sqlQuery.setParameter("updateDate", updateTime);
		}
		sqlQuery.setResultTransformer(Transformers.aliasToBean(OilWellDTO.class));
		@SuppressWarnings("unchecked")
		List<OilWellDTO> result = sqlQuery.list();
		if(result != null && result.size() > 0){
			oilWellDTO = result.get(0);
		}
		return oilWellDTO;
	}
	
	private String prepareSQLForOilWell(){
		String sql = "select id as id, well_type as \"wellType\",own_name as \"ownName\",oil_no as \"oilNo\","
			+ "area as area,manual_deep as \"manualDeep\",level_quanlity as \"levelQuanlity\","
			+ "surface_temperature as \"surfaceTemperature\",oil_temperature as \"oilTemperature\","
			+ "equipment_model_id as \"equipmentModelId\",equipments as equipments,spacer_model_id as \"spacerModelId\","
			+ "spacers as spacers,perforating_id as \"perforatingId\",connect_id as \"connectId\",phase as phase,"
			+ "extra_length as \"extraLength\",safety_length as \"safetyLength\",tech_remark as \"techRemark\","
			+ "create_by_id as \"createById\",created_by_name as \"createdByName\",created_date as \"createdDate\","
			+ "updated_by_id as \"updatedById\",updated_by_name as \"updatedByName\",updated_date as \"updatedDate\" "
			+ "from oil_well where 1=1";
		return sql;
	}
	
}
