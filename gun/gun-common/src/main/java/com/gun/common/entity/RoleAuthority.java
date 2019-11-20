package com.gun.common.entity;
// Generated 2017-6-1 18:10:41 by Hibernate Tools 4.0.1.Final

import com.gun.common.pojo.ValueObject;

/**
 * RoleAuthority generated by hbm2java
 */
public class RoleAuthority extends ValueObject<Integer> {

  private static final long serialVersionUID = 6668942054305396679L;
  
  private Integer seq;
  private String functionId;
  private Integer roleId;

  public RoleAuthority() {
  }

  public RoleAuthority(Integer seq, String functionId, Integer roleId) {
    this.seq = seq;
    this.functionId = functionId;
    this.roleId = roleId;
  }

  public Integer getSeq() {
    return this.seq;
  }

  public void setSeq(Integer seq) {
    this.seq = seq;
  }
  
  public String getFunctionId() {
    return functionId;
  }

  public void setFunctionId(String functionId) {
    this.functionId = functionId;
  }

  public Integer getRoleId() {
    return this.roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

}