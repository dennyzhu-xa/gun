package com.gun.common.entity.pojo;
// Generated 2017-6-1 18:10:41 by Hibernate Tools 4.0.1.Final

import com.gun.common.pojo.ValueObject;

/**
 * SysUserInfo generated by hbm2java
 */
public class QuestionsAndAnswersDTO extends ValueObject<Integer> {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -5855746927914564749L;

	//枚举
	public static enum ATTRIBUTE {
		ID("id"),
		CATEGORY("category"),
		CATEGORYNAME("categoryName"),
		QUESTION("question"),
		ANSWER("answer"),
	    CREATE_BY_ID("createById"),
	    CREATED_BY_NAME("createdByName"),
	    CREATED_DATE("createdDate"),
	    UPDATED_BYID("updatedById"),
	    UPDATED_BY_NAME("updatedByName"),
	    UPDATED_DATE("updatedDate")
	    ;
	    private String value;
	    ATTRIBUTE(String value) {
	      this.value = value;
	    };
	    public String getValue() {
	      return this.value;
	    }
	};
	private String category;//類別
	private String categoryName;//类别名称
	private String question;//问题/栏位
	private String answer;//答案/内容
	private String createById;//创建人id
	private String createdByName;//创建人name
	private String createdString;//创建日期
	private String upStringdById;//异动人id
	private String upStringdByName;//异动人name
	private String upStringdString;//异动时间
	
	/**
	 * Constructor:无参构造
	 */
	public QuestionsAndAnswersDTO() {
		
	}

	/**
	 * Constructor:有参构造
	 */
	public QuestionsAndAnswersDTO(String question, String answer, String category,
			String createById, String createdByName, String createdString,String categoryName,
			String upStringdById, String upStringdByName, String upStringdString) {
		super();
		this.question = question;
		this.category = category;
		this.categoryName = categoryName;
		this.answer = answer;
		this.createById = createById;
		this.createdByName = createdByName;
		this.createdString = createdString;
		this.upStringdById = upStringdById;
		this.upStringdByName = upStringdByName;
		this.upStringdString = upStringdString;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * @return the createById
	 */
	public String getCreateById() {
		return createById;
	}

	/**
	 * @param createById the createById to set
	 */
	public void setCreateById(String createById) {
		this.createById = createById;
	}

	/**
	 * @return the createdByName
	 */
	public String getCreatedByName() {
		return createdByName;
	}

	/**
	 * @param createdByName the createdByName to set
	 */
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	/**
	 * @return the createdString
	 */
	public String getCreatedString() {
		return createdString;
	}

	/**
	 * @param createdString the createdString to set
	 */
	public void setCreatedString(String createdString) {
		this.createdString = createdString;
	}

	/**
	 * @return the upStringdById
	 */
	public String getUpStringdById() {
		return upStringdById;
	}

	/**
	 * @param upStringdById the upStringdById to set
	 */
	public void setUpStringdById(String upStringdById) {
		this.upStringdById = upStringdById;
	}

	/**
	 * @return the upStringdByName
	 */
	public String getUpStringdByName() {
		return upStringdByName;
	}

	/**
	 * @param upStringdByName the upStringdByName to set
	 */
	public void setUpStringdByName(String upStringdByName) {
		this.upStringdByName = upStringdByName;
	}

	/**
	 * @return the upStringdString
	 */
	public String getUpStringdString() {
		return upStringdString;
	}

	/**
	 * @param upStringdString the upStringdString to set
	 */
	public void setUpStringdString(String upStringdString) {
		this.upStringdString = upStringdString;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
