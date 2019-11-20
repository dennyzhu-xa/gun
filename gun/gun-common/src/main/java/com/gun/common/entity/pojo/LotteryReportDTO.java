package com.gun.common.entity.pojo;

import java.util.Date;
import java.util.List;

/**
 * @author felixli
 *
 */
public class LotteryReportDTO {
//枚举
  public static enum ATTRIBUTE {
    PERIODS("periods"),
    SOLD_NUMBER("soldNumber"),
    NOT_FORSALE_NUMBER("notForsaleNumber"),
    ADDRESS("address")
      ;
      private String value;
      ATTRIBUTE(String value) {
        this.value = value;
      };
      public String getValue() {
        return this.value;
      }
  };
  /**
   * 已售出彩票数
   */
  private int soldNumber; 
  /**
   * 未售出彩票数
   */
  private int notForsaleNumber;
  /**
   * 彩票单价
   */
  private int lotterySalePrice;
  private String category;
  private String subCategory;
  private String lotteryNumber;
  private String purchaser;
  private Date purchaserDate;
  private String telephone;
  private String mail;
  private String iDCard;
  private String sex;
  private String address;
  private String flag;
  private String deleteflag;
  private Date deleteDate;
  private String deleteStatus;
  
  
  private List<LotteryReportDTO>  soldList;
  private List<LotteryReportDTO>  unsoldList;
  
  /**
   * @return the soldNumber
   */
  public int getSoldNumber() {
    return soldNumber;
  }
  
  /**
   * @param soldNumber the soldNumber to set
   */
  public void setSoldNumber(int soldNumber) {
    this.soldNumber = soldNumber;
  }
  
  /**
   * @return the notForsaleNumber
   */
  public int getNotForsaleNumber() {
    return notForsaleNumber;
  }
  
  /**
   * @param notForsaleNumber the notForsaleNumber to set
   */
  public void setNotForsaleNumber(int notForsaleNumber) {
    this.notForsaleNumber = notForsaleNumber;
  }

  
  /**
   * @return the category
   */
  public String getCategory() {
    return category;
  }

  
  /**
   * @param category the category to set
   */
  public void setCategory(String category) {
    this.category = category;
  }

  
  /**
   * @return the subCategory
   */
  public String getSubCategory() {
    return subCategory;
  }

  
  /**
   * @param subCategory the subCategory to set
   */
  public void setSubCategory(String subCategory) {
    this.subCategory = subCategory;
  }

  
  /**
   * @return the lotteryNumber
   */
  public String getLotteryNumber() {
    return lotteryNumber;
  }

  
  /**
   * @param lotteryNumber the lotteryNumber to set
   */
  public void setLotteryNumber(String lotteryNumber) {
    this.lotteryNumber = lotteryNumber;
  }

  
  /**
   * @return the soldList
   */
  public List<LotteryReportDTO> getSoldList() {
    return soldList;
  }

  
  /**
   * @param soldList the soldList to set
   */
  public void setSoldList(List<LotteryReportDTO> soldList) {
    this.soldList = soldList;
  }

  
  /**
   * @return the unsoldList
   */
  public List<LotteryReportDTO> getUnsoldList() {
    return unsoldList;
  }

  
  /**
   * @param unsoldList the unsoldList to set
   */
  public void setUnsoldList(List<LotteryReportDTO> unsoldList) {
    this.unsoldList = unsoldList;
  }

  
  /**
   * @return the purchaser
   */
  public String getPurchaser() {
    return purchaser;
  }

  
  /**
   * @param purchaser the purchaser to set
   */
  public void setPurchaser(String purchaser) {
    this.purchaser = purchaser;
  }

  
  /**
   * @return the purchaserDate
   */
  public Date getPurchaserDate() {
    return purchaserDate;
  }

  
  /**
   * @param purchaserDate the purchaserDate to set
   */
  public void setPurchaserDate(Date purchaserDate) {
    this.purchaserDate = purchaserDate;
  }

  
  /**
   * @return the telephone
   */
  public String getTelephone() {
    return telephone;
  }

  
  /**
   * @param telephone the telephone to set
   */
  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  
  /**
   * @return the mail
   */
  public String getMail() {
    return mail;
  }

  
  /**
   * @param mail the mail to set
   */
  public void setMail(String mail) {
    this.mail = mail;
  }

  
  /**
   * @return the iDCard
   */
  public String getiDCard() {
    return iDCard;
  }

  
  /**
   * @param iDCard the iDCard to set
   */
  public void setiDCard(String iDCard) {
    this.iDCard = iDCard;
  }

  
  /**
   * @return the sex
   */
  public String getSex() {
    return sex;
  }

  
  /**
   * @param sex the sex to set
   */
  public void setSex(String sex) {
    this.sex = sex;
  }

  
  /**
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  
  /**
   * @param address the address to set
   */
  public void setAddress(String address) {
    this.address = address;
  }

/**
 * @return the flag
 */
public final String getFlag() {
	return flag;
}

/**
 * @param flag the flag to set
 */
public final void setFlag(String flag) {
	this.flag = flag;
}

public int getLotterySalePrice() {
	return lotterySalePrice;
}

public void setLotterySalePrice(int lotterySalePrice) {
	this.lotterySalePrice = lotterySalePrice;
}

public String getDeleteflag() {
	return deleteflag;
}

public void setDeleteflag(String deleteflag) {
	this.deleteflag = deleteflag;
}

public Date getDeleteDate() {
	return deleteDate;
}

public void setDeleteDate(Date deleteDate) {
	this.deleteDate = deleteDate;
}

public String getDeleteStatus() {
	return deleteStatus;
}

public void setDeleteStatus(String deleteStatus) {
	this.deleteStatus = deleteStatus;
}


}
