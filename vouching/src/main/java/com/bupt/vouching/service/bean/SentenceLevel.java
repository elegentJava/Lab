package com.bupt.vouching.service.bean;

/**
 * 语句等级内部类试实体
 * 
 * @author Hogan
 * 
 */
public class SentenceLevel {

	private int id;
	private String name;

	public SentenceLevel(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
