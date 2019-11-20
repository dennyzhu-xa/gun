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
import com.cyber.lottery.dao.MagazineDAO;
import com.gun.common.entity.Magazine;
import com.gun.common.entity.pojo.EquipmentDTO;
import com.gun.common.entity.pojo.MagazineDTO;
import com.gun.common.exception.DataAccessException;

@Repository
public class MagazineDAOImpl extends AbstractDAOImpl<Magazine> implements
		MagazineDAO {
	private static final Log log = LogFactory.getLog(MagazineDAOImpl.class);
	
	/**
	 * list所有的器材型号
	 */
	public List<String> listEquipModel(){
		String sql  = "select distinct(model) from magazine where delete_Flag='N'";
		List<String> result = super.getQueryList(sql, null, null, null);
		return result;
	}

	private String prepareSQLForEquipment(){
		String sql = " SELECT id as id,"
				+ " magazine_name AS \"magazineName\", model AS \"model\", spec AS \"spec\", "
				+ "unit AS \"unit\", euip_model_name AS \"euipModelName\", stock_upper_limit AS \"stockUpperLimit\","
				+ "stock_lower_limit AS \"stockLowerLimit\", remark AS \"remark\", total_quantity AS \"totalQuantity\", "
				+ "delete_flag AS \"deleteFlag\", create_by_id AS \"createById\", created_by_name AS \"createdByName\","
				+ "created_date AS \"createdDate\", updated_by_id AS \"updatedById\", "
				+ "updated_by_name AS \"updatedByName\", updated_date AS \"updatedDate\""
				+ "FROM public.magazine where delete_flag = 'N' ";
		return sql;
	}
	
	public List<MagazineDTO> queryList(String equipName,String equipModel){
		List<MagazineDTO> equipmentDTOList = null;
		SQLQuery sqlQuery = null;
		try{
			String sql = prepareSQLForEquipment();
			if(StringUtils.hasText(equipName)){
				sql += " AND magazine_name like '%" + equipName +"%'";
				
			}
			if(StringUtils.hasText(equipModel)){
				sql += " AND model = :model ";
			}
			sqlQuery =  this.getSession().createSQLQuery(sql);
			if(StringUtils.hasText(equipModel)){
				sqlQuery.setParameter(MagazineDTO.ATTRIBUTE.MODEL.getValue(), equipModel);
			}
			sqlQuery.setResultTransformer(Transformers.aliasToBean(MagazineDTO.class));
			equipmentDTOList = sqlQuery.list();
		} catch (DataException e) {
			log.error(this.getClass().getName()+"queryList failed!!" + e );
			throw new DataAccessException(e);
		}
		return equipmentDTOList;
	}
	
}
