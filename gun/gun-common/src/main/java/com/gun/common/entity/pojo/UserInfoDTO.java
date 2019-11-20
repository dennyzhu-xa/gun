package com.gun.common.entity.pojo;
// Generated 2017-6-1 18:10:41 by Hibernate Tools 4.0.1.Final

import java.util.Date;

import com.gun.common.pojo.ValueObject;

public class UserInfoDTO extends ValueObject<Integer> {

  private static final long serialVersionUID = -2821021314409889777L;
  
  public static enum ATTRIBUTE {
    ID("id"),
    USER_ID("userId"),
    USER_NAME("userName"),
    EMAIL("email"),
    PASSWORD("password"),
    ROLE_ID("roleId"),
    CREATE_BY_ID("createById"),
    CREATED_BY_NAME("createdByName"),
    CREATED_DATE("createdDate"),
    UPDATED_BYID("updatedById"),
    UPDATED_BY_NAME("updatedByName"),
    UPDATED_DATE("updatedDate")
    ;
    
    private String value;
    ATTRIBUTE(String value) {
      this.value = value;
    };
    public String getValue() {
      return this.value;
    }
  };
  private String userId;
  private String userName;
  private String email;
  private String password;
  private String roleId;
  private String createById;
  private String createdByName;
  private Date createdDate;
  private String updatedById;
  private String updatedByName;
  private Date updatedDate;
  private String roleName;

  public UserInfoDTO() {
  }

  public UserInfoDTO(String userId, String userName, String password, String roleId) {
    this.userId = userId;
    this.userName = userName;
    this.password = password;
    this.roleId = roleId;
  }
  public UserInfoDTO(String userId, String userName, String email,  String password, String roleId, 
          String createById, String createdByName, Date createdDate, String updatedById, String updatedByName, Date updatedDate) {
    this.userId = userId;
    this.userName = userName;
    this.email = email;
    this.password = password;
    this.roleId = roleId;
    this.createById = createById;
    this.createdByName = createdByName;
    this.createdDate = createdDate;
    this.updatedById = updatedById;
    this.updatedByName = updatedByName;
    this.updatedDate = updatedDate;
  }

  public String getUserId() {
    return this.userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  public String getRoleId() {
    return this.roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }
  public String getCreateById() {
    return this.createById;
  }

  public void setCreateById(String createById) {
    this.createById = createById;
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

  public String getRoleName() {
    return roleName;
  }
  
  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

}
