package com.bupt.vouching.type;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件资源类型
 * 
 * @author Hogan
 * 
 */
public enum ResourceType {

	/**
	 * 章节资源
	 */
	CHAPTER(1,"章节信息","chapter","/vouching/template/chapter.xlsx"),
	/**
	 * 词汇资源
	 */
	GLOSSARY(2,"词汇信息","glossary","/vouching/template/glossary.xlsx"),
	/**
	 * 语句资源
	 */
	SENTENCE(3,"语句信息","sentence","/vouching/template/sentence.xlsx"),
	/**
	 * 函电资源
	 */
	CORRESPONDENCE(4,"函电信息","correspondence","/vouching/template/correspondence.xlsx"),
	;

	private int id;
	private String description;
	private String pathName;
	private String href;

	ResourceType(int id, String description, String pathName, String href) {
		this.id = id;
		this.description = description;
		this.pathName = pathName;
		this.href = href;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	private static final Map<Integer, ResourceType> DIRC = new HashMap<>();

	static {
		for (ResourceType e : ResourceType.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	public static ResourceType byId(int id) {
		return DIRC.get(id);
	}

}
