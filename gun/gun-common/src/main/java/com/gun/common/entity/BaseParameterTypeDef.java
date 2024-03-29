package com.gun.common.entity;
// Generated 2017-11-28 16:57:12 by Hibernate Tools 4.0.1.Final

import java.util.Date;

import com.gun.common.pojo.ValueObject;

/**
 * BaseParameterTypeDef generated by hbm2java
 */
public class BaseParameterTypeDef extends ValueObject<BaseParameterTypeDefId> {

  private static final long serialVersionUID = 5352898766961687819L;
  
  private BaseParameterTypeDefId id;
  private String ptName;
  private String isTree;
  private String ptDesc;
  private String variableType;
  private String valueScopeOperator1;
  private String valueScopeOperator2;
  private Integer dataType;
  private Integer ptLength;
  private String approvedFlag;
  private String createdById;
  private String createdByName;
  private Date createdDate;
  private String updatedById;
  private String updatedByName;
  private Date updatedDate;
  private String isNeedApprovedByMgr;
  private String mgrApprovedById;
  private String mgrApprovedByName;
  private Date mgrApprovedDate;

  public BaseParameterTypeDef() {
  }

  public BaseParameterTypeDef(BaseParameterTypeDefId id, String ptName) {
    this.id = id;
    this.ptName = ptName;
  }
  public BaseParameterTypeDef(BaseParameterTypeDefId id, String ptName, String isTree, String ptDesc, String variableType, String valueScopeOperator1,
          String valueScopeOperator2, Integer dataType, Integer ptLength, String approvedFlag, String createdById, String createdByName, Date createdDate,
          String updatedById, String updatedByName, Date updatedDate, String isNeedApprovedByMgr, String mgrApprovedById, String mgrApprovedByName,
          Date mgrApprovedDate) {
    this.id = id;
    this.ptName = ptName;
    this.isTree = isTree;
    this.ptDesc = ptDesc;
    this.variableType = variableType;
    this.valueScopeOperator1 = valueScopeOperator1;
    this.valueScopeOperator2 = valueScopeOperator2;
    this.dataType = dataType;
    this.ptLength = ptLength;
    this.approvedFlag = approvedFlag;
    this.createdById = createdById;
    this.createdByName = createdByName;
    this.createdDate = createdDate;
    this.updatedById = updatedById;
    this.updatedByName = updatedByName;
    this.updatedDate = updatedDate;
    this.isNeedApprovedByMgr = isNeedApprovedByMgr;
    this.mgrApprovedById = mgrApprovedById;
    this.mgrApprovedByName = mgrApprovedByName;
    this.mgrApprovedDate = mgrApprovedDate;
  }

  public BaseParameterTypeDefId getId() {
    return this.id;
  }

  public void setId(BaseParameterTypeDefId id) {
    this.id = id;
  }
  public String getPtName() {
    return this.ptName;
  }

  public void setPtName(String ptName) {
    this.ptName = ptName;
  }
  public String getIsTree() {
    return this.isTree;
  }

  public void setIsTree(String isTree) {
    this.isTree = isTree;
  }
  public String getPtDesc() {
    return this.ptDesc;
  }

  public void setPtDesc(String ptDesc) {
    this.ptDesc = ptDesc;
  }
  public String getVariableType() {
    return this.variableType;
  }

  public void setVariableType(String variableType) {
    this.variableType = variableType;
  }
  public String getValueScopeOperator1() {
    return this.valueScopeOperator1;
  }

  public void setValueScopeOperator1(String valueScopeOperator1) {
    this.valueScopeOperator1 = valueScopeOperator1;
  }
  public String getValueScopeOperator2() {
    return this.valueScopeOperator2;
  }

  public void setValueScopeOperator2(String valueScopeOperator2) {
    this.valueScopeOperator2 = valueScopeOperator2;
  }
  public Integer getDataType() {
    return this.dataType;
  }

  public void setDataType(Integer dataType) {
    this.dataType = dataType;
  }
  public Integer getPtLength() {
    return this.ptLength;
  }

  public void setPtLength(Integer ptLength) {
    this.ptLength = ptLength;
  }
  public String getApprovedFlag() {
    return this.approvedFlag;
  }

  public void setApprovedFlag(String approvedFlag) {
    this.approvedFlag = approvedFlag;
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
  public String getIsNeedApprovedByMgr() {
    return this.isNeedApprovedByMgr;
  }

  public void setIsNeedApprovedByMgr(String isNeedApprovedByMgr) {
    this.isNeedApprovedByMgr = isNeedApprovedByMgr;
  }
  public String getMgrApprovedById() {
    return this.mgrApprovedById;
  }

  public void setMgrApprovedById(String mgrApprovedById) {
    this.mgrApprovedById = mgrApprovedById;
  }
  public String getMgrApprovedByName() {
    return this.mgrApprovedByName;
  }

  public void setMgrApprovedByName(String mgrApprovedByName) {
    this.mgrApprovedByName = mgrApprovedByName;
  }
  public Date getMgrApprovedDate() {
    return this.mgrApprovedDate;
  }

  public void setMgrApprovedDate(Date mgrApprovedDate) {
    this.mgrApprovedDate = mgrApprovedDate;
  }

}
