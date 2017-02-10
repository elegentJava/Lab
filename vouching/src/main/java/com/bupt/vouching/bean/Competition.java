package com.bupt.vouching.bean;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.bupt.vouching.frame.Consts;

/**
 * 竞技实体
 * 
 * @author Hogan
 * 
 */
public class Competition {

	private Integer id;
	private Integer score;
	private Integer userId;
	private Date date;

	private String formatDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

}
