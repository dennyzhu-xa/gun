package com.gun.common.entity;

import com.gun.common.pojo.ValueObject;

public class WellLevel extends ValueObject<Integer> {
	private static final long serialVersionUID = 1L;
	private int wellId;
	private double oilStart;
	private double oilEnd;
	private double levelDepth;
	private double backinDepth;
	public double deepSum;
	public double backfinSum;
	public int getWellId() {
		return wellId;
	}
	public void setWellId(int wellId) {
		this.wellId = wellId;
	}
	public double getOilStart() {
		return oilStart;
	}
	public void setOilStart(double oilStart) {
		this.oilStart = oilStart;
	}
	public double getOilEnd() {
		return oilEnd;
	}
	public void setOilEnd(double oilEnd) {
		this.oilEnd = oilEnd;
	}
	public double getLevelDepth() {
		return levelDepth;
	}
	public void setLevelDepth(double levelDepth) {
		this.levelDepth = levelDepth;
	}
	public double getBackinDepth() {
		return backinDepth;
	}
	public void setBackinDepth(double backinDepth) {
		this.backinDepth = backinDepth;
	}
	public double getDeepSum() {
		return deepSum;
	}
	public void setDeepSum(double deepSum) {
		this.deepSum = deepSum;
	}
	public double getBackfinSum() {
		return backfinSum;
	}
	public void setBackfinSum(double backfinSum) {
		this.backfinSum = backfinSum;
	}

}
