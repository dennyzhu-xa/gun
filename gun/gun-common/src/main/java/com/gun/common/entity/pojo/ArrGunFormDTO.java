package com.gun.common.entity.pojo;

import java.util.List;

public class ArrGunFormDTO {

	// OilWell id
	private Integer wellId;
	// 地区
	private String area;
	// 中接头
	private String connector;
	// 底部零长
	private double extraLength;
	// 甲方
	private String firstParty;
	//射孔枪
	private String[] gun;
	//射孔枪类型
	private String gunModel;
	// 弹型
	private String magazine;
	// 人工井底
	private String manualWell;
	// 油层
	private List<OilLevelDTO> oilLevelDTOList;
	// 相位
	private String phase;
	// 技术说明
	private String remark;
	// 安全枪长度
	private int safeLength;
	// 夹层枪
	private String[] spaceGun;
	//夹层枪类型
	private String spaceModel;
	// 是否使用安全枪
	private boolean useSafety;
	// 井号
	private String wellNo;
	
	public Integer getWellId() {
		return wellId;
	}
	public void setWellId(Integer wellId) {
		this.wellId = wellId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getConnector() {
		return connector;
	}
	public void setConnector(String connector) {
		this.connector = connector;
	}
	public double getExtraLength() {
		return extraLength;
	}
	public void setExtraLength(double extraLength) {
		this.extraLength = extraLength;
	}
	public String getFirstParty() {
		return firstParty;
	}
	public void setFirstParty(String firstParty) {
		this.firstParty = firstParty;
	}
	public String[] getGun() {
		return gun;
	}
	public void setGun(String[] gun) {
		this.gun = gun;
	}
	public String getGunModel() {
		return gunModel;
	}
	public void setGunModel(String gunModel) {
		this.gunModel = gunModel;
	}
	public String getMagazine() {
		return magazine;
	}
	public void setMagazine(String magazine) {
		this.magazine = magazine;
	}
	public String getManualWell() {
		return manualWell;
	}
	public void setManualWell(String manualWell) {
		this.manualWell = manualWell;
	}
	public List<OilLevelDTO> getOilLevelDTOList() {
		return oilLevelDTOList;
	}
	public void setOilLevelDTOList(List<OilLevelDTO> oilLevelDTOList) {
		this.oilLevelDTOList = oilLevelDTOList;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getSafeLength() {
		return safeLength;
	}
	public void setSafeLength(int safeLength) {
		this.safeLength = safeLength;
	}
	public String[] getSpaceGun() {
		return spaceGun;
	}
	public void setSpaceGun(String[] spaceGun) {
		this.spaceGun = spaceGun;
	}
	public String getSpaceModel() {
		return spaceModel;
	}
	public void setSpaceModel(String spaceModel) {
		this.spaceModel = spaceModel;
	}
	public boolean isUseSafety() {
		return useSafety;
	}
	public void setUseSafety(boolean useSafety) {
		this.useSafety = useSafety;
	}
	public String getWellNo() {
		return wellNo;
	}
	public void setWellNo(String wellNo) {
		this.wellNo = wellNo;
	}
	
}
