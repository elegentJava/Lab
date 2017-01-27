package com.bupt.vouching.bean;

import java.util.Date;

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

}
