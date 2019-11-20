package com.gun.common.pojo;

import java.io.Serializable;

/**
 * @author Edward Yen
 * @since  JDK 1.5
 * @date   2014/7/31
 * @maintenance Edward Yen
 */
public interface IUserSessionContext extends Serializable {
	/**
	 * @return id
	 */
	public String getUserId();
	/**
	 * @return the empName
	 */
	public String getEmpName();
	/**
	 * @return the password
	 */
	public String getPassword();
	/**
	 * @return Returns the sessionId.
	 */
	public String getSessionId();
}
