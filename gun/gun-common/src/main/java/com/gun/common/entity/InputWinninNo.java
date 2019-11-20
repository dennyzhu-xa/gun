package com.gun.common.entity;
// Generated 2017/7/3 �U�� 06:04:37 by Hibernate Tools 4.0.0.Final

import java.math.BigDecimal;
import java.util.Date;

import com.gun.common.pojo.ValueObject;

/**
 * InputWinninNo generated by hbm2java
 */
public class InputWinninNo extends ValueObject<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private String winninId;
	private String winninPeriod;
	private int winninGrade;
	private String winninName;
	private String winninNameString;
	private String winninNumber;
	private BigDecimal winninSum;
	private String createById;
	private String createdByName;
	private Date createdDate;
	private String updatedById;
	private String updatedByName;
	private Date updatedDate;
	private String periodId;
	private String ourCompanyProduct;
	private String effectiveDate;
	
	public InputWinninNo() {
	}

	public InputWinninNo(String winninId) {
		this.winninId = winninId;
	}

	public InputWinninNo(String winninId, String winninPeriod, int winninGrade, String winninNumber,String winninNameString,
			BigDecimal winninSum, String createById, String createdByName, Date createdDate, String updatedById,String effectiveDate,
			String updatedByName, Date updatedDate,String periodId,String ourCompanyProduct,String winninName) {
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
		this.periodId = periodId;
		this.ourCompanyProduct = ourCompanyProduct;
		this.winninName = winninName;
		this.winninNameString = winninNameString;
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

	public String getWinninName() {
		return winninName;
	}

	public void setWinninName(String winninName) {
		this.winninName = winninName;
	}

	public String getWinninNameString() {
		return winninNameString;
	}

	public void setWinninNameString(String winninNameString) {
		this.winninNameString = winninNameString;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	

}