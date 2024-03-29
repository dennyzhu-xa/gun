package com.gun.common.entity;
// Generated 2017-11-28 16:57:12 by Hibernate Tools 4.0.1.Final

import java.math.BigDecimal;
import java.util.Date;

import com.gun.common.pojo.ValueObject;

/**
 * BaseParameterItemDef generated by hbm2java
 */
public class BaseParameterItemDef extends ValueObject<BaseParameterItemDefId> {

  private static final long serialVersionUID = 2947111276172573325L;
  
  private BaseParameterItemDefId id;
  private String expirationDate;
  private String itemName;
  private String itemValue;
  private String referenceCode;
  private String itemDesc;
  private Integer itemOrder;
  private Integer itemDepth;
  private String approvedFlag;
  private String textField1;
  private String textField2;
  private String textField3;
  private String textField4;
  private String textField5;
  private BigDecimal numberField1;
  private BigDecimal numberField2;
  private byte[] imgField1;
  private byte[] imgField2;
  private String imgFlag;
  private String parentBpidId;
  private String updatedById;
  private String updatedByName;
  private Date updatedDate;

  public BaseParameterItemDef() {
  }

  public BaseParameterItemDef(BaseParameterItemDefId id) {
    this.id = id;
  }
  public BaseParameterItemDef(BaseParameterItemDefId id, String expirationDate, String itemName, String itemValue, String referenceCode, String itemDesc,
          Integer itemOrder, Integer itemDepth, String approvedFlag, String textField1, String textField2, String textField3, String textField4,
          String textField5, BigDecimal numberField1, BigDecimal numberField2, byte[] imgField1, byte[] imgField2, String imgFlag, String parentBpidId, String updatedById,
          String updatedByName, Date updatedDate) {
    this.id = id;
    this.expirationDate = expirationDate;
    this.itemName = itemName;
    this.itemValue = itemValue;
    this.referenceCode = referenceCode;
    this.itemDesc = itemDesc;
    this.itemOrder = itemOrder;
    this.itemDepth = itemDepth;
    this.approvedFlag = approvedFlag;
    this.textField1 = textField1;
    this.textField2 = textField2;
    this.textField3 = textField3;
    this.textField4 = textField4;
    this.textField5 = textField5;
    this.numberField1 = numberField1;
    this.numberField2 = numberField2;
    this.imgField1 = imgField1;
    this.imgField2 = imgField2;
    this.imgFlag = imgFlag;
    this.parentBpidId = parentBpidId;
    this.updatedById = updatedById;
    this.updatedByName = updatedByName;
    this.updatedDate = updatedDate;
  }

  public BaseParameterItemDefId getId() {
    return this.id;
  }

  public void setId(BaseParameterItemDefId id) {
    this.id = id;
  }
  public String getExpirationDate() {
    return this.expirationDate;
  }

  public void setExpirationDate(String expirationDate) {
    this.expirationDate = expirationDate;
  }
  public String getItemName() {
    return this.itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }
  public String getItemValue() {
    return this.itemValue;
  }

  public void setItemValue(String itemValue) {
    this.itemValue = itemValue;
  }
  public String getReferenceCode() {
    return this.referenceCode;
  }

  public void setReferenceCode(String referenceCode) {
    this.referenceCode = referenceCode;
  }
  public String getItemDesc() {
    return this.itemDesc;
  }

  public void setItemDesc(String itemDesc) {
    this.itemDesc = itemDesc;
  }
  public Integer getItemOrder() {
    return this.itemOrder;
  }

  public void setItemOrder(Integer itemOrder) {
    this.itemOrder = itemOrder;
  }
  public Integer getItemDepth() {
    return this.itemDepth;
  }

  public void setItemDepth(Integer itemDepth) {
    this.itemDepth = itemDepth;
  }
  public String getApprovedFlag() {
    return this.approvedFlag;
  }

  public void setApprovedFlag(String approvedFlag) {
    this.approvedFlag = approvedFlag;
  }
  public String getTextField1() {
    return this.textField1;
  }

  public void setTextField1(String textField1) {
    this.textField1 = textField1;
  }
  public String getTextField2() {
    return this.textField2;
  }

  public void setTextField2(String textField2) {
    this.textField2 = textField2;
  }
  public String getTextField3() {
    return this.textField3;
  }

  public void setTextField3(String textField3) {
    this.textField3 = textField3;
  }
  public String getTextField4() {
    return this.textField4;
  }

  public void setTextField4(String textField4) {
    this.textField4 = textField4;
  }
  public String getTextField5() {
    return this.textField5;
  }

  public void setTextField5(String textField5) {
    this.textField5 = textField5;
  }
  public BigDecimal getNumberField1() {
    return this.numberField1;
  }

  public void setNumberField1(BigDecimal numberField1) {
    this.numberField1 = numberField1;
  }
  public BigDecimal getNumberField2() {
    return this.numberField2;
  }

  public void setNumberField2(BigDecimal numberField2) {
    this.numberField2 = numberField2;
  }
  public byte[] getImgField1() {
    return this.imgField1;
  }

  public void setImgField1(byte[] imgField1) {
    this.imgField1 = imgField1;
  }
  public byte[] getImgField2() {
    return this.imgField2;
  }

  public void setImgField2(byte[] imgField2) {
    this.imgField2 = imgField2;
  }
  public String getParentBpidId() {
    return this.parentBpidId;
  }

  public void setParentBpidId(String parentBpidId) {
    this.parentBpidId = parentBpidId;
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
  
  public String getImgFlag() {
    return imgFlag;
  }
  
  public void setImgFlag(String imgFlag) {
    this.imgFlag = imgFlag;
  }
  
}
