package com.bupt.vouching.service.bean;

/**
 * 问题难易程度实体
 * 
 * @author Hogan
 * 
 */
public class QuestionLevel {

	private Integer id;
	private String name;

	public QuestionLevel(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
