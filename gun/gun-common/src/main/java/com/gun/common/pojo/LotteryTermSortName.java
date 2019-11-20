package com.gun.common.pojo;
public class LotteryTermSortName extends ValueObject<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4017937811125645751L;
	private int termLvl;
	private int termSort;
	private String termName;
	
	
	/**
	 * Constructor:
	 */
	public LotteryTermSortName() {
		super();
	}
	/**
	 * Constructor:
	 */
	public LotteryTermSortName(int termLvl, int termSort, String termName) {
		super();
		this.termLvl = termLvl;
		this.termSort = termSort;
		this.termName = termName;
	}
	/**
	 * @return the termLvl
	 */
	public int getTermLvl() {
		return termLvl;
	}
	/**
	 * @param termLvl the termLvl to set
	 */
	public void setTermLvl(int termLvl) {
		this.termLvl = termLvl;
	}
	/**
	 * @return the termSort
	 */
	public int getTermSort() {
		return termSort;
	}
	/**
	 * @param termSort the termSort to set
	 */
	public void setTermSort(int termSort) {
		this.termSort = termSort;
	}
	/**
	 * @return the termName
	 */
	public String getTermName() {
		return termName;
	}
	/**
	 * @param termName the termName to set
	 */
	public void setTermName(String termName) {
		this.termName = termName;
	}

}
