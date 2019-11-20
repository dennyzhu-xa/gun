package com.gun.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.cyber.lottery.dao.AbstractDAOImpl;
import com.cyber.lottery.dao.WellLevelDAO;
import com.gun.common.entity.WellLevel;
import com.gun.common.entity.pojo.WellLevelDTO;
import com.gun.common.exception.DataAccessException;

@Repository
public class WellLevelDAOImpl extends AbstractDAOImpl<WellLevel> implements
		WellLevelDAO {
	private static final Log log = LogFactory.getLog(WellLevelDAOImpl.class);
	
	public void deleteByWellId(Integer wellId){
		String sql = "delete from well_level where well_id = :wellId";
		SQLQuery sqlQuery = this.getSession().createSQLQuery(sql);
		sqlQuery.setParameter("wellId", wellId);
		sqlQuery.executeUpdate();
	}
	
	public List<WellLevelDTO> listByWellId(Integer wellId){
		List<WellLevelDTO> results = null;
		try{
			String sql = this.prepareSQLForWellLevel();
			if(wellId != null && wellId>0){
				sql += " and well_id = :wellId ";
			}
			SQLQuery sqlQuery = this.getSession().createSQLQuery(sql);
			if(wellId != null && wellId > 0){
				sqlQuery.setParameter("wellId", wellId);
			}
			sqlQuery.setResultTransformer(Transformers.aliasToBean(WellLevelDTO.class));
			results = sqlQuery.list();
		}catch(DataAccessException e){
			log.error(this.getClass().getName()+"listByWellId failed!!" + e );
			throw new DataAccessException(e);
		}
		return results;
	}
	
	private String prepareSQLForWellLevel(){
		String sql = "select id as id,well_id as \"wellId\",oil_start as \"oilStart\","
					+ "oil_end as \"oilEnd\",level_depth as \"levelDepth\",backfin_depth as \"backinDepth\""
					+ "from well_level where 1=1";
		return sql;
	}
	
}
