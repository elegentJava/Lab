package com.bupt.vouching.bean;

/**
 * 语句类型实体
 * 
 * @author Hogan
 * 
 */
public class SentenceCategory {

	private Integer scid;
	private Integer fatherId;
	private String name;
	private Integer flag;

	public Integer getScid() {
		return scid;
	}

	public void setScid(Integer scid) {
		this.scid = scid;
	}

	public Integer getFatherId() {
		return fatherId;
	}

	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
}
