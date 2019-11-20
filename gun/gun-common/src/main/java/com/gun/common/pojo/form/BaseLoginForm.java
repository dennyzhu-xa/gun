package com.gun.common.pojo.form;

import com.gun.common.pojo.ValueObject;

/**
 * Purpose: 基本登入表單資訊
 * @author akumadeng
 * @since  JDK 1.5
 * @date   2015/1/22
 * @MaintenancePersonnel akumadeng
 */
public class BaseLoginForm extends ValueObject<String>{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3133753499314382837L;
	
	private String aliasName;			
	private String passWord;
	private String domainName;
	private String userName;
	private String serverName;
	private String functionCode;
	
	private String oldPassword;
	private String newPassword;
	private String isLogin;
	
	private String inputRand;
	private String userAccount;
	private String isForgotPassword;
  private String locale;
	
	
	public BaseLoginForm(){
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

  public String getPassWord() {
    return passWord;
  }

  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }

  public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	/**
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * @param oldPassword the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

  
  /**
   * @return the isLogin
   */
  public String getIsLogin() {
    return isLogin;
  }

  
  /**
   * @param isLogin the isLogin to set
   */
  public void setIsLogin(String isLogin) {
    this.isLogin = isLogin;
  }

  
  /**
   * @return the inputRand
   */
  public String getInputRand() {
    return inputRand;
  }

  
  /**
   * @param inputRand the inputRand to set
   */
  public void setInputRand(String inputRand) {
    this.inputRand = inputRand;
  }

  
  /**
   * @return the userAccount
   */
  public String getUserAccount() {
    return userAccount;
  }

  
  /**
   * @param userAccount the userAccount to set
   */
  public void setUserAccount(String userAccount) {
    this.userAccount = userAccount;
  }

  
  /**
   * @return the isForgotPassword
   */
  public String getIsForgotPassword() {
    return isForgotPassword;
  }

  
  /**
   * @param isForgotPassword the isForgotPassword to set
   */
  public void setIsForgotPassword(String isForgotPassword) {
    this.isForgotPassword = isForgotPassword;
  }

  
  /**
   * @return the locale
   */
  public String getLocale() {
    return locale;
  }

  
  /**
   * @param locale the locale to set
   */
  public void setLocale(String locale) {
    this.locale = locale;
  }
	
	
}
