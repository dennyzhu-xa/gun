package com.gun.common.pojo.form;

import java.util.List;

import com.gun.common.entity.pojo.SysUserInfoDTO;

/**
 * @author felixli
 *
 */
public class SysUserInfoForm extends SystemForm{

  /**
   * 
   */
  private static final long serialVersionUID = 2280909875640128427L;
  
  public static final String FORM_NAME = "sysUserInfoForm";
  
  private String queryUserAccount;
  private String queryUserName;
  private List<SysUserInfoDTO> results;

  
  
  /**
   * @return the queryUserAccount
   */
  public String getQueryUserAccount() {
    return queryUserAccount;
  }


  
  /**
   * @param queryUserAccount the queryUserAccount to set
   */
  public void setQueryUserAccount(String queryUserAccount) {
    this.queryUserAccount = queryUserAccount;
  }


  
  /**
   * @return the queryUserName
   */
  public String getQueryUserName() {
    return queryUserName;
  }


  
  /**
   * @param queryUserName the queryUserName to set
   */
  public void setQueryUserName(String queryUserName) {
    this.queryUserName = queryUserName;
  }


  /**
   * @return the results
   */
  public List<SysUserInfoDTO> getResults() {
    return results;
  }

  
  /**
   * @param results the results to set
   */
  public void setResults(List<SysUserInfoDTO> results) {
    this.results = results;
  }

  
}
