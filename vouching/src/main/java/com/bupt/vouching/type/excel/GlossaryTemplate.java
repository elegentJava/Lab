package com.bupt.vouching.type.excel;

import java.util.HashMap;
import java.util.Map;

import com.bupt.vouching.type.base.TemplateType;

/**
 * 词汇Excel单元格
 * 
 * @author hogan
 * 
 */
public enum GlossaryTemplate implements TemplateType{
	
	/**
	 * 没有意义
	 */
	DEFAULT(0,"没有意义"),
	/**
	 * 原词
	 */
	ORIGINAL_WORD(1,"OriginalWord"),
	/**
	 * 翻译
	 */
	TRANSLATE(2,"Translate"),
	/**
	 * 词汇来源
	 */
	SOURCE(3,"Source")
	;

	private int id;
	private String description;

	GlossaryTemplate(int id, String description) {
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

	private static final Map<Integer, GlossaryTemplate> DIRC = new HashMap<>();

	static {
		for (GlossaryTemplate e : GlossaryTemplate.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	@Override
	public GlossaryTemplate byId(int id) {
		return DIRC.get(id);
	}

}
