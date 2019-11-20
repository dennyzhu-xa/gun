package com.gun.common.entity;

import java.sql.Timestamp;

import com.gun.common.pojo.ValueObject;

public class OilWell extends ValueObject<Integer> {

	private String wellType;
	private String ownName;
	private String oilNo;
	private String area;
	private String manualDeep;
	private int levelQuanlity;
	private String surfaceTemperature;
	private String oilTemperature;
	private String equipmentModelId ;
	private String equipments;
	private String spacerModelId;
	private String spacers;
	private String perforatingId;
	private String connectId;
	private String phase;
	private double extraLength;
	private char use3M;
	private int safetyLength;
	private String techRemark;
	private String createById;
	private String createdByName;
	private Timestamp createdDate;
	private String updatedById;
	private String updatedByName;
	private Timestamp updatedDate;
	public String getWellType() {
		return wellType;
	}
	public void setWellType(String wellType) {
		this.wellType = wellType;
	}
	public String getOwnName() {
		return ownName;
	}
	public void setOwnName(String ownName) {
		this.ownName = ownName;
	}
	public String getOilNo() {
		return oilNo;
	}
	public void setOilNo(String oilNo) {
		this.oilNo = oilNo;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getManualDeep() {
		return manualDeep;
	}
	public void setManualDeep(String manualDeep) {
		this.manualDeep = manualDeep;
	}
	public int getLevelQuanlity() {
		return levelQuanlity;
	}
	public void setLevelQuanlity(int levelQuanlity) {
		this.levelQuanlity = levelQuanlity;
	}
	public String getSurfaceTemperature() {
		return surfaceTemperature;
	}
	public void setSurfaceTemperature(String surfaceTemperature) {
		this.surfaceTemperature = surfaceTemperature;
	}
	public String getOilTemperature() {
		return oilTemperature;
	}
	public void setOilTemperature(String oilTemperature) {
		this.oilTemperature = oilTemperature;
	}
	public String getEquipmentModelId() {
		return equipmentModelId;
	}
	public void setEquipmentModelId(String equipmentModelId) {
		this.equipmentModelId = equipmentModelId;
	}
	public String getEquipments() {
		return equipments;
	}
	public void setEquipments(String equipments) {
		this.equipments = equipments;
	}
	public String getSpacerModelId() {
		return spacerModelId;
	}
	public void setSpacerModelId(String spacerModelId) {
		this.spacerModelId = spacerModelId;
	}
	public String getSpacers() {
		return spacers;
	}
	public void setSpacers(String spacers) {
		this.spacers = spacers;
	}
	public String getPerforatingId() {
		return perforatingId;
	}
	public void setPerforatingId(String perforatingId) {
		this.perforatingId = perforatingId;
	}
	public String getConnectId() {
		return connectId;
	}
	public void setConnectId(String connectId) {
		this.connectId = connectId;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public double getExtraLength() {
		return extraLength;
	}
	public void setExtraLength(double extraLength) {
		this.extraLength = extraLength;
	}
	public char getUse3M() {
		return use3M;
	}
	public void setUse3M(char use3m) {
		use3M = use3m;
	}
	public int getSafetyLength() {
		return safetyLength;
	}
	public void setSafetyLength(int safetyLength) {
		this.safetyLength = safetyLength;
	}
	public String getTechRemark() {
		return techRemark;
	}
	public void setTechRemark(String techRemark) {
		this.techRemark = techRemark;
	}
	public String getCreateById() {
		return createById;
	}
	public void setCreateById(String createById) {
		this.createById = createById;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedById() {
		return updatedById;
	}
	public void setUpdatedById(String updatedById) {
		this.updatedById = updatedById;
	}
	public String getUpdatedByName() {
		return updatedByName;
	}
	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
