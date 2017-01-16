package com.bupt.vouching.bean;

/**
 * 函电实体
 * 
 * @author Hogan
 * 
 */
public class Correspondence {

	private Integer correspondenceId;
	private Integer categroyId;
	private String english;
	private String translate;

	public Integer getCorrespondenceId() {
		return correspondenceId;
	}

	public void setCorrespondenceId(Integer correspondenceId) {
		this.correspondenceId = correspondenceId;
	}

	public Integer getCategroyId() {
		return categroyId;
	}

	public void setCategroyId(Integer categroyId) {
		this.categroyId = categroyId;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getTranslate() {
		return translate;
	}

	public void setTranslate(String translate) {
		this.translate = translate;
	}

}
