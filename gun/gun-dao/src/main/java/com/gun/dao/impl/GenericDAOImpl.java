package com.gun.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * Purpose: 
 * @author akumadeng
 * @since  JDK 1.5
 * @date   2014/8/13
 * @MaintenancePersonnel akumadeng
 */
@Repository("genericDAOImpl")
public class GenericDAOImpl{

	private String presetSchema;
	private Map<String,String> schemas;
	
	public GenericDAOImpl(){
	}

	/**
	 * @return the presetSchema
	 */
	public String getPresetSchema() {
		return presetSchema;
	}

	/**
	 * @param presetSchema the presetSchema to set
	 */
	public void setPresetSchema(String presetSchema) {
		this.presetSchema = presetSchema;
	}

	/**
	 * @return the schemas
	 */
	public Map<String, String> getSchemas() {
		return schemas;
	}

	/**
	 * @param schemas the schemas to set
	 */
	public void setSchemas(Map<String, String> schemas) {
		this.schemas = schemas;
	}
		
	/**
	 * @return the schemas
	 */
	public String getCmsSchema() {
		String schema="";
		if (StringUtils.hasText(this.presetSchema)) schema = this.getSchema(this.presetSchema);
	    return schema;
	}
	
	/** 
	 * Purpose:回傳指定schema
	 * @param 傳入欲查詢的系統ID
	 * @return String
	 */
	public String getSchema(String systemId) {
	    if (CollectionUtils.isEmpty(this.schemas) || !this.schemas.containsKey(systemId)) return "";
	    return this.schemas.get(systemId);
	}
}
