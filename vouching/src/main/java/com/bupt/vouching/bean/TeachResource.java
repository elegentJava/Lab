package com.bupt.vouching.bean;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.bupt.vouching.frame.Consts;

/**
 * 教学资源实体
 * 
 * @author Hogan
 * 
 */
public class TeachResource {

	private Integer trid;
	private String path;
	private Integer userId;
	private Integer type;
	private Integer flag;
	private Date date;

	private User user;
	private String formatDate;
	private String flagName;

	public Integer getTrid() {
		return trid;
	}

	public void setTrid(Integer trid) {
		this.trid = trid;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFormatDate() {
		if (this.date != null) {
			formatDate = DateFormatUtils.format(this.date, Consts.DATE_SIMPLE_PATTERN);
		}
		return formatDate;
	}

	public void setFormatDate(String formatDate) {
		this.formatDate = formatDate;
	}

	public String getFlagName() {
		if(this.flag != null){
			flagName = this.flag == 1 ? "已经处理" : "尚未处理";
		}
		return flagName;
	}

	public void setFlagName(String flagName) {
		this.flagName = flagName;
	}

}
