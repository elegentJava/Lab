package com.bupt.vouching.bean;

import java.io.Serializable;

/**
 * 班级实体
 * 
 * @author Hogan
 * 
 */
public class Class implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer classId;
	private String bak;
	private String className;
	private Integer isActive;

	// 前台显示要用到的
	private String statusName;

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getStatusName() {
		if (this.isActive != null) {
			this.statusName = this.isActive == 1 ? "已激活" : "未激活";
		}
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getBak() {
		if (bak == null) {
			bak = "无";
		}
		return bak;
	}

	public void setBak(String bak) {
		this.bak = bak;
	}

}
