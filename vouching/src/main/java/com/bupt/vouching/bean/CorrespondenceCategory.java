package com.bupt.vouching.bean;

/**
 * 函电类型实体
 * 
 * @author Hogan
 * 
 */
public class CorrespondenceCategory {

	private Integer ccid;
	private Integer fatherId;
	private String name;
	private Integer flag;

	public Integer getCcid() {
		return ccid;
	}

	public void setCcid(Integer ccid) {
		this.ccid = ccid;
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
