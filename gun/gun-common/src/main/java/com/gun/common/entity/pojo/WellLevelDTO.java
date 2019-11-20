package com.gun.common.entity.pojo;
// Generated 2017-6-1 18:10:41 by Hibernate Tools 4.0.1.Final

import java.sql.Timestamp;

import com.gun.common.pojo.ValueObject;

public class WellLevelDTO extends ValueObject<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static enum ATTRIBUTE {
		ID("id"),
		WELL_ID("wellId"),
		OIL_START("oilStart"),
		OIL_END("oilEnd"),
		LEVEL_DEPTH("levelDepth"),
		DEEP_SUM("deepSum"),
		BACKFIN_SUM("backfinSum"),
		BACKIN_DEPTH("backinDepth");
	    
	    private String value;
	    ATTRIBUTE(String value) {
	      this.value = value;
	    };
	    public String getValue() {
	      return this.value;
	    }
	}
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


	public static void main(String args[]){
		java.lang.reflect.Field[] f  = WellLevelDTO.class.getDeclaredFields();
		String sub = "";
		for(int i=0;i<f.length;i++){
			// USER_ID("userId"),
			String name = f[i].getName();
			char[] c = name.toCharArray();
			for(char ci : c){
				// <91为大写，在其前方加_
				if((int)ci < 91){
					sub += "_";
				}
				sub += String.valueOf(ci).toUpperCase();
			}
			sub += "(\"" + name + "\"),";
			sub += "\n";
		}
		System.out.println(sub);
	}
}
