package com.gun.common.pojo.form;

import java.util.List;
import java.util.Map;

import com.gun.common.entity.pojo.BaseParameterItemDefDTO;
import com.gun.common.pojo.ParamItem;

/**
 * @author felixli
 *
 */
public class BaseParameterItemForm extends SystemForm{

  
  private static final long serialVersionUID = -2311336470390561177L;
  public static final String FORM_NAME = "baseParameterItemForm";
  public static final String QUERY_EFFECTIVE_DATE = "queryEffectiveDate";
  public static final String QUERY_BPTD_CODE = "queryBptdCode";
  
  private String queryEffectiveDate;
  
  private String queryBptdCode;
  
  private String bptdCode;
  
  private String effectiveDate;
  
  private String editFlag;
  
  private String approvedFlag;
  
  private String queryFlag;

  private List<ParamItem> baseParameterTypeList;
  
  private List<ParamItem> effectiveDateList;
  
  private List<BaseParameterItemDefDTO> results;
  
  private List<BaseParameterItemDefDTO> saveData;
  
  private Map<String,byte[]> map;
  
  /**
   * @return the queryEffectiveDate
   */
  public String getQueryEffectiveDate() {
    return queryEffectiveDate;
  }

  
  /**
   * @param queryEffectiveDate the queryEffectiveDate to set
   */
  public void setQueryEffectiveDate(String queryEffectiveDate) {
    this.queryEffectiveDate = queryEffectiveDate;
  }


  
  /**
   * @return the baseParameterTypeList
   */
  public List<ParamItem> getBaseParameterTypeList() {
    return baseParameterTypeList;
  }


  
  /**
   * @param baseParameterTypeList the baseParameterTypeList to set
   */
  public void setBaseParameterTypeList(List<ParamItem> baseParameterTypeList) {
    this.baseParameterTypeList = baseParameterTypeList;
  }


  
  /**
   * @return the effectiveDateList
   */
  public List<ParamItem> getEffectiveDateList() {
    return effectiveDateList;
  }


  
  /**
   * @param effectiveDateList the effectiveDateList to set
   */
  public void setEffectiveDateList(List<ParamItem> effectiveDateList) {
    this.effectiveDateList = effectiveDateList;
  }


  
  /**
   * @return the results
   */
  public List<BaseParameterItemDefDTO> getResults() {
    return results;
  }


  
  /**
   * @param results the results to set
   */
  public void setResults(List<BaseParameterItemDefDTO> results) {
    this.results = results;
  }


  
  /**
   * @return the editFlag
   */
  public String getEditFlag() {
    return editFlag;
  }


  
  /**
   * @param editFlag the editFlag to set
   */
  public void setEditFlag(String editFlag) {
    this.editFlag = editFlag;
  }


  
  /**
   * @return the saveData
   */
  public List<BaseParameterItemDefDTO> getSaveData() {
    return saveData;
  }


  
  /**
   * @param saveData the saveData to set
   */
  public void setSaveData(List<BaseParameterItemDefDTO> saveData) {
    this.saveData = saveData;
  }


  
  /**
   * @return the bptdCode
   */
  public String getBptdCode() {
    return bptdCode;
  }


  
  /**
   * @param bptdCode the bptdCode to set
   */
  public void setBptdCode(String bptdCode) {
    this.bptdCode = bptdCode;
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


  
  public Map<String, byte[]> getMap() {
    return map;
  }


  
  public void setMap(Map<String, byte[]> map) {
    this.map = map;
  }


  
  public String getApprovedFlag() {
    return approvedFlag;
  }


  
  public void setApprovedFlag(String approvedFlag) {
    this.approvedFlag = approvedFlag;
  }


  
  public String getQueryBptdCode() {
    return queryBptdCode;
  }


  
  public void setQueryBptdCode(String queryBptdCode) {
    this.queryBptdCode = queryBptdCode;
  }


  
  public String getQueryFlag() {
    return queryFlag;
  }


  
  public void setQueryFlag(String queryFlag) {
    this.queryFlag = queryFlag;
  }
  
  
}
