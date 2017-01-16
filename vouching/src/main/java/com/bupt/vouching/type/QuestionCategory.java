package com.bupt.vouching.type;

import java.util.HashMap;
import java.util.Map;


/**
 * 题目类型
 * 
 * @author Hogan
 * 
 */
public enum QuestionCategory {

	/**
	 * 单选题
	 */
	RADIO(1, "radio", "单项选择"),
	/**
	 * 名词填空
	 */
	BLANK(2, "blank", "名词填空"),
	/**
	 * 完型填空
	 */
	CLOZE(3, "cloze", "完型填空"),
	/**
	 * 句子解释
	 */
	TRANSLATE(4, "translate", "句子解释"),
	/**
	 * 名词解释
	 */
	PHRASE(5, "phrase", "名词解释");

	private int id;
	private String tag;
	private String name;

	QuestionCategory(int id, String tag, String name) {
		this.id = id;
		this.tag = tag;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
	private static final Map<Integer, QuestionCategory> DIRC = new HashMap<>();
	
	static {
		for (QuestionCategory e : QuestionCategory.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	public static QuestionCategory byId(int id) {
		return DIRC.get(id);
	}

}
