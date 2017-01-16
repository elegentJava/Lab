package com.bupt.vouching.service.bean;

/**
 * 词汇来源实体
 * 
 * @author Hogan
 * 
 */
public class GlossarySource {

	private Integer id;
	private String description;

	public GlossarySource(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
