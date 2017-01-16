package com.bupt.vouching.type.excel;

import java.util.HashMap;
import java.util.Map;

import com.bupt.vouching.type.base.TemplateType;

/**
 * 语句Excel单元格
 * 
 * @author hogan
 * 
 */
public enum SentenceTemplate implements TemplateType{
	
	/**
	 * 没有意义
	 */
	DEFAULT(0,"没有意义"),
	;

	private int id;
	private String description;

	SentenceTemplate(int id, String description) {
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

	private static final Map<Integer, SentenceTemplate> DIRC = new HashMap<>();

	static {
		for (SentenceTemplate e : SentenceTemplate.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	@Override
	public SentenceTemplate byId(int id) {
		return DIRC.get(id);
	}

}
