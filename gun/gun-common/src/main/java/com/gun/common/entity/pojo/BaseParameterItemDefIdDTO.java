package com.gun.common.entity.pojo;

public class BaseParameterItemDefIdDTO implements java.io.Serializable {


  private static final long serialVersionUID = 4301199891725454399L;
  
  private String bpidId;
  private String bptdCode;
  private String effectiveDate;

  public BaseParameterItemDefIdDTO() {
  }

  public BaseParameterItemDefIdDTO(String bpidId, String bptdCode, String effectiveDate) {
    this.bpidId = bpidId;
    this.bptdCode = bptdCode;
    this.effectiveDate = effectiveDate;
  }

  public String getBpidId() {
    return this.bpidId;
  }

  public void setBpidId(String bpidId) {
    this.bpidId = bpidId;
  }
  public String getBptdCode() {
    return this.bptdCode;
  }

  public void setBptdCode(String bptdCode) {
    this.bptdCode = bptdCode;
  }
  public String getEffectiveDate() {
    return this.effectiveDate;
  }

  public void setEffectiveDate(String effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public boolean equals(Object other) {
    if ((this == other))
      return true;
    if ((other == null))
      return false;
    if (!(other instanceof BaseParameterItemDefIdDTO))
      return false;
    BaseParameterItemDefIdDTO castOther = (BaseParameterItemDefIdDTO) other;

    return ((this.getBpidId() == castOther.getBpidId())
            || (this.getBpidId() != null && castOther.getBpidId() != null && this.getBpidId().equals(castOther.getBpidId())))
            && ((this.getBptdCode() == castOther.getBptdCode())
                    || (this.getBptdCode() != null && castOther.getBptdCode() != null && this.getBptdCode().equals(castOther.getBptdCode())))
            && ((this.getEffectiveDate() == castOther.getEffectiveDate()) || (this.getEffectiveDate() != null && castOther.getEffectiveDate() != null
                    && this.getEffectiveDate().equals(castOther.getEffectiveDate())));
  }

  public int hashCode() {
    int result = 17;

    result = 37 * result + (getBpidId() == null ? 0 : this.getBpidId().hashCode());
    result = 37 * result + (getBptdCode() == null ? 0 : this.getBptdCode().hashCode());
    result = 37 * result + (getEffectiveDate() == null ? 0 : this.getEffectiveDate().hashCode());
    return result;
  }

}
