package com.gun.common.entity.pojo;
// Generated 2017/7/3 �U�� 06:04:37 by Hibernate Tools 4.0.0.Final

import java.math.BigDecimal;
import java.util.Date;

import com.gun.common.pojo.ValueObject;

/**
 * InputWinninNo generated by hbm2java
 */
public class InputWinninNoDTO extends ValueObject<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -37916672903829190L;
	  public static enum ATTRIBUTE {
		  	WINNIN_NAME("winninName"),
		  	WINNIN_NAME_STRING("winninNameString"),
		  	OUR_COMPANY_PRODUCT("ourCompanyProduct"),
		  	PERIOD_ID("periodId"),
			WINNIN_ID("winninId"),
			WINNIN_PERIOD("winninPeriod"),
			WINNIN_GRADE("winninGrade"),
			WINNIN_NUMBER("winninNumber"),
			WINNIN_SUM("winninSum"),
			CREATE_BY_ID("createById"),
			CREATED_BY_NAME("createdByName"),
			CREATED_DATE("createdDate"),
			UPDATED_BY_ID("updatedById"),
			UPDATED_BY_NAME("updatedByName"),
			UPDATED_DATE("updatedDate"),
			EFFECTIVE_DATE("effectiveDate"),
			IS_CHECK("isCheck")						
		    ;
		    
		    private String value;
		    ATTRIBUTE(String value) {
		      this.value = value;
		    };
		    public String getValue() {
		      return this.value;
		    }
		  };
	private String winninId;
	private String winninPeriod;
	private int winninGrade;
	private String winninNumber;
	private BigDecimal winninSum;
	private String createById;
	private String createdByName;
	private Date createdDate;
	private String updatedById;
	private String updatedByName;
	private Date updatedDate;
	private String periodId;
	private String periodState;
	private String ourCompanyProduct;
	private String winninName;
	private String winninNameString;
	private String winninDesc;
	private String isCheck;
	private String createdDateString;
	private String effectiveDate;
	

	public InputWinninNoDTO() {
	}

	public InputWinninNoDTO(String winninId) {
		this.winninId = winninId;
	}

	public InputWinninNoDTO(String winninId, String winninPeriod, int winninGrade, String winninNumber, String isCheck,
			BigDecimal winninSum, String createById, String createdByName, Date createdDate, String updatedById,String effectiveDate,
			String updatedByName, Date updatedDate, String createdDateString) {
		this.winninId = winninId;
		this.winninPeriod = winninPeriod;
		this.winninGrade = winninGrade;
		this.winninNumber = winninNumber;
		this.winninSum = winninSum;
		this.createById = createById;
		this.createdByName = createdByName;
		this.createdDate = createdDate;
		this.updatedById = updatedById;
		this.updatedByName = updatedByName;
		this.updatedDate = updatedDate;
		this.isCheck = isCheck;
		this.createdDateString = createdDateString;
		this.effectiveDate = effectiveDate;
	}

	public String getWinninId() {
		return this.winninId;
	}

	public void setWinninId(String winninId) {
		this.winninId = winninId;
	}

	public String getWinninPeriod() {
		return this.winninPeriod;
	}

	public void setWinninPeriod(String winninPeriod) {
		this.winninPeriod = winninPeriod;
	}

	public int getWinninGrade() {
		return this.winninGrade;
	}

	public void setWinninGrade(int winninGrade) {
		this.winninGrade = winninGrade;
	}

	public String getWinninNumber() {
		return this.winninNumber;
	}

	public void setWinninNumber(String winninNumber) {
		this.winninNumber = winninNumber;
	}

	public BigDecimal getWinninSum() {
		return this.winninSum;
	}

	public void setWinninSum(BigDecimal winninSum) {
		this.winninSum = winninSum;
	}

	public String getCreateById() {
		return this.createById;
	}

	public void setCreateById(String createById) {
		this.createById = createById;
	}

	public String getCreatedByName() {
		return this.createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedById() {
		return this.updatedById;
	}

	public void setUpdatedById(String updatedById) {
		this.updatedById = updatedById;
	}

	public String getUpdatedByName() {
		return this.updatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the periodId
	 */
	public String getPeriodId() {
		return periodId;
	}

	/**
	 * @param periodId the periodId to set
	 */
	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}

	/**
	 * @return the ourCompanyProduct
	 */
	public String getOurCompanyProduct() {
		return ourCompanyProduct;
	}

	/**
	 * @param ourCompanyProduct the ourCompanyProduct to set
	 */
	public void setOurCompanyProduct(String ourCompanyProduct) {
		this.ourCompanyProduct = ourCompanyProduct;
	}

	/**
	 * @return the periodState
	 */
	public String getPeriodState() {
		return periodState;
	}

	/**
	 * @param periodState the periodState to set
	 */
	public void setPeriodState(String periodState) {
		this.periodState = periodState;
	}

	public String getWinninName() {
		return winninName;
	}

	public void setWinninName(String winninName) {
		this.winninName = winninName;
	}

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	/**
	 * @return the createdDateString
	 */
	public String getCreatedDateString() {
		return createdDateString;
	}

	/**
	 * @param createdDateString the createdDateString to set
	 */
	public void setCreatedDateString(String createdDateString) {
		this.createdDateString = createdDateString;
	}

	/**
	 * @return the winninNameString
	 */
	public String getWinninNameString() {
		return winninNameString;
	}

	/**
	 * @param winninNameString the winninNameString to set
	 */
	public void setWinninNameString(String winninNameString) {
		this.winninNameString = winninNameString;
	}

	/**
	 * @return the effectiveDate
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @param effectiveDate the effectiveDate to set
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getWinninDesc() {
		return winninDesc;
	}

	public void setWinninDesc(String winninDesc) {
		this.winninDesc = winninDesc;
	}
	
	
}