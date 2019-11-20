package com.gun.common.entity.pojo;

import com.gun.common.pojo.ValueObject;

/**
 * 
 * @author felixli
 *
 */
public class RoleDefDTO extends ValueObject<Integer> {

  private static final long serialVersionUID = 6668942054305396679L;
  
  public static enum ATTRIBUTE {
    ROLE_NAME("roleName"),
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
  
  private Integer roleId;
  private String roleName;
  

  public RoleDefDTO() {
  }


  
  /**
   * Constructor:
   */
  public RoleDefDTO(Integer roleId, String roleName) {
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
