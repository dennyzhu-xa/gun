package com.gun.common.entity.pojo;

public class OilLevelDTO {
	
	public static final String TYPE_OIL			= "O";
	public static final String TYPE_SPACE		= "S";
	
	public double startLevel;
	
	public double endLevel;
	
	public double deep;
	
	public double backinDepth;
	
	public double deepSum;
	
	public double backfinSum;
	// 用于分辨夹层或是油层，空或是O为油层，S为夹层
	public String type;

	public OilLevelDTO() {
	}
	
	public OilLevelDTO(double startLevel, double endLevel,double deep) {
		super();
		this.startLevel = startLevel;
		this.endLevel = endLevel;
		this.deep = deep;
	}

	
	
	public OilLevelDTO(double startLevel, double endLevel, double deep,
			String type) {
		super();
		this.startLevel = startLevel;
		this.endLevel = endLevel;
		this.deep = deep;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getStartLevel() {
		return startLevel;
	}

	public void setStartLevel(double startLevel) {
		this.startLevel = startLevel;
	}

	public double getEndLevel() {
		return endLevel;
	}

	public void setEndLevel(double endLevel) {
		this.endLevel = endLevel;
	}

	public double getDeep() {
		return deep;
	}

	public void setDeep(double deep) {
		this.deep = deep;
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
