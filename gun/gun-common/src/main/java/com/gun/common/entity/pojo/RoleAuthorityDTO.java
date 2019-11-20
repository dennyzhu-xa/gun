package com.gun.common.entity.pojo;
// Generated 2017-6-1 18:10:41 by Hibernate Tools 4.0.1.Final

import com.gun.common.pojo.ValueObject;

/**
 * RoleAuthority generated by hbm2java
 */
public class RoleAuthorityDTO extends ValueObject<Integer> {

  private static final long serialVersionUID = 6668942054305396679L;
  
  public static enum ATTRIBUTE {
    FUNCTION_ID("functionId"),
    ROLE_ID("roleId")
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
  private Integer roleId;
  private String roleName;

  public RoleAuthorityDTO() {
  }

  /**
   * Constructor:
   */
  public RoleAuthorityDTO(String functionId, Integer roleId) {
    this.functionId = functionId;
    this.roleId = roleId;
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

  public Integer getRoleId() {
    return this.roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  
  /**
   * @return the roleName
   */
  public String getRoleName() {
    return roleName;
  }

  
  /**
   * @param roleName the roleName to set
   */
  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

}