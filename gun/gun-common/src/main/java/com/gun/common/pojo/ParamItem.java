package com.gun.common.pojo;

import java.io.Serializable;

/**
 * @author Felixli
 * @since  JDK 1.5
 * @date   2017/3/22
 * @maintenance Felixli
 */
public class ParamItem implements Serializable {
	/**
   * 
   */
  private static final long serialVersionUID = 5644549310197897977L;
  /**
	 * ParamItem建構子
	 */
	public ParamItem() {
		super();
	}
	/**
	 * ParamItem建構子
	 * @param name
	 * @param value
	 */
	public ParamItem(String name, Object value) {
		super();
		this.name = name;
		this.value = value;
	}
	private String name;
	private Object value;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

}
