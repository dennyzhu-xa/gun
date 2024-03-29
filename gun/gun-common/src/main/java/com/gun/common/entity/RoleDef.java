package com.gun.common.entity;
// Generated 2017-6-1 18:10:41 by Hibernate Tools 4.0.1.Final

import com.gun.common.pojo.ValueObject;

/**
 * RoleDef generated by hbm2java
 */
public class RoleDef extends ValueObject<Integer> {

  private static final long serialVersionUID = 6668942054305396679L;
  
  private Integer roleId;
  private String roleName;
  

  public RoleDef() {
  }


  
  /**
   * Constructor:
   */
  public RoleDef(Integer roleId, String roleName) {
    this.roleId = roleId;
    this.roleName = roleName;
  }



  /**
   * @return the roleId
   */
  public Integer getRoleId() {
    return roleId;
  }


  
  /**
   * @param roleId the roleId to set
   */
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
