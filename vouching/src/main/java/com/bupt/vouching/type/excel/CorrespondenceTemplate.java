package com.bupt.vouching.type.excel;

import java.util.HashMap;
import java.util.Map;

import com.bupt.vouching.type.base.TemplateType;

/**
 * 函电Excel单元格
 * 
 * @author hogan
 * 
 */
public enum CorrespondenceTemplate implements TemplateType{
	
	/**
	 * 没有意义
	 */
	DEFAULT(0,"没有意义"),
	;

	private int id;
	private String description;

	CorrespondenceTemplate(int id, String description) {
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

	private static final Map<Integer, CorrespondenceTemplate> DIRC = new HashMap<>();

	static {
		for (CorrespondenceTemplate e : CorrespondenceTemplate.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	@Override
	public CorrespondenceTemplate byId(int id) {
		return DIRC.get(id);
	}

}
