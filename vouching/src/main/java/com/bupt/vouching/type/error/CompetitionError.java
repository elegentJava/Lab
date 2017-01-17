package com.bupt.vouching.type.error;

import java.util.HashMap;
import java.util.Map;

import com.bupt.vouching.type.base.ErrorType;

/**
 * 竞技操作错误代码
 * 
 * @author Hogan
 * 
 */
public enum CompetitionError implements ErrorType{
	
	/**
	 * 匹配还未完成
	 */
	MATCH_NOT_COMPLETE(0x001,"matchNotComplete"), 
	;
	
	private int id;
	private String description;

	CompetitionError(int id, String description) {
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

	private static final Map<Integer, CompetitionError> DIRC = new HashMap<>();

	static {
		for (CompetitionError e : CompetitionError.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	public static CompetitionError byId(int id) {
		return DIRC.get(id);
	}
}
