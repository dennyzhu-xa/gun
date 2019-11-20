package com.gun.common.pojo;

import java.io.Serializable;


/**
 * @author Felixli
 * @since  JDK 1.7
 * @date   2017/4/5
 * @maintenance Felixli
 */
public abstract class ValueObject<T extends Serializable> implements Serializable {
		/**
   * 
   */
  private static final long serialVersionUID = 5988943650332166885L;
    private T id;
		public ValueObject() {
			
		}
		public ValueObject(T id) {
			this.id = id;
		}
		public T getId() {
			return this.id;
		}
		public void setId(T id) {
			this.id = id;
		}
}
