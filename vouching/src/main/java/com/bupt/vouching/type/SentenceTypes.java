package com.bupt.vouching.type;

import java.util.HashMap;
import java.util.Map;

import com.bupt.vouching.type.base.Type;

/**
 * 语句分类类型
 * 
 * @author Hogan
 * 
 */
public enum SentenceTypes implements Type {

	/**
	 * 基本语句
	 */
	BASIC(0,"基本语句"),
	/**
	 * 委婉程度
	 */
	EUPHEMISTIC(1,"委婉程度"),
	/**
	 * 常用程度
	 */
	COMMON(2,"常用程度"),
	;

	private int id;
	private String description;

	SentenceTypes(int id, String description) {
		this.id = id;
		this.description = description;
	}

	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private static final Map<Integer, SentenceTypes> DIRC = new HashMap<>();

	static {
		for (SentenceTypes e : SentenceTypes.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	public static SentenceTypes byId(int id) {
		return DIRC.get(id);
	}

}
