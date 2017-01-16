package com.bupt.vouching.type;

import java.util.HashMap;
import java.util.Map;

import com.bupt.vouching.type.base.Type;

/**
 * 词汇来源
 * 
 * @author Hogan
 * 
 */
public enum GlossarySourceType implements Type {

	/**
	 * 常见缩略词
	 */
	COMMON(1, "常见缩略词"),
	/**
	 * 常见缩略词
	 */
	PRODUCT(2, "产品词汇"),
	/**
	 * 函电常用词汇
	 */
	USUAL(3, "函电常用词汇"),
	/**
	 * 营销英语词汇
	 */
	MARKETING(4, "营销英语词汇"),
	/**
	 * 知名品牌及机构名称
	 */
	BRAND(5, "知名品牌及机构名称"),
	/**
	 * 常用职位英语名称
	 */
	POSITION(6, "常用职位英语名称"),
	/**
	 * 外贸单证词汇
	 */
	TRADE(7, "外贸单证词汇"),
	/**
	 * 其他词汇
	 */
	OTHER(8, "其他词汇");

	private int id;
	private String description;

	GlossarySourceType(int id, String description) {
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

	private static final Map<Integer, GlossarySourceType> DIRC = new HashMap<>();

	static {
		for (GlossarySourceType e : GlossarySourceType.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	public static GlossarySourceType byId(int id) {
		return DIRC.get(id);
	}

}
