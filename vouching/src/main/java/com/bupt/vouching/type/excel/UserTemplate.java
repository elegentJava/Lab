package com.bupt.vouching.type.excel;

import java.util.HashMap;
import java.util.Map;

import com.bupt.vouching.type.base.TemplateType;

/**
 * 用户Excel单元格
 * 
 * @author hogan
 * 
 */
public enum UserTemplate implements TemplateType{
	
	/**
	 * 没有意义
	 */
	DEFAULT(0,"没有意义"),
	/**
	 * 账号
	 */
	ACCOUNT(1,"Account"),
	/**
	 * 姓名
	 */
	NAME(2,"Name"),
	/**
	 * 性别
	 */
	SEX(3,"Sex"),
	/**
	 * 邮箱
	 */
	EMAIL(4,"Email"),
	/**
	 * 班级
	 */
	CLASS(5,"ClassId"),
	;

	private int id;
	private String description;

	UserTemplate(int id, String description) {
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

	private static final Map<Integer, UserTemplate> DIRC = new HashMap<>();

	static {
		for (UserTemplate e : UserTemplate.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	@Override
	public UserTemplate byId(int id) {
		return DIRC.get(id);
	}

}
