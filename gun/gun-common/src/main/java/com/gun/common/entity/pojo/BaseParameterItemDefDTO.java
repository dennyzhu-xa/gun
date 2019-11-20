package com.gun.common.entity.pojo;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.gun.common.pojo.ValueObject;

public class BaseParameterItemDefDTO extends ValueObject<String> {

  private static final long serialVersionUID = 2947111276172573325L;
  
  private String bpidId;
  private String bptdCode;
  private String effectiveDate;
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
  private CommonsMultipartFile categoryImage;
  private byte[] imgField1;
  private byte[] imgField2;
  private String imgFlag;
  private String parentBpidId;
  private String ptName;
  private Double  winningAmount;
  private Integer numberOfWinners;
  private String updatedById;
  private String updatedByName;
  private Date updatedDate;

  public BaseParameterItemDefDTO() {
  }



  /**
   * Constructor:
   */
  public BaseParameterItemDefDTO(String bpidId, String bptdCode, String effectiveDate, String expirationDate, String itemName, String itemValue,
          String referenceCode, String itemDesc, Integer itemOrder, Integer itemDepth, String approvedFlag, String textField1, String textField2,
          String textField3, String textField4, String textField5, BigDecimal numberField1, BigDecimal numberField2, CommonsMultipartFile categoryImage,
          byte[] imgField1, byte[] imgField2, String parentBpidId, String ptName, Double winningAmount, Integer numberOfWinners, String updatedById,
          String updatedByName, Date updatedDate) {
    super();
    this.bpidId = bpidId;
    this.bptdCode = bptdCode;
    this.effectiveDate = effectiveDate;
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
    this.categoryImage = categoryImage;
    this.imgField1 = imgField1;
    this.imgField2 = imgField2;
    this.parentBpidId = parentBpidId;
    this.ptName = ptName;
    this.winningAmount = winningAmount;
    this.numberOfWinners = numberOfWinners;
    this.updatedById = updatedById;
    this.updatedByName = updatedByName;
    this.updatedDate = updatedDate;
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


  
  public String getBpidId() {
    return bpidId;
  }


  
  public void setBpidId(String bpidId) {
    this.bpidId = bpidId;
  }


  
  public String getBptdCode() {
    return bptdCode;
  }


  
  public void setBptdCode(String bptdCode) {
    this.bptdCode = bptdCode;
  }


  
  public String getEffectiveDate() {
    return effectiveDate;
  }


  
  public void setEffectiveDate(String effectiveDate) {
    this.effectiveDate = effectiveDate;
  }


  
  public String getPtName() {
    return ptName;
  }


  
  public void setPtName(String ptName) {
    this.ptName = ptName;
  }


  
  public String getExpirationDate() {
    return expirationDate;
  }


  
  public void setExpirationDate(String expirationDate) {
    this.expirationDate = expirationDate;
  }


  
  public CommonsMultipartFile getCategoryImage() {
    return categoryImage;
  }


  
  public void setCategoryImage(CommonsMultipartFile categoryImage) {
    this.categoryImage = categoryImage;
  }


  
  public Double getWinningAmount() {
    return winningAmount;
  }


  
  public void setWinningAmount(Double winningAmount) {
    this.winningAmount = winningAmount;
  }


  
  public Integer getNumberOfWinners() {
    return numberOfWinners;
  }


  
  public void setNumberOfWinners(Integer numberOfWinners) {
    this.numberOfWinners = numberOfWinners;
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



  
  public Date getUpdatedDate() {
    return updatedDate;
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
