package com.bupt.vouching.service.bean;

/**
 * 语句类型内部类试题
 * 
 * @author Hogan
 * 
 */
public class SentenceType {

	private int id;
	private String name;

	public SentenceType(int id, String name) {
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
