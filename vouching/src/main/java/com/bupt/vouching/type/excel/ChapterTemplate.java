package com.bupt.vouching.type.excel;

import java.util.HashMap;
import java.util.Map;

import com.bupt.vouching.type.base.TemplateType;

/**
 * 章节Excel单元格
 * 
 * @author hogan
 * 
 */
public enum ChapterTemplate implements TemplateType{
	
	/**
	 * 没有意义
	 */
	DEFAULT(0,"没有意义"),
	/**
	 * 章节名称
	 */
	NAME(1,"Name")
	;

	private int id;
	private String description;

	ChapterTemplate(int id, String description) {
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

	private static final Map<Integer, ChapterTemplate> DIRC = new HashMap<>();

	static {
		for (ChapterTemplate e : ChapterTemplate.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	@Override
	public ChapterTemplate byId(int id) {
		return DIRC.get(id);
	}

}
