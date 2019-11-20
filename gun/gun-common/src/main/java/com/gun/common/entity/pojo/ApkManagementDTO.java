package com.gun.common.entity.pojo;
// Generated 2017-6-1 18:10:41 by Hibernate Tools 4.0.1.Final

import java.util.Date;

import com.gun.common.pojo.ValueObject;

/**
 * SysUserInfo generated by hbm2java
 */
public class ApkManagementDTO extends ValueObject<Integer> {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -1816726165933490456L;

	//枚举
	public static enum ATTRIBUTE {
		ID("id"),
		APP_NAME("appName"),
		PACKAGE_NAME("packageName"),
		VERSION_CODE("versionCode"),
		VERSION_NAME("versionName"),
		MIN_SDK_VERSION("minSDKVersion"),
		RELEASE_TIME("releaseTime"),
		DOWNLOAD("download"),
		FILESIZE("filesize"),
		DETELE_TYPE("deteleType"),
		CONTEXT("context")
	    ;
	    private String value;
	    ATTRIBUTE(String value) {
	      this.value = value;
	    };
	    public String getValue() {
	      return this.value;
	    }
	};
	
	private String appName;
	private String packageName;
	private String versionCode;
	private String versionName;
	private String minSDKVersion;
	private Date releaseTime;
	private String download;
	private String filesize;
	private String deteleType;
	private String context;
	
	/**
	 * Constructor:无参构造器
	 */
	public ApkManagementDTO() {
		
	}

	/**
	 * Constructor:有参构造
	 */
	public ApkManagementDTO(String appName, String packageName,
			String versionCode, String versionName, String minSDKVersion,
			Date releaseTime, String download, String filesize,
			String deteleType, String context) {
		super();
		this.appName = appName;
		this.packageName = packageName;
		this.versionCode = versionCode;
		this.versionName = versionName;
		this.minSDKVersion = minSDKVersion;
		this.releaseTime = releaseTime;
		this.download = download;
		this.filesize = filesize;
		this.deteleType = deteleType;
		this.context = context;
	}

	/**
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * @param appName the appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * @return the versionCode
	 */
	public String getVersionCode() {
		return versionCode;
	}

	/**
	 * @param versionCode the versionCode to set
	 */
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	/**
	 * @return the versionName
	 */
	public String getVersionName() {
		return versionName;
	}

	/**
	 * @param versionName the versionName to set
	 */
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	/**
	 * @return the minSDKVersion
	 */
	public String getMinSDKVersion() {
		return minSDKVersion;
	}

	/**
	 * @param minSDKVersion the minSDKVersion to set
	 */
	public void setMinSDKVersion(String minSDKVersion) {
		this.minSDKVersion = minSDKVersion;
	}

	/**
	 * @return the releaseTime
	 */
	public Date getReleaseTime() {
		return releaseTime;
	}

	/**
	 * @param releaseTime the releaseTime to set
	 */
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	/**
	 * @return the download
	 */
	public String getDownload() {
		return download;
	}

	/**
	 * @param download the download to set
	 */
	public void setDownload(String download) {
		this.download = download;
	}

	/**
	 * @return the filesize
	 */
	public String getFilesize() {
		return filesize;
	}

	/**
	 * @param filesize the filesize to set
	 */
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	/**
	 * @return the deteleType
	 */
	public String getDeteleType() {
		return deteleType;
	}

	/**
	 * @param deteleType the deteleType to set
	 */
	public void setDeteleType(String deteleType) {
		this.deteleType = deteleType;
	}

	/**
	 * @return the context
	 */
	public String getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(String context) {
		this.context = context;
	}
	
}