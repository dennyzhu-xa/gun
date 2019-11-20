package com.gun.common.entity;
// Generated 2017-2-17 15:15:22 by Hibernate Tools 4.0.1.Final

import com.gun.common.pojo.ValueObject;

/**
 * FunctionType generated by hbm2java
 */
public class FunctionType extends ValueObject<String> {

	/**
   * 
   */
  private static final long serialVersionUID = -7728880643034612598L;
  private String functionId;
  private String functionName;
  private String functionCode;
  private String functionDescription;
  private String functionUrl;
  private String buttons;
  private String parentFunctionId;
  private Integer functionOrder;
  private String createdById;
  private String createdByName;
  private String createdDate;
  private String updatedById;
  private String updatedByName;
  private String updatedDate;

	public FunctionType() {
	}

	public FunctionType(String functionId) {
		this.functionId = functionId;
	}


  /**
   * Constructor:
   */
  public FunctionType(String functionId, String functionName, String functionCode, String functionDescription, String functionUrl, String buttons,
          String parentFunctionId, Integer functionOrder, String createdById, String createdByName, String createdDate,
          String updatedById, String updatedByName, String updatedDate) {
    this.functionId = functionId;
    this.functionName = functionName;
    this.functionCode = functionCode;
    this.functionDescription = functionDescription;
    this.functionUrl = functionUrl;
    this.buttons = buttons;
    this.parentFunctionId = parentFunctionId;
    this.functionOrder = functionOrder;
    this.createdById = createdById;
    this.createdByName = createdByName;
    this.createdDate = createdDate;
    this.updatedById = updatedById;
    this.updatedByName = updatedByName;
    this.updatedDate = updatedDate;
  }

  public String getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionCode() {
		return this.functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	public String getFunctionDescription() {
		return this.functionDescription;
	}

	public void setFunctionDescription(String functionDescription) {
		this.functionDescription = functionDescription;
	}

	public String getFunctionUrl() {
		return this.functionUrl;
	}

	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}

	public String getParentFunctionId() {
		return this.parentFunctionId;
	}

	public void setParentFunctionId(String parentFunctionId) {
		this.parentFunctionId = parentFunctionId;
	}

	public Integer getFunctionOrder() {
		return this.functionOrder;
	}

	public void setFunctionOrder(Integer functionOrder) {
		this.functionOrder = functionOrder;
	}

	public String getCreatedById() {
		return this.createdById;
	}

	public void setCreatedById(String createdById) {
		this.createdById = createdById;
	}

	public String getCreatedByName() {
		return this.createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public String getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(String createdDate) {
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

	public String getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
  
  public String getButtons() {
    return buttons;
  }
  
  public void setButtons(String buttons) {
    this.buttons = buttons;
  }

}
