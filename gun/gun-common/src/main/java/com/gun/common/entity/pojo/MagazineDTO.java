package com.gun.common.entity.pojo;
// Generated 2017-6-1 18:10:41 by Hibernate Tools 4.0.1.Final

import java.sql.Timestamp;

import com.gun.common.pojo.ValueObject;

public class MagazineDTO extends ValueObject<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static enum ATTRIBUTE {
	    ID("id"),
	    MAGAZINE_NAME("magazineName"),
	    MODEL("model"),
	    SPEC("spec"),
	    UNIT("unit"),
	    EUIP_MODEL_NAME("euipModelName"),
	    STOCK_UPPER_LIMIT("stockUpperLimit"),
	    STOCK_LOWER_LIMIT("stockLowerLimit"),
	    REMARK("remark"),
	    TOTAL_QUANTITY("totalQuantity"),
	    DELETE_FLAG("deleteFlag"),
	    CREATE_BY_ID("createById"),
	    CREATED_BY_NAME("createdByName"),
	    CREATED_DATE("createdDate"),
	    UPDATED_BY_ID("updatedById"),
	    UPDATED_BY_NAME("updatedByName"),
	    UPDATED_DATE("updatedDate")
	    ;
	    
	    private String value;
	    ATTRIBUTE(String value) {
	      this.value = value;
	    };
	    public String getValue() {
	      return this.value;
	    }
	};
  
	private String magazineName;
	private String model;
	private String spec;
	private String unit;
	private String euipModelName;
	private Integer stockUpperLimit;
	private Integer stockLowerLimit;
	private String remark;
	private int totalQuantity;
	private char deleteFlag;
	private String createById;
	private String createdByName;
	private Timestamp createdDate;
	private String updatedById;
	private String updatedByName;
	private Timestamp updatedDate;
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getEuipModelName() {
		return euipModelName;
	}
	public void setEuipModelName(String euipModelName) {
		this.euipModelName = euipModelName;
	}
	public Integer getStockUpperLimit() {
		return stockUpperLimit;
	}
	public void setStockUpperLimit(Integer stockUpperLimit) {
		this.stockUpperLimit = stockUpperLimit;
	}
	public Integer getStockLowerLimit() {
		return stockLowerLimit;
	}
	public void setStockLowerLimit(Integer stockLowerLimit) {
		this.stockLowerLimit = stockLowerLimit;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getMagazineName() {
		return magazineName;
	}
	public void setMagazineName(String magazineName) {
		this.magazineName = magazineName;
	}
	public int getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public char getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(char deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getCreateById() {
		return createById;
	}
	public void setCreateById(String createById) {
		this.createById = createById;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedById() {
		return updatedById;
	}
	public void setUpdatedById(String updatedById) {
		this.updatedById = updatedById;
	}
	public String getUpdatedByName() {
		return updatedByName;
	}
	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	public static void main(String args[]){
		java.lang.reflect.Field[] f  = MagazineDTO.class.getDeclaredFields();
		String sub = "";
		for(int i=0;i<f.length;i++){
			// USER_ID("userId"),
			String name = f[i].getName();
			char[] c = name.toCharArray();
			for(char ci : c){
				// <91为大写，在其前方加_
				if((int)ci < 91){
					sub += "_";
				}
				sub += String.valueOf(ci).toUpperCase();
			}
			sub += "(\"" + name + "\"),";
			sub += "\n";
		}
		System.out.println(sub);
	}
}
