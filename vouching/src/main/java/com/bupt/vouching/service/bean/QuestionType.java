package com.bupt.vouching.service.bean;

/**
 * 问题类型实体
 * 
 * @author Hogan
 * 
 */
public class QuestionType {

	private Integer id;
	private String tag;
	private String name;

	public QuestionType(Integer id, String tag, String name) {
		this.id = id;
		this.tag = tag;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
