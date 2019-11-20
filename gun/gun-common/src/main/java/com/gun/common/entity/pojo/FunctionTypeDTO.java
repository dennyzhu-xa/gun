package com.gun.common.entity.pojo;

import com.gun.common.pojo.ValueObject;

/**
 * @author felixli
 *
 */
public class FunctionTypeDTO extends ValueObject<String>{

  /**
   * 
   */
  private static final long serialVersionUID = 7579357554838042442L;
  
  
  public static enum ATTRIBUTE {
    FUNCTION_ID("functionId"),
    FUNCTION_NAME("functionName"),
    FUNCTION_CODE("functionCode"),
    FUNCTION_DESCRIPTION("functionDescription"),
    FUNCTION_URL("functionUrl"),
    BUTTONS("buttons"),
    PARENT_FUNCTION_ID("parentFunctionId"),
    FUNCTION_ORDER("functionOrder"),
    CREATED_BY_ID("createdById"),
    CREATED_BY_NAME("createdByName"),
    CREATED_DATE("createdDate"),
    UPDATED_BYID("updatedById"),
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
  
  
  
  /**
   * Constructor:
   */
  public FunctionTypeDTO() {
  }





  
  /**
   * Constructor:
   */
  public FunctionTypeDTO(String functionId, String functionName, String functionCode, String functionDescription, String functionUrl, String buttons,
          String parentFunctionId, Integer functionOrder, String createdById, String createdByName, String createdDate,
          String updatedById, String updatedByName, String updatedDate) {
    super();
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






  /**
   * @return the functionId
   */
  public String getFunctionId() {
    return functionId;
  }



  
  /**
   * @param functionId the functionId to set
   */
  public void setFunctionId(String functionId) {
    this.functionId = functionId;
  }



  
  /**
   * @return the functionName
   */
  public String getFunctionName() {
    return functionName;
  }



  
  /**
   * @param functionName the functionName to set
   */
  public void setFunctionName(String functionName) {
    this.functionName = functionName;
  }



  
  /**
   * @return the functionCode
   */
  public String getFunctionCode() {
    return functionCode;
  }



  
  /**
   * @param functionCode the functionCode to set
   */
  public void setFunctionCode(String functionCode) {
    this.functionCode = functionCode;
  }



  
  /**
   * @return the functionDescription
   */
  public String getFunctionDescription() {
    return functionDescription;
  }



  
  /**
   * @param functionDescription the functionDescription to set
   */
  public void setFunctionDescription(String functionDescription) {
    this.functionDescription = functionDescription;
  }



  
  /**
   * @return the functionUrl
   */
  public String getFunctionUrl() {
    return functionUrl;
  }



  
  /**
   * @param functionUrl the functionUrl to set
   */
  public void setFunctionUrl(String functionUrl) {
    this.functionUrl = functionUrl;
  }



  
  /**
   * @return the parentFunctionId
   */
  public String getParentFunctionId() {
    return parentFunctionId;
  }



  
  /**
   * @param parentFunctionId the parentFunctionId to set
   */
  public void setParentFunctionId(String parentFunctionId) {
    this.parentFunctionId = parentFunctionId;
  }



  
  
  /**
   * @return the functionOrder
   */
  public Integer getFunctionOrder() {
    return functionOrder;
  }



  
  /**
   * @param functionOrder the functionOrder to set
   */
  public void setFunctionOrder(Integer functionOrder) {
    this.functionOrder = functionOrder;
  }



  
  /**
   * @return the createdById
   */
  public String getCreatedById() {
    return createdById;
  }



  
  /**
   * @param createdById the createdById to set
   */
  public void setCreatedById(String createdById) {
    this.createdById = createdById;
  }



  
  /**
   * @return the createdByName
   */
  public String getCreatedByName() {
    return createdByName;
  }



  
  /**
   * @param createdByName the createdByName to set
   */
  public void setCreatedByName(String createdByName) {
    this.createdByName = createdByName;
  }



  
  /**
   * @return the createdDate
   */
  public String getCreatedDate() {
    return createdDate;
  }



  
  /**
   * @param createdDate the createdDate to set
   */
  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }



  
  /**
   * @return the updatedById
   */
  public String getUpdatedById() {
    return updatedById;
  }



  
  /**
   * @param updatedById the updatedById to set
   */
  public void setUpdatedById(String updatedById) {
    this.updatedById = updatedById;
  }



  
  /**
   * @return the updatedByName
   */
  public String getUpdatedByName() {
    return updatedByName;
  }



  
  /**
   * @param updatedByName the updatedByName to set
   */
  public void setUpdatedByName(String updatedByName) {
    this.updatedByName = updatedByName;
  }



  
  /**
   * @return the updatedDate
   */
  public String getUpdatedDate() {
    return updatedDate;
  }



  
  /**
   * @param updatedDate the updatedDate to set
   */
  public void setUpdatedDate(String updatedDate) {
    this.updatedDate = updatedDate;
  }


  
  /**
   * @return the buttons
   */
  public String getButtons() {
    return buttons;
  }


  
  /**
   * @param buttons the buttons to set
   */
  public void setButtons(String buttons) {
    this.buttons = buttons;
  }
  
  
}
