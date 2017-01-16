package com.bupt.vouching.type;

import java.util.HashMap;
import java.util.Map;

import com.bupt.vouching.type.base.Type;

/**
 * 选项类型
 * 
 * @author Hogan
 * 
 */
public enum OptionType implements Type {

	OPTION_A(1,"A"),
	OPTION_B(2,"B"),
	OPTION_C(3,"C"),
	OPTION_D(4,"D"),
	
	;
	
	private int id;
	private String description;

	OptionType(int id, String description) {
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

	private static final Map<Integer, OptionType> DIRC = new HashMap<>();

	static {
		for (OptionType e : OptionType.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	public static OptionType byId(int id) {
		return DIRC.get(id);
	}

}
