package com.bupt.vouching.type;

import java.util.HashMap;
import java.util.Map;

import com.bupt.vouching.type.base.Type;

/**
 * 难度等级
 * 
 * @author Hogan
 * 
 */
public enum LevelType implements Type {

	/**
	 * 简单
	 */
	ESAY(0,"简单"),
	/**
	 * 中等
	 */
	MIDDLE(1,"中等"),
	/**
	 * 困难
	 */
	HIGH(2,"困难"),
	;

	private int id;
	private String description;

	LevelType(int id, String description) {
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

	private static final Map<Integer, LevelType> DIRC = new HashMap<>();

	static {
		for (LevelType e : LevelType.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	public static LevelType byId(int id) {
		return DIRC.get(id);
	}

}
