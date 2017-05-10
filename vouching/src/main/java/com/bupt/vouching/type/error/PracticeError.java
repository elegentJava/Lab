package com.bupt.vouching.type.error;

import java.util.HashMap;
import java.util.Map;

import com.bupt.vouching.type.base.ErrorType;

/**
 * 练习操作错误代码
 * 
 * @author Hogan
 * 
 */
public enum PracticeError implements ErrorType{
	
	/**
	 * 装载开始考试页面失败
	 */
	LOAD_START_FAILD(0x001, "装载开始考试页面失败!"), 
	/**
	 * 显示答案失败
	 */
	SHOW_ANSWER_FAILD(0x002,"显示答案失败!"), 
	/**
	 * 暂无相关试题
	 */
	NO_MATCH_QUESTION(0x003,"暂无相关试题!"),

	;
	
	private int id;
	private String description;

	PracticeError(int id, String description) {
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

	private static final Map<Integer, PracticeError> DIRC = new HashMap<>();

	static {
		for (PracticeError e : PracticeError.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	public static PracticeError byId(int id) {
		return DIRC.get(id);
	}
}
