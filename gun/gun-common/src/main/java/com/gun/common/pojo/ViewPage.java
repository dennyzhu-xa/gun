package com.gun.common.pojo;

/**
 * @author Edward Yen
 * @since  JDK 1.5
 * @date   2014/7/25
 * @maintenance Edward Yen
 */
public class ViewPage {
	private String defaultPage;
	private String successfulPage;
	private String failedPage;
	/**
	 * ViewPage建構子
	 */
	public ViewPage() {
	}
	/**
	 * @return the defaultPage
	 */
	public String getDefaultPage() {
		return defaultPage;
	}
	/**
	 * @param defaultPage the defaultPage to set
	 */
	public void setDefaultPage(String defaultPage) {
		this.defaultPage = defaultPage;
	}
	/**
	 * @return the successfulPage
	 */
	public String getSuccessfulPage() {
		return successfulPage;
	}
	/**
	 * @param successfulPage the successfulPage to set
	 */
	public void setSuccessfulPage(String successfulPage) {
		this.successfulPage = successfulPage;
	}
	/**
	 * @return the failedPage
	 */
	public String getFailedPage() {
		return failedPage;
	}
	/**
	 * @param failedPage the failedPage to set
	 */
	public void setFailedPage(String failedPage) {
		this.failedPage = failedPage;
	}

}
