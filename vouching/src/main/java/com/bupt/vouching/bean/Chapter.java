package com.bupt.vouching.bean;

/**
 * 章节实体
 * 
 * @author Hogan
 * 
 */
public class Chapter {

	private Integer chapterId;
	private String name;
	private Integer isActive;

	public Integer getChapterId() {
		return chapterId;
	}

	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

}
