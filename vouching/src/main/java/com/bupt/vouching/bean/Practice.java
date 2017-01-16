package com.bupt.vouching.bean;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.bupt.vouching.frame.Consts;

/**
 * 练习实体
 * 
 * @author Hogan
 * 
 */
public class Practice {

	private Integer practiceId;
	private Integer score;
	private Date date;
	private Integer userId;
	private Integer chapterId;

	private Chapter chapter;
	private String chapterName;
	private String formatDate;

	public Integer getPracticeId() {
		return practiceId;
	}

	public void setPracticeId(Integer practiceId) {
		this.practiceId = practiceId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getChapterId() {
		return chapterId;
	}

	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}

	public String getChapterName() {
		if (this.chapter != null) {
			chapterName = chapter.getName();
		}
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	
	public String getFormatDate() {
		if(this.date != null){
			formatDate = DateFormatUtils.format(this.date, Consts.DATE_SIMPLE_PATTERN);
		}
		return formatDate;
	}

	public void setFormatDate(String formatDate) {
		this.formatDate = formatDate;
	}

}
